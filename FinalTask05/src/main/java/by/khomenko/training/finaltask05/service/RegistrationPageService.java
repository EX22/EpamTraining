package by.khomenko.training.finaltask05.service;

import by.khomenko.training.finaltask05.dao.UserDao;
import by.khomenko.training.finaltask05.dao.mysql.FavoriteDaoImpl;
import by.khomenko.training.finaltask05.dao.mysql.UserDaoImpl;
import by.khomenko.training.finaltask05.dao.pool.ConnectionPool;
import by.khomenko.training.finaltask05.entity.Favorite;
import by.khomenko.training.finaltask05.entity.User;
import by.khomenko.training.finaltask05.exception.PersistentException;
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


    public void createUser(User user) {

        Map<String, Object> map = new HashMap<>();

        UserDao userDao = new UserDaoImpl();


        try {
            userDao.setConnection( ConnectionPool.getInstance().getConnection());
            userDao.create(user);

        } catch (PersistentException e) {
            //TODO Put appropriate message into log.
            LOGGER.error("Some message here. ", e);
        }

        map.put("user", user);


    }

}
