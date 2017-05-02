package com.civicxpress.letters;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class Cx2DataAccess {

    private DatabaseConnectionInfo dbInfo = null;
    
    public Cx2DataAccess() {
    // 	this.dbInfo = new DatabaseConnectionInfo("jdbc:sqlserver://64.87.23.26:1433;", "cx2", "F!yingFishCove1957", "cx2", 1433);
    	this.dbInfo = new DatabaseConnectionInfo("jdbc:sqlserver://192.168.2.211:1433;", "cx2", "F!yingFishCove1957", "cx2_dev", 1433);
    }

//    public Cx2DataAccess(DatabaseConnectionInfo dbInfo) {
//    	this.dbInfo = dbInfo;
//    }
   
    public Cx2DataAccess(String sqlUrl, String username, String password) {
    // 	this.dbInfo = new DatabaseConnectionInfo("jdbc:sqlserver://64.87.23.26:1433;", "cx2", "F!yingFishCove1957", "cx2", 1433);
    	this.dbInfo = new DatabaseConnectionInfo("jdbc:sqlserver://192.168.2.211:1433;", "cx2", "F!yingFishCove1957", "cx2_dev", 1433);
    	//this.dbInfo = new DatabaseConnectionInfo(sqlUrl, username, password, username, 1433);
    }

    private Connection getDbConnection() {
        Connection connection = null;
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setURL(dbInfo.getDbUrl());
        ds.setUser(dbInfo.getDbUser());
        ds.setPassword(dbInfo.getDbPassword());
        ds.setDatabaseName(dbInfo.getDbName());
        ds.setPortNumber(dbInfo.getDbPortNumber());
        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private Connection getMunicipalityDbConnection(Long municipalityId) {
        Connection municipalityConnection = null;
        SQLServerDataSource ds = new SQLServerDataSource();
        Connection connection = getDbConnection();
        CallableStatement statement = null;
        ResultSet rs = null;
        ds.setURL(dbInfo.getDbUrl());
        ds.setPortNumber(dbInfo.getDbPortNumber());
        try {
            statement = connection.prepareCall("{call getMunicipalityDbInfo(?)}");
            statement.setLong("municipalityId", municipalityId);
            statement.execute();
            rs = statement.getResultSet();
            if (rs.next()) {
                // [DbName], [DbUser], [DbPassword]
                String dbName = rs.getString("DbName");
                String dbUser = rs.getString("DbUser");
                String dbPassword = rs.getString("DbPassword");
                ds.setDatabaseName(dbName);
                ds.setUser(dbUser);
                ds.setPassword(dbPassword);
                try {
                    municipalityConnection = ds.getConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return municipalityConnection;
    }

    private List<String> getColumnValuesById(String storedProcedure, String idField, long idValue) {
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
        return columnValues;
    }

    public List<String> getFormTypeFieldNames(long formTypeId) {
        List<String> fieldNames = getColumnValuesById("getFormTypeFieldNames", "formTypeId", formTypeId);
        return fieldNames;
    }

    public DynamicFormType getFormType(long formTypeId) {
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

    public DynamicForm getFormInfo(Long formTypeId, String formGuid) {
        DynamicForm dynamicForm = null;
        DynamicFormType formType = null;
        Connection connection = getDbConnection();
        CallableStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareCall("{call getFormInfo(?,?)}");
            statement.setLong("formTypeId", formTypeId);
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
        return dynamicForm;
    }

    public GlobalFormInfo getGlobalFormInfo(Long formTypeId, String formGuid) {
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
                // LocationLatitude  LocationLongitude
                globalFormInfo.setLocationLatitude(rs.getDouble("LocationLatitude"));
                globalFormInfo.setLocationLongitude(rs.getDouble("LocationLongitude"));
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
        return globalFormInfo;
    }

    public DynamicForm getFormData(Long formTypeId, String formGuid) {
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
            e.printStackTrace();
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

    public List<String> getLetterTemplatesForFormStatus(int formStatusId) {
        List<String> letterTemplates = getColumnValuesById("getLetterTemplatesForFormStatus", "formStatusId", formStatusId);
        return letterTemplates;
    }

    public SectionalTemplatePdf getLetterTemplate(int letterTemplateId) {
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
            		settings.setBold(rsLetterTemplateDetail.getBoolean("IsBold"));
            		section.setText(rsLetterTemplateDetail.getString("Text"));
            		sections.add(section);
            	}
            	
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rsLetterTemplate.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    	return letterTemplate;
    }

    public void updateLetterTemplate(SectionalTemplatePdf letterTemplate) throws SQLException {
        // TODO:
          Connection connection;
          CallableStatement templateStatement = null;
          CallableStatement sectionStatement = null;
          connection = getDbConnection();
          int rowIndex = 0;
          int sectionIndex = 0;
          
          try {
              connection.setAutoCommit(false);
              // TODO: use a transaction
              sectionStatement = connection.prepareCall("{call updateLetterTemplate(?,?)}");
              sectionStatement.setInt("id", letterTemplate.getId());
              sectionStatement.setString("letterTitle", letterTemplate.getTitle());
              sectionStatement.execute();
              templateStatement = connection.prepareCall("{call updateLetterSection(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
              for (TemplateSectionRow row : letterTemplate.getAllRows()) {
                    for (TemplateSection section : row.getSections()) {
                          LetterElementSettings settings = section.getTextSettings();
                          templateStatement.setInt("letterSectionId", section.getId());
                          templateStatement.setInt("letterTemplateId", letterTemplate.getId());
                          templateStatement.setFloat("x", section.getX());
                          templateStatement.setFloat("y", section.getY());
                          templateStatement.setFloat("height", section.getHeight());
                          templateStatement.setFloat("width", section.getWidth());
                          templateStatement.setInt("rowIndex", rowIndex);
                          templateStatement.setInt("sectionIndex", sectionIndex);
                          templateStatement.setInt("fontSize", settings.getFontSize());
                          templateStatement.setInt("lineHeight", settings.getLineHeight());
                          templateStatement.setString("justification", settings.getJustification().toString());
                          templateStatement.setBoolean("isBold", settings.isBold());
                          templateStatement.setString("text", section.getText());
                          templateStatement.execute();
                          sectionIndex++;
                    }
                    rowIndex++;
              }
              
              connection.commit();
          } catch (SQLException e) {
        	  connection.rollback();
              e.printStackTrace();
          } finally {
              try {
                  templateStatement.close();
                  connection.close();
              } catch (SQLException e) {
                  e.printStackTrace();
              }
          }
      }
}
