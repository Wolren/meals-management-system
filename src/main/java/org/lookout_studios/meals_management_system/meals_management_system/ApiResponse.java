package org.lookout_studios.meals_management_system.meals_management_system;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;

/*
 * This class can be used to generate JSON API responses
 */
public class ApiResponse {
    private JSONObject responseJson;

    /**
     * A default content of an API response
     * 
     * @param status  HTTP status of the response
     * @param message A custom message to be included in the response
     */
    public ApiResponse(HttpStatus status, String message) {
        responseJson = jsonPairs(
                "status", status.value(),
                "message", message);
    }

    public ApiResponse(HttpStatus status) {
        responseJson = new JSONObject();
        responseJson.put("status", status);
    }

    public ApiResponse(Object... keyValuePairs) {
        jsonPairs(keyValuePairs);
    }

    /**
     * Allows for addition of custom elements to the responseJson in key-value
     * pairs
     * 
     * @param keyValuePairs Key-value pairs
     * @return A JSONObject containing provided input
     */
    public JSONObject jsonPairs(Object... keyValuePairs) {
        responseJson = new JSONObject();
        for (int i = 0; i < keyValuePairs.length; i += 2) {
            String key = String.valueOf(keyValuePairs[i]);
            Object value = keyValuePairs[i + 1];
            responseJson.put(key, value);
        }
        return responseJson;
    }

    /**
     * 
     * @return An API response as JSON
     */
    public String getResponse() {
        return responseJson.toString();
    }
}
