package by.khomenko.training.finaltask05.dao.mysql;

import by.khomenko.training.finaltask05.dao.ImageDao;
import by.khomenko.training.finaltask05.entity.Image;
import by.khomenko.training.finaltask05.exception.PersistentException;

public class ImageDaoImpl extends BaseDaoImpl implements ImageDao {
    @Override
    public Integer create(Image entity) throws PersistentException {
        return null;
    }

    @Override
    public Image read(Integer identity) throws PersistentException {
        return null;
    }

    @Override
    public void update(Image entity) throws PersistentException {

    }

    @Override
    public void delete(Integer identity) throws PersistentException {

    }
}
