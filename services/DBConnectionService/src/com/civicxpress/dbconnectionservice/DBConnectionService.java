/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.dbconnectionservice;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;

import com.wavemaker.runtime.service.annotations.HideFromClient;

import java.sql.Connection;
import java.sql.SQLException;

import com.tekdog.dbutils.*; 

//import com.civicxpress.dbconnectionservice.model.*;

@HideFromClient
public class DBConnectionService {

    private static final Logger logger = LoggerFactory.getLogger(DBConnectionService.class);

    @Value("${cx2.url}")
    private String sqlUrl = "jdbc:sqlserver://64.87.23.26:1433;databaseName=cx2";
    
    @Value("${cx2.username}")
    private String sqlUser = "cx2";
    
    @Value("${cx2.schemaName}")
    private String sqlSchema;
    
    @Value("${cx2.password}")
    private String sqlPassword = "F!yingFishCove1957";

    private static DBConnectionService singleton = null;
    
    public static Connection getConnection() throws SQLException {
        if (singleton == null) {
            singleton = new DBConnectionService();
        }
        
        return DBUtils.getConnection(singleton.sqlUrl, singleton.sqlUser, singleton.sqlPassword);
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
