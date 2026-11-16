package es.srdirac.portfolio.model;

//También es record: Son datos que en el controlador no cambia
public record Estudio(String nombre,  String centro, Integer anioFinalizacion) {

    //Metodo propio en curso , para validar si ha terminado o no  , ya que por constructor se puede enviar un anioFinalizacion=null
    public boolean enCurso(){
        return anioFinalizacion==null;
    }
}
