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
        //No tocar





        //Datos de prueba
        /*
        ArrayList<Vendedor>contactos=new ArrayList<>();
        Vendedor vendedor=MetodosCrud.obtenerVendedor("0000");
        Vendedor vendedor1=new Vendedor("Vendedor1","Vendedor1","Vendedor1","Vendedor1","Vendedor1","Vendedor1",new ArrayList<>(),new ArrayList<>(),new Muro(),0);
        contactos.add(vendedor);
        vendedor1.setContactos(contactos);
        MetodosCrud.actualizarVendedor(vendedor1);


        Vendedor vendedor=new Vendedor("0000","Daniela","Trejos","Direccion","dany","1234",new ArrayList<>(),contactos,new Muro(),0);

        Publicacion publicacion=new Publicacion("1","Publicacion","2023/06/02",new Vendedor("Vendedor1","Vendedor1","Vendedor1","Vendedor1","Vendedor1","Vendedor1",new ArrayList<>(),new ArrayList<>(),new Muro(),0));
        //SistemaMarketPlace.crearComentario("me gusto lo recomiendo",v2,publicacion);
        //System.out.println(publicacion.getComentarios());
        publicacion.agregarMegusta(new MeGusta(LocalDateTime.now(),vendedor));
        //System.out.println(publicacion.getMeGusta().size());
        Producto producto=new Producto("1","Producto","C:/Users/trejo/OneDrive/Imágenes/4.jpg",Categoria.HOGAR,1.0,EstadoProducto.PUBLICADO, LocalDateTime.now(),vendedor);
        listaVendedores.add(vendedor);
        listaVendedores.add(vendedor1);
        Utilidades.serializarXml(Utilidades.rutaVendedores,listaVendedores);
        //System.out.println(producto.getImagenVista());

        /*Vendedor v1=new Vendedor("Vendedor1","Vendedor1","Vendedor1","Vendedor1","Vendedor1","Vendedor1",new ArrayList<>(),new ArrayList<>(),new Muro(),0);

        v1.enviarSolicitud(v1,v2);
        v2.getSolicitudesPendientes().get(0);
        System.out.println(v2.getSolicitudesPendientes().get(0));*/

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