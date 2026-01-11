package Vista.Menu;

import Controlador.MenuControlador;
import Controlador.TramiteControlador;
import Controlador.UsuarioControlador;
import Modelo.Clases.Usuario;

import javax.swing.*;

public class MenuAdministrador extends MenuBase {

    private JButton btnCrearUsuario;
    private JButton btnEditarUsuario;
    private JButton btnListarUsuarios;
    private JButton btnMostrarHistorial;

    public MenuAdministrador(Usuario u) {
        super(u);

        btnCrearUsuario = new JButton("Crear Usuario");
        btnEditarUsuario = new JButton("Editar Usuario");
        btnListarUsuarios = new JButton("Listar Usuarios");
        btnMostrarHistorial = new JButton("Mostrar Historial");

        panelBotones.add(btnCrearUsuario);
        panelBotones.add(btnEditarUsuario);
        panelBotones.add(btnListarUsuarios);
        panelBotones.add(btnMostrarHistorial);

        FormatoVentana("Menú Administrador", 400, 450);
    }

    @Override
    public void setControlador(MenuControlador mc, UsuarioControlador uc, TramiteControlador tc) {
        super.setControlador(mc, uc, tc);

        btnCrearUsuario.addActionListener(e -> uc.crearUsuario());
        btnEditarUsuario.addActionListener(e -> uc.editarUsuario());
        btnListarUsuarios.addActionListener(e -> uc.listarUsuarios());
        btnMostrarHistorial.addActionListener(e -> mc.mostrarHistorial());
    }
}
