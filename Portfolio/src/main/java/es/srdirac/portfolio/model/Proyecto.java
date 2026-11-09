package es.srdirac.portfolio.model;

import java.util.ArrayList;
import java.util.List;

public record Proyecto(
        String titulo,int anio, String categoria, String descripcion,
        String estado, List<String> tecnologias, String enlace, String imagen
) {
    private static final List<String> CATEGORIAS =List.of("aplicacion", "sitio-web", "diseno");
    private static final List<String> ESTADOS =List.of("en-curso", "terminado");


    public Proyecto{
        if(categoria==null || !CATEGORIAS.contains(categoria)){
            throw new IllegalArgumentException("Categoría errónea, selecciona una disponible");
        }

        if(estado==null || !ESTADOS.contains(estado)){
            throw new IllegalArgumentException("Estado incorrecto, selecciona uno disponible");
        }
        if (tecnologias == null) {
            tecnologias = List.of();
        }
    }

    public boolean cumpleFiltro(String filtroCategoria, Integer filtroAnio) {
        boolean cumpleCategoria = filtroCategoria == null
                || filtroCategoria.isBlank()
                || filtroCategoria.equals("todas")
                || filtroCategoria.equals(categoria);

        boolean cumpleAnio = filtroAnio == null
                || filtroAnio == anio;

        return cumpleCategoria && cumpleAnio;
    }
}
