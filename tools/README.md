Possible way of making localhosted database.

1.Download Xampp
	https://www.apachefriends.org/pl/index.html

2. Start MySQL and Apache hosting in a Xampp ControlPanel

3. Click Admin on MySQL in order to get into localhost/phpadmin/
	If it is imposible to connect to localhost here's video how to solve it:
	https://www.youtube.com/watch?v=na4s3lwUAjA&ab_channel=IrebeLibrary

4. Proced into SQL tab and paste code given in a file "MMS-data_creator.sql"

Default variables:
Id: 127.0.0.1
Port: 3306
user: root

In attached python file is example of code that is connecting to database and add data to user table.