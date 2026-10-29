package es.srdirac.p3p4springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.time.temporal.ChronoUnit;

import java.time.LocalDate;

import es.srdirac.p3p4springboot.form.SolicitudPrestamoForm;
import jakarta.validation.Valid;


@Controller
public class PrestamoController{

    // Catalogo de productos en memoria , es un array bidimensional , donde cada fila
    // almacena un producto y los valores del mismo(codigo, nombre y descripcion)
    // final porque es el mismo para todas las peticiones y porque no se va a modficar
    // es decir , no va a tener un metodo setter
    // Constantes o finales en mayusculas
    // DISTRIBUCION DE FILAS Y COLUMNAS EN UN ARRAY
    //              C1          C2
    //      F1   PORTATIL      Ordenador Portatil...
    //      F2      ...         ...
    private static final String MATERIALES[][]={
            {"PORTATIL", "Ordenador portatil", "Incluye un cargador y una funda"},
            {"MICRO", "Kit de microcontrolador", "Incluye una placa y un cable USB"},
            {"CAMARA", "Camara de documentacion", "Incluye un soporte de mesa"}
    };


    @GetMapping("/")
    public String catalogo(Model model){
        // materiales solo existe durante la peticion en la vista que lo recibe
        model.addAttribute("materiales",MATERIALES);
        return "catalogo";
    }
    /*
    @PathVariable String codigo lee el trozo de la URL que va en {codigo}. En /materiales/MICRO, codigo vale "MICRO".
    @RequestParam(defaultValue = "general") String turno lee lo que va después de ?turno=. Si no se envía, vale "general".
    buscarMaterial recorre las filas del array. Si la columna 0 de una fila coincide con el código, devuelve esa fila entera.
    Si termina el bucle sin encontrarlo, devuelve null.
    El if (material == null) es la decisión que distingue entre material existente e inexistente .
    En lugar de mostrar una ficha vacía, lanza una excepción que Spring convierte en una respuesta con estado HTTP 404.
    */
    @GetMapping("/materiales/{codigo}")

    public String ficha(@PathVariable String codigo,
                        @RequestParam(defaultValue = "general") String turno,
                        Model model) {
        String[] material = buscarMaterial(codigo);

        // Si el código no existe se lanza un 404 real; Spring Boot mostrará error/404.html
        // Lanzar errores o validaciones de estado de http linea [64] throw new + ResponseStatusExcepcion(HttpStatus.status,message)
        if (material == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Material no encontrado");
        }
        //Cualquier cosa de model se escribe de la siguiente manera
        // el primer valor entre comillas es el nombre con el que la plantilla podra encontrar el dato
        // el segundo valor es el contenido de la variable , la fila del array que devolvio buscarMaterial
        model.addAttribute("material", material);
        model.addAttribute("turno", turno);
        return "ficha";
    }

    @GetMapping("/solicitud/nueva")
    public String nuevaSolicitud(Model model) {
        // DTO vacío para que th:object tenga un objeto al que enlazar los campos
        model.addAttribute("solicitud", new SolicitudPrestamoForm());
        model.addAttribute("materiales", MATERIALES);
        return "solicitud-form";
    }
    // Días del préstamo contando el de recogida y el de devolución (por eso + 1).
    // Ejemplo: del 10 al 12 son 3 días; si ambas fechas coinciden, 1 día.
    private long calcularDiasPrestamo(LocalDate recogida, LocalDate devolucion) {
        return ChronoUnit.DAYS.between(recogida, devolucion) + 1;
    }

    @PostMapping("/solicitudes")
    public String enviarSolicitud(@Valid @ModelAttribute("solicitud") SolicitudPrestamoForm solicitud,
                                  BindingResult resultado,
                                  Model model) {
        // BindingResult debe ir justo después del DTO validado: recoge los errores
        // de conversión y de las anotaciones sin que Spring lance una excepción

        // El material debe existir en el catálogo (si llega vacío ya lo marca @NotBlank)
        String codigo = solicitud.getMaterial();
        if (codigo != null && !codigo.isBlank() && buscarMaterial(codigo) == null) {
            resultado.rejectValue("material", "material.invalido",
                    "Selecciona un material del catálogo.");
        }

        // Regla entre dos campos: solo se compara si ambas fechas se han podido convertir
        LocalDate recogida = solicitud.getFechaRecogida();
        LocalDate devolucion = solicitud.getFechaDevolucion();
        if (recogida != null && devolucion != null && devolucion.isBefore(recogida)) {
            resultado.rejectValue("fechaDevolucion", "fecha.anterior",
                    "La devolución no puede ser anterior a la recogida.");
        }

        if (resultado.hasErrors()) {
            // Al volver al formulario hay que reenviar las opciones del selector
            model.addAttribute("materiales", MATERIALES);
            return "solicitud-form";
        }

        // Solicitud válida: solo se muestra el resumen, no se guarda en ningún sitio
        long dias = calcularDiasPrestamo(recogida, devolucion);
        long diasUnidad = dias * solicitud.getUnidades();

        // El DTO "solicitud" ya está en el modelo gracias a @ModelAttribute;
        // se añaden el nombre del material y los cálculos
        model.addAttribute("nombreMaterial", buscarMaterial(codigo)[1]);
        model.addAttribute("dias", dias);
        model.addAttribute("diasUnidad", diasUnidad);
        return "resultado";
    }

    // Recorre el array y devuelve la fila cuyo código (columna 0) coincide; null si no existe
    // Se usa equals() y no ==: con String, == compara si son el mismo objeto en memoria,
    // mientras que equals() compara el texto que contienen
    private String[] buscarMaterial(String codigo) {
        for (String[] fila : MATERIALES) {
            if (fila[0].equals(codigo)) {
                return fila;
            }
        }
        return null;
    }

    /*TEMPORAL: solo para probar error/5xx.html. Borrar antes de entregar.
    @GetMapping("/forzar-error")
    public String forzarError() {
        throw new RuntimeException("prueba");
    }

     */

}