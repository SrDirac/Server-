package es.srdirac.portfolio.model;

public record Perfil (String nombre, String titularProfesional, String presentacion){

    //Dos constantes , mínimo y máximo, para que el usuario escriba como presentación máximo 4 líneas
    //Se realiza la validación de presentación en el constructor --> si es nulo o si sobrepasa los limites
    //lanza error
    private static final int MIN_PRESENTACION=140;
    private static final int  MAX_PRESENTACION=300;


    public Perfil{
        if(presentacion==null || presentacion.isBlank()){
            throw new IllegalArgumentException("La presentación es obligatoria");
        }

        if(presentacion.length()<MIN_PRESENTACION || presentacion.length()>MAX_PRESENTACION){
            throw new IllegalArgumentException("La presentación debe tener entre "+MIN_PRESENTACION+" y "+MAX_PRESENTACION+" caracteres");
        }
    }
}
