package es.srdirac.portfolio.model;

import java.util.List;

public record Habilidad(String nombre, String nivel) {
    //Tiene una propia validacion de constructor de niveles, si no es uno de los valores de la lista  o si es nulo
    //lanza IllegalArgumentException
    private static final List<String> NIVELES= List.of("alto","medio","bajo");

    public Habilidad{
        if(nivel==null || !NIVELES.contains(nivel)){
            throw new IllegalArgumentException("Nivel erróneo: " + nivel);
        }
    }
}
