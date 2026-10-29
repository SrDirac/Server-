package es.srdirac.p7springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ActividadController {
    // Lista de actividades que presentará la pagina
    // Atributo final y estático , pertenece a la clase y no podra ser
    // modificado
    // El array esta dividido en código, título, descripción y fecha.
    // El título y la descripción no son el texto, son la clave de los
    // messages.properties, asi Thymeleaf los saca en el idioma que toque
    // y no hay que traducir nada en Java
    private static final String[][] ACTIVIDADES = {
            //Excursiones
            {"EXC-01", "exc01.titulo", "exc01.descripcion", "14/11/2026"},
            {"EXC-02", "exc02.titulo", "exc02.descripcion", "28/11/2026"},
            {"EXC-03", "exc03.titulo", "exc03.descripcion", "16/01/2027"},
            //Actividades
            {"ACT-01", "act01.titulo", "act01.descripcion", "07/11/2026"},
            {"ACT-02", "act02.titulo", "act02.descripcion", "19/11/2026"},
            {"ACT-03", "act03.titulo", "act03.descripcion", "12/12/2026"},
            {"ACT-04", "act04.titulo", "act04.descripcion", "22/01/2027"},
            {"TOR-01", "tor01.titulo", "tor01.descripcion", "21/11/2026"},
            {"TOR-02", "tor02.titulo", "tor02.descripcion", "19/12/2026"},
            {"TOR-03", "tor03.titulo", "tor03.descripcion", "30/01/2027"}
    };

    @GetMapping("/")
    public String catalogo(Model model){
        // actividades solo existe durante la peticion en la vista que lo recibe
        model.addAttribute("actividades", ACTIVIDADES);
        return "velazquez";
    }

    @GetMapping("/visita")
    public String visita() {
        return "visita";   // carga templates/visita.html
    }
}
