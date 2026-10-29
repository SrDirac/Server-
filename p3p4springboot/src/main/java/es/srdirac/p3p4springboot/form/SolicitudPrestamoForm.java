package es.srdirac.p3p4springboot.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Objeto de entrada del formulario de préstamo (no es una entidad ni se guarda).
 * Spring MVC rellena sus propiedades con los valores enviados por POST.
 */
public class SolicitudPrestamoForm {

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(max = 60, message = "El nombre no puede superar los 60 caracteres.")
    private String nombre;

    @NotBlank(message = "El correo es obligatorio.")
    @Email(message = "Introduce un correo electrónico válido.")
    @Size(max = 120, message = "El correo no puede superar los 120 caracteres.")
    private String email;

    // Que el código exista en el catálogo se comprueba en el controlador con buscarMaterial
    @NotBlank(message = "Selecciona un material.")
    private String material;

    // Integer y no int: si el campo llega vacío queda null y salta @NotNull
    @NotNull(message = "Indica el número de unidades.")
    @Min(value = 1, message = "Debes solicitar al menos 1 unidad.")
    @Max(value = 3, message = "No puedes solicitar más de 3 unidades.")
    private Integer unidades;

    // ISO.DATE = formato aaaa-mm-dd, el que envía <input type="date">
    @NotNull(message = "Indica la fecha de recogida.")
    @FutureOrPresent(message = "La fecha de recogida debe ser hoy o posterior.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaRecogida;

    // La regla "no anterior a la recogida" compara dos campos: se valida en el controlador
    @NotNull(message = "Indica la fecha de devolución.")
    @FutureOrPresent(message = "La fecha de devolución debe ser hoy o posterior.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaDevolucion;

    @NotBlank(message = "El motivo es obligatorio.")
    @Size(min = 30, max = 400, message = "El motivo debe tener entre 30 y 400 caracteres.")
    private String motivo;

    // boolean primitivo: una casilla sin marcar llega como false y @AssertTrue la rechaza
    @AssertTrue(message = "Debes aceptar las normas de préstamo.")
    private boolean aceptaNormas;

    // Getters y setters: Spring los usa para leer y escribir cada propiedad

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    public Integer getUnidades() { return unidades; }
    public void setUnidades(Integer unidades) { this.unidades = unidades; }

    public LocalDate getFechaRecogida() { return fechaRecogida; }
    public void setFechaRecogida(LocalDate fechaRecogida) { this.fechaRecogida = fechaRecogida; }

    public LocalDate getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(LocalDate fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public boolean isAceptaNormas() { return aceptaNormas; }
    public void setAceptaNormas(boolean aceptaNormas) { this.aceptaNormas = aceptaNormas; }
}