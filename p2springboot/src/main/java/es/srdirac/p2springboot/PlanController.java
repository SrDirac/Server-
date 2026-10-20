package es.srdirac.p2springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PlanController {
    @GetMapping("/")
    public String mostrarPlan(@RequestParam(defaultValue = "estudiante")String nombre, Model model) {
        List<String> asignaturas = List.of("Programacion", "Base de datos", "Lenguaje de marcas");
        List<Integer> duracion = List.of(25, 40, 30);
        model.addAttribute("nombre",nombre);
        model.addAttribute("titulo", "Mi plan de estudio");
        model.addAttribute("asignaturas", asignaturas);
        model.addAttribute("duraciones", duracion);
        return "plan";
    }
}
