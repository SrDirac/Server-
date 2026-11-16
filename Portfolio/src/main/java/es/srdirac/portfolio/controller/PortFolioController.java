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

    //La descripción es una clave de messages para que se traduzca con el idioma
    //Todas las descripciones de los proyectos se guardan como claves del messages.properties
    //Para una vez retormamos la vista los traduce y les pone el valor real
    private static final List<Proyecto> PROYECTOS = List.of(
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
                    "teclea", 2026, "aplicacion",
                    "proyecto.teclea.descripcion",
                    "terminado", List.of("TypeScript", "SolidJS", "Vite", "IndexedDB", "PWA"),
                    "https://teclea.pages.dev", "/img/teclea.png"
            ),
            new Proyecto(
                    "Patrones de diseño en Spring", 2025, "aplicacion",
                    "proyecto.patrones.descripcion",
                    "en-curso", List.of("Java", "Spring Boot", "Git"),
                    "https://github.com/SrDirac/Server-", "/img/patrones.png"
            )
    );
    //Metodo principal de portFolio

    /**
     * Explicación de los requestParam: permite leer un parámetro de la petición y lo mete en una variable del metodo
     * ¿Por qué es false?: porque si el parámetro no llega, el parámetro vale null, sino Spring devolvería un error 400
     * sin ejecutar el metodo
     *
     * @param categoria:Busca en la petición un parámetro llamado categoria y lo mete en la variable
     * @param anio:Busca en la petición un parámetro llamado anio y lo mete en la variable
     * @param model:Contenedor, es donde metemos el resultado del filtrado de proyectos para posteriormente enviarlo a la vista
     * @return portolio: es la vista que se devolverá con la función  , el html.
     */
    @GetMapping("/portfolio")
    public String proyectos(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String anio,
            Model model
    ) {
        //El año llega como texto: se convierte aquí. Si no es válido, no se filtra por año.
        //Variable de control , debido a que anio llega como String
        Integer anioNumero = null;
        boolean anioInvalido = false;

        //El año llega como texto desde el formulario.
        //Si no es null ni está vacío, se convierte a número con Integer.parseInt.
        //Si no es un número válido, se captura la NumberFormatException:
        //se marca el error para mostrar "Introduce un año válido" y no se filtra por año.
        //Es simplemente un bloque de codigo para comprobar que el formato del anio en la peticion cumple el formato establecido
        if (anio != null && !anio.isBlank()) {
            try {
                anioNumero = Integer.parseInt(anio.trim());
            } catch (NumberFormatException e) {
                anioInvalido = true;
            }
        }

        //Una vez se ha comprobado el formato, ejecutamos el metodo filtrarProyectos con nuestros proyectos
        List<Proyecto> filtrados = filtrarProyectos(PROYECTOS, categoria, anioNumero);
        //El resultado lo añadimos al model y lo retormamos a la vista
        model.addAttribute("proyectos", filtrados);
        model.addAttribute("categoria", categoria);
        model.addAttribute("anio", anio);
        model.addAttribute("anioInvalido", anioInvalido);

        return "portfolio";
    }

    //Página propia del proyecto destacado (teclea). Todo su texto está en los messages,
    //así que no necesita datos del modelo
    @GetMapping("/portfolio/teclea")
    public String teclea() {
        return "teclea";
    }

    //Metodo privado : recibe una lista de proyectos e implementamos el metodo cumpleFiltro (creado en el record)
    //Si cumple el filtro , se añade al arrayList de resultados .
    //Este metodo es el que se implementa en proyectos para su filtrado
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
