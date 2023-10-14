package com.jrinehuls.mimeemail.service.impl;

import com.jrinehuls.mimeemail.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public String sendMimeEmail(MultipartFile[] files, String to, String[] cc, String subject, String body) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setCc(cc);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);

            for (MultipartFile file: files) {
                mimeMessageHelper.addAttachment(Objects.requireNonNull(file.getOriginalFilename()),
                        new ByteArrayResource(file.getBytes()));
            }

            javaMailSender.send(mimeMessage);

            return "Mail sent";
        } catch (MessagingException | IOException | NullPointerException e) {
            throw new RuntimeException(e);
        }

    }
}
