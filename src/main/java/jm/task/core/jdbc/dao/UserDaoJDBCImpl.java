package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connect = Util.getConnect();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        final String request = "CREATE TABLE IF NOT EXISTS users (\n" +
                "id bigint PRIMARY KEY AUTO_INCREMENT, name varchar(255), lastName varchar(255), age int)";
        try (Statement statement = connect.createStatement()) {
            statement.execute(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        final String request = "DROP TABLE IF EXISTS users;";
        try (Statement statement = connect.createStatement()) {
            statement.execute(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        final String request = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?);";
        try (PreparedStatement statement = connect.prepareStatement(request)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        final String request = "DELETE FROM users WHERE id =?;";
        try (PreparedStatement statement = connect.prepareStatement(request)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        final String request = "SELECT * FROM users;";
        try (Statement statement = connect.createStatement()) {
            ResultSet resultSet = statement.executeQuery(request);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        final String request = "TRUNCATE users;";
        try(Statement statement = connect.createStatement()) {
            statement.execute(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
