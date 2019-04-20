package by.khomenko.training.finaltask05.dao.mysql;

import by.khomenko.training.finaltask05.dao.BlackListDao;
import by.khomenko.training.finaltask05.entity.BlackList;
import by.khomenko.training.finaltask05.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BlackListDaoImpl extends BaseDaoImpl implements BlackListDao {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(BlackListDaoImpl.class);

    @Override
    public Integer create(BlackList blackList) throws PersistentException {
        String sql = "INSERT INTO blacklist (user_login) VALUES (?)";
        //TODO Find out if it the proper way to use try with resources.
        try (PreparedStatement statement = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
             ResultSet resultSet = statement.getGeneratedKeys()) {

            statement.setString(1, blackList.getUser_login());
            statement.executeUpdate();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                //TODO Put a proper message into the log.
                LOGGER.error("There is some message");
                throw new PersistentException();
            }
        } catch (SQLException e) {
            //TODO Should exception thrown here be different from the else block above?
            LOGGER.error("Some message");
            throw new PersistentException(e);
        }
    }

    @Override
    public BlackList read(Integer identity) throws PersistentException {
        return null;
    }

    @Override
    public void update(BlackList blackList) throws PersistentException {

        String sql = "UPDATE black_list SET login = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, blackList.getUser_login());

            statement.executeUpdate();
        } catch (SQLException e) {
            //TODO Put a proper message into the log.
            LOGGER.error("Some message here");
            throw new PersistentException(e);
        }

    }


    @Override
    public void delete(Integer identity) throws PersistentException {

        String sql = "DELETE FROM black_list WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, identity);
            statement.executeUpdate();
        } catch (SQLException e) {
            //TODO Put a proper message into the log.
            LOGGER.error("Deleting blacklist entry exception occurred", e);
            throw new PersistentException(e);
        }

    }
}
