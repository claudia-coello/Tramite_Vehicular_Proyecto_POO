package Vista;

import Controlador.LoginControlador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginPrueba extends JFrame {
    private JPanel panel1;
    private JLabel lblBienvenido;
    private JLabel lblUsuario;
    private JLabel lblClave;
    private JTextField inputNombre;
    private JPasswordField inputClave;
    private JPanel panel2;
    private JButton crearUsuario;
    private JButton iniciarSesionButton;

    private Image logo;
    LoginControlador controller;

    public LoginPrueba() {
        setSize(500, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Sistema de matriculación");

        panel1.setBorder(new EmptyBorder(1, 1, 1, 1));

        setContentPane(panel1);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public String getUsuario() {
        return inputNombre.getText();
    }

    public String getClave() {
        return new String(inputClave.getPassword());
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void cerrar() {
        dispose();
    }

    public void setController(LoginControlador controller) {
        this.controller = controller;

        iniciarSesionButton.addActionListener(e -> controller.iniciarSesion());
        crearUsuario.addActionListener(e -> controller.crearUsuario());
    }
    //poner imagen
    private void createUIComponents() {
        logo = new ImageIcon(
                getClass().getClassLoader().getResource("img/logo.jpg")
        ).getImage();


        panel2 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(logo, 0, 0, getWidth(), getHeight(), this);
            }
        };
    }
}
