package Clases;

import java.io.Serializable;
import java.util.List;

public class Vendedor extends Usuario implements Serializable {
    private List<Producto> productos;
    private List<Vendedor> contactos;
    private Muro muro;
    private double reputacion;

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void eliminarProducto(String id) {
        productos.removeIf(p -> p.getId().equals(id));
    }

    public void agregarContacto(Vendedor vendedor) {
        if (contactos.size() < 10) {
            contactos.add(vendedor);
        } else {
            System.out.println("Límite de contactos alcanzado");
        }
    }

    public void eliminarContacto(String id) {
        contactos.removeIf(v -> v.getId().equals(id));
    }

    public void publicarProducto(Producto producto) {
        agregarProducto(producto);
        muro.agregarPublicacion(new Publicacion());
    }

    public void enviarMensaje(Vendedor destinatario, String mensaje) {
        // Implementación pendiente
    }

    public void solicitarVinculo(Vendedor vendedor) {
        // Implementación pendiente
    }

    public void aceptarVinculo(Vendedor vendedor) {
        agregarContacto(vendedor);
    }

    public void rechazarVinculo(Vendedor vendedor) {
        // Implementación pendiente
    }

    public void calcularReputacion() {
        // Implementación pendiente
    }

    @Override
    public boolean validar() {
        // Implementar lógica de validación
        return true;
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

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                getId(), getNombre(), getApellido(), getDireccion(), getUsername(), getPassword(),
                productos.toString(), contactos.toString(),
                muro.toString(), reputacion);
    }
}
