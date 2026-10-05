package org.example.dao;

import org.example.model.Libro;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface LibroDAO {
    //Metodos de la interfaz , se implementarán en LibroDAOimp
    List<Libro> obtnerTodos() throws SQLException;
    Optional<Libro> buscarPorID(int id) throws SQLException;
    void agregarLibro(Libro libro) throws SQLException;
    void actualizar(Libro libro);
    void eliminar(Libro libro);
}
