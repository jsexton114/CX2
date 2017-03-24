/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.projecttaskassignment;

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


//import com.civicxpress.projecttaskassignment.model.*;

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
public class ProjectTaskAssignment {

    private static final Logger logger = LoggerFactory.getLogger(ProjectTaskAssignment.class);
     private static final String RESET_NOTIFICATION_MAIL_ID ="civicxpress@gmail.com ";
    private static final String RESET_NOTIFICATION_MAIL_PASSWORD ="civicxpress2016!";
   // private static final String PROJECT_URL = "http://e12561a71473b.cloud.wavemakeronline.com/CivicXpress/#/ViewProject?ProjectGUID=";
   

    @Autowired
    private SecurityService securityService;
    
     public String sendAssignedTaskMail(String username ,String recipient,String emailSubject,String municipalitySignature,String projectName,String projectLink) throws MessagingException {
        
      
        
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
        
        String projectURL=  projectLink;
        logger.info(projectLink);
        
        String emailContent = "Hi" + " "+username+","+"<br />";
        
     
        emailContent =emailContent+"<br />";
        
        emailContent =emailContent + "You have been assigned a task for "+projectName+". You may access the task by clicking the following link:" ;
      
        emailContent =emailContent+"<br />";
        emailContent = emailContent+"<a href ='"+projectURL+ "'> Click Here to View Project </a>";
        
        emailContent =emailContent+ "<br/><br/>"+municipalitySignature ;
        
        message.setSubject(emailSubject);
        message.setContent(emailContent, "text/html");
        // Send smtp message
        Transport tr = session.getTransport("smtp");
        tr.connect("smtp.gmail.com", 587, RESET_NOTIFICATION_MAIL_ID, RESET_NOTIFICATION_MAIL_PASSWORD);
        message.saveChanges();
        tr.sendMessage(message, message.getAllRecipients());
        tr.close();
        String displayMessage = "Message sent successfully to  " + recipient;
        logger.info(displayMessage);
        return displayMessage;
    }

}
