package com.example.dashboard.Entity;

public class UserJson {
    private String username;
    private String blob;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBlob() {
        return blob;
    }

    public void setBlob(String blob) {
        this.blob = blob;
    }

    public UserJson(String username, String blob) {
        this.username = username;
        this.blob = blob;
    }

    // Getters and setters (or use Lombok library for automatic generation)
}