package Clases;

public class HiloCopiaDeRespaldo implements Runnable{
    private String rutaArchivoOriginal;
    private String rutaCarpetaRespaldo;

    public HiloCopiaDeRespaldo(String rutaArchivoOriginal, String rutaCarpetaRespaldo) {
        this.rutaArchivoOriginal = rutaArchivoOriginal;
        this.rutaCarpetaRespaldo = rutaCarpetaRespaldo;
    }

    @Override
    public void run() {
        Utilidades.realizarCopiaSeguridad(rutaArchivoOriginal,rutaCarpetaRespaldo);
    }
}
