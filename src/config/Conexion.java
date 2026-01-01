package config;

import javax.swing.*;
import java.sql.*;
public class Conexion {
    public static Connection getConection(){
        String url = "jdbc:mysql://root:PAZTDBarJqZtURXnQWNUGOMFFlgolZCZ@caboose.proxy.rlwy.net:35516/railway";
        String clave = "PAZTDBarJqZtURXnQWNUGOMFFlgolZCZ";
        String usuario = "root";
        Connection con = null;
        try{
            con = DriverManager.getConnection(url,usuario,clave);

            if (con == null){
                throw new SQLException("No se pudo establecer una conexion con la base de datos exitosa");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return con;
    }

}
