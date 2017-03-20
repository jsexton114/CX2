/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.documentproxyservice;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.client.*;
import org.springframework.http.*;
import java.net.URI;
import java.util.Arrays;
import java.util.Enumeration;

import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;
import com.wavemaker.runtime.service.annotations.HideFromClient;

//import com.civicxpress.documentproxyservice.model.*;

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
public class DocumentProxyService {

    private static final Logger logger = LoggerFactory.getLogger(DocumentProxyService.class);

    @Autowired
    private SecurityService securityService;
    
    private static String serviceHost = "localhost";
    private static Integer servicePort = 19000;
    private static String apiPath = "/documents-service/api/";
    
    // This service stands as a proxy from the application to the LEADTOOLS documents-service. This file should remain unedited.
    
    public String forwardRequest(String body, HttpMethod method, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] uriParts = request.getRequestURI().split("/");
        String requestPath = "/documents-service/api/" + uriParts[uriParts.length - 2] + "/" + uriParts[uriParts.length - 1];

        URI uri = new URI("http", null, "localhost", 19000, requestPath, request.getQueryString(), null);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, method, new HttpEntity<String>(body, headers), String.class);

        return responseEntity.getBody();
    }
}
