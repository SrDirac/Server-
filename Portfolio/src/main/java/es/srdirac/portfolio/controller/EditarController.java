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
    //Constantes con los valores permitidos de Categorias y Estados, son necesarios ya que luego en el metodo
    //validar comprobará que lo que llega del proyecto del formulario tiene un valor valido
    private static final List<String> CATEGORIAS = List.of("aplicacion", "sitio-web", "diseno");
    private static final List<String> ESTADOS = List.of("en-curso", "terminado");

    //Valor tomado del messages.properties portfolio.max-proyectos , lo almacena en la variable
    @Value("${portfolio.max-proyectos}")
    private int maxProyectos;

    //Muestra el formulario vacío. El constructor de PerfilForm ya añade la fila de proyecto inicial.
    //Se pasa un mapa de errores vacío para que la plantilla pueda consultarlo sin que sea null.
    @GetMapping("/portfolio/editar")
    public String newForm(Model model) {
        PerfilForm form = new PerfilForm();
        model.addAttribute("form", form);
        model.addAttribute("maxProyectos", maxProyectos);
        model.addAttribute("errores", new HashMap<String, String>());
        return "editar";
    }

    /**
     * Metodo que recibe todos los envios del formulario y decide que hacer según el boton pulsado
     * Coordina validar y modificar filas y elige que pagina mostrar
     * @param form
     * @param model
     * @param accion
     * @param quitar
     * @return
     */
    @PostMapping("/portfolio/editar")
    public String processForm(
            //Spring crea un perfilForm y los rellena con todos los campos del formulario
            @ModelAttribute("form") PerfilForm form,
            Model model,
            @RequestParam(required = false) String accion,
            @RequestParam(required = false) Integer quitar
    ) {
        //Siempre es el primer metodo que se hace
        //El límite se comprueba en cada envío (añadir, quitar o vista previa),
        //por si se han manipulado los campos del formulario
        if (form.getProyectos().size() > maxProyectos) {
            model.addAttribute("errores", Map.of("limite", "editar.error.limite"));
            model.addAttribute("maxProyectos", maxProyectos);
            return "editar";
        }

        //Si la acción es previsualizar  valida(form) válida todos los campos del formulario
        if ("previsualizar".equals(accion)) {
            Map<String, String> errores = validar(form);
            //Si hay errores: volver al formulario
            if (!errores.isEmpty()) {
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
    //Método que añade una fila según el botón pulsado
    private void modificarFilas(PerfilForm form, String accion, Integer quitar) {
        List<ProyectoForm> proyectos = form.getProyectos();
        // Añade una fila vacía solo si no se ha llegado al máximo. Se comprueba en el servidor
        // aunque el botón esté oculto, por si se han manipulado los campos del formulario.
        if (accion != null &&
                accion.equalsIgnoreCase("anadir")
                && proyectos.size() < maxProyectos) {
            proyectos.add(new ProyectoForm());
        }

        // Quita la fila indicada solo si el índice existe y queda al menos una fila.
        // intValue() hace que remove borre por posición y no busque un objeto igual al número.
        if (quitar != null
                && quitar >= 0
                && quitar < proyectos.size()
                && proyectos.size() > 1) {
            proyectos.remove(quitar.intValue());
        }
    }

    //Válida todos los campos del PerfilForm, los mensajes no se escriben directamente si no que
    //Se introducen como Value la clave para que posteriomente se traduzca en la vista
    private Map<String, String> validar(PerfilForm form) {
        // Se guarda la clave del mensaje, no el texto: la plantilla lo traduce con #{...}
        //Validacion del Usuario/Perfil
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
        //Validación del proyecto
        //La validación de proyectos se realiza con un fori debido
        //a que queremos saber en qué posición está el error , además con un forof se pisarían y se sobreescribirán los errores
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
