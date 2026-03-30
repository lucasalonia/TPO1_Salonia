package com.ig.tpo1_salonia.models;

import java.io.Serializable;
import java.util.HashMap;

public class Conversion implements Serializable {
    private double dolarAConvertir;
    private double pesoAConvertir;
    private HashMap<Double, Double> tasasDeCambio;

    public Conversion() {
        this.tasasDeCambio = new HashMap<>();
    }

    public void agregarTasa(double valorDolar, double valorPeso) {
        this.tasasDeCambio.put(valorDolar, valorPeso);
    }

    public double getTasa(String nombre) {
        if (this.tasasDeCambio.containsKey(nombre)) {
            return this.tasasDeCambio.get(nombre);
        }
        return 0.0;
    }

    public double getDolarAConvertir() {
        return dolarAConvertir;
    }

    public void setDolarAConvertir(double dolarAConvertir) {
        this.dolarAConvertir = dolarAConvertir;
    }
    public double getPesoAConvertir() {
        return pesoAConvertir;
    }

    public void setPesoAConvertir(double pesoAConvertir) {
        this.pesoAConvertir = pesoAConvertir;
    }

    public HashMap<Double, Double> getTasasDeCambio() {
        return tasasDeCambio;
    }
}