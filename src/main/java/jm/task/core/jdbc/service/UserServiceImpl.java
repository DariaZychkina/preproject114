package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl daoJDBC = new UserDaoJDBCImpl();
    public void createUsersTable() {
        try {
            daoJDBC.createUsersTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void dropUsersTable() {
        try {
            daoJDBC.dropUsersTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            daoJDBC.saveUser(name, lastName, age);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try {
            daoJDBC.removeUserById(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        try {
            return daoJDBC.getAllUsers();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void cleanUsersTable() {
        try {
            daoJDBC.cleanUsersTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
