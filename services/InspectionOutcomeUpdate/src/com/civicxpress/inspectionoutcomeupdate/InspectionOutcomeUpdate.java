/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.inspectionoutcomeupdate;

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
import java.util.*;

//import com.civicxpress.inspectionoutcomeupdate.model.*;

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
public class InspectionOutcomeUpdate {

    private static final Logger logger = LoggerFactory.getLogger(InspectionOutcomeUpdate.class);
      private static final String RESET_NOTIFICATION_MAIL_ID ="civicxpress@gmail.com ";
    private static final String RESET_NOTIFICATION_MAIL_PASSWORD ="civicxpress2016!";

    @Autowired
    private SecurityService securityService;

    public String sendOutcomeUpdateMail(String username ,String recipient,String emailSubject,String emailBody,String municipality,String inspectionDesign,String feesAssessed,String lot,String fullAddress,String inspectionOutcome,String subdivision,String municipalitySignature,String inspectionTitle,String formLink) throws MessagingException {
        
      
        
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
        
        ArrayList recipientList= new ArrayList(Arrays.asList(recipient.split(",")));


        InternetAddress[] recipientAddress = new InternetAddress[recipientList.size()];
        
         for (int i = 0; i < recipientList.size(); i++)
          {
             recipientAddress[i] = new InternetAddress(recipientList.get(i).toString());
         }
        
        message.setRecipients(Message.RecipientType.TO, recipientAddress);
        
        String formURL=  formLink;
        logger.info(formLink);
        
        String emailContent = "Hi" + " "+username+","+"<br /><br />";
        
        emailContent =emailContent + emailBody ;
        emailContent =emailContent+"<br /><br />";
        
        emailContent =emailContent + municipality ;
        emailContent =emailContent+"<br />";
        emailContent =emailContent + inspectionDesign ;
        emailContent =emailContent+"<br />";
        emailContent =emailContent + inspectionTitle ;
        emailContent =emailContent+"<br />";
         emailContent =emailContent + fullAddress ;
        emailContent =emailContent+"<br />";
         emailContent =emailContent + subdivision ;
        emailContent =emailContent+"<br />";
         emailContent =emailContent + lot ;
        emailContent =emailContent+"<br />";
         emailContent =emailContent + inspectionOutcome ;
        emailContent =emailContent+"<br />";
         emailContent =emailContent + feesAssessed ;
        emailContent =emailContent+"<br />";
        emailContent = emailContent+"<a href ='"+formURL+ "'> "+formURL+" </a>";
        
        emailContent =emailContent+ "<br/><br/>"+ municipalitySignature +"<br/><br/>";
        
        message.setSubject(emailSubject);
        message.setContent(emailContent, "text/html");
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
