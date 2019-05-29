package by.khomenko.training.finaltask05.dao;

import by.khomenko.training.finaltask05.entity.BlackList;
import by.khomenko.training.finaltask05.entity.User;
import by.khomenko.training.finaltask05.exception.PersistentException;

import java.util.List;

public interface BlackListDao extends Dao<BlackList> {


    List<BlackList> readAll(int page, int pageSize) throws PersistentException;

    int count() throws PersistentException;
    boolean isUserInBlacklist(User user)
            throws PersistentException;
}
