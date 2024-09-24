package Clases;

import java.util.List;

public class Chat {
    private String id;
    private List<Vendedor> participantes;
    private List<Mensaje> mensajes;

    public void enviarMensaje(Mensaje mensaje){}
    public List<Mensaje> obtenerHistorial(){
        return null;
    }

    public Chat(String id, List<Vendedor> participantes, List<Mensaje> mensajes) {
        this.id = id;
        this.participantes = participantes;
        this.mensajes = mensajes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Vendedor> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Vendedor> participantes) {
        this.participantes = participantes;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }
}
