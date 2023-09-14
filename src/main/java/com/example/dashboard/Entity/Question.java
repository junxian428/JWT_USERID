package com.example.dashboard.Entity;

public class Question {
    private Integer Id;
    public Integer getId() {
        return Id;
    }



    public void setId(Integer id) {
        Id = id;
    }
    private String Question;
    private String OptionA;
    private String OptionB;
    private String OptionC;
    private String OptionD;
    private String CorrectOption;

    public String getCorrectOption() {
        return CorrectOption;
    }



    public void setCorrectOption(String correctOption) {
        CorrectOption = correctOption;
    }



    public Question(Integer id, String question, String optionA, String optionB, String optionC, String optionD, String CorrectOption) {
        Id = id;
        Question = question;
        OptionA = optionA;
        OptionB = optionB;
        OptionC = optionC;
        OptionD = optionD;
    }



    public Question(){

    }


    
    public String getQuestion() {
        return Question;
    }
    public void setQuestion(String question) {
        Question = question;
    }
    public String getOptionA() {
        return OptionA;
    }
    public void setOptionA(String optionA) {
        OptionA = optionA;
    }
    public String getOptionB() {
        return OptionB;
    }
    public void setOptionB(String optionB) {
        OptionB = optionB;
    }
    public String getOptionC() {
        return OptionC;
    }
    public void setOptionC(String optionC) {
        OptionC = optionC;
    }
    public String getOptionD() {
        return OptionD;
    }
    public void setOptionD(String optionD) {
        OptionD = optionD;
    }

}
