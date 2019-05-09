package by.khomenko.training.finaltask05.dao;

import by.khomenko.training.finaltask05.entity.Image;
import by.khomenko.training.finaltask05.exception.PersistentException;

import java.util.List;

public interface ImageDao extends Dao<Image> {

    List<Image> readAll(Integer userId, Integer categoryId, int page, int pageSize) throws PersistentException;

    int count() throws PersistentException;

    void updateImageCategories(List<Image> imageList) throws PersistentException;

    List<Image> readUserImages(Integer userId) throws PersistentException;
}
