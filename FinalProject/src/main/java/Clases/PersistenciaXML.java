package Clases;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.*;

public class PersistenciaXML implements Persistencia {
    private Object datos;

    @Override
    public void setDatos(Object datos) {
        this.datos = datos;
    }

    @Override
    public void guardarDatos() {
        if (datos == null) {
            System.out.println("No hay datos para guardar.");
            return;
        }
        // Utilizamos un hilo para la operación de guardado
        new Thread(() -> {
            try {
                serializarXml("datos.xml", datos);
                System.out.println("Datos guardados en XML exitosamente.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al guardar datos en XML.");
            }
        }).start();
    }

    @Override
    public void cargarDatos() {
        // Utilizamos otro hilo para la operación de carga
        new Thread(() -> {
            try {
                Object datosRecuperados = deserializarXml("datos.xml");
                System.out.println("Datos cargados desde XML: " + datosRecuperados);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al cargar datos desde XML.");
            }
        }).start();
    }

    // Serializar en formato XML
    private void serializarXml(String nombre, Object objeto) throws IOException {
        try (XMLEncoder codificador = new XMLEncoder(new FileOutputStream(nombre))) {
            codificador.writeObject(objeto);
        }
    }

    // Deserializar en formato XML
    private Object deserializarXml(String nombre) throws IOException {
        try (XMLDecoder decodificador = new XMLDecoder(new FileInputStream(nombre))) {
            return decodificador.readObject();
        }
    }
}

