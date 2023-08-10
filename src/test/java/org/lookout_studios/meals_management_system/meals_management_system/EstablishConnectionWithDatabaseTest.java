package org.lookout_studios.meals_management_system.meals_management_system;

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
  public void setUp() {
    MockitoAnnotations.openMocks(this);
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
}