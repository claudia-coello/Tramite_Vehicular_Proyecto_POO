package Vista.Menu;

import Controlador.MenuControlador;
import Modelo.Clases.Usuario;

import javax.swing.*;
import java.awt.*;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.EmptyBorder;

public class MenuAdministrador extends MenuBase{
    private JButton btnCrearUsuario;
    private JButton btnEditarUsuario;
    private JButton btnListarUsuarios;
    private JButton btnMostrarHistorial;

    public MenuAdministrador(Usuario u){
        super(u);

        btnCrearUsuario = new JButton("Crear Usuario");
        btnEditarUsuario = new JButton("Editar Usuario");
        btnListarUsuarios = new JButton("Listar Usuario");
        btnMostrarHistorial = new JButton("Mostrar Historial");

        panelBotones.add(btnCrearUsuario);
        panelBotones.add(btnEditarUsuario);
        panelBotones.add(btnListarUsuarios);
        panelBotones.add(btnMostrarHistorial);

        panelPrincipal.add(panelBotones);

        // Reorganizar el panel principal
        panelPrincipal.removeAll(); // Limpiar todo
        panelPrincipal.setLayout(new BorderLayout());

        JLabel lblBienvenido = new JLabel("Bienvenido: " + u.getUsername());

        panelPrincipal.add(lblBienvenido);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        FormatoVentana("Menu Administrador", 600, 400);

        // Actualizar la interfaz
        panelPrincipal.revalidate();
        panelPrincipal.repaint();

    }

    public void setControlador(MenuControlador controlador) {
        super.setControlador(controlador);

        btnCrearUsuario.addActionListener(e -> controlador.crearUsuario());
        btnEditarUsuario.addActionListener(e -> controlador.editarUsuario());
        btnListarUsuarios.addActionListener(e -> controlador.listarUsuarios());
        btnMostrarHistorial.addActionListener(e -> controlador.mostrarHistorial());
    }
}
