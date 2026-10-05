package org.example.clases;

import org.example.interfaces.ElementoAndaluz;

// Clase que solo implementa ElementoAndaluz , describir();
public class Flamenco  implements ElementoAndaluz {
    @Override
    public void describir() {
        System.out.println("Representa el baile y cante flamenco");
    }
}
