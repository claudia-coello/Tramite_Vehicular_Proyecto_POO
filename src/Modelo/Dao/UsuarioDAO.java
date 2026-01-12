package Modelo.Dao;

import Modelo.Clases.Usuario;

import java.util.List;

public interface UsuarioDAO {
    void crearUsuario(Usuario usuario);
    Usuario buscarPorUsername(String username);
    void editarUsuario(Usuario usuario);
    void cambiarClave(Usuario usuario);
    List<Usuario> listarUsuarios();
}
