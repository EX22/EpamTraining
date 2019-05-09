package by.khomenko.training.finaltask05.dao;

import by.khomenko.training.finaltask05.entity.Setting;
import by.khomenko.training.finaltask05.exception.PersistentException;

import java.util.List;

public interface SettingDao extends Dao<Setting> {

    List<Setting> read(Setting settingVal) throws PersistentException;
}
