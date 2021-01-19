package model.dao;

import model.entity.Activity;

import java.sql.Connection;

public abstract class ActivityDAO extends AbstractDAO<Activity,Long> {

    public ActivityDAO(Connection connection) {
        super(connection);
    }
    public abstract Activity findActivityByName(String name);
    public abstract Activity updateActivity(Long id , String newName, String newCategory);
}
