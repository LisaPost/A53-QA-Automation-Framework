package stepDefinitions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseUtils {
    public static void updateUserEmail(int userId, String newEmail) {
        Connection connection = null;
        String databaseUrl = "jdbc:mariadb://104.237.13.60:3306/dbkoel";
        String databaseUser = "dbuser08";
        String databasePassword = "pa$$08";
        try {
            connection = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
            String updateQuery = "UPDATE users SET email = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, newEmail);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            System.out.println("Email updated successfully for user with ID: " + userId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
