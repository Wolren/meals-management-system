package org.lookout_studios.meals_management_system.meals_management_system;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JsonResponseTests {

    @Test
    public void JsonResponse() {
        // Creating a JSON response
        JsonResponse response = new JsonResponse("status", "success", "message", "Operation completed successfully");

        // Getting the JSON response
        String jsonResponse = response.getResponse();

        // Expected JSON response
        String expectedJsonResponse = "{\"message\":\"Operation completed successfully\",\"status\":\"success\"}";

        // Asserting the expected and actual JSON responses
        Assertions.assertEquals(expectedJsonResponse, jsonResponse, "The JSON response is not as expected");
    }

    @Test
    public void okRequestStatusIntegration() {
        String expectedResponse = """
                {"message":"OK","status":"200"}""";
        JsonResponse response = new JsonResponse();
        response.addStatus(ResponseStatus.OK);
        Assertions.assertEquals(expectedResponse, response.getResponse());
    }

    @Test
    public void badRequestStatusIntegration() {
        String expectedResponse = """
                {"message":"Bad Request","status":"400"}""";
        JsonResponse response = new JsonResponse();
        response.addStatus(ResponseStatus.BAD_REQUEST);
        Assertions.assertEquals(expectedResponse, response.getResponse());
    }
}
