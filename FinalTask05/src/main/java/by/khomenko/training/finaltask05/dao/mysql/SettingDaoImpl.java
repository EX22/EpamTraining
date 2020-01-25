package by.khomenko.training.finaltask05.dao.mysql;

import by.khomenko.training.finaltask05.dao.SettingDao;
import by.khomenko.training.finaltask05.entity.Setting;
import by.khomenko.training.finaltask05.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SettingDaoImpl extends BaseDaoImpl<Setting> implements SettingDao {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(SettingDaoImpl.class);

    public SettingDaoImpl() throws PersistentException {
    }

    @Override
    public Integer create(Setting setting) throws PersistentException {

        String sql = "INSERT INTO settings (settings_name, settings_value) "
                + "VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, setting.getName());
            statement.setString(2, setting.getValue());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Creating setting an exception occurred. ", e);
            throw new PersistentException(e);
        }
        return 0;
    }

    @Override
    public Setting read(Integer identity) {

        return null;
    }

    public List<Setting> read() throws PersistentException {

        String sql = "SELECT settings_name, settings_value FROM settings";


        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            List<Setting> settingList = new ArrayList<>();

            try (ResultSet resultSet = statement.executeQuery()) {

                Setting setting;
                while (resultSet.next()) {
                    setting = new Setting();
                    setting.setName(resultSet.getString("settings_name"));
                    setting.setValue(resultSet.getString("settings_value"));
                    settingList.add(setting);
                }
            }
            return settingList;
        } catch (SQLException e) {
            LOGGER.error("Reading table `users` an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }


    @Override
    public void update(Setting setting) throws PersistentException {

        String sql = "UPDATE settings SET settings_value = ?"
                + " WHERE settings_name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, setting.getValue());
            statement.setString(2, setting.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Updating table `settings` "
                    + "an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    @Override
    public void delete(Integer identity)  {

    }
}
