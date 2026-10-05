package org.example;

import org.example.clases.AndaluciaFactory;
public class Main {
    static void main() {
        AndaluciaFactory andaluciaFactory= new AndaluciaFactory();

        //Creamos los elementos
        andaluciaFactory.createElementoAndaluz("flamenco").describir();
        andaluciaFactory.createElementoAndaluz("feria").describir();
        andaluciaFactory.createElementoAndaluz("gazpacho").describir();


        //Elemento no valido
        try {
            andaluciaFactory.createElementoAndaluz("aceitunas").describir();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
