package by.khomenko.training.finaltask05.dao.mysql;

import by.khomenko.training.finaltask05.dao.FavoriteDao;
import by.khomenko.training.finaltask05.entity.Category;
import by.khomenko.training.finaltask05.entity.Favorite;
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

public class FavoruteDaoImpl extends BaseDaoImpl implements FavoriteDao {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(FavoruteDaoImpl.class);

    @Override
    public Integer create(Favorite favorite) throws PersistentException {
        String sql = "INSERT INTO favorites (user_id,"
                + " category_id) VALUES (?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        //TODO Possibly to use try with resources here.
        try {
            statement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, favorite.getUserId());
            statement.setInt(2, favorite.getCategoryId());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                LOGGER.error("There is no autoincremented index after "
                        + "trying to add record into table `favorites`");
                throw new PersistentException();
            }
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                //TODO What should be inside this catch statement?
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                //TODO What should be inside this catch statement?
            }
        }
    }


    @Override
    public Favorite read(Integer identity) {
        //TODO Find out what can be returned here instead of null, if possible
        // and necessary.
        return null;
    }

    @Override
    public void update(Favorite entity) {
    }

    @Override
    public void delete(Integer identity) {
    }

    public List<Favorite> read(User user) throws PersistentException {

        String sql = "SELECT category_id"
                + " FROM favorites WHERE user_id = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        //TODO Possibly to use try with resources here.
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getIdentity());
            resultSet = statement.executeQuery();
            List<Favorite> favoriteList = new ArrayList<>();
            Favorite favorite;
            while (resultSet.next()) {
                Category category = new Category();
                category.setIdentity(resultSet.getInt("category_id"));
                favorite = new Favorite();
                favorite.setUserId(user.getIdentity());
                favorite.setCategoryId(category.getIdentity());
                favoriteList.add(favorite);
            }
            return favoriteList;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    public void delete(Integer userId, Integer categoryId)
            throws PersistentException {

        String sql = "DELETE FROM favorites WHERE user_id = ?"
                + " AND category_id = ?";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setInt(2, categoryId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

}
