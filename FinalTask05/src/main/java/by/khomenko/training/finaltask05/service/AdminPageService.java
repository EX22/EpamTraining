package by.khomenko.training.finaltask05.service;

import by.khomenko.training.finaltask05.dao.BlackListDao;
import by.khomenko.training.finaltask05.dao.SettingDao;
import by.khomenko.training.finaltask05.dao.mysql.BlackListDaoImpl;
import by.khomenko.training.finaltask05.dao.mysql.SettingDaoImpl;
import by.khomenko.training.finaltask05.dao.pool.ConnectionPool;
import by.khomenko.training.finaltask05.entity.BlackList;
import by.khomenko.training.finaltask05.entity.Setting;
import by.khomenko.training.finaltask05.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
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

    public Map<String, Object> load(int page) throws PersistentException {

        Map<String, Object> map = new HashMap<>();
        BlackListDao blackListDao = new BlackListDaoImpl();
        List<BlackList> blacklist;
        SettingDao settingDao = new SettingDaoImpl();
        List<Setting> settingsList;

        try {
            blackListDao.setConnection(ConnectionPool.getInstance().getConnection());
            blacklist = blackListDao.readAll(page, BLACKLIST_PAGE_SIZE);
            int c = blackListDao.count();
            int pageCount = c/BLACKLIST_PAGE_SIZE + 1;
            map.put("pageCount", pageCount);

            settingDao.setConnection(ConnectionPool.getInstance().getConnection());
            settingsList = settingDao.read(new Setting());

        } catch (PersistentException e) {
            LOGGER.error("Loading blacklist and settings to admin page an exception occurred", e);
            throw new PersistentException(e);
        }

        map.put("settingsList", settingsList);
        map.put("blacklist", blacklist);

        return map;
    }

    public void addUserToBlacklist(String login) throws PersistentException {
        if (login == null){
            return;
        }
        BlackListDao blackListDao = new BlackListDaoImpl();
        try {
            blackListDao.setConnection( ConnectionPool.getInstance().getConnection());
            blackListDao.create(new BlackList(login));

        } catch (PersistentException e) {
            LOGGER.error("During adding user into blacklist an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    public void removeUserFromBlacklist(String login) throws PersistentException {
        if (login == null){
            return;
        }
        BlackListDao blackListDao = new BlackListDaoImpl();
        try {
            blackListDao.setConnection( ConnectionPool.getInstance().getConnection());
            blackListDao.deleteBylogin(login);

        } catch (PersistentException ex) {
            LOGGER.error("During removing user from blacklist an exception occurred. ", ex);
            throw new PersistentException(ex);
        }
    }

    public void setSettings(String fileSize, String fileExtension) throws PersistentException {

        SettingDao settingDao = new SettingDaoImpl();

        try {
            settingDao.setConnection( ConnectionPool.getInstance().getConnection());
            settingDao.create(new Setting("File size", fileSize));
            settingDao.create(new Setting("File extension", fileExtension));

        } catch (PersistentException ex) {
            LOGGER.error("During removing user from blacklist an exception occurred. ", ex);
            throw new PersistentException(ex);
        }
    }
}
