package by.khomenko.training.finaltask05.dao.mysql;

import by.khomenko.training.finaltask05.dao.FavoriteDao;
import by.khomenko.training.finaltask05.entity.Favorite;
import by.khomenko.training.finaltask05.exception.PersistentException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FavoruteDaoImpl extends BaseDaoImpl implements FavoriteDao {

    @Override
    public Integer create(Favorite entity) throws PersistentException {
        String sql = "INSERT INTO favorites (identity,"
                + " user_identity, category_identity) VALUES (?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getIdentity());
            statement.setInt(2, entity.getUserIdentity());
            statement.setInt(3, entity.getCategoryIdentity());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                //TODO Add logger.error("There is no autoincremented index after trying to add record into table `usages`");
                throw new PersistentException();
            }
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
}
