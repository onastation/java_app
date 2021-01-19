package model.dao.realization;

import model.dao.*;

import java.sql.Connection;

public class DAOFactoryRealization extends DAOFactory {


    @Override
    public ActivityDAO createActivityDAO(Connection connection) {
        return new ActivityDAORealization(connection);
    }

    @Override
    public UserDAO createUserDAO(Connection connection) {
        return new UserDAORealization(connection);
    }

}
