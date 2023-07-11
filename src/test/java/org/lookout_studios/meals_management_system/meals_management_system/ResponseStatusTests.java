package org.lookout_studios.meals_management_system.meals_management_system;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ResponseStatusTests {
    @Test
    public void okStatus() {
        ResponseStatus status = ResponseStatus.OK;
        assertEquals("OK", status.getStatusName());
        assertEquals(200, status.getCode());
    }

    @Test
    public void badRequestStatus() {
        ResponseStatus status = ResponseStatus.BAD_REQUEST;
        assertEquals("Bad Request", status.getStatusName());
        assertEquals(400, status.getCode());
    }
}
