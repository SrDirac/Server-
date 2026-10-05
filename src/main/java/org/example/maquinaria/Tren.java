package org.example.maquinaria;

import org.example.personal.Maquinista;
import org.example.personal.Mecanico;

import java.util.ArrayList;
import java.util.List;

public class Tren implements MaquinistaAsignable {
    private Locomotora locomotora;
    private Maquinista maquinista;
    private List <Vagon> vagones= new ArrayList<>();

    public Tren(Locomotora locomotora, Maquinista maquinista) {
        this.locomotora = locomotora;
        this.maquinista = maquinista;
    }

    public boolean estaLleno(){
        return vagones.size()>=5;
    }

    public boolean agregarVagon(int capacidadMax, int capacidadActual, String tipoMercancia){
        if(!estaLleno()){
            Vagon vagon = new Vagon(capacidadMax,capacidadActual,tipoMercancia);
            vagones.add(vagon);
            return true;
        }
        return false;
    }
    public boolean asignarvagon(Vagon vagon){
        if(!estaLleno()){
            vagones.add(vagon);
            return true;
        }
        return false;
    }


    @Override
    public void asignarMaquinista(Maquinista maquinista) {
        this.maquinista=maquinista;
    }
}
