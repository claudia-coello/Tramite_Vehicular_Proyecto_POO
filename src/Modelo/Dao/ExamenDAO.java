package Modelo.Dao;

import Modelo.Clases.Examen;

import java.util.List;

public interface ExamenDAO {
    void crearExamen(Examen examen);
    Examen buscarExamenPorIdTramite(int id);
    List<Examen> listarExamenes();
}
