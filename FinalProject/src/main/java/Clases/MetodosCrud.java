package Clases;

import com.example.finalproject.Main;

import java.util.ArrayList;

public class MetodosCrud {
    // Crear
    public static boolean agregarVendedor(Vendedor vendedor) throws InterruptedException {
        if(obtenerVendedor(vendedor.getId()) == null){
            Main.listaVendedores.add(vendedor);
            realizarCambios(Main.listaVendedores,Utilidades.rutaVendedores);
            return true;
        }
        else{
            return false;
        }

    }

    // Leer
    public static Vendedor obtenerVendedor(String id) {
        for (Vendedor vendedor : Main.listaVendedores) {
            if (vendedor.getId().equals(id)) {
                return vendedor;
            }
        }
        return null; // Si no se encuentra el vendedor
    }

    public static boolean actualizarVendedor(Vendedor vendedor) throws InterruptedException {
        boolean existe=false;
        for (int i = 0; i < Main.listaVendedores.size(); i++) {
            if (Main.listaVendedores.get(i).getId().equals(vendedor.getId())) {
                Main.listaVendedores.set(i, vendedor);
                realizarCambios(Main.listaVendedores,Utilidades.rutaVendedores);
                existe=true;
            }
        }
       return existe;
    }

    // Eliminar
    public static boolean eliminarVendedor(String id) throws InterruptedException {
        if(obtenerVendedor(id) == null){
            return false;
        }
        else {
            Main.listaVendedores.removeIf(vendedor -> vendedor.getId().equals(id));
            realizarCambios(Main.listaVendedores,Utilidades.rutaVendedores);
            return true;
        }


    }

    public static <T> void actualizarDatos(ArrayList<T> datos,String ruta) throws InterruptedException {
        Thread hilo2=new Thread(new HiloCarga(ruta, datos),"HiloCarga xml");
        hilo2.start();
        hilo2.join();
    }
    public static <T> void realizarCambios(ArrayList<T> datos,String ruta) throws InterruptedException {
        Thread hilo2=new Thread(new HiloSerializar(ruta,datos),"HiloSerializar xml");
        hilo2.start();
        hilo2.join();
    }



}
