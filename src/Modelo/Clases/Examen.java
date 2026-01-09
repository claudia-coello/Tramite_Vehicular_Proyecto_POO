package Modelo.Clases;

public class Examen {
    private int idExamen;
    private Tramite tramite;
    private double notaTeorica;
    private double notaPractica;
    private boolean resultado;

    public Examen(){}

    public Examen(Tramite tramite, double notaPractica, double notaTeorica) {
        this.tramite = tramite;
        this.notaPractica = notaPractica;
        this.notaTeorica = notaTeorica;
        this.resultado = false;
    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    public Tramite getTramite() {
        return tramite;
    }

    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
    }

    public double getNotaTeorica() {
        return notaTeorica;
    }

    public void setNotaTeorica(double notaTeorica) {
        this.notaTeorica = notaTeorica;
    }

    public double getNotaPractica() {
        return notaPractica;
    }

    public void setNotaPractica(double notaPractica) {
        this.notaPractica = notaPractica;
    }

    public boolean getResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }
}
