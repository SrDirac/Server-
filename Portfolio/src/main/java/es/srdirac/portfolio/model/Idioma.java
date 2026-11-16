package es.srdirac.portfolio.model;

import java.util.List;

public record Idioma(String nombre, String nivel) {
    //Mismo funcionamiento que habilidades, si no forma parte de la lista el valor introducido de nivel, lanza excepción asi como si es nulo
    private static final List<String> NIVELES = List.of("alto", "medio", "bajo");

    public Idioma {
        if (nivel == null || !NIVELES.contains(nivel)) {
            throw new IllegalArgumentException("Nivel erróneo: " + nivel);
        }
    }
}
