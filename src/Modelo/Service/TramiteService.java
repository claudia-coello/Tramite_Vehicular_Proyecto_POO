package Modelo.Service;

import Modelo.Clases.Tramite;
import Modelo.Dao.TramiteJDBCDAO;
import Modelo.Enums.EstadoTramite;

import java.time.LocalDate;

public class TramiteService {

    private TramiteJDBCDAO dao = new TramiteJDBCDAO();

    public void crearTramite() {
        Tramite t = new Tramite();
        t.setEstado(EstadoTramite.PENDIENTE);
        t.setFechaSolicitud(LocalDate.now());

        dao.crearTramite(t);
    }

    public Tramite buscar(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID inválido");
        Tramite t = dao.buscarPorId(id);
        if (t == null) throw new IllegalArgumentException("Trámite no existe");
        return t;
    }
}
