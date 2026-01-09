package Modelo.Repositorio;
import Excepciones.ConexionFallidaException;

import javax.swing.*;
import java.sql.*;
public class Conexion {

    public static Connection getConection(){
        String USUARIO = "postgres";
        String CLAVE = "Postgre_123/";
        String URL = "jdbc:postgresql://db.ivexkitjvhhoalkdkbgw.supabase.co:5432/postgres";
        Connection con = null;
        try{
            con = DriverManager.getConnection(URL, USUARIO, CLAVE);

            if (con == null){
                throw new ConexionFallidaException("No se pudo establecer una conexion exitosa con la base de datos");
            }
        }catch(SQLException | ConexionFallidaException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return con;
    }

}
