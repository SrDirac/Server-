# Mis proyectos personales (U4P3)

Aplicación web Spring Boot para el registro de los proyectos que desarrollo fuera de clase. Permite ver el listado de proyectos guardados en MariaDB y añadir uno nuevo desde un formulario en la misma página.

## Dominio y atributos

La entidad principal es `Proyecto`. Las restricciones de la tabla están en la entidad (`@Column`) y las validaciones del formulario en el DTO (`ProyectoForm`).

| Atributo | Tipo | Obligatorio | Reglas |
|---|---|---|---|
| `id` | `Long` | — | Lo genera la base de datos al insertar, no lo pongo yo |
| `nombre` | `String` | Sí | No puede estar vacío, máximo 60 caracteres |
| `descripcion` | `String` | No | Máximo 300 caracteres |
| `urlRepositorio` | `String` | No | Máximo 150 caracteres, tiene que ser una URL válida (`@URL` acepta el campo vacío) |
| `horasDedicadas` | `Integer` | Sí | Entre 0 y 2000 incluidos |
| `porcentajeCompletado` | `BigDecimal` | Sí | Entre 0.00 y 100.00, máximo 2 decimales |
| `fechaInicio` | `LocalDate` | Sí | No puede ser futura |
| `publicado` | `Boolean` | Sí | Si el checkbox no se marca, vale false |

## Base de datos

- Nombre de la base de datos: `misproyectos_db`. Hay que crearla vacía antes de arrancar la aplicación.
- La conexión está en `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/misproyectos_db
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
```

- El usuario y la contraseña no están en el código, se leen de las variables de entorno `DB_USER` y `DB_PASSWORD`.
- Con `ddl-auto=update` Hibernate crea la tabla `proyecto` a partir de la entidad y no borra los datos al reiniciar.

## Cómo iniciar la aplicación

```bash
export DB_USER=mi_usuario
export DB_PASSWORD=mi_contraseña
./mvnw spring-boot:run
```

La página se abre en http://localhost:8080

## Capas y responsabilidades

| Capa | Clase | Qué hace |
|---|---|---|
| Entidad | `model/Proyecto` | `@Entity` marca la clase como algo que se guarda en la base de datos, Hibernate la detecta al arrancar. `@Table(name = "proyecto")` le da el nombre a la tabla |
| Repositorio | `repository/ProyectoRepository` | Acceder a los datos y guardarlos, no se encarga de nada más. Hereda `JpaRepository`, que tiene `findAll()`, `save()`, `findById()` y `count()` sin escribir la query a mano. Aquí también van las consultas con `@Query` |
| Servicio | `service/ProyectoService` | Aquí es donde se define la lógica, hace de intermediario entre el controlador y el repositorio. Recibe el `ProyectoForm`, crea el `Proyecto` y se lo pasa al repositorio, que hace el `save` |
| Controlador | `controller/ProyectoController` | Es la parte web, recibe las peticiones del navegador (GET y POST), le pide los datos al servicio y se los pasa a la vista a través del `Model` |
| DTO | `dto/ProyectoForm` | Objeto de entrada del formulario. Solo tiene los campos que rellena el usuario (sin id) y las reglas de validación que se comprueban al enviar el formulario |
| Vista | `templates/index.html` | Thymeleaf genera la página en el servidor con los datos que le pasa el controlador. Aquí no hay lógica ni acceso a datos, solo se muestran |

### Separación de presentación y lógica

El controlador no accede nunca al repositorio directamente, todo lo hace a través del servicio, así cada capa se encarga solo de lo suyo. El repositorio solo guarda y consulta, el cómo se gestionan esos datos no es asunto suyo, de eso se encarga el servicio. La vista solo muestra lo que le llega en el `Model`.

El DTO se usa en lugar de la entidad para que la vista no trabaje directamente con el modelo persistente.

### Recorrido del formulario

1. `GET /` carga el listado completo y un `ProyectoForm` vacío, que es el que se enlaza con el formulario (`th:object`).
2. Al enviarlo, `POST /proyectos` lo recibe el método `guardar`. `@ModelAttribute` rellena el `ProyectoForm` con lo que ha escrito el usuario y `@Valid` comprueba las validaciones del DTO. Los errores se guardan en `BindingResult`.
3. Si hay errores no se guarda nada, se vuelve a mostrar la página con el mismo `proyectoForm`, por eso se conservan los valores escritos y salen los mensajes de error.
4. Si está bien, el servicio crea el `Proyecto` y lo guarda en MariaDB. Se hace `redirect:/` para que al recargar la página (F5) no se vuelva a enviar el formulario y se duplique el proyecto.

El formulario lleva `novalidate`, que desactiva la validación del navegador, así las validaciones las hace solo el servidor con las del DTO.

## Tecnologías de acceso a datos

- **MariaDB**: la base de datos donde se guardan los proyectos.
- **Spring Data JPA**: `JpaRepository` da los métodos de acceso a datos y permite escribir consultas propias con `@Query`.
- **Hibernate**: detecta la entidad al arrancar y crea o actualiza la tabla con `ddl-auto=update`.
- **Lombok**: `@Getter`, `@Setter` y `@NoArgsConstructor` escriben ese código al compilar.

## Principio o patrón aplicado

**Inyección de dependencias por constructor.** Se ve en `ProyectoService`, que recibe el repositorio en su constructor, y en `ProyectoController`, que recibe el servicio. No hago `new ProyectoService()`, Spring crea el servicio y me lo pasa al crear el controlador.

## Ampliación: consultas personalizadas

Las consultas están definidas en `ProyectoRepository` con `@Query`, y cada una tiene su método en el servicio.

| Opción | Ruta | Criterio | Método del servicio |
|---|---|---|---|
| Publicados | `/publicados` | Proyectos publicados, del más reciente al más antiguo por fecha de inicio | `listarPublicados()` |
| En curso | `/en-curso` | Proyectos aún no publicados, ordenados por porcentaje completado | `listarEnCurso()` |

Se accede desde los enlaces que hay encima del listado: *Todos los proyectos*, *Publicados* y *En curso*. Se reutiliza la misma vista, solo cambia la lista y el título que se muestra encima de la tabla. Si el controlador no envía título (listado completo), se usa el de por defecto. *Todos los proyectos* vuelve al listado completo.
