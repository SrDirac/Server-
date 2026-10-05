package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        Configurador instancia= Configurador.obtenerInstancia();
        instancia.setConfiguracion("Valores por defecto establecidos");
        System.out.println(instancia.getConfiguracion());

    }
}
