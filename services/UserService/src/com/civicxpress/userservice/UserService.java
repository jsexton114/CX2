/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.userservice;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import com.tekdog.dbutils.*;

//import com.civicxpress.userservice.model.*;

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
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private SecurityService securityService;
    
    @Value("${cx2.url}")
    private String sqlUrl;
    
    @Value("${cx2.username}")
    private String defaultSqlUser;
    
    @Value("${cx2.schemaName}")
    private String defaultSqlDatabase;
    
    @Value("${cx2.password}")
    private String defaultSqlPassword = "F!yingFishCove1957";

    /**
     * This is sample java operation that accepts an input from the caller and responds with "Hello".
     *
     * SecurityService that is Autowired will provide access to the security context of the caller. It has methods like isAuthenticated(),
     * getUserName() and getUserId() etc which returns the information based on the caller context.
     *
     * Methods in this class can declare HttpServletRequest, HttpServletResponse as input parameters to access the
     * caller's request/response objects respectively. These parameters will be injected when request is made (during API invocation).
     */
    
    public void saveUserPhoto(MultipartFile photo[], Long userId) throws Exception {
    	Connection cx2Conn = DBUtils.getConnection(sqlUrl, defaultSqlUser, defaultSqlPassword);
    	DBQueryParams queryParams = new DBQueryParams();
    	
    	if ((userId == null || !userId.equals(Long.parseLong(securityService.getUserId())))  && !Arrays.asList(securityService.getUserRoles()).contains("CXAdmin")) {
    		throw new Exception("You may only update your own photo.");
    	}
    	
    	queryParams.addLong("userId", userId);
    	if (photo != null && photo.length > 0) {
    		queryParams.addBytes("newPhoto", photo[0].getBytes());
    	} else {
    		queryParams.addObject("newPhoto", null);
    	}
    	
    	DBUtils.simpleUpdateQuery(cx2Conn, "UPDATE Users SET photo=:newPhoto WHERE ID=:userId", queryParams);
    }
}
