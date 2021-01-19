package model.mapper.impl;

import model.entity.User;
import model.mapper.ObjDeconstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Deconstructor implements ObjDeconstructor<User> {
    @Override
    public User extractEntityFromTheRS(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("user_id"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("role")
        );
    }
}
