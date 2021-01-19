package model.dao;

import model.dao.realization.DAOFactoryRealization;

import java.sql.Connection;

public abstract class DAOFactory {
    private static DAOFactory daoFactory;

    public abstract ActivityDAO createActivityDAO(Connection connection);

    public abstract UserDAO createUserDAO(Connection connection);

    public static DAOFactory getDaoFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactoryRealization();
        }
        return daoFactory;
    }

}
