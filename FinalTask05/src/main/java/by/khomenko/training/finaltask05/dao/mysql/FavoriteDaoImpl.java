package by.khomenko.training.finaltask05.dao.mysql;

import by.khomenko.training.finaltask05.dao.FavoriteDao;
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

public class FavoriteDaoImpl extends BaseDaoImpl<Favorite> implements FavoriteDao {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(FavoriteDaoImpl.class);

    public FavoriteDaoImpl() throws PersistentException {
    }

    @Override
    public Integer create(Favorite favorite) throws PersistentException {

        String sql = "INSERT INTO favorites (user_id,"
                + " category_id) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, favorite.getUserId());
            statement.setInt(2, favorite.getCategoryId());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Creating favorite category "
                    + "an exception occurred. ", e);
            throw new PersistentException(e);
        }
        return 0;
    }


    @Override
    public Favorite read(Integer identity) {
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

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, user.getId());
            List<Favorite> favoriteList = new ArrayList<>();

            try (ResultSet resultSet = statement.executeQuery()) {

                Favorite favorite;
                while (resultSet.next()) {

                    favorite = new Favorite();
                    favorite.setUserId(user.getId());
                    favorite.setCategoryId(resultSet.getInt("category_id"));
                    favoriteList.add(favorite);
                }
            }
            return favoriteList;
        } catch (SQLException e) {
            LOGGER.error("Reading from table `favorites`"
                    + " an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    public void delete(Integer userId, Integer categoryId)
            throws PersistentException {

        String sql = "DELETE FROM favorites WHERE user_id = ?"
                + " AND category_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            statement.setInt(2, categoryId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Deleting from table `favorites`"
                    + " an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    public void deleteAll(Integer userId)
            throws PersistentException {

        String sql = "DELETE FROM favorites WHERE user_id = ?";


        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Deleting from table `favorites`"
                    + " an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

}
