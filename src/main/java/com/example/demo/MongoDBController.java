package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MongoDBController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("mongodb/store/{userID}")
    public ResponseEntity<String> storeDataToMongoDB(@RequestBody String body, @PathVariable String userID) {
        mongoTemplate.save(body+userID, "mealPlans" );
        return new ResponseEntity<>("Data stored successfully for user " + userID, HttpStatus.OK);
    }

    @GetMapping("/mongodb/fetch/{userID}")
    public ResponseEntity<String> fetchDataFromMongoDB(@PathVariable String userID) {
        String data = mongoTemplate.findAll(String.class, "mealPlans" ).toString();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
