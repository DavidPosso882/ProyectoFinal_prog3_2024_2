package Clases;

import java.util.List;
import java.util.Map;

public class SistemaMarketPlace {
    private List<Vendedor> vendedorList;
    private List<Administrador> administradorList;
    private Persistencia persistencia;

    public void registrarVendedor(Vendedor vendedor) {}
    public Usuario autenticarUsuario(String username, String password) {
        return null;
    }
    public List<Vendedor> getVendedorList(String criterio) {
        return null;
    }
    public List<Vendedor> sugerirVendedores(Vendedor vendedor) {
        return null;
    }
    public Map<String, Object> generarEstadisticas(){
        return null;
    }
    public void registrarAccion(String tipoUsuario, String accion, String interfaz){}

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

    public Persistencia getPersistencia() {
        return persistencia;
    }

    public void setPersistencia(Persistencia persistencia) {
        this.persistencia = persistencia;
    }

    public SistemaMarketPlace(List<Vendedor> vendedorList, List<Administrador> administradorList, Persistencia persistencia) {
        this.vendedorList = vendedorList;
        this.administradorList = administradorList;
        this.persistencia = persistencia;


    }
}
