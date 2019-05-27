package by.khomenko.training.finaltask05.service;

import by.khomenko.training.finaltask05.dao.CategoryDao;
import by.khomenko.training.finaltask05.dao.ImageDao;
import by.khomenko.training.finaltask05.dao.RecognizedImgDao;
import by.khomenko.training.finaltask05.dao.UserDao;
import by.khomenko.training.finaltask05.dao.mysql.CategoryDaoImpl;
import by.khomenko.training.finaltask05.dao.mysql.ImageDaoImpl;
import by.khomenko.training.finaltask05.dao.mysql.RecognizedImgDaoImpl;
import by.khomenko.training.finaltask05.dao.mysql.UserDaoImpl;
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

        try (ImageDao imageDao = new ImageDaoImpl();
             CategoryDao categoryDao = new CategoryDaoImpl()) {

            List<Image> images = imageDao.readAll(userId, categoryId, 1, 3);
            Category category = categoryDao.read(categoryId);

            map.put("images", images);
            map.put("category", category);

        } catch (Exception e) {
            LOGGER.error("Loading category images "
                    + "an exception occurred. ", e);
            throw new PersistentException(e);
        }

        return map;
    }

    public void saveRecognizedImages(List<RecognizedImg> recognizedImgList, Integer userId)
            throws PersistentException {


        try (RecognizedImgDao recognizedImgDao = new RecognizedImgDaoImpl();
             UserDao userDao = new UserDaoImpl()) {

            userDao.increaseUserLevel(userId, recognizedImgList.size());
            for (RecognizedImg recognizedImg : recognizedImgList) {
                recognizedImgDao.create(recognizedImg);
            }
        } catch (Exception e) {
            LOGGER.error("Saving recognized images an "
                    + "exception occurred. ", e);
            throw new PersistentException(e);
        }
    }


}
