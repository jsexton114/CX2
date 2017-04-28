/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.gismessagingmailservice;

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
import java.util.ArrayList;
import java.util.*;

//import com.civicxpress.gismessagingmailservice.model.*;

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
public class GisMessagingMailService {

    private static final Logger logger = LoggerFactory.getLogger(GisMessagingMailService.class);
     private static final String RESET_NOTIFICATION_MAIL_ID ="civicxpress@gmail.com ";
    private static final String RESET_NOTIFICATION_MAIL_PASSWORD ="civicxpress2016!";

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
    public String sendMessagingMail(String sender,String comments,String username ,String recipient,String municipality,String subdivision,String municipalitySignature,String address,String gisLink) throws MessagingException {
        
        //logger.info("_______________________________________________________________"+comments);
        
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
        
        //message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        
        //String[] recipient={"sagar.vemala@wavemaker.com","anvesh.nara@wavemaker.com"};
       
     //String recipient="sagar.vemala@wavemaker.com,anvesh.nara@wavemaker.com";
     ArrayList recipientList= new ArrayList(Arrays.asList(recipient.split(",")));


        InternetAddress[] recipientAddress = new InternetAddress[recipientList.size()];
        
         for (int i = 0; i < recipientList.size(); i++)
                 {
                  recipientAddress[i] = new InternetAddress(recipientList.get(i).toString());
                  }
        
        message.setRecipients(Message.RecipientType.TO, recipientAddress);
        
        String emailSubject="New Message on CivicXpress GIS Address "+address;
        
        String gisURL= gisLink;
        logger.info(gisLink);
        
        String emailContent = "You have been tagged by "+sender+" on the following message (on "+municipality+" GisRecord ";
        emailContent = emailContent+"<a href ='"+gisURL+"'>"+address+"</a>):";
      
        emailContent = emailContent+"<br /><br />";
        
        emailContent = emailContent+"<blockquote>"+comments+"</blockquote>";
        
        emailContent = emailContent+"<br /><br />";
        
        emailContent = emailContent+"If you would like to reply, "; 
         
        emailContent = emailContent+"<a href ='"+gisURL+ "'>Click Here</a>";
         
        emailContent = emailContent+" to view the form and write a message.";
         
        emailContent = emailContent+"<br /><br />";
        
        emailContent = emailContent+ "<br/><br/>"+ municipalitySignature ;
        
        message.setSubject(emailSubject);
        message.setContent(emailContent, "text/html");
        // Send smtp message
        Transport tr = session.getTransport("smtp");
        tr.connect("smtp.gmail.com", 587, RESET_NOTIFICATION_MAIL_ID, RESET_NOTIFICATION_MAIL_PASSWORD);
        message.saveChanges();
        tr.sendMessage(message, message.getAllRecipients());
        tr.close();
        String displayMessage = "Message sent successfully ";
        logger.info(displayMessage);
        return displayMessage;
    }

}
