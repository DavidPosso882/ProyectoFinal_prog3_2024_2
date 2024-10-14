package Clases;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.*;
import java.util.ArrayList;

public class PersistenciaXML implements Persistencia {

    public PersistenciaXML(Object datos) {
        this.datos = datos;
    }

    public PersistenciaXML() {
    }

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
                //ArrayList<Vendedor> vendedores=(ArrayList<Vendedor>) deserializarXml("datos.xml");
                //vendedores.add((Vendedor) datos);
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
    public  void serializarXml(String nombre, Object objeto) throws IOException {
        try (XMLEncoder codificador = new XMLEncoder(new FileOutputStream(nombre))) {
            codificador.writeObject(objeto);
        }
    }

    // Deserializar en formato XML
    public  Object deserializarXml(String nombre) throws IOException {
        try (XMLDecoder decodificador = new XMLDecoder(new FileInputStream(nombre))) {
            return decodificador.readObject();
        }
    }
}

