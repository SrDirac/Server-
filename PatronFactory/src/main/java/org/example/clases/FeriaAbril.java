package org.example.clases;

import org.example.interfaces.ElementoAndaluz;

// Clase que solo implementa ElementoAndaluz , describir();
public class FeriaAbril implements ElementoAndaluz {
    @Override
    public void describir() {
        System.out.println("Representa la famosa feria de Sevilla.");
    }
}
