package com.example.dashboard.Controller;

import java.util.HashMap;
import java.util.List;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dashboard.Entity.Question;
import com.example.dashboard.Entity.Questions;
import com.example.dashboard.Entity.Token;
import com.example.dashboard.Entity.User;
import com.example.dashboard.Entity.UserAnswer;
import com.example.dashboard.Entity.UserJson;
import com.example.dashboard.Entity.Username;
import com.example.dashboard.Mapper.TokenMapper;

import java.util.ArrayList;
import java.util.Base64;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private TokenMapper tokenMapper;

    public DashboardController(TokenMapper tokenMapper){
        this.tokenMapper = tokenMapper;
    }

@GetMapping("/allquestions")
@ResponseBody
public List<Questions> allQuestions(){
    List<Questions> questionsList = tokenMapper.getAllQuestion();
    return questionsList;
}




        @GetMapping("/{userId}")
        public String getCurrentQuestion(@PathVariable String userId) {
            return tokenMapper.getCurrentQuestion(userId);
        }


        @GetMapping("/GETQUESTIONCOUNT")
        public String getCurrentQuestion() {
            return Integer.toString(tokenMapper.countQuestions());
        }



        
        @PostMapping("/mark/{userid}/{questionid}/{answer}")
        public String postAnswerandMark(@PathVariable String userid, @PathVariable String questionid,@PathVariable String answer) {
            try{
                tokenMapper.insertUserAnswer(userid, questionid, answer);
                if(tokenMapper.countQuestions() == Integer.valueOf(questionid)){
                    System.out.println("Quiz Completed");
                    return "Quiz Completed";
                } else{
                    System.out.println("Next question");
                    tokenMapper.updateCurrentUserQuestion(Integer.parseInt(questionid) + 1, Integer.parseInt(userid));
                }
                //System.out.println(tokenMapper.countQuestions());
                return userid + questionid + answer;
            }catch(Exception ex){
                return "Error message: " + ex;
            }   
        
        }


                
        @PostMapping("/mark/{userid}/{booleanCorrect}")
        public String updateMark(@PathVariable String userid,@PathVariable String booleanCorrect) {
            System.out.println(userid + " " + booleanCorrect);
            if(booleanCorrect.equals("1")){
                int CurrentMarkData = 0;
                CurrentMarkData = tokenMapper.CurrentMark(Integer.parseInt(userid));
                System.out.println("Current Mark " +  CurrentMarkData);
                System.out.println("Correct");
                tokenMapper.updateUserTotalMark( CurrentMarkData + 1,Integer.parseInt(userid));
            } else{
                System.out.println("no mark");
            }
            return "mark";  
        }

        //TO prevent the last question kept poped out
        /*
         * 
         * 
         * 
         */
        @GetMapping("/lastquestionverified/{useridString}")
        public String getUserLastAnswer(@PathVariable String useridString) {
            return  tokenMapper.findLargestQuestionForUser(Integer.valueOf(useridString));
        }



        @GetMapping("question/{userId}")
        public List<Question> getUserQuestion(@PathVariable String userId) {
            int totalQuestion = tokenMapper.countQuestions();
            int largestQuestionId = tokenMapper.getLargestQuestionIdForId(Integer.valueOf(userId));
                List<Question> result = new ArrayList<>();
//System.out.println("The largest questionid for id 102 is: " + largestQuestionId);
            if(totalQuestion == largestQuestionId || largestQuestionId >= totalQuestion){
                System.out.println("Quiz Completed");
                Question completedQuestion = new Question();
                completedQuestion.setQuestion("Quiz completed");
                completedQuestion.setId(999);
                result.add(completedQuestion);
                return result;
            } else{
                return tokenMapper.getUserQuestion(userId);

            }
        }
    @GetMapping
    public String getDashboardData(@RequestHeader("Authorization") String authorizationHeader) {
        // Logic to retrieve and process dashboard data
        String username = "";


       // Logic to retrieve and process dashboard data
       System.out.println("Authorization Header: " + authorizationHeader);
       if (authorizationHeader != null && authorizationHeader.length() > 7) {
          authorizationHeader = authorizationHeader.substring(7);
     }
       // Retrieve the latest token using MyBatis
        // Retrieve token data from the database
   // Retrieve the latest token from the database
   String encodedString = "";
        try{
                 Token latestToken = tokenMapper.getTokenByTokenValue(authorizationHeader);

                 User userid = tokenMapper.getUserByUserID(Integer.toString(latestToken.getUser_Id()));


                if (latestToken != null && userid != null) {
                    // Process the token data as needed
                    System.out.println("Latest Token: " + latestToken.getToken());
                    System.out.println("User ID: " + latestToken.getUser_Id());
                    System.out.println("Username " + userid.getFirstname() + userid.getLastname());
                    username = userid.getFirstname() + userid.getLastname();

                    //

                    // Encoding
                    //        String encodedString = Base64.getEncoder().encodeToString(originalString.getBytes());
                    encodedString = Base64.getEncoder().encodeToString(Integer.toString(latestToken.getUser_Id()).getBytes());
                    System.out.println("Encoded string: " + encodedString);
                    
                    // Decoding
                    //byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
                    //String decodedString = new String(decodedBytes);
                    //System.out.println("Decoded string: " + decodedString);
                    UserJson userjson = new UserJson(username, encodedString);

                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonString = null;
            
                    try {
                        jsonString = objectMapper.writeValueAsString(userjson);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            
                    System.out.println(jsonString);
                    encodedString = jsonString;
                    //
                    // ...
                } else {
                    // Token not found, handle the case accordingly
                    System.out.println("null");
                }
        
        }catch(Exception e){
            System.out.println(e);
        }
                

       
       return encodedString;
   
    }

    //

      @GetMapping("/userid")
    public String getUser(@RequestHeader("Authorization") String authorizationHeader) {
        // Logic to retrieve and process dashboard data
        String useraccount= "";


       // Logic to retrieve and process dashboard data
       System.out.println("Authorization Header: " + authorizationHeader);
       if (authorizationHeader != null && authorizationHeader.length() > 7) {
          authorizationHeader = authorizationHeader.substring(7);
     }
       // Retrieve the latest token using MyBatis
        // Retrieve token data from the database
   // Retrieve the latest token from the database
        try{
                 Token latestToken = tokenMapper.getTokenByTokenValue(authorizationHeader);

                 User userid = tokenMapper.getUserByUserID(Integer.toString(latestToken.getUser_Id()));

                if (latestToken != null && userid != null) {
                    // Process the token data as needed
                    //System.out.println("Latest Token: " + latestToken.getToken());
                    System.out.println("User ID: " + latestToken.getUser_Id());
                    //System.out.println("Username " + userid.getFirstname() + userid.getLastname());
                    //username = userid.getFirstname() + userid.getLastname();
                    useraccount = Integer.toString(latestToken.getUser_Id());
                    
                    // ...
                } else {
                    // Token not found, handle the case accordingly
                    System.out.println("null");
                }
        
        }catch(Exception e){
            System.out.println(e);
        }
                

       
       return useraccount;
    }
}