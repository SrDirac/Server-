package es.srdirac.portfolio.controller;

import es.srdirac.portfolio.model.Proyecto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PortFolioController {

    // La descripción es una clave de messages para que se traduzca con el idioma
    private static final List<Proyecto> PROYECTOS =List.of(
            new Proyecto(
                    "Portafolio personal", 2025, "aplicacion",
                    "proyecto.portafolio.descripcion",
                    "en-curso", List.of("Java", "Spring Boot", "Thymeleaf", "HTML", "CSS"),
                    null, "/img/portafolio.png"
            ),
            new Proyecto(
                    "Migas Amigas", 2025, "diseno",
                    "proyecto.migas.descripcion",
                    "terminado", List.of("Figma", "Diseño gráfico"),
                    null, "/img/migas-amigas.png"
            ),
            new Proyecto(
                    "Clínica de fisioterapia", 2025, "sitio-web",
                    "proyecto.fisio.descripcion",
                    "terminado", List.of("HTML", "CSS", "Bootstrap"),
                    null, "/img/fisio.png"
            ),
            new Proyecto(
                    "Tienda BBDD", 2025, "aplicacion",
                    "proyecto.tienda.descripcion",
                    "terminado", List.of("Java", "SQL", "JDBC"),
                    null, null
            ),
            new Proyecto(
                    "Dino Game", 2026, "aplicacion",
                    "proyecto.dino.descripcion",
                    "terminado", List.of("Godot", "GDScript"),
                    null, "/img/dino-game.png"
            ),
            new Proyecto(
                    "Patrones de diseño en Spring", 2025, "aplicacion",
                    "proyecto.patrones.descripcion",
                    "en-curso", List.of("Java", "Spring Boot", "Git"),
                    "https://github.com/SrDirac/Server-", "/img/patrones.png"
            )
    );
    @GetMapping("/portfolio")
    public String proyectos(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String anio,
            Model model
    ) {
        // El año llega como texto: se convierte aquí. Si no es válido, no se filtra por año.
        Integer anioNumero = null;
        boolean anioInvalido = false;

        if (anio != null && !anio.isBlank()) {
            try {
                anioNumero = Integer.parseInt(anio.trim());
            } catch (NumberFormatException e) {
                anioInvalido = true;
            }
        }

        List<Proyecto> filtrados = filtrarProyectos(PROYECTOS, categoria, anioNumero);

        model.addAttribute("proyectos", filtrados);
        model.addAttribute("categoria", categoria);
        model.addAttribute("anio", anio);
        model.addAttribute("anioInvalido", anioInvalido);

        return "portfolio";
    }
    private List<Proyecto> filtrarProyectos(List<Proyecto> proyectos, String categoria, Integer anio) {
        List<Proyecto> resultado = new ArrayList<>();
        for (Proyecto proyecto : proyectos) {
            if (proyecto.cumpleFiltro(categoria, anio)) {
                resultado.add(proyecto);
            }
        }
        return resultado;
    }
}
