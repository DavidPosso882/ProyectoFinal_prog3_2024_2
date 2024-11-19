package Clases;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MeGusta implements Serializable {

    private LocalDateTime fecha;
    private Vendedor autor;


    public MeGusta(LocalDateTime fecha, Vendedor autor) {
        this.fecha = fecha;
        this.autor = autor;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Vendedor getAutor() {
        return autor;
    }

    public void setAutor(Vendedor autor) {
        this.autor = autor;
    }
}
