package by.khomenko.training.finaltask05.dao.mysql;

import by.khomenko.training.finaltask05.dao.BlackListDao;
import by.khomenko.training.finaltask05.entity.BlackList;
import by.khomenko.training.finaltask05.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        //TODO Add user's id in query!
        String sql = "INSERT INTO blacklist (login) VALUES (?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, blackList.getUserLogin());
            statement.executeUpdate();


        } catch (SQLException e) {
            LOGGER.error("Creating blacklist entry "
                    + "an exception occurred. ", e);
            throw new PersistentException(e);
        }
        return 0;
    }

    @Override
    public BlackList read(Integer identity) throws PersistentException {
        return null;
    }

    @Override
    public void update(BlackList blackList) throws PersistentException {
        //TODO Add user's id in query!
        String sql = "UPDATE blacklist SET  login = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, blackList.getUserLogin());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Updating blacklist entry "
                    + "an exception occurred. ", e);
            throw new PersistentException(e);
        }

    }


    @Override
    public void delete(Integer identity) throws PersistentException {
        //TODO Add user's id in query!
        String sql = "DELETE FROM blacklist WHERE user_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, identity);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Deleting blacklist entry "
                    + "an exception occurred. ", e);
            throw new PersistentException(e);
        }

    }

    public void deleteBylogin(String login) throws PersistentException {
        //TODO Add user's id in query!
        String sql = "DELETE FROM blacklist WHERE login = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Deleting user from blacklist by login "
                    + "an exception occurred. ", e);
            throw new PersistentException(e);
        }

    }

    @Override
    public List<BlackList> readAll(int page, int pageSize)
            throws PersistentException {

        String sql = "SELECT * FROM blacklist LIMIT ? OFFSET ?";

        int offset = (page - 1) * pageSize;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, pageSize);
            statement.setInt(2, offset);

            List<BlackList> blackLists = new ArrayList<>();

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    BlackList blackList
                            = new BlackList(resultSet.getInt("user_id"),
                            resultSet.getString("login"));
                    blackLists.add(blackList);
                }
            }
            return blackLists;
        } catch (SQLException e) {
            LOGGER.error("Reading blacklist an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    @Override
    public int count() throws PersistentException {
        String sql = "SELECT COUNT(user_id) FROM blacklist";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            int c = 0;
            if (resultSet.next()) {
                c = resultSet.getInt(1);
            }
            return c;
        } catch (SQLException e) {
            LOGGER.error("Counting blacklist entries "
                    + "an Exception occurred", e);
            throw new PersistentException(e);
        }


    }
}
