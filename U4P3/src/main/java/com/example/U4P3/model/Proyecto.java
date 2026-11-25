package com.example.U4P3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

//@Entity marca la clase como algo que se guarda en la base de datos. Hibernate la detecta al arrancar.
@Entity
//@Table(name = "proyecto") le da el nombre a la tabla.
@Table(name = "proyecto")
// Las tres son anotaciones de Lombok, del paquete lombok. Lombok escribe ese código al compilar.
@Getter
@Setter
@NoArgsConstructor
public class Proyecto {
    /** El id lo genera la base de datos al insertar, no lo pongo yo. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Length máximo 60 y no puede ser nulo
    @Column(length = 60, nullable = false)
    private String nombre;

    //Length máximo 300, si no aparece nada, se permiten valores nulos (menos en él id obviamente), es como poner nullable=true
    @Column(length = 300)
    private String descripcion;

    //Length máximo 150
    @Column(length = 150)
    private String urlRepositorio;

    //Se han puesto restricciones en el DTO, pero el valor suele puede estar entre 0 y 2000, no puede ser nulo
    @Column(nullable = false)
    private Integer horasDedicadas;

    //Se han puesto restricciones en el DTO , valor entre 0 y 100, con dos decimales , no puede ser nulo
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal porcentajeCompletado;

    //No puede ser nulo, restricciones en el DTO,
    @Column(nullable = false)
    private LocalDate fechaInicio;

    //No puede ser nulo
    @Column(nullable = false)
    private Boolean publicado;


//   Anotaciones, como vemos aquí solo hemos definido las restricciones y parametrizado los valores a nivel de las tablas de las
//   bases de datos, es decir, como queremos definir la entidad y la información de cada uno de los proyectos. Las restricciones de @Column
//   (nullable, length) las aplica la base de datos; lo que no tiene esta clase son las validaciones del formulario, esas están en el DTO
//   y se comprueban antes de guardar, así al usuario le salen los mensajes de error en vez de un fallo de la base de datos
}
