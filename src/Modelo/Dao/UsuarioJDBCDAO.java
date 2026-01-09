package Modelo.Dao;

import Modelo.Clases.EstadoUsuario;
import Modelo.Clases.Rol;
import Modelo.Repositorio.Conexion;

import javax.swing.*;
import java.sql.*;
import Modelo.Clases.Usuario;

public class UsuarioJDBCDAO implements UsuarioDAO{
    @Override
    public void crearUsuarioJBDC(Usuario usuario){
        String crear = "INSERT INTO usuarios(nombre, username, password_hash, rol, estado) VALUES (?, ?, ?, ?, ?)";

        try(Connection con = Conexion.getConection(); PreparedStatement pstm = con.prepareStatement(crear) ){
            pstm.setString(1, usuario.getNombre());
            pstm.setString(2, usuario.getUsername());
            pstm.setString(3, usuario.getPasswordHash());
            pstm.setString(4, usuario.getRol().name());
            pstm.setString(5, usuario.getEstado().name());

            pstm.execute();
            JOptionPane.showMessageDialog(null, "Usuario creado con exito");

        }catch(SQLException e){
            throw new RuntimeException("Error al crear usuario", e);
        }
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        String buscar = "SELECT * FROM usuarios WHERE username = ?";
        try(Connection conn = Conexion.getConection(); PreparedStatement pstm = conn.prepareStatement(buscar)){
            pstm.setString(1, username);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()){
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id"));
                u.setNombre("nombre");
                u.setUsername("username");
                u.setPasswordHash("password_hash");
                u.setRol(Rol.valueOf(rs.getString("rol")));
                u.setEstado(EstadoUsuario.valueOf(rs.getString("estado")));
            }


        }catch (SQLException e){
            throw new RuntimeException("Error al buscar usuario por username", e);
        }

        return null;
    }

    @Override
    public void actualizarEstadoUsuario(int id, EstadoUsuario estado) {
        String actualizar = "UPDATE usuarios SET estado = ? WHERE id = ?";
        try(Connection conn = Conexion.getConection(); PreparedStatement pstm = conn.prepareStatement(actualizar)) {
            pstm.setString(1, estado.name());
            pstm.setInt(2, id);

            pstm.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Error al actualizar estado de usuario", e);
        }
    }

    @Override
    public void cambiarClave(Usuario usuario){
        String actualizar = "UPDATE usuarios SET password_hash = ? WHERE username = ?";
        try(Connection conn = Conexion.getConection(); PreparedStatement pstm = conn.prepareStatement(actualizar)) {
            pstm.setString(1, usuario.getPasswordHash());
            pstm.setString(2, usuario.getUsername());

            pstm.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException("Error al cambiar clave", e);
        }
    }
}
