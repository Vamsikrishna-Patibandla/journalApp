package net.vamsikrishna.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendEmail(){
        emailService.sendMail("vamsikrishna11122000@gmail.com",
                "Testing java mail sender",
                "Hi Krishna, How are you? \n \n System Generated Mail please don't reply");
    }

    @Autowired
    private JavaMailSender mailSender;

    @Disabled
    @Test
    void sendRealEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("pvk11122000@gmail.com");  // Replace with your address
        message.setSubject("Test Email from Spring Boot");
        message.setText("This is a test email sent from Java.");
        message.setFrom("vkpat.dev@gmail.com");

        mailSender.send(message);
        System.out.println("Email sent successfully!");
    }
}
