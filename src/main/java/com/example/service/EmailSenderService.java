package com.example.service;

import com.example.dto.ApiResponse;
import com.example.entity.EmailHistoryEntity;
import com.example.repository.EmailHistoryRepository;
import com.example.util.HTMLUtil;
import com.example.util.JWTUtil;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import jakarta.mail.internet.MimeMessage;
@Service
public class EmailSenderService {
    @Autowired
    private EmailHistoryRepository emailHistoryRepository;
    @Value("${server.url}")
    private String serverUrl;
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Autowired
    private JavaMailSender javaMailSender;

    void sendMimeEmail(String toAccount, String subject, String text) {
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            msg.setFrom(fromEmail);
            helper.setTo(toAccount);
            helper.setSubject(subject);
            helper.setText(text, true);
            javaMailSender.send(msg);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public ApiResponse sendEmailVerification(String toAccount) {

        ExecutorService executor = Executors.newSingleThreadExecutor();

        String jwt = JWTUtil.encode(toAccount);
//        serverUrl = "http://localhost:8082"; // for email port
        String url = serverUrl + "/auth/verification/email/" + jwt;

        executor.submit(() -> {
            sendMimeEmail(toAccount, "YOUTUBE VERIFICATION", HTMLUtil.getRegistrationButton(url));

            EmailHistoryEntity entity = new EmailHistoryEntity();
            entity.setToEmail(toAccount);
            entity.setMessage(url);
            emailHistoryRepository.save(entity);
            executor.shutdown();
        });
        return new ApiResponse(true, "SUCCESS SEND VERIFICATION PAGE!");
    }
}
