package com.ig.tpo1_salonia.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ig.tpo1_salonia.R;
import com.ig.tpo1_salonia.databinding.ActivitySetearConversionBinding;
import com.ig.tpo1_salonia.models.Conversion;
import com.ig.tpo1_salonia.viewModels.SetearConversionActivityViewModel;

import java.util.HashMap;
import java.util.Map;

public class SetearConversionActivity extends AppCompatActivity {
    private ActivitySetearConversionBinding binding;
    private SetearConversionActivityViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetearConversionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(SetearConversionActivityViewModel.class);
        binding.btAplicarTipoCambio.setOnClickListener(v -> {
            String peso = binding.etEditPeso.getText().toString();
            String dolar = binding.etEditDolar.getText().toString();
            viewModel.guardarConversionYVolver(dolar,peso);

        });
        viewModel.getConversionMutable().observe(this, new Observer<Conversion>() {
            @Override
            public void onChanged(Conversion conversion) {

                HashMap<Double, Double> mapa = conversion.getTasasDeCambio();
                for (Map.Entry<Double, Double> entrada : mapa.entrySet()) {
                    Double dolar = entrada.getKey();
                    Double peso = entrada.getValue();
                    binding.etEditDolar.setText(dolar.toString());
                    binding.etEditPeso.setText(peso.toString());
                }
            }
        });
        viewModel.recibirDatosDeNavegacion(getIntent());
    }
}