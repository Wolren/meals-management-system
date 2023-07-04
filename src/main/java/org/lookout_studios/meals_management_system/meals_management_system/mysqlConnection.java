package org.lookout_studios.meals_management_system.meals_management_system;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class mysqlConnection {
    /* TO-DO: Change `test` to mms_database when the connection will be working.*/
    String url = "jdbc:mysql://localhost:3306/test"; 
    String username = "root";
    String password = "";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(url, username, password);

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery ("select * from student");

        while(resultSet.next()) {
            System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+resultSet.getInt(3));
        }

        connection.close();
    }
    
    catch(Exception exception) {
        System.out.println(exception);
    }
}
