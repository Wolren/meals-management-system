package org.lookout_studios.meals_management_system.meals_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
/*
 * This class handles the app logic.
 * TO-DO: Remove (exclude = { DataSourceAutoConfiguration.class })
 * once the database support is implemented.
 */
@SpringBootApplication (exclude = { DataSourceAutoConfiguration.class })
public class MealsManagementSystemApplication {
	public static void main (String[] args) {
		SpringApplication.run (MealsManagementSystemApplication.class, args);
		EstablishConnectionWithDatabase connectionWithDatabaseFileReference = new EstablishConnectionWithDatabase () ;
		connectionWithDatabaseFileReference.createSqlConnection ();
	}
}