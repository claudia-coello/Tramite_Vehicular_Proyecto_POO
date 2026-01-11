package Vista.Formularios;

import Controlador.UsuarioControlador;
import Vista.BaseVistas;

import javax.swing.*;
import java.awt.*;

public class CrearUsuario extends BaseVistas {
    UsuarioControlador controlador;

    private JLabel lblNombres;
    private JLabel lblApellidos;
    private JLabel lblUsername;
    private JLabel lblClave;

    private JTextField inputNombres;
    private JTextField inputApellidos;
    private JTextField inputUsername;
    private JPasswordField inputClave;

    private JButton btnRegistrar;
    private JButton btnCancelar;

    private JPanel panel;
    private JPanel panelInputs;
    private JPanel panelBotones;

    public CrearUsuario(){

        panel = new JPanel(new GridLayout(2, 1, 0, 10));

        panelInputs = new JPanel(new GridLayout(4, 2, 5, 5));
        panelBotones = new JPanel(new GridLayout(1, 2, 10, 0));

        lblNombres = new JLabel("Nombres: ");
        lblApellidos = new JLabel("Apellidos: ");
        lblUsername  = new JLabel("Usuario: ");
        lblClave  = new JLabel("Clave: ");

        inputNombres = new JTextField();
        inputApellidos = new JTextField();
        inputUsername = new JTextField();
        inputClave = new JPasswordField();

        btnRegistrar = new JButton("Registrarse");
        btnCancelar = new JButton("Cancelar");

        panelInputs.add(lblNombres);
        panelInputs.add(inputNombres);

        panelInputs.add(lblApellidos);
        panelInputs.add(inputApellidos);

        panelInputs.add(lblUsername);
        panelInputs.add(inputUsername);

        panelInputs.add(lblClave);
        panelInputs.add(inputClave);

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnCancelar);

        // Agregar al panel principal
        panel.add(panelInputs);   // Fila 1: Inputs
        panel.add(panelBotones);  // Fila 2: Botones

        FormatoVentana("Registro nuevo usuario", 400, 300);
        setContentPane(panel);
    }

    public String getUsuario() {
        return inputUsername.getText();
    }

    public String getNombreCompleto() {
        return inputNombres.getText() + " " + inputApellidos.getText();
    }

    public String getClave() {
        return new String(inputClave.getPassword());
    }

    public void setController(UsuarioControlador controlador) {
        this.controlador = controlador;

        btnCancelar.addActionListener(e -> controlador.volver());
        btnRegistrar.addActionListener(e -> controlador.crearUsuario());
    }
}
