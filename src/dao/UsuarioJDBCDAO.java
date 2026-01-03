package dao;

import util.Conexion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioJDBCDAO implements UsuarioDAO{
    public void crearUsuario(String nombre, String clave){
        String crear = "INSERT INTO usuarios(nombre_usuario, clave_usuario) VALUES (?, ?)";

        try(Connection con = Conexion.getConection(); PreparedStatement pstm = con.prepareStatement(crear) ){
            pstm.setString(1, nombre);
            pstm.setString(2, clave);

            pstm.execute();
            JOptionPane.showMessageDialog(null, "Usuario creado con exito");

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
