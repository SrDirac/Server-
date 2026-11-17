package com.example.u4p2.apartado1.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Espacio de trabajo del aula maker.
 * No tiene relacion con Recurso, son dos inventarios separados.
 *
 * Uso Lombok para que me genere los get, los set y el constructor vacio
 * (public Espacio(){}), que JPA lo necesita para crear el objeto.
 * No uso @Data porque tambien genera equals y hashCode con todos los campos,
 * incluido el id, y el id cambia cuando se guarda en la base de datos.
 */
@Entity
@Table(name = "espacio")
@Getter
@Setter
@NoArgsConstructor
public class Espacio {

    /** El id lo genera la base de datos al insertar, no lo pongo yo. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * En Java se llama codigo porque ya estamos dentro de Espacio,
     * pero en el SQL es codigo_espacio porque el diseño relacional impone ese nombre.
     */
    @Column(name = "codigo_espacio", nullable = false, length = 8, unique = true)
    private String codigo;

    /** Lo mismo que codigo: en el SQL el diseño impone nombre_espacio. */
    @Column(name = "nombre_espacio", nullable = false, length = 60)
    private String nombre;

    @Column(name = "aforo", nullable = false)
    private Integer aforo;

    @Column(name = "planta", nullable = false)
    private Integer planta;

    @Column(name = "accesible", nullable = false)
    private Boolean accesible;
}
