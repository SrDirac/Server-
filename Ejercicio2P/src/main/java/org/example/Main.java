package org.example;


import org.example.dao.LibroDAO;
import org.example.dao.LibroDAOimpl;
import org.example.model.Conexion;
import org.example.model.Libro;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        try {
            Conexion conexion =  Conexion.getInstancia();
            LibroDAO libroDAO = new LibroDAOimpl(conexion);

            // 1. Agregar libros, este valor debe ir cambiandose ya que el ID no se puede repetir
            libroDAO.agregarLibro(new Libro(31, "Cien años de soledad", "Gabriel García Márquez", 1967));
            libroDAO.agregarLibro(new Libro(32, "Don Quijote de la Mancha", "Miguel de Cervantes", 1605));


            // 2. Listar todos
            List<Libro> libros = libroDAO.obtnerTodos();
            libros.forEach(System.out::println);

            // 3. Buscar por id
            System.out.println("\nBuscando el libro con id 1:");
            Optional<Libro> encontrado = libroDAO.buscarPorID(1);
            encontrado.ifPresentOrElse(
                    System.out::println,
                    () -> System.out.println("No existe ese libro")
            );

            // 4. Actualizar
            Libro libroActualizado = new Libro(2, "El ingenioso hidalgo Don Quijote de la Mancha", "Miguel de Cervantes", 1605);
            libroDAO.actualizar(libroActualizado);
            System.out.println("\nTras actualizar:");
            libroDAO.obtnerTodos().forEach(System.out::println);

            // 5. Eliminar
            libroDAO.eliminar(new Libro(2, "", "", 0));
            System.out.println("\nTras eliminar el id 2:");
            libroDAO.obtnerTodos().forEach(System.out::println);

        } catch (SQLException e) {
            System.err.println("Error de base de datos: " + e.getMessage());
        }
    }
}
