package by.khomenko.training.finaltask05.dao;

import by.khomenko.training.finaltask05.entity.Category;
import by.khomenko.training.finaltask05.exception.PersistentException;

import java.util.List;

public interface CategoryDao extends Dao<Category> {
    List<Category> readAll(int page, int pageSize) throws PersistentException;

    int count() throws PersistentException;
}
