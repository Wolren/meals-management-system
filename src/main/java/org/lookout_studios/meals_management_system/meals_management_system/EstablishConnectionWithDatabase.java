package org.lookout_studios.meals_management_system.meals_management_system;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Class establishing connection with database.
 * 
 * @author Hubert Borysowski  
 */
public class EstablishConnectionWithDatabase {
    static String connectionInstanceName = "com.mysql.cj.jdbc.Driver";
    /**
     * 
     * The method connects with database using database url and admin credentials.
     * @throws org.json.simple.parser.ParseException
     */
    public void createSqlConnection () {
        /* Create JSONParser object, so you can read configuration data from JSON file. */
        JSONParser jsonParser = new JSONParser();
        Connection accessConnection;
        try {
            /* Read configuration data from JSON file. */
            FileReader jsonFileDataReader = new FileReader
                (".config\\establishConnectionWithDatabaseConfig.json");
            Object saveParsedData = jsonParser.parse(jsonFileDataReader);
            JSONObject saveJsonData = (JSONObject)saveParsedData;
            String databaseJdbcUrl = (String) saveJsonData.get("databaseJdbcUrl"); /* Naming comes from JSON variables. */
            String databaseUsername = (String) saveJsonData.get("databaseUsername");
            String databasePassword = (String) saveJsonData.get("databasePassword");
            /* Establish MySQL connection. */
            Class.forName (connectionInstanceName);
            accessConnection = DriverManager.getConnection 
                (databaseJdbcUrl, databaseUsername, databasePassword);
            accessConnection.close ();
        }
        catch (FileNotFoundException fileNotFoundError)
        {
            System.out.println(fileNotFoundError);
        }
        catch (IOException ioError)
        {
            System.out.println(ioError);
        }
        catch (Exception mysqlError) {
            System.out.println (mysqlError);
        }
    }
}