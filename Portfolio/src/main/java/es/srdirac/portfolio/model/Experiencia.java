package es.srdirac.portfolio.model;

public record Experiencia(String puesto, String organizacion, Integer anioInicio, Integer anioFin) {

    public Experiencia{
        if(anioFin!=null &&anioInicio!=null && anioFin < anioInicio){
            throw new IllegalArgumentException("El año de fin no puede ser anterior al de inicio");
        }
    }

    public boolean enCurso(){
        return anioFin ==null;
    }
}
