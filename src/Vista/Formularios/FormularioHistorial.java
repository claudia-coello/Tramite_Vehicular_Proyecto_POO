package Vista.Formularios;

import Controlador.HistorialControlador;
import Modelo.Clases.Tramite;
import Vista.BaseVistas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FormularioHistorial extends BaseVistas {

    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JButton btnVolver;

    private HistorialControlador controlador;

    public FormularioHistorial() {

        // Columnas de la tabla
        modeloTabla = new DefaultTableModel(
                new String[]{"ID", "Solicitante", "Fecha", "Estado"},
                0
        );

        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);

        btnVolver = new JButton("Volver");

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnVolver);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.add(scroll, BorderLayout.CENTER);
        panelPrincipal.add(panelBoton, BorderLayout.SOUTH);

        setContentPane(panelPrincipal);
        FormatoVentana("Historial de Trámites", 600, 400);
    }

    public void setControlador(HistorialControlador controlador) {
        this.controlador = controlador;

        btnVolver.addActionListener(e -> controlador.volver());
    }

    public void mostrarTramites(List<Tramite> tramites) {
        modeloTabla.setRowCount(0); // limpiar tabla

        for (Tramite t : tramites) {
            modeloTabla.addRow(new Object[]{
                    t.getIdTramite(),
                    t.getSolicitante().getNombreSolicitante(),
                    t.getFechaSolicitud(),
                    t.getEstado()
            });
        }
    }
}
