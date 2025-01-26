package com.jaiswarup.stocky.controller;

import com.jaiswarup.stocky.model.MyUser;
import com.jaiswarup.stocky.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class UserController {
    public final UserService userService;

    @GetMapping
    public ResponseEntity<List<MyUser>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<MyUser> registerUser(@RequestBody MyUser myUser) {
//        try {
            return ResponseEntity.ok(userService.registerUser(myUser));

    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody MyUser myUser) {
//        try {
        //            return ResponseEntity.ok(token);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
        Object token = userService.loginUser(myUser);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else {
            return ResponseEntity.ok(token);
        }
    }
}
