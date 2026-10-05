package org.example;

import org.example.maquinaria.Locomotora;
import org.example.maquinaria.Tren;
import org.example.maquinaria.Vagon;
import org.example.personal.JefeEstacion;
import org.example.personal.Maquinista;
import org.example.personal.Mecanico;


public class Main {
    static void main() {

        // Mecanicos
        Mecanico mecanico1 = new Mecanico("Luis Pérez Gómez", "612345678", "frenos");
        Mecanico mecanico2 = new Mecanico("Carmen Ruiz Díaz", "655987321", "hidraulicos");

        //Maquinista
        Maquinista maquinista = new Maquinista("Ana López Martín", "12345678A", 2450.0, "Senior");

        //JefeEstacion
        JefeEstacion jefeEstacion = new JefeEstacion("Javier Moreno Sanz", "87654321B");


        //Locomotora
        Locomotora locomotora = new Locomotora("252-001-3", 5600, 1992, mecanico1);

        Tren tren = new Tren(locomotora,maquinista);

        /* Como no es pública , la clase vagon solo puede ser vista por las clases que comparten paquete , por ende
        no se puede hacer :
        Vagon v1 = new Vagon(30000, 25000, "Carbón");
        Vagon v2 = new Vagon(25000, 18000, "Cereal");
        Vagon v3 = new Vagon(40000, 0, "Vacío");
        Vagon v4 = new Vagon(35000, 35000, "Contenedores");
        Vagon v5 = new Vagon(20000, 12500, "Madera");

         */

        //Si queremos añadir vagones sera desde trenes

        System.out.println(tren.agregarVagon(30000, 25000, "Carbón"));
        System.out.println(tren.agregarVagon(25000, 18000, "Cereal"));
        System.out.println(tren.agregarVagon(40000, 0, "Vacío"));
        System.out.println(tren.agregarVagon(35000, 35000, "Contenedores"));
        System.out.println(tren.agregarVagon(20000, 12500, "Madera"));
        //Rechaza el sexto vagon
        System.out.println(tren.agregarVagon(30000, 10000, "Acero"));

        // Inyección por interfaz: cambiamos el mecánico de la locomotora
        System.out.println("Mecánico antes: " + locomotora.getMecanico().getNombreCompleto());
        locomotora.asignarMecanico(mecanico2);
        System.out.println("Mecánico después: " + locomotora.getMecanico().getNombreCompleto());

        // Pruebas de validación
        try {
            new Mecanico("Pedro Sanz", "600000000", "cocina");
        } catch (IllegalArgumentException e) {
            System.out.println("Error esperado: " + e.getMessage());
        }

        try {
            tren.agregarVagon(10000, 15000, "Arena");
        } catch (IllegalArgumentException e) {
            System.out.println("Error esperado: " + e.getMessage());
        }
    }
}

