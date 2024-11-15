package Clases;

import java.io.IOException;
import java.util.ArrayList;

public class HiloCarga<T> implements Runnable {
    private String ruta;
    public ArrayList<T> lista;

    public HiloCarga(String ruta, ArrayList<T> lista) {
        this.ruta = ruta;
        this.lista = lista;
    }

    @Override
    public void run() {
        System.out.println("Carga de datos iniciada");
        try {
            ArrayList<T> datos = Utilidades.deserializarXml(ruta); // Ajusta el método deserializarXml para que sea genérico
            synchronized (lista) {
                lista.clear();
                lista.addAll(datos);
            }
            System.out.println("Datos cargados exitosamente");
        } catch (IOException e) {
            System.out.println("Archivo no encontrado");
            throw new RuntimeException(e);
        }
    }
}
