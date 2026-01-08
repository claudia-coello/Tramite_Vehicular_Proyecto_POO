package service;

import dao.UsuarioJDBCDAO;
import model.Rol;
import model.Usuario;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioService {
    private UsuarioJDBCDAO usuarioJDBC = new UsuarioJDBCDAO();

    public boolean login(String usser, String clave){
        Usuario u = usuarioJDBC.buscarPorUsername(usser);
        if (u == null) return false;
        return (BCrypt.checkpw(clave, u.getPasswordHash()));
    }

    public void crearUsuario(String nombre, String usser, String clave, Rol rol){
        if (nombre == null || usser == null || clave == null || rol == null) throw new DatosIncompletosException("Datos del usuario incompletos");

        String passwordHash = BCrypt.hashpw(clave, BCrypt.gensalt(10));
        Usuario u = new Usuario(nombre, usser, passwordHash, rol);
        usuarioJDBC.crearUsuarioJBDC(u);
    }

    public Usuario buscarUsuarioPorUsser(String usser){
        if (usser == null) throw new DatosIncompletosException("Usuario requerido");
        return usuarioJDBC.buscarPorUsername(usser);

    }

    public void cambiarClave(String usser, String claveNueva){
        if (usser == null || claveNueva == null) throw new DatosIncompletosException("Datos del usuario incompletos");

        Usuario usuarioEncontrado = usuarioJDBC.buscarPorUsername(usser);
        if (usuarioEncontrado == null) throw new IllegalArgumentException("Usuario no encontrado");

        usuarioEncontrado.setPasswordHash(BCrypt.hashpw(claveNueva, BCrypt.gensalt(10)));
        usuarioJDBC.cambiarClave(usuarioEncontrado);
    }
}
