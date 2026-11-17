package com.example.u4p2.apartado1.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Equipo del aula maker que se puede prestar.
 * No tiene relacion con Espacio, son dos inventarios separados.
 *
 * Uso Lombok para que me genere los get, los set y el constructor vacio
 * (public Recurso(){}), que JPA lo necesita para crear el objeto.
 * No uso @Data porque tambien genera equals y hashCode con todos los campos,
 * incluido el id, y el id cambia cuando se guarda en la base de datos.
 */
@Entity
@Table(name = "recurso")
@Getter
@Setter
@NoArgsConstructor
public class Recurso {

    /** El id lo genera la base de datos al insertar, no lo pongo yo. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, length = 12, unique = true)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 80)
    private String nombre;

    /** Es el unico campo que puede estar vacio. */
    @Column(name = "descripcion", length = 250)
    private String descripcion;

    /**
     * Es BigDecimal y no double porque para el tema de precios es mucho mas exacto.
     * Con precision 8 y scale 2 llega hasta 999999,99.
     */
    @Column(name = "coste_reposicion", nullable = false, precision = 8, scale = 2)
    private BigDecimal costeReposicion;

    @Column(name = "prestable", nullable = false)
    private Boolean prestable;
}
