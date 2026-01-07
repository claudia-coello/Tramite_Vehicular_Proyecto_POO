package dao;

import model.Examen;
import model.Tramite;

public interface ExamenDAO {
    void crearExamen(Examen examen);
    Tramite buscarExamenPorIdTramite(int id);
}
