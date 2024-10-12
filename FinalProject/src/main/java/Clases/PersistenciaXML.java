package Clases;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.*;
import java.util.List;

public class PersistenciaXML implements Persistencia {

    Vendedor vendedor = new Vendedor("123", "David", "Posso", "Calle 123", "DavidNice", "password123", null, null, null, 0.0);

    @Override
    public void guardarDatos(String tipo) {
        if (tipo.equals("xml")) {
            try {
                serializarXml("vendedor.xml", vendedor);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (tipo.equals("dat")) {
            try {
                serializarBinario("vendedor.dat", vendedor);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Formato no soportado en PersistenciaXML.");
        }
    }

    @Override
    public void cargarDatos(String tipo) {
        if (tipo.equals("xml")) {
            try {
                Vendedor vendedor = (Vendedor) deserializarXml("vendedor.xml");
                System.out.println("Datos cargados desde XML: " + vendedor);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (tipo.equals("dat")) {
            try {
                Vendedor vendedor = (Vendedor) deserializarBinario("vendedor.dat");
                System.out.println("Datos cargados desde .dat: " + vendedor);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Formato no soportado en PersistenciaXML.");
        }
    }

    // Serializar en formato binario
    public void serializarBinario(String nombre, Object objeto) throws IOException {
        ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(nombre));
        salida.writeObject(objeto);
        salida.close();
    }

    // Deserializar en formato binario
    public Object deserializarBinario(String nombre) throws IOException, ClassNotFoundException {
        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(nombre));
        Object objeto = entrada.readObject();
        entrada.close();
        return objeto;
    }

    // Serializar en formato XML
    public void serializarXml(String nombre, Object objeto) throws IOException {
        XMLEncoder codificador = new XMLEncoder(new FileOutputStream(nombre));
        codificador.writeObject(objeto);
        codificador.close();
    }

    // Deserializar en formato XML
    public Object deserializarXml(String nombre) throws IOException {
        XMLDecoder decodificador = new XMLDecoder(new FileInputStream(nombre));
        Object objeto = decodificador.readObject();
        decodificador.close();
        return objeto;
    }
}

