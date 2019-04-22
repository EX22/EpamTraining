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
import java.util.ArrayList;
import java.util.List;

public class BlackListDaoImpl extends BaseDaoImpl implements BlackListDao {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(BlackListDaoImpl.class);

    @Override
    public Integer create(BlackList blackList) throws PersistentException {
        String sql = "INSERT INTO blacklist (login) VALUES (?)";
        //TODO Find out if it the proper way to use try with resources.
        try (PreparedStatement statement = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
             ResultSet resultSet = statement.getGeneratedKeys()) {

            statement.setString(1, blackList.getUserLogin());
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

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, blackList.getUserLogin());

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

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, identity);
            statement.executeUpdate();
        } catch (SQLException e) {
            //TODO Put a proper message into the log.
            LOGGER.error("Deleting blacklist entry exception occurred", e);
            throw new PersistentException(e);
        }

    }

    @Override
    public List<BlackList> readAll(int page, int pageSize) throws PersistentException {
        String sql = "SELECT * FROM blacklist LIMIT ? OFFSET ?";
        int limit = pageSize;
        int offset = (page - 1) * pageSize;
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            resultSet = statement.executeQuery();
            List<BlackList> blackLists = new ArrayList<>();
            while (resultSet.next()) {
                BlackList blackList = new BlackList(resultSet.getString(
                        "login"));
                blackLists.add(blackList);
            }
            return blackLists;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                //TODO Add logger
            }

        }
    }

    @Override
    public int count() throws PersistentException {
        String sql = "SELECT COUNT(id) FROM blacklist";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            int c = 0;
            if (resultSet.next()) {
                c = resultSet.getInt(1);
            }
            return c;
        } catch (SQLException e) {
            throw new PersistentException(e);
        }


    }
}
