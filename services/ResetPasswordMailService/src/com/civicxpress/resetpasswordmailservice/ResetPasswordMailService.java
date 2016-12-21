/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.resetpasswordmailservice;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;


import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;
import com.wavemaker.runtime.service.annotations.HideFromClient;

import java.util.Properties;   
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//import com.civicxpress.resetpasswordmailservice.model.*;

/**
 * This is a singleton class with all its public methods exposed as REST APIs via generated controller class.
 * To avoid exposing an API for a particular public method, annotate it with @HideFromClient.
 *
 * Method names will play a major role in defining the Http Method for the generated APIs. For example, a method name
 * that starts with delete/remove, will make the API exposed as Http Method "DELETE".
 *
 * Method Parameters of type primitives (including java.lang.String) will be exposed as Query Parameters &
 * Complex Types/Objects will become part of the Request body in the generated API.
 */
@ExposeToClient
public class ResetPasswordMailService {

    private static final Logger logger = LoggerFactory.getLogger(ResetPasswordMailService.class);
    private static final String RESET_NOTIFICATION_MAIL_ID ="civicxpress@gmail.com ";
    private static final String RESET_NOTIFICATION_MAIL_PASSWORD ="civicxpress2016!";
    private static final String EMAIL_SUBJECT = "Account Password Reset";
    private static final String EMAIL_SUBJECT_WELCOME = "Welcome to CivicXpress";
    private static final String RESET_URL = "http://e12561a71473b.cloud.wavemakeronline.com/CivicXpress/#/ResetPassword?token=";
    
    @Autowired
    private SecurityService securityService;

    /**
     * This is sample java operation that accepts an input from the caller and responds with "Hello".
     *
     * SecurityService that is Autowired will provide access to the security context of the caller. It has methods like isAuthenticated(),
     * getUserName() and getUserId() etc which returns the information based on the caller context.
     *
     * Methods in this class can declare HttpServletRequest, HttpServletResponse as input parameters to access the
     * caller's request/response objects respectively. These parameters will be injected when request is made (during API invocation).
     */
    public String sendEmail(String recipient, String token) throws MessagingException {
        
        String emailBody = "CivicXpress User, <br /><br />It looks like somebody forgot their password!  No problem, you can reset your password using the following link. <br /><br />";
        
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.enabled","true");
        props.put("mail.imap.ssl.enabled", "true");

        Session session = Session.getDefaultInstance(props, null);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(RESET_NOTIFICATION_MAIL_ID));
        
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        
        String resetUrl = RESET_URL + token;
        emailBody += "\n <a href ='"+resetUrl+ "'> Click Here to Reset </a> <br /><br />";
        emailBody += "This link is only good for the next 48 hours, after which time it will expire like old milk!  Yuck!<br /><br />Your friends at CivicXpress<br />support@tekdoginc.com<br />614-737-3743<br />";
        
        message.setSubject(EMAIL_SUBJECT);
        message.setContent(emailBody, "text/html");
        // Send smtp message
        Transport tr = session.getTransport("smtp");
        tr.connect("smtp.gmail.com", 587, RESET_NOTIFICATION_MAIL_ID, RESET_NOTIFICATION_MAIL_PASSWORD);
        message.saveChanges();
        tr.sendMessage(message, message.getAllRecipients());
        tr.close();
        String displayMessage = "Message sent successfully with body " + emailBody;
        logger.info(displayMessage);
        return displayMessage;
    }
    
     public String sendWelcomeEmail(String username ,String recipient) throws MessagingException {
        
        String emailBody = "Hi" + " "+username+","+"<br /><br />";
        
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.enabled","true");
        props.put("mail.imap.ssl.enabled", "true");

        Session session = Session.getDefaultInstance(props, null);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(RESET_NOTIFICATION_MAIL_ID));
        
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        
        emailBody += "Thank you for creating a CivicXpress account.  You can now subscribe to participating CivicXpress municipalities and start submitting digital forms today!  We strongly recommend you checkout our FREE CivicXpress training available under the support link once you’ve login to CivicXpress.  If you have any questions about your CX account, please feel free to contact support from our support page located within CivicXpress.  If you have any questions about your submitted forms please contact the municipality you submitted your form to as they will be your primary contact for anything related to submitted forms and their associated processes.  Once again, thank you for joining the CivicXpress community!<br /><br />";
        emailBody += "Thank you!<br/>CivicXpress Support Team<br/>";
        message.setSubject(EMAIL_SUBJECT_WELCOME);
        message.setContent(emailBody, "text/html");
        // Send smtp message
        Transport tr = session.getTransport("smtp");
        tr.connect("smtp.gmail.com", 587, RESET_NOTIFICATION_MAIL_ID, RESET_NOTIFICATION_MAIL_PASSWORD);
        message.saveChanges();
        tr.sendMessage(message, message.getAllRecipients());
        tr.close();
        String displayMessage = "Message sent successfully with body " + emailBody;
        logger.info(displayMessage);
        return displayMessage;
    }

}
