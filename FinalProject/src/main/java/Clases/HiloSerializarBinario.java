package Clases;

import java.io.IOException;
import java.util.ArrayList;

public class HiloSerializarBinario implements Runnable {
  private String ruta;
  private ArrayList<Object>lista;

    public HiloSerializarBinario(String ruta, ArrayList<Object> lista) {
        this.ruta = ruta;
        this.lista = lista;
    }

    @Override
    public void run() {
        System.out.println("proceso iniciado serializar en .dat");
        try {
            Utilidades.serializarBinario(ruta,lista);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Guardado en .dat");

    }
}
