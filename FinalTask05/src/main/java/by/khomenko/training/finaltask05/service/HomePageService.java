package by.khomenko.training.finaltask05.service;

import by.khomenko.training.finaltask05.dao.BlackListDao;
import by.khomenko.training.finaltask05.dao.CategoryDao;
import by.khomenko.training.finaltask05.dao.mysql.BlackListDaoImpl;
import by.khomenko.training.finaltask05.dao.mysql.CategoryDaoImpl;
import by.khomenko.training.finaltask05.dao.pool.ConnectionPool;
import by.khomenko.training.finaltask05.entity.BlackList;
import by.khomenko.training.finaltask05.entity.Category;
import by.khomenko.training.finaltask05.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomePageService {

    public static final int CATEGORIES_PAGE_SIZE = 10;

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(HomePageService.class);

    public Map<String, Object> load(int page) {

        Map<String, Object> map = new HashMap<>();
        List<Category> categories = new ArrayList<>();
        CategoryDao categoryDao = new CategoryDaoImpl();

        try {
            categoryDao.setConnection( ConnectionPool.getInstance().getConnection());
            categories = categoryDao.readAll(page, CATEGORIES_PAGE_SIZE);
            int c = categoryDao.count();
            int pageCount = c/CATEGORIES_PAGE_SIZE + 1;
            map.put("pageCount", pageCount);
        } catch (PersistentException e) {
            //TODO Put appropriate message into log.
            LOGGER.error("Some message here. ", e);
        }

        map.put("categories", categories);

        return map;
    }
}
