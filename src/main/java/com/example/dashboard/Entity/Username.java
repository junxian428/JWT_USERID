package com.example.dashboard.Entity;

public class Username {
    
    private String firstname;
    private String lastname;

    public Username(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public Username(){
        
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}
