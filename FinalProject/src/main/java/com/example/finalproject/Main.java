package com.example.finalproject;

import Clases.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
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
        //No tocar
        Utilidades.cargarRutas();
        MetodosCrud.actualizarDatos(listaVendedores,Utilidades.rutaVendedores);
        Thread hiloCopia=new Thread(new HiloCopiaDeRespaldo(Utilidades.rutaAOrigen,Utilidades.rutaARespaldo));
        hiloCopia.start();
        hiloCopia.join();

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Servidor iniciado en el puerto 5000");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado");

                ManejadorCliente manejador = new ManejadorCliente(clientSocket);
                new Thread(manejador).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}