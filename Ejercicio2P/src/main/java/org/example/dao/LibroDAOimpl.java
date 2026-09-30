package org.example.dao;

import org.example.model.Conexion;
import org.example.model.Libro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibroDAOimpl implements LibroDAO {

    private final Conexion conexion;


    String BASE_SELECT= """
            SELECT id , titulo, autor, anioPublicacion
            FROM Libros 
            """;

    public LibroDAOimpl(Conexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<Libro> obtnerTodos() throws SQLException {
        List <Libro> libros = new ArrayList<>();

        String sql= BASE_SELECT +"order by id";
        Connection connection= conexion.getConexion();

        try{
            PreparedStatement statement= connection.prepareStatement(sql);
            ResultSet resultSet= statement.executeQuery();

            while(resultSet.next()){
                libros.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());;
        }
        System.out.println("Todos los libros \n -----------------------");
        return libros;
    }

    @Override
    public Optional<Libro> buscarPorID(int id) throws SQLException {
        String sql= BASE_SELECT + " where id = ?";
        Connection connection= conexion.getConexion();

        try{
            PreparedStatement statement= connection.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultSet= statement.executeQuery();
            if(resultSet.next()){
                return Optional.of(mapRow(resultSet));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void agregarLibro(Libro libro) throws SQLException {
        String sql="INSERT INTO Libros (id , titulo, autor, anioPublicacion) VALUES (? , ?, ?, ?)" ;


        try{
            Connection connection= conexion.getConexion();
            PreparedStatement statement= connection.prepareStatement(sql);
            statement.setInt(1,libro.getId());
            statement.setString(2, libro.getTitulo());
            statement.setString(3, libro.getAutor());
            statement.setInt(4,libro.getAnioPublicacion());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println((e.getMessage()));
        }


    }

    @Override
    public void actualizar(Libro libro) {
        String sql = "UPDATE Libros SET titulo = ?, autor = ?, anioPublicacion = ? WHERE id = ?";

        try {
            Connection connection = conexion.getConexion();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, libro.getTitulo());
            statement.setString(2, libro.getAutor());
            statement.setInt(3, libro.getAnioPublicacion());
            statement.setInt(4, libro.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void eliminar(Libro libro) {
        String sql = "DELETE FROM Libros WHERE id = ?";

        try {
            Connection connection = conexion.getConexion();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, libro.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }
    private Libro mapRow(ResultSet resultSet) throws SQLException {
        return new Libro(
                resultSet.getInt("id"),
                resultSet.getNString("titulo"),
                resultSet.getNString("autor"),
                resultSet.getInt("anioPublicacion")
        );
    }
}
