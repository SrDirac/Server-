package org.example;

public class Configurador {
    private String configuracion;
    private static  Configurador instancia;

    private Configurador() {
    }

    public static Configurador obtenerInstancia(){
        if(instancia==null){
            instancia= new Configurador();
        }
        return instancia;
    }

    public String getConfiguracion() {
        if (configuracion !=null){
            return configuracion;
        }
        return null;
    }

    public void setConfiguracion(String configuracion) {
        this.configuracion = configuracion;
    }
}
