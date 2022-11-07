package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        inputtingSQLQuery("CREATE TABLE IF NOT EXISTS Users (Id BIGINT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(20), Lastname VARCHAR(20), Age TINYINT)");
    }

    public void dropUsersTable() {
        inputtingSQLQuery("DROP TABLE Users");
    }

    public void saveUser(String name, String lastName, byte age) {
        inputtingSQLQuery("INSERT INTO Users(Name, Lastname, Age) VALUES('" + name +"','"+ lastName + "'," + age + ")");
    }

    public void removeUserById(long id) {
        inputtingSQLQuery("DELETE FROM Users WHERE Id = "+ id);
    }

    public List<User> getAllUsers() {
        String sqlComand = "SELECT * FROM Users";
        List<User> users = new LinkedList<>();
        try (Connection conn = Util.getMySQLConnection()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlComand);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("Id"));
                user.setName(rs.getString("Name"));
                user.setLastName(rs.getString("Lastname"));
                user.setAge(rs.getByte("Age"));
                users.add(user);
            }
            return users;
        } catch (SQLException | ClassNotFoundException e) {
            try {
                throw  new SQLException();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void cleanUsersTable() {
        inputtingSQLQuery("DELETE FROM Users");
    }

    public void inputtingSQLQuery(String sqlQuery){
        try (Connection conn = Util.getMySQLConnection()) {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlQuery);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
