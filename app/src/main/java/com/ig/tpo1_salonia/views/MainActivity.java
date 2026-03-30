package com.ig.tpo1_salonia.views;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ig.tpo1_salonia.R;
import com.ig.tpo1_salonia.databinding.ActivityMainBinding;
import com.ig.tpo1_salonia.models.Conversion;
import com.ig.tpo1_salonia.viewModels.MainActivityViewModel;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mainBinding;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        mainActivityViewModel.getConversionMutable().observe(this, new Observer<Conversion>() {
            @Override
            public void onChanged(Conversion conversion) {
                HashMap<Double, Double> mapa = conversion.getTasasDeCambio();

                for (Map.Entry<Double, Double> entrada : mapa.entrySet()) {
                    Double dolar = entrada.getKey();
                    Double peso = entrada.getValue();
                    mainBinding.tvTasa.setText(dolar+" USD = "+peso +" ARS");
                }
            }
        });

        mainActivityViewModel.recibirDatosDeNavegacion(getIntent());

        mainActivityViewModel.cargarConversionInicial();
        mainActivityViewModel.test();


    }
}