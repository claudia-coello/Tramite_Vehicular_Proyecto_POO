package Vista;

import Controlador.LoginControlador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Login extends BaseVistas {
    private JTextField inputUsuario;
    private JPasswordField inputClave;
    private JButton btnIniciarSesion;
    private JButton btnCrearUsuario;
    private JLabel lblUsuario;
    private JLabel lblClave;

    private LoginControlador controller;

    public Login() {
        // Crear los componentes, deben ser instanciados con new
        lblUsuario = new JLabel("Usuario:");
        lblClave = new JLabel("Clave:");
        inputUsuario = new JTextField();
        inputClave = new JPasswordField();
        btnIniciarSesion = new JButton("Iniciar sesión");
        btnCrearUsuario = new JButton("Crear usuario");

        // Panel principal
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 0, 10));
        panel.setBackground(Color.CYAN);

        panel.add(lblUsuario);
        panel.add(inputUsuario);
        panel.add(lblClave);
        panel.add(inputClave);
        panel.add(btnIniciarSesion);
        panel.add(btnCrearUsuario);

        setContentPane(panel);
        FormatoVentana("Sistema matriculación", 400, 150);
    }

    public void setController(LoginControlador controlador) {
        this.controller = controlador;

        btnIniciarSesion.addActionListener(e -> controlador.iniciarSesion());
        btnCrearUsuario.addActionListener(e -> controlador.crearUsuario());
    }

    public String getUsuario() {
        return inputUsuario.getText();
    }

    public String getClave() {
        return new String(inputClave.getPassword());
    }
}

