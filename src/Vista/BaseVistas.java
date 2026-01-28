package Vista;

import javax.swing.*;
import java.awt.*;

public abstract class BaseVistas extends JFrame {
    public void FormatoVentana(String titulo, int w, int h){
        setSize(w,h);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(titulo);
        setLocationRelativeTo(null);
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

    public void redimensionarImagenIcono(JLabel label, int ancho, int largo){
        label.setPreferredSize(new Dimension(ancho, largo));

        ImageIcon iconoOriginal = (ImageIcon) label.getIcon();
        if (iconoOriginal != null) {
            Image imagenOriginal = iconoOriginal.getImage();
            // Redimensionar a 200x200 (o el tamaño que desees)
            Image imagenRedimensionada = imagenOriginal.getScaledInstance(
                    ancho, largo, Image.SCALE_SMOOTH);
            ImageIcon nuevoIcono = new ImageIcon(imagenRedimensionada);
            label.setIcon(nuevoIcono);
        }
    }
}
