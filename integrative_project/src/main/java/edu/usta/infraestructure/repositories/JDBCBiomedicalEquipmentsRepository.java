package edu.usta.infraestructure.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import edu.usta.domain.entities.BiomedicalEquipments;
import edu.usta.domain.entities.Provider;
import edu.usta.domain.enums.EquipmentStatus;
import edu.usta.domain.enums.EquipmentType;
import edu.usta.infraestructure.db.DatabaseConnection;

public class JDBCBiomedicalEquipmentsRepository implements GenericRepository<BiomedicalEquipments> {

    private final DatabaseConnection db;
    private String ListBaseQuery = """
            SELECT* FROM BiomedicalEquipment
            
            bc.id AS bc_id,
            bc.risk_class AS bc_risk_class,
            bc.calibration_cert AS bc_calibration_cert,

              eq.id AS eq_id,
                     eq.serial AS eq_serial,
                     eq.brand AS eq_brand,
              eq.model AS eq_model,
                     eq.type AS eq_type,
                     eq.state AS eq_state,
              eq.image_path AS eq_image_path,

                     pr.id as pr_id,
                     pr.name as pr_name,
              pr.tax_id as pr_tax_id,
                     pr.contact_email as contact_email

              FROM tech_equipment as tc
              JOIN equipment AS eq ON eq.id = tc.id
              JOIN provider AS pr ON pr.id = eq.provider_id
            
            
            """;
private static final Map<String, String> ALLOWED_FIELDS = Map.of(
            "serial", "e.serial",
            "brand", "e.brand",
            "model", "p.name",
        "image.path", "p.contact_email"
        );
    public JDBCBiomedicalEquipmentsRepository(DatabaseConnection db) {
        this.db = db;
    }

    private BiomedicalEquipments map(ResultSet result) throws SQLException {
        Provider provider = new Provider(
                result.getObject("pr_id", String.class),
                result.getObject("pr_name", String.class),
                result.getObject("pr_tax_id", String.class),
                result.getObject("pr_contact_email", String.class));
        return new BiomedicalEquipments(
                result.getObject("eq_id", String.class),
                result.getObject("eq_serial", String.class),
                result.getObject("eq_model", String.class),
                result.getObject("eq_image_path", String.class),
                result.getObject("eq_brand", String.class),
                result.getObject("eq_type", EquipmentType.class),
                result.getObject("eq_state", EquipmentStatus.class),
                provider,
                result.getObject("bc_risk_class", String.class),
                result.getObject("bc_calibration_cert", String.class));

    }

    @Override
    public BiomedicalEquipments create(BiomedicalEquipments entity) {
        final String sqlQuery = "INSERT INTO equipment "
                + "(serial, brand, model, type, state, provider, imagePath) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?) "
                + "RETURNING id";

        try (
                Connection connection = db.getConnection(); PreparedStatement ps = connection.prepareStatement(sqlQuery)) {
            ps.setString(1, entity.getSerial());
            ps.setString(2, entity.getBrand());
            ps.setString(3, entity.getModel());
            ps.setString(4, entity.getType().toString());
            ps.setString(5, entity.getStatus().toString());
            ps.setString(6, entity.getProvider().getId());
            ps.setString(7, entity.getImagePath());

            try (ResultSet result = ps.executeQuery()) {
                if (result.next()) {
                    String id = result.getObject("id", String.class);

                    entity = createBiomedicalEquipments(id, entity);
                }
            } catch (Exception e) {
                throw new SQLException("No se generó ID", e);
            }

            return entity;
        } catch (SQLException error) {
            throw new RuntimeException("Error insertando el equipo: ", error);
        }
    }

    private BiomedicalEquipments createBiomedicalEquipments(String id, BiomedicalEquipments entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createBiomedicalEquipments'");
    }

    @Override
    public Optional<BiomedicalEquipments> findById(UUID id) {
        String ListBaseQuerySQL = null;
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
    public List<BiomedicalEquipments> findBy(String attribute, String value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<BiomedicalEquipments> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BiomedicalEquipments update(BiomedicalEquipments entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(UUID id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
