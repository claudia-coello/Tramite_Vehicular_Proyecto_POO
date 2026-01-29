package Modelo.Dao;

import Modelo.Clases.Licencia;
import Modelo.Clases.Solicitante;
import Modelo.Clases.TipoLicencia;
import Modelo.Clases.Tramite;
import Modelo.Repositorio.Conexion;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LicenciaJDBCDAO implements LicenciaDAO {

    @Override
    public void crearLicencia(Licencia licencia) {
        String crear = "INSERT INTO licencias(tramite_id, fecha_vencimiento, tipo) VALUES (?, ?, ?)";  // Solo 3 columnas

        try (Connection conn = Conexion.getConection();
             PreparedStatement pstm = conn.prepareStatement(crear, Statement.RETURN_GENERATED_KEYS)) {  // Solo agregué RETURN_GENERATED_KEYS

            pstm.setInt(1, licencia.getTramite().getIdTramite());
            pstm.setDate(2, Date.valueOf(licencia.getFechaVencimiento()));  // Solo fecha_vencimiento
            pstm.setString(3, licencia.getTipo().name());

            pstm.executeUpdate();

            // Solo agregué este bloque para obtener el ID
            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                licencia.setIdLicencia(rs.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Licencia generada exitosamente");

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear licencia", e);
        }
    }

    @Override
    public Licencia buscarPorId(int id) {
        String buscar = """
            SELECT l.*, t.id as tramite_id, s.cedula, s.nombre as solicitante_nombre
            FROM licencias l
            JOIN tramites t ON l.tramite_id = t.id
            JOIN solicitantes s ON t.solicitante_id = s.id
            WHERE l.id = ?
            """;
        Licencia licencia = null;

        try (Connection conn = Conexion.getConection();
             PreparedStatement pstm = conn.prepareStatement(buscar)) {

            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                licencia = mapearLicencia(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar licencia por ID", e);
        }

        return licencia;
    }

    @Override
    public List<Licencia> buscarPorCedulaSolicitante(String cedula) {
        String buscar = """
            SELECT l.*, t.id as tramite_id, s.cedula, s.nombre as solicitante_nombre
            FROM licencias l
            JOIN tramites t ON l.tramite_id = t.id
            JOIN solicitantes s ON t.solicitante_id = s.id
            WHERE s.cedula = ?
            ORDER BY l.fecha_emision DESC
            """;
        List<Licencia> licencias = new ArrayList<>();

        try (Connection conn = Conexion.getConection();
             PreparedStatement pstm = conn.prepareStatement(buscar)) {

            pstm.setString(1, cedula);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                licencias.add(mapearLicencia(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar licencias por cédula", e);
        }

        return licencias;
    }

    @Override
    public List<Licencia> buscarPorTramiteId(int tramiteId) {
        String buscar = """
            SELECT l.*, t.id as tramite_id, s.cedula, s.nombre as solicitante_nombre
            FROM licencias l
            JOIN tramites t ON l.tramite_id = t.id
            JOIN solicitantes s ON t.solicitante_id = s.id
            WHERE t.id = ?
            """;
        List<Licencia> licencias = new ArrayList<>();

        try (Connection conn = Conexion.getConection();
             PreparedStatement pstm = conn.prepareStatement(buscar)) {

            pstm.setInt(1, tramiteId);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                licencias.add(mapearLicencia(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar licencias por ID de trámite", e);
        }

        return licencias;
    }

    @Override
    public List<Licencia> listarLicencias() {
        String listar = """
            SELECT l.*, t.id as tramite_id, s.cedula, s.nombre as solicitante_nombre
            FROM licencias l
            JOIN tramites t ON l.tramite_id = t.id
            JOIN solicitantes s ON t.solicitante_id = s.id
            ORDER BY l.fecha_emision DESC
            """;
        List<Licencia> licencias = new ArrayList<>();

        try (Connection conn = Conexion.getConection();
             PreparedStatement pstm = conn.prepareStatement(listar);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                licencias.add(mapearLicencia(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar licencias", e);
        }

        return licencias;
    }

    @Override
    public List<Licencia> buscarPorFechaEmision(LocalDate fecha) {
        String buscar = """
            SELECT l.*, t.id as tramite_id, s.cedula, s.nombre as solicitante_nombre
            FROM licencias l
            JOIN tramites t ON l.tramite_id = t.id
            JOIN solicitantes s ON t.solicitante_id = s.id
            WHERE DATE(l.fecha_emision) = ?
            ORDER BY l.fecha_emision DESC
            """;
        List<Licencia> licencias = new ArrayList<>();

        try (Connection conn = Conexion.getConection();
             PreparedStatement pstm = conn.prepareStatement(buscar)) {

            pstm.setDate(1, Date.valueOf(fecha));
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                licencias.add(mapearLicencia(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar licencias por fecha de emisión", e);
        }

        return licencias;
    }

    @Override
    public List<Licencia> buscarPorFechaVencimiento(LocalDate fecha) {
        String buscar = """
            SELECT l.*, t.id as tramite_id, s.cedula, s.nombre as solicitante_nombre
            FROM licencias l
            JOIN tramites t ON l.tramite_id = t.id
            JOIN solicitantes s ON t.solicitante_id = s.id
            WHERE DATE(l.fecha_vencimiento) = ?
            ORDER BY l.fecha_vencimiento DESC
            """;
        List<Licencia> licencias = new ArrayList<>();

        try (Connection conn = Conexion.getConection();
             PreparedStatement pstm = conn.prepareStatement(buscar)) {

            pstm.setDate(1, Date.valueOf(fecha));
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                licencias.add(mapearLicencia(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar licencias por fecha de vencimiento", e);
        }

        return licencias;
    }

    @Override
    public boolean tieneLicenciaVigente(String cedula, TipoLicencia tipo) {
        String buscar = """
            SELECT COUNT(*) as count
            FROM licencias l
            JOIN tramites t ON l.tramite_id = t.id
            JOIN solicitantes s ON t.solicitante_id = s.id
            WHERE s.cedula = ? 
            AND l.tipo = ? 
            AND l.fecha_vencimiento >= CURRENT_DATE
            """;

        try (Connection conn = Conexion.getConection();
             PreparedStatement pstm = conn.prepareStatement(buscar)) {

            pstm.setString(1, cedula);
            pstm.setString(2, tipo.name());
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                return rs.getInt("count") > 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al verificar licencia vigente", e);
        }

        return false;
    }

    @Override
    public List<Licencia> buscarLicenciasVigentes() {
        String buscar = """
            SELECT l.*, t.id as tramite_id, s.cedula, s.nombre as solicitante_nombre
            FROM licencias l
            JOIN tramites t ON l.tramite_id = t.id
            JOIN solicitantes s ON t.solicitante_id = s.id
            WHERE l.fecha_vencimiento >= CURRENT_DATE
            ORDER BY l.fecha_vencimiento ASC
            """;
        List<Licencia> licencias = new ArrayList<>();

        try (Connection conn = Conexion.getConection();
             PreparedStatement pstm = conn.prepareStatement(buscar);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                licencias.add(mapearLicencia(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar licencias vigentes", e);
        }

        return licencias;
    }

    @Override
    public List<Licencia> buscarLicenciasVencidas() {
        String buscar = """
            SELECT l.*, t.id as tramite_id, s.cedula, s.nombre as solicitante_nombre
            FROM licencias l
            JOIN tramites t ON l.tramite_id = t.id
            JOIN solicitantes s ON t.solicitante_id = s.id
            WHERE l.fecha_vencimiento < CURRENT_DATE
            ORDER BY l.fecha_vencimiento DESC
            """;
        List<Licencia> licencias = new ArrayList<>();

        try (Connection conn = Conexion.getConection();
             PreparedStatement pstm = conn.prepareStatement(buscar);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                licencias.add(mapearLicencia(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar licencias vencidas", e);
        }

        return licencias;
    }

    private Licencia mapearLicencia(ResultSet rs) throws SQLException {
        Licencia licencia = new Licencia();
        licencia.setIdLicencia(rs.getInt("id"));
        licencia.setFechaEmision(rs.getDate("fecha_emision").toLocalDate());
        licencia.setFechaVencimiento(rs.getDate("fecha_vencimiento").toLocalDate());
        licencia.setTipo(TipoLicencia.valueOf(rs.getString("tipo")));

        // Crear y configurar trámite
        Tramite tramite = new Tramite();
        tramite.setIdTramite(rs.getInt("tramite_id"));

        // Crear y configurar solicitante
        Solicitante solicitante = new Solicitante();
        solicitante.setCedula(rs.getString("cedula"));
        solicitante.setNombreSolicitante(rs.getString("solicitante_nombre"));
        tramite.setSolicitante(solicitante);

        licencia.setTramite(tramite);
        return licencia;
    }
}