package dao;

import model.EstadoUsuario;
import model.Rol;
import model.EstadoUsuario;
import util.Conexion;

import javax.swing.*;
import java.sql.*;
import model.Usuario;

import util.Conexion;

public class UsuarioJDBCDAO implements UsuarioDAO{
    @Override
    public void crearUsuario(Usuario usuario){
        String crear = "INSERT INTO usuarios(nombre, ussername, password_hash, rol, estado) VALUES (?, ?, ?, ?, ?)";

        try(Connection con = Conexion.getConection(); PreparedStatement pstm = con.prepareStatement(crear) ){
            pstm.setString(1, usuario.getNombre());
            pstm.setString(2, usuario.getUsername());
            pstm.setString(3, usuario.getPasswordHash());
            pstm.setString(4, usuario.getRol().name());
            pstm.setString(5, usuario.getEstado().name());

            pstm.execute();
            JOptionPane.showMessageDialog(null, "Usuario creado con exito");

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Usuario buscarPorUsername(String username) {
        String buscar = "SELECT * FROM usuario WHERE username = ?";
        try(Connection conn = Conexion.getConection(); PreparedStatement pstm = conn.prepareStatement(buscar)){
            pstm.setString(1, username);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()){
                Usuario u = new Usuario();
                u.setUsername("id");
                u.setNombre("nombre");
                u.setUsername("username");
                u.setPasswordHash("password_hash");
                u.setRol(Rol.valueOf(rs.getString("rol")));
                u.setEstado(EstadoUsuario.valueOf(rs.getString("estado")));
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error", e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }

    @Override
    public void actualizarEstadoUsuario(int id, EstadoUsuario estado) {
        String actualizar = "UPDATE usuario SET estado = ? WHERE id = ?";
        try(Connection conn = Conexion.getConection(); PreparedStatement pstm = conn.prepareStatement(actualizar)) {
            pstm.setString(1, estado.name());
            pstm.setInt(2, id);

            pstm.executeUpdate();

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error", e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }
}
