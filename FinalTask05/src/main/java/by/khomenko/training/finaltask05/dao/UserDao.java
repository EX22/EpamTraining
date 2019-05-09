package by.khomenko.training.finaltask05.dao;

import by.khomenko.training.finaltask05.entity.User;
import by.khomenko.training.finaltask05.exception.PersistentException;

public interface UserDao extends Dao<User> {

    User read(String login, String password) throws PersistentException;

    boolean isUserExist(String login) throws PersistentException;

    void updateUserName(String userName, Integer userId) throws PersistentException;

    void updateUserPass(Integer curUserId, String newPass) throws PersistentException;
}
