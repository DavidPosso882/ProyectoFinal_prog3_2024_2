package Clases;

import java.time.LocalDateTime;
import java.util.List;

public class Producto {
    private String id;
    private String nombre;
    private String imagen;
    private Categoria categoria;
    private double precio;
    private EstadoProducto estado;
    private LocalDateTime fechaPublicacion;
    private Vendedor vendedor;
    private List<Comentario> comentarios;
    private List<MeGusta> meGusta;

    public void agregarComentario(Comentario comentario){}
    public void agregarMeGusta(MeGusta meGusta){}
    public void cambiarEstado(EstadoProducto estado){}

    public Producto(String id, String nombre, String imagen, Categoria categoria, double precio, EstadoProducto estado, LocalDateTime fechaPublicacion, Vendedor vendedor, List<Comentario> comentarios, List<MeGusta> meGusta) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.categoria = categoria;
        this.precio = precio;
        this.estado = estado;
        this.fechaPublicacion = fechaPublicacion;
        this.vendedor = vendedor;
        this.comentarios = comentarios;
        this.meGusta = meGusta;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public EstadoProducto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProducto estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
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
