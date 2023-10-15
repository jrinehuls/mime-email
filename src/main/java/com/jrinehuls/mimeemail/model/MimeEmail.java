package com.jrinehuls.mimeemail.model;


import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class MimeEmail {

    private MultipartFile[] files;
    private String from;
    @NotBlank
    @NotNull
    @Email
    private String to;
    private String[] cc;
    private String subject;
    private String body;

}
