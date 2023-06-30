package by.teachmeskills.hw_02062023.shop.utils;

import by.teachmeskills.hw_02062023.shop.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDUtils {
    private static final String GET_USER_QUERY = "SELECT * FROM users WHERE login = ? AND password = ?";

    public static User getUserDB(String login, String password, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_QUERY);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new User(resultSet.getString("login"), resultSet.getString("password"));
        } catch (SQLException e) {
            return null;
        }
    }
}
