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

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(AdminPageService.class);

    public Map<String, Object> load() throws PersistentException {

        Map<String, Object> map = new HashMap<>();

        try (SettingDao settingDao = new SettingDaoImpl();
             UserDao userDao = new UserDaoImpl()) {


            List<User> blacklist = userDao.readAllInBlacklist();
            List<User> usersNotInBlacklist = userDao.readAllNotInBlacklist();

            Map<String, String> settings = new HashMap<>();
            for (Setting s : settingDao.read()) {
                settings.put(s.getName(), s.getValue());
            }

            map.put("settings", settings);
            map.put("blacklist", blacklist);
            map.put("usersNotInBlacklist", usersNotInBlacklist);

        } catch (Exception e) {
            LOGGER.error("Loading blacklist and settings to admin page "
                    + "an exception occurred", e);
            throw new PersistentException(e);
        }

        return map;
    }

    public void addUserToBlacklist(String userId) throws PersistentException {


        try (BlackListDao blackListDao = new BlackListDaoImpl()) {
            if (userId != null) {

                blackListDao.create(new BlackList(Integer.parseInt(userId)));
            }

        } catch (Exception e) {
            LOGGER.error("During adding user into blacklist an "
                    + "exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    public void removeUserFromBlacklist(String userId)
            throws PersistentException {

        try (BlackListDao blackListDao = new BlackListDaoImpl()) {

            if (userId != null) {

                blackListDao.delete(Integer.parseInt(userId));
            }

        } catch (Exception ex) {
            LOGGER.error("During removing user from blacklist an"
                    + " exception occurred. ", ex);
            throw new PersistentException(ex);
        }
    }

    public void setSettings(String fileSize, String fileExtensions, String filesLocation)
            throws PersistentException {


        try (SettingDao settingDao = new SettingDaoImpl()) {

            if(fileSize != null && (!"".equals(fileSize))) {

                settingDao.update(new Setting("fileSize", fileSize));
            }
            if (fileExtensions != null && (!"".equals(fileExtensions))) {

                settingDao.update(new Setting("fileExtensions",
                        fileExtensions));
            }

            if (filesLocation != null && (!"".equals(filesLocation))) {

                settingDao.update(new Setting("filesLocation",
                        filesLocation));
            }

        } catch (Exception ex) {
            LOGGER.error("During update settings an "
                    + "exception occurred. ", ex);
            throw new PersistentException(ex);
        }
    }
}
