package org.lookout_studios.meals_management_system.meals_management_system;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonResponseTests {

    @Test
    public void testJsonResponse() {
        // Creating a JSON response
        JsonResponse response = new JsonResponse("status", "success", "message", "Operation completed successfully");

        // Getting the JSON response
        String jsonResponse = response.getResponse();

        // Expected JSON response
        String expectedJsonResponse = "{\"message\":\"Operation completed successfully\",\"status\":\"success\"}";

        // Asserting the expected and actual JSON responses
        Assertions.assertEquals(expectedJsonResponse, jsonResponse, "The JSON response is not as expected");
    }
}


