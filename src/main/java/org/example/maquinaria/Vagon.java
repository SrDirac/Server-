package org.example.maquinaria;

public class Vagon {
    private int capacidadMax;
    private int capacidadActual;
    private String tipoMercancia;

     Vagon(int capacidadMax, int capacidadActual, String tipoMercancia) {

        if (capacidadMax >0){
            this.capacidadMax=capacidadMax;
        }else{throw new IllegalArgumentException("La carga debe ser mayor a 0");
        }

        if (capacidadActual<=this.capacidadMax) {
            this.capacidadActual=capacidadActual;

        }else {
            throw new IllegalArgumentException(("La carga es superior a la carga máxima"));
        }

        this.tipoMercancia = tipoMercancia;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public int getCapacidadActual() {
        return capacidadActual;
    }

    public String getTipoMercancia() {
        return tipoMercancia;
    }

    public void setTipoMercancia(String tipoMercancia) {
        this.tipoMercancia = tipoMercancia;
    }

}
