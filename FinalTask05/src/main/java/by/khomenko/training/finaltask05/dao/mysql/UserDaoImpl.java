package by.khomenko.training.finaltask05.dao.mysql;

import by.khomenko.training.finaltask05.dao.UserDao;
import by.khomenko.training.finaltask05.entity.Role;
import by.khomenko.training.finaltask05.entity.User;
import by.khomenko.training.finaltask05.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(UserDaoImpl.class);


    @Override
    public Integer create(User user) throws PersistentException {

        String sql = "INSERT INTO users (login, password, role) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
             ResultSet resultSet = statement.getGeneratedKeys()) {

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRole().getIdentity());
            statement.executeUpdate();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                LOGGER.error("There is no autoincremented index after"
                        + " trying to add record into table `users`");
                throw new PersistentException();
            }
        } catch (SQLException e) {
            LOGGER.error("Creating user an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    @Override
    public User read(Integer identity) throws PersistentException {

        String sql = "SELECT login, password, role FROM users WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            statement.setInt(1, identity);

            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(identity);
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.getByIdentity(resultSet.getInt("role")));

            }
            return user;
        } catch (SQLException e) {
            LOGGER.error("Reading table `users` an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    @Override
    public void update(User user) throws PersistentException {

        String sql = "UPDATE users SET login = ?, password = ?, role = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRole().getIdentity());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Updating user an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    @Override
    public void delete(Integer identity) throws PersistentException {

        String sql = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, identity);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Deleting user an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    public User read(String login, String password) throws PersistentException {

        String sql = "SELECT id, role FROM users WHERE login = ? AND password = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            statement.setString(1, login);
            statement.setString(2, password);

            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(login);
                user.setPassword(password);
                user.setRole(Role.getByIdentity(resultSet.getInt("role")));

            }
            return user;
        } catch (SQLException e) {
            LOGGER.error("Reading user by login and password"
                    + " an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }
}
