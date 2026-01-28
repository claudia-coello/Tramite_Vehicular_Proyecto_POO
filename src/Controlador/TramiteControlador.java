package Controlador;

import Modelo.Service.TramiteService;
import Vista.Formularios.CrearTramiteFormulario;

public class TramiteControlador {
    private CrearTramiteFormulario ct;
    private TramiteService ts;

    public TramiteControlador(CrearTramiteFormulario ct){
        this.ct = ct;
        this.ts = new TramiteService();

    }

    public void registrarTramite() {
    }

    public void listarTramites() {
    }

    public void buscarTramite() {
    }

    public void actualizarTramite() {
    }
}
