package dao;

import model.Examen;
import model.Tramite;
import util.Conexion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            JOptionPane.showMessageDialog(null, "Error", e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Tramite buscarExamenPorIdTramite(int idTramite) {
        String buscar = "select * from examenes where tramite_id=?";

        try(Connection conn = Conexion.getConection(); PreparedStatement pstm = conn.prepareStatement(buscar);) {

            pstm.setInt(1, idTramite);
            pstm.execute();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error", e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
