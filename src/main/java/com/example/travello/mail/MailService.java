package com.example.travello.mail;

import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
@Service
public class MailService {

    private static String travelloMail = "travello.contact@gmail.com";

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
                MimeMessage mail;
                if(receivedMailData.getType() == 0){
                    mail = composeBusinessMail(receivedMailData, session);
                } else if( receivedMailData.getType() == 1){
                    mail = composeWarningMail(receivedMailData, session);
                } else {
                    mail = composeMailFromUser(receivedMailData, session);
                }

                Transport.send(mail);
                return true;
            }   catch (MessagingException mex){
                return false;
            }

    }

    private MimeMessage composeWarningMail(Mail receivedMail, Session session) throws MessagingException {
        MimeMessage mail = new MimeMessage(session);
        String to = receivedMail.getUserEmail();
        InternetAddress[] address = InternetAddress.parse(to, true);

        String defaultWarningMessage = "[WARNING MESSAGE FROM TRAVELLO TEAM. YOUR TRIP HAS BEEN BLOCKED.]";
        String defaultSubjectMessage = "[WARNING]";

        String formattedMail = defaultWarningMessage
                .concat("\n\n")
                .concat(receivedMail.getMessage())
                .concat("\n\n Contact e-mail address: ")
                .concat(receivedMail.getEmail());

        mail.setRecipients(Message.RecipientType.TO, address);
        mail.setSubject(defaultSubjectMessage.concat(" ").concat(receivedMail.getSubject()));
        mail.setSentDate(new Date());
        mail.setText(formattedMail);

        return mail;
    }

    private MimeMessage composeBusinessMail(Mail receivedMail, Session session) throws MessagingException {
        MimeMessage mail = new MimeMessage(session);
        String to = travelloMail;
        InternetAddress[] address = InternetAddress.parse(to, true);

        String formattedMail = receivedMail.getMessage() + "\n\n Contact e-mail address: " + receivedMail.getEmail();
        mail.setRecipients(Message.RecipientType.TO, address);
        mail.setSubject("[TRAVELLO BUSINESS] Mail from: " + receivedMail.getName() + ", Subject: "+ receivedMail.getSubject());
        mail.setSentDate(new Date());
        mail.setText(formattedMail);

        return mail;
    }

    private MimeMessage composeMailFromUser(Mail receivedMail, Session session) throws MessagingException {
        MimeMessage mail = new MimeMessage(session);
        String to = travelloMail;
        InternetAddress[] address = InternetAddress.parse(to, true);

        String userInfo = "[USER LOGIN: " + receivedMail.getName() +
                ", USER E-MAIL: " + receivedMail.getUserEmail() +
                ", TRIP ID: " +  receivedMail.getTripId() + "]" ;

        String formattedMail = userInfo
                .concat("\n\n")
                .concat(receivedMail.getMessage());

        mail.setRecipients(Message.RecipientType.TO, address);
        mail.setSubject("[USER CONTACT] Mail from: " + receivedMail.getName() + ", Subject: "+ receivedMail.getSubject());
        mail.setSentDate(new Date());
        mail.setText(formattedMail);

        return mail;
    }

}

