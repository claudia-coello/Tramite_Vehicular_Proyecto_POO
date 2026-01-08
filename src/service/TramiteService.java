package service;

import dao.ExamenDAO;
import dao.ExamenJDBCDAO;
import dao.TramiteJBDCDAO;
import model.EstadoTramite;
import model.Examen;
import model.Solicitante;
import model.Tramite;

import java.time.LocalDate;

/*
Responsabilidades

Crear trámite completo

Validar reglas del trámite

Definir estado inicial

Coordinar inserciones
* */
public class TramiteService {
    private TramiteJBDCDAO tramiteDAO = new TramiteJBDCDAO();

    public void crearTramite(Solicitante solicitante){
        if (solicitante == null) throw new IllegalArgumentException("Tramite no puede estar vacio");

        Tramite t = new Tramite(solicitante);
        t.setEstado(EstadoTramite.PENDIENTE);
        t.setFechaSolicitud(LocalDate.now());

        tramiteDAO.crearTramite(t);
    }

    public Tramite buscarTramitePorId(int id){
        validarId(id);
        Tramite t = tramiteDAO.buscarTramitePorId(id);

        if (t == null) throw new IllegalArgumentException("Tramite no encontrado");
        return t;
    }

    public void actualizarEstadoTramite(int tramiteId, EstadoTramite estado){
        validarId(tramiteId);
        if (estado == null) throw  new DatosIncompletosException("Estado no porporcionado");
        buscarTramitePorId(tramiteId);//solo se necesita pasar la excepcion no lo que retorna

        tramiteDAO.actualizarEstadoTramite(tramiteId, estado);
    }

    public void rechazarTramite(int tramiteId){
        ExamenJDBCDAO examenDAO = new ExamenJDBCDAO();
        validarId(tramiteId);
        Examen e = examenDAO.buscarExamenPorIdTramite(tramiteId);

        if (e == null ) throw  new DatosIncompletosException("Tramite no encontrado");
        if (e.getNotaPractica() < 7 && e.getNotaTeorica() < 7) e.setResultado(false);

    }

    public void aprobarTramite(int tramiteId){
        validarId(tramiteId);
    }

    public void listarTramites(){}

    public void listarTramitesPorEstado(EstadoTramite estado){}

    public void validarId(int id){
        if (id <= 0) throw  new DatosIncompletosException("Id no valido");
    }

}
