package by.khomenko.training.finaltask05.service;

import by.khomenko.training.finaltask05.dao.BlackListDao;
import by.khomenko.training.finaltask05.dao.SettingDao;
import by.khomenko.training.finaltask05.dao.UserDao;
import by.khomenko.training.finaltask05.dao.mysql.BlackListDaoImpl;
import by.khomenko.training.finaltask05.dao.mysql.SettingDaoImpl;
import by.khomenko.training.finaltask05.dao.mysql.UserDaoImpl;
import by.khomenko.training.finaltask05.dao.pool.ConnectionPool;
import by.khomenko.training.finaltask05.entity.BlackList;
import by.khomenko.training.finaltask05.entity.Setting;
import by.khomenko.training.finaltask05.entity.User;
import by.khomenko.training.finaltask05.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminPageService {

    public static final int BLACKLIST_PAGE_SIZE = 10;

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(AdminPageService.class);

    public Map<String, Object> load() throws PersistentException {

        Map<String, Object> map = new HashMap<>();
        List<User> blacklist;
        SettingDao settingDao = new SettingDaoImpl();
        Map<String, String> settings = new HashMap<>();
        List<User> usersNotInBlacklist;
        UserDao userDao = new UserDaoImpl();

        try {
            userDao.setConnection(ConnectionPool.getInstance()
                    .getConnection());

            blacklist = userDao.readAllInBlacklist();
            usersNotInBlacklist = userDao.readAllNotInBlacklist();

            settingDao.setConnection(ConnectionPool.getInstance()
                    .getConnection());
            for (Setting s : settingDao.read()) {
                settings.put(s.getName(), s.getValue());
            }

        } catch (PersistentException e) {
            LOGGER.error("Loading blacklist and settings to admin page "
                    + "an exception occurred", e);
            throw new PersistentException(e);
        }

        map.put("settings", settings);
        map.put("blacklist", blacklist);
        map.put("usersNotInBlacklist", usersNotInBlacklist);

        return map;
    }

    public void addUserToBlacklist(String userId) throws PersistentException {

        BlackListDao blackListDao = new BlackListDaoImpl();

        try {
            if (userId != null) {
                blackListDao.setConnection(ConnectionPool.getInstance()
                        .getConnection());
                blackListDao.create(new BlackList(Integer.parseInt(userId)));
            }
        //TODO Free connections.
        } catch (PersistentException|NumberFormatException e) {
            LOGGER.error("During adding user into blacklist an "
                    + "exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    public void removeUserFromBlacklist(String userId)
            throws PersistentException {


        BlackListDao blackListDao = new BlackListDaoImpl();
        try {
            if (userId != null) {
            blackListDao.setConnection(ConnectionPool.getInstance()
                    .getConnection());
            blackListDao.delete(Integer.parseInt(userId));
        }

        } catch (PersistentException|NumberFormatException ex) {
            LOGGER.error("During removing user from blacklist an"
                    + " exception occurred. ", ex);
            throw new PersistentException(ex);
        }
    }

    public void setSettings(String fileSize, String fileExtensions)
            throws PersistentException {

        SettingDao settingDao = new SettingDaoImpl();

        try {
            settingDao.setConnection(ConnectionPool.getInstance()
                    .getConnection());
            settingDao.create(new Setting("fileSize", fileSize));
            settingDao.create(new Setting("fileExtensions",
                    fileExtensions));

        } catch (PersistentException ex) {
            LOGGER.error("During update settings an "
                    + "exception occurred. ", ex);
            throw new PersistentException(ex);
        }
    }
}
