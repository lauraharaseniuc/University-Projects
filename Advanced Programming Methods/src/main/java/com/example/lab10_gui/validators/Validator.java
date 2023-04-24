package com.example.lab10_gui.validators;


import com.example.lab10_gui.exceptions.ValidationError;

public interface Validator<T>{
    /**
     * Verifies if a given entity is valid
     * @param entity should not be null
     * @throws ValidationError if the given entity is null or is not valid
     */
    void validate(T entity) throws ValidationError;
}
