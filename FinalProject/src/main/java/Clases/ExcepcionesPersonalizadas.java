package Clases;

public class ExcepcionesPersonalizadas {

    public static class VendedorNoEncontradoException extends Exception {
        public VendedorNoEncontradoException(String vendedorId) {
            super("No se pudo encontrar el vendedor con ID: " + vendedorId);
        }
    }

    public static class ProductoNoDisponibleException extends Exception {
        public ProductoNoDisponibleException(String productoId) {
            super("El producto con ID " + productoId + " no está disponible actualmente.");
        }
    }

    public static class AutenticacionFallidaException extends Exception {
        public AutenticacionFallidaException() {
            super("La autenticación ha fallado. Por favor, verifique sus credenciales.");
        }
    }

    public static class MaximoContactosAlcanzadoException extends Exception {
        public MaximoContactosAlcanzadoException(String usuarioId) {
            super("El usuario con ID " + usuarioId + " ha alcanzado el máximo de contactos permitidos.");
        }
    }

    public static class CalificacionInvalidaException extends Exception {
        public CalificacionInvalidaException(int calificacion) {
            super("La calificación " + calificacion + " no es válida. Debe estar entre 1 y 5.");
        }
    }

    public static class ProductoDuplicadoException extends Exception {
        public ProductoDuplicadoException(String productoId) {
            super("El producto con ID " + productoId + " ya existe en el catálogo del vendedor.");
        }
    }

    public static class CategoriaInvalidaException extends Exception {
        public CategoriaInvalidaException(String categoria) {
            super("La categoría '" + categoria + "' no es válida o no existe en el sistema.");
        }
    }

    public static class ExportacionFallidaException extends Exception {
        public ExportacionFallidaException(String tipoExportacion, String razon) {
            super("La exportación de " + tipoExportacion + " ha fallado. Razón: " + razon);
        }
    }

    public static class ImagenProductoInvalidaException extends Exception {
        public ImagenProductoInvalidaException(String razon) {
            super("La imagen del producto no es válida. Razón: " + razon);
        }
    }

    public static class BusquedaSinResultadosException extends Exception {
        public BusquedaSinResultadosException(String criterio) {
            super("La búsqueda con el criterio '" + criterio + "' no produjo ningún resultado.");
        }
    }

    public static class ActualizacionPerfilFallidaException extends Exception {
        public ActualizacionPerfilFallidaException(String usuarioId, String razon) {
            super("No se pudo actualizar el perfil del usuario con ID " + usuarioId + ". Razón: " + razon);
        }
    }

    // Añadimos la excepción VendedorDuplicadoException (esta es nueva, no la teniamos en la entrega anterior)
    public static class VendedorDuplicadoException extends Exception {
        public VendedorDuplicadoException(String vendedorId) {
            super("Ya existe un vendedor con el ID: " + vendedorId);
        }
    }
}
