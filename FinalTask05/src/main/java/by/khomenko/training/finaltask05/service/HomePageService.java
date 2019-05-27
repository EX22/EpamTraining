package by.khomenko.training.finaltask05.service;

import by.khomenko.training.finaltask05.dao.CategoryDao;
import by.khomenko.training.finaltask05.dao.mysql.CategoryDaoImpl;
import by.khomenko.training.finaltask05.dao.pool.ConnectionPool;
import by.khomenko.training.finaltask05.entity.Category;
import by.khomenko.training.finaltask05.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePageService {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(HomePageService.class);

    public Map<String, Object> load() throws PersistentException {

        Map<String, Object> map = new HashMap<>();

        try (CategoryDao categoryDao = new CategoryDaoImpl()) {

            List<Category> categories = categoryDao.readAll();

            map.put("categories", categories);

        } catch (Exception e) {
            LOGGER.error("Loading home page an exception occurred.", e);
            throw new PersistentException(e);
        }

        return map;
    }
}
