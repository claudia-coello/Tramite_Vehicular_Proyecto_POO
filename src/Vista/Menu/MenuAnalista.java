package Vista.Menu;

import Modelo.Clases.Usuario;

public class MenuAnalista extends MenuBase {
    public MenuAnalista(Usuario u) {
        super(u);
        FormatoVentana("Menu Analista", 500, 300);
        setContentPane(panelPrincipal);

        FormatoVentana("Menu Administrador", 200, 200);
    }
}
