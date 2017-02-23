/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.formservice;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.wavemaker.runtime.file.model.DownloadResponse;
import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
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

	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat monthYearFormatter = new SimpleDateFormat("MMyyyy");
	private static SimpleDateFormat yearMonthFormatter = new SimpleDateFormat("yyyyMM");
	
	private static List<String> autoFeeTypes = Arrays.asList("Flat Fee;flatFee", "Square Feet Fee;sfFee", "Unit Fee;unitFee", "Basement Fee;basementFee", "State Fee;stateFee");

    @Autowired
    private SecurityService securityService;
    
    @Value("${cx2.url}")
    private String sqlUrl;
    
    @Value("${cx2.username}")
    private String defaultSqlUser;
    
    @Value("${cx2.schemaName}")
    private String defaultSqlDatabase;
    
    private String defaultSqlPassword = "F!yingFishCove1957";
    
    /**
     * This is sample java operation that accepts an input from the caller and responds with "Hello".
     *
     * SecurityService that is Autowired will provide access to the security context of the caller. It has methods like isAuthenticated(),
     * getUserName() and getUserId() etc which returns the information based on the caller context.
     *
     * Methods in this class can declare HttpServletRequest, HttpServletResponse as input parameters to access the
     * caller's request/response objects respectively. These parameters will be injected when request is made (during API invocation).
     */
    
//    public static void main(String args[]) { // Function for testing/debugging purposes
////    	Long formTypeId = 1L;
////    	String formGuid = "xny7fS8UWUOP5ukVeYaTWwdNZutxlBjk";
////    	HashMap<String, Object> fieldData = new HashMap<String, Object>();
////    	fieldData.put("BuildDate", 1485406800000L);
////    	fieldData.put("Birthday", null);
////    	fieldData.put("Comments", null);
////    	fieldData.put("MeetingTime", 1484551504330L);
////    	
////    	try {
////    		saveFormData(formTypeId, formGuid, fieldData);
////    	} catch (SQLException e) {
////    		e.printStackTrace();
////    	}
//    	
////    	try {
////    		HashMap<String, Object> testValues = new HashMap<String, Object>();
////    		testValues.put("PhoneNumber", "245736");
////    		testValues.put("EmailAddress", "test@test.org");
////    		testValues.put("TestAddress", "Test Address");
////    		testValues.put("CreatedDate", 1485353756034L);
////    		testValues.put("TotalSqft", "200");
////    		testValues.put("TotalUnits", 10);
////    		testValues.put("Basement", true);
////    		submitForm("E90DF2F6-D1E4-E611-80C9-0CC47A46DD63", testValues);
////		} catch (SQLException e) {
////			e.printStackTrace();
////		}
//    	
//    	Pattern p = Pattern.compile("(.+)=(.+)");
//    	Matcher muniMatcher = p.matcher("jdbc:sqlserver://64.87.23.26:1433;databaseName=cx2");
//    	
////    	String muniUrl = muniMatcher.group(0);
//    	System.out.println("jdbc:sqlserver://64.87.23.26:1433;databaseName=cx2".replaceAll("databaseName=.+", "databaseName="+"testString"));
//    }
    
    private Connection getMunicipalityDbConnection(Connection conn, Long municipalityId) throws SQLException {
    	String getMuniDbDetailsQuery = "SELECT DbName, DbUser, DbPassword FROM Municipalities WHERE ID=:municipalityId";
    	Map<String, Object> muniDbDetailsParams = new HashMap<String, Object>();
        muniDbDetailsParams.put("municipalityId", municipalityId);
    	
    	DBRow muniDetails = DBUtils.selectQuery(conn, getMuniDbDetailsQuery, muniDbDetailsParams).get(0);
    	
    	String muniUrl = sqlUrl.replaceAll("databaseName=.+", "databaseName="+muniDetails.getString("DbName"));
    	return DBUtils.getConnection(muniUrl, muniDetails.getString("DbUser"), muniDetails.getString("DbPassword"));
    }
    
    public Map<String, Object> getFormData(Long formTypeId, String formGuid) throws SQLException {
    	Connection cx2Conn = DBUtils.getConnection(sqlUrl, defaultSqlUser, defaultSqlPassword);

    	String getFormInfoQuery = "SELECT FormTableName, MunicipalityId FROM FormTypes WHERE ID=:formTypeId";

		Map<String, Object> formTbNameParams = new HashMap<String, Object>();
		formTbNameParams.put("formTypeId", formTypeId);
		DBRow formInfo = DBUtils.selectQuery(cx2Conn, getFormInfoQuery, formTbNameParams).get(0);
		String formTableName = formInfo.getString("FormTableName");
		
		Long municipalityId = formInfo.getLong("MunicipalityId");
		
		Connection formDbConn = getMunicipalityDbConnection(cx2Conn, municipalityId);
		
		cx2Conn.close();
		
		Map<String, Object> formDbParams = new HashMap<String, Object>();
		formDbParams.put("formGuid", formGuid);
		DBRow formDataRow = DBUtils.selectQuery(formDbConn, ("SELECT * FROM "+formTableName+" WHERE FormGUID=:formGuid"), formDbParams).get(0);
		
		formDbConn.close();
        
        return formDataRow.getFieldValues();
    }
    
    public void saveFormTypeField(Long formTypeId, String label, Long fieldTypeId, Integer displayOrder, Boolean required, String defaultValue, String helpText, String possibleValues) throws SQLException {
    	String fieldName = label == null ? null : DBUtils.getSqlSafeString(label);
    	
    	Connection cx2Conn = DBUtils.getConnection(sqlUrl, defaultSqlUser, defaultSqlPassword);
    	cx2Conn.setAutoCommit(false);
    	HashMap<String, Object> queryParams = new HashMap<String, Object>();
    	queryParams.put("formTypeId",  formTypeId);
    	
    	DBRow muniData = DBUtils.selectQuery(cx2Conn, "SELECT MunicipalityId, FormTableName from FormTypes WHERE ID=:formTypeId", queryParams).get(0);
    	
    	Connection muniDbConn = getMunicipalityDbConnection(cx2Conn, muniData.getLong("MunicipalityId"));
    	muniDbConn.setAutoCommit(false);
    	
    	try {
    		fieldName += DBUtils.selectQuery(muniDbConn, "SELECT NEXT VALUE FOR DynamicFieldIndex as DynamicFieldIndex").get(0).getString("DynamicFieldIndex");
    		
	    	queryParams.put("label", label);
	    	queryParams.put("fieldName", fieldName);
	    	queryParams.put("fieldTypeId", fieldTypeId);
	    	queryParams.put("displayOrder", displayOrder);
	    	queryParams.put("required", required);
	    	queryParams.put("defaultValue", defaultValue);
	    	queryParams.put("helpText", helpText);
	    	queryParams.put("possibleValues", possibleValues);
	    	DBUtils.simpleQuery(cx2Conn, "INSERT INTO FormTypeFields "
	    			+ "(FormTypeId, FieldName, Label, DisplayOrder, Required, DefaultValue, HelpText, FieldTypeId, PossibleValues)"
	    			+" VALUES (:formTypeId, :fieldName, :label, :displayOrder, :required, :defaultValue, :helpText, :fieldTypeId, :possibleValues)",
	    			queryParams);
	    	
	    	String fieldSqlType = DBUtils.selectQuery(cx2Conn, "SELECT SqlType FROM FormFieldTypes WHERE ID=:fieldTypeId", queryParams).get(0).getString("SqlType");
	    	
	    	if (fieldSqlType != null && !fieldSqlType.isEmpty()) {
	    		DBUtils.simpleQuery(muniDbConn, "ALTER TABLE "+muniData.getString("FormTableName")+" ADD "+fieldName+" "+fieldSqlType);
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
    
    public Long saveFormType(Long municipalityId, String formType) throws SQLException {
    	Connection cx2Conn = DBUtils.getConnection(sqlUrl, defaultSqlUser, defaultSqlPassword);
    	cx2Conn.setAutoCommit(false);
        Connection muniDbConn = getMunicipalityDbConnection(cx2Conn, municipalityId);
        muniDbConn.setAutoCommit(false);
    	Long newFormTypeId = null;
        
        try {
        	String formTableName = (DBUtils.getSqlSafeString(formType) + DBUtils.selectQuery(muniDbConn, "SELECT NEXT VALUE FOR DynamicFieldIndex as DynamicFieldIndex").get(0).getString("DynamicFieldIndex"));
        	StringBuilder formTitlePrefix = new StringBuilder();
        	String[] formTypeParts = formType.trim().replaceAll("[^a-zA-Z0-9 ]|[\n]|[\r\n]", "").split(" ");
        	for (int i = 0; i < formTypeParts.length; i++) {
        		String formTypePart = formTypeParts[i];
        		if (formTypePart.trim().isEmpty()) {
        			continue;
        		}
        		
        		formTitlePrefix.append(formTypePart.substring(0, 1).toUpperCase());
        	}
        	
	        HashMap<String, Object> formCreateParams = new HashMap<String, Object>();
	        formCreateParams.put("formType", formType);
	        formCreateParams.put("municipalityId", municipalityId);
	        formCreateParams.put("formTableName", formTableName);
	        formCreateParams.put("titlePrefix", formTitlePrefix.toString());
	        
	        DBUtils.simpleQuery(cx2Conn, "INSERT INTO FormTypes (FormType, MunicipalityId, FormTableName, MunicipalityInternalForm, Active, TitlePrefix) VALUES (:formType, :municipalityId, :formTableName, 0, 0, :titlePrefix)", formCreateParams);
	        
	        newFormTypeId = DBUtils.selectQuery(cx2Conn, "SELECT @@IDENTITY as formId").get(0).getLong("formId");
	        formCreateParams.put("newFormTypeId", newFormTypeId);
	        
	        DBUtils.simpleQuery(cx2Conn, "INSERT INTO FormStatuses (FormTypeId, ConsiderClosed, SortOrder, Status, Description, SendEmail) "
	        		+ "VALUES (:newFormTypeId, 0, 1, 'Draft', 'Draft', 0),"
	        		+ " (:newFormTypeId, 0, 2, 'Submitted', 'Submitted', 1)", formCreateParams);
	        
	        DBUtils.simpleUpdateQuery(muniDbConn, "CREATE TABLE "+formTableName+" ("
	    			+"ID numeric(10) identity(1,1), "
	            	+"FormGUID uniqueidentifier NOT NULL DEFAULT NEWSEQUENTIALID(), "
	    			+"FormTitle varchar(255), "
	    			+"CreatedBy varchar(255), "
	    			+"CreatedDate datetime2 NOT NULL DEFAULT sysdatetime(), "
	    			+"ModifiedDate datetime2, "
	    			+"ModifiedBy varchar(255), "
	    			+"TotalSqft numeric(38), "
	    			+"TotalUnits numeric(38), "
	    			+"Basement bit, "
	    			+"VendorId numeric(10)"
	            	+")");
	        
	        DBUtils.simpleQuery(cx2Conn, "INSERT INTO FormTypeFields "
	    			+ "(FormTypeId, FieldName, Label, DisplayOrder, Required, DefaultValue, FieldTypeId)"
	    			+" VALUES (:newFormTypeId, 'TotalSqft', 'Total Square Feet', 100, 1, 0, 5),"
	    			+" (:newFormTypeId, 'TotalUnits', 'Total Units', 101, 1, 1, 5),"
	    			+" (:newFormTypeId, 'Basement', 'Has Basement', 102, 1, 0, 6)",
	    			formCreateParams);
	        
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
        
        return newFormTypeId;
    }
    
    private String createForm(Connection cx2Conn, Long formTypeId, Long primaryVendorId, Long createUserId, Long ownerId) throws SQLException {
    	DBQueryParams queryParams = new DBQueryParams();
    	queryParams.addLong("formTypeId", formTypeId);
    	Long municipalityId = DBUtils.selectQuery(cx2Conn, "SELECT MunicipalityId FROM FormTypes WHERE ID=:formTypeId", queryParams).get(0).getLong("MunicipalityId");
    	Connection muniDbConn = getMunicipalityDbConnection(cx2Conn, municipalityId);
    	String newFormGuid = null;
    	
    	try {
	    	muniDbConn.setAutoCommit(false);
	    	queryParams.addLong("currentUserId", createUserId);
	    	queryParams.addLong("ownerId", ownerId);
	    	queryParams.addString("currentUser", DBUtils.selectQuery(cx2Conn, "SELECT Email FROM Users WHERE id=:currentUserId", queryParams).get(0).getString("Email"));
	    	queryParams.addLong("municipalityId", municipalityId);
	    	queryParams.addLong("primaryVendorId", primaryVendorId);
	    	
	    	String formTableName = DBUtils.selectQuery(cx2Conn, "SELECT FormTableName FROM FormTypes WHERE ID=:formTypeId", queryParams).get(0).getString("FormTableName");
	    	
	    	List<DBRow> formTypeFieldList = DBUtils.selectQuery(cx2Conn, "SELECT FTF.*, FFT.* FROM FormTypeFields FTF, FormFieldTypes FFT WHERE FTF.FieldTypeId=FFT.ID AND FTF.FormTypeId=:formTypeId", queryParams);
	    	
	    	StringBuilder newFormQueryFieldNames = new StringBuilder("CreatedBy");
	    	StringBuilder newFormQueryVariableNames = new StringBuilder(":currentUser");
	    	
	    	for (DBRow formTypeField : formTypeFieldList) {
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
	    			newFormQueryFieldNames.append(", "+fieldName);
	    			newFormQueryVariableNames.append(", :"+fieldName);
	    		}
	    	}
	    	
	    	String newFormQuery = "INSERT INTO "+formTableName+" ("+newFormQueryFieldNames.toString()+") VALUES ("+newFormQueryVariableNames.toString()+")";
	    	
	    	DBUtils.simpleQuery(muniDbConn, newFormQuery, queryParams);
	    	
	    	Long newFormId = DBUtils.selectQuery(muniDbConn, "SELECT @@IDENTITY as newFormDataId").get(0).getLong("newFormDataId");
	    	queryParams.addLong("newFormId", newFormId);
	    	newFormGuid = DBUtils.selectQuery(muniDbConn, "SELECT FormGUID FROM "+formTableName+" WHERE ID=:newFormId", queryParams).get(0).getString("FormGUID");
	    	queryParams.addString("newFormGUID", newFormGuid);
	    	
	    	Long newFormStatusId = DBUtils.selectQuery(cx2Conn, "SELECT ID FROM FormStatuses WHERE FormTypeId=:formTypeId ORDER BY SortOrder ASC", queryParams).get(0).getLong("ID");
	    	queryParams.addLong("newFormStatusId", newFormStatusId);
	    	
	    	DBUtils.simpleUpdateQuery(cx2Conn, "INSERT INTO MasterForms (MunicipalityId, FormTypeId, FormGUID, UserId, CXVendorId, OwnerId, FormStatusId, Closed) "
	    			+"VALUES (:municipalityId, :formTypeId, :newFormGUID, :currentUserId, :primaryVendorId, :ownerId, :newFormStatusId, 0)", queryParams);
	    	
	    	cx2Conn.commit();
	    	muniDbConn.commit();
    	} catch (SQLException e) {
    		cx2Conn.rollback();
    		muniDbConn.rollback();
    		logger.error(e.getLocalizedMessage());
    		throw e;
    	} finally {
    		muniDbConn.close();
    	}
    	
    	return newFormGuid;
    }
    
    public void saveFormData(Long formTypeId, String formGuid, HashMap<String, Object> fieldData) throws SQLException {
    	Connection cx2Conn = DBUtils.getConnection(sqlUrl, defaultSqlUser, defaultSqlPassword);
    	cx2Conn.setAutoCommit(false);
    	
    	try {
    		saveFormData(cx2Conn, formTypeId, formGuid, fieldData);
    		cx2Conn.commit();
    	} catch (SQLException e) {
    		cx2Conn.rollback();
    		logger.error(e.getLocalizedMessage());
    		throw e;
    	} finally {
    		cx2Conn.close();
    	}
    }
    
    private void saveFormData(Connection cx2Conn, Long formTypeId, String formGuid, HashMap<String, Object> fieldData) throws SQLException {
    	DBQueryParams queryParams = new DBQueryParams();
    	queryParams.addLong("formTypeId", formTypeId);
    	queryParams.addString("formGuid", formGuid);
    	
    	DBRow formTypeData = DBUtils.selectQuery(cx2Conn, "SELECT MunicipalityId, FormTableName FROM FormTypes WHERE ID=:formTypeId", queryParams).get(0);
    	
    	List<DBRow> formFieldsMetaData = DBUtils.selectQuery(cx2Conn, "SELECT FTF.FieldName as FieldName, FFT.SqlType as SqlType FROM FormTypeFields FTF, FormFieldTypes FFT WHERE FFT.ID=FTF.FieldTypeId AND FTF.FormTypeId=:formTypeId", queryParams);
    	
    	Long municipalityId = formTypeData.getLong("MunicipalityId");
    	
    	Connection muniDbConn = getMunicipalityDbConnection(cx2Conn, municipalityId);
    	muniDbConn.setAutoCommit(false);
    	
    	try {
	    	StringBuilder formSaveQuery = new StringBuilder("UPDATE "+formTypeData.getString("FormTableName")+" SET ");
	    	
	    	for (DBRow formFieldsMetaRow : formFieldsMetaData) {
	    		String sqlSafeFieldName = DBUtils.getSqlSafeString(formFieldsMetaRow.getString("FieldName"));
	    		Object fieldValue;
	    		
	    		String sqlType = formFieldsMetaRow.getString("SqlType");
	    		
	    		if (!fieldData.containsKey(sqlSafeFieldName) || sqlType == null) {
	    			continue;
	    		}
	    		
	    		if (sqlType.equals("datetime2") || sqlType.equals("date")) {
	    			Long dateMs = fieldData.get(sqlSafeFieldName) != null ? Long.parseLong(fieldData.get(sqlSafeFieldName).toString()) : null;
	    			
	    			if (sqlType.equals("datetime2")) {
	    				queryParams.addDateTime(sqlSafeFieldName, dateMs);
	    			} else {
	    				queryParams.addDate(sqlSafeFieldName, dateMs);
	    			}
	    		} else {
	    			fieldValue = fieldData.get(sqlSafeFieldName);
		    		queryParams.addObject(sqlSafeFieldName, fieldValue);
	    		}
	    		
	    		formSaveQuery.append(sqlSafeFieldName+"=:"+sqlSafeFieldName);
	    		
	    		formSaveQuery.append(",");
	    	}
	    	
	    	formSaveQuery.deleteCharAt(formSaveQuery.length()-1);
	    	
	    	formSaveQuery.append(" WHERE FormGUID=:formGuid");
	    	
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
    
    public void uploadDocuments(MultipartFile[] files, String formGuid) throws SQLException {
        Connection cx2Conn = DBUtils.getConnection(sqlUrl, defaultSqlUser, defaultSqlPassword);
    	cx2Conn.setAutoCommit(false);
    	
    	StringBuilder documentAddQuery = new StringBuilder("INSERT INTO Document (ItemGUID, Filename, Mimetype, Contents) VALUES ");
    	
    	HashMap<String, Object> queryParams = new HashMap<String, Object>();
    	queryParams.put("formGuid", formGuid);
    	
    	try {
	        for (int i = 0; i < files.length; i++) {
	        	MultipartFile file = files[i];
	        	
	        	if (i > 0) {
	        		documentAddQuery.append(',');
	        	}
				
	        	queryParams.put("doc"+i+"filename", file.getOriginalFilename());
	        	queryParams.put("doc"+i+"mimetype", file.getContentType());
	        	queryParams.put("doc"+i+"contents", file.getBytes());
	        	
	        	documentAddQuery.append("(:formGuid, :doc"+i+"filename, :doc"+i+"mimetype, :doc"+i+"contents)");
	        }
	        
	        if (files.length > 0) {
	        	DBUtils.simpleUpdateQuery(cx2Conn, documentAddQuery.toString(), queryParams);
	        }
	        
	        cx2Conn.commit();
        } catch (IOException e) {
        	cx2Conn.rollback();
			e.printStackTrace();
		} finally {
			cx2Conn.close();
		}
    }
    
    public DownloadResponse downloadDocument(Long documentId) throws SQLException {
    	Connection cx2Conn = DBUtils.getConnection(sqlUrl, defaultSqlUser, defaultSqlPassword);
    	HashMap<String, Object> queryParams = new HashMap<String, Object>();
    	queryParams.put("documentId", documentId);
    	
    	DBRow documentData = DBUtils.selectQuery(cx2Conn, "SELECT * FROM Document WHERE ID=:documentId", queryParams).get(0);
    	
    	DownloadResponse dr = new DownloadResponse();
    	dr.setFileName(documentData.getString("Filename"));
    	dr.setContentType(documentData.getString("Mimetype"));
    	dr.setContents(new ByteArrayInputStream(documentData.getBytes("Contents")));
    	
    	return dr;
    }
    
    public void setFormStatus(String formGuid, Long formStatusId, String comments) throws SQLException {
    	Connection cx2Conn = DBUtils.getConnection(sqlUrl, defaultSqlUser, defaultSqlPassword);
    	Connection muniDbConn = null;
    	cx2Conn.setAutoCommit(false);
    	
    	try {
    		DBQueryParams queryParams = new DBQueryParams();
    		queryParams.addString("formGuid", formGuid);
    		queryParams.addLong("newFormStatusId", formStatusId);
    		queryParams.addString("Comments", comments);
    		
    		DBRow masterFormData = DBUtils.selectQuery(cx2Conn, "SELECT * FROM MasterForms WHERE FormGUID=:formGuid", queryParams).get(0);

    		queryParams.addLong("OldStatusId", masterFormData.getLong("FormStatusId"));
    		queryParams.addLong("FormTypeId", masterFormData.getLong("FormTypeId"));
    		queryParams.addLong("CreatedBy", Long.parseLong(securityService.getUserId()));
    		
    		DBRow formTypeData = DBUtils.selectQuery(cx2Conn, "SELECT * FROM FormTypes WHERE ID=:FormTypeId", queryParams).get(0);
    		
    		DBUtils.simpleUpdateQuery(cx2Conn, "UPDATE MasterForms SET FormStatusId=:newFormStatusId, "
    				+"Closed=(SELECT ConsiderClosed FROM FormStatuses WHERE ID=:newFormStatusId) WHERE FormGUID=:formGuid",
    				queryParams);
    		
    		DBUtils.simpleUpdateQuery(cx2Conn, "INSERT INTO FormHistory (FormGUID,FormTypeId,NewStatusId,OldStatusId,Comments,CreatedBy,CreatedTime) "
    				+"VALUES (:formGuid,:FormTypeId,:newFormStatusId,:OldStatusId,:Comments,:CreatedBy,SYSUTCDATETIME())",
    				queryParams);
    		
    		if (formTypeData.getBoolean("AutomaticFees") && DBUtils.selectQuery(cx2Conn, "SELECT RecalculateAutoFees FROM FormStatuses WHERE ID=:newFormStatusId", queryParams).get(0).getBoolean("RecalculateAutoFees")) {
    			muniDbConn = getMunicipalityDbConnection(cx2Conn, formTypeData.getLong("MunicipalityId"));
    			DBRow formFieldValues = DBUtils.selectQuery(muniDbConn, "SELECT * FROM "+DBUtils.getSqlSafeString(formTypeData.getString("FormTableName"))+" WHERE FormGUID=:formGuid", queryParams).get(0);
    			
    			DBQueryParams feeQueryParams = calculateAutoFees(formTypeData, formFieldValues.getFieldValues());
    			
    			feeQueryParams.addString("formGuid", formGuid);
    			
    			for (String autoFeeType : autoFeeTypes) {
	    			String[] autoFeeTypeParts = autoFeeType.split(";");
	    			String typeName = autoFeeTypeParts[0];
	    			String argName = autoFeeTypeParts[1];
	    			
	    			if (feeQueryParams.paramExists(argName+"Amount")) {
	    				DBUtils.simpleUpdateQuery(cx2Conn, "UPDATE Fees SET Amount=:"+argName+"Amount WHERE FormGuid=:formGuid AND FeeType='"+typeName+"' AND PaidStatus='Unpaid'", feeQueryParams);
	    			}
	    		}
    		}
    		
    		cx2Conn.commit();
    	} catch (Exception e) {
    		cx2Conn.rollback();
    		if (muniDbConn!=null) {
    			muniDbConn.rollback();
    		}
    	} finally {
    		cx2Conn.close();
    		if (muniDbConn!=null) {
    			muniDbConn.close();
    		}
    	}
    }
    
    private DBQueryParams calculateAutoFees(DBRow formTypeData, HashMap<String, Object> fieldData) {
    	BigDecimal totalFees = new BigDecimal("0.00");
    	DBQueryParams queryParams = new DBQueryParams();
    	
		BigDecimal flatFee = formTypeData.getBigDecimal("FlatFee");
		BigDecimal sfFee = formTypeData.getBigDecimal("SfFee");
		BigDecimal unitFee = formTypeData.getBigDecimal("UnitFee");
		BigDecimal stateFee = formTypeData.getBigDecimal("StateFee");
		BigDecimal basementFee = formTypeData.getBigDecimal("BasementFee");
    	
		if (flatFee != null && !flatFee.equals(0)) {
			totalFees = totalFees.add(flatFee);
			queryParams.addString("flatFeeAmount", flatFee.toString());
			queryParams.addString("flatFeeAccountingCode", formTypeData.getString("FlatFeeAccountingCode"));
		}
		
		if (sfFee != null && !sfFee.equals(0)) {
			if (fieldData.get("TotalSqft") != null) {
    			BigDecimal totalSqft = new BigDecimal(Math.abs(Long.parseLong(fieldData.get("TotalSqft").toString())));
    			
    			if (!totalSqft.equals(0)) {
    				totalFees = totalFees.add(sfFee.multiply(totalSqft));
    				queryParams.addString("sfFeeAmount", sfFee.multiply(totalSqft).toString());
    				queryParams.addString("sfFeeAccountingCode", formTypeData.getString("SfFeeAccountingCode"));
    			}
			}
		}
		
		if (unitFee != null && !unitFee.equals(0)) {
			if (fieldData.get("TotalUnits") != null) {
				BigDecimal totalUnits = new BigDecimal(Math.abs(Long.parseLong(fieldData.get("TotalUnits").toString())));
    			
    			if (!totalUnits.equals(0)) {
    				totalFees = totalFees.add(unitFee.multiply(totalUnits));
    				queryParams.addString("unitFeeAmount", unitFee.multiply(totalUnits).toString());
    				queryParams.addString("unitFeeAccountingCode", formTypeData.getString("UnitFeeAccountingCode"));
    			}
			}
		}
		
		if (basementFee != null && !basementFee.equals(0) && fieldData.get("Basement") != null && (Boolean) fieldData.get("Basement")) {
			totalFees = totalFees.add(basementFee);
			queryParams.addString("basementFeeAmount", basementFee.toString());
			queryParams.addString("basementFeeAccountingCode", formTypeData.getString("StateFeeAccountingCode"));
		}
		
		if (stateFee != null && !stateFee.equals(0)) {
			BigDecimal calcedStateFee = totalFees.multiply(stateFee);
			totalFees = totalFees.add(calcedStateFee);
			queryParams.addString("stateFeeAmount", calcedStateFee.toString());
			queryParams.addString("stateFeeAccountingCode", formTypeData.getString("StateFeeAccountingCode"));
		}
		
		queryParams.addString("totalFees", totalFees.toString());
		
		return queryParams;
    }
    
    public String submitForm(Long formTypeId, Long behalfOfUserId, Long ownerId, String locationIds, String vendorIds, Long primaryVendorId, String usersWithWhomToShare, HashMap<String, Object> fieldData) throws SQLException {
    	Connection cx2Conn = DBUtils.getConnection(sqlUrl, defaultSqlUser, defaultSqlPassword);
    	cx2Conn.setAutoCommit(false);
    	String formGuid = "";
    	
    	try {
	    	HashMap<String, Object> queryParams = new HashMap<String, Object>();
	    	
	    	Long createUserId = (behalfOfUserId != null ? behalfOfUserId : Long.parseLong(securityService.getUserId()));
    		queryParams.put("createUserId", createUserId);
	    	formGuid = createForm(cx2Conn, formTypeId, primaryVendorId, createUserId, ownerId);
	    	
	    	queryParams.put("formGuid", formGuid);
	    	
	    	DBRow masterFormData = DBUtils.selectQuery(cx2Conn, "SELECT * FROM MasterForms WHERE FormGUID=:formGuid", queryParams).get(0);
	    	
	    	queryParams.put("formTypeId", masterFormData.getLong("FormTypeId"));
	    	
	    	DBRow formTypeData = DBUtils.selectQuery(cx2Conn, "SELECT * FROM FormTypes WHERE ID=:formTypeId", queryParams).get(0);
	    	
	    	/*
	    	 * Begin form title creation
	    	 */
	    	// Form title prefix
	    	StringBuilder formTitle = new StringBuilder(formTypeData.getString("TitlePrefix"));
	    	
	    	// Form title date
	    	String dateOption = formTypeData.getString("PrefixDate");
	    	Boolean addDashes = formTypeData.getBoolean("PrefixDashes");
	    	
    		Calendar today = new GregorianCalendar();
	    	
	    	if (!dateOption.equalsIgnoreCase("None")) {
	    		if (addDashes) {
	    			formTitle.append('-');
	    		}
	    		
	    		if (dateOption.equals("YearMonth")) {
	    			formTitle.append(yearMonthFormatter.format(today.getTime()));
	    		} else {
	    			formTitle.append(monthYearFormatter.format(today.getTime()));
	    		}
	    	}

	    	if (addDashes) {
	    		formTitle.append('-');
	    	}
	    	
	    	// Form title number
	    	String numberOption = !dateOption.equalsIgnoreCase("None") ? formTypeData.getString("PrefixNumber") : "AutoIncrement";
	    	Long prefixNumberStart = formTypeData.getLong("PrefixNumberStart");
	    	Integer prefixNumberStep = formTypeData.getInteger("PrefixNumberStep");
	    	Long currentPrefixNumber = formTypeData.getLong("CurrentPrefixNumber");
    		Integer numberResetOn = formTypeData.getInteger("PrefixNumberResetOn");
	    	Integer newResetTime = numberOption.equalsIgnoreCase("ResetMonth") ? today.get(Calendar.MONTH)+1 : today.get(Calendar.YEAR);
	    	Long newPrefixNumber;
	    	
	    	if (!numberOption.equalsIgnoreCase("AutoIncrement") && !newResetTime.equals(numberResetOn)) {
	    		newPrefixNumber = prefixNumberStart;
	    	} else {
	    		newPrefixNumber = currentPrefixNumber == null ? prefixNumberStart : (currentPrefixNumber + prefixNumberStep.longValue());
	    	}
	    	
	    	formTitle.append(newPrefixNumber.toString());
	    	
	    	queryParams.put("newPrefixNumber", newPrefixNumber);
	    	queryParams.put("newResetTime", newResetTime);
	    	
	    	fieldData.put("FormTitle", formTitle.toString());
	    	queryParams.put("formTitle", formTitle.toString());
	    	/*
	    	 * End form title creation
	    	 */
	    	
	    	// Save the form data
	    	saveFormData(cx2Conn, masterFormData.getLong("FormTypeId"), formGuid, fieldData);
	    	
	    	DBUtils.simpleUpdateQuery(cx2Conn, "UPDATE FormTypes SET CurrentPrefixNumber=:newPrefixNumber, PrefixNumberResetOn=:newResetTime", queryParams);

	    	// Add Location(s)
	    	if (formTypeData.getBoolean("GISRecord")) {
		    	StringBuilder locationsQuery = new StringBuilder("INSERT INTO GIS2Forms (RelatedFormGUID, GISRecordId, AddedBy, AddedTime) VALUES ");
		    	int locationIndex = 0;
		    	
	    		for (String locationId : locationIds.split(",")) {
	    			if (locationIndex > 0) {
	    				locationsQuery.append(',');
	    			}
	    			
	    			String paramName = DBUtils.getSqlSafeString("location"+locationIndex+"GISRecordId");
	    			locationIndex++;
	    			
	    			queryParams.put(paramName, Long.parseLong(locationId));
	    			
	    			locationsQuery.append("(:formGuid, :"+paramName+", :createUserId, SYSDATETIME())");
	    		}
	    		
	    		DBUtils.simpleUpdateQuery(cx2Conn, locationsQuery.toString(), queryParams);
	    	}
	    	
	    	// Add Vendor(s)
	    	if (formTypeData.getBoolean("VendorSelection")) {
		    	StringBuilder vendorsQuery = new StringBuilder("INSERT INTO Vendors2Form (RelatedFormGUID, VendorId, PrimaryVendor, SharedOn) VALUES ");
		    	int vendorIndex = 0;
		    	
	    		for (String vendorId : vendorIds.split(",")) {
	    			if (vendorIndex > 0) {
	    				vendorsQuery.append(',');
	    			}
	    			
	    			Long vendorIdLong = Long.parseLong(vendorId);
	    			
	    			String paramName = "vendor"+vendorIndex+"VendorId";
	    			String primaryParamName = "vendor"+vendorIndex+"Primary";
	    			vendorIndex++;
	    			
	    			queryParams.put(paramName, vendorIdLong);
	    			queryParams.put(primaryParamName, vendorIdLong.equals(primaryVendorId));
	    			
	    			vendorsQuery.append("(:formGuid, :"+paramName+", :"+primaryParamName+", SYSUTCDATETIME())");
	    		}
	    		
	    		DBUtils.simpleUpdateQuery(cx2Conn, vendorsQuery.toString(), queryParams);
	    	}
	    	
	    	// Add sharing
	    	if (behalfOfUserId != null || (formTypeData.getBoolean("SharedWith") && usersWithWhomToShare != null && !usersWithWhomToShare.trim().isEmpty())) {
	    		StringBuilder sharingQuery = new StringBuilder("INSERT INTO SharedWith (RelatedGUID, SharedWithUser, CreatedOn, CreatedBy) VALUES ");
		    	int shareIndex = 0;
		    	List<String> shareUserIdList = usersWithWhomToShare != null && !usersWithWhomToShare.trim().isEmpty() ? new ArrayList<String>(Arrays.asList(usersWithWhomToShare.split(","))) : new ArrayList<String>();
		    	
		    	if (behalfOfUserId != null && !shareUserIdList.contains(securityService.getUserId())) { // Add the current user to shared if they are submitting on behalf of someone else
		    		shareUserIdList.add(securityService.getUserId());
		    	}
		    	
	    		for (String sharedUserId : shareUserIdList) {
	    			if (shareIndex > 0) {
	    				sharingQuery.append(',');
	    			}
	    			
	    			Long sharedUserIdLong = Long.parseLong(sharedUserId);
	    			
	    			String paramName = DBUtils.getSqlSafeString("shareUser"+shareIndex+"Id");
	    			shareIndex++;
	    			
	    			queryParams.put(paramName, sharedUserIdLong);
	    			
	    			sharingQuery.append("(:formGuid, :"+paramName+", SYSDATETIME(), :createUserId)");
	    		}
	    		
	    		DBUtils.simpleUpdateQuery(cx2Conn, sharingQuery.toString(), queryParams);
	    	}
	    	
	    	// Calculate and add fees
	    	if (formTypeData.getBoolean("AutomaticFees")) {
	        	StringBuilder formFeesQuery = new StringBuilder("INSERT INTO Fees (FormGuid, Amount, FeeType, AutoFeeYN, AccountingCode, PaidStatus) VALUES ");
	        	List<String> formFeesValues = new ArrayList<String>();
	    		
	    		DBQueryParams feeQueryParams = calculateAutoFees(formTypeData, fieldData);
	    		
	    		feeQueryParams.addString("formGuid", formGuid);
	    		
	    		for (String autoFeeType : autoFeeTypes) {
	    			String[] autoFeeTypeParts = autoFeeType.split(";");
	    			String typeName = autoFeeTypeParts[0];
	    			String argName = autoFeeTypeParts[1];
	    			
	    			if (feeQueryParams.paramExists(argName+"Amount")) {
	    				formFeesValues.add("(:formGuid, :"+argName+"Amount, '"+typeName+"', 1, :"+argName+"AccountingCode, 'Unpaid')");
	    			}
	    		}
	    		
	    		if (formFeesValues.size() > 0) {
	    			for (int i = 0; i < formFeesValues.size(); i++) {
	    				String formFeeValue = formFeesValues.get(i);
	    				if (i != 0) {
	    					formFeesQuery.append(',');
	    				}
	    				
	    				formFeesQuery.append(formFeeValue);
	    			}
	    			
	    			DBUtils.simpleUpdateQuery(cx2Conn, formFeesQuery.toString(), feeQueryParams);
	    		}
	    	}
	    	
	    	// Finish up by updating MasterForms and adding a history entry
	    	Long newFormStatusId = DBUtils.selectQuery(cx2Conn, "SELECT ID FROM FormStatuses WHERE FormTypeId=:formTypeId ORDER BY SortOrder ASC", queryParams).get(1).getLong("ID");
	    	queryParams.put("newFormStatusId", newFormStatusId);
	    	
	    	DBUtils.simpleUpdateQuery(cx2Conn, "UPDATE MasterForms SET FormStatusId=:newFormStatusId, TotalFees=:totalFees, TotalPayment='0', BalanceDue=:totalFees, FormTitle=:formTitle WHERE FormGUID=:formGuid", queryParams);
	    	
	    	queryParams.put("oldFormStatusId", masterFormData.getLong("FormStatusId"));
	    	queryParams.put("createdTime", datetimeFormatter.format(today.getTime()));
	    	
	    	DBUtils.simpleUpdateQuery(cx2Conn, "INSERT INTO FormHistory "
	    			+"(FormGUID, FormTypeId, NewStatusId, OldStatusId, CreatedBy, CreatedTime) "
	    			+"VALUES (:formGuid, :formTypeId, :newFormStatusId, :oldFormStatusId, :createUserId, :createdTime)",
	    			queryParams);
	    	
	    	cx2Conn.commit();
    	} catch (Exception e) {
    		cx2Conn.rollback();
    		e.printStackTrace();
    		logger.error(e.getLocalizedMessage());
    		throw e;
    	} finally {
    		cx2Conn.close();
    	}
    	
    	return formGuid;
    }

}
