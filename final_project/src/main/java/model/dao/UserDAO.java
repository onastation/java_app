package model.dao;

import model.entity.Activity;
import model.entity.User;

import java.sql.Connection;

public abstract class UserDAO extends AbstractDAO<User,Long> {
    public UserDAO(Connection connection) {
        super(connection);
    }
    public abstract User findUserByUsername(String username);
    public abstract User updateUser(Long id, String newUsername, String newPassword, String newRole);
}
