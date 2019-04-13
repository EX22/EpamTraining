package by.khomenko.training.finaltask05.dao.mysql;

import by.khomenko.training.finaltask05.dao.UserDao;
import by.khomenko.training.finaltask05.entity.User;
import by.khomenko.training.finaltask05.exception.PersistentException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {


    public Integer create(User entity) {
        return null;
    }

    public User read(Integer identity) {
        return null;
    }

    public void update(User entity) {

    }

    public void delete(Integer identity) {

    }

    public User read(String login, String password) throws PersistentException {
        String sql = "SELECT identity FROM users WHERE login = ? AND password = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            User user = null;
            if(resultSet.next()) {
                user = new User();
                user.setIdentity(resultSet.getInt("identity"));
                user.setLogin(login);
                user.setPassword(password);

            }
            return user;
        } catch(SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }
    }
}
