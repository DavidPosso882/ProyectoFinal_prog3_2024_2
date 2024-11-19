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
                    case "CREAR_VENDEDOR":
                        manejarCrearVendedor();
                        break;
                    case "OBTENER_VENDEDOR":
                        manejarObtenerVendedor();
                        break;
                    case "ACTUALIZAR_VENDEDOR":
                        manejarActualizarVendedor();
                        break;
                    case "ELIMINAR_VENDEDOR":
                        manejarEliminarVendedor();
                        break;
                    case "AGREGAR_PRODUCTO":
                        manejarAgregarProducto();
                        break;
                    case "AGREGAR_PUBLICACION":
                        manejarAgregarPublicacion();
                        break;
                    case "OBTENER_CONTACTOS":
                        manejarObtenerContactos();
                        break;
                    default:
                        LOGGER.warning("Tipo de mensaje desconocido: " + tipoMensaje);
                        break;
                }
            }
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
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

    private void manejarCrearVendedor() throws IOException, ClassNotFoundException, InterruptedException {
        Vendedor nuevoVendedor = (Vendedor) entrada.readObject();
        boolean exito = MetodosCrud.agregarVendedor(nuevoVendedor);
        salida.writeBoolean(exito);
        if (exito) {
            salida.writeObject(nuevoVendedor);
        }
        salida.flush();
        LOGGER.info("Creación de vendedor " + (exito ? "exitosa" : "fallida") + ": " + nuevoVendedor.getUsername());
    }

    private void manejarObtenerVendedor() throws IOException, ClassNotFoundException {
        String id = (String) entrada.readObject();
        Vendedor vendedor = MetodosCrud.obtenerVendedor(id);
        boolean exito = (vendedor != null);
        salida.writeBoolean(exito);
        if (exito) {
            salida.writeObject(vendedor);
        }
        salida.flush();
        LOGGER.info("Obtención de vendedor " + (exito ? "exitosa" : "fallida") + ": " + id);
    }

    private void manejarActualizarVendedor() throws IOException, ClassNotFoundException, InterruptedException {
        Vendedor vendedorActualizado = (Vendedor) entrada.readObject();
        boolean exito =MetodosCrud.actualizarVendedor(vendedorActualizado);
        salida.writeBoolean(exito);
        salida.flush();
        LOGGER.info("Actualización de vendedor " + (exito ? "exitosa" : "fallida") + ": " + vendedorActualizado.getUsername());
    }

    private void manejarEliminarVendedor() throws IOException, ClassNotFoundException, InterruptedException {
        String id = (String) entrada.readObject();
        boolean exito = MetodosCrud.eliminarVendedor(id);
        salida.writeBoolean(exito);
        salida.flush();
        LOGGER.info("Eliminación de vendedor " + (exito ? "exitosa" : "fallida") + ": " + id);
    }

    private void manejarAgregarProducto() throws IOException, ClassNotFoundException {
        String idVendedor = (String) entrada.readObject();
        Producto producto = (Producto) entrada.readObject();
        boolean exito = SistemaMarketPlace.agregarProducto(idVendedor, producto);
        salida.writeBoolean(exito);
        salida.flush();
        LOGGER.info("Agregar producto " + (exito ? "exitoso" : "fallido") + " para vendedor: " + idVendedor);
    }

    private void manejarAgregarPublicacion() throws IOException, ClassNotFoundException {
        String idVendedor = (String) entrada.readObject();
        Publicacion publicacion = (Publicacion) entrada.readObject();
        boolean exito = SistemaMarketPlace.agregarPublicacion(idVendedor, publicacion);
        salida.writeBoolean(exito);
        salida.flush();
        LOGGER.info("Agregar publicación " + (exito ? "exitosa" : "fallida") + " para vendedor: " + idVendedor);
    }

    private void manejarObtenerContactos() throws IOException, ClassNotFoundException {
        String idVendedor = (String) entrada.readObject();
        Map<String, Vendedor> contactos = SistemaMarketPlace.obtenerContactos(idVendedor);
        boolean exito = (contactos != null);
        salida.writeBoolean(exito);
        if (exito) {
            salida.writeObject(contactos);
        }
        salida.flush();
        LOGGER.info("Obtención de contactos " + (exito ? "exitosa" : "fallida") + " para vendedor: " + idVendedor);
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