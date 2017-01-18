/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.formservice;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.tekdog.dbutils.*;

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
    
    private static final String sqlUrl = "64.87.23.26";
    private static final String defaultSqlUser = "cx2";
    private static final String defaultSqlPassword = "F!yingFishCove1957";

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
    
    private static Connection getMunicipalityDbConnection(Connection conn, Long municipalityId) throws SQLException {
    	String getMuniDbDetailsQuery = "SELECT DbName, DbUser, DbPassword FROM Municipalities WHERE ID=:municipalityId";Map<String, Object> muniDbDetailsParams = new HashMap<String, Object>();
        muniDbDetailsParams.put("municipalityId", municipalityId);
    	
    	HashMap<String, DBColumn> muniDetails = DBUtils.simpleQuery(conn, getMuniDbDetailsQuery, muniDbDetailsParams).get(0);
    	return DBUtils.getConnection(sqlUrl, muniDetails.get("DbUser").getData().toString(), muniDetails.get("DbPassword").getData().toString(), muniDetails.get("DbName").getData().toString());
    }
    
    public Map<String, DBColumn> getFormData(Long municipalityId, Long formTypeId, String formGuid) throws SQLException {
    	String formTableName;
    	
    	Connection cx2Conn = DBUtils.getConnection(sqlUrl, defaultSqlUser, defaultSqlPassword, defaultSqlUser);

    	String getFormTableNameQuery = "SELECT FormTableName FROM FormTypes WHERE ID=:formTypeId";

		Map<String, Object> formTbNameParams = new HashMap<String, Object>();
		formTbNameParams.put("formTypeId", formTypeId);
		formTableName = DBUtils.simpleQuery(cx2Conn, getFormTableNameQuery, formTbNameParams).get(0).get("FormTableName").getData().toString();
		
		Connection formDbConn = getMunicipalityDbConnection(cx2Conn, municipalityId);
		
		cx2Conn.close();
		
		Map<String, Object> formDbParams = new HashMap<String, Object>();
		formDbParams.put("formGuid", formGuid);
		HashMap<String, DBColumn> formDataHashMap = DBUtils.simpleQuery(formDbConn, ("SELECT * FROM "+formTableName+" WHERE FormGUID=:formGuid"), formDbParams).get(0);
		
		formDbConn.close();
        
        return formDataHashMap;
    }
    
    public void saveFormTypeField(Long formTypeFieldId, Long formTypeId, String label, String fieldType, Integer displayOrder, Boolean required, String defaultValue, String helpText, String possibleValues) throws SQLException {
    	String fieldName = label.replaceAll("[^a-zA-Z]|[\n]|[\r\n]", "");
    	
    	Connection cx2Conn = DBUtils.getConnection(sqlUrl, defaultSqlUser, defaultSqlPassword, defaultSqlUser);
    	
    	HashMap<String, Object> queryParams = new HashMap<String, Object>();
    	queryParams.put("formTypeId",  formTypeId);
    	queryParams.put("label", label);
    	queryParams.put("fieldName", fieldName);
    	queryParams.put("fieldType", fieldType);
    	queryParams.put("displayOrder", displayOrder);
    	queryParams.put("required", required);
    	queryParams.put("defaultValue", defaultValue);
    	queryParams.put("helpText", helpText);
    	queryParams.put("possibleValues", possibleValues);
    	DBUtils.simpleQuery(cx2Conn, "INSERT INTO FormTypeFields "
    			+ "(FormTypeId, FieldName, Label, DisplayOrder, Required, DefaultValue, HelpText, FieldType, PossibleValues)"
    			+" VALUES (:formTypeId, :fieldName, :label, :displayOrder, :defaultValue, :helpText, :fieldType, :possibleValues)",
    			queryParams);
    	
    	String fieldSqlType;
    	
    	if (fieldType.equals("Text")) {
    		fieldSqlType = "varchar(1000)";
    	} else if (fieldType.equals("Date")) {
    		fieldSqlType = "date";
    	} else if (fieldType.equals("Date+Time")) {
    		fieldSqlType = "datetime2";
    	} else if (fieldType.equals("Number")) {
    		fieldSqlType = "numeric(20)";
    	} else {
    		cx2Conn.close();
    		return;
    	}
    	
    	HashMap<String, DBColumn> muniData = DBUtils.simpleQuery(cx2Conn, "SELECT MunicipalityId, FormTableName from FormTypes WHERE ID=:formTypeId", queryParams).get(0);
    	
    	Connection muniDbConn = getMunicipalityDbConnection(cx2Conn, (Long) muniData.get("MunicipalityId").getData());
    	
    	DBUtils.simpleQuery(muniDbConn, "ALTER TABLE "+muniData.get("FormTableName").getData().toString()+" ADD COLUMN "+fieldName+" "+fieldSqlType);

    	cx2Conn.close();
    	muniDbConn.close();
    }
    
    public void saveFormType(Long municipalityId, String formType) throws SQLException {
    	String formTableName = formType.replaceAll("[^a-zA-Z]|[\n]|[\r\n]", "");
    	
    	Connection cx2Conn = DBUtils.getConnection(sqlUrl, defaultSqlUser, defaultSqlPassword, defaultSqlUser);
		
        Connection muniDbConn = getMunicipalityDbConnection(cx2Conn, municipalityId);
        
        HashMap<String, Object> formCreateParams = new HashMap<String, Object>();
        formCreateParams.put("formType", formType);
        formCreateParams.put("municipalityId", municipalityId);
        formCreateParams.put("formTableName", formTableName);
        
        Long newFormId = (Long) DBUtils.simpleQuery(cx2Conn, "INSERT INTO FormTypes (FormType, MunicipalityId, FormTypeGuid, FormTableName) VALUES (:formType, :municipalityId, NEWSEQUENTIALID(), :formTableName); SELECT @@IDENTITY as formId").get(0).get("formId").getData();
        
        DBUtils.simpleQuery(muniDbConn, "CREATE TABLE "+formTableName+" ("
			+"ID numeric(10) identity(1,1), "
			+"FormTitle varchar(255), "
			+"CreatedBy varchar(255), "
			+"CreatedDate datetime2(100), "
			+"ModifiedDate datetime2(100), "
			+"ModifiedBy varchar(255), "
			+"TotalSqft int, "
			+"TotalUnits int,"
			+"Basement bit"
        	+")go");
        
        cx2Conn.close();
        muniDbConn.close();
    }

}
