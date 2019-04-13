package by.khomenko.training.finaltask05.dao;

import by.khomenko.training.finaltask05.entity.Entity;
import by.khomenko.training.finaltask05.exception.PersistentException;

public interface Dao<Type extends Entity> {
    Integer create(Type entity) throws PersistentException;

    Type read(Integer identity) throws PersistentException;

    void update(Type entity) throws PersistentException;

    void delete(Integer identity) throws PersistentException;
}

