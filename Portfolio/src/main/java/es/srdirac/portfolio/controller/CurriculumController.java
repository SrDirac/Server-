package es.srdirac.portfolio.controller;

import es.srdirac.portfolio.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

@Controller
public class CurriculumController {


    @Value("${cv.nombre}")
    private String nombre;

    private final MessageSource mensajes;

    public CurriculumController(MessageSource mensajes) {
        this.mensajes = mensajes;
    }

    // La raíz no tiene página propia: se redirige al currículum para no mostrar un 404
    @GetMapping("/")
    public String inicio() {
        return "redirect:/curriculum";
    }

    @GetMapping("/curriculum")
    public  String curriculum(Model model, Locale locale){
        // El perfil se traduce aquí (y no en la plantilla) porque Perfil valida la longitud
        // de la presentación, y eso solo tiene sentido con el texto real del idioma elegido
        Perfil perfil = new Perfil(nombre,
                mensajes.getMessage("cv.perfil.titular", null, locale),
                mensajes.getMessage("cv.perfil.presentacion", null, locale));

        // El resto de textos que cambian con el idioma se guardan como claves de messages
        // y la plantilla los traduce con #{...}. Nombres propios (centros, tecnologías) van tal cual.
        Estudio[] estudios = {
                new Estudio("cv.estudio.integracion", "FP Vedruna", 2022),
                new Estudio("cv.estudio.daw", "IES Velazquez", null)
        };

        Experiencia[] experiencias = {
                new Experiencia("cv.experiencia.practicas", "IES Velazquez", 2025, 2025),
                new Experiencia("cv.experiencia.animador", "Asociación protegida", 2021, 2022),
                new Experiencia("cv.experiencia.freelance", "Freelance", 2025, null)
        };

        Habilidad[] habilidades = {
                new Habilidad("Java", "alto"),
                new Habilidad("HTML/CSS", "alto"),
                new Habilidad("Spring Boot", "medio"),
                new Habilidad("SQL", "medio"),
                new Habilidad("Git", "bajo")
        };

       Contacto contacto= new Contacto("agusarrate02@gmail.com");

        Idioma[] idiomas = {
                new Idioma("cv.idioma.espanol", "alto"),
                new Idioma("cv.idioma.ingles", "medio"),
                new Idioma("cv.idioma.frances", "bajo")
        };


        model.addAttribute("perfil",perfil);
        model.addAttribute("contacto", contacto);
        model.addAttribute("estudios",estudios);
        model.addAttribute("experiencias", experiencias);
        model.addAttribute("habilidades", habilidades);
        model.addAttribute("idiomas",idiomas);
        return "curriculum";
    }
}
