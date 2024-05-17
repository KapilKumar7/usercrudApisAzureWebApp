package com.example.demo.Services;

import com.example.demo.Models.Users;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> createUserAccount(Users user) {
        try {
            // Validate user
            userValidation(user);
            user.setCreatedAt(new Date());
            user.setUpdatedAt(new Date());
            user.setEmail(user.getEmail().toLowerCase());
            user.setFirstName(user.getFirstName().toLowerCase());
            user.setLastName(user.getLastName().toLowerCase());

            Users savedUser = userRepository.save(user);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity<?> deleteUserAccount(String userName) {
        try {
            if (userName == null || userName.isEmpty()) {
                throw new IllegalArgumentException("First name is required to delete the user account");
            }

            Users user = userRepository.findByFirstName(userName.toLowerCase());
            if (user == null) {
                throw new IllegalArgumentException("User not found for the given user name: " + userName);
            }

            userRepository.deleteByEmail(user.getEmail());
            return ResponseEntity.ok("User account deleted successfully for the user name: " + userName + " and email: " + user.getEmail() );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    public ResponseEntity<?> getUserAccount(String userName) {
        try {
            if (userName == null || userName.isEmpty()) {
                throw new IllegalArgumentException("First name is required to get the user account");
            }

            Users user = userRepository.findByFirstName(userName.toLowerCase() );
            if (user == null) {
                throw new IllegalArgumentException("User not found for the given user name: " + userName);
            }

            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    private void userValidation(Users user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null or empty");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }

        Users existingUser = userRepository.findByEmail(user.getEmail().toLowerCase()).orElse(null);
        if (existingUser != null) {
            throw new IllegalArgumentException("User already exists with the given email: " + user.getEmail());
        }

        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First Name is required");
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Last Name is required");
        }
    }
}
