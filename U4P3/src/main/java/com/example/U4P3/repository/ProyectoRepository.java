package com.example.U4P3.repository;

import com.example.U4P3.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
//Interfaz del repositorio Proyecto, hereda JpaREpository que tiene findAll(), save(), findById() y count() sin que tenga que escribir la query a mano
//La función de la interfaz es acceder a los datos y guardarlos, no se encarga de nada más, el cómo se gestiona esos datos no es asunto suyo, de eso se encarga el servicio
//En esta clase también se pueden escribir las Consultas que se quieren realizar a la base datos con @Query
//Me imagino que cada tabla tendrá un repositorio, pero por ejemplo almejar te interesa tener en un solo servicio en una base de datos nada más, es decir que un mismo servicio gestione dos Repositorios
//Por ejemplo en el ejercicio de Aula y materiales??
public interface ProyectoRepository extends JpaRepository<Proyecto,Long> {

//    Las consultas que queremos hacer a la tabla de proyectos en la base da datos
    @Query("SELECT proyecto FROM Proyecto proyecto ORDER BY proyecto.nombre ASC ")
    List<Proyecto> findAllOrdenadosNombre();

    @Query("SELECT proyecto FROM Proyecto proyecto WHERE proyecto.publicado=TRUE  ORDER BY proyecto.fechaInicio DESC ")
    List<Proyecto> findAllPublicadosOrdenadosPorFechaInicio();

    @Query("SELECT proyecto FROM Proyecto proyecto WHERE proyecto.publicado=FALSE ORDER BY proyecto.porcentajeCompletado ASC")
    List<Proyecto> findAllNoPublicadosOrdenadosPorPorcentaje();
}
