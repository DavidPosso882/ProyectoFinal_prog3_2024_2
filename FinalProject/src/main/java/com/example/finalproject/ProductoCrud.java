package com.example.finalproject;

import Clases.PersistenciaXML;
import Clases.Producto;
import Clases.Vendedor;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Logger;

public class ProductoCrud {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    static String fechaSistema;

    public static void crearProducto(Producto producto) {
        try {
            ArrayList<Producto> productos = (ArrayList<Producto>) PersistenciaXML.deserializarXml("producto.xml");
            productos.add(producto);

            ArrayList<String> lista = new ArrayList<>();
            for (Producto p : productos) {
                lista.add(p.toString());
            }
        }catch (IOException e){
            System.out.println("Error al crear el producto");
        }
        Utils.guardarRegistroLog("Se guardo un nuevo producto ", 1, " GUARDAR ", LOGGER);
    }

    public static void eliminarProducto(String idBuscar) {
        boolean eliminado = false;
        try {
            ArrayList<Producto> productos = (ArrayList<Producto>) PersistenciaXML.deserializarBinario("producto.dat");
            for (int i = 0; i < productos.size(); i++) {
                if (productos.get(i).getId().equals(idBuscar)) {
                    productos.remove(i);
                    eliminado = true;
                    Utils.guardarRegistroLog("Se elimino un producto ", 2, " ELIMINAR ", LOGGER);
                    break;
                }
            }
            if (!eliminado) {
                Utils.guardarRegistroLog("Se intento eliminar un producto no existente ", 2, " ELIMINAR ", LOGGER);
            }
            PersistenciaXML.serializarBinario("producto.dat", productos);
            PersistenciaXML.serializarXml("producto.xml", productos);
        }catch (Exception e){
            System.out.println("Error al eliminar el producto");
        }
    }

    public static Producto buscarProducto(String idBuscar) {
        boolean encontrado = false;
        Producto producto = new Producto();
        try {
            ArrayList<Producto> productos = (ArrayList<Producto>) PersistenciaXML.deserializarBinario("producto.dat");
            for (Producto p : productos) {
                if (p.getId().equals(idBuscar)) {
                    producto = p;
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                mostrarAlerta("NoEncontradoTitulo","NoEncontradoDescricion", Alert.AlertType.INFORMATION);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return producto;
    }

    public static void modificarProducto(Producto producto) {
        boolean encontrado = false;
        try {
            ArrayList<Producto> productos = (ArrayList<Producto>) PersistenciaXML.deserializarBinario("producto.dat");
            for (int i = 0; i < productos.size(); i++) {
                if (productos.get(i).getId().equals(producto.getId())) {
                    productos.set(i, producto);
                    encontrado = true;
                    Utils.guardarRegistroLog("Se modifico un producto ", 2, " MODIFICAR ", LOGGER);
                    break;
                }
            }
            if (!encontrado) {
                mostrarAlerta("NoEncontradoTitulo","NoEncontradoDescricion", Alert.AlertType.INFORMATION);
            }
            PersistenciaXML.serializarBinario("producto.dat", productos);
            PersistenciaXML.serializarXml("producto.xml", productos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void mostrarAlerta(String tituloClave, String mensajeClave, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.showAndWait();
    }

    private static void cargarFechaSistema() {
        Calendar cal = Calendar.getInstance();
        int dia = cal.get(Calendar.DATE);
        int mes = cal.get(Calendar.MONTH) + 1;
        int año = cal.get(Calendar.YEAR);
        int hora = cal.get(Calendar.HOUR_OF_DAY);
        int minuto = cal.get(Calendar.MINUTE);

        fechaSistema = String.format("%04d-%02d-%02d %02d:%02d", año, mes, dia, hora, minuto);
    }
}
