package com.ig.tpo1_salonia.viewModels;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ig.tpo1_salonia.models.Conversion;
import com.ig.tpo1_salonia.views.SetearConversionActivity;

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
    public void recibirDatosDeNavegacion(Intent intent) {
        if (intent != null && intent.hasExtra("conversion_nueva")) {
            Conversion c = (Conversion) intent.getSerializableExtra("conversion_nueva");

            if (c != null) {
                conversionMutable.setValue(c);
            }
        }
    }
    public void test(){

    }
}
