package org.lookout_studios.meals_management_system.meals_management_system;

/**
 * This enum contains HTTP status names and codes.
 * Using this enum ensures consistency and clarity.
 */
public enum ResponseStatus {
    OK("OK"),
    BAD_REQUEST("Bad Request");

    private final String statusName;
    private int statusCode;

    private ResponseStatus(String statusName) {
        this.statusName = statusName;
        switch (statusName) {
            case "OK":
                this.statusCode = 200;
                break;
            case "Bad Request": {
                this.statusCode = 400;
                break;
            }
        }
    }

    /**
     * @return HTTP status code
     */
    public int getCode() {
        return statusCode;
    }

    /**
     * @return HTTP response name, e.g. "Bad Request"
     */
    public String getStatusName() {
        return statusName;
    }
}
