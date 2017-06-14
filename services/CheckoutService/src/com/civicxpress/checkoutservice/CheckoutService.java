/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.checkoutservice;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;   
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import com.civicxpress.dbconnectionservice.DBConnectionService;
import com.tekdog.dbutils.*;
import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;

import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;

//import com.civicxpress.checkoutservice.model.*;

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
public class CheckoutService {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutService.class);

    @Autowired
    private SecurityService securityService;

    public void municipalityCheckout(Long municipalityId, String paymentMethod, String paymentNumber, BigDecimal amountReceived, String comments, Long[] feeIds) throws Exception {
        DBQueryParams queryParams = new DBQueryParams();
    	Connection connection = DBConnectionService.getConnection();
    	CallableStatement checkoutStatement = null;
    	
    	queryParams.addString("comments",  comments);
    	
    	BigDecimal amountDue = DBUtils.selectOne(connection, "SELECT SUM(Amount) as amountDue FROM Fees WHERE ID IN ("+StringUtils.join(feeIds, ',')+")", null).getBigDecimal("amountDue");
    	
    	if (amountReceived.compareTo(amountDue) == -1) { // Ensure we are not getting swindled.
    		throw new Exception("The amount received is not enough to pay for the chosen fees.");
    	}
    	
    	int currentUserId = Integer.parseInt(securityService.getUserId());
    	
    	System.out.println("amountReceived: " + amountReceived);
    	System.out.println("amountDue: " + amountDue);
    	
    	try {
    	    Boolean transactionSuccess = false;
	    	connection.setAutoCommit(false);
	    	
	    	if (paymentMethod.equals("Credit Card")) {
	    	    transactionSuccess = chargeCreditCard(amountReceived, "Municipality fees: " + comments, paymentNumber);
	    	} else {
	    	    transactionSuccess = true;
	    	}
	    	
	    	if (transactionSuccess) {
    	        checkoutStatement = connection.prepareCall("{call municipalityCheckout(?,?,?,?,?,?)}");
    	        checkoutStatement.setString("paymentMethod", paymentMethod);
    	        checkoutStatement.setString("paymentNumber", paymentNumber);
    	        checkoutStatement.setBigDecimal("amountReceived", amountReceived);
    	        checkoutStatement.setString("comments", comments);
    	        checkoutStatement.setInt("createdBy", currentUserId);
    	        checkoutStatement.registerOutParameter("transactionId", java.sql.Types.NUMERIC);
    	        checkoutStatement.execute();
    	        
    	        Long transactionId = checkoutStatement.getLong("transactionId");
    	        
    	        for (Long feeId : feeIds) { // Mark fees as paid.
    	        	CallableStatement feeStatement = DBUtils.prepareProcedure(connection, "payFee", feeId, transactionId, currentUserId);
    	        	feeStatement.execute();
    // Updating TransactionComments
    	        		DBUtils.simpleUpdateQuery(connection, " UPDATE Fees SET TransactionComments=:comments where ID="+feeId,
        				queryParams);
    	        
    	        }
	        }
	        
	        connection.commit();
    	} catch (Exception e) {
    		connection.rollback();
    		throw e;
    	} finally {
    		connection.close();
    	}
    }
    
    private Boolean chargeCreditCard(BigDecimal amount, String description, String stripeToken) {
        Boolean returnSuccess = false;
        Charge charge = null;
        Map<String, Object> params = null;
        Long amountInCents = amount.longValue() * 100;
        
        params = new HashMap<String, Object>();
		params.put("amount", amountInCents);
		params.put("currency", "usd");
		params.put("description", description); // can this description make a useful receipt from Stripe?
		params.put("source", stripeToken);
		try {
		    
		    String status = null;
			charge = Charge.create(params);
			status = charge.getStatus();
			System.out.println("charge.getId(): " + charge.getId());
			System.out.println("charge.getOutcome(): " + charge.getOutcome());

    		if (status.equals("succeeded")) {
    		    System.out.println("Succeed branch");
        		BigDecimal decimalAmount = new BigDecimal(charge.getAmount() / 100);
        		System.out.println("decimalAmount: " + decimalAmount.toString());
                returnSuccess = true;
    		} else {
    		    // TODO: how will user know what the problem is? I need to pass an output value
    		    System.out.println("Did not succeed branch");
    		    returnSuccess = false;
    		}
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CardException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return returnSuccess;
    }
}
