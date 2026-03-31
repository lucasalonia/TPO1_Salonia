package com.ig.tpo1_salonia.viewModels;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ig.tpo1_salonia.models.Conversion;
import com.ig.tpo1_salonia.views.MainActivity;
import com.ig.tpo1_salonia.views.SetearConversionActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivityViewModel extends AndroidViewModel {
    private MutableLiveData<Conversion> conversionMutable;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Conversion> getConversionMutable() {
        if(conversionMutable==null){
            conversionMutable= new MutableLiveData<>();

        }
        return conversionMutable;
    }
    public void cargarConversionInicial(){
        if(conversionMutable.getValue() == null){
            Intent i = new Intent(getApplication(), SetearConversionActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplication().startActivity(i);

        }
    }
    public void enviarDatosDeConversion(){
        if(conversionMutable!=null){
            Intent resultado = new Intent(getApplication(), SetearConversionActivity.class);
            Conversion c = conversionMutable.getValue();
            resultado.putExtra("converion_para_modificar", c);
            resultado.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplication().startActivity(resultado);
        }
    }
    public void recibirDatosDeNavegacion(Intent intent) {
        if (intent != null && intent.hasExtra("conversion_nueva")) {
            Conversion c = (Conversion) intent.getSerializableExtra("conversion_nueva");

            if (c != null) {
                conversionMutable.setValue(c);
            }
        }
    }
    public void convertir(String etDolar, String etPeso, boolean esPesoADolar, boolean esDolarAPeso) {
        Conversion conversion = getConversionMutable().getValue();
        if (conversion == null) return;

        HashMap<Double, Double> mapa = conversion.getTasasDeCambio();

        Log.d("TAG", "fefefe");
        double refDolar = 0;
        double refPeso = 0;
        for (Map.Entry<Double, Double> entrada : mapa.entrySet()) {
            refDolar = entrada.getKey();
            refPeso = entrada.getValue();
        }

        try {
            if (esPesoADolar && !etPeso.isEmpty()) {
                double p = Double.parseDouble(etPeso);
                double resultado = (p * refDolar) / refPeso;
                conversion.setValorConvertido(resultado);
                conversionMutable.setValue(conversion);

            } else if (esDolarAPeso && !etDolar.isEmpty()) {
                double d = Double.parseDouble(etDolar);
                double resultado = (d * refPeso) / refDolar;
                Log.d("TAG", resultado+"fefefe");
                conversion.setValorConvertido(resultado);
                conversionMutable.setValue(conversion);
            }
        } catch (NumberFormatException e) {
            Log.e("ERROR_CONV", "Error al parsear el monto");
        }
    }
}

