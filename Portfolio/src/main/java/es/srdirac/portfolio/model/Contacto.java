package es.srdirac.portfolio.model;

// Record porque el contacto es un dato fijo que se prepara en el controlador y no cambia.
// El correo es opcional: si está vacío o el contacto es null, la plantilla oculta el apartado completo.
public record Contacto(String correo) {
}
