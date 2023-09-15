package com.example.dashboard.Entity;

import jakarta.persistence.Entity;


public class Email {
    private String email;

    public Email(String email) {
        this.email = email;
    }



    public Email(){

    }

    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
