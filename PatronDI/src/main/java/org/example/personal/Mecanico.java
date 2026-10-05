package org.example.personal;

public class Mecanico {

    private String nombreCompleto;
    private String  tlfno;
    private Especialidad especialidad; //Solo puede valer frenos o hidraulicos

    public Mecanico(String nombreCompleto, String tlfno, String  especialidad) {
        this.nombreCompleto = nombreCompleto;
        this.tlfno = tlfno;
        this.especialidad = Especialidad.validarEspecialidad(especialidad);
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTlfno() {
        return tlfno;
    }

    public void setTlfno(String tlfno) {
        this.tlfno = tlfno;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }
}

