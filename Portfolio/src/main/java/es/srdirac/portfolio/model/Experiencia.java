package es.srdirac.portfolio.model;

public record Experiencia(String puesto, String organizacion, Integer anioInicio, Integer anioFin) {

    //Validación de experiencia , ya que no tiene sentido de que el Año Inicio y Fin sean nulos si tienes ya experiencia
    //además se hace comprobación de que el añoInicio sea menor que el Año Fin,  si el año fin es menor
    //lanza Exception IllegalArgumentException
    public Experiencia{
        if(anioFin!=null &&anioInicio!=null && anioFin < anioInicio){
            throw new IllegalArgumentException("El año de fin no puede ser anterior al de inicio");
        }
    }

    //El anioFin puede ser nulo , por ende , esta función nos permite saber si todavía sigue en el puesto laboral.
    public boolean enCurso(){
        return anioFin ==null;
    }
}
