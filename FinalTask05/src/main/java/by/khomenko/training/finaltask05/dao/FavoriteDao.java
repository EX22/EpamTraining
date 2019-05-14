package by.khomenko.training.finaltask05.dao;

import by.khomenko.training.finaltask05.entity.Favorite;
import by.khomenko.training.finaltask05.exception.PersistentException;

public interface FavoriteDao extends Dao<Favorite> {

    void deleteAll(Integer userId)
            throws PersistentException;
}
