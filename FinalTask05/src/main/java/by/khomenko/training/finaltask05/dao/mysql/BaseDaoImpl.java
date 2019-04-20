package by.khomenko.training.finaltask05.dao.mysql;

import java.sql.Connection;

public class BaseDaoImpl {

    protected Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }
}
