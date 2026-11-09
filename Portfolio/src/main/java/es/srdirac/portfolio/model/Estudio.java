package es.srdirac.portfolio.model;

public record Estudio(String nombre,  String centro, Integer anioFinalizacion) {

    public boolean enCurso(){
        return anioFinalizacion==null;
    }
}
