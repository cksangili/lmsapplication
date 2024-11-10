package com.lms.app.entity;

import java.io.Serializable;

// User Class
public class User implements Serializable {
    private String username;
    private String password;
    private String role;

    public User(String role, String username, String password) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }
    public String getRole() {
        return role;
    }
    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }
}
