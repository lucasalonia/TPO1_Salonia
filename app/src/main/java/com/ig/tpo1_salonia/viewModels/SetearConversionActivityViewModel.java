package com.ig.tpo1_salonia.viewModels;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.ig.tpo1_salonia.models.Conversion;
import com.ig.tpo1_salonia.views.MainActivity;

public class SetearConversionActivityViewModel extends AndroidViewModel {
    private Conversion conversion;
    private MutableLiveData<Conversion> conversionMutable;
    public SetearConversionActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Conversion> getConversionMutable() {

        if(conversionMutable==null){
            conversionMutable= new MutableLiveData<>();

        }
        return conversionMutable;
    }

    /**
     * Guarda los datos ingresados por el usuario en el objeto de tipo mutable con parametro de
     * la clase conversion
     * @param dolar valor ingresado por el usuario. Determinará la llave del mapa
     * @param peso valor ingresado por el usuario. Determinará el valor asociado a la llave
     */
    public void guardarConversionYVolver(String dolar, String peso) {

        if (!dolar.isEmpty() && !peso.isEmpty()) {
            try {
                Double dClave = Double.parseDouble(dolar);
                Double pValor = Double.parseDouble(peso);

                Conversion c = new Conversion();
                c.agregarTasa(dClave, pValor);

                conversionMutable.setValue(c);
                volverAMain(c);
            }catch (NumberFormatException e){
                Log.e("ERROR_CONVERSION", "Formato de numero invalido en guardarConversionYVolver()");
            }
        }
    }

    /**
     * Si en la vista principal hay informacion sobre la tasa este metodo la recibe y configura los
     * valores para el objeto mutable
     */
    public void recibirDatosDeNavegacion(Intent intent) {
        if (intent != null && intent.hasExtra("converion_para_modificar")) {
            Conversion c = (Conversion) intent.getSerializableExtra("converion_para_modificar");

            if (c != null) {
                conversionMutable.setValue(c);
            }
        }
    }

    /**
     * Vuelve al main y envia un objeto de tipo Conversion para que el "MainActivityViewModel" lo
     * guarde en su mutable local
     * @param conversion objeto a comunicar
     */
    public void volverAMain(Conversion conversion){
        if (conversion != null) {
            Intent resultado = new Intent(getApplication(), MainActivity.class);
            resultado.putExtra("conversion_nueva", conversion);
            resultado.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplication().startActivity(resultado);


        }
    }
}
