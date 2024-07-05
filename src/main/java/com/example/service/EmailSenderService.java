package com.example.service;

import com.example.entity.EmailHistoryEntity;
import com.example.repository.EmailHistoryRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Service
public class EmailSenderService {
    @Autowired
    private EmailHistoryRepository emailHistoryRepository;
    @Autowired
    private EmailHistoryService emailHistoryService;
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${server.url}")
    private String serverUrl;
    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendMimeEmail(String toAccount, String text) {
        ExecutorService emailExecutor = Executors.newSingleThreadExecutor();
        emailExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    MimeMessage msg=javaMailSender.createMimeMessage();
                    MimeMessageHelper helper=new MimeMessageHelper(msg,true);
                    helper.setTo(toAccount);
                    helper.setSubject("chopar");
                    helper.setText("Xush kelibsiz",text);
                    javaMailSender.send(msg);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        emailExecutor.shutdown();
    }

    public void sendEmailVerification(String toAccount, Integer code) {
        EmailHistoryEntity emailHistoryEntity = new EmailHistoryEntity();
        emailHistoryEntity.setEmail(toAccount);
        emailHistoryService.create(emailHistoryEntity);
        String builder = "<h1 style=\"text-align: center\">Hello %s</h1>" +
                "<p>" +
                " Bu chopar saytiga kirish uchun kod" +
                "</p>" +
                String.format("<h1 style=\"text-align: center\">%s</h1>", code);

        sendMimeEmail(toAccount, builder);
    }
}
