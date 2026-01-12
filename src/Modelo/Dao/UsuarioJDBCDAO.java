package Modelo.Dao;

import Excepciones.DatosIncompletosException;
import Excepciones.DatosInexistentesException;
import Modelo.Clases.Usuario;
import Modelo.Enums.EstadoUsuario;
import Modelo.Enums.Rol;
import Modelo.Repositorio.Conexion;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioJDBCDAO implements UsuarioDAO {

    @Override
    public void crearUsuario(Usuario u) {
        String sql = "INSERT INTO usuarios(nombre, username, password_hash, rol, estado) VALUES (?,?,?,?,?)";

        try (Connection c = Conexion.getConection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getUsername());
            ps.setString(3, u.getPasswordHash());
            ps.setString(4, u.getRol().name());
            ps.setString(5, u.getEstado().name());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        String sql = "SELECT * FROM usuarios WHERE username=?";
        try (Connection c = Conexion.getConection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setUsername(rs.getString("username"));
                u.setPasswordHash(rs.getString("password_hash"));
                u.setRol(Rol.valueOf(rs.getString("rol")));
                u.setEstado(EstadoUsuario.valueOf(rs.getString("estado")));
                return u;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editarUsuario(Usuario u) {
        String sql = "UPDATE usuarios SET nombre=?, rol=?, estado=? WHERE username=?";

        try (Connection c = Conexion.getConection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getRol().name());
            ps.setString(3, u.getEstado().name());
            ps.setString(4, u.getUsername());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cambiarClave(Usuario u) {
        String sql = "UPDATE usuarios SET password_hash=? WHERE username=?";

        try (Connection c = Conexion.getConection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, u.getPasswordHash());
            ps.setString(2, u.getUsername());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Usuario> listarUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection c = Conexion.getConection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setUsername(rs.getString("username"));
                u.setRol(Rol.valueOf(rs.getString("rol")));
                u.setEstado(EstadoUsuario.valueOf(rs.getString("estado")));
                lista.add(u);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }


}
