package com.ig.tpo1_salonia.models;

import java.io.Serializable;
import java.util.HashMap;

public class Conversion implements Serializable {
    private double valorConvertido;

    /**
     * Se opto por clave valor. Me parecio una solucion mas concreta aunque hay que hacer un foreach
     * siempre que se quiera buscar el unico registro del mapa
     */
    private HashMap<Double, Double> tasasDeCambio;

    public Conversion() {
        this.tasasDeCambio = new HashMap<>();
    }

    public void agregarTasa(double valorDolar, double valorPeso) {
        this.tasasDeCambio.put(valorDolar, valorPeso);
    }

    public double getValorConvertido() {
        return valorConvertido;
    }

    public void setValorConvertido(double valorConvertido) {
        this.valorConvertido = valorConvertido;
    }

    public HashMap<Double, Double> getTasasDeCambio() {
        return tasasDeCambio;
    }
}