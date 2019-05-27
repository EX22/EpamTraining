package by.khomenko.training.finaltask05.dao.mysql;


import by.khomenko.training.finaltask05.dao.Dao;
import by.khomenko.training.finaltask05.dao.pool.ConnectionPool;
import by.khomenko.training.finaltask05.dao.pool.PooledConnection;
import by.khomenko.training.finaltask05.entity.Entity;
import by.khomenko.training.finaltask05.exception.PersistentException;

import java.sql.Connection;

public abstract class BaseDaoImpl <T extends Entity> implements Dao<T> {

    protected Connection connection;

     BaseDaoImpl() throws PersistentException {
        this.connection = ConnectionPool.getInstance().getConnection();
    }

    public void setConnection(final Connection connectionVal) {
        this.connection = connectionVal;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() throws Exception {
        ConnectionPool.getInstance().freeConnection((PooledConnection) this.connection);
    }
}
