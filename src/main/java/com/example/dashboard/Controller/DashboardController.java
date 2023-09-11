package com.example.dashboard.Controller;

import java.util.HashMap;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dashboard.Entity.Token;
import com.example.dashboard.Entity.User;
import com.example.dashboard.Entity.UserJson;
import com.example.dashboard.Mapper.TokenMapper;
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