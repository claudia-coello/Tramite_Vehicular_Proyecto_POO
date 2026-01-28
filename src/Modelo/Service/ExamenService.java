package Modelo.Service;

import Modelo.Dao.ExamenJDBCDAO;
import Excepciones.DatosInexistentesException;
import Modelo.Clases.Examen;
import Excepciones.DatosIncompletosException;

/*
Responsabilidades

Calcular resultado

Decidir aprobación

Cambiar estado del trámite
* */
public class ExamenService{
    ExamenJDBCDAO e = new ExamenJDBCDAO();

    public void crearExamen(Examen examen) {
        if (examen == null ) throw new DatosIncompletosException("Examen no porporcionado");

        ExamenJDBCDAO e = new ExamenJDBCDAO();
        e.crearExamen(examen);
    }

    public Examen buscarExamenPorIdTramite(int id) {
        if (id < 0) throw new DatosIncompletosException("Id no porporcionado");

        ExamenJDBCDAO e = new ExamenJDBCDAO();
        Examen examenEncontrado = e.buscarExamenPorIdTramite(id);

        if (examenEncontrado == null) throw new DatosInexistentesException("No se encontraron examenes relacionados a ese id");

        return examenEncontrado;
    }

    public void validarResultado(Examen examen) {
        if (examen.getNotaPractica() >= 7 && examen.getNotaTeorica() >= 7) examen.setResultado(true);
        else examen.setResultado(false);
    }
}
