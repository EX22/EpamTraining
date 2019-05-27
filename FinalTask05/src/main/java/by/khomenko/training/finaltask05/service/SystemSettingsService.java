package by.khomenko.training.finaltask05.service;

import by.khomenko.training.finaltask05.dao.SettingDao;
import by.khomenko.training.finaltask05.dao.mysql.SettingDaoImpl;
import by.khomenko.training.finaltask05.entity.Setting;


import java.util.List;

public class SystemSettingsService {

    public String getSetting(String setting){


        try (SettingDao settingDao = new SettingDaoImpl()) {

            List<Setting> settingList = settingDao.read();
            for (Setting s : settingList){
                if (s.getName().equals(setting)){
                    return s.getValue();
                }
            }
        } catch (Exception e) {
            //TODO Add logger;
            e.printStackTrace();
        }

        return "";
    }
}
