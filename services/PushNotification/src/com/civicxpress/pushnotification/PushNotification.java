/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.pushnotification;

import javax.servlet.http.HttpServletRequest;

import com.civicxpress.cx2.UserDeviceDetails;
import com.civicxpress.cx2.service.UserDeviceDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.File;
import java.util.*;
import org.joda.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import org.codehaus.jackson.map.ObjectMapper;
import javapns.devices.Devices;
import javapns.notification.*;
import javapns.notification.PushedNotifications;
 
import com.google.android.gcm.server.*;
 
import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;
import com.wavemaker.runtime.service.annotations.HideFromClient;
import org.springframework.data.domain.Page;

//import com.civicxpress.pushnotification.model.*;

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
public class PushNotification {

    private static final Logger logger = LoggerFactory.getLogger(PushNotification.class);
    
    //Android
	private String apiKey = "AIzaSyChi0qlJeVdswiS6SaEH8kg68Dh0ACBCjw";
	private String senderId = "950799566941";
 
	//ios
	private String iosCertificateName = "Certificates.p12";
	private String iosCertificateKey = "xxxxxx";
    
    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private UserDeviceDetailsService userDeviceDetailsService;

    public void registerDevice(String deviceId, String deviceOs, Integer userId, String deviceUUID, String deviceModel) {
    	UserDeviceDetails userDeviceDetails = new UserDeviceDetails();
    	LocalDateTime date = new LocalDateTime();
    	userDeviceDetails.setDeviceId(deviceId);
    	userDeviceDetails.setDeviceOs(deviceOs);
    	userDeviceDetails.setUserId(userId);
    	userDeviceDetails.setDeviceUuid(deviceUUID);
    	userDeviceDetails.setDeviceModel(deviceModel);
    	userDeviceDetails.setDevicelastContactTime(date);
    	userDeviceDetails.setLastLogin(date);
    	logger.info(userDeviceDetails.toString());
    	userDeviceDetailsService.create(userDeviceDetails); 
	}
	
	public void unregisterDevice(String deviceId,Integer userId, String deviceOs) {
    	UserDeviceDetails userDeviceDetails = new UserDeviceDetails();
    	userDeviceDetails = userDeviceDetailsService.getByDeviceIdAndUserIdAndDeviceOs(deviceId, userId, deviceOs);
    	userDeviceDetailsService.delete(userDeviceDetails.getId());
	}
	
	private void sendIOSNotification(List<String> deviceIds, String message) {
    	boolean production = false;
    	String iosCertificate = this.getClass().getResource("").getPath() + iosCertificateName;
 
    	try {
            	File file = new File(iosCertificate);
            	logger.info(iosCertificate);
            	AppleNotificationServer jksServer = new AppleNotificationServerBasicImpl(file, iosCertificateKey, production);
            	PushNotificationPayload payload = PushNotificationPayload.alert(message);
            	PushNotificationManager pushManager = new PushNotificationManager();
            	pushManager.initializeConnection(jksServer);
            	PushedNotifications notifications = pushManager.sendNotifications(payload, Devices.asDevices(deviceIds));
 
        	} catch (Exception e) {
            	e.printStackTrace();
        	}
 
	}
	
	
	private void sendAndroidNotification(List<String> deviceIds, String message) {
    	try {
        	Sender sender = new Sender(apiKey);
        	// use this to send message with payload data
        	Message messageObj = new Message.Builder()
                	.collapseKey("message")
                	.timeToLive(3)
                	.delayWhileIdle(true)
                	.addData("message", message) //you can get this message on client side app
                	.build();
 
        	//Use this code for multicast messages
        	MulticastResult multicastResult = sender.send(messageObj, deviceIds, 1);
   	}
    	catch (Exception e) {
        	e.printStackTrace();
    	}
 
	}
 
 
  public void notify(String[] message, String deviceToken, String deviceOS) {
    	try {
        	
 
        	List<String> androidDevices = new ArrayList<String>();
        	List<String> iosDevices = new ArrayList<String>();
        	int size = message.length;
            for (int i=0;i<size;i++){
                if(deviceOS.equals("Android")){
                    androidDevices.add(deviceToken);
                    sendAndroidNotification(androidDevices, message[i]);;
                }
                else{
                    iosDevices.add(deviceToken);
                    sendIOSNotification(iosDevices, message[i]);
                }
            }
                
            }
        	
    	catch(Exception e) {
        	e.printStackTrace();
    	}
    
	}
 

   

}
