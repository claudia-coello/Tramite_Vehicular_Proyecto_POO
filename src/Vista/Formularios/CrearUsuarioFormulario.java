package Vista.Formularios;

import Excepciones.DatosIncompletosException;
import Excepciones.DatosInexistentesException;
import Modelo.Clases.Rol;
import Vista.BaseVistas;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.mindrot.jbcrypt.BCrypt; // Asegúrate de tener esta dependencia

public class CrearUsuarioFormulario extends BaseVistas {
    private JButton cancelarButton1;
    private JButton guardarButton;
    private JTextField nombresInput;
    private JTextField apellidosInput;
    private JTextField usuarioInput;
    private JPasswordField claveInput;
    private JPasswordField confirmarInput;
    private JComboBox<Rol> opcionesRol;
    private JPanel panelIzquierdo;
    private JPanel panelDerecho;
    private JPanel panelGrande;
    private JLabel lblCrear;
    private JLabel lblNombres;
    private JLabel lblApellidos;
    private JLabel lblUsuario;
    private JLabel lblClave;
    private JLabel lblConfirmarClave;
    private JLabel lblRol;

    private JFrame ventanaAnterior;

    public CrearUsuarioFormulario(JFrame ventanaAnterior){
        this.ventanaAnterior = ventanaAnterior;
        FormatoVentana("Crear usuario", 500, 500); // Aumenté dimensiones

        opcionesRol.setModel(new DefaultComboBoxModel<>(Rol.values()));

        this.setContentPane(panelGrande);
        this.setVisible(true);

        cancelarButton1.addActionListener(e -> confirmarCancelar());
        guardarButton.addActionListener(e -> guardarUsuario());
    }

    private void guardarUsuario() {
        try {
            String nombres = nombresInput.getText().trim();
            String apellidos = apellidosInput.getText().trim();
            String username = usuarioInput.getText().trim();
            String clave = new String(claveInput.getPassword());
            String confirmarClave = new String(confirmarInput.getPassword());

            if (nombres.isEmpty() || apellidos.isEmpty() || username.isEmpty() || clave.isEmpty() || confirmarClave.isEmpty()) {
                throw new DatosInexistentesException("Todos los campos son obligatorios");
            }

            //validar que las claves coincidan
            if (!clave.equals(confirmarClave)) {
                claveInput.setText("");
                confirmarInput.setText("");
                claveInput.requestFocus();//para que resalte el campo
                throw new DatosIncompletosException("Las contraseñas no coinciden");
            }

            if (clave.length() < 6) {
                throw new DatosIncompletosException("La contraseña debe tener al menos 6 caracteres");
            }

            String claveHash = BCrypt.hashpw(clave, BCrypt.gensalt());

            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea guardar el usuario con los siguientes datos?", "Confirmar guardado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (respuesta == JOptionPane.YES_OPTION) {
                mostrarMensaje("Usuario creado exitosamente");

                // Cerrar ventana y regresar
                this.dispose();
                if (ventanaAnterior != null) {
                    ventanaAnterior.setVisible(true);
                }
            }

        } catch (Exception ex) {
            mostrarMensaje("Error al crear usuario: " + ex.getMessage());
        }
    }

    private void confirmarCancelar() {
        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de querer cancelar?\nPerderá los datos ingresados",
                "Confirmar cancelación",
                JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            this.dispose();
            if (ventanaAnterior != null) {
                ventanaAnterior.setVisible(true);
            }
        }
    }
}