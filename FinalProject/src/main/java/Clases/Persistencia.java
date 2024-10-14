package Clases;

public interface Persistencia {
    // Metodo para cargar datos
    void cargarDatos();

    // Metodo para guardar datos
    void guardarDatos();

    // Metodo para establecer los datos a guardar
    void setDatos(Object datos);
}

