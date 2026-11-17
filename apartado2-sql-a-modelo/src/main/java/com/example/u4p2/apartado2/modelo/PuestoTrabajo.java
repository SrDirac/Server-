package com.example.u4p2.apartado2.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Puesto de trabajo del aula maker.
 * No tiene relacion con MaterialAula, son dos tablas separadas.
 * La tabla ya la crea el schema.sql, asi que esta clase se tiene que adaptar
 * a ese SQL y Hibernate solo comprueba que coincide (validate).
 *
 * Uso Lombok para que me genere los get, los set y el constructor vacio
 * (public PuestoTrabajo(){}), que JPA lo necesita para crear el objeto.
 * No uso @Data porque tambien genera equals y hashCode con todos los campos,
 * incluido el id.
 */
@Entity
@Table(name = "puesto_trabajo")
@Getter
@Setter
@NoArgsConstructor
public class PuestoTrabajo {

    /**
     * El id no lleva @GeneratedValue porque lo asigna un sistema externo,
     * no la base de datos ni Hibernate.
     */
    @Id
    private Long id;

    /**
     * En Java se llama codigo porque ya estamos dentro de PuestoTrabajo,
     * pero en el SQL es codigo_puesto porque asi viene en el schema.sql.
     */
    @Column(name = "codigo_puesto", length = 10, nullable = false, unique = true)
    private String codigo;

    @Column(name = "denominacion", length = 60, nullable = false)
    private String denominacion;

    @Column(name = "planta", nullable = false)
    private Integer planta;

    /** Indica si el puesto tiene enchufe / alimentacion electrica. */
    @Column(name = "tiene_electricidad", nullable = false)
    private Boolean tieneElectricidad;
}
