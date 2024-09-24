package Clases;

import java.time.LocalDateTime;

public class MeGusta {
    private String id;
    private LocalDateTime fecha;
    private Vendedor autor;

    public MeGusta(String id, LocalDateTime fecha, Vendedor autor) {
        this.id = id;
        this.fecha = fecha;
        this.autor = autor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
