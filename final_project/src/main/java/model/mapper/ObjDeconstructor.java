package model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ObjDeconstructor<T> {
    T extractEntityFromTheRS(ResultSet resultSet) throws SQLException;
}
