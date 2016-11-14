/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cxpasswordresetservice;

import javax.servlet.http.HttpServletRequest;
import com.civicxpress.cx2.service.Cx2QueryExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;
import java.util.UUID;

@ExposeToClient
public class CXPasswordResetService {
    private static final Logger logger = LoggerFactory.getLogger(CXPasswordResetService.class);

    @Autowired
    private SecurityService securityService;

    @Autowired
    private Cx2QueryExecutorService queryService;

    public int generateAndSendPasswordTokenForUser(int userID){
        return queryService.executeResetPasswordWithTokenForUser(userID, generateUUID());
    }

    // Validates if the user clicked on the correct token
    // This calls a SQL query on the backend
    public int validateSignupToken(String token){
        return queryService.executeVerifyPasswordResetToken(null,token).getSize();
    }

    // Given a valid token and password, reset user's password
    // This calls a SQL query on the backend
    public boolean resetPasswordUsingToken(String token, String newPassword){
        //This method needs to check if the supplied token is invalid or not.
        return false;
    }

    private String generateUUID(){
        return UUID.randomUUID().toString();
    }
}
