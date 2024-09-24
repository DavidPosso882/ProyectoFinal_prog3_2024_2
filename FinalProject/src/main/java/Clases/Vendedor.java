package Clases;

import java.util.List;

public class Vendedor extends Usuario{
    private List<Producto> productos;
    private List<Vendedor> contactos;
    private Muro muro;
    private double reputacion;

    public void agregarProducto(Producto producto){}
    public void eliminarProducto(int id){}
    public void agregarContacto(Vendedor vendedor){}
    public void eliminarContacto(int id){}
    public void publicarProducto(Producto producto){}
    public void enviarMensaje(Vendedor destinatario, String mensaje){}
    public void solicitarVinculo(Vendedor vendedor){}
    public void aceptarVinculo(Vendedor vendedor){}
    public void rechazarVinculo(Vendedor vendedor){}
    public void calcularReputacion(){}

    @Override
    public boolean validar() {
        return false;
    }

    public Vendedor(String id, String nombre, String apellido, String direccion, String username, String password, List<Producto> productos, List<Vendedor> contactos, Muro muro, double reputacion) {
        super(id, nombre, apellido, direccion, username, password);
        this.productos = productos;
        this.contactos = contactos;
        this.muro = muro;
        this.reputacion = reputacion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Vendedor> getContactos() {
        return contactos;
    }

    public void setContactos(List<Vendedor> contactos) {
        this.contactos = contactos;
    }

    public Muro getMuro() {
        return muro;
    }

    public void setMuro(Muro muro) {
        this.muro = muro;
    }

    public double getReputacion() {
        return reputacion;
    }

    public void setReputacion(double reputacion) {
        this.reputacion = reputacion;
    }
}
