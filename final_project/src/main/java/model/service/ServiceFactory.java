package model.service;

import model.service.impl.ServiceFactoryImpl;

public abstract class ServiceFactory {
    private static ServiceFactory serviceFactory;


    public static ServiceFactory getInstance() {
        if (serviceFactory == null) {
                    serviceFactory = new ServiceFactoryImpl();
        }
        return serviceFactory;
    }

    public abstract ActivityService createActivityService();

    public abstract UserService createUserService();

}
