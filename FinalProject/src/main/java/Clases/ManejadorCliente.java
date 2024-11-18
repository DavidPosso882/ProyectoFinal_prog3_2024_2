package Clases;

import java.io.*;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import static Clases.SistemaMarketPlace.LOGGER;

public class ManejadorCliente implements Runnable {
    private Socket clienteSocket;
    private static Map<String, ManejadorCliente> clientesConectados =
            Collections.synchronizedMap(new HashMap<>());
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private String nombreUsuario;

    public ManejadorCliente(Socket clienteSocket) {
        this.clienteSocket = clienteSocket;
    }

    @Override
    public void run() {
        try {
            salida = new ObjectOutputStream(clienteSocket.getOutputStream());
            entrada = new ObjectInputStream(clienteSocket.getInputStream());

            while (!clienteSocket.isClosed()) {
                String tipoMensaje = (String) entrada.readObject();
                LOGGER.info("Tipo de mensaje recibido: " + tipoMensaje);

                switch (tipoMensaje) {
                    case "AUTENTICAR":
                        manejarAutenticacion();
                        break;
                    case "INICIAR_CHAT":
                        iniciarChat();
                        break;
                    case "CHAT":
                        manejarChat();
                        break;
                    case "CERRAR_CHAT":
                        cerrarConexion();
                        return;
                    default:
                        LOGGER.warning("Tipo de mensaje desconocido: " + tipoMensaje);
                        break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error en la conexión con el cliente", e);
        } finally {
            cerrarConexion();
        }
    }

    private void manejarAutenticacion() throws IOException, ClassNotFoundException {
        String username = (String) entrada.readObject();
        String password = (String) entrada.readObject();

        Vendedor vendedor = SistemaMarketPlace.autenticarVendedor(username, password);
        boolean autenticado = (vendedor != null);

        salida.writeBoolean(autenticado);
        if (autenticado) {
            salida.writeObject(vendedor);
            nombreUsuario = username;
            clientesConectados.put(nombreUsuario, this);
            LOGGER.info("Usuario autenticado: " + username);
        }
        salida.flush();
    }

    private void iniciarChat() throws IOException, ClassNotFoundException {
        nombreUsuario = (String) entrada.readObject();
        clientesConectados.put(nombreUsuario, this);

        LOGGER.info("Chat iniciado por " + nombreUsuario);

        String mensajeAlerta = "ALERTA: " + nombreUsuario + " se ha unido al chat.";
        broadcastMensaje(mensajeAlerta);

        salida.writeObject("Chat iniciado. Bienvenido " + nombreUsuario);
        salida.flush();
    }

    private void manejarChat() throws IOException, ClassNotFoundException {
        String mensaje = (String) entrada.readObject();
        String mensajeCompleto = nombreUsuario + ": " + mensaje;
        LOGGER.info("Mensaje recibido de " + nombreUsuario + ": " + mensaje);
        broadcastMensaje(mensajeCompleto);
    }

    private void broadcastMensaje(String mensaje) {
        for (ManejadorCliente cliente : clientesConectados.values()) {
            try {
                cliente.salida.writeObject(mensaje);
                cliente.salida.flush();
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "Error al enviar mensaje a " + cliente.nombreUsuario, e);
            }
        }
    }

    private void cerrarConexion() {
        try {
            if (nombreUsuario != null) {
                clientesConectados.remove(nombreUsuario);
                LOGGER.info("Cliente desconectado: " + nombreUsuario);

                String mensajeAlerta = "ALERTA: " + nombreUsuario + " ha salido del chat.";
                broadcastMensaje(mensajeAlerta);
            }
            if (salida != null) salida.close();
            if (entrada != null) entrada.close();
            if (clienteSocket != null && !clienteSocket.isClosed()) clienteSocket.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al cerrar la conexión", e);
        }
    }
}