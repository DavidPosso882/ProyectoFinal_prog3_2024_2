package Clases;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Muro implements Serializable {
    private List<Publicacion> publicaciones;



    public void agregarPublicacion(Publicacion publicacion) {
        publicaciones.add(publicacion);
    }

    public void eliminarPublicacion(String id) {
        publicaciones.removeIf(p -> p.getId().equals(id));
    }

    public List<Publicacion> obtenerPublicacionesOrdenadas() {
        if (publicaciones == null) {
            return new ArrayList<>();
        }



        List<Publicacion> publicacionesOrdenadas = new ArrayList<>(publicaciones);

        Collections.sort(publicacionesOrdenadas, new Comparator<Publicacion>() {
            @Override
            public int compare(Publicacion p1, Publicacion p2) {
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date fecha1 = formato.parse(p1.getFecha());
                    Date fecha2 = formato.parse(p2.getFecha());
                    return fecha2.compareTo(fecha1); // Orden descendente (más reciente primero)
                } catch (ParseException e) {
                    System.out.println("Error al parsear la fecha: " + e.getMessage());
                    return 0;
                }
            }
        });

        return publicacionesOrdenadas;
    }

    public Muro(List<Publicacion> publicaciones) {

        this.publicaciones = new ArrayList<>();
    }

    public Muro() {
    }



    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }
}
