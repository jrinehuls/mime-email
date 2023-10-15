package com.jrinehuls.mimeemail.service;

import com.jrinehuls.mimeemail.model.MimeEmail;

public interface MailService {
    String sendMimeEmail(MimeEmail mimeEmail);
}
