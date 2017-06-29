package com.civicxpress.letters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LetterTemplate {
    private static final String[] GLOBAL_TEXT_TOKENS = {"FormType", "FormTitle", "MunicipalityName", "MunicipalityAddress", "MunicipalityAddressLine1", "MunicipalityAddressLine2", "MunicipalityCity", "MunicipalityState", "MunicipalityPostalCode", "ContractorName", "ContractorAddress",
            "LocationLot", "Location", "LocationMap", "LocationParcel", "Subdivision", "UserSubmitted", "DateGenerated", "Fees", "AmountDue", "Owner", "OwnerPhone", "OwnerAddress", "Tenant",
            "Violations", "ExpiresDate", "IssuedDate"};
    
    private static final String[] GLOBAL_INSPECTION_TOKENS = {"FormType", "FormTitle", "MunicipalityName", "MunicipalityAddress", "MunicipalityAddressLine1", "MunicipalityAddressLine2", "MunicipalityCity", "MunicipalityState", "MunicipalityPostalCode", "ContractorName", "ContractorAddress",
            "LocationLot", "Location", "LocationMap", "LocationParcel", "Subdivision", "Fees", "AmountDue", "Owner", "OwnerPhone", "OwnerAddress", "Tenant",
            "Violations", "ExpiresDate", "IssuedDate"};
    
    private static final String[] GLOBAL_IMAGE_TOKENS = {"MunicipalityLogo"};
    private static final String TOKEN_PATTERN = "\\[(.*?)\\]";
    private static final int COMPLETE_TOKEN_GROUP_INDEX = 0;
    private static final int TOKEN_NAME_GROUP_INDEX = 1;
    private String templateText = null;
    private String output = null;

    public LetterTemplate() {

    }

    public LetterTemplate(String templateText) {
        this.templateText = templateText;
    }

    public String getTemplateText() {
        return this.templateText;
    }

    public void setTemplateText(String templateText) {
        this.templateText = templateText;
    }

    public String getOutput() {
        return this.output;
    }

    public String createLetterFromTokenValuesJson(String tokenValuesJson) {
        String returnLetter = null;
        Map<String, String> tokenValues = null;
        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, String>>(){}.getType();
        tokenValues = gson.fromJson(tokenValuesJson, mapType);
        returnLetter = createLetter(tokenValues);
        return returnLetter;
    }

    public String createLetter(Map<String, String> tokens) {
        Pattern p = Pattern.compile(TOKEN_PATTERN);
        Matcher m = p.matcher(this.templateText);
        String output = templateText;
        while (m.find()) {
            String completeToken = m.group(COMPLETE_TOKEN_GROUP_INDEX);
            String tokenName = m.group(TOKEN_NAME_GROUP_INDEX);
            Object valueToApply = tokens.get(tokenName);
            if (valueToApply != null) {
            	if (valueToApply instanceof String) {
            		output = output.replace(completeToken, valueToApply.toString());
            	}
            }
        }
        this.output = output;
        return output;
    }

//    public static String getTokenValuesJson(Long formTypeId, String formGuid) {
//        Gson gson = new Gson();
//        Map<String, String> tokenValues = getTextTokenValues(formTypeId, formGuid);
//        String tokenValuesJson = gson.toJson(tokenValues);
//        return tokenValuesJson;
//    }

    public static Map<String, String> getTextTokenValues(Cx2DataAccess db, Long formTypeId, String formGuid) throws SQLException {
        List<String> avaliableTokens = null;
        DynamicForm form = db.getFormData(formTypeId, formGuid); //TODO: see why global form data is collected twice
        GlobalFormInfo globalFormInfo = db.getGlobalFormInfo(formTypeId, formGuid);
        Map<String, Object> dynamicFormDataValues = form.getFormDataValues();
        Map<String, String> returnTokenValues = new HashMap<String, String>();
        Iterator availableTokensIterator = null;
        avaliableTokens = getAvailableTokens(db, formTypeId, -1);
        availableTokensIterator = avaliableTokens.iterator();
        while (availableTokensIterator.hasNext()) {
            String completeToken = (String) availableTokensIterator.next();
            String tokenName = getTokenName(completeToken);
            String stringValue = null;
            boolean isExistingDynamicToken = dynamicFormDataValues.containsKey(tokenName); 
            Object objectValue = null;
            boolean isNullValue = true;
            if (isExistingDynamicToken) {
            	objectValue = dynamicFormDataValues.get(tokenName);
            	isNullValue = (objectValue == null);
            }
            if (!isNullValue) {
                if (objectValue instanceof String) {
                	stringValue = objectValue.toString();
                }
            } else {
	            // if it's a global token, populate value here
	            if (Arrays.asList(GLOBAL_TEXT_TOKENS).contains(tokenName)) {
	            	stringValue = getGlobalFormValueByTokenName(globalFormInfo, tokenName);
	            } 
            }
            if (stringValue == null) stringValue = "";
            returnTokenValues.put(tokenName, stringValue);
        }
        return returnTokenValues;
    }
    
    public static Map<String, String> getTextTokenValuesForInspection(Cx2DataAccess db, Long inspectionDesignId, String inspectionGuid) throws SQLException {
        List<String> avaliableTokens = null;
        DynamicInspection form = db.getInspectionData(inspectionDesignId, inspectionGuid); //TODO: see why global form data is collected twice
        GlobalInspectionInfo globalInspectionInfo = db.getGlobalInspectionInfo(inspectionGuid);
        Map<String, Object> dynamicFormDataValues = form.getFormDataValues();
        Map<String, String> returnTokenValues = new HashMap<String, String>();
        Iterator availableTokensIterator = null;
        avaliableTokens = getAvailableTokens(db, -1, inspectionDesignId);
        availableTokensIterator = avaliableTokens.iterator();
        while (availableTokensIterator.hasNext()) {
            String completeToken = (String) availableTokensIterator.next();
            String tokenName = getTokenName(completeToken);
            String stringValue = null;
            boolean isExistingDynamicToken = dynamicFormDataValues.containsKey(tokenName); 
            Object objectValue = null;
            boolean isNullValue = true;
            if (isExistingDynamicToken) {
            	objectValue = dynamicFormDataValues.get(tokenName);
            	isNullValue = (objectValue == null);
            }
            if (!isNullValue) {
                if (objectValue instanceof String) {
                	stringValue = objectValue.toString();
                }
            } else {
	            // if it's a global token, populate value here
	            if (Arrays.asList(GLOBAL_INSPECTION_TOKENS).contains(tokenName)) {
	            	stringValue = getGlobalInspectionValueByTokenName(globalInspectionInfo, tokenName);
	            } 
            }
            if (stringValue == null) stringValue = "";
            returnTokenValues.put(tokenName, stringValue);
        }
        return returnTokenValues;
    }

    private static String getGlobalFormValueByTokenName(GlobalFormInfo globalFormInfo, String tokenName) {
        // NOTE: this is inelegant maybe, but will perform better than using reflection
        // Insane that FormInfo and InspectionInfo aren't derived from a common parent class - JS
        String returnValue = null;
        final String s = GLOBAL_TEXT_TOKENS[1];
        switch (tokenName) {
            case "FormType":
                returnValue = globalFormInfo.getFormType();
                break;
            case "FormTitle":
                returnValue = globalFormInfo.getFormTitle();
                break;
            case "MunicipalityName":
                returnValue = globalFormInfo.getMunicipalityName();
                break;
//            case "MunicipalityLogo":
//                returnValue = globalFormInfo.getMunicipalityLogo(); // TODO: deal with byte logo
            case "MunicipalityAddress":
                returnValue = globalFormInfo.getMunicipalityAddress().toString();
                break;
            case "MunicipalityAddressLine1":
                returnValue = globalFormInfo.getMunicipalityAddress().getAddressLine1();
                break;
            case "MunicipalityAddressLine2":
                returnValue = globalFormInfo.getMunicipalityAddress().getAddressLine2();
                break;
            case "MunicipalityCity":
                returnValue = globalFormInfo.getMunicipalityAddress().getCity();
                break;
            case "MunicipalityState":
                returnValue = globalFormInfo.getMunicipalityAddress().getState();
                break;
            case "MunicipalityPostalCode":
                returnValue = globalFormInfo.getMunicipalityAddress().getPostalCode();
                break;
            case "ContractorName":
                returnValue = globalFormInfo.getVendorCompanyName();
                break;
            case "ContractorAddress":
                returnValue = globalFormInfo.getVendorAddress().toString();
                break;
            case "LocationLot":
                returnValue = globalFormInfo.getLocationLot().toString();
                break;
            case "Location":
                returnValue = globalFormInfo.getLocationAddress().toString();
                break;
            case "LocationMap":
                double latitude = globalFormInfo.getLocationLatitude();
                double longitude = globalFormInfo.getLocationLongitude();
                returnValue = latitude + ", " + longitude;
                break;
            case "LocationParcel":
                returnValue = globalFormInfo.getLocationParcel();
                break;
            case "Subdivision":
                returnValue = globalFormInfo.getSubdivision();
                break;
            case "UserSubmitted":
                returnValue = globalFormInfo.getUserSubmitted();
                break;
            case "DateGenerated":
                Date dateGenerated = globalFormInfo.getDateGenerated();
                if (dateGenerated != null) {
                    returnValue =  dateGenerated.toString();
                }
                break;
            case "Fees":
                BigDecimal totalFees = globalFormInfo.getTotalFees();
                if (totalFees != null) {
                    returnValue = totalFees.toString();
                }
                break;
            case "AmountDue":
                BigDecimal balanceDue = globalFormInfo.getBalanceDue();
                if (balanceDue != null) {
                    returnValue = balanceDue.toString();
                }
                break;
            case "Owner":
                returnValue = globalFormInfo.getOwnerFirstName() + " " + globalFormInfo.getOwnerLastName();
                break;
            case "OwnerAddress":
                returnValue = globalFormInfo.getOwnerAddress().toString();
            case "Tenant":
                returnValue = globalFormInfo.getTenantFirstName() + " " + globalFormInfo.getTenantLastName();
                break;
            case "Violations":
                returnValue = "getViolations()"; // TODO: implement violations
                break;
            case "ExpiresDate":
                Date expiresData = globalFormInfo.getExpiresDate();
                if (expiresData != null) {
                    returnValue = expiresData.toString();
                }
                break;
            case "IssuedDate":
                Date issuedDate = globalFormInfo.getIssuedDate();
                if (issuedDate != null) {
                    returnValue = issuedDate.toString();
                }
                break;
        }
        return returnValue;
    }
    
    private static String getGlobalInspectionValueByTokenName(GlobalInspectionInfo globalInspectionInfo, String tokenName) {
        // NOTE: this is inelegant maybe, but will perform better than using reflection
        String returnValue = null;
        final String s = GLOBAL_INSPECTION_TOKENS[1];
        switch (tokenName) {
            case "FormType":
                returnValue = globalInspectionInfo.getInspectionType();
                break;
            case "FormTitle":
                returnValue = globalInspectionInfo.getFormTitle();
                break;
            case "MunicipalityName":
                returnValue = globalInspectionInfo.getMunicipalityName();
                break;
//            case "MunicipalityLogo":
//                returnValue = globalInspectionInfo.getMunicipalityLogo(); // TODO: deal with byte logo
            case "MunicipalityAddress":
                returnValue = globalInspectionInfo.getMunicipalityAddress().toString();
                break;
            case "MunicipalityAddressLine1":
                returnValue = globalInspectionInfo.getMunicipalityAddress().getAddressLine1();
                break;
            case "MunicipalityAddressLine2":
                returnValue = globalInspectionInfo.getMunicipalityAddress().getAddressLine2();
                break;
            case "MunicipalityCity":
                returnValue = globalInspectionInfo.getMunicipalityAddress().getCity();
                break;
            case "MunicipalityState":
                returnValue = globalInspectionInfo.getMunicipalityAddress().getState();
                break;
            case "MunicipalityPostalCode":
                returnValue = globalInspectionInfo.getMunicipalityAddress().getPostalCode();
                break;
            case "ContractorName":
                returnValue = globalInspectionInfo.getVendorCompanyName();
                break;
            case "ContractorAddress":
                returnValue = globalInspectionInfo.getVendorAddress().toString();
                break;
            case "LocationLot":
                returnValue = globalInspectionInfo.getLocationLot().toString();
                break;
            case "Location":
                returnValue = globalInspectionInfo.getLocationAddress().toString();
                break;                
            case "LocationMap":
                double latitude = globalInspectionInfo.getLocationLatitude();
                double longitude = globalInspectionInfo.getLocationLongitude();
                returnValue = latitude + ", " + longitude;
                break;
            case "LocationParcel":
                returnValue = globalInspectionInfo.getLocationParcel();                
                break;
            case "Subdivision":
                returnValue = globalInspectionInfo.getSubdivision();
                break;
            case "Fees":
                BigDecimal totalFees = globalInspectionInfo.getTotalFees();
                if (totalFees != null) {
                    returnValue = totalFees.toString();
                }
                break;
            case "AmountDue":
                BigDecimal balanceDue = globalInspectionInfo.getBalanceDue();
                if (balanceDue != null) {
                    returnValue = balanceDue.toString();
                }
                break;
            case "Owner":
                returnValue = globalInspectionInfo.getOwnerFirstName() + " " + globalInspectionInfo.getOwnerLastName();
                break;
            case "OwnerAddress":
                returnValue = globalInspectionInfo.getOwnerAddress().toString();
            case "Tenant":
                returnValue = globalInspectionInfo.getTenantFirstName() + " " + globalInspectionInfo.getTenantLastName();
                break;
            case "Violations":
                returnValue = "getViolations()"; // TODO: implement violations
                break;
            case "ExpiresDate":
                Date expiresData = globalInspectionInfo.getExpiresDate();
                if (expiresData != null) {
                    returnValue = expiresData.toString();
                }
                break;
            case "IssuedDate":
                Date issuedDate = globalInspectionInfo.getIssuedDate();
                if (issuedDate != null) {
                    returnValue = issuedDate.toString();
                }
                break;
        }
        return returnValue;
    }

    public static List<String> getAvailableTokens(Cx2DataAccess data, long formTypeId, long inspectionDesignId) throws SQLException {
        List<String> availableTokens = new ArrayList<String>();
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.addAll(Arrays.asList(GLOBAL_TEXT_TOKENS));
        fieldNames.addAll(Arrays.asList(GLOBAL_IMAGE_TOKENS));
        fieldNames.addAll(data.getFormTypeFieldNames(formTypeId, inspectionDesignId));
        for (String fieldName : fieldNames) {
            availableTokens.add("[" + fieldName + "]");
        }
        return availableTokens;
    }

    private static String getTokenName(String completeToken) {
        Pattern p = Pattern.compile(TOKEN_PATTERN);
        Matcher m = p.matcher(completeToken);
        String tokenName = null;
        if (m.find()) {
            tokenName = m.group(TOKEN_NAME_GROUP_INDEX);
        }
        return tokenName;
    }


}
