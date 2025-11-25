package edu.usta.infraestructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GenericRepository<Entity> {

    // Create 
    Entity create(Entity entity);

    // Read
    Optional<Entity> findById(UUID id);

    List<Entity> findAll();

    List<Entity> findBy(String attribute, String value);

    //Update
    Entity update(Entity entity);

    // Delete
    boolean delete(UUID id);

}
