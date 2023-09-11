package com.example.dashboard.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "token")
public class Token {
    @Id
    private Integer id;
    private boolean expired;
    private boolean revoked;
    private String token;
    private String token_type;
    private Integer user_Id;
    public Token() {
    }
    public Token(Integer id, boolean expired, boolean revoked, String token, String token_type, Integer user_Id) {
        this.id = id;
        this.expired = expired;
        this.revoked = revoked;
        this.token = token;
        this.token_type = token_type;
        this.user_Id = user_Id;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public boolean isExpired() {
        return expired;
    }
    public void setExpired(boolean expired) {
        this.expired = expired;
    }
    public boolean isRevoked() {
        return revoked;
    }
    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getToken_type() {
        return token_type;
    }
    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }
    public Integer getUser_Id() {
        return user_Id;
    }
    public void setUser_Id(Integer user_Id) {
        this.user_Id = user_Id;
    }

    // Getters and setters
    // ...
}