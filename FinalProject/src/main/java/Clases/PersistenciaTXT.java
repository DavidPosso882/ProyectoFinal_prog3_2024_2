package Clases;

import java.io.FileWriter;
import java.io.IOException;

public class PersistenciaTXT implements Persistencia {

    private Object datos;

    @Override
    public void guardarDatos() {
        guardarTXT("estadisticas.txt");
    }

    @Override
    public void cargarDatos() {
        // No tenga funcionalidad en este contexto, pero por si acaso.
        System.out.println("Cargar datos no soportado en PersistenciaTXT.");
    }

    @Override
    public void setDatos(Object datos) {
        this.datos = datos;  // Almacenar los datos que se guardarán en el archivo .txt
    }

    // Guardar estadísticas en un archivo TXT
    public void guardarTXT(String ruta) {
        try {
            FileWriter escritor = new FileWriter(ruta, true); // 'true' para añadir al archivo sin sobreescribir
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


