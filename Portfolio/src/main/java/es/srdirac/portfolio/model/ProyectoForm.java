package es.srdirac.portfolio.model;

public class ProyectoForm {

    private String titulo;
    private String categoria;
    private String estado;
    private String descripcion;
    private Integer anio;


    public boolean estaVacio(){
        return (titulo==null ||titulo.isBlank()) &&
        (categoria==null ||categoria.isBlank()) &&
        (estado==null || estado.isBlank()) &&
        (descripcion==null || descripcion.isBlank()) &&
        anio==null;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }
}
