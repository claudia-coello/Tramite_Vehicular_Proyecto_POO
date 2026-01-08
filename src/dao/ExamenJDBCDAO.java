package dao;

import model.EstadoTramite;
import model.Examen;
import model.Solicitante;
import model.Tramite;
import util.Conexion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamenJDBCDAO implements ExamenDAO{
    @Override
    public void crearExamen(Examen examen) {
        String crear = "INSERT INTO examenes(tramite_id, nota_teorica, nota_practica) values(?,?, ?)";

        try(Connection conn = Conexion.getConection(); PreparedStatement pstm = conn.prepareStatement(crear);) {

            pstm.setInt(1, examen.getTramite().getIdTramite());
            pstm.setDouble(2, examen.getNotaTeorica());
            pstm.setDouble(3, examen.getNotaPractica());

            pstm.execute();
            JOptionPane.showMessageDialog(null, "Examen creado con exito");
        } catch (SQLException e) {
            throw new RuntimeException("Error al crear examen", e);
        }
    }

    @Override
    public Examen buscarExamenPorIdTramite(int idTramite) {
        String buscar = "select * from examenes where tramite_id=?";

        try(Connection conn = Conexion.getConection(); PreparedStatement pstm = conn.prepareStatement(buscar);) {

            pstm.setInt(1, idTramite);
            pstm.execute();
        }catch (SQLException e){
            throw new RuntimeException("Error al buscar examenes", e);
        }
        return null;
    }

    @Override
    public List<Examen> listarExamenes() {
        String listar = "Select * from examenes";
        List<Examen> examenes = new ArrayList<>();

        try(Connection conn = Conexion.getConection(); PreparedStatement pstm = conn.prepareStatement(listar)){
            ResultSet rs = pstm.executeQuery();

            while (rs.next()){
                Examen e = new Examen();

                Tramite t = new Tramite();
                t.setIdTramite(rs.getInt("tramite_id"));

                e.setIdExamen(rs.getInt("id"));
                e.setTramite(t);
                e.setNotaTeorica(rs.getDouble("nota_teorica"));
                e.setNotaPractica(rs.getDouble("nota_practica"));
                e.setResultado(rs.getBoolean("estado"));

                examenes.add(e);
            }
        }catch (SQLException e ){
            throw new RuntimeException("Error al listar examenes", e);
        }
        return examenes;
    }

}
