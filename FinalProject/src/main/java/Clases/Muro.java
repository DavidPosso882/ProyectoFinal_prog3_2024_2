package Clases;

import java.io.Serializable;
import java.util.List;

public class Muro implements Serializable {
    private List<Publicacion> publicaciones;

    public void agregarPublicacion(Publicacion publicacion) {
        publicaciones.add(publicacion);
    }

    public void eliminarPublicacion(String id) {
        publicaciones.removeIf(p -> p.getId().equals(id));
    }

    public List<Publicacion> obtenerPublicacionesOrdenadas() {
        // Implementar lógica de ordenamiento
        return publicaciones;
    }

    public Muro(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }
}
