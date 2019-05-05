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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfilePageService {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ProfilePageService.class);

    public Map<String, Object> load(Integer id) {

        Map<String, Object> map = new HashMap<>();
        User user = new User();
        List<Favorite> favorites = new ArrayList<>();
        UserDao userDao = new UserDaoImpl();
        FavoriteDaoImpl favoriteDao = new FavoriteDaoImpl();

        try {
            userDao.setConnection( ConnectionPool.getInstance().getConnection());
            user = userDao.read(id);
            favoriteDao.setConnection( ConnectionPool.getInstance().getConnection());
            favorites = favoriteDao.read(user);
        } catch (PersistentException e) {
            //TODO Put appropriate message into log.
            LOGGER.error("Some message here. ", e);
        }

        map.put("user", user);
        map.put("favorites", favorites);

        return map;
    }

}
