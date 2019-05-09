package by.khomenko.training.finaltask05.service;

import by.khomenko.training.finaltask05.dao.CategoryDao;
import by.khomenko.training.finaltask05.dao.ImageDao;
import by.khomenko.training.finaltask05.dao.mysql.CategoryDaoImpl;
import by.khomenko.training.finaltask05.dao.mysql.ImageDaoImpl;
import by.khomenko.training.finaltask05.dao.pool.ConnectionPool;
import by.khomenko.training.finaltask05.entity.Category;
import by.khomenko.training.finaltask05.entity.Image;
import by.khomenko.training.finaltask05.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyImagesPageService {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(MyImagesPageService.class);

    public Map<String, Object> load(Integer userId, int categoryId) {
        Map<String, Object> map = new HashMap<>();
        List<Image> images = new ArrayList<>();
        ImageDao imageDao = new ImageDaoImpl();
        CategoryDao categoryDao = new CategoryDaoImpl();
        List<Category> categories = new ArrayList<>();

        try {
            imageDao.setConnection(ConnectionPool.getInstance().getConnection());
            images = imageDao.readUserImages(userId);
            categoryDao.setConnection(ConnectionPool.getInstance().getConnection());
            categories = categoryDao.readAll(1, categoryDao.count());

        } catch (PersistentException e) {
            LOGGER.error("Loading my images an exception occurred. ", e);
        }

        map.put("images", images);
        map.put("categories", categories);

        return map;
    }

    public void updateImgCategories(List<Image> addedImageList){

        ImageDao imageDao = new ImageDaoImpl();
        try {
            imageDao.setConnection(ConnectionPool.getInstance().getConnection());
            imageDao.updateImageCategories(addedImageList);
        } catch (PersistentException e) {
            LOGGER.error("Saving added images an exception occurred. ", e);
        }
    }
}
