package Modelo.Dao;

import Modelo.Clases.Solicitante;
import Modelo.Clases.TipoLicencia;
import java.util.List;

public interface SolicitanteDAO {
    void crearSolicitante(Solicitante solicitante);
    Solicitante buscarPorCedula(String cedula);
    Solicitante buscarPorId(int id);
    List<Solicitante> listarSolicitantes();
    void actualizarSolicitante(Solicitante solicitante);
    List<Solicitante> buscarPorNombre(String nombre);
    List<Solicitante> buscarPorTipoLicencia(TipoLicencia tipo);
}