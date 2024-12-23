package Clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Producto> productos;
    private List<Vendedor> contactos;
    private Muro muro;
    private double reputacion;
    private List<Vendedor> solicitudesPendientes;

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    // Update
    public boolean actualizarProducto(String id, Producto productoActualizado) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId().equals(id)) {
                productos.set(i, productoActualizado);
                return true;
            }
        }
        return false;
    }

    public void eliminarProducto(String id) {
        productos.removeIf(p -> p.getId().equals(id));
    }

    public String agregarContacto(Vendedor vendedor) {
        if (contactos.size() < 10) {
            contactos.add(vendedor);
            return "Contacto agregado";
        } else {
            System.out.println("Límite de contactos alcanzado");
            return "Límite de contactos alcanzado";
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
        this.muro = new Muro(new ArrayList<>());
        this.reputacion = reputacion;
        this.solicitudesPendientes = new ArrayList<>();
    }

    public Vendedor() {

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

    public synchronized void recibirSolicitud(Vendedor remitente) {
        if (!contactos.contains(remitente) && !solicitudesPendientes.contains(remitente)) {
            solicitudesPendientes.add(remitente);
            System.out.println("Nueva solicitud de contacto recibida de " + remitente.getNombre());
        } else if (contactos.contains(remitente)) {
            System.out.println(remitente.getNombre() + " ya es un contacto.");
        } else {
            System.out.println("Ya existe una solicitud pendiente de " + remitente.getNombre());
        }
    }

    public synchronized String aceptarSolicitud (Vendedor solicitante) {
        if (solicitudesPendientes.contains(solicitante)) {
            agregarContacto(solicitante);
            solicitudesPendientes.remove(solicitante);
            System.out.println("Solicitud de " + solicitante.getNombre() + " aceptada.");
            return "Solicitud de " + solicitante.getNombre() + " aceptada.";
        } else {
            System.out.println("No hay solicitud pendiente de " + solicitante.getNombre());
            return "No hay solicitud pendiente de " + solicitante.getNombre();
        }
    }

    public synchronized String rechazarSolicitud(Vendedor solicitante) {
        if (solicitudesPendientes.remove(solicitante)) {
            System.out.println("Solicitud de " + solicitante.getNombre() + " rechazada.");
            return "Solicitud de " + solicitante.getNombre() + " rechazada.";
        } else {
            System.out.println("No hay solicitud pendiente de " + solicitante.getNombre());
            return "No hay solicitud pendiente de " + solicitante.getNombre();
        }
    }

    // Función para enviar solicitud de un vendedor a otro
    public synchronized String enviarSolicitud(Vendedor remitente, Vendedor destinatario) {
        if (!remitente.getContactos().contains(destinatario)) {
            destinatario.recibirSolicitud(remitente);
            System.out.println("Solicitud enviada de " + remitente.getNombre() + " a " + destinatario.getNombre());
            return "Solicitud enviada de " + remitente.getNombre() + " a " + destinatario.getNombre();
        } else {
            System.out.println("Ya son contactos.");
            return "Ya son contactos.";
        }
    }

    public List<Vendedor> getSolicitudesPendientes() {
        return new ArrayList<>(solicitudesPendientes);
    }

}
