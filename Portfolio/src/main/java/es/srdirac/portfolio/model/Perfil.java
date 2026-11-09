package es.srdirac.portfolio.model;

public record Perfil (String nombre, String titularProfesional, String presentacion){

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
