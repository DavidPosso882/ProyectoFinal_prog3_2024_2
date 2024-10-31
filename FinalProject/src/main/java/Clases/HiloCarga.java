package Clases;

import java.io.IOException;
import java.util.ArrayList;

public class HiloCarga implements  Runnable {
    private String ruta;
    public ArrayList<Object>lista;

    public HiloCarga(String ruta, ArrayList<Object> lista) {
        this.ruta = ruta;
        this.lista = lista;
    }

    @Override
    public void run() {
        System.out.println("Carga de datos iniciada");
        try {
            ArrayList<Object> datos = (ArrayList<Object>) Utilidades.deserializarXml(ruta);
            synchronized (lista) {
                lista.clear();
                lista.addAll(datos);
            }
        } catch (IOException e) {
            System.out.println("Archivo no encontrado");
            throw new RuntimeException(e);
        }

    }
}
