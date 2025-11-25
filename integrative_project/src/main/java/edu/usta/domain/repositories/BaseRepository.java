package edu.usta.domain.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import edu.usta.domain.entities.TechEquipment;

public interface BaseRepository<Entity> {
    //Create
    Entity create(Entity entity);

    //Read
    Optional<Entity> findById(UUID id);

    Optional<Entity> findByserial(String serial);

    List<Entity> findAll();

    

    //Uptate
    Entity uptate(Entity equipment);

    //Delete
    boolean delete(UUID id);

    TechEquipment update(TechEquipment entity);

    List<Entity> findBy(String attribute, String value);
}
