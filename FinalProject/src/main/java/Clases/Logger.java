package Clases;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class Logger {

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(SistemaMarketPlace.class.getName());

    public static void configurarLogger(String rutaDirectorio, String mensaje, Throwable excepcion) {
        try {
            FileHandler fileHandler = new FileHandler(rutaDirectorio, true);
            fileHandler.setLevel(Level.SEVERE); // Cambiado a SEVERE para excepciones
            fileHandler.setFormatter(new SimpleFormatter());

            LOGGER.addHandler(fileHandler);
            LOGGER.setUseParentHandlers(false);
            if (excepcion != null) {
                // Obtener el nombre completo de la excepción
                String nombreExcepcion = excepcion.getClass().getSimpleName(); // Cambiado a getSimpleName()
                // Obtener el mensaje de la excepción
                String mensajeExcepcion = excepcion.getMessage() != null ? excepcion.getMessage() : "Sin mensaje";

                // Crear el mensaje en el formato deseado
                String mensajeCompleto = String.format("%s: %s", nombreExcepcion, mensajeExcepcion);
                // Registrar el mensaje junto con la excepción
                LOGGER.log(Level.SEVERE, mensaje + " - " + mensajeCompleto);
            } else {
                // Registrar el mensaje sin excepciones
                LOGGER.log(Level.INFO, mensaje + " - Sin excepciones.");
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "No se pudo configurar el FileHandler en la ruta especificada: " + e.getMessage());
        }
    }

    /*public static void configurarLogger(String rutaDirectorio, String mensaje, Throwable excepcion) {
        try {
            FileHandler fileHandler = new FileHandler(rutaDirectorio, true);
            fileHandler.setLevel(Level.SEVERE); // Cambiado a SEVERE para excepciones
            fileHandler.setFormatter(new SimpleFormatter());

            LOGGER.addHandler(fileHandler);
            LOGGER.setUseParentHandlers(false);

            // Obtener el nombre completo de la excepción
            String nombreExcepcion = excepcion.getClass().getName();

            // Obtener el mensaje de la excepción
            String mensajeExcepcion = excepcion.getMessage();

            // Crear el mensaje en el formato deseado
            String mensajeCompleto = String.format("%s: %s", nombreExcepcion, mensajeExcepcion);

            // Registrar el mensaje sin el stack trace
            LOGGER.log(Level.SEVERE, mensaje +" - " + mensajeCompleto);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "No se pudo configurar el FileHandler en la ruta especificada: " + e.getMessage());
        }
    }*/
}
