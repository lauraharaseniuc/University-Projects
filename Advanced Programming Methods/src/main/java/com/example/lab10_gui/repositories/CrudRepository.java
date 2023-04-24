package com.example.lab10_gui.repositories;


import com.example.lab10_gui.entities.Entity;

public interface CrudRepository<ID, E extends Entity<ID>> {
    /**
     * Adds the given entity to the repository
     * @param entity should not be null
     * @return null, if the entity was successfully added
     *          the given entity, if it already exists in the repository
     * @throws IllegalArgumentException if the given entity refers null
     */
    E add(E entity);

    /**
     * deletes the entity with the given id from the repository
     * @param id should not be null
     * @return the removed entity, if there is one with the given id
     *          null, if the given id does not refer an entity from the repository
     * @throws IllegalArgumentException if the given id refers null
     */
    E delete(ID id);

    /**
     * Returns all entities in the repository
     * @return all entities
     */
    Iterable<E> getAllEntities();
}
