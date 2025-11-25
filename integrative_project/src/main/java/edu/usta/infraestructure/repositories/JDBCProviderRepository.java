package edu.usta.infraestructure.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import edu.usta.domain.entities.Provider;
import edu.usta.infraestructure.db.DatabaseConnection;

public class JDBCProviderRepository implements GenericRepository<Provider> {

    private final DatabaseConnection db;
    private String ListBaseSqloQuery = """
         SELECT* FROM provider
        """;

    public JDBCProviderRepository(DatabaseConnection db) {
        this.db = db;
    }

    private Provider map(ResultSet result) throws SQLException {
        return new Provider(
                result.getObject("name", String.class),
                result.getObject("contact_email", String.class));
    }

    @Override
    public Provider create(Provider entity) {
        final String sqlQuery = "INSERT INTO equipment"
                + "(name, contact_email)"
                + "VALUES (?, ?)"
                + "RETURNING id";
        try (
                Connection connection = db.getConnection(); PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getContactEmail());

            try (ResultSet result = ps.executeQuery()) {
                if (result.next()) {
                    String id = result.getObject("id", String.class);
                    entity = new Provider(
                            id, entity.getName(), entity.getContactEmail(), id);
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
    public Optional<Provider> findById(UUID id) {
        String sql = ListBaseSqloQuery + "WHERE name = ?";
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
            throw new RuntimeException("Error al consultar el nombre y el contacto de email: " + e);
        }
    }


    @Override
    public List<Provider> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }


    @Override
    public List<Provider> findBy(String attribute, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Provider update(Provider entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
