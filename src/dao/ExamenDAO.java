package dao;

import model.Examen;
import model.Tramite;

import java.util.List;

public interface ExamenDAO {
    void crearExamen(Examen examen);
    Examen buscarExamenPorIdTramite(int id);
    List<Examen> listarExamenes();
}
