package es.srdirac.portfolio.controller;

import es.srdirac.portfolio.model.PerfilForm;
import es.srdirac.portfolio.model.ProyectoForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EditarController {
    private static final List<String> CATEGORIAS = List.of("aplicacion", "sitio-web", "diseno");
    private static final List<String> ESTADOS = List.of("en-curso", "terminado");

    @Value("${portfolio.max-proyectos}")
    private int maxProyectos;

    @GetMapping("/portfolio/editar")
    public String newForm(Model model) {
        PerfilForm form = new PerfilForm();
        model.addAttribute("form", form);
        model.addAttribute("maxProyectos", maxProyectos);
        model.addAttribute("errores", new HashMap<String, String>());
        return "editar";
    }

    @PostMapping("/portfolio/editar")
    public String processForm(
            @ModelAttribute("form") PerfilForm form,
            Model model,
            @RequestParam(required = false) String accion,
            @RequestParam(required = false) Integer quitar
    ) {

        // El límite se comprueba en cada envío (añadir, quitar o vista previa),
        // por si se han manipulado los campos del formulario
        if (form.getProyectos().size() > maxProyectos) {
            model.addAttribute("errores", Map.of("limite", "editar.error.limite"));
            model.addAttribute("maxProyectos", maxProyectos);
            return "editar";
        }

        if ("previsualizar".equals(accion)) {
            Map<String, String> errores = validar(form);

            if (!errores.isEmpty()) {
                // hay errores: volver al formulario
                model.addAttribute("errores", errores);
                model.addAttribute("maxProyectos", maxProyectos);

                return "editar";
            }

            // sin errores: quitar filas vacías y mostrar la vista previa
            form.getProyectos().removeIf(ProyectoForm::estaVacio);
            return "vista-previa";
        }

        // añadir o quitar filas
        modificarFilas(form, accion, quitar);
        model.addAttribute("errores", new HashMap<String, String>());
        model.addAttribute("maxProyectos", maxProyectos);
        return "editar";
    }

    private void modificarFilas(PerfilForm form, String accion, Integer quitar) {
        List<ProyectoForm> proyectos = form.getProyectos();
        if (accion != null &&
                accion.equalsIgnoreCase("anadir")
                && proyectos.size() < maxProyectos) {
            proyectos.add(new ProyectoForm());
        }

        if (quitar != null
                && quitar >= 0
                && quitar < proyectos.size()
                && proyectos.size() > 1) {
            proyectos.remove(quitar.intValue());
        }
    }

    private Map<String, String> validar(PerfilForm form) {
        // Se guarda la clave del mensaje, no el texto: la plantilla lo traduce con #{...}
        Map<String, String> errores = new HashMap<>();
        if (form.getNombre() == null || form.getNombre().isBlank()) {
            errores.put("nombre", "editar.error.nombre");
        }
        if (form.getTitular() == null || form.getTitular().isBlank()) {
            errores.put("titular", "editar.error.titular");
        }
        if (form.getPresentacion() == null || form.getPresentacion().isBlank()) {
            errores.put("presentacion", "editar.error.presentacion");
        }
        if (form.getCorreo() != null && !form.getCorreo().isBlank() && !form.getCorreo().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            errores.put("correo", "editar.error.correo");
        }
        if (form.getProyectos().size() > maxProyectos) {
            errores.put("limite", "editar.error.limite");
        }

        for (int i = 0; i < form.getProyectos().size(); i++) {
            ProyectoForm proyecto = form.getProyectos().get(i);
            if (proyecto.estaVacio()) {
                continue;
            }
            if (proyecto.getTitulo() == null || proyecto.getTitulo().isBlank()) {
                errores.put("proyectos[" + i + "].titulo", "editar.error.proyecto.titulo");
            }

            if (proyecto.getAnio() == null) {
                errores.put("proyectos[" + i + "].anio", "editar.error.proyecto.anio");
            }

            if (proyecto.getCategoria() == null || !CATEGORIAS.contains(proyecto.getCategoria())) {
                errores.put("proyectos[" + i + "].categoria", "editar.error.proyecto.categoria");
            }

            if (proyecto.getEstado() == null || !ESTADOS.contains(proyecto.getEstado())) {
                errores.put("proyectos[" + i + "].estado", "editar.error.proyecto.estado");
            }

            if (proyecto.getDescripcion() == null || proyecto.getDescripcion().isBlank()) {
                errores.put("proyectos[" + i + "].descripcion", "editar.error.proyecto.descripcion");
            }
        }
        return errores;
    }
}
