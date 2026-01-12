package Controlador;

import Modelo.Service.TramiteService;
import Vista.Formularios.FormularioTramite;

import javax.swing.*;

public class TramiteControlador {

    private FormularioTramite vista;
    private TramiteService service = new TramiteService();

    public TramiteControlador(FormularioTramite vista) {
        this.vista = vista;
        vista.setControlador(this);
    }

    public void buscarTramite() {
        try {
            var t = service.buscar(vista.getIdTramite());
            JOptionPane.showMessageDialog(vista, "Estado: " + t.getEstado());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, e.getMessage());
        }
    }

    public void volver() {
        vista.dispose();
    }

    public void registrarTramite() {
    }

    public void listarTramites() {
    }

    public void actualizarTramite() {

    }
}
