package org.lookout_studios.meals_management_system.meals_management_system;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MealsManagementSystemApplicationTests {

	@Test
	void contextLoads() {
		EstablishConnectionWithDatabaseTest 
		connectionWithDatabaseFileReferenceSetUp = new EstablishConnectionWithDatabaseTest () ;
	connectionWithDatabaseFileReferenceSetUp.setUp ();
		EstablishConnectionWithDatabaseTest
		connectionWithDatabaseFileReference = new EstablishConnectionWithDatabaseTest () ;
	connectionWithDatabaseFileReference.testMockDBConnection ();
	}

}
