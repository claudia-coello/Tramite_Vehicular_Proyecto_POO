package Vista;

import javax.swing.*;

public abstract class BaseVistas extends JFrame {
    public void FormatoVentana(String titulo, int w, int h){
        setSize(w,h);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(titulo);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void cerrarVentana() {
        dispose();
    }

    public void cambiarVentana(JFrame nueva){
        this.dispose();
        nueva.setVisible(true);
    }

}
