package Clases;

import java.time.LocalDateTime;

public class Comentario {
    private String id;
    private String contenido;
    private LocalDateTime fecha;
    private Vendedor autor;

    public Comentario(String id, String contenido, LocalDateTime fecha, Vendedor autor) {
        this.id = id;
        this.contenido = contenido;
        this.fecha = fecha;
        this.autor = autor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
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
