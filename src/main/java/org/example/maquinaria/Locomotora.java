package org.example.maquinaria;

import org.example.personal.Mecanico;

public class Locomotora implements MecanicoAsignable {
    private String matricula;
    private int potenciaMotor;
    private int anioFabricacion;
    private Mecanico mecanico;

    public Locomotora(String matricula, int potenciaMotor, int anioFabricacion, Mecanico mecanico) {
        this.matricula = matricula;
        this.potenciaMotor = potenciaMotor;
        this.anioFabricacion = anioFabricacion;
        this.mecanico = mecanico;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getPotenciaMotor() {
        return potenciaMotor;
    }

    public void setPotenciaMotor(int potenciaMotor) {
        this.potenciaMotor = potenciaMotor;
    }

    public int getAnioFabricacion() {
        return anioFabricacion;
    }

    public void setAnioFabricacion(int anioFabricacion) {
        this.anioFabricacion = anioFabricacion;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }


    @Override
    public void asignarMecanico(Mecanico mecanico) {
        this.mecanico=mecanico;
    }
}
