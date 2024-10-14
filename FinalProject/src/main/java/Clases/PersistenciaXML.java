package Clases;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.*;
import java.util.concurrent.CountDownLatch;

public class PersistenciaXML implements Persistencia {
    private Object datos;
    private static final String NOMBRE_ARCHIVO = "datos.xml";
    private CountDownLatch latch;

    public PersistenciaXML() {
        latch = new CountDownLatch(1);
    }

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
                serializarXml(NOMBRE_ARCHIVO, datos);
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
                Object datosRecuperados = deserializarXml(NOMBRE_ARCHIVO);
                if (datosRecuperados != null) {
                    this.datos = datosRecuperados;
                    System.out.println("Datos cargados desde XML exitosamente.");
                } else {
                    System.out.println("No se encontraron datos para cargar.");
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al cargar datos desde XML.");
            } finally {
                latch.countDown();
            }
        }).start();
    }

    // Serializar en formato XML
    private void serializarXml(String nombre, Object objeto) throws IOException {
        try (XMLEncoder codificador = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(nombre)))) {
            codificador.writeObject(objeto);
        }
    }

    // Deserializar en formato XML
    private Object deserializarXml(String nombre) throws IOException {
        File archivo = new File(nombre);
        if (!archivo.exists()) {
            System.out.println("El archivo XML no existe. Se devolverá null.");
            return null;
        }
        try (XMLDecoder decodificador = new XMLDecoder(new BufferedInputStream(new FileInputStream(nombre)))) {
            return decodificador.readObject();
        }
    }

    // Metodo para esperar a que la carga de datos se complete
    public void esperarCargaDatos() throws InterruptedException {
        latch.await();
    }

    // Metodo para obtener los datos cargados
    public Object getDatos() {
        return this.datos;
    }
}

