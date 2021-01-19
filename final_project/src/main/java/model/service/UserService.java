package model.service;

import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.dao.connection.BasicConnectionPool;

import java.sql.Connection;

import model.entity.User;
import model.exception.ActivityNotFoundException;
import model.exception.UserAlreadyExistsException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserService {
    private DAOFactory daoFactory;
    private Connection transactionConnection;
    private BasicConnectionPool basicConnectionPool;

    public UserService(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
        this.basicConnectionPool = BasicConnectionPool.getInstance();
    }

    public UserService() {
        this.daoFactory = DAOFactory.getDaoFactory();
        this.basicConnectionPool = BasicConnectionPool.getInstance();
    }


    public User findUserByUsername(String username) {
        try (UserDAO userDAO = daoFactory.createUserDAO(basicConnectionPool.getConnection());
        ) {
            User user = userDAO.findUserByUsername(username);
            if (user == null) {
                return null;
            }
            String role = user.getRole();
            user.setRole(role);
            return user;
        }
    }

    public void deleteUser(Long userId) {
        try (UserDAO userDAO = DAOFactory.getDaoFactory().createUserDAO(transactionConnection)) {
            transactionConnection.setAutoCommit(false);
            boolean user = userDAO.deleteByKey(userId);

            if (!user) {
                throw new ActivityNotFoundException("User with id : " + userId + " not found");
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

    public User updateUser(Long id, String newUsername, String newPassword, String newRole) {
        User user;
        try (UserDAO userDAO = daoFactory.createUserDAO(basicConnectionPool.getConnection())) {
            user = userDAO.updateUser(id, newUsername, newPassword, newRole);
        }
        return user;
    }

    public User createUser(User user) throws UserAlreadyExistsException {
        try (UserDAO userDAO = daoFactory.createUserDAO(basicConnectionPool.getConnection())) {
            User newUser = userDAO.findUserByUsername(user.getUsername());
            if (newUser == null) {
                userDAO.create(user);
                return user;
            } else {
                throw new UserAlreadyExistsException("User with :"+user.getUsername()+ " is already created");
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> listOfUsers = new ArrayList<>();
        try (UserDAO userDAO = daoFactory.createUserDAO(basicConnectionPool.getConnection())) {
            listOfUsers = userDAO.getAll();
        }
        return listOfUsers;
    }
}
