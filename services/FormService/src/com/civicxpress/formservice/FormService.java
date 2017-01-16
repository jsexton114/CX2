/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.formservice;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;
import com.wavemaker.runtime.service.annotations.HideFromClient;

import com.civicxpress.cx2.service.Cx2QueryExecutorService;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import com.wavemaker.runtime.data.model.CustomQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.civicxpress.formservice.model.*;

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
public class FormService {

    private static final Logger logger = LoggerFactory.getLogger(FormService.class);

    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private Cx2QueryExecutorService cx2QueryExecutorService;

    /**
     * This is sample java operation that accepts an input from the caller and responds with "Hello".
     *
     * SecurityService that is Autowired will provide access to the security context of the caller. It has methods like isAuthenticated(),
     * getUserName() and getUserId() etc which returns the information based on the caller context.
     *
     * Methods in this class can declare HttpServletRequest, HttpServletResponse as input parameters to access the
     * caller's request/response objects respectively. These parameters will be injected when request is made (during API invocation).
     */
    public String sampleJavaOperation(String name, HttpServletRequest request) {
        logger.debug("Starting sample operation with request url " + request.getRequestURL().toString());
        
        String result = null;
        if (securityService.isAuthenticated()) {
            result = "Hello " + name + ", You are logged in as "+  securityService.getLoggedInUser().getUserName();
        } else {
            result = "Hello " + name + ", You are not authenticated yet!";
        }
        logger.debug("Returning {}", result);
        return result;
    }
    
    public Map<String, Object> getFormData(Long municipalityId, Long formTypeId, String formGuid) throws SQLException {
    	String dbName;
    	String dbUser;
    	String dbPassword;
    	String formTableName;
    	
    	String getMuniDbDetailsQuery = "SELECT DbName, DbUser, DbPassword FROM Municipalities WHERE ID="+municipalityId.toString();
    	String getFormTableNameQuery = "SELECT FormTableName FROM FormTypes WHERE ID="+formTypeId.toString();
        
        CustomQuery muniDbDetailsQuery = new CustomQuery();
        muniDbDetailsQuery.setQueryStr(getMuniDbDetailsQuery);
        muniDbDetailsQuery.setNativeSql(true);
        @SuppressWarnings("unchecked")
		HashMap<String, Object> muniDetails = (HashMap<String, Object>) cx2QueryExecutorService.executeWMCustomQuerySelect(muniDbDetailsQuery, null).getContent().get(0);
        
        dbName = (String) muniDetails.get("DbName");
		dbUser = (String) muniDetails.get("DbUser");
		dbPassword = (String) muniDetails.get("DbPassword");
		
		CustomQuery formTableNameQuery = new CustomQuery();
		formTableNameQuery.setQueryStr(getFormTableNameQuery);
		formTableNameQuery.setNativeSql(true);
		formTableName = (String) ((HashMap<String, Object>) cx2QueryExecutorService.executeWMCustomQuerySelect(formTableNameQuery, null).getContent().get(0)).get("FormTableName");
		
		SQLServerDataSource sds = new SQLServerDataSource();
		sds.setUser(dbUser);
		sds.setPassword(dbPassword);
		sds.setServerName("64.87.23.26");
		sds.setPortNumber(1433);
		sds.setDatabaseName(dbName);
		Connection conn = sds.getConnection();
		
		Statement statement = conn.createStatement();
		ResultSet formData = statement.executeQuery("SELECT * FROM "+dbName+"."+formTableName+" WHERE FormGUID='"+formGuid+"'");
		HashMap<String, Object> formDataHashMap = new HashMap<String, Object>();
		ResultSetMetaData formDataMD = formData.getMetaData();
		int colCount = formDataMD.getColumnCount();
		if (formData.next()) {
		    for (int i = 1; i <= colCount; i++) {
			    formDataHashMap.put(formDataMD.getColumnLabel(i), formData.getObject(i));
		    }
		}
		
		conn.close();
        
        return formDataHashMap;
    }

}
