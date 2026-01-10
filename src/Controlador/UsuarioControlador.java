package Controlador;

import Modelo.Clases.Rol;
import Modelo.Clases.Usuario;
import Modelo.Service.UsuarioService;
import Vista.Formularios.FormularioUsuarios;

public class UsuarioControlador {
    private FormularioUsuarios fu;
    private UsuarioService usuarioService;

    public UsuarioControlador(FormularioUsuarios fu){
        this.fu = fu;
        this.usuarioService = new UsuarioService();
        this.fu.setController(this);
    }

    public void crearUsuario() {
        usuarioService.crearUsuario(fu.getNombreCompleto(), fu.getUsuario(), fu.getClave(), Rol.ANALISTA);
    }

    public void volver() {

    }
}
