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
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(UserDaoImpl.class);


    @Override
    public Integer create(User user) throws PersistentException {

        String sql = "INSERT INTO users (login, password, role)"
                + " VALUES (?, MD5(?), ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getRole().getIdentity());
            statement.executeUpdate();

            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    LOGGER.error("There is no autoincremented index after"
                            + " trying to add record into table `users`");
                    throw new PersistentException();
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Creating user an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    @Override
    public User read(Integer identity) throws PersistentException {

        String sql = "SELECT * FROM users WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, identity);
            User user = null;

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setName(resultSet.getString("name"));
                    user.setId(identity);
                    user.setPhotoPath(resultSet.getString("photo_path"));
                    user.setLevel(resultSet.getInt("level"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(Role.getByIdentity(resultSet.getInt("role")));

                }
            }
            return user;
        } catch (SQLException e) {
            LOGGER.error("Reading table `users` an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    @Override
    public void update(User user) throws PersistentException {

        String sql = "UPDATE users SET login = ?, password = ?, role = ?"
                + " WHERE id = ?";

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

    public void updateUserName(String userName, Integer userId)
            throws PersistentException {

        String sql = "UPDATE users SET name = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, userName);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Updating user's name an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    public void updateUserPass(Integer curUserId, String newPass)
            throws PersistentException {

        String sql = "UPDATE users SET password = MD5(?) WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, newPass);
            statement.setInt(2, curUserId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Updating user's password "
                    + "an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    public void updateUserAvatar(Integer curUserId, String newPhotoPath)
            throws PersistentException {

        String sql = "UPDATE users SET photo_path = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, newPhotoPath);
            statement.setInt(2, curUserId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Updating user's avatar "
                    + "an exception occurred. ", e);
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

        String sql = "SELECT id, role FROM users"
                + " WHERE login = ? AND password = MD5(?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, login);
            statement.setString(2, password);
            User user = null;

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setLogin(login);
                    user.setRole(Role.getByIdentity(resultSet.getInt("role")));

                }
            }
            return user;
        } catch (SQLException e) {
            LOGGER.error("Reading user by login and password"
                    + " an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    public List<User> readAllNotInBlacklist() throws PersistentException {

        String sql = "SELECT * FROM users WHERE role <> 0 AND id NOT IN (SELECT user_id FROM blacklist)";

        List<User> users = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {

                    User user = new User();
                    user.setName(resultSet.getString("name"));
                    user.setId(resultSet.getInt("id"));
                    user.setPhotoPath(resultSet.getString("photo_path"));
                    user.setLevel(resultSet.getInt("level"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(Role.getByIdentity(resultSet.getInt("role")));
                    users.add(user);
                }
            }
            return users;
        } catch (SQLException e) {
            LOGGER.error("Reading users not in blacklist"
                    + " an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    public List<User> readAllInBlacklist() throws PersistentException {

        String sql = "SELECT * FROM users WHERE role <> 0 AND id IN (SELECT user_id FROM blacklist)";

        List<User> users = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {

                    User user = new User();
                    user.setName(resultSet.getString("name"));
                    user.setId(resultSet.getInt("id"));
                    user.setPhotoPath(resultSet.getString("photo_path"));
                    user.setLevel(resultSet.getInt("level"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(Role.getByIdentity(resultSet.getInt("role")));
                    users.add(user);
                }
            }
            return users;
        } catch (SQLException e) {
            LOGGER.error("Reading users not in blacklist"
                    + " an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }


    public boolean isUserExist(String login) throws PersistentException {
        String sql = "SELECT id FROM users WHERE login = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, login);

            try (ResultSet resultSet = statement.executeQuery()) {

                return resultSet.next();
            }
        } catch (SQLException e) {
            LOGGER.error("Checking if user exists"
                    + " an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }
}
