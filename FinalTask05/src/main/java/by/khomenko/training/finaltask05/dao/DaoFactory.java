package by.khomenko.training.finaltask05.dao;

import by.khomenko.training.finaltask05.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DaoFactory {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(DaoFactory.class);

    private static DaoFactory instance = new DaoFactory();

    public static DaoFactory getInstance() {
        return instance;
    }

    private Map<Class<? extends Dao<?>>, Class> classes = new ConcurrentHashMap<>();

    public void init(String implementation) throws PersistentException{

        if ("mysql".equals(implementation)){

            classes.put(BlackListDao.class, by.khomenko.training.finaltask05.dao.mysql.BlackListDaoImpl.class);
            classes.put(CategoryDao.class, by.khomenko.training.finaltask05.dao.mysql.CategoryDaoImpl.class);
            classes.put(FavoriteDao.class, by.khomenko.training.finaltask05.dao.mysql.FavoriteDaoImpl.class);
            classes.put(ImageDao.class, by.khomenko.training.finaltask05.dao.mysql.ImageDaoImpl.class);
            classes.put(RecognizedImgDao.class, by.khomenko.training.finaltask05.dao.mysql.RecognizedImgDaoImpl.class);
            classes.put(SettingDao.class, by.khomenko.training.finaltask05.dao.mysql.SettingDaoImpl.class);
            classes.put(UserDao.class, by.khomenko.training.finaltask05.dao.mysql.UserDaoImpl.class);

        } else {
            throw new PersistentException("Implementation for database connection does not exist");
        }

    }

    @SuppressWarnings("unchecked")
    public <T extends Dao<?>> T createDao(Class<T> key) throws PersistentException {
        Class value = classes.get(key);
        if(value != null) {
            try {
                Object dao = value.newInstance();
                return (T)dao;
            } catch(InstantiationException | IllegalAccessException e) {
                LOGGER.error("It is impossible to create data access object", e);
                throw new PersistentException(e);
            }
        }
        return null;
    }
}
