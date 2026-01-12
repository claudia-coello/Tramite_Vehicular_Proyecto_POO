package Modelo.Clases;

import Modelo.Enums.TipoLicencia;

import java.time.LocalDate;

public class Licencia {
    private int idLicencia;
    private Tramite tramite;
    private LocalDate fechaEmision;
    private LocalDate fechaVencimiento;
    private TipoLicencia tipo;

    public Licencia(){}

    public Licencia(TipoLicencia tipo, Tramite tramite) {
        this.tipo = tipo;
        this.tramite = tramite;
        this.fechaEmision = LocalDate.now();
    }

    public int getIdLicencia() {
        return idLicencia;
    }

    public void setIdLicencia(int idLicencia) {
        this.idLicencia = idLicencia;
    }

    public Tramite getTramite() {
        return tramite;
    }

    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }
}
