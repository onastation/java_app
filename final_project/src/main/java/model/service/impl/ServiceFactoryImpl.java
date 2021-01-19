package model.service.impl;

import model.dao.DAOFactory;
import model.service.*;

public class ServiceFactoryImpl extends ServiceFactory {

    @Override
    public ActivityService createActivityService() {
        return new ActivityService(DAOFactory.getDaoFactory());
    }

    @Override
    public UserService createUserService() {
        return new UserService(DAOFactory.getDaoFactory());
    }

}
