package com.jrinehuls.mimeemail.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class MailController {

    @GetMapping("/")
    public ResponseEntity<String> sayHi() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

}
