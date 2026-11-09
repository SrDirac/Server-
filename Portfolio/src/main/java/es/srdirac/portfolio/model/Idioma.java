package es.srdirac.portfolio.model;

import java.util.List;

public record Idioma(String nombre, String nivel) {
    private static final List<String> NIVELES = List.of("alto", "medio", "bajo");

    public Idioma {
        if (nivel == null || !NIVELES.contains(nivel)) {
            throw new IllegalArgumentException("Nivel erróneo: " + nivel);
        }
    }
}
