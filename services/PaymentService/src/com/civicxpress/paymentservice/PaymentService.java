/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.paymentservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;


import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;
import com.wavemaker.runtime.service.annotations.HideFromClient;

//import com.civicxpress.paymentservice.model.*;

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
public class PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

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
    public void chargeCreditCard(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Starting chargeCreditCard() with request url " + request.getRequestURL().toString());
        String stripeToken = request.getParameter("stripeToken");
        logger.debug("stripeToken: " + stripeToken);
    }
    
    public void callback(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        logger.debug("Callback invoked");
 	    try {
 	          //String page="you want to redirect ";
 	          //page="Main";
 	        //response.sendRedirect(request.getContextPath() + "#/" + page);
 	        //response.sendRedirect("https://www.wavemakeronline.com/run-j4lrgncwgl/CivicXpress/#/Main");
 	        
            String stripeToken = request.getParameter("stripeToken");
            logger.debug("stripeToken: " + stripeToken);

 	    
 	    } catch (Exception ex) {
 	        throw new ServletException(ex);
 	    }
    }

}