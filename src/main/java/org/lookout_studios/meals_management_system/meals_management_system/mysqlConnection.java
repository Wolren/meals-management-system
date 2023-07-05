package org.lookout_studios.meals_management_system.meals_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MysqlConnection {
    public void sqlConnection() {
        String url = "jdbc:mysql://localhost:3306/test"; /* Change `test` to desired database.*/
        String username = "root";
        String password = "";
        try {
            Class.forName ("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection (url, username, password);
            Statement statement = connection.createStatement ();
            ResultSet resultSet = statement.executeQuery ("select * from students"); /* Change `students` to desired table. */
            /* For tests only. */
            while (resultSet.next ()) {
                System.out.println(
                    resultSet.getInt (1)+" "+resultSet.getString (2)+" "+resultSet.getInt (3));
            }
            connection.close ();
        }
        catch (Exception exception) {
            System.out.println ("An error has occured.");
            System.out.println (exception);
        }
    }
}
