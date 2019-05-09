package by.khomenko.training.finaltask05.service;

import by.khomenko.training.finaltask05.dao.UserDao;
import by.khomenko.training.finaltask05.dao.mysql.UserDaoImpl;
import by.khomenko.training.finaltask05.dao.pool.ConnectionPool;
import by.khomenko.training.finaltask05.entity.User;
import by.khomenko.training.finaltask05.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogInPageService {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(AdminPageService.class);

    public User logInUser(String login, String password) {

        UserDao userDao = new UserDaoImpl();
        User loggedUser = null;
        try {
            userDao.setConnection(ConnectionPool.getInstance().getConnection());

            loggedUser = userDao.read(login, password);

        } catch (PersistentException e) {
            LOGGER.error("Checking user in `users` table an exception occurred. ", e);
        }

        return loggedUser;
    }
}
