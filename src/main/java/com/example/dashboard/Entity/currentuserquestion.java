package com.example.dashboard.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "currentuserquestion")
public class currentuserquestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userid;
    private Long questionid;

    public currentuserquestion(Long id, Long userid, Long questionid) {
        this.id = id;
        this.userid = userid;
        this.questionid = questionid;
    }


    public currentuserquestion(){

    }

    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserid() {
        return userid;
    }
    public void setUserid(Long userid) {
        this.userid = userid;
    }
    public Long getQuestionid() {
        return questionid;
    }
    public void setQuestionid(Long questionid) {
        this.questionid = questionid;
    }


}
