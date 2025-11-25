package edu.usta.infraestructure.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import edu.usta.domain.entities.Person;
import edu.usta.domain.enums.Role;
import edu.usta.infraestructure.db.DatabaseConnection;

public class JDBCPersonRepository implements GenericRepository<Person> {

    private final DatabaseConnection db;
    private String ListBaseSqloQuery = "SELECT * FROM person";

    public JDBCPersonRepository(DatabaseConnection db) {
        this.db = db;
    }

    private Person map(ResultSet result) throws SQLException {
        return new Person(
                result.getObject("id", String.class),
                result.getObject("full_name", String.class),
                result.getObject("document", String.class),
                result.getObject("role", Role.class));

    }

    @Override
    public Person create(Person entity) {
        final String sqlQuery = "INSERT INTO Person"
                + "(full_name, document, role)"
                + "VALUES (?, ?, ?)"
                + "RETURNING id";
        try (
                Connection connection = db.getConnection(); PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setString(1, entity.getFullName());
            ps.setString(2, entity.getDocument());
            ps.setString(3, entity.getRole().toString());

            try (ResultSet result = ps.executeQuery()) {
                if (result.next()) {
                    String id = result.getObject("id", String.class);
                    entity = new Person(
                            id, entity.getFullName(), entity.getDocument(), entity.getRole());
                }
            } catch (Exception e) {
                throw new SQLException("No se genero ID", e);
            }

            return entity;

        } catch (SQLException error) {
            throw new RuntimeException("Error insertando al personal: ", error);
        }

    }

    @Override
    public Optional<Person> findById(UUID id) {
        String sql = ListBaseSqloQuery + "WHERE id = ?";
        try (
                Connection connection = db.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, id.toString());

            try (ResultSet result = ps.executeQuery()) {
                if (result.next()) {
                    return Optional.empty();
                }
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException("Error al consultar un equipo por el id: " + id, e);
        }

    }

    @Override
    public List<Person> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Person> findBy(String attribute, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Person update(Person entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
