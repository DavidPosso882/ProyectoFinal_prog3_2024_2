package Clases;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ManejadorCliente implements Runnable {
    private Socket clienteSocket;

    public ManejadorCliente(Socket clienteSocket) {
        this.clienteSocket = clienteSocket;
    }

    @Override
    public void run() {
        try (
                ObjectOutputStream salida = new ObjectOutputStream(clienteSocket.getOutputStream());
                ObjectInputStream entrada = new ObjectInputStream(clienteSocket.getInputStream())
        ) {
            while (!clienteSocket.isClosed()) {
                try {
                    // Recibir primero tipo de entidad y luego operación
                    String tipoEntidad = (String) entrada.readObject();
                    String operacion = (String) entrada.readObject();

                    if ("Vendedor".equals(tipoEntidad)) {
                        manejarVendedor(operacion, entrada, salida);
                    } else if ("Producto".equals(tipoEntidad)) {
                        // manejarProducto(operacion, entrada, salida);
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
            try {
                clienteSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
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
            }
        } catch (Exception e) {
            System.err.println("Error manejando operación de vendedor: " + e.getMessage());
            salida.writeObject(false); // Enviar respuesta de error al cliente
            salida.flush();
        }
    }
}
