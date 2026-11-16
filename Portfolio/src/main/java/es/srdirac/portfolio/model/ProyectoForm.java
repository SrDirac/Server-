package es.srdirac.portfolio.model;

//Clase del formulario de Proyectos
public class ProyectoForm {

    private String titulo;
    private String categoria;
    private String estado;
    private String descripcion;
    private Integer anio;


    //Función que válida si el formulario enviado está vacío para luego en el Controller validar el formato,
    //Si cualquier de los valores está relleno, devolverá false,  hace una comprobación de todos los atributos.
    public boolean estaVacio(){
        return (titulo==null ||titulo.isBlank()) &&
        (categoria==null ||categoria.isBlank()) &&
        (estado==null || estado.isBlank()) &&
        (descripcion==null || descripcion.isBlank()) &&
        anio==null;
    }
    //Getters y setters
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
