package Vista.Menu;

import Controlador.MenuControlador;
import Modelo.Clases.Usuario;
import Vista.BaseVistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static javax.swing.SwingConstants.CENTER;

public class MenuBase extends BaseVistas {
    private MenuControlador menuControlador;
    private JButton btnRegistrarTramite;
    private JButton btnListarTramites;
    private JButton btnBuscarTramite;
    private JButton btnActualizarTramite;
    private JButton btnCambiarClave;
    private JButton btnCerrarSesion;

    protected JLabel lblBienvenido;

    protected JPanel panelPrincipal;
    protected JPanel panelBotones;

    MenuControlador controlador;

    public MenuBase(Usuario usuario){
        panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

        lblBienvenido = new JLabel("Bienvenido: " + usuario.getUsername(), CENTER);

        panelBotones = new JPanel(new GridLayout(0, 1, 5, 5));

        btnRegistrarTramite = new JButton("Registrar Trámite");
        btnListarTramites = new JButton("Listar Trámites");
        btnBuscarTramite = new JButton("Buscar Trámite");
        btnActualizarTramite = new JButton("Actualizar Trámite");
        btnCambiarClave = new JButton("Cambiar Clave");
        btnCerrarSesion = new JButton("Cerrar Sesión");

        panelBotones.add(btnRegistrarTramite);
        panelBotones.add(btnListarTramites);
        panelBotones.add(btnBuscarTramite);
        panelBotones.add(btnActualizarTramite);
        panelBotones.add(btnCambiarClave);
        panelBotones.add(btnCerrarSesion);

        panelPrincipal.add(lblBienvenido, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        setContentPane(panelPrincipal);
    }

    public void setControlador(MenuControlador controlador) {
        this.controlador = controlador;

        btnRegistrarTramite.addActionListener(e -> controlador.registrarTramite());
        btnListarTramites.addActionListener(e -> controlador.listarTramites());
        btnBuscarTramite.addActionListener(e -> controlador.buscarTramite());
        btnActualizarTramite.addActionListener(e -> controlador.actualizarTramite());
        btnCambiarClave.addActionListener(e -> controlador.cambiarClave());
        btnCerrarSesion.addActionListener(e -> controlador.cerrarSesion());
    }
}
