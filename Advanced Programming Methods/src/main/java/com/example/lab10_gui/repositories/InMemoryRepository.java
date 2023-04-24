package com.example.lab10_gui.repositories;

import com.example.lab10_gui.entities.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class InMemoryRepository<ID,E extends Entity<ID>> implements CrudRepository<ID,E> {
    protected Map<ID,E> entities;
    protected Map<ID,E> deleted_entities;
    public InMemoryRepository() {
        entities=new HashMap<>();
        deleted_entities=new HashMap<>();
    }

    /**
     * Adds a new entity in the repository
     * @param entity should not be null
     * @return null, if the entity was successfully added
     *         the given entity, if it already exists in the repository
     * @throws IllegalArgumentException if the given entity refers null
     */
    @Override
    public E add(E entity) {
        if (entity==null) {
            throw new IllegalArgumentException("Can not add a null entity!");
        }

        E found=this.entities.get(entity.getId());
        if (found!=null) {
            return found;
        }
        this.entities.put(entity.getId(), entity);
        return null;
    }

    /**
     * deletes the entity with the given id from the repository
     * @param id should not be null
     * @return the removed entity, if there is one with the given id
     *          null, if the given id does not refer an entity from the repository
     * @throws IllegalArgumentException if the given id refers null
     */
    @Override
    public E delete(ID id) {
        if (id==null) {
            throw new IllegalArgumentException("Can not delete an entity with null id!");
        }

        E removed_entity= this.entities.remove(id);
        if (removed_entity!=null) {
            this.deleted_entities.put(removed_entity.getId(),removed_entity);
        }
        return removed_entity;
    }

    @Override
    public Iterable<E> getAllEntities() {
        return null;
    }

    /**
     * returns all the entities in the repository
     * @return all entities
     */
    public Set<Map.Entry<ID, E>> getAll() {
        return this.entities.entrySet();
    }

    public E getEntity (ID id) {
        if (id==null) {
            throw new IllegalArgumentException("Can not search for an entity with a null id!");
        }
        return this.entities.get(id);
    }

}
