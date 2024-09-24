package Clases;

import java.util.List;

public class Muro {
    private List<Publicacion> publicaciones;

    public void agregarPublicacion(Publicacion publicacion){}
    public void eliminarPublicacion(int id){}
    public List<Publicacion> obtenerPublicacionesOrdenadas(){
        return null;
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
