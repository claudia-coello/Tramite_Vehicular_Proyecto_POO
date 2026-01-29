package Vista.Menu;

import javax.swing.*;

import Modelo.Clases.Usuario;
import Modelo.Dao.UsuarioJDBCDAO;
import Modelo.Service.UsuarioService;
import Vista.BaseVistas;
import Vista.Formularios.CrearUsuarioFormulario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginMenu extends BaseVistas{
    private JPanel panel1;
    private JTextField inputUsuario;
    private JPasswordField inputClave;
    private JLabel lblImagen;
    private JButton btnIniciarSesion;
    private JButton registrarseButton;
    private JLabel lblClave;
    private JLabel lblBienvenida;
    private JLabel lblUsuario;
    private JPanel panelGrande;
    private JPanel panelDerecho;
    private JPanel panelIzquierdo;
    private Usuario usuario;

    public LoginMenu(){
        FormatoVentana("LOGIN", 600, 400); // Aumenté las dimensiones para mejor visualización
        redimensionarImagenIcono(lblImagen, 200, 200);

        // Configurar alineación
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagen.setVerticalAlignment(SwingConstants.CENTER);

        this.setContentPane(panel1);
        this.setVisible(true);

        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });
        registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearUsuario();
            }
        });
    }

    private void iniciarSesion() {
        if (controlador != null) {
            controlador.iniciarSesion();
        } else {
            JOptionPane.showMessageDialog(this, "Controlador no configurado");
        }
    }

    // Método para el botón registrarse
    private void crearUsuario() {
        if (controlador != null) {
            controlador.crearUsuario();
        } else {
            // Fallback al método antiguo si no hay controlador
            CrearUsuarioFormulario uf = new CrearUsuarioFormulario(this);
            cerrarVentana();
        }
    }

    public JTextField getInputUsuario() {
        return inputUsuario;
    }

    public void setInputUsuario(JTextField inputUsuario) {
        this.inputUsuario = inputUsuario;
    }

    public JPasswordField getInputClave() {
        return inputClave;
    }

    public void setInputClave(JPasswordField inputClave) {
        this.inputClave = inputClave;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
