package Vista.ControladoreVentanas;

import Controlador.UsuarioControlador;
import Modelo.Clases.Rol;
import Modelo.Clases.Usuario;
import Modelo.Service.UsuarioService;
import Vista.Formularios.CrearUsuarioFormulario;
import Vista.Menu.AnalistaMenu;
import Vista.Menu.AdministradorMenu;

public class LoginControlador {
    private AnalistaMenu menuAnalista;
    private UsuarioService usuarioService;

    public LoginControlador(AnalistaMenu menuAnalista){
        this.menuAnalista = menuAnalista;
        this.usuarioService = new UsuarioService();
        //unimos la vista del menuAnalista con este controlador
        this.menuAnalista.setController(this);
    }

    public void iniciarSesion(){
        String usuarioIngresado = menuAnalista.getUsuario();
        String claveIngresada = menuAnalista.getClave();

        if ((usuarioIngresado == null || usuarioIngresado.isBlank()) || (claveIngresada == null || claveIngresada.isBlank())){
            menuAnalista.mostrarMensaje("Porfavor ingrese su usuario y clave");
            return;
        }

        try {
            Usuario usuario = usuarioService.login(usuarioIngresado, claveIngresada);
            if (usuarioService.login(usuarioIngresado, claveIngresada) != null){
                menuAnalista.mostrarMensaje("Bienvido: " + usuarioIngresado);
                cambiarVista(usuario);
            }

        }catch (Exception e){
            menuAnalista.mostrarMensaje(e.getMessage());
        }

    }
    public void crearUsuario() {
        CrearUsuario fu = new CrearUsuario();
        new UsuarioControlador(fu);   // ← ESTA LÍNEA ES OBLIGATORIA
    }


    public void cambiarVista(Usuario usuario){
        Rol rol = usuario.getRol();

        switch (rol){
            case ANALISTA -> menuAnalista.cambiarVentana(new AnalistaMenu(usuario));
            case ADMINISTRADOR -> menuAnalista.cambiarVentana(new MenuAdministrador(usuario));
            default -> menuAnalista.mostrarMensaje("Rol no encontrado");
        }
    }

}
