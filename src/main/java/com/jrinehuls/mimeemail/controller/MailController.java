package com.jrinehuls.mimeemail.controller;

import com.jrinehuls.mimeemail.model.MimeEmail;
import com.jrinehuls.mimeemail.service.MailService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/email")
@AllArgsConstructor
public class MailController {

    private MailService mailService;

    @PostMapping("/mime")
    public ResponseEntity<String> sendMimeEmail(@ModelAttribute @Valid MimeEmail mimeEmail) {
        return new ResponseEntity<>(mailService.sendMimeEmail(mimeEmail), HttpStatus.OK);
    }


}
