package com.example.U4P3.service;

import com.example.U4P3.dto.ProyectoForm;
import com.example.U4P3.model.Proyecto;
import com.example.U4P3.repository.ProyectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
//Servicio: aquí es donde se define la lógica, funcione de intermediario entre el controlador y el repositorio, en este programa
// No tiene mucha lógica, ya que como hemos definido las consultas directamente en el repositorio simplemente tiene 3 métodos
// que ejecutan cada una de las consultas
//  del repositorio, sin embargo tiene un método que se llama guardar, ya que esta clase funciona de la siguiente manera
//Recibe un Form del DTO(ProyectoForm proyectoForm), con esto crea el proyecto, gracias al modelo que hemos creado anteriormente y se lo pasa al repositorio
//que hace el save del proyecto(linea 44) si no he movido nada
@Service
public class ProyectoService {
    private final ProyectoRepository repositorio;

    //Inyección de dependencias del tema 1, le pasamos el objeto repositorio al constructor del servicio
    public ProyectoService(ProyectoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Proyecto> listarTodos(){
        return repositorio.findAllOrdenadosNombre();
    }

    public List<Proyecto> listarPublicados(){
        return repositorio.findAllPublicadosOrdenadosPorFechaInicio();
    }

    public List<Proyecto> listarEnCurso(){
        return repositorio.findAllNoPublicadosOrdenadosPorPorcentaje();
    }

    public void guardar(ProyectoForm formulario) {
        Proyecto proyecto = new Proyecto();
        proyecto.setNombre(formulario.getNombre());
        proyecto.setDescripcion(formulario.getDescripcion());
        proyecto.setUrlRepositorio(formulario.getUrlRepositorio());
        proyecto.setHorasDedicadas(formulario.getHorasDedicadas());
        proyecto.setPorcentajeCompletado(formulario.getPorcentajeCompletado());
        proyecto.setFechaInicio(formulario.getFechaInicio());
        proyecto.setPublicado(formulario.isPublicado());

        repositorio.save(proyecto);
    }
}
