package by.khomenko.training.finaltask05.dao.mysql;

import by.khomenko.training.finaltask05.dao.RecognizedImgDao;
import by.khomenko.training.finaltask05.entity.RecognizedImg;
import by.khomenko.training.finaltask05.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RecognizedImgDaoImpl extends BaseDaoImpl<RecognizedImg>
        implements RecognizedImgDao {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(RecognizedImgDaoImpl.class);

    public RecognizedImgDaoImpl() throws PersistentException {
    }

    @Override
    public Integer create(RecognizedImg recognizedImg)
            throws PersistentException {

        String sql = "INSERT INTO recognizedimgs (image_id, user_id, answer)"
                + " VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, recognizedImg.getImageId());
            statement.setInt(2, recognizedImg.getUserId());
            statement.setString(3, recognizedImg.getAnswer());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Creating user an exception occurred. ", e);
            throw new PersistentException(e);
        }
        return 0;
    }

    @Override
    public RecognizedImg read(Integer identity) throws PersistentException {

        String sql = "SELECT user_id, answer FROM recognizedimgs WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, identity);
            RecognizedImg recognizedImg = null;

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    recognizedImg = new RecognizedImg();
                    recognizedImg.setUserId(resultSet.getInt("user_id"));
                    recognizedImg.setAnswer(resultSet.getString("answer"));
                }
            }
            return recognizedImg;
        } catch (SQLException e) {
            LOGGER.error("Reading from table `recognizedimgs` "
                    + "an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }

    @Override
    public void update(RecognizedImg recognizedImg) throws PersistentException {

    }

    @Override
    public void delete(Integer identity) throws PersistentException {

        String sql = "DELETE FROM recognizedimgs WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, identity);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Deleting from table `recognizedimgs`"
                    + " an exception occurred. ", e);
            throw new PersistentException(e);
        }
    }
}
