package Clases;

import com.example.finalproject.Main;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;



public class SistemaMarketPlace {
    static final Logger LOGGER = Logger.getLogger(SistemaMarketPlace.class.getName());
    private static SistemaMarketPlace instance;
    private List<Vendedor> vendedorList;
    private List<Administrador> administradorList;
    private Persistencia persistencia;

    private SistemaMarketPlace(List<Vendedor> vendedorList, List<Administrador> administradorList, Persistencia persistencia) {
        this.vendedorList = vendedorList;
        this.administradorList = administradorList;
        this.persistencia = persistencia;
    }

   /* public static synchronized SistemaMarketPlace getInstance() {
        if (instance == null) {
            instance = new SistemaMarketPlace(new ArrayList<>(), new ArrayList<>(), new PersistenciaXML());
        }
        return instance;
    }

   /* public void registrarVendedor(Vendedor vendedor) throws ExcepcionesPersonalizadas.VendedorDuplicadoException, IOException, InterruptedException {
        //Thread hilo2=new Thread(new HiloCarga(Utilidades.rutaVendedores, lista),"HiloCarga xml");
       // hilo2.start();
        //hilo2.join();
        //System.out.println(lista.get(0).toString());
        lista.add(vendedor);
        Thread hilo1=new Thread(new HiloSerializar(Utilidades.rutaVendedores,lista),"Hilo xml");
        hilo1.start();
        hilo1.join();
        /*Thread hilo3=new Thread(new HiloSerializarBinario(Utilidades.rutavendedoresB,lista),"Hilo persistencia .dat");
        hilo3.start();
        hilo3.join();
        Thread hilo2=new Thread(new HiloCarga(Utilidades.rutaVendedores, lista),"HiloCarga xml");
         hilo2.start();
        hilo2.join();
        System.out.println(lista);
        //ArrayList<Vendedor>listaV=(ArrayList<Vendedor>) lista;

        if (vendedorList.stream().anyMatch(v -> v.getId().equals(vendedor.getId()))) {
            throw new ExcepcionesPersonalizadas.VendedorDuplicadoException(vendedor.getId());
        }

        vendedorList.add(vendedor);
        persistencia.setDatos(vendedorList);
        persistencia.guardarDatos();
        LOGGER.log(Level.INFO, "Vendedor registrado: " + vendedor.getNombre());
    }

    public Usuario autenticarUsuario(String username, String password) throws ExcepcionesPersonalizadas.AutenticacionFallidaException {
        for (Vendedor vendedor : vendedorList) {
            if (vendedor.getUsername().equals(username) && vendedor.getPassword().equals(password)) {
                return vendedor;
            }
        }
        for (Administrador admin : administradorList) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        throw new ExcepcionesPersonalizadas.AutenticacionFallidaException();
    }

    public List<Vendedor> getVendedorList(String criterio) {
        // Falta Implementar lógica de búsqueda (revisar después)
        return vendedorList;
    }

    public List<Vendedor> sugerirVendedores(Vendedor vendedor) {
        // Implementar lógica de sugerencia de vendedores (este también toca revisarlo después)
        return new ArrayList<>();
    }

    public Map<String, Object> generarEstadisticas() {
        Map<String, Object> estadisticas = new HashMap<>();
        // Falta implementar lógica para generar estadísticas
        return estadisticas;
    }

    public void registrarAccion(String tipoUsuario, String accion, String interfaz) {
        LOGGER.log(Level.INFO, "Acción registrada - Usuario: " + tipoUsuario + ", Acción: " + accion + ", Interfaz: " + interfaz);
        // Implementar lógica para registrar la acción en un log o base de datos
    }

    public List<Vendedor> getVendedorList() {
        return vendedorList;
    }

    public void setVendedorList(List<Vendedor> vendedorList) {
        this.vendedorList = vendedorList;
    }

    public List<Administrador> getAdministradorList() {
        return administradorList;
    }

    public void setAdministradorList(List<Administrador> administradorList) {
        this.administradorList = administradorList;
    }

   /*public static void configurarLogger(String rutaDirectorio,String mensaje, Throwable excepcion) {
        try {
            FileHandler fileHandler = new FileHandler(rutaDirectorio, true);
            fileHandler.setLevel(Level.INFO); // Nivel de log para el archivo
            fileHandler.setFormatter(new SimpleFormatter()); // Formato simple

            // Asocia el FileHandler al LOGGER
            LOGGER.addHandler(fileHandler);
            LOGGER.setUseParentHandlers(false); // Evita que se loguee en la consola

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "No se pudo configurar el FileHandler en la ruta especificada.", e);
        }
       // Solo el nombre de la excepción
        LOGGER.log(Level.INFO,mensaje,excepcion);
    }*/

    public static void configurarLogger(String rutaDirectorio, String mensaje, Throwable excepcion) {
        try {
            FileHandler fileHandler = new FileHandler(rutaDirectorio, true);
            fileHandler.setLevel(Level.SEVERE); // Cambiado a SEVERE para excepciones
            fileHandler.setFormatter(new SimpleFormatter());

            LOGGER.addHandler(fileHandler);
            LOGGER.setUseParentHandlers(false);

            // Obtener el nombre completo de la excepción
            String nombreExcepcion = excepcion.getClass().getName();

            // Obtener el mensaje de la excepción
            String mensajeExcepcion = excepcion.getMessage();

            // Crear el mensaje en el formato deseado
            String mensajeCompleto = String.format("%s: %s", nombreExcepcion, mensajeExcepcion);

            // Registrar el mensaje sin el stack trace
            LOGGER.log(Level.SEVERE, mensaje +" - " + mensajeCompleto);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "No se pudo configurar el FileHandler en la ruta especificada: " + e.getMessage());
        }
    }

    //Funciones

    // Función para autenticar un vendedor

    public static Vendedor autenticarVendedor(String username, String password) {
        for (Vendedor vendedor : Main.listaVendedores) {
            if (vendedor.getUsername().equals(username) && vendedor.getPassword().equals(password)) {
                LOGGER.info("Vendedor autenticado correctamente: " + username);
                return vendedor;
            }
        }
        LOGGER.warning("Error de autenticación para el usuario: " + username);
        return null;
    }

    public static String crearComentario(String contenido, Vendedor autor,Publicacion publicacion) {
        // Generar un ID único para el comentario
        String id = UUID.randomUUID().toString();

        // Obtener la fecha y hora actual
        LocalDateTime fechaActual = LocalDateTime.now();

        // Crear y retornar el nuevo comentario
        Comentario nuevoComentario = new Comentario(id, contenido, fechaActual, autor);

        System.out.println("Nuevo comentario creado por " + autor.getNombre() + ": " + contenido);
        publicacion.agregarComentarios(nuevoComentario);
        return "Nuevo comentario creado por " + autor.getNombre() + ": " + contenido;
    }



    public Persistencia getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(Persistencia persistencia) {
        this.persistencia = persistencia;
    }
}
