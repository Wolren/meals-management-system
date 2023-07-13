# meals-management-system
Meals Management System aims to reduce food waste by suggesting recepies based on products in your fridge and their expiration date

## Creating database
    Possible way of making localhosted database.

    1.Download Xampp
	    https://www.apachefriends.org/pl/index.html

    2. Start MySQL and Apache hosting in a Xampp ControlPanel

    3. Click Admin on MySQL in order to get into localhost/phpadmin/
	    If it is imposible to connect to localhost here's video how to solve it:
	    https://www.youtube.com/watch?v=na4s3lwUAjA&ab_channel=IrebeLibrary

    4. Proced into SQL tab and paste code given in a file "tools/MMS-data_creator.sql"

    Default variables:
    Id: 127.0.0.1
    Port: 3306
    user: root

## Inserting database connection establishment data
    1. Find "EstablishConnectionWithDatabase.java" file at `src/main/java/org/lookout_studios/meals_management_system/meals_management_system`.

    2. Find accessData class (ln 5).

    3. Change:
        a. **databaseJdbcUrl** `"jdbc:mysql://localhost:3306/mms_database"` to `"jdbc:mysql://yourDatabaseIp:port/databaseName"`
        b. **databaseUsername** `""` to `"yourDatabaseUsername"`
        c. **databasePassword** `""` to `"yourDatabasePassword"`.