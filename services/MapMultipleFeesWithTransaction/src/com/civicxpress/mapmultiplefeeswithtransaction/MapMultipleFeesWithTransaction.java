/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.mapmultiplefeeswithtransaction;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;


import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;
import com.wavemaker.runtime.service.annotations.HideFromClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import com.fasterxml.jackson.core.type.TypeReference;

import org.apache.commons.io.IOUtils;
import com.fasterxml.jackson.databind.*;

import com.civicxpress.cx2.TransactionToFees;
import com.civicxpress.cx2.service.TransactionToFeesService;

//import com.civicxpress.mapmultiplefeeswithtransaction.model.*;

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
public class MapMultipleFeesWithTransaction {

    private static final Logger logger = LoggerFactory.getLogger(MapMultipleFeesWithTransaction.class);

    @Autowired
    private SecurityService securityService;
    
     @Autowired
    private TransactionToFeesService transactionToFeesService;

     
    ObjectMapper objectMapper = new ObjectMapper();
    
    public void tagFeeWithTransaction(final String feeListString,final Integer transactionId) {
     ObjectMapper objectMapper = new ObjectMapper();
     try {
        Map map = objectMapper.readValue(feeListString, Map.class);
        logger.info(feeListString);
        List list = (List) map.get("content");
        for(int i=0;i<list.size();i++){
            createTable((Map)list.get(i),transactionId);
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    
}


   // method to insert data into table
    public TransactionToFees createTable(Map feeItems,Integer transactionId)
    { 
        TransactionToFees transactionToFees = new TransactionToFees();
        transactionToFees.setFeeId((Integer)feeItems.get("feeId"));
        transactionToFees.setTransactionId(transactionId);
        TransactionToFees persistedTable = transactionToFeesService.create(transactionToFees);
        return persistedTable;
    }

}
