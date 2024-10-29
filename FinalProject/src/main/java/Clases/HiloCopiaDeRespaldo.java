package Clases;

import java.io.IOException;

public class HiloCopiaDeRespaldo implements Runnable{
    private String rutaArchivoOriginal;
    private String rutaCarpetaRespaldo;

    public HiloCopiaDeRespaldo(String rutaArchivoOriginal, String rutaCarpetaRespaldo) {
        this.rutaArchivoOriginal = rutaArchivoOriginal;
        this.rutaCarpetaRespaldo = rutaCarpetaRespaldo;
    }

    @Override
    public void run() {
        try {
            Utilidades.realizarCopiaSeguridad(rutaArchivoOriginal,rutaCarpetaRespaldo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
