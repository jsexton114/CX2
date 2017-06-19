/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.companyservice;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import com.civicxpress.dbconnectionservice.DBConnectionService;
import com.civicxpress.dynamicfieldservice.DynamicFieldService;
import com.tekdog.dbutils.DBQueryParams;
import com.tekdog.dbutils.DBRow;
import com.tekdog.dbutils.DBUtils;

import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;
import com.wavemaker.runtime.service.annotations.HideFromClient;

//import com.civicxpress.companyservice.model.*;

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
public class CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);

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
    // Attachments
    public void uploadDocuments(MultipartFile[] files, Integer municipalityId, Integer vendorId) throws SQLException, IOException {
    	Connection cx2Conn = DBConnectionService.getConnection();
    	cx2Conn.setAutoCommit(false);
    	
    	try {
	    	StringBuilder documentAddQuery = new StringBuilder("INSERT INTO Document (MunicipalityId, VendorId, Filename, Mimetype, Contents, CreatedBy) VALUES ");
	    	
	    	DBQueryParams queryParams = new DBQueryParams();
	    	queryParams.addInteger("municipalityId", municipalityId);
	    	queryParams.addInteger("vendorId", vendorId);
	    	queryParams.addLong("createdBy", Long.parseLong(securityService.getUserId()));
	
	        for (int i = 0; i < files.length; i++) {
	        	MultipartFile file = files[i];
	        	
	        	if (i > 0) {
	        		documentAddQuery.append(',');
	        	}
				
	        	queryParams.addString("doc"+i+"filename", file.getOriginalFilename());
	        	queryParams.addString("doc"+i+"mimetype", file.getContentType());
	        	queryParams.addBytes("doc"+i+"contents", file.getBytes());
	        	
	        	documentAddQuery.append("(:municipalityId,:vendorId, :doc"+i+"filename, :doc"+i+"mimetype, :doc"+i+"contents, :createdBy)");
	        }
	        
	        if (files.length > 0) {
	        	DBUtils.simpleUpdateQuery(cx2Conn, documentAddQuery.toString(), queryParams);
	        }
	        
	        cx2Conn.commit();
    	} catch (Exception e) {
    		cx2Conn.rollback();
    		throw e;
    	} finally {
    		cx2Conn.close();
    	}
    }

}
