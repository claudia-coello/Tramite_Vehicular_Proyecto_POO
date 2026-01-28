package Vista.Menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdministradorMenu {
    private JButton administrarUsuariosButton;
    private JButton administrarTramitesButton;
    private JButton generarReportesButton;
    private JButton generarLicenciaButton;
    private JButton btnCerrarSesion;
    private JLabel lblNombreUsuario;
    private JLabel lblCorreo;
    private JLabel lblCedula;
    private JLabel lblOpcion;
    private JLabel lblAdministrador;
    private JButton administrarSolicitantesButton;

    public AdministradorMenu() {
        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        administrarUsuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        administrarSolicitantesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        administrarTramitesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        generarReportesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        generarLicenciaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
