package model.dao;

import model.dao.connection.BasicConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO<T, PK> implements AutoCloseable {

    private Connection connection;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }


    public abstract T create(T t);

    public abstract T findByKey(PK key);

    public abstract boolean deleteByKey(PK key);

    public abstract List<T> getAll();

    public void close() {
        BasicConnectionPool.getInstance().shutdownConnection(connection);
    }

    public PreparedStatement getStatement(String query) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = this.connection.prepareStatement(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return preparedStatement;
    }

    public Connection getConnection() {
        return connection;
    }

}
