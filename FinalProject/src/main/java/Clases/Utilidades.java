package Clases;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utilidades {

    public static void realizarCopiaSeguridad(String rutaOriginal, String rutaCarpetaRespaldo) {
        try {
            Path original = Paths.get(rutaOriginal);
            System.out.println("Verificando ruta: " + original.toAbsolutePath());

            if (!Files.exists(original)) {
                System.out.println("Error: La ruta original no existe.");
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = LocalDateTime.now().format(formatter);

            if (Files.isDirectory(original)) {
                // Es un directorio, copiamostodo su contenido
                String nombreDirectorioRespaldo = original.getFileName().toString() + "_" + timestamp;
                Path directorioRespaldo = Paths.get(rutaCarpetaRespaldo, nombreDirectorioRespaldo);

                copiarDirectorio(original, directorioRespaldo);
                System.out.println("Copia de seguridad del directorio realizada con éxito: " + directorioRespaldo);
            } else {
                // Es un archivo individual
                String nombreArchivoRespaldo = original.getFileName().toString().replaceFirst("[.][^.]+$", "")
                        + "_" + timestamp + getFileExtension(original.getFileName().toString());
                Path archivoRespaldo = Paths.get(rutaCarpetaRespaldo, nombreArchivoRespaldo);

                Files.copy(original, archivoRespaldo, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Copia de seguridad del archivo realizada con éxito: " + archivoRespaldo);
            }
        } catch (IOException e) {
            System.out.println("Error al realizar la copia de seguridad: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void copiarDirectorio(Path origen, Path destino) throws IOException {
        Files.walkFileTree(origen, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Path targetDir = destino.resolve(origen.relativize(dir));
                Files.createDirectories(targetDir);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.copy(file, destino.resolve(origen.relativize(file)), StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private static String getFileExtension(String fileName) {
        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // No extension found
        }
        return fileName.substring(lastIndexOf);
    }

    /*public static void realizarCopiaSeguridad(String rutaArchivoOriginal, String rutaCarpetaRespaldo) {
        try {
            Path archivoOriginal = Paths.get(rutaArchivoOriginal);
            if (!Files.exists(archivoOriginal)) {
                System.out.println("El archivo original no existe.");
                return;
            }

            // Formatear la fecha y hora
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = LocalDateTime.now().format(formatter);

            // Crear el nombre del archivo con la fecha y hora
            String nombreArchivoRespaldo = archivoOriginal.getFileName().toString().replace(".xml", "")
                    + "_" + timestamp + ".xml";
            Path archivoRespaldo = Paths.get(rutaCarpetaRespaldo, nombreArchivoRespaldo);

            // Realizar la copia de seguridad
            Files.copy(archivoOriginal, archivoRespaldo, StandardCopyOption.REPLACE_EXISTING);

            // Copiar el contenido del archivo
            try (BufferedReader reader = Files.newBufferedReader(archivoOriginal);
                 BufferedWriter writer = Files.newBufferedWriter(archivoRespaldo)) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    writer.write(linea);
                    writer.newLine();
                }
            }

            System.out.println("Copia de seguridad realizada con éxito: " + archivoRespaldo.getFileName());
        } catch (IOException e) {
            System.out.println("Error al realizar la copia de seguridad: " + e.getMessage());
        }
    }


    public static void realizarCopiaSeguridad(String rutaCarpetaOriginal, String rutaCarpetaRespaldo) {
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
                    // Crear el nombre del archivo de respaldo con fecha y hora
                    String nombreArchivoRespaldo = archivo.getName().replace(".xml", "") + "_" + timestamp + ".xml";
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


    public static void realizarCopiaSeguridad(String rutaArchivoOriginal, String rutaCarpetaRespaldo) {
        try {
            File archivoOriginal = new File(rutaArchivoOriginal);
            if (!archivoOriginal.exists()) {
                System.out.println("El archivo original no existe.");
                return;
            }

            // Formatear la fecha y hora
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = LocalDateTime.now().format(formatter);

            // Crear el nombre del archivo con la fecha y hora
            String nombreArchivoRespaldo = archivoOriginal.getName().replace(".xml", "")
                    + "_" + timestamp + ".xml";
            File archivoRespaldo = new File(rutaCarpetaRespaldo + nombreArchivoRespaldo);

            // Realizar la copia de seguridad
            Files.copy(archivoOriginal.toPath(), archivoRespaldo.toPath());
            System.out.println("Copia de seguridad realizada: " + archivoRespaldo.getName());
        } catch (IOException e) {
            System.out.println("Error al realizar la copia de seguridad: " + e.getMessage());
        }
    }*/





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

    public static Object deserializarXml(String nombre) throws IOException {
        XMLDecoder decodificador = new XMLDecoder(new FileInputStream(nombre));
        Object objeto = decodificador.readObject();
        decodificador.close();
        return objeto;
    }
}
