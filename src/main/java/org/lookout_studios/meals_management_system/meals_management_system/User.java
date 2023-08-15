package org.lookout_studios.meals_management_system.meals_management_system;

import java.security.SecureRandom;
import java.util.Base64;

public class User {
    private int userId;
    private String email;
    private int passwordHash;
    private String registrationToken;
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.passwordHash = password.hashCode();
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
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

    public int getPasswordHash() {
        return passwordHash;
    }

    public String getRegistrationToken() {
        return registrationToken;
    }
}
