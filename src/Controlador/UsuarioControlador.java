//package Controlador;
//
//import Excepciones.DatosIncompletosException;
//import Modelo.Clases.Rol;
//import Modelo.Service.UsuarioService;
//import Vista.Formularios.CrearUsuarioFormulario;
//import Vista.Menu.AnalistaMenu;
//
//import javax.swing.*;
//
//public class UsuarioControlador {
//    private CrearUsuarioFormulario fu;
//    private UsuarioService usuarioService;
//
//    public UsuarioControlador(CrearUsuarioFormulario fu){
//        this.fu = fu;
//        this.usuarioService = new UsuarioService();
//    }
//
//    public void crearUsuario() {
//        try {
//            String nombreCompleto = fu.getNombreCompleto();
//            String usuario = fu.getUsuario();
//            String clave = fu.getClave();
//            Rol rol = Rol.ANALISTA;
//
//            if ((nombreCompleto == null || nombreCompleto.isBlank()) || (usuario == null || usuario.isBlank()) || (clave == null || clave.isBlank())) throw  new DatosIncompletosException("Porfavor llene todos los datos");
//            usuarioService.crearUsuario(nombreCompleto, usuario, clave, rol);
//
//        }catch (DatosIncompletosException d){
//            JOptionPane.showMessageDialog(null, d.getMessage());
//        }
//    }
//
//    public void editarUsuario() {
//    }
//
//    public void listarUsuarios() {
//    }
//
//    public void cambiarClaveUsuario() {
//    }
//
//    public void volver() {
//        fu.cambiarVentana(new AnalistaMenu());
//    }
//}
