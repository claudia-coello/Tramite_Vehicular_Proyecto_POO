package Modelo.Dao;

import Modelo.Clases.Licencia;
import Modelo.Clases.TipoLicencia;
import java.time.LocalDate;
import java.util.List;

public interface LicenciaDAO {
    void crearLicencia(Licencia licencia);
    Licencia buscarPorId(int id);
    List<Licencia> buscarPorCedulaSolicitante(String cedula);
    List<Licencia> buscarPorTramiteId(int tramiteId);
    List<Licencia> listarLicencias();
    List<Licencia> buscarPorFechaEmision(LocalDate fecha);
    List<Licencia> buscarPorFechaVencimiento(LocalDate fecha);
    boolean tieneLicenciaVigente(String cedula, TipoLicencia tipo);
    List<Licencia> buscarLicenciasVigentes();
    List<Licencia> buscarLicenciasVencidas();
}