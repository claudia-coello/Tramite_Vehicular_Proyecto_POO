package Vista.Menu;

import Modelo.Clases.Usuario;
import Vista.BaseVistas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnalistaMenu extends BaseVistas {
    private JButton administrarTramitesButton;
    private JLabel lblOpcion;
    private JButton generarLicenciaButton;
    private JLabel lblNombreUsuario;
    private JLabel lblUsername;//username
    private JLabel lblRol;//rol
    private JButton btnCerrarSesion;
    private JLabel lblAdministrador;
    private JButton administrarSolicitantesButton;

    public AnalistaMenu(Usuario usuario){
        lblNombreUsuario.setText(usuario.getNombre());
        lblRol.setText(usuario.getRol().toString());
        lblUsername.setText(usuario.getUsername());

        FormatoVentana("Analista panel", 400, 400);
        administrarTramitesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        administrarSolicitantesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        generarLicenciaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


}
