package org.lookout_studios.meals_management_system.meals_management_system;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;

/*
 * This class can be used to generate JSON API responses
 */
public class ApiResponse {
    private JSONObject responseJson;

    public ApiResponse(Object... keyValuePairs) {
        jsonPairs(keyValuePairs);
    }

    public JSONObject jsonPairs(Object... keyValuePairs) {
        responseJson = new JSONObject();
        for (int i = 0; i < keyValuePairs.length; i += 2) {
            String key = String.valueOf(keyValuePairs[i]);
            Object value = keyValuePairs[i + 1];
            responseJson.put(key, value);
        }
        return responseJson;
    }

    public String getResponse() {
        return responseJson.toString();
    }

    public ApiResponse(HttpStatus status) {
        responseJson = new JSONObject();
        responseJson.put("status", status);
    }

    /**
     * @return JSON response as string
     */
    public ApiResponse(HttpStatus status, String message) {
        responseJson = jsonPairs(
                "status", status.value(),
                "message", message);
    }
}
