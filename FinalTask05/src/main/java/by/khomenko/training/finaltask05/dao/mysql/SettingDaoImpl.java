package by.khomenko.training.finaltask05.dao.mysql;

import by.khomenko.training.finaltask05.dao.SettingDao;
import by.khomenko.training.finaltask05.entity.Setting;
import by.khomenko.training.finaltask05.exception.PersistentException;

public class SettingDaoImpl extends BaseDaoImpl implements SettingDao {
    @Override
    public Integer create(Setting entity) throws PersistentException {
        return null;
    }

    @Override
    public Setting read(Integer identity) throws PersistentException {
        return null;
    }

    @Override
    public void update(Setting entity) throws PersistentException {

    }

    @Override
    public void delete(Integer identity) throws PersistentException {

    }
}
