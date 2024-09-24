package Clases;

public class Administrador extends Usuario{
    public Administrador(String id, String nombre, String apellido, String direccion, String username, String password) {
        super(id, nombre, apellido, direccion, username, password);
    }

    public void generarReporte(){}
    public void gestionarUsuario(){}

    @Override
    public boolean validar() {
        return false;
    }
    
}
