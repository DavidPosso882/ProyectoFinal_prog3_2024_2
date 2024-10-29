package Clases;

import java.io.IOException;

public class HiloSerializar implements Runnable{
    private String ruta;
    private Object lista;

    public HiloSerializar(String ruta, Object lista) {
        this.ruta = ruta;
        this.lista = lista;
    }

    @Override
    public void run() {
        System.out.println("proceso iniciado");
        try {
            Utilidades.serializarXml(ruta,lista);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("proceso terminado");
    }
}
