package com.civicxpress.letters;

import com.civicxpress.PaymentReceipt;
import com.civicxpress.PaymentReceiptFee;
import com.civicxpress.cx2.Fees;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;

import com.civicxpress.dbconnectionservice.DBConnectionService;

public class Cx2DataAccess {
	
	private static final Logger logger = Logger.getLogger(Cx2DataAccess.class);

    public Cx2DataAccess() {}

    private Connection getDbConnection() throws SQLException {
        return DBConnectionService.getConnection();
    }

    private Connection getMunicipalityDbConnection(Long municipalityId) throws SQLException {
        return DBConnectionService.getMunicipalityDBConnection(municipalityId);
    }

    private List<String> getColumnValuesById(String storedProcedure, String idField, long idValue) throws SQLException {
        List<String> columnValues = new ArrayList<String>();
        Connection connection = getDbConnection();
        CallableStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareCall("{call " + storedProcedure + "(?)}");
            statement.setLong(idField, idValue);
            statement.execute();
            rs = statement.getResultSet();
            while (rs.next()) {
                String cellValue = null;
                cellValue = rs.getString(1);
                columnValues.add(cellValue);
            }
        } catch (SQLException e) {
        	logger.error(e.getMessage());
            throw e;
        } finally {
            try {
                rs.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return columnValues;
    }

    public List<String> getFormTypeFieldNames(long formTypeId, long inspectionDesignId) throws SQLException {
        List<String> fieldNames = new ArrayList<String>();
        
        if (formTypeId >= 0) {
        	fieldNames = getColumnValuesById("getFormTypeFieldNames", "formTypeId", formTypeId);
        } else if (inspectionDesignId >= 0) {
        	fieldNames = getColumnValuesById("getInspectionFieldNames", "inspectionDesignId", inspectionDesignId);
        }
        
        return fieldNames;
    }

    public DynamicInspectionDesign getInspectionDesign(long inspectionDesignId) throws SQLException {
        DynamicInspectionDesign dynamicInspectionDesign = null;
        Connection connection = getDbConnection();
        CallableStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareCall("{call getInspectionDesign(?)}");
            statement.setLong("inspectionDesignId", inspectionDesignId);
            statement.execute();
            rs = statement.getResultSet();
            if (rs.next()) {
                String name = rs.getString("Name");
                String tableName = rs.getString("TableName");
                Long municipalityId = rs.getLong("MunicipalityId");
                dynamicInspectionDesign = new DynamicInspectionDesign(name, tableName, municipalityId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dynamicInspectionDesign;
    }

    public DynamicFormType getFormType(long formTypeId) throws SQLException {
        DynamicFormType dynamicFormType = null;
        Connection connection = getDbConnection();
        CallableStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareCall("{call getFormType(?)}");
            statement.setLong("formTypeId", formTypeId);
            statement.execute();
            rs = statement.getResultSet();
            if (rs.next()) {
                String formType = rs.getString("FormType");
                String formTableName = rs.getString("FormTableName");
                Long municipalityId = rs.getLong("MunicipalityId");
                dynamicFormType = new DynamicFormType(formType, formTableName, municipalityId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dynamicFormType;
    }

    public DynamicForm getFormInfo(Long formTypeId, String formGuid) throws SQLException {
        DynamicForm dynamicForm = null;
        DynamicFormType formType = null;
        Connection connection = getDbConnection();
        CallableStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareCall("{call getFormInfo(?)}");
            statement.setString("formGuid", formGuid);
            statement.execute();
            rs = statement.getResultSet();
            if (rs.next()) {
                String formTitle = rs.getString("FormTitle");
                String creatorFullName = rs.getString("FullName");
                formType = getFormType(formTypeId);
                dynamicForm = new DynamicForm(formType, formTitle, creatorFullName);
            }
        } catch (Exception e) {
        	connection.rollback();
        	
            throw e;
        } finally {
            try {
                rs.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dynamicForm;
    }
    
    public DynamicInspection getInspectionInfo(Long inspectionDesignId, String inspectionGuid) throws SQLException {
        DynamicInspection dynamicInspection = null;
        DynamicInspectionDesign inspectionDesign = null;
        Connection connection = getDbConnection();
        CallableStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareCall("{call getInspectionInfo(?)}");
            statement.setString("inspectionGuid", inspectionGuid);
            statement.execute();
            rs = statement.getResultSet();
            if (rs.next()) {
                String inspectionTitle = rs.getString("InspectionTitle");
                String creatorFullName = rs.getString("FullName");
                inspectionDesign = getInspectionDesign(inspectionDesignId);
                dynamicInspection = new DynamicInspection(inspectionDesign, inspectionTitle, creatorFullName);
            }
        } catch (Exception e) {
        	connection.rollback();
        	
            throw e;
        } finally {
            try {
                rs.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dynamicInspection;
    }
    
    public GlobalInspectionInfo getGlobalInspectionInfo(String inspectionGuid) throws SQLException {
    	GlobalInspectionInfo globalInspectionInfo = null;

        Connection connection = getDbConnection();
        CallableStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareCall("{call getGlobalInspectionInfo(?)}");
            statement.setString("inspectionGuid", inspectionGuid);
            statement.execute();
            rs = statement.getResultSet();
            if (rs.next()) {
                Address municipalityAddress = new Address(null,
                        rs.getString("MunicipalityAddress1"),
                        rs.getString("MunicipalityAddress2"),
                        rs.getString("MunicipalityCity"),
                        rs.getString("MunicipalityState"),
                        rs.getString("MunicipalityPostalCode"),
                        null
                );
                Address vendorAddress = new Address(null,
                        rs.getString("VendorAddress1"),
                        rs.getString("VendorAddress2"),
                        rs.getString("VendorCity"),
                        rs.getString("VendorState"),
                        rs.getString("VendorPostalCode"),
                        null
                );
                Address ownerAddress = new Address(null,
                        rs.getString("OwnerAddress1"),
                        rs.getString("OwnerAddress2"),
                        rs.getString("OwnerCity"),
                        rs.getString("OwnerState"),
                        rs.getString("OwnerPostalCode"),
                        null
                );
                globalInspectionInfo = new GlobalInspectionInfo();
                // FormTypeId  FormType  FormTableName  MunicialityLogo  MunicipalityName
                globalInspectionInfo.setInspectionDesignId(rs.getLong("InspectionDesignId"));
                globalInspectionInfo.setInspectionType(rs.getString("InspectDesignName"));
                globalInspectionInfo.setTableName(rs.getString("InspectionTableName"));
                globalInspectionInfo.setMunicipalityLogo(rs.getBytes("MunicipalityLogo")); 
                globalInspectionInfo.setMunicipalityName(rs.getString("MunicipalityName"));
                // MunicipalityAddress1  MunicipalityAddress2  MunicipalityCity  MunicipalityState  MunicipalityPostalCode  
                municipalityAddress.setId(rs.getLong("MunicipalityAddressId"));
                globalInspectionInfo.setMunicipalityAddress(municipalityAddress);
                // MunicipalityPhone  MunicipalityEmail  VendorCompanyName  
                globalInspectionInfo.setMunicipalityPhone(rs.getString("MunicipalityPhone"));
                globalInspectionInfo.setMunicipalityEmail(rs.getString("MunicipalityEmail"));
                globalInspectionInfo.setVendorCompanyName(rs.getString("VendorCompanyName"));
                // VendorAddress1  VendorAddress2  VendorCity  VendorState  VendorCountry  VendorPostalCode
                vendorAddress.setId(rs.getLong("VendorAddressId"));
                globalInspectionInfo.setVendorAddress(vendorAddress);
                // FormGUID  TotalFees  BalanceDue  FormTitle  ExpiresDate  IssuedDate
                globalInspectionInfo.setInspectionGuid(rs.getString("InspectionGuid"));
                globalInspectionInfo.setFormGuid(rs.getString("FormGUID"));
                globalInspectionInfo.setTotalFees(rs.getBigDecimal("TotalFees"));
                globalInspectionInfo.setBalanceDue(rs.getBigDecimal("BalanceDue"));
                globalInspectionInfo.setFormTitle(rs.getString("FormTitle"));
                globalInspectionInfo.setExpiresDate(rs.getDate("ExpiresDate"));
                globalInspectionInfo.setIssuedDate(rs.getDate("IssuedDate"));
                // OwnerFirstName  OwnerLastName  TenantFirstName  TenantLastName  LocationParcel  LocationLot
                globalInspectionInfo.setOwnerFirstName(rs.getString("OwnerFirstName"));
                globalInspectionInfo.setOwnerLastName(rs.getString("OwnerLastName"));
                globalInspectionInfo.setOwnerPhone(rs.getString("OwnerPhone"));
                globalInspectionInfo.setOwnerAddress(ownerAddress);
                globalInspectionInfo.setTenantFirstName(rs.getString("TenantFirstName"));
                globalInspectionInfo.setTenantLastName(rs.getString("TenantLastName"));
                globalInspectionInfo.setLocationParcel(rs.getString("LocationParcel"));
                globalInspectionInfo.setLocationLot(rs.getString("LocationLot"));
                // LocationAddress1  LocationAddress2  LocationState  LocationPostalCode  
                globalInspectionInfo.setLocationAddress(new Address(null,
                        rs.getString("LocationAddress1"),
                        rs.getString("LocationAddress2"),
                        rs.getString("LocationCity"),
                        rs.getString("LocationState"),
                        rs.getString("LocationPostalCode"),
                        null
                ));
                globalInspectionInfo.setSubdivision(rs.getString("Subdivision"));
                globalInspectionInfo.setUserSubmitted(rs.getString("UserSubmitted"));
                globalInspectionInfo.setDateGenerated(rs.getDate("DateGenerated"));
                globalInspectionInfo.setCurrentStatus(rs.getString("CurrentStatus"));
                globalInspectionInfo.setCurrentStatusUser(rs.getString("CurrentStatusUser"));
                globalInspectionInfo.setCurrentStatusDate(rs.getDate("CurrentStatusDate"));
                globalInspectionInfo.setCurrentStatusYear(rs.getString("CurrentStatusYear"));
                // LocationLatitude  LocationLongitude
                String locationLatitudeString = rs.getString("LocationLatitude");
                if (!rs.wasNull() && locationLatitudeString.length() != 0) {
                    Double locationLatitude = rs.getDouble("LocationLatitude");
                    if (!rs.wasNull()) globalInspectionInfo.setLocationLatitude(locationLatitude);
                }
                String locationLongitudeString = rs.getString("LocationLongitude");
                if (!rs.wasNull() && locationLongitudeString.length() != 0) {
                    Double locationLongitude = rs.getDouble("LocationLongitude");
                    if (!rs.wasNull()) globalInspectionInfo.setLocationLongitude(locationLongitude);
                }
                if (statement.getMoreResults()) {
                    ResultSet rsAdditionalImages = statement.getResultSet();
                    HashMap<String, byte[]> additionalImages = new HashMap<String, byte[]>();
                    while (rsAdditionalImages.next()) {
                        Long imageId = rsAdditionalImages.getLong("ID");
                        byte[] imageBytes = rsAdditionalImages.getBytes("Contents");
                        additionalImages.put(imageId.toString(), imageBytes);
                    }
                    globalInspectionInfo.setAdditionalImages(additionalImages);
                }                
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                rs.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return globalInspectionInfo;
    }

    public GlobalFormInfo getGlobalFormInfo(Long formTypeId, String formGuid) throws SQLException {
        GlobalFormInfo globalFormInfo = null;
        Connection connection = getDbConnection();
        CallableStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareCall("{call getGlobalFormInfo(?,?)}");
            statement.setLong("formTypeId", formTypeId);
            statement.setString("formGuid", formGuid);
            statement.execute();
            rs = statement.getResultSet();
            if (rs.next()) {
                Address municipalityAddress = new Address(null,
                        rs.getString("MunicipalityAddress1"),
                        rs.getString("MunicipalityAddress2"),
                        rs.getString("MunicipalityCity"),
                        rs.getString("MunicipalityState"),
                        rs.getString("MunicipalityPostalCode"),
                        null
                );
                Address vendorAddress = new Address(null,
                        rs.getString("VendorAddress1"),
                        rs.getString("VendorAddress2"),
                        rs.getString("VendorCity"),
                        rs.getString("VendorState"),
                        rs.getString("VendorPostalCode"),
                        null
                );
                Address ownerAddress = new Address(null,
                        rs.getString("OwnerAddress1"),
                        rs.getString("OwnerAddress2"),
                        rs.getString("OwnerCity"),
                        rs.getString("OwnerState"),
                        rs.getString("OwnerPostalCode"),
                        null
                );
                globalFormInfo = new GlobalFormInfo();
                // FormTypeId  FormType  FormTableName  MunicialityLogo  MunicipalityName
                globalFormInfo.setFormTypeId(rs.getLong("FormTypeId"));
                globalFormInfo.setFormType(rs.getString("FormType"));
                globalFormInfo.setFormTableName(rs.getString("FormTableName"));
                globalFormInfo.setMunicipalityLogo(rs.getBytes("MunicipalityLogo")); 
                globalFormInfo.setMunicipalityName(rs.getString("MunicipalityName"));
                // MunicipalityAddress1  MunicipalityAddress2  MunicipalityCity  MunicipalityState  MunicipalityPostalCode  
                municipalityAddress.setId(rs.getLong("MunicipalityAddressId"));
                globalFormInfo.setMunicipalityAddress(municipalityAddress);
                // MunicipalityPhone  MunicipalityEmail  VendorCompanyName  
                globalFormInfo.setMunicipalityPhone(rs.getString("MunicipalityPhone"));
                globalFormInfo.setMunicipalityEmail(rs.getString("MunicipalityEmail"));
                globalFormInfo.setVendorCompanyName(rs.getString("VendorCompanyName"));
                // VendorAddress1  VendorAddress2  VendorCity  VendorState  VendorCountry  VendorPostalCode
                vendorAddress.setId(rs.getLong("VendorAddressId"));
                globalFormInfo.setVendorAddress(vendorAddress);
                // FormGUID  TotalFees  BalanceDue  FormTitle  ExpiresDate  IssuedDate
                globalFormInfo.setFormGuid(rs.getString("FormGUID"));
                globalFormInfo.setTotalFees(rs.getBigDecimal("TotalFees"));
                globalFormInfo.setBalanceDue(rs.getBigDecimal("BalanceDue"));
                globalFormInfo.setFormTitle(rs.getString("FormTitle"));
                globalFormInfo.setExpiresDate(rs.getDate("ExpiresDate"));
                globalFormInfo.setIssuedDate(rs.getDate("IssuedDate"));
                // OwnerFirstName  OwnerLastName  TenantFirstName  TenantLastName  LocationParcel  LocationLot
                globalFormInfo.setOwnerFirstName(rs.getString("OwnerFirstName"));
                globalFormInfo.setOwnerLastName(rs.getString("OwnerLastName"));
                globalFormInfo.setOwnerPhone(rs.getString("OwnerPhone"));
                globalFormInfo.setOwnerAddress(ownerAddress);
                globalFormInfo.setTenantFirstName(rs.getString("TenantFirstName"));
                globalFormInfo.setTenantLastName(rs.getString("TenantLastName"));
                globalFormInfo.setLocationParcel(rs.getString("LocationParcel"));
                globalFormInfo.setLocationLot(rs.getString("LocationLot"));
                // LocationAddress1  LocationAddress2  LocationState  LocationPostalCode  
                globalFormInfo.setLocationAddress(new Address(null,
                        rs.getString("LocationAddress1"),
                        rs.getString("LocationAddress2"),
                        rs.getString("LocationCity"),
                        rs.getString("LocationState"),
                        rs.getString("LocationPostalCode"),
                        null
                ));
                globalFormInfo.setSubdivision(rs.getString("Subdivision"));
                globalFormInfo.setUserSubmitted(rs.getString("UserSubmitted"));
                globalFormInfo.setDateGenerated(rs.getDate("DateGenerated"));
                globalFormInfo.setCurrentStatus(rs.getString("CurrentStatus"));
                globalFormInfo.setCurrentStatusUser(rs.getString("CurrentStatusUser"));
                globalFormInfo.setCurrentStatusDate(rs.getDate("CurrentStatusDate"));
                globalFormInfo.setCurrentStatusYear(rs.getString("CurrentStatusYear"));
                // LocationLatitude  LocationLongitude
                // look at how much extra code has to be written because the database is designed incorrectly
                String locationLatitudeString = rs.getString("LocationLatitude");
                if (!rs.wasNull() && locationLatitudeString.length() != 0) {
                    Double locationLatitude = rs.getDouble("LocationLatitude");
                    if (!rs.wasNull()) globalFormInfo.setLocationLatitude(locationLatitude);
                }
                String locationLongitudeString = rs.getString("LocationLongitude");
                if (!rs.wasNull() && locationLongitudeString.length() != 0) {
                    Double locationLongitude = rs.getDouble("LocationLongitude");
                    if (!rs.wasNull()) globalFormInfo.setLocationLongitude(locationLongitude);
                }
                if (statement.getMoreResults()) {
                    ResultSet rsAdditionalImages = statement.getResultSet();
                    HashMap<String, byte[]> additionalImages = new HashMap<String, byte[]>();
                    while (rsAdditionalImages.next()) {
                        Long imageId = rsAdditionalImages.getLong("ID");
                        byte[] imageBytes = rsAdditionalImages.getBytes("Contents");
                        additionalImages.put(imageId.toString(), imageBytes);
                    }
                    globalFormInfo.setAdditionalImages(additionalImages);
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                rs.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return globalFormInfo;
    }

    public DynamicForm getFormData(Long formTypeId, String formGuid) throws SQLException {
        DynamicForm dynamicForm = null;
        DynamicFormType formType = null;
        Long municipalityId;
        Connection municipalityConnection;
        CallableStatement statement = null;
        ResultSet rs = null;
        String formTableName = null;
        ResultSetMetaData resultSetMetaData = null;
        int columnCount;
        Map<String, Object> formDataValues = new HashMap<String, Object>();
        dynamicForm = getFormInfo(formTypeId, formGuid);
        formType = dynamicForm.getDynamicFormType();
        formTableName = formType.getFormTableName();
        municipalityId = formType.getMunicipalityId();
        municipalityConnection = getMunicipalityDbConnection(municipalityId);
        try {
            statement = municipalityConnection.prepareCall("{call getFormData(?,?)}");
            statement.setString("tableName", formTableName);
            statement.setString("formGuid", formGuid);
            statement.execute();
            rs = statement.getResultSet();
            resultSetMetaData = rs.getMetaData();
            columnCount = resultSetMetaData.getColumnCount();
            if (rs.next()) {
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    String columnName = resultSetMetaData.getColumnLabel(columnIndex);
                    Object columnValue = rs.getObject(columnIndex);
                    formDataValues.put(columnName, columnValue);
                }
            }
        } catch (SQLException e) {
            municipalityConnection.rollback();
            
            throw e;
        } finally {
            try {
                rs.close();
                statement.close();
                municipalityConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        dynamicForm.setFormDataValues(formDataValues);
        return dynamicForm;
    }

    public DynamicInspection getInspectionData(Long inspectionDesignId, String inspectionGuid) throws SQLException {
        DynamicInspection dynamicForm = null;
        DynamicInspectionDesign formType = null;
        Long municipalityId;
        Connection municipalityConnection;
        CallableStatement statement = null;
        ResultSet rs = null;
        String formTableName = null;
        ResultSetMetaData resultSetMetaData = null;
        int columnCount;
        Map<String, Object> formDataValues = new HashMap<String, Object>();
        dynamicForm = getInspectionInfo(inspectionDesignId, inspectionGuid);
        formType = dynamicForm.getDynamicInspectionType();
        formTableName = formType.getTableName();
        municipalityId = formType.getMunicipalityId();
        municipalityConnection = getMunicipalityDbConnection(municipalityId);
        try {
            statement = municipalityConnection.prepareCall("{call getInspectionData(?,?)}");
            municipalityConnection.setAutoCommit(false);
            statement.setString("tableName", formTableName);
            statement.setString("inspectionGuid", inspectionGuid);
            statement.execute();
            rs = statement.getResultSet();
            resultSetMetaData = rs.getMetaData();
            columnCount = resultSetMetaData.getColumnCount();
            if (rs.next()) {
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    String columnName = resultSetMetaData.getColumnLabel(columnIndex);
                    Object columnValue = rs.getObject(columnIndex);
                    formDataValues.put(columnName, columnValue);
                }
            }
        } catch (SQLException e) {
            municipalityConnection.rollback();
            
            throw e;
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                municipalityConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        dynamicForm.setFormDataValues(formDataValues);
        return dynamicForm;
    }

    public List<String> getLetterTemplatesForFormStatus(int formStatusId) throws SQLException {
        List<String> letterTemplates = getColumnValuesById("getLetterTemplatesForFormStatus", "formStatusId", formStatusId);
        return letterTemplates;
    }

    public SectionalTemplatePdf getLetterTemplate(int letterTemplateId) throws SQLException {
    	SectionalTemplatePdf letterTemplate = null;
        Connection connection;
        CallableStatement statement = null;
        ResultSet rsLetterTemplate = null;
        ResultSet rsLetterTemplateDetail = null;
        String formTableName = null;
        ResultSetMetaData resultSetMetaData = null;
        letterTemplate = new SectionalTemplatePdf();
        connection = getDbConnection();
        try {
            statement = connection.prepareCall("{call getLetterTemplate(?)}");
            statement.setInt("letterTemplateId", letterTemplateId);
            statement.execute();
            rsLetterTemplate = statement.getResultSet();
            if (rsLetterTemplate.next()) {
            	letterTemplate.setId(rsLetterTemplate.getInt("Id"));
                letterTemplate.setTitle(rsLetterTemplate.getString("LetterTitle"));
            }
            if (statement.getMoreResults()) {
        		List<TemplateSectionRow> rows = letterTemplate.getAllRows();
        		TemplateSectionRow row = null;
        		List<TemplateSection> sections = null;
        		TemplateSection section = null;
        		LetterElementSettings settings = null;
            	int rowIndex = 0;
            	int previousRowIndex = -1;
            	rsLetterTemplateDetail = statement.getResultSet();
            	while (rsLetterTemplateDetail.next()) {
            		rowIndex = rsLetterTemplateDetail.getInt("RowIndex");
            		if (rowIndex > previousRowIndex) {
            			float margin = 36f; // // TODO: crap, I forgot the header margin is different. Where to store?
            			if (rowIndex == 0 || rowIndex == 9) { margin = 16f; } // HACK: see above
            			row = new TemplateSectionRow(margin, margin); 
                		sections = row.getSections();
            			rows.add(row);
            			previousRowIndex = rowIndex;
            		}
            		section = new TemplateSection(
            				rsLetterTemplateDetail.getFloat("X"), 
            				rsLetterTemplateDetail.getFloat("Y"), 
            				rsLetterTemplateDetail.getFloat("Height"),
            				rsLetterTemplateDetail.getFloat("Width"));
            		section.setId(rsLetterTemplateDetail.getInt("Id"));
            		settings = section.getTextSettings(); 
            		settings.setFontSize(rsLetterTemplateDetail.getInt("FontSize"));
            		settings.setLineHeight(rsLetterTemplateDetail.getInt("LineHeight"));
            		settings.setJustification(PdfDocument.textJustificationFromString(rsLetterTemplateDetail.getString("Justification")));
            		settings.setIsBold(rsLetterTemplateDetail.getBoolean("IsBold"));
            		section.setText(rsLetterTemplateDetail.getString("Text"));
            		sections.add(section);
            	}
            }
        } catch (SQLException e) {
        	connection.rollback();
        	
            throw e;
        } finally {
            try {
                rsLetterTemplate.close();
                rsLetterTemplateDetail.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    	return letterTemplate;
    }

    public PaymentReceipt getPaymentReceipt(Long transactionId) {
        PaymentReceipt paymentReceipt = null;
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet rsPaymentReceipt = null;
        ResultSet rsPaymentReceiptDetail = null;
        paymentReceipt = new PaymentReceipt();
        try {
            connection = getDbConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareCall("{call getPaymentReceipt(?)}");
            statement.setQueryTimeout(120);
            statement.setLong("transactionId", transactionId);
            logger.debug("transactionId: " + transactionId);
            statement.execute();
            rsPaymentReceipt = statement.getResultSet();
            if (rsPaymentReceipt.next()) {
                String paymentMethod = rsPaymentReceipt.getString("PaymentMethod");
                String paymentNumber = rsPaymentReceipt.getString("PaymentNumber");
                Float amountReceived = rsPaymentReceipt.getFloat("AmountReceived");
                String comments = rsPaymentReceipt.getString("Comments");
                Date dateCreated = rsPaymentReceipt.getDate("DateCreated");
                LocalDateTime localDateTimeDateCreated = new LocalDateTime(dateCreated);
                String userFullName = rsPaymentReceipt.getString("UserFullName");
                String userEmail = rsPaymentReceipt.getString("UserEmail");
                paymentReceipt.setTransactionId(new BigDecimal(transactionId).intValueExact());
                if (paymentMethod != null) paymentReceipt.setPaymentMethod(paymentMethod);
                if (paymentNumber != null) paymentReceipt.setPaymentNumber(paymentNumber);
                if (amountReceived != null) paymentReceipt.setAmountReceived(amountReceived);
                if (comments != null) paymentReceipt.setComments(comments);
                if (localDateTimeDateCreated != null) paymentReceipt.setDateCreated(localDateTimeDateCreated);
                if (userFullName != null) paymentReceipt.setUserFullName(userFullName);
                if (userEmail != null) paymentReceipt.setUserEmail(userEmail);
            }
            if (statement.getMoreResults()) {
                //logger.debug("statement.getMoreResults()");
                ArrayList<PaymentReceiptFee> fees = new ArrayList<PaymentReceiptFee>();
            	rsPaymentReceiptDetail = statement.getResultSet();
            	//logger.debug("rsPaymentReceiptDetail: " + rsPaymentReceiptDetail);
            	while (rsPaymentReceiptDetail.next()) {
            	    //logger.debug("rsPaymentReceiptDetail.next()");
            	    PaymentReceiptFee fee = new PaymentReceiptFee();
            	    String municipalityName = rsPaymentReceiptDetail.getString("MunicipalityName");
            	    String feeType = rsPaymentReceiptDetail.getString("FeeType");
            	    String accountingCode = rsPaymentReceiptDetail.getString("AccountingCode");
            	    String paidStatus = rsPaymentReceiptDetail.getString("PaidStatus");
                    Date paidDate = rsPaymentReceiptDetail.getDate("PaidDate");
                    String comments = rsPaymentReceiptDetail.getString("Comments");
                    String itemTitle = rsPaymentReceiptDetail.getString("ItemTitle");
                    BigDecimal amount = rsPaymentReceiptDetail.getBigDecimal("Amount");
                    String transactionComments = rsPaymentReceiptDetail.getString("TransactionComments");
                    if (municipalityName !=null) fee.setMunicipalityName(municipalityName);
            	    if (feeType != null) fee.setFeeType(feeType);
            	    if (accountingCode != null ) fee.setAccountingCode(accountingCode);
            	    if (paidStatus != null) fee.setPaidStatus(paidStatus);
            	    if (paidDate != null) fee.setPaidDate(paidDate);
            	    if (comments != null) fee.setComments(comments);
            	    if (itemTitle != null) fee.setItemTitle(itemTitle);
            	    if (amount != null) fee.setAmount(amount);
            	    if (transactionComments != null) fee.setTransactionComments(transactionComments);
            	    //fee.setMunicipalityName(rsPaymentReceiptDetail.getString("MunicipalityName"));
                    fees.add(fee);
            	}
            	paymentReceipt.setPaymentReceiptFees(fees.toArray(new PaymentReceiptFee[fees.size()]));
            }
        } catch (SQLException e) {
            try {
        	    connection.rollback();
        	    //logger.debug("connection.rollback();");
        	    e.printStackTrace();
        	    throw e;
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (rsPaymentReceipt != null) rsPaymentReceipt.close();
                if (rsPaymentReceiptDetail != null) rsPaymentReceiptDetail.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
                logger.debug("connection.close();");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.debug("return paymentReceipt;");
        return paymentReceipt;
    }
    
    public void updateLetterTemplate(SectionalTemplatePdf letterTemplate, Long formTypeId, Long inspectionDesignId, Long userId) throws SQLException {
          Connection connection;
          CallableStatement templateStatement = null;
          CallableStatement sectionStatement = null;
          connection = getDbConnection();
          int rowIndex = 0;
          int sectionIndex = 0;
          int letterTemplateId;
          
          try {
              connection.setAutoCommit(false);
              templateStatement = connection.prepareCall("{ call updateLetterTemplate(?,?,?,?,?,?)}");
              templateStatement.setInt("id", letterTemplate.getId());
              templateStatement.setString("letterTitle", letterTemplate.getTitle());
              
              if (formTypeId != null) {
            	  templateStatement.setLong("formTypeId", formTypeId);
              } else {
            	  templateStatement.setObject("formTypeId", formTypeId);
              }
              
              if (inspectionDesignId != null) {
            	  templateStatement.setLong("inspectionDesignId", inspectionDesignId);
              } else {
            	  templateStatement.setObject("inspectionDesignId", inspectionDesignId);
              }
              templateStatement.setLong("userId", userId);
              templateStatement.registerOutParameter("letterTemplateId", java.sql.Types.INTEGER);
              templateStatement.execute();
              letterTemplateId = templateStatement.getInt("letterTemplateId");
              sectionStatement = connection.prepareCall("{call updateLetterSection(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
              for (TemplateSectionRow row : letterTemplate.getAllRows()) {
                    for (TemplateSection section : row.getSections()) {
                          LetterElementSettings settings = section.getTextSettings();
                          sectionStatement.setInt("letterSectionId", section.getId());
                          sectionStatement.setInt("letterTemplateId", letterTemplateId);
                          sectionStatement.setFloat("x", section.getX());
                          sectionStatement.setFloat("y", section.getY());
                          sectionStatement.setFloat("height", section.getHeight());
                          sectionStatement.setFloat("width", section.getWidth());
                          sectionStatement.setInt("rowIndex", rowIndex);
                          sectionStatement.setInt("sectionIndex", sectionIndex);
                          sectionStatement.setInt("fontSize", settings.getFontSize());
                          sectionStatement.setInt("lineHeight", settings.getLineHeight());
                          sectionStatement.setString("justification", settings.getJustification().toString());
                          sectionStatement.setBoolean("isBold", settings.getIsBold());
                          sectionStatement.setString("text", section.getText());
                          sectionStatement.execute();
                          sectionIndex++;
                    }
                    rowIndex++;
              }
              
              connection.commit();

              sectionStatement.close();
          } catch (SQLException e) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					throw e1;
				}
				throw e;
          } finally {
              try {
                  connection.close();
              } catch (SQLException e) {
                  throw e;
              }
          }
      }
}
