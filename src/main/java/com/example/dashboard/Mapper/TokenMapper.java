package com.example.dashboard.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.dashboard.Entity.Question;
import com.example.dashboard.Entity.Questions;
import com.example.dashboard.Entity.Token;
import com.example.dashboard.Entity.User;
import com.example.dashboard.Entity.UserAnswer;
import com.example.dashboard.Entity.Username;

@Mapper
public interface TokenMapper {
    @Select("SELECT * FROM token WHERE token = #{tokenValue} ")
    Token getTokenByTokenValue(String tokenValue);

     @Select("SELECT * FROM _user WHERE id = #{userid} ")
    User getUserByUserID(String userid);

    @Select("SELECT firstname, lastname FROM _user WHERE id = #{userid} ")
    Username getUserNameByID(String id);

    @Select("SELECT * FROM questions")
    List<Questions> getAllQuestion();


    @Select("SELECT questionid FROM currentuserquestion WHERE userid = #{userid} ")
    String getCurrentQuestion(String id);

    @Select("SELECT id, Question, OptionA, OptionB, OptionC, OptionD, CorrectOption FROM questions WHERE id = #{userid} ")
    List<Question> getUserQuestion(String id);

    @Insert("INSERT INTO useranswer (userid, questionid, answer) VALUES (#{userid}, #{questionid}, #{answer} )")
    void insertUserAnswer(String userid, String questionid, String answer);


    @Update("UPDATE usermark SET totalmark = #{totalmark} WHERE userid = #{userid}")
    void updateUserTotalMark(@Param("totalmark") int totalmark, @Param("userid") int userid);

    @Select("SELECT totalmark FROM usermark WHERE userid  = #{userid}")
    int  CurrentMark( @Param("userid") int userid);

    @Update("UPDATE currentuserquestion SET questionid = #{questionid} WHERE userid = #{userid}")
    void updateCurrentUserQuestion(@Param("questionid") int questionid, @Param("userid") int userid);


    @Select("SELECT COUNT(*) FROM questions")
    int countQuestions();


    @Select("SELECT questionid FROM currentuserquestion WHERE userid = #{userid}")
    String getLargestQuestionIdForId(@Param("userid") int id);

    @Select("SELECT questionid FROM useranswer WHERE userid = #{userId} ORDER BY questionid DESC LIMIT 1;")
    String findLargestQuestionForUser(int userId);    

}