package Modelo.Dao;

import Modelo.Clases.Solicitante;
import Modelo.Clases.TipoLicencia;
import Modelo.Repositorio.Conexion;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolicitanteJDBCDAO implements SolicitanteDAO {

    @Override
    public void crearSolicitante(Solicitante solicitante) {
        String crear = "INSERT INTO solicitantes(cedula, nombre, fecha_nacimiento, tipo_licencia) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexion.getConection();
             PreparedStatement pstm = conn.prepareStatement(crear, Statement.RETURN_GENERATED_KEYS)) {

            pstm.setString(1, solicitante.getCedula());
            pstm.setString(2, solicitante.getNombreSolicitante());
            pstm.setDate(3, Date.valueOf(solicitante.getFechaNacimiento()));
            pstm.setString(4, solicitante.getTipoLicencia().name());

            pstm.executeUpdate();

            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                solicitante.setIdSolicitante(rs.getInt(1));
            }

            JOptionPane.showMessageDialog(null, "Solicitante creado con exito");

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear solicitante", e);
        }
    }

    @Override
    public Solicitante buscarPorCedula(String cedula) {
        String buscar = "SELECT * FROM solicitantes WHERE cedula = ?";
        Solicitante s = new Solicitante();

        try (Connection conn = Conexion.getConection();
             PreparedStatement pstm = conn.prepareStatement(buscar)) {

            pstm.setString(1, cedula);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                s.setIdSolicitante(rs.getInt("id"));
                s.setCedula(rs.getString("cedula"));
                s.setNombreSolicitante(rs.getString("nombre"));
                s.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                s.setTipoLicencia(TipoLicencia.valueOf(rs.getString("tipo_licencia")));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar solicitante por cedula", e);
        }

        return s;
    }

    @Override
    public Solicitante buscarPorId(int id) {
        String buscar = "SELECT * FROM solicitantes WHERE id = ?";
        Solicitante s = new Solicitante();

        try (Connection conn = Conexion.getConection();
             PreparedStatement pstm = conn.prepareStatement(buscar)) {

            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                s.setIdSolicitante(rs.getInt("id"));
                s.setCedula(rs.getString("cedula"));
                s.setNombreSolicitante(rs.getString("nombre"));
                s.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                s.setTipoLicencia(TipoLicencia.valueOf(rs.getString("tipo_licencia")));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar solicitante por id", e);
        }

        return s;
    }

    @Override
    public List<Solicitante> listarSolicitantes() {
        String listar = "SELECT * FROM solicitantes ORDER BY nombre";
        List<Solicitante> lista = new ArrayList<>();

        try (Connection conn = Conexion.getConection();
             PreparedStatement pstm = conn.prepareStatement(listar)) {

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Solicitante s = new Solicitante();
                s.setIdSolicitante(rs.getInt("id"));
                s.setCedula(rs.getString("cedula"));
                s.setNombreSolicitante(rs.getString("nombre"));
                s.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                s.setTipoLicencia(TipoLicencia.valueOf(rs.getString("tipo_licencia")));

                lista.add(s);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar solicitantes", e);
        }
        return lista;
    }

    @Override
    public void actualizarSolicitante(Solicitante solicitante) {
        String actualizar = "UPDATE solicitantes SET nombre = ?, fecha_nacimiento = ?, tipo_licencia = ? WHERE cedula = ?";

        try (Connection conn = Conexion.getConection();
             PreparedStatement pstm = conn.prepareStatement(actualizar)) {

            pstm.setString(1, solicitante.getNombreSolicitante());
            pstm.setDate(2, Date.valueOf(solicitante.getFechaNacimiento()));
            pstm.setString(3, solicitante.getTipoLicencia().name());
            pstm.setString(4, solicitante.getCedula());

            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar solicitante", e);
        }
    }

    @Override
    public List<Solicitante> buscarPorNombre(String nombre) {
        return List.of();
    }

    @Override
    public List<Solicitante> buscarPorTipoLicencia(TipoLicencia tipo) {
        return List.of();
    }
}