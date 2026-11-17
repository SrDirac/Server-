package com.example.u4p2.apartado2.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Material del aula maker.
 * No tiene relacion con PuestoTrabajo, son dos tablas separadas.
 * La tabla ya la crea el schema.sql, asi que esta clase se tiene que adaptar
 * a ese SQL y Hibernate solo comprueba que coincide (validate).
 *
 * Uso Lombok para que me genere los get, los set y el constructor vacio
 * (public MaterialAula(){}), que JPA lo necesita para crear el objeto.
 * No uso @Data porque tambien genera equals y hashCode con todos los campos,
 * incluido el id.
 */
@Entity
@Table(name = "material_aula")
@Getter
@Setter
@NoArgsConstructor
public class MaterialAula {

    /**
     * El id no lleva @GeneratedValue porque lo asigna un sistema externo,
     * no la base de datos ni Hibernate.
     */
    @Id
    private Long id;

    @Column(name = "referencia", length = 12, nullable = false, unique = true)
    private String referencia;

    /**
     * En Java se llama nombre porque ya estamos dentro de MaterialAula,
     * pero en el SQL es nombre_material porque asi viene en el schema.sql.
     */
    @Column(name = "nombre_material", length = 80, nullable = false)
    private String nombre;

    /** Es el unico campo que puede estar vacio. */
    @Column(name = "descripcion", length = 250)
    private String descripcion;

    /**
     * Es BigDecimal y no double porque para el tema de precios es mucho mas exacto.
     * Con precision 8 y scale 2 llega hasta 999999,99.
     */
    @Column(name = "coste_reposicion", precision = 8, scale = 2, nullable = false)
    private BigDecimal costeReposicion;

    /** Es Short porque en el SQL la columna es SMALLINT. */
    @Column(name = "unidades", nullable = false)
    private Short unidades;
}
