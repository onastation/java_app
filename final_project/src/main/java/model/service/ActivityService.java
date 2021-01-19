package model.service;

import model.dao.DAOFactory;
import model.dao.ActivityDAO;
import model.entity.Activity;
import model.dao.connection.BasicConnectionPool;
import model.exception.ActivityNotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityService {

    private DAOFactory daoFactory;
    private Connection transactionConnection;
    private BasicConnectionPool basicConnectionPool;

    public ActivityService(DAOFactory daoFactory) {
        this.transactionConnection = BasicConnectionPool.getInstance().getConnection();
        this.daoFactory = daoFactory;
        this.basicConnectionPool = BasicConnectionPool.getInstance();
    }
    public ActivityService() {
       // this.transactionConnection = BasicConnectionPool.getInstance().getConnection();
        this.daoFactory = DAOFactory.getDaoFactory();
        this.basicConnectionPool = BasicConnectionPool.getInstance();
    }


    public void deleteActivity(long activityId) {
        try (ActivityDAO activityDAO = DAOFactory.getDaoFactory().createActivityDAO(transactionConnection)) {
            transactionConnection.setAutoCommit(false);
            boolean activity = activityDAO.deleteByKey(activityId);

            if (!activity) {
                throw new ActivityNotFoundException("Activity with id : " + activityId + " not found");
            }
            transactionConnection.commit();
            transactionConnection.setAutoCommit(true);
        } catch (SQLException | ActivityNotFoundException exception) {
            try {
                transactionConnection.rollback();
            } catch (SQLException throwables) {
                exception.printStackTrace();
            }
        }
    }

    public Activity findActivityById(long id){
        Activity activity;
        try(ActivityDAO activityDAO = daoFactory.createActivityDAO(basicConnectionPool.getConnection())){
            activity = activityDAO.findByKey(id);
        }
        return activity;
    }

    public Activity addActivity(Activity activity) {
        Activity activity1;
        try (ActivityDAO activityDAO = daoFactory.createActivityDAO(basicConnectionPool.getConnection())) {
            activity1 = activityDAO.create(activity);
        }
        return activity1;
    }

    public Activity updateActivity(long id, String newActivityName, String newActivityCategory) {
        Activity activity;
        try (ActivityDAO activityDAO = daoFactory.createActivityDAO(basicConnectionPool.getConnection())) {
            activity = activityDAO.updateActivity(id, newActivityName, newActivityCategory);
        }
        return activity;
    }

    public List<Activity> getAllActivities() {
        List<Activity> listOfActivities = new ArrayList<>();
        try (ActivityDAO activityDAO = daoFactory.createActivityDAO(basicConnectionPool.getConnection())) {
            listOfActivities = activityDAO.getAll();
        }
        return listOfActivities;
    }
}
