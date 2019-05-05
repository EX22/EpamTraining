package by.khomenko.training.finaltask05.dao.mysql;

import by.khomenko.training.finaltask05.dao.ImageDao;
import by.khomenko.training.finaltask05.entity.Image;
import by.khomenko.training.finaltask05.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ImageDaoImpl extends BaseDaoImpl implements ImageDao {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ImageDaoImpl.class);

    @Override
    public Integer create(Image image) throws PersistentException {

        String sql = "INSERT INTO images (path, user_id, category_id) VALUES (?, ?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys()) {

            statement.setString(1, image.getPath());
            statement.setInt(2, image.getUserId());
            statement.setInt(3, image.getCategoryId());
            statement.executeUpdate();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                LOGGER.error("There is no autoincremented index after"
                        + " trying to add record into table `images`");
                throw new PersistentException();
            }
        } catch (SQLException e) {
            LOGGER.error("Creating image an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    @Override
    public Image read(Integer identity) throws PersistentException {
        //TODO Find out why it underline comma in sql query.
        String sql = "SELECT path, user_id, category_id FROM images WHERE id = ?";


        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            statement.setInt(1, identity);

            Image image = null;
            if (resultSet.next()) {
                image = new Image();
                image.setId(identity);
                image.setPath(resultSet.getString("path"));
                image.setUserId(resultSet.getInt("user_id"));
                image.setCategoryId(resultSet.getInt("category_id"));
            }
            return image;
        } catch (SQLException e) {
            LOGGER.error("Reading from table `images` an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    //TODO Find out whether this method is needed to be implemented.
    @Override
    public void update(Image image) throws PersistentException {

        String sql = "UPDATE images SET path = ?, user_id = ?, category_id = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, image.getPath());
            statement.setInt(2, image.getUserId());
            statement.setInt(3, image.getCategoryId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Updating table `images` an exception occurred. ", e);
            throw new PersistentException(e);
        }

    }

    @Override
    public void delete(Integer identity) throws PersistentException {

        String sql = "DELETE FROM images WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, identity);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Deleting image an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    @Override
    public List<Image> readAll(Integer userId, Integer categoryId, int page, int pageSize) throws PersistentException {
        String sql = "SELECT id, path " +
                " FROM images " +
                " WHERE category_id = ? AND user_id <> ? " +
                " AND id NOT IN " +
                " ( " +
                " SELECT im.id " +
                " FROM images AS im " +
                " JOIN recognizedimgs AS re " +
                " ON im.id = re.image_id " +
                " WHERE im.category_id = ? " +
                " AND re.user_id = ?) LIMIT ? OFFSET ?";
        int limit = pageSize;
        int offset = (page - 1) * pageSize;
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryId);
            statement.setInt(2, userId);
            statement.setInt(3, categoryId);
            statement.setInt(4, userId);
            statement.setInt(5, limit);
            statement.setInt(6, offset);
            resultSet = statement.executeQuery();
            List<Image> images = new ArrayList<>();
            while (resultSet.next()) {
                Image image = new Image();
                image.setId(resultSet.getInt("id"));
                image.setUserId(userId);
                image.setPath(resultSet.getString("path"));
                image.setCategoryId(categoryId);

                images.add(image);
            }
            return images;
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
        String sql = "SELECT COUNT(id) FROM images";

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
