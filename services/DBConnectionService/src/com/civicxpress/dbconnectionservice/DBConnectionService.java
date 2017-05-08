/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.dbconnectionservice;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.wavemaker.runtime.service.annotations.ExposeToClient;
import com.wavemaker.runtime.service.annotations.HideFromClient;

import java.sql.Connection;
import java.sql.SQLException;

import com.tekdog.dbutils.*; 

//import com.civicxpress.dbconnectionservice.model.*;

@HideFromClient
public class DBConnectionService {

    private static final Logger logger = LoggerFactory.getLogger(DBConnectionService.class);
    
    private static String sqlUrl;// = "jdbc:sqlserver://64.87.23.26:1433;databaseName=cx2";
    private static String sqlUser = "cx2";
    private static String sqlSchema;
    private static String sqlPassword;// = "F!yingFishCove1957";

    @Value("${cx2.url}")
    public void setSqlUrl(String url) {
    	sqlUrl = url;
    }
    
    @Value("${cx2.username}")
    public void setUsername(String username) {
    	sqlUser = username;
    }
    
    @Value("${cx2.schemaName}")
    public void setSchemaName(String schemaName) {
    	sqlSchema = schemaName;
    }
    
    @Value("${cx2.password}")
    public void setPassword(String password) {
    	sqlPassword = password;
    }
    
    @ExposeToClient
    public void generateController() {} // Method that is exposed to client but does nothing. This is used to ensure that Wavemaker puts the @Autowired in the controller that we need for values to set properly.

    public static Connection getConnection() throws SQLException {
        return DBUtils.getConnection(sqlUrl, sqlUser, sqlPassword);
    }
    
    public static Connection getMunicipalityDBConnection(Long municipalityId) throws SQLException {
    	Connection cx2Conn = getConnection();
    	String getMuniDbDetailsQuery = "SELECT DbName, DbUser, DbPassword FROM Municipalities WHERE ID=:municipalityId";
    	DBQueryParams muniDbDetailsParams = new DBQueryParams();
        muniDbDetailsParams.addLong("municipalityId", municipalityId);
    	
    	DBRow muniDetails = DBUtils.selectOne(cx2Conn, getMuniDbDetailsQuery, muniDbDetailsParams);
    	
    	String muniUrl = cx2Conn.getMetaData().getURL().replaceAll("databaseName=.+", "databaseName="+muniDetails.getString("DbName"));
    	
    	cx2Conn.close();
    	
    	return DBUtils.getConnection(muniUrl, muniDetails.getString("DbUser"), muniDetails.getString("DbPassword"));
    }
}
