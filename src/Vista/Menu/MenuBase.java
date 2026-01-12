package Vista.Menu;

import Controlador.MenuControlador;
import Controlador.TramiteControlador;
import Controlador.UsuarioControlador;
import Modelo.Clases.Usuario;
import Vista.BaseVistas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MenuBase extends BaseVistas {

    protected MenuControlador mControlador;
    protected UsuarioControlador uControlador;
    protected TramiteControlador tControlador;

    protected JPanel panelPrincipal;
    protected JPanel panelBotones;
    protected JPanel panelInferior;

    protected JLabel lblBienvenido;

    protected JButton btnRegistrarTramite;
    protected JButton btnListarTramites;
    protected JButton btnBuscarTramite;
    protected JButton btnActualizarTramite;
    protected JButton btnCambiarClave;
    protected JButton btnCerrarSesion;

    public MenuBase(Usuario usuario) {

        panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

        lblBienvenido = new JLabel("Bienvenido: " + usuario.getUsername(), SwingConstants.CENTER);
        panelPrincipal.add(lblBienvenido, BorderLayout.NORTH);

        panelBotones = new JPanel(new GridLayout(0, 1, 5, 5));

        btnRegistrarTramite = new JButton("Registrar Trámite");
        btnListarTramites = new JButton("Listar Trámites");
        btnBuscarTramite = new JButton("Buscar Trámite");
        btnActualizarTramite = new JButton("Actualizar Trámite");
        btnCambiarClave = new JButton("Cambiar mi clave");

        panelBotones.add(btnRegistrarTramite);
        panelBotones.add(btnListarTramites);
        panelBotones.add(btnBuscarTramite);
        panelBotones.add(btnActualizarTramite);
        panelBotones.add(btnCambiarClave);

        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        panelInferior = new JPanel();
        btnCerrarSesion = new JButton("Cerrar sesión");
        panelInferior.add(btnCerrarSesion);

        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        setContentPane(panelPrincipal);
    }

    public void setControlador(MenuControlador mc, UsuarioControlador uc, TramiteControlador tc) {
        this.mControlador = mc;

        btnRegistrarTramite.addActionListener(e -> tc.registrarTramite());
        btnListarTramites.addActionListener(e -> tc.listarTramites());
        btnBuscarTramite.addActionListener(e -> tc.buscarTramite());
        btnActualizarTramite.addActionListener(e -> tc.actualizarTramite());
        btnCambiarClave.addActionListener(e -> uc.cambiarClaveUsuario());
        btnCerrarSesion.addActionListener(e -> mc.cerrarSesion());
    }

}
