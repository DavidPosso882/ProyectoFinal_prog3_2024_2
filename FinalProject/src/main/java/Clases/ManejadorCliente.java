package Clases;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ManejadorCliente implements Runnable {
    private Socket clienteSocket;
    private static List<ManejadorCliente> clientes = new ArrayList<>();
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private String nombreUsuario;

    public ManejadorCliente(Socket clienteSocket) {
        this.clienteSocket = clienteSocket;
        synchronized (clientes) {
            clientes.add(this);
        }
    }

    @Override
    public void run() {
        try {
            // Crear streams de entrada y salida
            salida = new ObjectOutputStream(clienteSocket.getOutputStream());
            entrada = new ObjectInputStream(clienteSocket.getInputStream());

            while (!clienteSocket.isClosed()) {
                try {
                    // Leer el tipo de mensaje desde el cliente
                    String tipoMensaje = (String) entrada.readObject();
                    System.out.println("Tipo de mensaje recibido: " + tipoMensaje);

                    if ("CHAT".equals(tipoMensaje)) {
                        manejarChat(entrada);
                    } else if ("Vendedor".equals(tipoMensaje)) {
                        String operacion = (String) entrada.readObject();
                        manejarVendedor(operacion, entrada, salida);
                    } else if ("Producto".equals(tipoMensaje)) {
                        // Lógica futura para manejar productos
                        System.out.println("Operación de producto no implementada.");
                    }
                } catch (EOFException e) {
                    // Cliente cerró la conexión
                    break;
                } catch (Exception e) {
                    System.err.println("Error procesando solicitud: " + e.getMessage());
                    e.printStackTrace();
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error en la conexión: " + e.getMessage());
        } finally {
            // Limpiar recursos y notificar a los demás clientes
            synchronized (clientes) {
                clientes.remove(this);
            }
            if (nombreUsuario != null) {
                broadcastMensaje(nombreUsuario + " ha salido del chat.");
            }
            try {
                clienteSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void manejarChat(ObjectInputStream entrada) throws IOException, ClassNotFoundException {
        String operacionChat = (String) entrada.readObject();
        if ("LOGIN".equals(operacionChat)) {
            nombreUsuario = (String) entrada.readObject();
            System.out.println("Nuevo usuario conectado: " + nombreUsuario);
            broadcastMensaje(nombreUsuario + " se ha unido al chat.");
        } else if ("MESSAGE".equals(operacionChat)) {
            String mensaje = (String) entrada.readObject();
            if (mensaje != null) {
                System.out.println("Mensaje de chat recibido: " + mensaje);
                broadcastMensaje(nombreUsuario + ": " + mensaje);
            }
        }
    }

    private void manejarVendedor(String operacion, ObjectInputStream entrada, ObjectOutputStream salida)
            throws IOException, ClassNotFoundException {
        try {
            switch (operacion) {
                case "agregar":
                    Vendedor nuevoVendedor = (Vendedor) entrada.readObject();
                    boolean vendedorAgregado = MetodosCrud.agregarVendedor(nuevoVendedor);
                    salida.writeObject(vendedorAgregado);
                    salida.flush();
                    break;
                case "obtener":
                    int idVendedor = entrada.readInt();
                    Vendedor vendedorObtenido = MetodosCrud.obtenerVendedor(String.valueOf(idVendedor));
                    salida.writeObject(vendedorObtenido);
                    salida.flush();
                    break;
                case "actualizar":
                    Vendedor vendedorActualizado = (Vendedor) entrada.readObject();
                    boolean vendedorActualizadoResult = MetodosCrud.actualizarVendedor(vendedorActualizado);
                    salida.writeObject(vendedorActualizadoResult);
                    salida.flush();
                    break;
                case "eliminar":
                    int idEliminarVendedor = entrada.readInt();
                    boolean eliminado = MetodosCrud.eliminarVendedor(String.valueOf(idEliminarVendedor));
                    salida.writeBoolean(eliminado);
                    salida.flush();
                    break;
                default:
                    System.out.println("Operación de vendedor desconocida: " + operacion);
                    salida.writeObject(false); // Respuesta de error
                    salida.flush();
                    break;
            }
        } catch (Exception e) {
            System.err.println("Error manejando operación de vendedor: " + e.getMessage());
            salida.writeObject(false); // Respuesta de error
            salida.flush();
        }
    }

    private void broadcastMensaje(String mensaje) {
        synchronized (clientes) {
            for (ManejadorCliente cliente : clientes) {
                if (cliente != this) {
                    try {
                        cliente.salida.writeObject(mensaje);
                        cliente.salida.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}