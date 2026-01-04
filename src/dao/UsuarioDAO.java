package dao;
import javax.swing.*;
import java.sql.*;
import model.Usuario;
import model.EstadoUsuario;
import util.Conexion;

public interface UsuarioDAO {
    void crearUsuario(Usuario usuario);
    Usuario buscarPorUsername(String username);
    void actualizarEstadoUsuario(int id, EstadoUsuario estado);
}
