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
                //TODO Put a proper message into the log.
                LOGGER.error("There is some message for table `categories`");
                throw new PersistentException();
            }
        } catch (SQLException e) {
            //TODO Put a proper message into the log.
            LOGGER.error("Other message for table `categories`");
            throw new PersistentException(e);
        }
    }

    @Override
    public Category read(Integer identity) throws PersistentException {
        return null;
    }

    @Override
    public void update(Category entity) throws PersistentException {

    }

    @Override
    public void delete(Integer identity) throws PersistentException {

    }
}
