import Controlador.LoginControlador;
import Vista.Login;

public class Main {
    public static void main(String[] args) {
        Login l = new Login();
        new LoginControlador(l);

    }
}
