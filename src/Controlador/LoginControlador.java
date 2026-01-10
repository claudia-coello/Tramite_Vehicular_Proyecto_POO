package Controlador;

import Modelo.Clases.Rol;
import Modelo.Clases.Usuario;
import Modelo.Service.UsuarioService;
import Vista.Formularios.FormularioUsuarios;
import Vista.Login;
import Vista.Menu.MenuAdministrador;
import Vista.Menu.MenuAnalista;

public class LoginControlador {
    private Login login;
    private UsuarioService usuarioService;

    public LoginControlador(Login login){
        this.login = login;
        this.usuarioService = new UsuarioService();
        //unimos la vista del login con este controlador
        this.login.setController(this);
    }

    public void iniciarSesion(){
        String usuarioIngresado = login.getUsuario();
        String claveIngresada = login.getClave();

        if ((usuarioIngresado == null || usuarioIngresado.isBlank()) || (claveIngresada == null || claveIngresada.isBlank())){
            login.mostrarMensaje("Porfavor ingrese su usuario y clave");
            return;
        }

        try {
            Usuario usuario = usuarioService.login(usuarioIngresado, claveIngresada);
            if (usuarioService.login(usuarioIngresado, claveIngresada) != null){
                login.mostrarMensaje("Bienvido: " + usuarioIngresado);
                cambiarVista(usuario);
            }

        }catch (Exception e){
            login.mostrarMensaje(e.getMessage());
        }

    }
    public void crearUsuario(){
        login.cambiarVentana(new FormularioUsuarios());
    }

    public void cambiarVista(Usuario usuario){
        Rol rol = usuario.getRol();

        switch (rol){
            case ANALISTA -> login.cambiarVentana(new MenuAnalista(usuario));
            case ADMINISTRADOR -> login.cambiarVentana(new MenuAdministrador(usuario));
            default -> login.mostrarMensaje("Rol no encontrado");
        }
    }

}
