package by.khomenko.training.finaltask05.service;

import by.khomenko.training.finaltask05.dao.CategoryDao;
import by.khomenko.training.finaltask05.dao.ImageDao;
import by.khomenko.training.finaltask05.dao.RecognizedImgDao;
import by.khomenko.training.finaltask05.dao.mysql.CategoryDaoImpl;
import by.khomenko.training.finaltask05.dao.mysql.ImageDaoImpl;
import by.khomenko.training.finaltask05.dao.mysql.RecognizedImgDaoImpl;
import by.khomenko.training.finaltask05.dao.pool.ConnectionPool;
import by.khomenko.training.finaltask05.entity.Category;
import by.khomenko.training.finaltask05.entity.Image;
import by.khomenko.training.finaltask05.entity.RecognizedImg;
import by.khomenko.training.finaltask05.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryPageService {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(CategoryPageService.class);

    public Map<String, Object> load(Integer userId, Integer categoryId)
            throws PersistentException {

        Map<String, Object> map = new HashMap<>();
        List<Image> images;
        ImageDao imageDao = new ImageDaoImpl();
        CategoryDao categoryDao = new CategoryDaoImpl();
        Category category;

        try {
            imageDao.setConnection(ConnectionPool.getInstance()
                    .getConnection());
            images = imageDao.readAll(userId, categoryId, 1, 10);
            categoryDao.setConnection(ConnectionPool.getInstance()
                    .getConnection());
            category = categoryDao.read(categoryId);
        } catch (PersistentException e) {
            LOGGER.error("Loading category images "
                    + "an exception occurred. ", e);
            throw new PersistentException(e);
        }

        map.put("images", images);
        map.put("category", category);

        return map;
    }

    public void saveRecognizedImages(List<RecognizedImg> recognizedImgList)
            throws PersistentException {

        RecognizedImgDao recognizedImgDao = new RecognizedImgDaoImpl();
        try {
            recognizedImgDao.setConnection(ConnectionPool.getInstance()
                    .getConnection());
            for (RecognizedImg recognizedImg : recognizedImgList) {
                recognizedImgDao.create(recognizedImg);
            }
        } catch (PersistentException e) {
            LOGGER.error("Saving recognized images an "
                    + "exception occurred. ", e);
            throw new PersistentException(e);
        }
    }


}
