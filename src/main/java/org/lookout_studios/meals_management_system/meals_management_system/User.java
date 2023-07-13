package org.lookout_studios.meals_management_system.meals_management_system;

import java.security.SecureRandom;
import java.util.Base64;

public class User {
    private int userId;
    private String email;
    private String password;
    private String registrationToken;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Puts contents of the User object into the database
     */
    public void register() {
        // TO-DO: Put the token and other properties in the database
        throw new UnsupportedOperationException("Not implemented");
    }

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
    private static final byte[] tokenBytes = new byte[48];

    /**
     * @return A random user registration key
     */
    public String generateRegistrationToken() {
        secureRandom.nextBytes(tokenBytes);
        String token = base64Encoder.encodeToString(tokenBytes);
        this.registrationToken = token;
        return token;
    }
}
