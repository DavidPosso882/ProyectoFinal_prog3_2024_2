package Clases;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.logging.Logger;
import java.util.logging.Level;

public class SistemaMarketPlace {
    private static final Logger LOGGER = Logger.getLogger(SistemaMarketPlace.class.getName());
    private static SistemaMarketPlace instance;
    private List<Vendedor> vendedorList;
    private List<Administrador> administradorList;
    private Persistencia persistencia;

    private SistemaMarketPlace() {
        this.persistencia = new PersistenciaXML();
        this.persistencia.cargarDatos();
        try {
            ((PersistenciaXML) this.persistencia).esperarCargaDatos();
            this.vendedorList = (List<Vendedor>) ((PersistenciaXML) this.persistencia).getDatos();
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "Error al esperar la carga de datos", e);
        }
        if (this.vendedorList == null) {
            this.vendedorList = new ArrayList<>();
        }
        this.administradorList = new ArrayList<>();
    }

    public static synchronized SistemaMarketPlace getInstance() {
        if (instance == null) {
            instance = new SistemaMarketPlace();
        }
        return instance;
    }

    public void registrarVendedor(Vendedor vendedor) throws ExcepcionesPersonalizadas.VendedorDuplicadoException {
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
        persistencia.setDatos(vendedorList);
        persistencia.guardarDatos();
    }

    public List<Administrador> getAdministradorList() {
        return administradorList;
    }

    public void setAdministradorList(List<Administrador> administradorList) {
        this.administradorList = administradorList;
    }

    public Persistencia getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(Persistencia persistencia) {
        this.persistencia = persistencia;
    }
}
