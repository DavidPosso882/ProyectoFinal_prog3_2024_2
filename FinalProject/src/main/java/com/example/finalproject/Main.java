package com.example.finalproject;

import Clases.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 641, 488);
        stage.setTitle("Proyecto Programación III");
        stage.setScene(scene);
        stage.show();

    }
    public static ArrayList<Object>lista=new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        //launch();
        Vendedor vendedor=new Vendedor("6","plo","t","fgg","de","uuuhg",null,null,null,12);


        /*Thread hilo2=new Thread(new HiloCarga("vendedor.xml"),"HiloCarga xml");
        hilo2.start();
        hilo2.join();
        //System.out.println(lista.get(0).toString());
        lista.add(vendedor);
        Thread hilo1=new Thread(new HiloSerializar("vendedor.xml",lista),"Hilo xml");
        hilo1.start();
        hilo1.join();*/

        Thread hiloCopia=new Thread(new HiloCopiaDeRespaldo("C:/Users/trejo/OneDrive/Documentos/ProgramacionTres/Git/ProyectoFinal_prog3_2024_2/FinalProject/ProyectoFinal/Persistencia/Archivo","C:/Users/trejo/OneDrive/Documentos/ProgramacionTres/Git/ProyectoFinal_prog3_2024_2/FinalProject/ProyectoFinal/Persistencia/Respaldo"));
        hiloCopia.start();
        hiloCopia.join();


    }
}