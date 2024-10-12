package com.example.finalproject;

import Clases.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistroViewController {

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnFinalizar;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void volverLogin() {
        try {
            // Cargar el FXML de la ventana de login
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Parent root = fxmlLoader.load();

            // Crear la escena y el Stage para la ventana de login
            Stage stage = new Stage();
            stage.setTitle("Proyecto Programación III");
            stage.setScene(new Scene(root, 641, 488)); // Tamaño del login

            // Mostrar la ventana de login
            stage.show();

            // Cerrar la ventana de registro actual
            btnAtras.getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void finalizarRegistro() {
        // Obtener los datos del formulario
        String id = txtID.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String direccion = txtDireccion.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        // Crear un nuevo Vendedor con los datos del formulario
        List<Producto> productos = new ArrayList<>(); // Inicializa la lista de productos
        List<Vendedor> contactos = new ArrayList<>(); // Inicializa la lista de contactos
        Muro muro = new Muro(new ArrayList<>()); // Inicializa el muro
        double reputacion = 0.0; // Inicializa la reputación

        Vendedor nuevoVendedor = new Vendedor(id, nombre, apellido, direccion, username, password, productos, contactos, muro, reputacion);

        // Persistir los datos del nuevo vendedor
        Persistencia persistencia = new PersistenciaXML();
        persistencia.guardarDatos(String.valueOf(nuevoVendedor)); // Guardar el vendedor en formato XML

        // Mostrar mensaje de éxito 
        System.out.println("Registro exitoso del vendedor: " + nuevoVendedor.getNombre());

        // Cerrar la ventana del formulario de registro
        Stage stage = (Stage) btnFinalizar.getScene().getWindow();
        stage.close();

        // Volver a la vista de login
        volverLogin();
    }

}


