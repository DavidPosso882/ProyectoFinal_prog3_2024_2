package Clases;

import java.util.List;

public interface Persistencia {
    void cargarDatos(String tipo);  // tipo puede ser 'xml', 'dat', 'txt'
    void guardarDatos(String tipo); // para elegir el formato
}

