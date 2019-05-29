package by.khomenko.training.finaltask05.service;

import by.khomenko.training.finaltask05.dao.CategoryDao;
import by.khomenko.training.finaltask05.dao.DaoFactory;
import by.khomenko.training.finaltask05.dao.ImageDao;
import by.khomenko.training.finaltask05.dao.mysql.CategoryDaoImpl;
import by.khomenko.training.finaltask05.dao.mysql.ImageDaoImpl;
import by.khomenko.training.finaltask05.dao.pool.ConnectionPool;
import by.khomenko.training.finaltask05.entity.Category;
import by.khomenko.training.finaltask05.entity.Image;
import by.khomenko.training.finaltask05.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private static final Integer IMAGESPERPAGE = 3;

    public Map<String, Object> load(Integer userId, Integer pageNumber) throws PersistentException {

        Map<String, Object> map = new HashMap<>();

        try (ImageDao imageDao = DaoFactory.getInstance().createDao(ImageDao.class);
             CategoryDao categoryDao = DaoFactory.getInstance().createDao(CategoryDao.class)) {

            List<Image> images = imageDao.readUserImages(userId, pageNumber, IMAGESPERPAGE);
            List<Category> categories = categoryDao.readAll();
            Integer imgCount = imageDao.countUserImages(userId);
            Integer pageCount = imgCount/IMAGESPERPAGE
                    + ((imgCount%IMAGESPERPAGE > 0) ? 1 : 0);

            map.put("images", images);
            map.put("categories", categories);
            map.put("pageCount", pageCount);
            map.put("page", pageNumber);

        } catch (Exception e) {
            LOGGER.error("Loading my images an exception occurred. ", e);
            throw new PersistentException(e);
        }

        return map;
    }

    public void updateImgCategories(List<Image> addedImageList)
            throws PersistentException {


        try(ImageDao imageDao = DaoFactory.getInstance().createDao(ImageDao.class)){

            imageDao.updateImageCategories(addedImageList);

        } catch (Exception e) {
            LOGGER.error("Updating image categories an "
                    + "exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    public void addImage(Image image) throws PersistentException {


        try(ImageDao imageDao = DaoFactory.getInstance().createDao(ImageDao.class)) {

            imageDao.create(image);

        } catch (Exception e) {
            LOGGER.error("Adding image categories an "
                    + "exception occurred. ", e);
            throw new PersistentException(e);
        }
    }
}
