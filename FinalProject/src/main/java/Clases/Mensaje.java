package Clases;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Mensaje implements Serializable {
    private String id;
    private String contenido;
    private LocalDateTime fecha;
    private Vendedor emisor;
    private Vendedor receptor;

    public Mensaje(String id, String contenido, LocalDateTime fecha, Vendedor emisor, Vendedor receptor) {
        this.id = id;
        this.contenido = contenido;
        this.fecha = fecha;
        this.emisor = emisor;
        this.receptor = receptor;
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

    public Vendedor getEmisor() {
        return emisor;
    }

    public void setEmisor(Vendedor emisor) {
        this.emisor = emisor;
    }

    public Vendedor getReceptor() {
        return receptor;
    }

    public void setReceptor(Vendedor receptor) {
        this.receptor = receptor;
    }
}
