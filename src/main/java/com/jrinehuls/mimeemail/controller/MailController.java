package com.jrinehuls.mimeemail.controller;

import com.jrinehuls.mimeemail.service.MailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;


@RestController
@RequestMapping("/api/email")
@AllArgsConstructor
public class MailController {

    private MailService mailService;

    @PostMapping("/mime")
    public ResponseEntity<String> sendMimeEmail(@RequestParam(value = "file", required = false) MultipartFile file) {
        return new ResponseEntity<>("fileString", HttpStatus.OK);
    }

    @PostMapping("/b64")
    public ResponseEntity<String> encodeFileB64(@RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            byte[] bytes = Base64.getEncoder().encode(file.getBytes());
            String fileString = new String(bytes);
            return new ResponseEntity<>(fileString, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
