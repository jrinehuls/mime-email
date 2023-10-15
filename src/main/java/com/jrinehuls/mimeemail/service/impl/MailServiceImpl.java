package com.jrinehuls.mimeemail.service.impl;

import com.jrinehuls.mimeemail.model.MimeEmail;
import com.jrinehuls.mimeemail.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public String sendMimeEmail(MimeEmail mimeEmail) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(mimeEmail.getTo());
            mimeMessageHelper.setCc(mimeEmail.getCc());
            mimeMessageHelper.setSubject(mimeEmail.getSubject());
            mimeMessageHelper.setText(mimeEmail.getBody());

            for (MultipartFile file: mimeEmail.getFiles()) {
                mimeMessageHelper.addAttachment(Objects.requireNonNull(file.getOriginalFilename()), file);
            }

            javaMailSender.send(mimeMessage);

            return "Mail sent";
        } catch (MessagingException | NullPointerException e) {
            throw new RuntimeException(e);
        }

    }
}
