package com.example.dashboard.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dashboard.Entity.Items;
import com.example.dashboard.Service.ItemService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;




import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "*") // Adjust the allowed origins as per your Vue.js app's URL
public class ItemController {
    


    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Items>> getAllItems(@RequestHeader("Authorization") String bearerToken) throws IOException {
        System.out.println(bearerToken);
        //System.out.println(blobData);
        //
        String userId= "";
        
        if (bearerToken != null && bearerToken.length() > 7) {
            bearerToken = bearerToken.substring(7);
       }


        String url = "http://localhost:8087/dashboard/userid";
        String token = bearerToken;

        // Create the URL object
        URL apiUrl = new URL(url);

        // Open a connection
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

        // Set the request method to GET
        connection.setRequestMethod("GET");

        // Set the Authorization header with the authentication token
        connection.setRequestProperty("Authorization", "Bearer " + token);

        // Get the response code
        int responseCode = connection.getResponseCode();

        // If the response code is 200 (OK), read the response body
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder responseBody = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                responseBody.append(line);
            }
            reader.close();

            // Print the response body
            System.out.println(responseBody.toString());
            //List<Items> items = itemService.getItemsByUserId(responseBody.toString());
            userId = responseBody.toString();
            
        } else {
            System.out.println("Request failed with response code: " + responseCode);
        }

        // Close the connection
        connection.disconnect();
    


        List<Items> items = itemService.getItemsByUserId( userId );

        //

        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<Items> createItem(@RequestHeader("Authorization") String bearerToken, @RequestBody Items item) throws IOException {
        System.out.println(bearerToken);
        //

        
        if (bearerToken != null && bearerToken.length() > 7) {
            bearerToken = bearerToken.substring(7);
       }


        String url = "http://localhost:8087/dashboard/userid";
        String token = bearerToken;

        // Create the URL object
        URL apiUrl = new URL(url);

        // Open a connection
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

        // Set the request method to GET
        connection.setRequestMethod("GET");

        // Set the Authorization header with the authentication token
        connection.setRequestProperty("Authorization", "Bearer " + token);

        // Get the response code
        int responseCode = connection.getResponseCode();

        // If the response code is 200 (OK), read the response body
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder responseBody = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                responseBody.append(line);
            }
            reader.close();

            // Print the response body
            System.out.println(responseBody.toString());
            item.setUser_id(Integer.parseInt(responseBody.toString()));

        } else {
            System.out.println("Request failed with response code: " + responseCode);
        }

        // Close the connection
        connection.disconnect();
    



        //
        Items createdItem = itemService.createItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Items> updateItem(@RequestHeader("Authorization") String bearerToken, @PathVariable("id") int id, @RequestBody Items updatedItem) throws IOException {
        System.out.println(bearerToken);
        //

        if (bearerToken != null && bearerToken.length() > 7) {
            bearerToken = bearerToken.substring(7);
       }


        String url = "http://localhost:8087/dashboard/userid";
        String token = bearerToken;

        // Create the URL object
        URL apiUrl = new URL(url);

        // Open a connection
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

        // Set the request method to GET
        connection.setRequestMethod("GET");

        // Set the Authorization header with the authentication token
        connection.setRequestProperty("Authorization", "Bearer " + token);

        // Get the response code
        int responseCode = connection.getResponseCode();

        // If the response code is 200 (OK), read the response body
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder responseBody = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                responseBody.append(line);
            }
            reader.close();

            // Print the response body
            System.out.println(responseBody.toString());
            updatedItem.setUser_id(Integer.parseInt(responseBody.toString()));

            
        } else {
            System.out.println("Request failed with response code: " + responseCode);
        }

        // Close the connection
        connection.disconnect();
    



        //

        Items item = itemService.getItemById(id);
        if (item != null) {
            updatedItem.setId(id);
            Items savedItem = itemService.updateItem(updatedItem);
            return ResponseEntity.ok(savedItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@RequestHeader("Authorization") String bearerToken, @PathVariable("id") int id) throws IOException{
        System.out.println(bearerToken);
        //


        if (bearerToken != null && bearerToken.length() > 7) {
            bearerToken = bearerToken.substring(7);
       }


        String url = "http://localhost:8087/dashboard/userid";
        String token = bearerToken;

        // Create the URL object
        URL apiUrl = new URL(url);

        // Open a connection
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

        // Set the request method to GET
        connection.setRequestMethod("GET");

        // Set the Authorization header with the authentication token
        connection.setRequestProperty("Authorization", "Bearer " + token);

        // Get the response code
        int responseCode = connection.getResponseCode();

        // If the response code is 200 (OK), read the response body
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder responseBody = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                responseBody.append(line);
            }
            reader.close();

            // Print the response body
            System.out.println(responseBody.toString());
        } else {
            System.out.println("Request failed with response code: " + responseCode);
        }

        // Close the connection
        connection.disconnect();
    



        //

        //
        Items item = itemService.getItemById(id);
        if (item != null) {
            itemService.deleteItem(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
