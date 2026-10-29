# entregaP3P4 — Préstamo de material del aula maker

Aplicación con Spring Boot y Thymeleaf para ver el catálogo del aula maker y enviar
solicitudes de préstamo. No hay base de datos: las solicitudes solo se muestran.

## Cómo ejecutarlo

- Requisitos: Java 17 o superior.
- Ejecutar `P3p4springbootApplication` desde el IDE, o `./mvnw spring-boot:run` en la terminal.
- Abrir `http://localhost:8080/`.

## Rutas

- `GET /` → catálogo
- `GET /materiales/{codigo}?turno=...` → ficha de un material (turno por defecto: `general`)
- `GET /solicitud/nueva` → formulario
- `POST /solicitudes` → valida y muestra el resumen o los errores

## Ámbito de las variables

- **Propiedades del DTO** (`nombre`, `email`, `unidades`...): pertenecen al objeto `SolicitudPrestamoForm`.
- **Parámetros** (`codigo`, `turno`, `solicitud`...): solo existen dentro de su método.
- **Variables locales** (`material`, `dias`, `diasUnidad`...): solo existen en el bloque donde se declaran.
- **Atributos del modelo**: se añaden con `model.addAttribute("nombre", valor)` y la plantilla
  los lee con `${nombre}` solo durante esa petición.

## Pruebas realizadas

| Prueba | Resultado |
|---|---|
| Abrir `/` | Salen los 3 materiales y cada “Ver ficha” abre su ficha. |
| `/materiales/MICRO?turno=tarde` | Sale el kit de microcontrolador con turno “tarde”. |
| `/materiales/MICRO` (turno general) | Sale el kit de microcontrolador con turno “general”. |
| `/materiales/NO-EXISTE` (404) | Sale la página de error 404 y el estado HTTP es 404. |
| Formulario vacío | Salen errores en todos los campos y el selector sigue mostrando los materiales. |
| Correo no válido, 0 unidades, motivo corto | Salen esos tres errores y los demás campos conservan lo escrito. |
| Texto en unidades o fecha inválida | Sale “Introduce un número entero.” |
| Devolución antes de la recogida | Sale el error en la fecha de devolución. |
| Todo correcto | Sale el resumen con 3 días, 9 días-unidad y el mensaje de varios días; al volver, el formulario está vacío. |
| Texto con HTML en un campo | En el resumen se ve `<b>Hola</b>` tal cual, sin negrita. |
