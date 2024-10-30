package com.example.finalproject;

import Clases.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

import static com.example.finalproject.Main.lista;

public class RegistroViewController {
    private static final Logger LOGGER = Logger.getLogger(RegistroViewController.class.getName());

    static {
        try {
            // Crear un manejador de archivos para guardar los logs
            FileHandler fileHandler = new FileHandler("registro_vendedores.log", true);

            // Establecer un formato simple para los logs
            fileHandler.setFormatter(new SimpleFormatter());

            // Agregar el manejador de archivos al logger
            LOGGER.addHandler(fileHandler);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al configurar el logger para archivo", e);
        }
    }

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

    private SistemaMarketPlace sistema;

    public RegistroViewController() {
        // Inicializar el sistema
        this.sistema = SistemaMarketPlace.getInstance();
    }

    @FXML
    void volverLogin() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setTitle("Proyecto Programación III");
            stage.setScene(new Scene(root, 641, 488));
            stage.show();

            btnAtras.getScene().getWindow().hide();

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al volver a la pantalla de login", e);
            mostrarAlerta("Error", "No se pudo abrir la ventana de login.");
        }
    }

    @FXML
    void finalizarRegistro() {
        if (validarCampos()) {
            String id = txtID.getText();
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String direccion = txtDireccion.getText();
            String username = txtUsername.getText();
            String password = txtPassword.getText();

            List<Producto> productos = new ArrayList<>();
            List<Vendedor> contactos = new ArrayList<>();
            Muro muro = new Muro(new ArrayList<>());
            double reputacion = 0.0;

            Vendedor nuevoVendedor = new Vendedor(id, nombre, apellido, direccion, username, password, productos, contactos, muro, reputacion);

            try {
                sistema.registrarVendedor(nuevoVendedor);
                //LOGGER.log(Level.INFO, "Nuevo vendedor registrado: " + nuevoVendedor.getNombre());
                //Clases.Logger.configurarLogger(Utilidades.rutaLogVendedores,"Vendedor registrado exitosamente - ",null);
                mostrarAlerta("Éxito", "Registro exitoso del vendedor: " + nuevoVendedor.getNombre());


                // Cerrar la ventana del formulario de registro
                Stage stage = (Stage) btnFinalizar.getScene().getWindow();
                stage.close();

                // Volver a la vista de login
                volverLogin();
            } catch (ExcepcionesPersonalizadas.VendedorDuplicadoException e) {
                //LOGGER.log(Level.WARNING, "Intento de registro de vendedor duplicado");
                Clases.Logger.configurarLogger(Utilidades.rutaLogVendedores,"Intento de registro duplicado ",e);
                mostrarAlerta("Error", "El vendedor ya existe en el sistema.");
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error al registrar el vendedor", e);
                mostrarAlerta("Error", "No se pudo registrar el vendedor. Por favor, intente nuevamente.");
            }
        }
    }

    private boolean validarCampos() {
        if (txtID.getText().isEmpty() || txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty() ||
                txtDireccion.getText().isEmpty() || txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.");
            return false;
        }
        return true;
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}


