package com.example.U4P3.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Objeto de entrada del formulario de alta.
 *
 * Se usa en lugar de la entidad para que la vista no trabaje directamente con
 * el modelo persistente: aquí solo están los campos que rellena el usuario
 * (sin id, ya que este se gestiona en la base de datos) y las reglas de validación que se comprueban al enviar el formulario.
 */
@Getter
@Setter
@NoArgsConstructor
public class ProyectoForm {

    //No puede estar vacío, el tamaño máximo del mensaje es de 60 caracteres
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 60, message = "El nombre no puede superar los 60 caracteres")
    private String nombre;

    //Esta definido en la base de datos como posible nulo, asi que solo aplicamos un tamaño máximo de 300 caracteres
    @Size(max = 300, message = "La descripción no puede superar los 300 caracteres")
    private String descripcion;

    // @URL acepta el campo vacío, es un valor opcional
    @Size(max = 150, message = "La URL no puede superar los 150 caracteres")
    @URL(message = "Introduce una URL válida (por ejemplo, https://github.com/usuario/proyecto)")
    private String urlRepositorio;

    //No puede ser nulo, solo se puede poner valores en rango de 0 a 2000 incluidos
    @NotNull(message = "Las horas dedicadas son obligatorias")
    @Min(value = 0, message = "Las horas no pueden ser negativas")
    @Max(value = 2000, message = "Las horas no pueden superar 2000")
    private Integer horasDedicadas;

    //No nulo  rango entre 0.00 y 100.00, la etiqueta digits de integer y fraction funcionan de una manera parecida
    //a precision y scale, en integer pones la parte entera y en fraction el número de decimales.
    @NotNull(message = "El porcentaje completado es obligatorio")
    @DecimalMin(value = "0.00", message = "El porcentaje no puede ser menor que 0")
    @DecimalMax(value = "100.00", message = "El porcentaje no puede ser mayor que 100")
    @Digits(integer = 3, fraction = 2, message = "El porcentaje admite como máximo 2 decimales")
    private BigDecimal porcentajeCompletado;

    // El <input type="date"> envía la fecha como texto (aaaa-mm-dd); esta anotación
    // indica a Spring cómo convertirla a LocalDate
    @NotNull(message = "La fecha de inicio es obligatoria")
    @PastOrPresent(message = "La fecha de inicio no puede ser futura")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaInicio;

    // boolean primitivo: si el checkbox no se marca, vale false en lugar de null
    private boolean publicado;
}