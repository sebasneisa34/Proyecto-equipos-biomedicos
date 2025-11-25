package edu.usta.application.usecases;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import edu.usta.domain.repositories.BaseRepository;

public class GenericUseCases<Entity> {

    private final BaseRepository<Entity> repository;

    public GenericUseCases(BaseRepository<Entity> repository) {
        this.repository = repository;
    }

    public Entity create(Entity entity) {
        return this.repository.create(entity);
    }

    public Optional<Entity> findById(UUID id) {
        return this.findById(id);
    }

    public List<Entity> findAll() {
        return this.findAll();
    }

    public List<Entity> findBy(String attribute, String value) {
        return this.repository.findBy(attribute, value);
    }

    public Entity uptate(Entity entity) {
        return this.uptate(entity);
    }

    public boolean delete(UUID id) {
        return this.delete(id);
    }
}
