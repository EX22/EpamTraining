package by.khomenko.training.finaltask05.dao.mysql;

import by.khomenko.training.finaltask05.dao.CategoryDao;
import by.khomenko.training.finaltask05.entity.Category;
import by.khomenko.training.finaltask05.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl extends BaseDaoImpl implements CategoryDao {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(CategoryDaoImpl.class);

    @Override
    public Integer create(Category category) throws PersistentException {

        String sql = "INSERT INTO categories (name, image_path) VALUES (?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys()) {

            statement.setString(1, category.getName());
            statement.setString(2, category.getImagePath());
            statement.executeUpdate();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                LOGGER.error("There is no autoincremented index after"
                        + " trying to add record into table `categories`");
                throw new PersistentException();
            }
        } catch (SQLException e) {
            LOGGER.error("Creating category an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    @Override
    public Category read(Integer identity) throws PersistentException {

        String sql = "SELECT name, image_path, question FROM categories WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            statement.setInt(1, identity);

            Category category = null;
            if (resultSet.next()) {
                category = new Category(identity,
                        resultSet.getString("name"),
                        resultSet.getString("image_path"),
                        resultSet.getString("question"));
            }
            return category;
        } catch (SQLException e) {
            LOGGER.error("Reading category an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    @Override
    public void update(Category category) throws PersistentException {

        String sql = "UPDATE categories SET name = ?, image_path = ?,"
                + " question = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, category.getName());
            statement.setString(2, category.getImagePath());
            statement.setString(3, category.getQuestion());
            statement.setInt(4, category.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Updating category an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    @Override
    public void delete(Integer identity) throws PersistentException {

        String sql = "DELETE FROM categories WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, identity);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Deleting category an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    public List<Category> readAll(int page, int pageSize) throws PersistentException {
        String sql = "SELECT * FROM categories LIMIT ? OFFSET ?";
        int limit = pageSize;
        int offset = (page - 1) * pageSize;
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            resultSet = statement.executeQuery();
            List<Category> categories = new ArrayList<>();
            while (resultSet.next()) {
                Category category
                        = new Category(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("image_path"),
                        resultSet.getString("question"));
                categories.add(category);
            }
            return categories;
        } catch (SQLException e) {
            LOGGER.error("Reading blacklist an exception occurred. ", e);
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException ex) {
                //TODO Put appropriate message into log.
                LOGGER.error("Exception occurred", ex);
            }

        }
    }

    @Override
    public int count() throws PersistentException {
        String sql = "SELECT COUNT(id) FROM categories";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            int c = 0;
            if (resultSet.next()) {
                c = resultSet.getInt(1);
            }
            return c;
        } catch (SQLException e) {
            //TODO Put appropriate message into log.
            LOGGER.error("Exception occurred", e);
            throw new PersistentException(e);
        }

    }
}
