package by.khomenko.training.finaltask05.dao;

import by.khomenko.training.finaltask05.entity.BlackList;
import by.khomenko.training.finaltask05.exception.PersistentException;

import java.util.List;

public interface BlackListDao extends Dao<BlackList> {

    void deleteBylogin(String login) throws PersistentException;

    List<BlackList> readAll(int page, int pageSize) throws PersistentException;

    int count() throws PersistentException;
}
