/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.inspectionservice;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.civicxpress.MultiDatabaseHelper;
import com.civicxpress.dynamicfieldservice.DynamicFieldService;
import com.tekdog.dbutils.DBQueryParams;
import com.tekdog.dbutils.DBRow;
import com.tekdog.dbutils.DBUtils;
import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;

//import com.civicxpress.inspectionservice.model.*;

@ExposeToClient
public class InspectionService {

    private static final Logger logger = LoggerFactory.getLogger(InspectionService.class);
    
    private static SimpleDateFormat monthYearFormatter = new SimpleDateFormat("MMyyyy");
	private static SimpleDateFormat yearMonthFormatter = new SimpleDateFormat("yyyyMM");

    @Autowired
    private SecurityService securityService;

    @Value("${cx2.url}")
    private String sqlUrl;
    
    @Value("${cx2.username}")
    private String defaultSqlUser;
    
    @Value("${cx2.schemaName}")
    private String defaultSqlDatabase;
    
    @Value("${cx2.password}")
    private String defaultSqlPassword;

    public Long saveInspectionDesign(Long municipalityId, String inspectionName) throws SQLException {
    	Connection cx2Conn = DBUtils.getConnection(sqlUrl, defaultSqlUser, defaultSqlPassword);
    	cx2Conn.setAutoCommit(false);
        Connection muniDbConn = MultiDatabaseHelper.getMunicipalityDbConnection(cx2Conn, municipalityId);
        muniDbConn.setAutoCommit(false);
    	Long newInspectionDesignId = null;
        
        try {
        	String inspectionTableName = (DBUtils.getSqlSafeString(inspectionName) + DBUtils.selectOne(muniDbConn, "SELECT NEXT VALUE FOR DynamicFieldIndex as DynamicFieldIndex", null).getString("DynamicFieldIndex"));
        	StringBuilder inspectionTitlePrefix = new StringBuilder();
        	String[] formTypeParts = inspectionName.trim().replaceAll("[^a-zA-Z0-9 ]|[\n]|[\r\n]", "").split(" ");
        	for (int i = 0; i < formTypeParts.length; i++) {
        		String formTypePart = formTypeParts[i];
        		if (formTypePart.trim().isEmpty()) {
        			continue;
        		}
        		
        		inspectionTitlePrefix.append(formTypePart.substring(0, 1).toUpperCase());
        	}
        	
        	DBQueryParams inspectionCreateParams = new DBQueryParams();
	        inspectionCreateParams.addString("inspectionName", inspectionName);
	        inspectionCreateParams.addLong("municipalityId", municipalityId);
	        inspectionCreateParams.addString("inspectionTableName", inspectionTableName);
	        inspectionCreateParams.addString("titlePrefix", inspectionTitlePrefix.toString());
	        
	        DBUtils.simpleQuery(cx2Conn, "INSERT INTO InspectionDesign (InspectDesignName, MunicipalityId, InspectionTableName, TitlePrefix) VALUES (:inspectionName, :municipalityId, :inspectionTableName, :titlePrefix)", inspectionCreateParams);
	        
	        newInspectionDesignId = DBUtils.selectOne(cx2Conn, "SELECT @@IDENTITY as inspectionId", null).getLong("inspectionId");
	        inspectionCreateParams.addLong("newInspectionDesignId", newInspectionDesignId);
	        
	        DBUtils.simpleUpdateQuery(muniDbConn, "CREATE TABLE "+inspectionTableName+" ("
	    			+"ID numeric(10) identity(1,1), "
	            	+"InspectionGUID uniqueidentifier NOT NULL"
	            	+")");
	        
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
        
        return newInspectionDesignId;
    }
    
    public void scheduleInspection(String formGuid, Long inspectionDesignId, Date requestedFor) throws SQLException {
    	Connection cx2Conn = DBUtils.getConnection(sqlUrl, defaultSqlUser, defaultSqlPassword);
    	DBQueryParams queryParams = new DBQueryParams();
    	queryParams.addLong("inspectionDesignId", inspectionDesignId);
    	
    	DBRow inspectionDesignData = DBUtils.selectOne(cx2Conn, "SELECT * FROM InspectionDesign WHERE ID=:inspectionDesignId", queryParams);
    	Long municipalityId = inspectionDesignData.getLong("MunicipalityId");
    	Connection muniDbConn = MultiDatabaseHelper.getMunicipalityDbConnection(cx2Conn, municipalityId);
    	String newInspectionGuid = null;
    	
    	try {
	    	muniDbConn.setAutoCommit(false);
	    	queryParams.addString("formGuid", formGuid);
	    	queryParams.addLong("requestedBy", Long.parseLong(securityService.getUserId()));
	    	queryParams.addDate("requestedFor", requestedFor);
	    	
	    	/*
	    	 * Begin Inspection title creation
	    	 */
	    	// Inspection title prefix
	    	StringBuilder inspectionTitle = new StringBuilder(inspectionDesignData.getString("TitlePrefix"));
	    	
	    	// Inspection title date
	    	String dateOption = inspectionDesignData.getString("PrefixDate");
	    	Boolean addDashes = inspectionDesignData.getBoolean("PrefixDashes");
	    	
    		Calendar today = new GregorianCalendar();
	    	
	    	if (!dateOption.equalsIgnoreCase("None")) {
	    		if (addDashes) {
	    			inspectionTitle.append('-');
	    		}
	    		
	    		if (dateOption.equals("YearMonth")) {
	    			inspectionTitle.append(yearMonthFormatter.format(today.getTime()));
	    		} else {
	    			inspectionTitle.append(monthYearFormatter.format(today.getTime()));
	    		}
	    	}

	    	if (addDashes) {
	    		inspectionTitle.append('-');
	    	}
	    	
	    	// Inspection title number
	    	String numberOption = !dateOption.equalsIgnoreCase("None") ? inspectionDesignData.getString("PrefixNumber") : "AutoIncrement";
	    	Long prefixNumberStart = inspectionDesignData.getLong("PrefixNumberStart");
	    	Integer prefixNumberStep = inspectionDesignData.getInteger("PrefixNumberStep");
	    	Long currentPrefixNumber = inspectionDesignData.getLong("CurrentPrefixNumber");
    		Integer numberResetOn = inspectionDesignData.getInteger("PrefixNumberResetOn");
	    	Integer newResetTime = numberOption.equalsIgnoreCase("ResetMonth") ? today.get(Calendar.MONTH)+1 : today.get(Calendar.YEAR);
	    	Long newPrefixNumber;
	    	
	    	if (!numberOption.equalsIgnoreCase("AutoIncrement") && !newResetTime.equals(numberResetOn)) {
	    		newPrefixNumber = prefixNumberStart;
	    	} else {
	    		newPrefixNumber = currentPrefixNumber == null ? prefixNumberStart : (currentPrefixNumber + prefixNumberStep.longValue());
	    	}
	    	
	    	inspectionTitle.append(newPrefixNumber.toString());
	    	
	    	queryParams.addLong("newPrefixNumber", newPrefixNumber);
	    	queryParams.addInteger("newResetTime", newResetTime);
	    	
	    	queryParams.addString("inspectionTitle", inspectionTitle.toString());
	    	/*
	    	 * End Inspection title creation
	    	 */
	    	
	    	DBUtils.simpleUpdateQuery(cx2Conn, "INSERT INTO MasterInspections (InspectionDesignId, InspectionTitle, RequestedFor, FormGuid, RequestedBy) "
	    			+"VALUES (:inspectionDesignId, :inspectionTitle, :requestedFor, :formGuid, :requestedBy)", queryParams);
	    	
	    	Long newInspectionId = DBUtils.selectOne(muniDbConn, "SELECT @@IDENTITY as newInspectionId", null).getLong("newInspectionId");
	    	queryParams.addLong("newInspectionId", newInspectionId);
	    	newInspectionGuid = DBUtils.selectOne(cx2Conn, "SELECT InspectionGuid FROM MasterInspections WHERE ID=:newInspectionId", queryParams).getString("InspectionGuid");
	    	queryParams.addString("newInspectionGuid", newInspectionGuid);
	    	
	    	String inspectionTableName = inspectionDesignData.getString("InspectionTableName");
	    	
	    	List<DBRow> dynamicFieldList = DBUtils.selectQuery(cx2Conn, "SELECT FTF.*, FFT.* FROM FormTypeFields FTF, FormFieldTypes FFT WHERE FTF.FieldTypeId=FFT.ID AND FTF.FormTypeId=:formTypeId", queryParams);
	    	
	    	StringBuilder newInspectionQueryFieldNames = new StringBuilder("InspectionGUID");
	    	StringBuilder newInspectionQueryVariableNames = new StringBuilder(":newInspectionGuid");
	    	
	    	for (DBRow formTypeField : dynamicFieldList) {
	    		String defaultValue = formTypeField.getString("DefaultValue");
	    		
	    		if (defaultValue != null && !defaultValue.isEmpty()) {
	    			String fieldName = formTypeField.getString("FieldName");
	    			String sqlType = formTypeField.getString("SqlType");
	    			
	    			try {
		    			if (sqlType.contains("numeric")) {
		        			queryParams.addBigDecimal(fieldName, formTypeField.getBigDecimal("DefaultValue"));
		    			} else if (sqlType.contains("bit")) {
		    				queryParams.addBoolean(fieldName, formTypeField.getBoolean("DefaultValue"));
		    			} else {
		    				queryParams.addObject(fieldName, formTypeField.getObject("DefaultValue"));
		    			}
	    			} catch (Exception e) {
	    				continue;
	    			}
	    			newInspectionQueryFieldNames.append(", "+fieldName);
	    			newInspectionQueryVariableNames.append(", :"+fieldName);
	    		}
	    	}
	    	
	    	String newInspectionQuery = "INSERT INTO "+inspectionTableName+" ("+newInspectionQueryFieldNames.toString()+") VALUES ("+newInspectionQueryVariableNames.toString()+")";
	    	
	    	DBUtils.simpleQuery(muniDbConn, newInspectionQuery, queryParams);
	    	
	    	DBUtils.simpleUpdateQuery(cx2Conn, "INSERT INTO FormsToInspections (RelatedFormGUID, RelatedInspectionGUID, AddedBy) VALUES (:formGuid, :newInspectionGuid, :requestedBy)", queryParams);
	    	
	    	DBUtils.simpleUpdateQuery(cx2Conn, "UPDATE InspectionDesign SET CurrentPrefixNumber=:newPrefixNumber, PrefixNumberResetOn=:newResetTime WHERE ID=:inspectionDesignId", queryParams);
	    	
	    	muniDbConn.commit();
	    	cx2Conn.commit();
    	} catch (SQLException e) {
    		muniDbConn.rollback();
    		cx2Conn.rollback();
    		logger.error(e.getLocalizedMessage());
    		throw e;
    	} finally {
    		muniDbConn.close();
    		cx2Conn.close();
    	}
    }
    
    public void setInspectionOutcome(String inspectionGuid, Long inspectionStatusId, String comments) {
        //
    }
    
    // Dynamic fields
    public void saveDynamicFieldData(String inspectionGuid, HashMap<String, Object> fieldData) throws SQLException {
    	Connection cx2Conn = DBUtils.getConnection(sqlUrl, defaultSqlUser, defaultSqlPassword);
    	
    	cx2Conn.setAutoCommit(false);
    	
    	DBQueryParams params = new DBQueryParams();
    	params.addString("inspectionGuid", inspectionGuid);
    	
    	Long inspectionDesignId = DBUtils.selectOne(cx2Conn, "SELECT InspectionDesignId FROM MasterInspections where InspectionGuid=:inspectionGuid", params).getLong("InspectionDesignId");
    	
    	try {
    		saveDynamicFieldData(cx2Conn, inspectionDesignId, inspectionGuid, fieldData, false);
    		cx2Conn.commit();
    	} catch (SQLException e) {
    		cx2Conn.rollback();
    		logger.error(e.getLocalizedMessage());
    		throw e;
    	} finally {
    		cx2Conn.close();
    	}
    }
    
    private void saveDynamicFieldData(Connection cx2Conn, Long inspectionDesignId, String inspectionGuid, HashMap<String, Object> fieldData, Boolean isNew) throws SQLException {
    	DBQueryParams queryParams = new DBQueryParams();
    	queryParams.addLong("inspectionDesignId", inspectionDesignId);
    	queryParams.addString("formGuid", inspectionGuid);
    	
    	DBRow inspectionDesignData = DBUtils.selectOne(cx2Conn, "SELECT MunicipalityId, FormTableName FROM InspectionDesign WHERE ID=:inspectionDesignId", queryParams);
    	
    	Long municipalityId = inspectionDesignData.getLong("MunicipalityId");
    	
    	DynamicFieldService.saveDynamicFieldData(cx2Conn, municipalityId, inspectionDesignData.getString("FormTableName"), inspectionGuid, inspectionDesignId, "inspection", fieldData);
    }
    
    public void saveDynamicField(Long inspectionDesignId, String label, Long fieldTypeId, Integer displayOrder, Boolean required, String defaultValue, String helpText, String possibleValues) throws SQLException {
    	Connection cx2Conn = DBUtils.getConnection(sqlUrl, defaultSqlUser, defaultSqlPassword);
    	
    	DBQueryParams queryParams = new DBQueryParams();
    	queryParams.addLong("inspectionDesignId", inspectionDesignId);
    	
    	DBRow muniData = DBUtils.selectOne(cx2Conn, "SELECT MunicipalityId, InspectionTableName as ItemTableName from InspectionDesign WHERE ID=:inspectionDesignId", queryParams);
    	
    	DynamicFieldService.saveDynamicField(cx2Conn, inspectionDesignId, muniData.getLong("MunicipalityId"), "InspectionDesignId", muniData.getString("ItemTableName"), label, fieldTypeId, displayOrder, required, defaultValue, helpText, possibleValues, null);
    }
    
    public Map<String, Object> getDynamicFieldData(String inspectionGuid) throws SQLException {
    	Connection cx2Conn = DBUtils.getConnection(sqlUrl, defaultSqlUser, defaultSqlPassword);
    	
    	DBQueryParams formTbNameParams = new DBQueryParams();
    	formTbNameParams.addString("inspectionGuid", inspectionGuid);
    	
    	Long inspectionDesignId = DBUtils.selectOne(cx2Conn, "SELECT FormTypeId FROM MasterForms WHERE FormGUID=:formGuid", formTbNameParams).getLong("FormTypeId");
    	formTbNameParams.addLong("inspectionDesignId", inspectionDesignId);

    	String getInspectionInfoQuery = "SELECT InspectionTableName, MunicipalityId FROM InspectionDesign WHERE ID=:inspectionDesignId";

		DBRow formInfo = DBUtils.selectOne(cx2Conn, getInspectionInfoQuery, formTbNameParams);
		String inspectionTableName = formInfo.getString("InspectionTableName");
		
		Long municipalityId = formInfo.getLong("MunicipalityId");
		
		Connection formDbConn = MultiDatabaseHelper.getMunicipalityDbConnection(cx2Conn, municipalityId);
		
		cx2Conn.close();
		
		return DynamicFieldService.getFieldData(formDbConn, inspectionGuid, "inspection", inspectionTableName);
    }
}
