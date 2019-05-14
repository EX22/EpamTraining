package by.khomenko.training.finaltask05.service;

import by.khomenko.training.finaltask05.dao.CategoryDao;
import by.khomenko.training.finaltask05.dao.FavoriteDao;
import by.khomenko.training.finaltask05.dao.UserDao;
import by.khomenko.training.finaltask05.dao.mysql.CategoryDaoImpl;
import by.khomenko.training.finaltask05.dao.mysql.FavoriteDaoImpl;
import by.khomenko.training.finaltask05.dao.mysql.UserDaoImpl;
import by.khomenko.training.finaltask05.dao.pool.ConnectionPool;
import by.khomenko.training.finaltask05.entity.Category;
import by.khomenko.training.finaltask05.entity.Favorite;
import by.khomenko.training.finaltask05.entity.User;
import by.khomenko.training.finaltask05.exception.PersistentException;
import by.khomenko.training.finaltask05.exception.ValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class ProfilePageService {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ProfilePageService.class);

    public Map<String, Object> loadProfile(Integer id)
            throws PersistentException {

        Map<String, Object> map = new HashMap<>();
        User user;
        List<Favorite> favorites;
        UserDao userDao = new UserDaoImpl();
        FavoriteDaoImpl favoriteDao = new FavoriteDaoImpl();
        CategoryDao categoryDao = new CategoryDaoImpl();
        List<Category> categories;
        Set<Integer> favoriteIds = new HashSet<>();

        try {
            userDao.setConnection(ConnectionPool.getInstance()
                    .getConnection());
            user = userDao.read(id);
            favoriteDao.setConnection(ConnectionPool.getInstance()
                    .getConnection());
            favorites = favoriteDao.read(user);
            categoryDao.setConnection(ConnectionPool.getInstance()
                    .getConnection());
            categories = categoryDao.readAll();
            for (Favorite f : favorites) {
                favoriteIds.add(f.getCategoryId());
            }

        } catch (PersistentException e) {
            LOGGER.error("Loading user's profile an exception occurred.", e);
            throw new PersistentException(e);
        }

        map.put("user", user);
        map.put("favorites", favorites);
        map.put("categories", categories);
        map.put("favoriteIds", favoriteIds);

        return map;
    }

    public void updateProfile(Integer curUserId,
                              String userName, String curUserPass,
                              String newPass, String confirmPass)
            throws PersistentException, ValidationException {

        UserDao userDao = new UserDaoImpl();
        User loggedUser;
        try {

            if ((userName != null) && (!"".equals(userName))) {
                userDao.setConnection(ConnectionPool.getInstance()
                        .getConnection());
                userDao.updateUserName(userName, curUserId);
            }

            if ((newPass != null) && (!"".equals(newPass))) {
                if (newPass.equals(confirmPass)) {

                    userDao.setConnection(ConnectionPool.getInstance()
                            .getConnection());
                    loggedUser = userDao.read(userDao.read(curUserId)
                            .getLogin(), curUserPass);


                    if (loggedUser != null) {
                        userDao.updateUserPass(curUserId, newPass);
                    } else {
                        throw new ValidationException("Current password is"
                                + " false.");
                    }

                } else {
                    throw new ValidationException("Password not equals to"
                            + " confirm password.");
                }
            }

        } catch (PersistentException e) {
            LOGGER.error("Updating user's profile an "
                    + "exception occurred.", e);
            throw new PersistentException(e);
        }

    }

    public void saveFavorites(List<Favorite> favorites, Integer userId)
            throws PersistentException {

        FavoriteDao favoriteDao = new FavoriteDaoImpl();
        try {
            favoriteDao.setConnection(ConnectionPool.getInstance()
                    .getConnection());
            favoriteDao.deleteAll(userId);
            for (Favorite favorite : favorites) {
                favoriteDao.create(favorite);
            }
        } catch (PersistentException e) {
            LOGGER.error("Saving favorite categories "
                    + "an exception occurred.", e);
            throw new PersistentException(e);
        }
    }
}
