package es.srdirac.portfolio.model;

import java.util.ArrayList;
import java.util.List;

public record Proyecto(
        String titulo,int anio, String categoria, String descripcion,
        String estado, List<String> tecnologias, String enlace, String imagen
) {
    //Constantes finales, se validarán en el constructor que categorias y estados cumplen un formato valido,
    // asi como que sus valores no sean nulos
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

    //Función que se implementa luego en filtrarProyectos
    //Crea dos variables booleanas y su comportamiento funciona como un if
    //y devuelve true o false si cumple la categoria y el año
    //Si la categoria == categoria o si es todas o si está vacía, la variable vale true
    //Si el año es igual al pasado por parámetro o es nulo, vale true
    // Si las dos son verdaderas --> return true, asi nos aseguramos de que en el List<Proyectos> cuando se filtre
    // cumpla los dos filtros.
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
