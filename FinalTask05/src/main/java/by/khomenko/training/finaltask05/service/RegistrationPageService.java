package by.khomenko.training.finaltask05.service;

import by.khomenko.training.finaltask05.dao.UserDao;
import by.khomenko.training.finaltask05.dao.mysql.FavoriteDaoImpl;
import by.khomenko.training.finaltask05.dao.mysql.UserDaoImpl;
import by.khomenko.training.finaltask05.dao.pool.ConnectionPool;
import by.khomenko.training.finaltask05.entity.Favorite;
import by.khomenko.training.finaltask05.entity.User;
import by.khomenko.training.finaltask05.exception.PersistentException;
import by.khomenko.training.finaltask05.exception.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationPageService {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(RegistrationPageService.class);


    public Integer createUser(String login, String pass, String confirmPass) throws ValidationException, PersistentException {

        UserDao userDao = new UserDaoImpl();

        Integer userId;
        try {
            if (!pass.equals(confirmPass)) {
                throw new ValidationException("Password and confirmPassword are not equal");
            }
            userDao.setConnection(ConnectionPool.getInstance().getConnection());
            if (userDao.isUserExist(login)) {
                throw new ValidationException("User already exists");
            }
            User user = new User();
            user.setLogin(login);
            user.setPassword(pass);
            userId = userDao.create(user);

        } catch (PersistentException e) {
            LOGGER.error("Creating user an exception occurred. ", e);
            throw new PersistentException(e);
        }

        return userId;


    }

}
