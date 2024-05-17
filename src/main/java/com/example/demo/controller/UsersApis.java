package com.example.demo.controller;

import com.example.demo.Models.Users;
import com.example.demo.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/api/users")
public class UsersApis {

    @Autowired
    private UsersService usersService;

    @PostMapping("/account/save")
    public ResponseEntity<?> saveUserAccount(@RequestBody Users user){
        return usersService.createUserAccount(user);
    }

    @GetMapping("/account/get")
    public ResponseEntity<?> getUserAccount(@RequestParam String firstName ){
        return usersService.getUserAccount(firstName);
    }

    @DeleteMapping("/account/delete")
    public ResponseEntity<?> deleteUserAccount(@RequestParam String firstName ){
        return usersService.deleteUserAccount(firstName);
    }

    // Healthcheck API
    @GetMapping("/healthCheck")
    public ResponseEntity<?> healthCheck(){
        return ResponseEntity.ok("Health Check is Success");
    }

}
