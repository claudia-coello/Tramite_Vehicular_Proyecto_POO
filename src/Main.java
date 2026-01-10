import Controlador.LoginController;
import Modelo.Clases.Rol;
import Modelo.Clases.Usuario;
import Modelo.Dao.UsuarioJDBCDAO;
import Modelo.Repositorio.Conexion;
import Modelo.Service.UsuarioService;
import Vista.Login;

public class Main {
    public static void main(String[] args) {
        Login l = new Login();
        LoginController c = new LoginController(l);

        //UsuarioJDBCDAO us = new UsuarioJDBCDAO();
        //Usuario u = us.buscarPorUsername("ccoello");

        //System.out.println("Clave: "+ u.getPasswordHash());

    }
}
