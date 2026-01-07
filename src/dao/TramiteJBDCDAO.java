package dao;

import model.EstadoTramite;
import model.Solicitante;
import model.Tramite;
import util.Conexion;

import javax.swing.*;
import java.sql.*;
import java.sql.SQLException;

public class TramiteJBDCDAO implements TramiteDao{
    @Override
    public void crearTramite(Tramite tramite) {
        String crear = "INSERT INTO tramites(solicitante_id) values(?)";//es el unico que realmente necesitamos poner, el resto es automatico en la bd
        try(Connection conn = Conexion.getConection(); PreparedStatement pstm = conn.prepareStatement(crear)){
            pstm.setInt(1, tramite.getSolicitante().getIdSolicitante());
            pstm.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error", e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Tramite buscarTramitePorId(int id) {
        String buscar = """
                SELECT t.id, t.fecha_solicitud, t.estado,
                    s.id as solicitante_id, s.cedula, s.nombre
                    FROM tramites t
                    JOIN solicitantes s on t.solicitante_id = s.id
                    WHERE t.id=?
                """;
        try(Connection conn = Conexion.getConection(); PreparedStatement stm = conn.prepareStatement(buscar)){
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            if (rs.next()){
                Solicitante s = new Solicitante();
                s.setIdSolicitante(rs.getInt("solicitante_id"));
                s.setCedula(rs.getString("cedula"));
                s.setNombreSolicitante(rs.getString("nombre"));

                Tramite t = new Tramite();
                t.setIdTramite(rs.getInt("id"));
                t.setFechaSolicitud(rs.getDate("fecha_solicitud").toLocalDate());
                t.setEstado(EstadoTramite.valueOf(rs.getString("estado")));

                JOptionPane.showMessageDialog(null, "Coincidencia: " + t.getIdTramite());
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error", e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    @Override
    public void actualizarEstadoTramite(int id, EstadoTramite estadoTramite) {
        String actualizar = "UPDATE tramites SET estado = ? WHERE id = ?";
        try(Connection conn = Conexion.getConection(); PreparedStatement stm = conn.prepareStatement(actualizar)){
            stm.setString(1, estadoTramite.name());
            stm.setInt(2, id);

            stm.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error", e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }
}
