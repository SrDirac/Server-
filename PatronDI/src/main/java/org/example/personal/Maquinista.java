package org.example.personal;

public class Maquinista  {

    private String nombreCompleto;
    private String dni;
    private double sueldoMensual;
    private String rango;

    public Maquinista(String nombreCompleto, String dni, double sueldoMensual, String rango) {
        this.nombreCompleto = nombreCompleto;
        this.dni = dni;
        this.sueldoMensual = sueldoMensual;
        this.rango = rango;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public double getSueldoMensual() {
        return sueldoMensual;
    }

    public void setSueldoMensual(double sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }
}
