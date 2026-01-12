package Modelo.Dao;

import Modelo.Clases.Tramite;
import Modelo.Enums.EstadoTramite;

import java.util.List;

public interface TramiteDAO {
    void crearTramite(Tramite t);
    Tramite buscarPorId(int id);
    void actualizarEstado(int id, EstadoTramite estado);
    List<Tramite> listar();
}
