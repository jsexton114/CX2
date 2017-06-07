/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.inspectionservice;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletRequest;

import com.civicxpress.letters.Cx2DataAccess;
import com.civicxpress.letters.GlobalInspectionInfo;
import com.civicxpress.letters.LetterTemplate;
import com.civicxpress.letters.SectionalTemplatePdf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

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
import java.util.Properties;

import com.civicxpress.dbconnectionservice.DBConnectionService;
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
    private static final String RESET_NOTIFICATION_MAIL_ID ="civicxpress@gmail.com ";
    private static final String RESET_NOTIFICATION_MAIL_PASSWORD ="civicxpress2016!";

    @Autowired
    private SecurityService securityService;

    public Long saveInspectionDesign(Long municipalityId, String inspectionName, String description) throws SQLException {
    	Connection cx2Conn = DBConnectionService.getConnection();
    	cx2Conn.setAutoCommit(false);
        Connection muniDbConn = DBConnectionService.getMunicipalityDBConnection(municipalityId);
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
	         inspectionCreateParams.addString("description", description);
	        inspectionCreateParams.addString("titlePrefix", inspectionTitlePrefix.toString());
	        
	        DBUtils.simpleQuery(cx2Conn, "INSERT INTO InspectionDesign (InspectDesignName, MunicipalityId, InspectionTableName, Description, TitlePrefix) VALUES (:inspectionName, :municipalityId, :inspectionTableName, :description, :titlePrefix)", inspectionCreateParams);
	        
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
    
    public void scheduleInspection(String formGuid, Long inspectionDesignId, Date requestedFor) throws SQLException,MessagingException {
    	Connection cx2Conn = DBConnectionService.getConnection();
    	DBQueryParams queryParams = new DBQueryParams();
    	queryParams.addLong("inspectionDesignId", inspectionDesignId);
    	
    	cx2Conn.setAutoCommit(false);
    	
    	DBRow inspectionDesignData = DBUtils.selectOne(cx2Conn, "SELECT * FROM InspectionDesign WHERE ID=:inspectionDesignId", queryParams);
    	Long municipalityId = inspectionDesignData.getLong("MunicipalityId");
    	Connection muniDbConn = DBConnectionService.getMunicipalityDBConnection(municipalityId);
    	String newInspectionGuid = null;
    	
    	try {
    		Long currentUserId = Long.parseLong(securityService.getUserId());
	    	muniDbConn.setAutoCommit(false);
	    	queryParams.addString("formGuid", formGuid);
	    	queryParams.addLong("requestedBy", currentUserId);
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
	    	
	    	Long newInspectionId = DBUtils.selectOne(cx2Conn, "SELECT @@IDENTITY as newInspectionId", null).getLong("newInspectionId");
	    	queryParams.addLong("newInspectionId", newInspectionId);
	    	
	    	DBRow newInspectionData = DBUtils.selectOne(cx2Conn, "SELECT InspectionGuid, InspectionOutcomeId FROM MasterInspections WHERE ID=:newInspectionId", queryParams);
	    	newInspectionGuid = newInspectionData.getString("InspectionGuid");
	    	Long newInspectionOutcomeId = newInspectionData.getLong("InspectionOutcomeId");
	    	
	    	queryParams.addString("newInspectionGuid", newInspectionGuid);
	    	
	    	String inspectionTableName = inspectionDesignData.getString("InspectionTableName");
	    	
	    	List<DBRow> dynamicFieldList = DBUtils.selectQuery(cx2Conn, "SELECT FTF.*, FFT.* FROM FormTypeFields FTF, FormFieldTypes FFT WHERE FTF.FieldTypeId=FFT.ID AND FTF.FormTypeId=:formTypeId", queryParams);
	    	
	    	StringBuilder newInspectionQueryFieldNames = new StringBuilder("InspectionGUID");
	    	StringBuilder newInspectionQueryVariableNames = new StringBuilder(":newInspectionGuid");
	    	
	    	if (dynamicFieldList != null) {
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
	    	}
	    	
	    	Calendar todayCal = Calendar.getInstance();
	    	Calendar requestedForCal = Calendar.getInstance();
	    	requestedForCal.setTime(requestedFor);
	    	
	    	DBUtils.prepareProcedure(cx2Conn, "addInspectionHistory", newInspectionGuid, newInspectionOutcomeId, null, "Inspection requested.", currentUserId).execute();
	    	
	    	// Same day fee
	    	BigDecimal sameDayFee = inspectionDesignData.getBigDecimal("SameDayInspectionFee");
	    	if (todayCal.get(Calendar.YEAR) == requestedForCal.get(Calendar.YEAR) && todayCal.get(Calendar.DAY_OF_YEAR) == requestedForCal.get(Calendar.DAY_OF_YEAR)
	    			&& sameDayFee != null && !sameDayFee.equals(new BigDecimal("0"))) {
	    		queryParams.addBigDecimal("sameDayFee", sameDayFee);
	    		queryParams.addString("sameDayAccountingCode", inspectionDesignData.getString("SameDayInspectionFeeAcctCode"));
	    		
	    		DBUtils.simpleUpdateQuery(cx2Conn, "INSERT INTO Fees (InspectionGuid, FormGuid, Amount, FeeType, AutoFeeYN, AccountingCode, PaidStatus) VALUES (:newInspectionGuid, :formGuid, :sameDayFee, 'Same Day Scheduling Fee', 1, :sameDayAccountingCode, 'Unpaid')", queryParams);
	    		
	    		DBUtils.prepareProcedure(cx2Conn, "addInspectionHistory", newInspectionGuid, newInspectionOutcomeId, newInspectionOutcomeId, "A Same Day fee was charged for this inspection.", currentUserId).execute();
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
    
    public void assignInspector(Long inspectorId, String inspectionGuid, Date dateAssigned) throws SQLException {
    	Connection cx2Conn = DBConnectionService.getConnection();
    	cx2Conn.setAutoCommit(false);
    	
    	try {
    		DBQueryParams params = new DBQueryParams();
    		params.addString("inspectionGuid", inspectionGuid);
    		params.addLong("inspectorId", inspectorId);
    		params.addDate("dateAssigned", dateAssigned);
    		
    		DBRow inspectionData = DBUtils.selectOne(cx2Conn, "SELECT * FROM MasterInspections WHERE InspectionGuid=:inspectionGuid", params);
    		
    		params.addLong("inspectionDesignId", inspectionData.getLong("InspectionDesignId"));
    		
    		Long newInspectionOutcomeId = DBUtils.selectOne(cx2Conn, "SELECT ID FROM InspectionOutcome WHERE InspectDesignId=:inspectionDesignId AND Outcome='Scheduled'", params).getLong("ID");
    		
    		params.addLong("newInspectionOutcomeId", newInspectionOutcomeId);
    		
    		if (!newInspectionOutcomeId.equals(inspectionData.getLong("InspectionOutcomeId"))) {
    			DBUtils.simpleUpdateQuery(cx2Conn, "UPDATE MasterInspections SET InspectionOutcomeId=:newInspectionOutcomeId WHERE InspectionGuid=:inspectionGuid", params);
    		}
    		
    		DBUtils.simpleUpdateQuery(cx2Conn, "UPDATE MasterInspections SET AssignedTo=:inspectorId, DateAssigned=:dateAssigned WHERE InspectionGuid=:inspectionGuid", params);
    		
    		cx2Conn.commit();
    	} catch (SQLException e) {
    		cx2Conn.rollback();
    		throw e;
    	} finally {
    		cx2Conn.close();
    	}
    }
    
    public void sendLetter(String inspectionGuid, Integer letterTemplateId, String formLink) throws SQLException, MessagingException {
    	Connection cx2Conn = DBConnectionService.getConnection();
    	
    	DBQueryParams params = new DBQueryParams();
    	params.addInteger("letterTemplateId", letterTemplateId);
    	params.addString("inspectionGuid", inspectionGuid);
    	
    	DBRow inspectionData = DBUtils.selectOne(cx2Conn, "select InspectionDesign.ID as InspectionDesignId, InspectionDesign.InspectDesignName, MasterInspections.InspectionTitle, RU.FullName, RU.Email, InspectionOutcome.Outcome as InspectionOutcome, MU.MunicipalityName, MU.GlobalEmailSig from MasterInspections INNER JOIN Users RU ON RU.ID=MasterInspections.RequestedBy INNER JOIN InspectionOutcome ON InspectionOutcome.ID=MasterInspections.InspectionOutcomeId INNER JOIN InspectionDesign ON InspectionDesign.ID=MasterInspections.InspectionDesignId INNER JOIN Municipalities MU ON MU.ID=InspectionDesign.MunicipalityId WHERE MasterInspections.InspectionGUID=:inspectionGuid", params);
    	Long inspectionDesignId = inspectionData.getLong("InspectionDesignId");
    	String recipientEmail = inspectionData.getString("Email");
    	String recipientFullName = inspectionData.getString("FullName");
    	String inspectionDesignName = inspectionData.getString("InspectDesignName");
    	String inspectionTitle = inspectionData.getString("InspectionTitle");
    	String municipality = inspectionData.getString("MunicipalityName");
    	String municipalitySignature = inspectionData.getString("GlobalEmailSig");
    	
    	DBRow letterTemplateData = DBUtils.selectOne(cx2Conn, "SELECT * FROM LetterTemplates WHERE ID=:letterTemplateId", params);
    	String letterTitle = letterTemplateData.getString("LetterTitle");
    	
    	byte[] letterPdf = createLetterPdf(letterTemplateId, inspectionDesignId, inspectionGuid);
    	
    	Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.enabled","true");
        props.put("mail.imap.ssl.enabled", "true");

        Session session = Session.getDefaultInstance(props, null);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(RESET_NOTIFICATION_MAIL_ID));
        
        InternetAddress recipientAddress;
        recipientAddress = new InternetAddress(recipientEmail);
        
        message.setRecipient(Message.RecipientType.TO, recipientAddress);
        
        StringBuilder emailContent = new StringBuilder("Hi "+recipientFullName+",<br /><br />");
        
        emailContent.append("Here is a PDF copy of your " + letterTitle + ".");
	    emailContent.append("<br /><br />");
        
        emailContent.append(municipality);
        emailContent.append("<br />");
        emailContent.append(inspectionDesignName);
        emailContent.append("<br />");
        emailContent.append(inspectionTitle);
        emailContent.append("<br />");
        emailContent.append("<a href ='"+formLink+"'> "+formLink+" </a>");
        
        emailContent.append( "<br/><br/>"+ municipalitySignature +"<br/><br/>");
        
        Multipart messageContents = new MimeMultipart();
        
        MimeBodyPart messageBody = new MimeBodyPart();
        messageBody.setContent(emailContent.toString(), "text/html");
        
        messageContents.addBodyPart(messageBody);
		String filename = letterTemplateId.toString()+".pdf";
		
        ByteArrayDataSource fileDS = new ByteArrayDataSource(letterPdf, "application/pdf");
        MimeBodyPart letterAttachment = new MimeBodyPart();
        letterAttachment.setDataHandler(new DataHandler(fileDS));
        letterAttachment.setFileName(filename);
        
        messageContents.addBodyPart(letterAttachment);
        
        message.setSubject(letterTitle + " for form " + inspectionTitle);
        message.setContent(messageContents);
        // Send smtp message
        Transport tr = session.getTransport("smtp");
        tr.connect("smtp.gmail.com", 587, RESET_NOTIFICATION_MAIL_ID, RESET_NOTIFICATION_MAIL_PASSWORD);
        message.saveChanges();
        tr.sendMessage(message, message.getAllRecipients());
        tr.close();
    }

    private byte[] createLetterPdf(Integer letterTemplateId, Long inspectionDesignId, String inspectionGuid) throws SQLException {
		Cx2DataAccess db = new Cx2DataAccess();
    	SectionalTemplatePdf lt = null;
		lt = db.getLetterTemplate(letterTemplateId);
        GlobalInspectionInfo globalInspectionInfo = db.getGlobalInspectionInfo(inspectionGuid);
        Map<String, String> textTokens = LetterTemplate.getTextTokenValuesForInspection(db, inspectionDesignId, inspectionGuid);
        byte[] fileBytes = lt.createLetter(globalInspectionInfo, textTokens);
        
        return fileBytes;
    }

    private String sendOutcomeUpdateMail(String requestorFullName ,String requestorEmail,String emailSubject,String emailBody,String municipality,String inspectionDesign,String inspectionOutcome,String lot,String fullAddress,String subdivision,String municipalitySignature,String inspectionTitle,String formLink, MimeBodyPart letterAttachment) throws MessagingException {
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.enabled","true");
        props.put("mail.imap.ssl.enabled", "true");

        Session session = Session.getDefaultInstance(props, null);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(RESET_NOTIFICATION_MAIL_ID));
        
        ArrayList<String> recipientList = new ArrayList<String>(Arrays.asList(requestorEmail.split(",")));

        InternetAddress[] recipientAddress = new InternetAddress[recipientList.size()];
        
        for (int i = 0; i < recipientList.size(); i++) {
            recipientAddress[i] = new InternetAddress(recipientList.get(i).toString());
        }
        
        message.setRecipients(Message.RecipientType.TO, recipientAddress);
        
        logger.info(formLink);
        
        StringBuilder emailContent = new StringBuilder("Hi "+requestorFullName+",<br /><br />");
        
        emailContent.append(emailBody);
        emailContent.append("<br /><br />");
        
        emailContent.append(municipality);
        emailContent.append("<br />");
        emailContent.append(inspectionDesign);
        emailContent.append("<br />");
        emailContent.append(inspectionTitle);
        emailContent.append("<br />");
        emailContent.append("<a href ='"+formLink+ "'>"+formLink+"</a>");
        emailContent.append("<br />");
        emailContent.append("Outcome: "+inspectionOutcome);
        emailContent.append("<br /><br />");
        emailContent.append("Address: "+fullAddress);
        emailContent.append("<br />");
        emailContent.append("Subdivision: "+subdivision);
        emailContent.append("<br />");
        emailContent.append("Lot: "+lot);
        emailContent.append("<br /><br />");
        emailContent.append(municipalitySignature +"<br/><br/>");
        
        message.setSubject(emailSubject);
        message.setContent(emailContent.toString(), "text/html");
        // Send smtp message
        Transport tr = session.getTransport("smtp");
        tr.connect("smtp.gmail.com", 587, RESET_NOTIFICATION_MAIL_ID, RESET_NOTIFICATION_MAIL_PASSWORD);
        message.saveChanges();
        tr.sendMessage(message, message.getAllRecipients());
        tr.close();
        String displayMessage = "Message sent successfully with body " + emailBody;
        logger.info(displayMessage);

        return displayMessage;
    }
    
    public void setInspectionOutcome(String inspectionGuid, Long inspectionOutcomeId, String comments, String formLink) throws SQLException, MessagingException {
        Connection cx2Conn = DBConnectionService.getConnection();
        cx2Conn.setAutoCommit(false);
        
        try {
        	Long currentUserId = Long.parseLong(securityService.getUserId());
        	
        	DBQueryParams params = new DBQueryParams();
        	params.addString("inspectionGuid", inspectionGuid);
        	params.addLong("newOutcomeId", inspectionOutcomeId);
        	params.addLong("createdBy", currentUserId);
        	
        	DBRow inspectionData = DBUtils.selectOne(cx2Conn, "SELECT MI.*, IND.MunicipalityId as MunicipalityId, RU.Email as RequestorEmail, RU.FullName as RequestorFullName, IND.InspectDesignName FROM MasterInspections MI INNER JOIN Users RU ON RU.ID=MI.RequestedBy INNER JOIN InspectionDesign IND ON IND.ID=MI.InspectionDesignId WHERE InspectionGUID=:inspectionGuid", params);
        	Long municipalityId = inspectionData.getLong("MunicipalityId");
        	Long oldOutcomeId = inspectionData.getLong("InspectionOutcomeId");
    		String inspectionTitle = inspectionData.getString("InspectionTitle");
			Long inspectionDesignId = inspectionData.getLong("InspectionDesignId");
        	
        	params.addLong("oldOutcomeId", oldOutcomeId);
        	params.addString("comments", comments);
        	
        	DBUtils.simpleUpdateQuery(cx2Conn, "UPDATE MasterInspections SET InspectionOutcomeId=:newOutcomeId, "
    				+"Closed=(SELECT ConsiderClosed FROM InspectionOutcome WHERE ID=:newOutcomeId) WHERE InspectionGUID=:inspectionGuid",
    				params);
    		
        	DBUtils.prepareProcedure(cx2Conn, "addInspectionHistory", inspectionGuid, inspectionOutcomeId, oldOutcomeId, comments, currentUserId).execute();
        	
    		DBUtils.simpleUpdateQuery(cx2Conn, "INSERT INTO InspectionHistory (InspectionGUID,NewOutcomeId,OldOutcomeId,Comments,CreatedBy) "
    				+"VALUES (:inspectionGuid,:newOutcomeId,:oldOutcomeId,:comments,:createdBy)",
    				params);
        	
        	cx2Conn.commit();
        	
			System.out.println("*JS* outcomeLetterTemplates code");
			System.out.println("*JS* inspectionOutcomeId: " + inspectionOutcomeId);
			MimeBodyPart letterAttachment = null;
			List<DBRow> outcomeLetterTemplates = DBUtils.selectQuery(cx2Conn, "SELECT * FROM [LetterTemplateToInspectionOutcome] WHERE [InspectionOutcomeId]=:newOutcomeId", params);
			System.out.println("*JS* outcomeLetterTemplates != null: " + String.valueOf(outcomeLetterTemplates != null));
			if (outcomeLetterTemplates != null) {
				for (DBRow outcomeLetterTemplate : outcomeLetterTemplates) {
					Boolean attachToEmail = outcomeLetterTemplate.getBoolean("AttachToEmail");
					Boolean attachToItem = outcomeLetterTemplate.getBoolean("AttachToItem");
					
					if (attachToEmail || attachToItem) {
						Integer letterTemplateId = outcomeLetterTemplate.getInteger("LetterTemplateId");
						String filename = cleanInspectionTitleAndDateForFilename(inspectionTitle) + ".pdf";
						System.out.println("*JS* letterTemplateId: " + letterTemplateId);
						System.out.println("*JS* filename: " + filename);
						System.out.println("*JS* inspectionDesignId: " + inspectionDesignId);
						byte[] letterPdf = createLetterPdf(letterTemplateId, inspectionDesignId, inspectionGuid);

						if (attachToItem) {
							DBQueryParams attachParams = new DBQueryParams();
							attachParams.addString("inspectionGuid", inspectionGuid);
							System.out.println("*JS* inspectionGuid: " + inspectionGuid);
							attachParams.addString("filename", filename);
							attachParams.addBytes("letterPdf", letterPdf);
							attachParams.addLong("createdBy", Long.parseLong(securityService.getUserId()));
							DBUtils.simpleUpdateQuery(cx2Conn, "INSERT INTO Document (ItemGUID, Filename, Mimetype, Contents, CreatedBy) VALUES (:inspectionGuid, :filename, 'application/pdf', :letterPdf, :createdBy)", attachParams);
						}

						if (attachToEmail) {
						    ByteArrayDataSource fileDS = new ByteArrayDataSource(letterPdf, "application/pdf");
						    
						    letterAttachment.setDataHandler(new DataHandler(fileDS));
						    letterAttachment.setFileName(filename);
							
							letterAttachment = new MimeBodyPart();
						}
					}
				}
			}

        	DBRow outcomeData = DBUtils.selectOne(cx2Conn, "SELECT * FROM InspectionOutcome WHERE ID=:newOutcomeId", params);
			Boolean sendEmail = outcomeData.getBoolean("SendEmail");

        	if (sendEmail) {
        		params.addLong("municipalityId", municipalityId);
            	DBRow municipalityData = DBUtils.selectOne(cx2Conn, "SELECT * FROM Municipalities WHERE ID=:municipalityId", params);
            	params.addString("formGuid", inspectionData.getString("FormGuid"));
            	DBRow gisRecordData = DBUtils.selectOne(cx2Conn, "SELECT TOP 1 GR.*, SUB.Subdivision FROM GIS2Forms GF INNER JOIN GISRecords GR ON GR.ID=GF.GISRecordId LEFT OUTER JOIN Subdivisions SUB ON SUB.ID=GR.SubdivisionId WHERE GF.RelatedFormGUID=:formGuid", params);
            	
	        	sendOutcomeUpdateMail(
	        			inspectionData.getString("RequestorFullName"),
	        			inspectionData.getString("RequestorEmail"),
	        			outcomeData.getString("EmailSubjectLine"),
	        			outcomeData.getString("EmailBodyText"),
	        			municipalityData.getString("MunicipalityName"),
	        			inspectionData.getString("InspectDesignName"),
	        			outcomeData.getString("Outcome"),
	        			gisRecordData.getString("Lot"),
	        			gisRecordData.getString("FullAddress"),
	        			gisRecordData.getString("Subdivision"),
	        			municipalityData.getString("GlobalEmailSig"),
	        			inspectionData.getString("InspectionTitle"),
	        			formLink, 
						letterAttachment
	        	);
        	}
        } catch (SQLException e) {
        	cx2Conn.rollback();
        	throw e;
        } finally {
        	cx2Conn.close();
        }
    }
    
	private static String cleanInspectionTitleAndDateForFilename(String inspectionTitle) {
    	String returnTitle = null;
		String cleanTitle = null;
		Date dt = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
		String cleanDate = dateFormat.format(dt);
		cleanTitle = inspectionTitle.replaceAll("[^\\w\\d]+", "-");
		returnTitle = cleanTitle + " " + cleanDate;
		return returnTitle;
    }
    

    public void addViolation(String inspectionGuid, Long codeId, String notes, MultipartFile[] pictures) throws SQLException, IOException {
    	Connection cx2Conn = DBConnectionService.getConnection();
    	
    	cx2Conn.setAutoCommit(false);
    	
    	try {
	    	DBQueryParams params = new DBQueryParams();
	    	params.addLong("codeId", codeId);
	    	params.addString("notes", notes);
	    	params.addLong("createdBy", Long.parseLong(securityService.getUserId()));
	    	params.addString("inspectionGuid", inspectionGuid);
	    	
	    	DBUtils.simpleUpdateQuery(cx2Conn, "INSERT INTO Violations (CodeId, Notes, CreatedBy, RelatedInspectionGuid) VALUES " +
	    			"(:codeId, :notes, :createdBy, :inspectionGuid)", params);
	    	
	    	Long violationId = DBUtils.selectOne(cx2Conn, "SELECT @@IDENTITY as violationId", null).getLong("violationId");
	    	params.addLong("violationId", violationId);
	    	
	    	if (pictures.length > 0) {
		    	StringBuilder picturesAddQuery = new StringBuilder("INSERT INTO Document (ItemGUID, ViolationId, Filename, Mimetype, Contents, CreatedBy) VALUES ");
		    	
		    	for (int i = 0; i < pictures.length; i++) {
		    		MultipartFile picture = pictures[i];
		    		
		    		if (i > 0) {
		        		picturesAddQuery.append(',');
		        	}
					
		        	params.addString("pic"+i+"filename", picture.getOriginalFilename());
		        	params.addString("pic"+i+"mimetype", picture.getContentType());
		        	params.addBytes("pic"+i+"contents", picture.getBytes());
		        	
		        	picturesAddQuery.append("(:inspectionGuid, :violationId, :pic"+i+"filename, :pic"+i+"mimetype, :pic"+i+"contents, :createdBy)");
		    	}
		    	
		    	DBUtils.simpleUpdateQuery(cx2Conn, picturesAddQuery.toString(), params);
	    	}
	    	
	    	cx2Conn.commit();
    	} catch (Exception e) {
    		cx2Conn.rollback();
    		throw e;
    	} finally {
    		cx2Conn.close();
    	}
    }
    
    // Dynamic fields
    public void saveDynamicFieldData(String inspectionGuid, HashMap<String, Object> fieldData) throws SQLException {
    	Connection cx2Conn = DBConnectionService.getConnection();
    	
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
    	
    	DBRow inspectionDesignData = DBUtils.selectOne(cx2Conn, "SELECT MunicipalityId, InspectionTableName FROM InspectionDesign WHERE ID=:inspectionDesignId", queryParams);
    	
    	Long municipalityId = inspectionDesignData.getLong("MunicipalityId");
    	
    	DynamicFieldService.saveDynamicFieldData(cx2Conn, municipalityId, inspectionDesignData.getString("InspectionTableName"), inspectionGuid, inspectionDesignId, "inspection", fieldData);
    }
    
    public void saveDynamicField(Long inspectionDesignId, String label, Long fieldTypeId, Integer displayOrder, Boolean required, String defaultValue, String helpText, String possibleValues) throws SQLException {
    	Connection cx2Conn = DBConnectionService.getConnection();
    	
    	DBQueryParams queryParams = new DBQueryParams();
    	queryParams.addLong("inspectionDesignId", inspectionDesignId);
    	
    	DBRow muniData = DBUtils.selectOne(cx2Conn, "SELECT MunicipalityId, InspectionTableName as ItemTableName from InspectionDesign WHERE ID=:inspectionDesignId", queryParams);
    	
    	DynamicFieldService.saveDynamicField(cx2Conn, inspectionDesignId, muniData.getLong("MunicipalityId"), "InspectionDesignId", muniData.getString("ItemTableName"), label, fieldTypeId, displayOrder, required, defaultValue, helpText, possibleValues, null);
    }
    
    public Map<String, Object> getDynamicFieldData(String inspectionGuid) throws SQLException {
    	Connection cx2Conn = DBConnectionService.getConnection();
    	
    	DBQueryParams formTbNameParams = new DBQueryParams();
    	formTbNameParams.addString("inspectionGuid", inspectionGuid);
    	
    	Long inspectionDesignId = DBUtils.selectOne(cx2Conn, "SELECT InspectionDesignId FROM MasterInspections WHERE InspectionGuid=:inspectionGuid", formTbNameParams).getLong("InspectionDesignId");
    	formTbNameParams.addLong("inspectionDesignId", inspectionDesignId);

    	String getInspectionInfoQuery = "SELECT InspectionTableName, MunicipalityId FROM InspectionDesign WHERE ID=:inspectionDesignId";

		DBRow formInfo = DBUtils.selectOne(cx2Conn, getInspectionInfoQuery, formTbNameParams);
		String inspectionTableName = formInfo.getString("InspectionTableName");
		
		Long municipalityId = formInfo.getLong("MunicipalityId");
		
		Connection formDbConn = DBConnectionService.getMunicipalityDBConnection(municipalityId);
		
		cx2Conn.close();
		
		return DynamicFieldService.getFieldData(formDbConn, inspectionGuid, "inspection", inspectionTableName);
    }
    
    // Attachments
    public void uploadDocuments(MultipartFile[] files, String inspectionGuid) throws SQLException, IOException {
    	Connection cx2Conn = DBConnectionService.getConnection();
    	cx2Conn.setAutoCommit(false);
    	
    	try {
	    	StringBuilder documentAddQuery = new StringBuilder("INSERT INTO Document (ItemGUID, Filename, Mimetype, Contents, CreatedBy) VALUES ");
	    	
	    	DBQueryParams queryParams = new DBQueryParams();
	    	queryParams.addString("inspectionGuid", inspectionGuid);
	    	queryParams.addLong("createdBy", Long.parseLong(securityService.getUserId()));
	
	        for (int i = 0; i < files.length; i++) {
	        	MultipartFile file = files[i];
	        	
	        	if (i > 0) {
	        		documentAddQuery.append(',');
	        	}
				
	        	queryParams.addString("doc"+i+"filename", file.getOriginalFilename());
	        	queryParams.addString("doc"+i+"mimetype", file.getContentType());
	        	queryParams.addBytes("doc"+i+"contents", file.getBytes());
	        	
	        	documentAddQuery.append("(:inspectionGuid, :doc"+i+"filename, :doc"+i+"mimetype, :doc"+i+"contents, :createdBy)");
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
