package by.khomenko.training.finaltask05.service;

import by.khomenko.training.finaltask05.dao.UserDao;
import by.khomenko.training.finaltask05.dao.mysql.UserDaoImpl;
import by.khomenko.training.finaltask05.dao.pool.ConnectionPool;
import by.khomenko.training.finaltask05.entity.User;
import by.khomenko.training.finaltask05.exception.PersistentException;
import by.khomenko.training.finaltask05.exception.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistrationPageService {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(RegistrationPageService.class);


    public Integer createUser(String login, String pass, String confirmPass)
            throws ValidationException, PersistentException {

        Integer userId;

        try (UserDao userDao = new UserDaoImpl()) {

            if (!pass.equals(confirmPass)) {
                throw new ValidationException("Password and confirmPassword"
                        + " are not equal");
            }

            if (userDao.isUserExist(login)) {
                throw new ValidationException("User already exists");
            }
            User user = new User();
            user.setLogin(login);
            user.setPassword(pass);
            userId = userDao.create(user);

        } catch (ValidationException e) {
            LOGGER.error("Creating user an exception occurred. ", e);
            throw new ValidationException(e);
        }catch (Exception e) {
            LOGGER.error("Creating user an exception occurred. ", e);
            throw new PersistentException(e);
        }

        return userId;

    }

}
