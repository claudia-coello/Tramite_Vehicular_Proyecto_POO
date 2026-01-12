package Vista.Formularios;

import Controlador.TramiteControlador;
import Modelo.Enums.EstadoTramite;
import Modelo.Clases.Tramite;
import Vista.BaseVistas;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FormularioTramite extends BaseVistas {

    private JTextField inputIdTramite;
    private JTextField inputIdSolicitante;
    private JComboBox<EstadoTramite> comboEstado;

    private JButton btnBuscar;
    private JButton btnCancelar;

    private TramiteControlador controlador;

    public FormularioTramite() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));

        panel.add(new JLabel("ID Trámite:"));
        inputIdTramite = new JTextField();
        panel.add(inputIdTramite);

        panel.add(new JLabel("ID Solicitante:"));
        inputIdSolicitante = new JTextField();
        panel.add(inputIdSolicitante);

        panel.add(new JLabel("Estado:"));
        comboEstado = new JComboBox<>(EstadoTramite.values());
        panel.add(comboEstado);

        btnBuscar = new JButton("Buscar");
        btnCancelar = new JButton("Cancelar");

        panel.add(btnBuscar);
        panel.add(btnCancelar);

        setContentPane(panel);
        FormatoVentana("Gestión de Trámite", 350, 220);
    }

    public int getIdTramite() {
        return Integer.parseInt(inputIdTramite.getText());
    }

    public int getIdSolicitante() {
        return Integer.parseInt(inputIdSolicitante.getText());
    }

    public EstadoTramite getEstado() {
        return (EstadoTramite) comboEstado.getSelectedItem();
    }

    public void mostrarTramite(Tramite t) {
        String mensaje =
                "ID: " + t.getIdTramite() + "\n" +
                        "Fecha: " + t.getFechaSolicitud() + "\n" +
                        "Estado: " + t.getEstado();

        JOptionPane.showMessageDialog(this, mensaje, "Trámite encontrado", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarLista(List<Tramite> lista) {
        StringBuilder sb = new StringBuilder();

        for (Tramite t : lista) {
            sb.append("ID: ").append(t.getIdTramite())
                    .append(" | Estado: ").append(t.getEstado())
                    .append(" | Fecha: ").append(t.getFechaSolicitud())
                    .append("\n");
        }

        JTextArea area = new JTextArea(sb.toString());
        area.setEditable(false);

        JOptionPane.showMessageDialog(this, new JScrollPane(area),
                "Lista de Trámites", JOptionPane.INFORMATION_MESSAGE);
    }

    public void setControlador(TramiteControlador controlador) {
        this.controlador = controlador;

        btnBuscar.addActionListener(e -> controlador.buscarTramite());
        btnCancelar.addActionListener(e -> controlador.volver());
    }

}
