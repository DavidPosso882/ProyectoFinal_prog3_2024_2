package com.example.finalproject;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginViewController {

    @FXML
    private PasswordField passField;

    @FXML
    private TextField textField;

    @FXML
    private Button btnRegistro;

    @FXML
    private ToggleButton tglBtn;

    @FXML
    void irFormulario() {
        try {
            // Cargar el FXML de la ventana de registro
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registro-view.fxml"));
            Parent root = fxmlLoader.load();

            // Crear el Stage y Scene para la ventana de registro
            Stage stage = new Stage();
            stage.setTitle("Formulario de Registro");
            stage.setScene(new Scene(root, 489, 493)); // Tamaño de la ventana de registro

            // Mostrar la ventana de registro
            stage.show();

            // Cerrar la ventana de login actual
            btnRegistro.getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void cambiarEstadoPass() {
        if (tglBtn.isSelected()) {
            // Mostrar la contraseña en el TextField
            textField.setText(passField.getText());
            textField.setVisible(true);
            passField.setVisible(false);
            tglBtn.setText("Ocultar");
        } else {
            // Volver a ocultar la contraseña en el PasswordField
            passField.setText(textField.getText());
            passField.setVisible(true);
            textField.setVisible(false);
            tglBtn.setText("Mostrar");
        }
    }
}




/*
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable {

    @FXML
    private Button btnInicioSesion;

    @FXML
    private Button btnRegistro;

    @FXML
    private PasswordField passField;

    @FXML
    private ToggleButton tglBtn;

    @FXML
    private TextField txtUsuario;

    @FXML
    private Label lblcontrasenia;

    @FXML
    void visibilizarPass(KeyEvent event) {
        lblcontrasenia.textProperty().bind(Bindings.concat(passField.getText()));
    }

    @FXML
    void irFormulario() {
        try {
            // Cargar el FXML de la ventana de registro
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registro-view.fxml"));
            Parent root = fxmlLoader.load();

            // Crear el Stage y Scene para la ventana de registro
            Stage stage = new Stage();
            stage.setTitle("Formulario de Registro");
            stage.setScene(new Scene(root, 489, 493)); // Tamaño de la ventana de registro

            // Mostrar la ventana de registro
            stage.show();

            // Cerrar la ventana de login actual
            btnRegistro.getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblcontrasenia.setVisible(false);
    }

    @FXML
    public void cambiarEstadoPass(javafx.event.ActionEvent actionEvent) {
        if (tglBtn.isSelected()) {
            // Mostrar la contraseña
            lblcontrasenia.setVisible(true);
            // Desenlazar antes de enlazar nuevamente
            lblcontrasenia.textProperty().unbind();
            lblcontrasenia.textProperty().bind(passField.textProperty());
            tglBtn.setText("Ocultar");
        } else {
            // Ocultar la contraseña
            lblcontrasenia.setVisible(false);
            // Desenlazar para poder modificar el texto
            lblcontrasenia.textProperty().unbind();
            tglBtn.setText("Mostrar");
        }
    }



}

 */



