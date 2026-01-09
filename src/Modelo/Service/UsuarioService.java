package Modelo.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import Modelo.Dao.UsuarioJDBCDAO;
import Excepciones.DatosIncompletosException;
import Modelo.Clases.Rol;
import Modelo.Clases.Usuario;

public class UsuarioService {
    private UsuarioJDBCDAO usuarioJDBC = new UsuarioJDBCDAO();

    public boolean login(String usser, String clave){
        Usuario u = usuarioJDBC.buscarPorUsername(usser);
        if (u == null) return false;
        BCrypt.Result result = BCrypt.verifyer().verify(clave.toCharArray(), u.getPasswordHash());
        return result.verified;
    }

    public void crearUsuario(String nombre, String usser, String clave, Rol rol){
        if (nombre == null || usser == null || clave == null || rol == null) throw new DatosIncompletosException("Datos del usuario incompletos");

        String passwordHash = BCrypt.withDefaults().hashToString(10, clave.toCharArray());
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

        usuarioEncontrado.setPasswordHash(BCrypt.withDefaults().hashToString(10, claveNueva.toCharArray()));
        usuarioJDBC.cambiarClave(usuarioEncontrado);
    }
}
