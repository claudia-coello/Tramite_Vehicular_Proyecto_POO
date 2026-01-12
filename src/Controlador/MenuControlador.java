package Controlador;

import Vista.Login;
import Vista.Menu.MenuBase;
import Vista.Formularios.FormularioHistorial;

public class MenuControlador {

    private MenuBase vistaMenu;

    public MenuControlador(MenuBase vistaMenu) {
        this.vistaMenu = vistaMenu;
        this.vistaMenu.setControlador(this);
    }


    public void cerrarSesion() {
        vistaMenu.cambiarVentana(new Login());
    }

    public void mostrarHistorial() {
        FormularioHistorial vista = new FormularioHistorial();
        new HistorialControlador(vista);
        vistaMenu.dispose();
    }
}
