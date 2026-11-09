package es.srdirac.portfolio.model;

import java.util.List;

public record Habilidad(String nombre, String nivel) {

    private static final List<String> NIVELES= List.of("alto","medio","bajo");

    public Habilidad{
        if(nivel==null || !NIVELES.contains(nivel)){
            throw new IllegalArgumentException("Nivel erróneo: " + nivel);
        }
    }
}
