package Vista.Formularios;

import Controlador.UsuarioControlador;
import Modelo.Enums.EstadoUsuario;
import Modelo.Enums.ModoFormularioUsuario;
import Modelo.Enums.Rol;
import Vista.BaseVistas;

import javax.swing.*;
import java.awt.*;

public class FormularioUsuario extends BaseVistas {

    private UsuarioControlador controlador;
    private ModoFormularioUsuario modo;

    private JTextField inputNombres;
    private JTextField inputApellidos;
    private JTextField inputUsername;
    private JPasswordField inputClave;

    private JComboBox<Rol> inputRol;
    private JComboBox<EstadoUsuario> inputEstado;

    private JButton btnAceptar;
    private JButton btnCancelar;

    public FormularioUsuario(ModoFormularioUsuario modo) {
        this.modo = modo;

        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 10));
        JPanel panelInputs = new JPanel(new GridLayout(6, 2, 5, 5));
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 10, 0));

        inputNombres = new JTextField();
        inputApellidos = new JTextField();
        inputUsername = new JTextField();
        inputClave = new JPasswordField();

        inputRol = new JComboBox<>(Rol.values());
        inputEstado = new JComboBox<>(EstadoUsuario.values());

        panelInputs.add(new JLabel("Nombres:"));
        panelInputs.add(inputNombres);

        panelInputs.add(new JLabel("Apellidos:"));
        panelInputs.add(inputApellidos);

        panelInputs.add(new JLabel("Usuario:"));
        panelInputs.add(inputUsername);

        panelInputs.add(new JLabel("Clave:"));
        panelInputs.add(inputClave);

        panelInputs.add(new JLabel("Rol:"));
        panelInputs.add(inputRol);

        panelInputs.add(new JLabel("Estado:"));
        panelInputs.add(inputEstado);

        btnAceptar = new JButton();
        btnCancelar = new JButton("Cancelar");

        configurarModo();

        panelBotones.add(btnAceptar);
        panelBotones.add(btnCancelar);

        panel.add(panelInputs);
        panel.add(panelBotones);

        setContentPane(panel);
        FormatoVentana("Gestión de Usuario", 450, 350);
    }

    private void configurarModo() {
        switch (modo) {
            case CREAR -> {
                btnAceptar.setText("Crear Usuario");
                inputRol.setVisible(false);
                inputEstado.setVisible(false);
            }
            case EDITAR -> {
                btnAceptar.setText("Editar Usuario");
                inputClave.setEnabled(false);
                inputUsername.setEnabled(false);
            }
            case CAMBIAR_CLAVE -> {
                btnAceptar.setText("Cambiar Clave");
                inputNombres.setEnabled(false);
                inputApellidos.setEnabled(false);
                inputRol.setVisible(false);
                inputEstado.setVisible(false);
            }
        }
    }

    // Getters
    public String getUsuario() {
        return inputUsername.getText();
    }

    public String getNombreCompleto() {
        return inputNombres.getText() + " " + inputApellidos.getText();
    }

    public String getClave() {
        return new String(inputClave.getPassword());
    }

    public Rol getRol() {
        return (Rol) inputRol.getSelectedItem();
    }

    public EstadoUsuario getEstado() {
        return (EstadoUsuario) inputEstado.getSelectedItem();
    }

    public void setController(UsuarioControlador controlador) {
        this.controlador = controlador;

        btnCancelar.addActionListener(e -> controlador.volver());

        btnAceptar.addActionListener(e -> {
            switch (modo) {
                case CREAR -> controlador.crearUsuario();
                case EDITAR -> controlador.editarUsuario();
                case CAMBIAR_CLAVE -> controlador.cambiarClaveUsuario();
            }
        });
    }
}
