package org.example.clases;

import org.example.interfaces.ElementoAndaluz;

// Clase que crea las los objetos a partir de un String tipo,
// Extiende la clase ELementoAndaluzFactory y hereda su método abstracto
public class AndaluciaFactory extends ElementoAndaluzFactory{

    /**
     * Usamos trim & equalsIgnoreCase para que el tipo siempre cumpla con el formato de
     * sin espacios y no interfiera las mayúsculas o minúsculas , podría hacerse también con un
     * toLowerCase() o toUpperCase();
     * @param tipo
     * @return ElementoAndaluz
     */
    @Override
    public ElementoAndaluz createElementoAndaluz(String tipo) {
        //Si es nulo --> throw new IllegalArgumentException
        if (tipo==null){
            throw new IllegalArgumentException("No puede ser nulo");
        }else if (tipo.trim().equalsIgnoreCase("flamenco")){
            return new Flamenco();
        }else if (tipo.trim().equalsIgnoreCase("feria")){
            return new FeriaAbril();
        }else if (tipo.trim().equalsIgnoreCase("gazpacho")){
            return new Gazpacho();
            //Si no es uno de los valores mencionados anteriormente --> throw new IllegalArgumentException
        }else{
            throw new IllegalArgumentException("Debe ser de uno de los siguientes tipos : Feria, Flamenco o Gazpacho");
        }
    }
}
