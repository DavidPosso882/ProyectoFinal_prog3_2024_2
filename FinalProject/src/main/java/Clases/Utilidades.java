package Clases;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;


public class Utilidades {

    public static String rutaVendedores;
    public static String rutavendedoresB;
    public static String rutaAOrigen;
    public static String rutaARespaldo;
    public static String rutaLogVendedores;


    public static void realizarCopiaSeguridad(String rutaCarpetaOriginal, String rutaCarpetaRespaldo) throws IOException {
        try {
            // Crear un objeto File para la carpeta original
            File carpetaOriginal = new File(rutaCarpetaOriginal);

            // Verificar que la carpeta exista y sea un directorio
            if (!carpetaOriginal.exists() || !carpetaOriginal.isDirectory()) {
                System.out.println("La carpeta original no existe o no es un directorio.");
                return;
            }

            // Verificar o crear la carpeta de respaldo
            File carpetaRespaldo = new File(rutaCarpetaRespaldo);
            if (!carpetaRespaldo.exists()) {
                carpetaRespaldo.mkdirs(); // Crear la carpeta si no existe
            }

            // Obtener los archivos dentro de la carpeta original
            File[] archivos = carpetaOriginal.listFiles();
            if (archivos == null || archivos.length == 0) {
                System.out.println("La carpeta no contiene archivos para respaldar.");
                return;
            }

            // Formatear la fecha y hora
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = LocalDateTime.now().format(formatter);

            // Recorrer los archivos en la carpeta original
            for (File archivo : archivos) {
                if (archivo.isFile()) {  // Asegurarse de que sea un archivo y no un subdirectorio
                    // Obtener la extensión del archivo original
                    String extension = archivo.getName().substring(archivo.getName().lastIndexOf("."));
                    // Crear el nombre del archivo de respaldo con fecha y hora
                    String nombreArchivoRespaldo = archivo.getName().replace(extension, "") + "_" + timestamp + extension;
                    File archivoRespaldo = new File(rutaCarpetaRespaldo + File.separator + nombreArchivoRespaldo);

                    // Copiar el archivo al directorio de respaldo
                    Files.copy(archivo.toPath(), archivoRespaldo.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Copia de seguridad realizada: " + archivoRespaldo.getName());
                }
            }

        } catch (IOException e) {
            System.out.println("Error al realizar la copia de seguridad: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    public static void cargarRutas() {
        Properties propiedades = new Properties();

        try {
            // Usar ClassLoader para cargar el archivo desde el classpath
            InputStream input = Utilidades.class.getClassLoader().getResourceAsStream("config.properties");

            if (input == null) {
                throw new FileNotFoundException("Archivo config.properties no encontrado en el classpath");
            }

            propiedades.load(input);

            // Cargar propiedades
            rutaVendedores = propiedades.getProperty("ruta.vendedores");
            rutavendedoresB = propiedades.getProperty("ruta.vendedoresBinario");
            rutaAOrigen = propiedades.getProperty("ruta.archivoOriginal");
            rutaARespaldo = propiedades.getProperty("ruta.archivoRespaldo");
            rutaLogVendedores= propiedades.getProperty("ruta.logVendedores");


            input.close(); // Cerrar el InputStream después de cargar las propiedades

        } catch (FileNotFoundException e) {
            System.err.println("Error: No se encontró el archivo config.properties - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al cargar las propiedades desde config.properties - " + e.getMessage());
        }
    }

    public static void serializarBinario(String nombre, Object objeto) throws IOException {
        ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(nombre));
        salida.writeObject(objeto);
        salida.close();
    }

    public static Object deserializarBinario(String nombre) throws IOException, ClassNotFoundException {
        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(nombre));
        Object objeto = entrada.readObject();
        entrada.close();
        return objeto;
    }

    public static void serializarXml(String nombre, Object objeto) throws IOException {
        XMLEncoder codificador = new XMLEncoder(new FileOutputStream(nombre));
        codificador.writeObject(objeto);
        codificador.close();
    }

    /*public static Object deserializarXml(String nombre) throws IOException {
        XMLDecoder decodificador = new XMLDecoder(new FileInputStream(nombre));
        Object objeto = decodificador.readObject();
        decodificador.close();
        return objeto;
    }*/

    public static <T> ArrayList<T> deserializarXml(String nombre) throws IOException {
        ArrayList<T> lista = new ArrayList<>();

        try (XMLDecoder decodificador = new XMLDecoder(new FileInputStream(nombre))) {
            lista = (ArrayList<T>) decodificador.readObject();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error en la estructura del archivo XML: " + e.getMessage());
        }

        return lista;
    }


}
