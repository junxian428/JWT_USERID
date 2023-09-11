package com.example.dashboard.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer user_id;

    private String text;

    private String alarm;
    
    @Column(name = "plc_id")
    private Integer plc_id;

    // Constructors, getters, and setters

    public String getAlarm() {
        return alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public Integer getPlc_id() {
        return plc_id;
    }

    public void setPlc_id(Integer plc_id) {
        this.plc_id = plc_id;
    }

    public Items(Integer id, Integer user_id, String text, String alarm, Integer plc_id) {
        this.id = id;
        this.user_id = user_id;
        this.text = text;
        this.alarm = alarm;
        this.plc_id = plc_id;
    }

    public Items() {
    }
}
