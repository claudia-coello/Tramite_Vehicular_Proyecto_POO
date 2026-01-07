package dao;

import model.Tramite;
import model.EstadoTramite;

public interface TramiteDao {
    void crearTramite(Tramite tramite);
    Tramite buscarTramitePorId(int id);
    void actualizarEstadoTramite(int id, EstadoTramite estadoTramite);
}
