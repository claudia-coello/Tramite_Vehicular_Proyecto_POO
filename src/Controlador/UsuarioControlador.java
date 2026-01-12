package Controlador;

import Modelo.Enums.ModoFormularioUsuario;
import Modelo.Service.UsuarioService;
import Vista.Formularios.FormularioUsuario;

import javax.swing.*;
import java.awt.*;

public class UsuarioControlador {

    private UsuarioService service = new UsuarioService();
    private FormularioUsuario vista;

    public UsuarioControlador(FormularioUsuario vista) {
        this.vista = vista;
        vista.setController(this);
    }

    public void crearUsuario() {
        try {
            service.crearUsuario(
                    vista.getNombreCompleto(),
                    vista.getUsuario(),
                    vista.getClave(),
                    vista.getRol()
            );
            JOptionPane.showMessageDialog(vista, "Usuario creado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, e.getMessage());
        }
    }

    public void editarUsuario() {
        try {
            service.editarUsuario(
                    vista.getUsuario(),
                    vista.getNombreCompleto(),
                    vista.getRol(),
                    vista.getEstado()
            );
            JOptionPane.showMessageDialog(vista, "Usuario editado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, e.getMessage());
        }
    }

    public void cambiarClaveUsuario() {
        try {
            service.cambiarClave(vista.getUsuario(), vista.getClave());
            JOptionPane.showMessageDialog(vista, "Clave actualizada");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, e.getMessage());
        }
    }

    public void volver() {
        vista.dispose();
    }

    public void listarUsuarios() {
        try {
            var lista = service.listarUsuarios();

            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(
                        vista,
                        "No existen usuarios registrados",
                        "Listado de usuarios",
                        JOptionPane.INFORMATION_MESSAGE
                );
                return;
            }

            StringBuilder sb = new StringBuilder("USUARIOS REGISTRADOS\n\n");

            for (var u : lista) {
                sb.append("ID: ").append(u.getIdUsuario()).append("\n")
                        .append("Nombre: ").append(u.getNombre()).append("\n")
                        .append("Usuario: ").append(u.getUsername()).append("\n")
                        .append("Rol: ").append(u.getRol()).append("\n")
                        .append("Estado: ").append(u.getEstado()).append("\n")
                        .append("---------------------------\n");
            }

            JTextArea area = new JTextArea(sb.toString());
            area.setEditable(false);
            area.setFont(new Font("monospaced", Font.PLAIN, 12));

            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new Dimension(450, 300));

            JOptionPane.showMessageDialog(
                    vista,
                    scroll,
                    "Listado de usuarios",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    vista,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

}
