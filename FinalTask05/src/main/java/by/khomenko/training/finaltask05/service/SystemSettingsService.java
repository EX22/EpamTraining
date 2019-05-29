package by.khomenko.training.finaltask05.service;

import by.khomenko.training.finaltask05.dao.DaoFactory;
import by.khomenko.training.finaltask05.dao.SettingDao;
import by.khomenko.training.finaltask05.entity.Setting;
import by.khomenko.training.finaltask05.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;

public class SystemSettingsService {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(RegistrationPageService.class);

    public String getSetting(String setting) throws PersistentException {

        try (SettingDao settingDao = DaoFactory.getInstance().createDao(SettingDao.class)) {

            List<Setting> settingList = settingDao.read();
            for (Setting s : settingList){
                if (s.getName().equals(setting)){
                    return s.getValue();
                }
            }
        } catch (Exception e) {
            LOGGER.error("Getting settings an exception occurred. ", e);
            throw new PersistentException(e);
        }

        return "";
    }
}
