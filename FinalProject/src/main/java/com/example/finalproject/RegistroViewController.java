package com.example.finalproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

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

}


