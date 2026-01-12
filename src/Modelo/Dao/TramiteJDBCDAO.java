package Modelo.Dao;

import Modelo.Clases.Tramite;
import Modelo.Enums.EstadoTramite;
import Modelo.Repositorio.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TramiteJDBCDAO implements TramiteDAO {

    @Override
    public void crearTramite(Tramite t) {
        String sql = "INSERT INTO tramites(estado, fecha_solicitud) VALUES (?, CURRENT_DATE)";
        try (Connection c = Conexion.getConection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, t.getEstado().name());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Tramite buscarPorId(int id) {
        String sql = "SELECT * FROM tramites WHERE id=?";
        try (Connection c = Conexion.getConection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Tramite t = new Tramite();
                t.setIdTramite(id);
                t.setEstado(EstadoTramite.valueOf(rs.getString("estado")));
                t.setFechaSolicitud(rs.getDate("fecha_solicitud").toLocalDate());
                return t;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actualizarEstado(int id, EstadoTramite estado) {
        String sql = "UPDATE tramites SET estado=? WHERE id=?";
        try (Connection c = Conexion.getConection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, estado.name());
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Tramite> listar() {
        List<Tramite> lista = new ArrayList<>();
        String sql = "SELECT * FROM tramites";

        try (Connection c = Conexion.getConection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Tramite t = new Tramite();
                t.setIdTramite(rs.getInt("id"));
                t.setEstado(EstadoTramite.valueOf(rs.getString("estado")));
                t.setFechaSolicitud(rs.getDate("fecha_solicitud").toLocalDate());
                lista.add(t);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
}
