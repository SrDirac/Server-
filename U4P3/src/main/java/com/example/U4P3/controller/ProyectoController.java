package com.example.U4P3.controller;

import com.example.U4P3.dto.ProyectoForm;
import com.example.U4P3.model.Proyecto;
import com.example.U4P3.service.ProyectoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//Controlador: es la parte web de la aplicación, recibe las peticiones del navegador (GET y POST), le pide los datos al servicio
//y se los pasa a la vista (index.html) a través del Model para que Thymeleaf genere la página en el servidor
//No accede nunca al repositorio directamente, todo lo hace a través del servicio, así cada capa se encarga solo de lo suyo
//Todos los métodos devuelven "index", que es el nombre de la plantilla de templates, menos guardar cuando sale bien, que redirige
@Controller
public class ProyectoController {
    private final ProyectoService servicio;

    //Inyección de dependencias por constructor: no hago new ProyectoService(), Spring crea el servicio y me lo pasa al crear el controlador
    public ProyectoController(ProyectoService servicio) {
        this.servicio = servicio;
    }

    //Página principal: carga el listado completo y un ProyectoForm vacío, que es el que se enlaza con el formulario de la vista (th:object)
    @GetMapping("/")
    public String inicio(Model model){
        ProyectoForm proyectoForm= new ProyectoForm();
        model.addAttribute("proyectos",servicio.listarTodos());
        model.addAttribute("proyectoForm",proyectoForm);
        return "index";
    }

    //Se ejecuta al enviar el formulario (method="post" en la vista)
    //@ModelAttribute rellena el ProyectoForm con lo que ha escrito el usuario y @Valid comprueba las validaciones del DTO
    //Los errores que encuentra se guardan en BindingResult, que tiene que ir justo después del objeto que se valida
    @PostMapping("/proyectos")
    public String guardar(@Valid @ModelAttribute("proyectoForm") ProyectoForm proyectoForm,
                          BindingResult errores,
                          Model model) {

        //Si hay errores no se guarda nada, se vuelve a mostrar la página con el mismo proyectoForm, por eso se conservan los valores
        //escritos y salen los mensajes de error. Hay que volver a cargar el listado porque la vista lo necesita
        if (errores.hasErrors()) {
            model.addAttribute("proyectos", servicio.listarTodos());
            return "index";
        }

        //Si está bien, el servicio crea el Proyecto y lo guarda en MariaDB
        //Se hace redirect en vez de devolver "index" para que al recargar la página (F5) no se vuelva a enviar el formulario y se duplique el proyecto
        servicio.guardar(proyectoForm);
        return "redirect:/";

    }

    //Ampliación: proyectos publicados, del más reciente al más antiguo
    //Se reutiliza la misma vista, solo cambia la lista y el título que se muestra encima de la tabla
    @GetMapping("/publicados")
    public String publicados(Model model) {
        model.addAttribute("proyectos", servicio.listarPublicados());
        model.addAttribute("titulo", "Proyectos publicados");
        model.addAttribute("proyectoForm", new ProyectoForm());
        return "index";
    }

    //Ampliación: proyectos aún no publicados, ordenados por porcentaje completado
    //También hay que mandar un ProyectoForm vacío porque el formulario de alta sale en todas las páginas
    @GetMapping("/en-curso")
    public String enCurso(Model model) {
        model.addAttribute("proyectos", servicio.listarEnCurso());
        model.addAttribute("titulo", "Proyectos en curso");
        model.addAttribute("proyectoForm", new ProyectoForm());
        return "index";
    }
}
