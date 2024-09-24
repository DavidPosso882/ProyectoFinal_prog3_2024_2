package Clases;

import java.util.List;

public class Publicacion {

    private String id;
    private String contenido;
    private String fecha;
    private Vendedor autor;
    private List<Comentario> comentarios;
    private List<MeGusta> meGusta;

    public void agregarComentarios(Comentario comentario) {}
    public void agregarMegusta(MeGusta meGusta) {}

    public Publicacion(String id, String contenido, String fecha, Vendedor autor, List<Comentario> comentarios, List<MeGusta> meGustas) {
        this.id = id;
        this.contenido = contenido;
        this.fecha = fecha;
        this.autor = autor;
        this.comentarios = comentarios;
        this.meGusta = meGustas;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Vendedor getAutor() {
        return autor;
    }

    public void setAutor(Vendedor autor) {
        this.autor = autor;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<MeGusta> getMeGusta() {
        return meGusta;
    }

    public void setMeGusta(List<MeGusta> meGusta) {
        this.meGusta = meGusta;
    }
}
