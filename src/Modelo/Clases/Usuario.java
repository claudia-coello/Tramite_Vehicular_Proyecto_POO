package Modelo.Clases;

import Modelo.Enums.EstadoUsuario;
import Modelo.Enums.Rol;

public class Usuario {
    private int idUsuario;
    private String username;
    private String nombre;
    private String passwordHash;
    private Rol rol;
    private EstadoUsuario estado;

    public Usuario() {
    }

    public Usuario(String nombre, String username, String passwordHash, Rol rol) {
        this.nombre = nombre;
        this.username = username;
        this.passwordHash = passwordHash;
        this.rol = rol;
        this.estado = EstadoUsuario.ACTIVO;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public EstadoUsuario getEstado() {
        return estado;
    }

    public void setEstado(EstadoUsuario estado) {
        this.estado = estado;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
