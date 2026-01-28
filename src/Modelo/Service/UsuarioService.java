package Modelo.Service;

import Excepciones.DatosInexistentesException;
import Modelo.Dao.UsuarioJDBCDAO;
import Excepciones.DatosIncompletosException;
import Modelo.Clases.Rol;
import Modelo.Clases.Usuario;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioService {
    private UsuarioJDBCDAO usuarioJDBC = new UsuarioJDBCDAO();

    public Usuario login(String usser, String clave){
        Usuario u = usuarioJDBC.buscarPorUsername(usser);
        if (u == null) throw  new DatosInexistentesException("Usuario no encontrado");

        boolean verificado = BCrypt.checkpw(clave, u.getPasswordHash());
        if (!verificado){
            throw new DatosInexistentesException("Clave incorrecta");
        }
        return u;
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

        String nuevoHash = BCrypt.hashpw(claveNueva, BCrypt.gensalt(10));
        usuarioEncontrado.setPasswordHash(nuevoHash);
        usuarioJDBC.cambiarClave(usuarioEncontrado);
    }
}
