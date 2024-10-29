package Clases;

import com.example.finalproject.Main;

import java.io.IOException;
import java.util.ArrayList;

public class HiloCarga implements  Runnable {
    private String ruta;

    public HiloCarga(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public void run() {
        System.out.println("carga de datos iniciada");
        try {
            Main.lista = (ArrayList<Object>) Utilidades.deserializarXml(ruta);
            //System.out.println(lista.get(0));
        } catch (IOException e) {
            System.out.println("Archivo no encontrado ");
            throw new RuntimeException(e);
        }

    }
}
