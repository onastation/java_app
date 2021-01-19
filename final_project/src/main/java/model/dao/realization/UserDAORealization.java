package model.dao.realization;

import model.dao.UserDAO;
import model.entity.User;
import model.mapper.impl.Deconstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAORealization extends UserDAO {

    private Deconstructor deconstructor = new Deconstructor();
    private final String table = "users";
    private final String CREATE_QUERY = "insert into " + table +
            " (username,password,role) values(?,?,?)";
    private final String FIND_ALL = "select * from " + table;
    private final String FIND_USER_BY_USERNAME = "select * from " + table + " where username=?";
    private final String FIND_USER_BY_ID = "select * from " +table+ " where user_id=?";
    private final String UPDATE_USER_BY_ID = "update " + table + " set user_name=?, password=?, role=? where user_id=?";
    private final String DELETE_USER_BY_ID = "delete from " + table + " where user_id=?";
    private final String GET_ALL_USERS = "select * from " + table;

    public UserDAORealization(Connection connection) {
        super(connection);
    }

    public User findUserByUsername(String username) {
        User findedUser = null;
        try(PreparedStatement preparedStatement = getStatement(FIND_USER_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                findedUser = deconstructor.extractEntityFromTheRS(res);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return findedUser;
    }


    @Override
    public User create(User user) {
        try(PreparedStatement preparedStatement = getStatement(CREATE_QUERY)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public User findByKey(Long key) {
        User user = null;
        try(PreparedStatement preparedStatement = getStatement(FIND_USER_BY_ID)) {
            preparedStatement.setLong(1, key);
            ResultSet res = preparedStatement.executeQuery();
            user = deconstructor.extractEntityFromTheRS(res);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean deleteByKey(Long key) {
        int deletedRows = 0;
        try(PreparedStatement preparedStatement = getStatement(DELETE_USER_BY_ID)) {
            preparedStatement.setLong(1, key);
            deletedRows = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return deletedRows != 0;
    }

    @Override
    public User updateUser(Long id, String newUsername, String newPassword, String newRole) {
        try(PreparedStatement preparedStatement = getStatement(UPDATE_USER_BY_ID)){
            preparedStatement.setString(1,newUsername);
            preparedStatement.setString(2,newPassword);
            preparedStatement.setString(3,newRole);
            preparedStatement.setLong(4,id);
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return new User(id,newUsername, newPassword, newRole);
    }

    @Override
    public List<User> getAll() {
        List<User> listOfUsers = new ArrayList<User>();
        ResultSet res = null;
        try(PreparedStatement preparedStatement = getStatement(GET_ALL_USERS)){
            res = preparedStatement.executeQuery();
            while (res.next()) {
                User user = deconstructor.extractEntityFromTheRS(res);
                listOfUsers.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listOfUsers;
    }
}
