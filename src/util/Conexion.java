package util;
import javax.swing.*;
import java.sql.*;
public class Conexion {
    private static String URL = "jdbc:postgresql://db.ivexkitjvhhoalkdkbgw.supabase.co:5432/postgres";
    private static String CLAVE = "Postgre_123/";
    private static String USUARIO = "postgres";

    public static Connection getConection(){
        Connection con = null;
        try{
            con = DriverManager.getConnection(URL, USUARIO, CLAVE);

            if (con == null){
                throw new ConexionFallidaException("No se pudo establecer una conexion exitosa con la base de datos");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }catch(ConexionFallidaException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return con;
    }

}
