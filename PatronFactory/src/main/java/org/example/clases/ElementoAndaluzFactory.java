package org.example.clases;

import org.example.interfaces.ElementoAndaluz;

// Clase abstracta con un único metodo abstracto , createElementoAndaluz
// Este lo recibirá la clase que la extiede e implementara la función AndaluciaFactory
public abstract class ElementoAndaluzFactory {
    abstract ElementoAndaluz createElementoAndaluz(String tipo);
}
