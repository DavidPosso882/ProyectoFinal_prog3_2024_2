package Clases;

import java.util.ArrayList;
import java.util.List;

public class Publicacion {

    private String id;
    private String contenido;
    private String fecha;
    private Vendedor autor;
    private List<Comentario> comentarios;
    private List<MeGusta> meGusta;


    public Publicacion() {
    }



    public void agregarComentarios(Comentario comentario) {
        comentarios.add(comentario);
    }


    public void agregarMegusta(MeGusta meGusta) {
        if (this.meGusta == null) {
            this.meGusta = new ArrayList<>();
        }

        // Verificar si el vendedor ya dio me gusta
        boolean yaExiste = this.meGusta.stream()
                .anyMatch(mg -> mg.getAutor().getId().equals(meGusta.getAutor().getId()));

        if (!yaExiste) {
            this.meGusta.add(meGusta);
            System.out.println(meGusta.getAutor().getNombre() + " ha dado me gusta a la publicación.");
        } else {
            System.out.println(meGusta.getAutor().getNombre() + " ya había dado me gusta a esta publicación.");
        }
    }

    public Publicacion(String id, String contenido, String fecha, Vendedor autor) {
        this.id = id;
        this.contenido = contenido;
        this.fecha = fecha;
        this.autor = autor;
        this.comentarios = new ArrayList<>();
        this.meGusta = new ArrayList<>();
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
