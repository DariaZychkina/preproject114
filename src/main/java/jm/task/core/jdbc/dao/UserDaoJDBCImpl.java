package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection conn = null;

    public UserDaoJDBCImpl() { }

    public void createUsersTable() {
        PreparedStatement createTab = null;
        try {
            conn = Util.getMySQLConnection();
            conn.setAutoCommit(false);
            createTab = conn.prepareStatement("CREATE TABLE IF NOT EXISTS Users (Id BIGINT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(20), Lastname VARCHAR(20), Age TINYINT)");
            createTab.executeUpdate();
            conn.commit();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            try {//не понимаю, как тут без вложенных проверок, если методы их требуют
                if (conn != null ) {
                    conn.rollback();
                }
            } catch (SQLException excep) {
                System.out.println(excep.getMessage());
            }
        } finally {
            try {
                if (createTab != null) {
                    createTab.close();
                }
            } catch (SQLException excep) {
                System.out.println(excep.getMessage());
            }
        }
    }

    public void dropUsersTable() {
        PreparedStatement dropTable = null;
        try {
            conn = Util.getMySQLConnection();
            conn.setAutoCommit(false);
            dropTable = conn.prepareStatement("DROP TABLE Users");
            dropTable.executeUpdate();
            conn.commit();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            try {       //и тут
                if (conn != null ) {
                    conn.rollback();
                }
            } catch (SQLException excep) {
                System.out.println(excep.getMessage());
            }
        } finally {
            try {
                if (dropTable != null) {
                    dropTable.close();
                }
            } catch (SQLException excep) {
                System.out.println(excep.getMessage());
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement savingUser = null;
        try {
            conn = Util.getMySQLConnection();
            conn.setAutoCommit(false);
            savingUser = conn.prepareStatement("INSERT INTO Users(Name, Lastname, Age) VALUES(?,?,?)");
            savingUser.setString(1, name);
            savingUser.setString(2, lastName);
            savingUser.setByte(3, age);
            savingUser.executeUpdate();
            conn.commit();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            try { //и вот тут еще
                if (conn != null ) {
                    conn.rollback();
                }
            } catch (SQLException excep) {
                System.out.println(excep.getMessage());
            }
        } finally {
            try {
                if (savingUser != null) {
                    savingUser.close();
                }
            } catch (SQLException excep) {
                System.out.println(excep.getMessage());
            }
        }
    }

    public void removeUserById(long id) {
        PreparedStatement remUser = null;
        try {
            conn = Util.getMySQLConnection();
            conn.setAutoCommit(false);
            remUser = conn.prepareStatement("DELETE FROM Users WHERE Id = ?");
            remUser.setLong(1, id);
            remUser.executeUpdate();
            conn.commit();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            try {  //кхм, да-да и тут
                if (conn != null ) {
                    conn.rollback();
                }
            } catch (SQLException excep) {
                System.out.println(excep.getMessage());
            }
        } finally {
            try {
                if (remUser != null) {
                    remUser.close();
                }
            } catch (SQLException excep) {
                System.out.println(excep.getMessage());
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new LinkedList<>();
        PreparedStatement getUsers = null;
        try {
            conn = Util.getMySQLConnection();
            conn.setAutoCommit(false);
            getUsers = conn.prepareStatement("SELECT * FROM Users");
            ResultSet rs = getUsers.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("Id"));
                user.setName(rs.getString("Name"));
                user.setLastName(rs.getString("Lastname"));
                user.setAge(rs.getByte("Age"));
                users.add(user);
            }
            conn.commit();
        } catch (SQLException | ClassNotFoundException e) {
            try {
                if (conn != null ) {
                    conn.rollback();
                }
            } catch (SQLException excep) {
                throw new SQLException();
            }
        } finally { //везде в общем + нет возможности использовать try с ресурсами, тк нужен rollback, который происходит при исключении, но при try с ресурсами коннекта, конечно, видно не будет
            try {
                if (getUsers != null) {
                    getUsers.close();
                }
            } catch (SQLException excep) {
                System.out.println(excep.getMessage());
            }
            return users;
        }
    }

    public void cleanUsersTable() {
        PreparedStatement cleanTab = null;
        try {
            conn = Util.getMySQLConnection();
            conn.setAutoCommit(false);
            cleanTab = conn.prepareStatement("DELETE FROM Users");
            cleanTab.executeUpdate();
            conn.commit();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            try {
                if (conn != null ) {
                    conn.rollback();
                }
            } catch (SQLException excep) {
                System.out.println(excep.getMessage());
            }
        } finally {
            try {
                if (cleanTab != null) {
                    cleanTab.close();
                }
            } catch (SQLException excep) {
                System.out.println(excep.getMessage());
            }
        }
    }
}
