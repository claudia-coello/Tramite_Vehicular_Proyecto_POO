package Modelo.Dao;

import Modelo.Clases.Tramite;
import Modelo.Clases.EstadoTramite;

import java.util.List;

public interface TramiteDAO {
    void crearTramite(Tramite tramite);
    Tramite buscarTramitePorId(int id);
    void actualizarEstadoTramite(int id, EstadoTramite estadoTramite);
    List<Tramite> listarTramites();
    List<Tramite> listarTramitesPorEstado(EstadoTramite estado);
}
