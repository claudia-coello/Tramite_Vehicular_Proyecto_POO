package Modelo.Clases;

import java.time.LocalDate;

public class Solicitante {
    private int idSolicitante;
    private String cedula;
    private String nombreSolicitante;
    private LocalDate fechaNacimiento;
    private TipoLicencia tipoLicencia;

    public Solicitante(){}
    public Solicitante(String cedula, String nombreSolicitante, LocalDate fechaNacimiento, TipoLicencia tipoLicencia) {
        this.cedula = cedula;
        this.nombreSolicitante = nombreSolicitante;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoLicencia = tipoLicencia;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(int idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }

    public TipoLicencia getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(TipoLicencia tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
