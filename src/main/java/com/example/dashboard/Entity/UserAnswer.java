package com.example.dashboard.Entity;

public class UserAnswer {
    private int userid;
    private int questionid;
    private String answer;
    public UserAnswer(){

    }
    
    public UserAnswer(int userid, int questionid, String answer) {
        this.userid = userid;
        this.questionid = questionid;
        this.answer = answer;
    }
    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }
    public int getQuestionid() {
        return questionid;
    }
    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
