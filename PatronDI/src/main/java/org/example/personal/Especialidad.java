package org.example.personal;

public enum Especialidad {
    frenos,
    hidraulicos;

    public static Especialidad validarEspecialidad(String especialidad){
        for (Especialidad especialidad1: Especialidad.values()){
            if (especialidad1.name().equalsIgnoreCase(especialidad)){
                return especialidad1;
            }
        }
        throw new IllegalArgumentException("Especiallidad no valida "+especialidad);
    }
}
