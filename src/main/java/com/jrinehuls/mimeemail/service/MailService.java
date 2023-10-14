package com.jrinehuls.mimeemail.service;

import org.springframework.web.multipart.MultipartFile;

public interface MailService {
    String sendMimeEmail(MultipartFile[] files, String to, String[] cc, String subject, String body);
}
