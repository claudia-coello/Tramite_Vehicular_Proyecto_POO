package dao;
import model.Usuario;
import model.EstadoUsuario;

public interface UsuarioDAO {
    void crearUsuarioJBDC(Usuario usuario);
    Usuario buscarPorUsername(String username);
    void actualizarEstadoUsuario(int id, EstadoUsuario estado);

    void cambiarClave(Usuario usuario);
}
