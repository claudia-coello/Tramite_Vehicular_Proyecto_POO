package Modelo.Service;

import Excepciones.DatosIncompletosException;
import Excepciones.DatosInexistentesException;
import Modelo.Clases.Usuario;
import Modelo.Dao.UsuarioJDBCDAO;
import Modelo.Enums.EstadoUsuario;
import Modelo.Enums.Rol;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    private UsuarioJDBCDAO dao = new UsuarioJDBCDAO();

    public void crearUsuario(String nombre, String username, String clave, Rol rol) {
        if (nombre.isEmpty() || username.isEmpty() || clave.isEmpty())
            throw new IllegalArgumentException("Datos incompletos");

        String hash = BCrypt.hashpw(clave, BCrypt.gensalt());
        Usuario u = new Usuario(nombre, username, hash, rol);
        u.setEstado(EstadoUsuario.ACTIVO);

        dao.crearUsuario(u);
    }

    public void editarUsuario(String username, String nombre, Rol rol, EstadoUsuario estado) {
        Usuario u = dao.buscarPorUsername(username);
        if (u == null) throw new IllegalArgumentException("Usuario no existe");

        u.setNombre(nombre);
        u.setRol(rol);
        u.setEstado(estado);

        dao.editarUsuario(u);
    }

    public void cambiarClave(String username, String nuevaClave) {
        Usuario u = dao.buscarPorUsername(username);
        if (u == null) throw new IllegalArgumentException("Usuario no existe");

        u.setPasswordHash(BCrypt.hashpw(nuevaClave, BCrypt.gensalt()));
        dao.cambiarClave(u);
    }

    public Usuario login(String username, String clave) {
        if (username == null || clave == null)
            throw new DatosIncompletosException("Datos incompletos");

        Usuario u = dao.buscarPorUsername(username);

        if (u == null)
            throw new DatosInexistentesException("Usuario no encontrado");

        if (!BCrypt.checkpw(clave, u.getPasswordHash()))
            throw new DatosInexistentesException("Clave incorrecta");

        if (u.getEstado() != EstadoUsuario.ACTIVO)
            throw new IllegalStateException("Usuario inactivo");

        return u;
    }
    public List<Usuario> listarUsuarios(){
        List<Usuario> u = new ArrayList<>();
        u = dao.listarUsuarios();
        if (u.isEmpty()) throw new DatosIncompletosException("No existen usuarios registrados");
        return u;
    }
}
