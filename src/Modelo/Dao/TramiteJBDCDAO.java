package Modelo.Dao;

import Modelo.Clases.EstadoTramite;
import Modelo.Clases.Solicitante;
import Modelo.Clases.Tramite;
import Modelo.Repositorio.Conexion;

import javax.swing.*;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TramiteJBDCDAO implements TramiteDAO {
    @Override
    public void crearTramite(Tramite tramite) {
        String crear = "INSERT INTO tramites(solicitante_id) values(?)";//es el unico que realmente necesitamos poner, el resto es automatico en la bd
        try(Connection conn = Conexion.getConection(); PreparedStatement pstm = conn.prepareStatement(crear)){
            pstm.setInt(1, tramite.getSolicitante().getIdSolicitante());
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear trámite", e);
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

            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al encontrar trámite", e);
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
        }
        catch (SQLException e) {
            throw new RuntimeException("Error al actualizar trámite", e);
        }
    }

    @Override
    public List<Tramite> listarTramites() {
        List<Tramite> lista = new ArrayList<>();
        String listar = "Select * from tramites";
        try(Connection conn = Conexion.getConection();
            PreparedStatement pstm = conn.prepareStatement(listar);) {

            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                Tramite t = new Tramite();

                Solicitante s = new Solicitante();
                s.setIdSolicitante(rs.getInt("solicitante_id"));

                t.setIdTramite(rs.getInt("id"));
                t.setFechaSolicitud(rs.getDate("fecha_solicitud").toLocalDate());
                t.setEstado(EstadoTramite.valueOf(rs.getString("estado")));
                t.setSolicitante(s);

                lista.add(t);
            }

        }catch (SQLException e) {
            throw new RuntimeException("Error al listar trámites", e);
        }
        return lista;
    }

    @Override
    public List<Tramite> listarTramitesPorEstado(EstadoTramite estado) {
        String listar = "Select * from tramites where estado = ?";
        List<Tramite> tramites = new ArrayList<>();

        try(Connection conn = Conexion.getConection(); PreparedStatement pstm = conn.prepareStatement(listar)){
            pstm.setString(1, estado.toString());

            ResultSet rs = pstm.executeQuery();

            while (rs.next()){
                Tramite t = new Tramite();

                Solicitante s = new Solicitante();
                s.setIdSolicitante(rs.getInt("solicitante_id"));

                t.setIdTramite(rs.getInt("id"));
                t.setFechaSolicitud(rs.getDate("fecha_solicitud").toLocalDate());
                t.setEstado(EstadoTramite.valueOf(rs.getString("estado")));
                t.setSolicitante(s);

                tramites.add(t);
            }
        }catch (SQLException e ){
            throw new RuntimeException("Error al listar trámites por estado", e);
        }
        return tramites;
    }


}
