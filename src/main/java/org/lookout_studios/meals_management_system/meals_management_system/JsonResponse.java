package org.lookout_studios.meals_management_system.meals_management_system;

import org.json.JSONObject;

/*
 * This class can be used to generate JSON API responses
 */
public class JsonResponse {
    private JSONObject responseJson;

    public JsonResponse(Object... keyValuePairs) {
        responseJson = new JSONObject();
        for (int i = 0; i < keyValuePairs.length; i += 2) {
            String key = String.valueOf(keyValuePairs[i]);
            Object value = keyValuePairs[i + 1];
            responseJson.put(key, value);
        }
    }

    /**
     * @return JSON response as string
     */
    public String toString() {
        return responseJson.toString();
    }
}
