package Modelo.Service;

import Modelo.Dao.ExamenJDBCDAO;
import Modelo.Dao.TramiteJBDCDAO;
import Excepciones.DatosIncompletosException;
import Modelo.Clases.EstadoTramite;
import Modelo.Clases.Examen;
import Modelo.Clases.Solicitante;
import Modelo.Clases.Tramite;

import java.time.LocalDate;
import java.util.List;

/*
Responsabilidades

Crear trámite completo

Validar reglas del trámite

Definir estado inicial

Coordinar inserciones
* */
public class TramiteService {

    public void crearTramite(Solicitante solicitante){
        TramiteJBDCDAO tramiteDAO = new TramiteJBDCDAO();

        if (solicitante == null) throw new IllegalArgumentException("No existe solicitante asociado al tramite");

        Tramite t = new Tramite(solicitante);
        t.setEstado(EstadoTramite.PENDIENTE);
        t.setFechaSolicitud(LocalDate.now());

        tramiteDAO.crearTramite(t);
    }

    public Tramite buscarTramitePorId(int id){
        TramiteJBDCDAO tramiteDAO = new TramiteJBDCDAO();
        validarId(id);
        Tramite t = tramiteDAO.buscarTramitePorId(id);

        if (t == null) throw new IllegalArgumentException("Tramite no encontrado");
        return t;
    }

    public void actualizarEstadoTramite(int tramiteId, EstadoTramite estado){
        TramiteJBDCDAO tramiteDAO = new TramiteJBDCDAO();
        validarId(tramiteId);
        if (estado == null) throw  new DatosIncompletosException("Estado no porporcionado");
        buscarTramitePorId(tramiteId);//solo se necesita pasar la excepcion no lo que retorna

        tramiteDAO.actualizarEstadoTramite(tramiteId, estado);
    }

    public void validarTramite(int tramiteId){
        ExamenJDBCDAO examenDAO = new ExamenJDBCDAO();

        validarId(tramiteId);
        Examen e = examenDAO.buscarExamenPorIdTramite(tramiteId);

        if (e == null ) throw  new DatosIncompletosException("Tramite no encontrado");

    }

    public List<Tramite> listarTramites(){
        TramiteJBDCDAO tramiteDAO = new TramiteJBDCDAO();
        List<Tramite> listaTramites = tramiteDAO.listarTramites();

        if (listaTramites.isEmpty()) throw  new IllegalStateException("No hay tramites registrados");

        return listaTramites;
    }

    public List<Tramite> listarTramitesPorEstado(EstadoTramite estado){
        TramiteJBDCDAO tramiteDAO = new TramiteJBDCDAO();

        if (estado == null) throw new DatosIncompletosException("Estado nulo");

        List<Tramite> listaTramites = tramiteDAO.listarTramitesPorEstado(estado);
        if (listaTramites.isEmpty()) throw new DatosIncompletosException("Sin tramites registrados por estado: " + estado);
        return listaTramites;
    }

    public void validarId(int id){
        if (id <= 0) throw  new DatosIncompletosException("Id no valido");
    }

}
