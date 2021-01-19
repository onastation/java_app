package model.dao.realization;

import model.dao.ActivityDAO;
import model.entity.Activity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityDAORealization extends ActivityDAO {

    private final String table = "activities";
    private final String CREATE = "insert into " + table + " (activity_name, category) values(?,?)";
    private final String FIND_ACTIVITY_BY_NAME = "select * from " + table + " where activity_name=?";
    private final String FIND_ACTIVITY_BY_ID = "select * from " + table + " where activity_id=?";
    private final String DELETE_ACTIVITY_BY_ID = "delete from " + table + " where activity_id=?";
    private final String UPDATE_ACTIVITY_BY_ID = "update " + table + " set activity_name=?, category=? where activity_id=?";
    private String GET_ALL_ACTIVITIES = "select * from " + table;

    public ActivityDAORealization(Connection connection) {
        super(connection);
    }

    public Activity findActivityByName(String name) {
        Activity activity = null;
        try (PreparedStatement preparedStatement = getStatement(FIND_ACTIVITY_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                activity = new Activity(res.getLong("activity_id"),
                        res.getString("activity_name"),
                        res.getString("category"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return activity;
    }

    @Override
    public Activity updateActivity(Long id, String newName, String newCategory) {
        try(PreparedStatement preparedStatement = getStatement(UPDATE_ACTIVITY_BY_ID)){
            preparedStatement.setString(1,newName);
            preparedStatement.setString(2,newCategory);
            preparedStatement.setLong(3,id);
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return new Activity(id,newName);
    }

    public Activity create(Activity activity) {
        boolean executed = false;
        try (PreparedStatement preparedStatement = getStatement(CREATE)) {
            preparedStatement.setString(1, activity.getName());
            preparedStatement.setString(2, activity.getCategory());
            executed = preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return executed ? activity : null;
    }

    public Activity findByKey(Long key) {
        Activity activity = null;
        try (PreparedStatement preparedStatement = getStatement(FIND_ACTIVITY_BY_ID)) {
            preparedStatement.setLong(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                activity = new Activity(
                        resultSet.getLong("activity_id"),
                        resultSet.getString("activity_name"),
                        resultSet.getString("category"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return activity;
    }



    public boolean deleteByKey(Long key) {
        int updated = 0 ;
        try (PreparedStatement preparedStatement = getStatement(DELETE_ACTIVITY_BY_ID)) {
            preparedStatement.setLong(1, key);
            updated = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return updated != 0;
    }

    public List<Activity> getAll() {
        List<Activity> listOfActivity = new ArrayList<Activity>();
        try (PreparedStatement preparedStatement = getStatement(GET_ALL_ACTIVITIES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Activity activity = new Activity(
                        resultSet.getLong("activity_id"),
                        resultSet.getString("activity_name"),
                        resultSet.getString("category")
                );
                listOfActivity.add(activity);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listOfActivity;
    }
}
