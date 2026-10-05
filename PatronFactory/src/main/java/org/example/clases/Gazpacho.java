package org.example.clases;

import org.example.interfaces.ElementoAndaluz;

// Clase que solo implementa ElementoAndaluz , describir();
public class Gazpacho implements ElementoAndaluz {
    @Override
    public void describir() {
        System.out.println("Representa el plato típico andaluz, el gazpacho");
    }
}
