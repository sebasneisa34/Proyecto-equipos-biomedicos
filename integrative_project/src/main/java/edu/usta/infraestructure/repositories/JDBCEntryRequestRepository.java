package edu.usta.infraestructure.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import edu.usta.domain.entities.EntryRequest;
import edu.usta.infraestructure.db.DatabaseConnection;

public class JDBCEntryRequestRepository implements GenericRepository<EntryRequest> {

    private final DatabaseConnection db;
    private String ListBaseQuerySQL = """
            SELECT* FROM
            eq.equipment AS eq_equipment
            eq.internalResponsible AS eq_internal_responsible
            eq.requester AS eq_requester
            """;

    public JDBCEntryRequestRepository(DatabaseConnection db) {
        this.db = db;
    }

    private EntryRequest map(ResultSet result) throws SQLException {
        return new EntryRequest();
    }

    @Override
    public EntryRequest create(EntryRequest entity) {
        final String sqlQuery = "INSERT INTO equipment"
                + "(equipment, internalResponsible, requester)"
                + "VALUES (?, ?, ?)"
                + "RETURNING id";
        try (
                Connection connection = db.getConnection(); PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setString(1, entity.getEquipment().getId());
            ps.setString(2, entity.getInternalResponsible().getId());
            ps.setString(3, entity.getRequester().getId());

            try (ResultSet result = ps.executeQuery()) {
                if (result.next()) {
                    String id = result.getObject("id", String.class);
                    entity = new EntryRequest(
                            id, entity.getEquipment(), entity.getInternalResponsible(),
                            entity.getRequester());

                }
            } catch (Exception e) {
                throw new SQLException("No se genero ID", e);
            }

            return entity;

        } catch (SQLException error) {
            throw new RuntimeException("Error al consultar el equipo: ", error);
        }

    }

    @Override
    public Optional<EntryRequest> findById(UUID id) {
        String sql = ListBaseQuerySQL + "WHERE eq.id = ?";

        try (
                Connection connection = db.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, id.toString());

            try (ResultSet result = ps.executeQuery()) {
                if (result.next()) {
                    return Optional.of(map(result));
                }
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar un equipo por el id: " + id, e);
        }
    }
    @Override
    public List<EntryRequest> findBy(String attribute, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<EntryRequest> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EntryRequest update(EntryRequest entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
