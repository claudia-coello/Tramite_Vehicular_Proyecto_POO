import dao.ExamenJDBCDAO;
import dao.TramiteJBDCDAO;
import dao.UsuarioJDBCDAO;
import model.*;
import util.Conexion;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        Tramite t = new Tramite();
        t.setIdTramite(1);
        Examen e = new Examen(t, 10.5, 14.8);

        ExamenJDBCDAO ex = new ExamenJDBCDAO();

        ex.buscarExamenPorIdTramite(1);

    }
}
