package com.example.travello.service;

import com.example.travello.entity.Mail;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
@Service
public class MailService {

public boolean sendMail(Mail receivedMailData){

        Properties settings = new Properties();
        settings.put("mail.smtp.host","true");
        settings.put("mail.smtp.starttls.enable.","true");
        settings.put("mail.smtp.host","smtp.gmail.com");
        settings.put("mail.smtp.port","587");
        settings.put("mail.smtp.auth","true");
        settings.put("mail.smtp.socketFactory.port", "465");
        settings.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        settings.put("mail.smtp.socketFactory.fallback", "false");


    Session session = Session.getInstance(settings, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("travello.contact@gmail.com","zaq1@WSX");
        }});

        try{
            MimeMessage mail = new MimeMessage(session);
            String to = "jaruzzzelski@gmail.com";

            InternetAddress[] address = InternetAddress.parse(to, true);

            String formettedMail = receivedMailData.getMessage() + "/n Contact e-email address: " + receivedMailData.getEmail();
            mail.setRecipients(Message.RecipientType.TO, address);
            mail.setSubject("Travello App: Mail from: " + receivedMailData.getName() + " Subject: "+ receivedMailData.getSubject());
            mail.setSentDate(new Date());
            mail.setText(formettedMail);

            Transport.send(mail);
            System.out.println("Message sent");
            return true;
        }   catch (MessagingException mex){

            System.out.println("Unable to send message");
            return false;

        }

}


}

