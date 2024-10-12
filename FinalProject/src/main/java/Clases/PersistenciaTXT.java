package Clases;

import java.io.FileWriter;
import java.io.IOException;   //Eliminar List<Object> objeto
import java.util.List;

public class PersistenciaTXT implements Persistencia {

    @Override
    public void guardarDatos(String tipo) {
        if (tipo.equals("txt")) {
            guardarTXT("estadisticas.txt");
        } else {
            System.out.println("Formato no soportado en PersistenciaTXT.");
        }
    }

    @Override
    public void cargarDatos(String tipo) {
        // Este metodo no se implementa en este caso, porque estamos guardando estadísticas en texto,
        // pero por si acaso
        System.out.println("Cargar datos no soportado en PersistenciaTXT.");
    }

    // Guardar estadísticas en un archivo TXT
    public void guardarTXT(String ruta) {
        try {
            FileWriter escritor = new FileWriter(ruta, true); // 'true' para añadir al archivo sin sobreescribir
            // Ejemplo de estadística: se debe sustituir con datos reales
            escritor.write("Estadísticas de ventas: \n");
            escritor.write("Número de productos vendidos: 100\n");
            escritor.write("Total ganancias: $5000\n");
            escritor.close();

            System.out.println("Estadísticas guardadas en archivo TXT.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

