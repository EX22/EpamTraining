package by.khomenko.training.finaltask05.service;

import by.khomenko.training.finaltask05.dao.CategoryDao;
import by.khomenko.training.finaltask05.dao.DaoFactory;
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

        try (UserDao userDao = DaoFactory.getInstance().createDao(UserDao.class);
             FavoriteDao favoriteDao = DaoFactory.getInstance().createDao(FavoriteDao.class);
             CategoryDao categoryDao = DaoFactory.getInstance().createDao(CategoryDao.class)) {

            User user = userDao.read(id);
            List<Favorite> favorites = favoriteDao.read(user);
            List<Category> categories = categoryDao.readAll();
            Set<Integer> favoriteIds = new HashSet<>();
            for (Favorite f : favorites) {
                favoriteIds.add(f.getCategoryId());
            }

            map.put("user", user);
            map.put("favorites", favorites);
            map.put("categories", categories);
            map.put("favoriteIds", favoriteIds);

        } catch (Exception e) {
            LOGGER.error("Loading user's profile an exception occurred.", e);
            throw new PersistentException(e);
        }

        return map;
    }

    public void updateProfile(Integer curUserId, String avatarFileName,
                              String userName, String curUserPass,
                              String newPass, String confirmPass)
            throws PersistentException, ValidationException {

        User loggedUser;

        try (UserDao userDao = DaoFactory.getInstance().createDao(UserDao.class)) {

            if ((avatarFileName != null)) {

                userDao.updateUserAvatar(curUserId,
                        ("avatars/" + avatarFileName));
            }


            if ((userName != null) && (!"".equals(userName))) {

                userDao.updateUserName(userName, curUserId);
            }

            if ((newPass != null) && (!"".equals(newPass))) {
                if (newPass.equals(confirmPass)) {


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

        } catch (ValidationException e) {
            LOGGER.error("Updating user's profile an "
                    + "exception occurred.", e);
            throw new ValidationException(e);
        } catch (Exception e) {
            LOGGER.error("Updating user's profile an "
                    + "exception occurred.", e);
            throw new PersistentException(e);
        }

    }

    public void saveFavorites(List<Favorite> favorites, Integer userId)
            throws PersistentException {


        try (FavoriteDao favoriteDao = DaoFactory.getInstance().createDao(FavoriteDao.class)) {

            favoriteDao.deleteAll(userId);
            for (Favorite favorite : favorites) {
                favoriteDao.create(favorite);
            }
        } catch (Exception e) {
            LOGGER.error("Saving favorite categories "
                    + "an exception occurred.", e);
            throw new PersistentException(e);
        }
    }
}
