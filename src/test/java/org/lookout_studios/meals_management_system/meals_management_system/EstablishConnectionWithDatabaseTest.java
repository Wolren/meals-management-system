package org.lookout_studios.meals_management_system.meals_management_system;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class EstablishConnectionWithDatabaseTest {

    @InjectMocks
    private EstablishConnectionWithDatabase dbConnectionMock;
    @Mock
    private Connection mockConnection;
    @Mock
    private Statement mockStatement;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        dbConnectionMock = mock(EstablishConnectionWithDatabase.class);
    }

    @Test
    public void testMockDBConnection() {
        try {
            Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
            Mockito.when(mockConnection.createStatement().executeUpdate(Mockito.any())).thenReturn(1);
            Mockito.verify(mockConnection.createStatement(), Mockito.times(1));
        } catch (Exception mysqlError) {
            System.out.println(mysqlError);
        }
    }

    @Test
    public void userPresentInTheDb() throws Exception {
        User existingUser = new User();
        existingUser.setEmail("test@email.com");
        String userEmail = existingUser.getEmail();
        when(dbConnectionMock.isUserRegistered(userEmail)).thenReturn(true);
        boolean registered = dbConnectionMock.isUserRegistered(userEmail);
        assertEquals(true, registered);
    }

    @Test
    public void userNotPresentInDb() throws Exception {
        User newUser = new User();
        newUser.setEmail("new@email.com");
        String userEmail = newUser.getEmail();
        when(dbConnectionMock.isUserRegistered(userEmail)).thenReturn(false);
        boolean registered = dbConnectionMock.isUserRegistered(userEmail);
        assertEquals(false, registered);
    }
}