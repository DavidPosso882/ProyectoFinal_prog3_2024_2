package com.example.finalproject;

import Clases.PersistenciaXML;
import Clases.Vendedor;
import javafx.scene.control.Alert;
import jdk.jshell.execution.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class VendedorCrud {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    static String fechaSistema;

    public static void crearVendedor(Vendedor vendedor) {
        try {
            ArrayList<Vendedor> vendedores = (ArrayList<Vendedor>) PersistenciaXML.deserializarXml("vendedor.xml");
            vendedores.add(vendedor);

            ArrayList<String> lista = new ArrayList<>();
            for (Vendedor v : vendedores) {
                lista.add(v.toString());
            }
        }catch (IOException e){
            System.out.println("Error al crear el vendedor");
        }
        Utils.guardarRegistroLog("Se guardo un nuevo vendedor ", 1, " GUARDAR ", LOGGER);
    }

    public static void eliminarVendedor(String idBuscar) {
        boolean eliminado = false;
        try {
            ArrayList<Vendedor> vendedores = (ArrayList<Vendedor>) PersistenciaXML.deserializarBinario("vendedor.dat");
            for (int i = 0; i < vendedores.size(); i++) {
                if (vendedores.get(i).getId().equals(idBuscar)) {
                    vendedores.remove(i);
                    eliminado = true;
                    Utils.guardarRegistroLog("Se elimino un vendedor ", 2, " ELIMINAR ", LOGGER);
                    break;
                }
            }
            if (!eliminado) {
                Utils.guardarRegistroLog("Se intento eliminar un vendedor no existente ", 2, " ELIMINAR ", LOGGER);
            }
            PersistenciaXML.serializarBinario("vendedor.dat", vendedores);
            PersistenciaXML.serializarXml("vendedor.xml", vendedores);
        }catch (Exception e){
            System.out.println("Error al eliminar el vendedor");
        }
    }

    public static Vendedor buscarVendedor(String idBuscar) {
        boolean encontrado = false;
        Vendedor vendedor = new Vendedor();
        try {
            ArrayList<Vendedor> vendedores = (ArrayList<Vendedor>) PersistenciaXML.deserializarBinario("vendedor.dat");
            for (Vendedor v : vendedores) {
                if (v.getId().equals(idBuscar)) {
                    vendedor = v;
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
        return vendedor;
    }

    public static void modificarVendedor(Vendedor vendedor) {
        boolean encontrado = false;
        try {
            ArrayList<Vendedor> vendedores = (ArrayList<Vendedor>) PersistenciaXML.deserializarBinario("vendedor.dat");
            for (int i = 0; i < vendedores.size(); i++) {
                if (vendedores.get(i).getId().equals(vendedor.getId())) {
                    vendedores.set(i, vendedor);
                    encontrado = true;
                    Utils.guardarRegistroLog("Se modifico un vendedor ", 2, " MODIFICAR ", LOGGER);
                    break;
                }
            }
            if (!encontrado) {
                mostrarAlerta("NoEncontradoTitulo","NoEncontradoDescricion", Alert.AlertType.INFORMATION);
            }
            PersistenciaXML.serializarBinario("vendedor.dat", vendedores);
            PersistenciaXML.serializarXml("vendedor.xml", vendedores);
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
