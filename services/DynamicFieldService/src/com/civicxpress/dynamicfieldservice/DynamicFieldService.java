/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.dynamicfieldservice;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import com.civicxpress.dbconnectionservice.DBConnectionService;
import com.tekdog.dbutils.DBQueryParams;
import com.tekdog.dbutils.DBRow;
import com.tekdog.dbutils.DBUtils;
import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;
import com.wavemaker.runtime.service.annotations.HideFromClient;

//import com.civicxpress.dynamicfieldservice.model.*;

@ExposeToClient
public class DynamicFieldService {

    private static final Logger logger = LoggerFactory.getLogger(DynamicFieldService.class);
    
    private static final int MAX_COLUMN_LENGTH = 128;

    @Autowired
    private SecurityService securityService;

    @HideFromClient
    public static Map<String, Object> getFieldData(Connection muniDbConn, String itemGuid, String itemType, String fieldDataTableName) throws SQLException {
    	String guidColumnName;
		if (itemType.equals("form")) {
			guidColumnName = "FormGUID";
		} else if (itemType.equals("inspection")) {
			guidColumnName = "InspectionGUID";
		} else {
			throw new SQLException("Type passed does not support dynamic fields");
		}
    	
		DBQueryParams formDbParams = new DBQueryParams();
		formDbParams.addString("formGuid", itemGuid);
		DBRow formDataRow = DBUtils.selectOne(muniDbConn, ("SELECT * FROM "+fieldDataTableName+" WHERE "+guidColumnName+"=:formGuid"), formDbParams);
		
		muniDbConn.close();
        
        return formDataRow.getFieldValues();
    }

    @HideFromClient
    public static void saveDynamicField(Connection cx2Conn, Long parentObjectId, Long municipalityId, String parentObjectIdColumnName, String itemTableName, String label, Long fieldTypeId, Integer displayOrder, Boolean required, String defaultValue, String helpText, String possibleValues, String automaticFeeType) throws SQLException {
      	String fieldName = label == null ? null : DBUtils.getSqlSafeString(label, 3).replaceAll("^\\d+", ""); // Make sure no leading numbers remain
    
      	cx2Conn.setAutoCommit(false);
      	DBQueryParams queryParams = new DBQueryParams();
    
      	queryParams.addLong("parentItemId", parentObjectId);
    
      	Connection muniDbConn = DBConnectionService.getMunicipalityDBConnection(municipalityId);
      	muniDbConn.setAutoCommit(false);
    
      	try {
      		if (fieldName != null) {
          		String dynamicFieldIndex = DBUtils.selectOne(muniDbConn, "SELECT NEXT VALUE FOR DynamicFieldIndex as DynamicFieldIndex", null).getString("DynamicFieldIndex");
          		
      			if (fieldName.length() + dynamicFieldIndex.length() > MAX_COLUMN_LENGTH) {
          			fieldName = fieldName.substring(0, (MAX_COLUMN_LENGTH - dynamicFieldIndex.length() - 1));
      			}
      			
          		fieldName += dynamicFieldIndex;
      		}
    
        	queryParams.addString("label", label);
        	queryParams.addString("fieldName", fieldName);
        	queryParams.addLong("fieldTypeId", fieldTypeId);
        	queryParams.addInteger("displayOrder", displayOrder);
        	queryParams.addBoolean("required", required);
        	queryParams.addString("defaultValue", defaultValue);
        	queryParams.addString("helpText", helpText);
        	queryParams.addString("possibleValues", possibleValues);
        	queryParams.addString("automaticFeeType", automaticFeeType);
        	DBUtils.simpleQuery(cx2Conn, "INSERT INTO FormTypeFields "
        			+ "(" + parentObjectIdColumnName + ", FieldName, Label, DisplayOrder, Required, DefaultValue, HelpText, FieldTypeId, PossibleValues, AutomaticFeeType)"
        			+" VALUES (:parentItemId, :fieldName, :label, :displayOrder, :required, :defaultValue, :helpText, :fieldTypeId, :possibleValues, :automaticFeeType)",
        			queryParams);
    
        	String fieldSqlType = DBUtils.selectOne(cx2Conn, "SELECT SqlType FROM FormFieldTypes WHERE ID=:fieldTypeId", queryParams).getString("SqlType");
    
        	if (fieldSqlType != null && !fieldSqlType.isEmpty()) {
        		DBUtils.simpleQuery(muniDbConn, "ALTER TABLE "+itemTableName+" ADD "+fieldName+" "+fieldSqlType);
        	}
    
        	cx2Conn.commit();
        	muniDbConn.commit();
      	} catch (SQLException e) {
      		cx2Conn.rollback();
      		muniDbConn.rollback();
      		logger.error(e.getLocalizedMessage());
      		throw e;
      	} finally {
      		cx2Conn.close();
      		muniDbConn.close();
      	}
    }
    
    public void updateDynamicField(Long formTypeFieldId, String label, Integer displayOrder, Boolean required, String defaultValue, String helpText, String possibleValues) throws SQLException {
    	Connection cx2Conn = DBConnectionService.getConnection();

    	cx2Conn.setAutoCommit(false);
    	DBQueryParams queryParams = new DBQueryParams();
    	
    	try {
	    	queryParams.addString("label", label);
	    	queryParams.addInteger("displayOrder", displayOrder);
	    	queryParams.addBoolean("required", required);
	    	queryParams.addString("defaultValue", defaultValue);
	    	queryParams.addString("helpText", helpText);
	    	queryParams.addString("possibleValues", possibleValues);
	    	queryParams.addLong("formTypeFieldId", formTypeFieldId);
	    	DBUtils.simpleQuery(cx2Conn, "UPDATE FormTypeFields "
	    			+ "SET Label=:label, DisplayOrder=:displayOrder, Required=:required, DefaultValue=:defaultValue, HelpText=:helpText, PossibleValues=:possibleValues WHERE ID=:formTypeFieldId",
	    			queryParams);
	    	
	    	cx2Conn.commit();
    	} catch (SQLException e) {
    		cx2Conn.rollback();
    		logger.error(e.getLocalizedMessage());
    		throw e;
    	} finally {
    		cx2Conn.close();
    	}
    }
    
    @HideFromClient
    public static void saveDynamicFieldData(Connection cx2Conn, Long municipalityId, String formTableName, String parentObjectGuid, Long parentObjectId, String parentObjectType, HashMap<String, Object> fieldData) throws SQLException {
    	DBQueryParams queryParams = new DBQueryParams();
    	queryParams.addLong("parentObjectId", parentObjectId);
    	queryParams.addString("parentObjectGuid", parentObjectGuid);
    	
    	String parentObjectIdName = "";
    	String parentObjectGuidName = "";
    	
    	if (parentObjectType.equals("form")) {
    		parentObjectIdName = "FormTypeId";
    		parentObjectGuidName = "FormGUID";
    	} else if (parentObjectType.equals("inspection")) {
    		parentObjectIdName = "InspectionDesignId";
    		parentObjectGuidName = "InspectionGUID";
    	} else {
    		throw new SQLException("Invalid parent object type");
    	}
    	
    	List<DBRow> formFieldsMetaData = DBUtils.selectQuery(cx2Conn, "SELECT FTF.FieldName as FieldName, FFT.SqlType as SqlType FROM FormTypeFields FTF, FormFieldTypes FFT WHERE FFT.ID=FTF.FieldTypeId AND FTF."+parentObjectIdName+"=:parentObjectId", queryParams);
    	
    	Connection muniDbConn = DBConnectionService.getMunicipalityDBConnection(municipalityId);
    	muniDbConn.setAutoCommit(false);
    	
    	try {
	    	StringBuilder formSaveQuery = new StringBuilder("UPDATE "+formTableName+" SET ");
	    	
	    	for (DBRow formFieldsMetaRow : formFieldsMetaData) {
	    		String fieldName = formFieldsMetaRow.getString("FieldName");
	    		String sqlSafeFieldName;
	    		Object fieldValue;
	    		
	    		String sqlType = formFieldsMetaRow.getString("SqlType");
	    		
	    		if (fieldName == null || sqlType == null) {
	    			continue;
	    		}
	    		
	    		sqlSafeFieldName = DBUtils.getSqlSafeString(fieldName);
	    		
	    		if (!fieldData.containsKey(sqlSafeFieldName)) {
	    			continue;
	    		}
	    		
	    		if (sqlType.equals("datetime2") || sqlType.equals("date")) {
	    			Long dateMs = fieldData.get(sqlSafeFieldName) != null ? Double.valueOf(fieldData.get(sqlSafeFieldName).toString()).longValue() : null;
	    			
	    			if (sqlType.equals("datetime2")) {
	    				queryParams.addDateTime(sqlSafeFieldName, dateMs);
	    			} else {
	    				queryParams.addDate(sqlSafeFieldName, dateMs);
	    			}
	    		} else if (sqlType.contains("numeric") && fieldData.get(sqlSafeFieldName) != null) {
	    			queryParams.addBigDecimal(sqlSafeFieldName, new BigDecimal(fieldData.get(sqlSafeFieldName).toString()));
	    		} else {
	    			fieldValue = fieldData.get(sqlSafeFieldName);
		    		queryParams.addObject(sqlSafeFieldName, fieldValue);
	    		}
	    		
	    		formSaveQuery.append(sqlSafeFieldName+"=:"+sqlSafeFieldName);
	    		
	    		formSaveQuery.append(",");
	    	}
	    	
	    	formSaveQuery.deleteCharAt(formSaveQuery.length()-1);
	    	
	    	formSaveQuery.append(" WHERE "+parentObjectGuidName+"=:parentObjectGuid");
	    	
	    	System.out.println(formSaveQuery.toString());
	    	
	    	DBUtils.simpleQuery(muniDbConn, formSaveQuery.toString(), queryParams);
	    	
	    	muniDbConn.commit();
    	} catch (SQLException e) {
    		muniDbConn.rollback();
    		logger.error(e.getLocalizedMessage());
    		throw e;
    	} finally {
    	   	muniDbConn.close();
    	}
    }
}
