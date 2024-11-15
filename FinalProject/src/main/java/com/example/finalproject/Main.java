package com.example.finalproject;

import Clases.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main extends Application {

    public static ArrayList<Vendedor>listaVendedores=new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 641, 488);
        stage.setTitle("Proyecto Programación III");
        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args) throws InterruptedException, IOException {
        Utilidades.cargarRutas();
        MetodosCrud.actualizarDatos(listaVendedores,Utilidades.rutaVendedores);
        Thread hiloCopia=new Thread(new HiloCopiaDeRespaldo(Utilidades.rutaAOrigen,Utilidades.rutaARespaldo));
        hiloCopia.start();
        hiloCopia.join();

        //Implementación de sockets

        try (ServerSocket servidorSocket = new ServerSocket(5000)) {
            System.out.println("Servidor en funcionamiento, esperando conexiones...");

            while (true) {
                Socket clienteSocket = servidorSocket.accept();
                System.out.println("Cliente conectado");

                // Crear un nuevo hilo para manejar cada cliente
                ManejadorCliente manejador = new ManejadorCliente(clienteSocket);
                new Thread(manejador).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}