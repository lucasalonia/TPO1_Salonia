package com.ig.tpo1_salonia.viewModels;

import android.app.Application;
import android.content.Intent;

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
    public void guardarConversionYVolver(String dolar, String peso) {
        if (!dolar.isEmpty() && !peso.isEmpty()) {
            Double dClave = Double.parseDouble(dolar);
            Double pValor = Double.parseDouble(peso);

            Conversion c = new Conversion();
            c.agregarTasa(dClave, pValor);

            getConversionMutable().setValue(c);
        }
    }

    public void volverAMain(Conversion conversion){
        if (conversion != null) {
            Intent resultado = new Intent(getApplication(), MainActivity.class);
            resultado.putExtra("conversion_nueva", conversion);
            resultado.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplication().startActivity(resultado);


        }
    }
}
