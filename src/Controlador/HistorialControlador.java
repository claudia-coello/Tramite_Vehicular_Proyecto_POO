package Controlador;

import Modelo.Service.TramiteService;
import Vista.Formularios.FormularioHistorial;

public class HistorialControlador {

    private FormularioHistorial vista;
    private TramiteService tramiteService;

    public HistorialControlador(FormularioHistorial vista) {
        this.vista = vista;
        this.tramiteService = new TramiteService();
        this.vista.setControlador(this);
        cargarHistorial();
    }

    private void cargarHistorial() {
        vista.mostrarTramites(tramiteService.listarTramites());
    }

    public void volver() {
    }
}
