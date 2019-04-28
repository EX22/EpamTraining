package by.khomenko.training.finaltask05.dao.mysql;

import java.sql.Connection;

public class BaseDaoImpl {

    protected Connection connection;

    public void setConnection(final Connection connectionVal) {
        this.connection = connectionVal;
    }

    public Connection getConnection() {
        return connection;
    }
}
