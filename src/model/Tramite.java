package model;

import java.time.LocalDate;
import java.util.Date;

public class Tramite {
    private int idTramite;
    private int idSolicitante;
    private EstadoTramite estado;
    private boolean requisitoMedico;
    private boolean requisitoPago;
    private boolean requisitoMultas;
    private LocalDate fechaSolicitud;
    private String createdBy;
    private Date createdAt;

    public Tramite(int idSolicitante) {
        this.idSolicitante = idSolicitante;
        this.estado = EstadoTramite.PENDIENTE;
        this.fechaSolicitud = LocalDate.now();
    }

    public int getIdTramite() {
        return idTramite;
    }

    public void setIdTramite(int idTramite) {
        this.idTramite = idTramite;
    }

    public int getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(int idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public EstadoTramite getEstado() {
        return estado;
    }

    public void setEstado(EstadoTramite estado) {
        this.estado = estado;
    }

    public boolean isRequisitoMedico() {
        return requisitoMedico;
    }

    public void setRequisitoMedico(boolean requisitoMedico) {
        this.requisitoMedico = requisitoMedico;
    }

    public boolean isRequisitoPago() {
        return requisitoPago;
    }

    public void setRequisitoPago(boolean requisitoPago) {
        this.requisitoPago = requisitoPago;
    }

    public boolean isRequisitoMultas() {
        return requisitoMultas;
    }

    public void setRequisitoMultas(boolean requisitoMultas) {
        this.requisitoMultas = requisitoMultas;
    }

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
