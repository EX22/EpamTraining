package by.khomenko.training.finaltask05.dao.mysql;

import by.khomenko.training.finaltask05.dao.SettingDao;
import by.khomenko.training.finaltask05.entity.Setting;
import by.khomenko.training.finaltask05.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SettingDaoImpl extends BaseDaoImpl implements SettingDao {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(SettingDaoImpl.class);

    @Override
    public Integer create(Setting setting) throws PersistentException {

        String sql = "INSERT INTO settings (settings_name, settings_value) VALUES (?, ?)";

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
    public Setting read(Integer identity) throws PersistentException {
        //TODO There's no need in this method implementation as far as there's no id in settings table.
        return null;
    }

    public Setting read(Setting settingVal) throws PersistentException {

        String sql = "SELECT settings_value FROM settings WHERE settings_name = ?";


        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            statement.setString(1, settingVal.getName());

            Setting setting = null;
            if (resultSet.next()) {
                setting = new Setting();
                setting.setName(settingVal.getName());
                setting.setValue(resultSet.getString("settings_value"));
            }
            return setting;
        } catch (SQLException e) {
            LOGGER.error("Reading table `users` an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    @Override
    public void update(Setting setting) throws PersistentException {

        String sql = "UPDATE settings SET settings_value = ? WHERE settings_name = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, setting.getValue());
            statement.setString(2, setting.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Updating table `settings` an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    @Override
    public void delete(Integer identity) throws PersistentException {
    //TODO Find out whether this method needed to be implemented.
    }
}
