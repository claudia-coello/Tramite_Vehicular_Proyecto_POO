package Vista;

import Modelo.Dao.UsuarioJDBCDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JPanel panel1;
    private JLabel lblBienvenido;
    private JLabel lblNombre;
    private JLabel lblClave;
    private JTextField inputNombre;
    private JPasswordField inputClave;
    private JPanel panel2;
    private JButton crearUsuario;

    private Image logo;

    public Login() {
        setSize(500, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Sistema de matriculación");

        panel1.setBorder(new EmptyBorder(1, 1, 1, 1));

        setContentPane(panel1);
        setLocationRelativeTo(null);
        setVisible(true);
        crearUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new UsuarioJDBCDAO();
                //UsuarioJDBCDAO.crearUsuario("Claudia Coello", "1234");
            }
        });
    }

    private void createUIComponents() {
        //logo = new ImageIcon(getClass().getResource("src/resources/img/logo.jpg")).getImage();

        panel2 = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(logo, 0, 0, getWidth(), getHeight(), this);
            }
        };
    }
}
