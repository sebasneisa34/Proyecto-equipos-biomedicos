package edu.usta.infraestructure.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import edu.usta.domain.entities.Equipment;
import edu.usta.domain.entities.Provider;
import edu.usta.domain.enums.EquipmentStatus;
import edu.usta.domain.enums.EquipmentType;
import edu.usta.infraestructure.db.DatabaseConnection;

public class JDBCEquipmentRepository implements GenericRepository<Equipment> {

    private final DatabaseConnection db;
    private String ListBaseQuerySQL = """
                    SELECT
                     eq.id AS eq_id,
                     eq.serial AS eq_serial,
                     eq.brand AS eq_brand,
                     eq.model AS eq_model,
                     eq.type AS eq_type,
                     eq.state AS eq_state,
                     eq.image_path AS eq_image_path                 
                     pr.id AS pr_id,
                     pr.name AS pr_name,
                     pr.tax_id AS pr_tax_id
                     pr.contact_email AS pr_contact_email,     
            FROM quipment AS eq
            JOIN provider AS pr ON pr.id = eq.provider_id
            
    """;
    
            
    public JDBCEquipmentRepository(DatabaseConnection db) {
        this.db = db;
    }

    private Equipment map(ResultSet result) throws SQLException {
        Provider provider = new Provider(
                result.getObject("pr_id", String.class),
                result.getObject("pr_name", String.class),
                result.getObject("pr_tax_id", String.class),
                result.getObject("pr_contact_email", String.class));
        return new Equipment(
                result.getObject("eq_id", String.class),
                result.getObject("eq_serial", String.class),
                result.getObject("eq_model", String.class),
                result.getObject("eq_image_path", String.class),
                result.getObject("eq_brand", String.class),
                result.getObject("eq_type", EquipmentType.class),
                result.getObject("eq_status", EquipmentStatus.class),
                null);
    }

    @Override
    public Equipment create(Equipment entity) {
        final String sqlQuery = "INSERT INTO equipment"
                + "(serial, model, state, imagePath, brand, type, provider)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)"
                + "RETURNING id";
        try (
                Connection connection = db.getConnection(); PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setString(1, entity.getSerial());
            ps.setString(2, entity.getModel());
            ps.setString(3, entity.getImagePath());
            ps.setString(4, entity.getType().toString());
            ps.setString(5, entity.getBrand());
            ps.setString(6, entity.getProvider().getId());
            ps.setString(7, entity.getStatus().toString());

            try (ResultSet result = ps.executeQuery()) {
                if (result.next()) {
                    String id = result.getObject("id", String.class);
                    entity = new Equipment(
                            id, entity.getSerial(), entity.getModel(), entity.getImagePath(),
                            entity.getBrand(),
                            entity.getType(), entity.getStatus(),
                            entity.getProvider());
                }
            } catch (Exception e) {
                throw new SQLException("No se genero ID", e);
            }

            return entity;

        } catch (SQLException error) {
            throw new RuntimeException("Error insertando el equipo: ", error);
        }

    }

    @Override
    public Optional<Equipment> findById(UUID id) {
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

    public List<Equipment> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public boolean delete(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<Equipment> findBy(String attribute, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Equipment update(Equipment entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
