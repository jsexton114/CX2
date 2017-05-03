/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.letterservice;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.wavemaker.runtime.file.model.DownloadResponse;

import com.civicxpress.letters.LetterTemplate;
import com.civicxpress.letters.SectionalTemplatePdf;
import com.civicxpress.letters.Cx2DataAccess;
import com.civicxpress.letters.DatabaseConnectionInfo;
import com.civicxpress.letters.GlobalFormInfo;
import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;

//import com.civicxpress.letterservice.model.*;

@ExposeToClient
public class LetterService {

    private static final Logger logger = LoggerFactory.getLogger(LetterService.class);

    @Autowired
    private SecurityService securityService;

    @Value("${cx2.url}")
    private String sqlUrl = "jdbc:sqlserver://192.168.2.211:1433;databaseName=CX2_DEV";

    @Value("${cx2.username}")
    private String defaultSqlUser = "cx2";

    @Value("${cx2.password}")
    private String defaultSqlPassword = "F!yingFishCove1957";

    public List<String> getAvailableTokens(int formTypeId) {
		Cx2DataAccess db = new Cx2DataAccess(sqlUrl, defaultSqlUser, defaultSqlPassword);
        List<String> availableTokens = null;
        availableTokens = LetterTemplate.getAvailableTokens(db, formTypeId);
        return availableTokens;
    }

    public SectionalTemplatePdf getLetterTemplate(Integer letterTemplateId) {
    	SectionalTemplatePdf lt = null;
//		DatabaseConnectionInfo dbInfo = new DatabaseConnectionInfo();
//		dbInfo.readFromWebContext();
//		Cx2DataAccess db = new Cx2DataAccess(dbInfo);

		Cx2DataAccess db = new Cx2DataAccess(sqlUrl, defaultSqlUser, defaultSqlPassword);

		if (letterTemplateId == null) {
			lt = new SectionalTemplatePdf();
	    	lt.setUpDefaultSections();
		} else {
			lt = db.getLetterTemplate(letterTemplateId);
		}

		return lt;
    }

    public void updateLetterTemplate(SectionalTemplatePdf letterTemplate) throws SQLException {
    	Cx2DataAccess db = new Cx2DataAccess(sqlUrl, defaultSqlUser, defaultSqlPassword);

    	db.updateLetterTemplate(letterTemplate);
    }

    public DownloadResponse createLetter(Long formTypeId, String formGuid, int letterTemplateId) {
		SectionalTemplatePdf lt = null;
//		DatabaseConnectionInfo dbInfo = new DatabaseConnectionInfo();
//		dbInfo.readFromWebContext();
		Cx2DataAccess db = new Cx2DataAccess(sqlUrl, defaultSqlUser, defaultSqlPassword);
		lt = db.getLetterTemplate(letterTemplateId);
        GlobalFormInfo globalFormInfo = db.getGlobalFormInfo(formTypeId, formGuid);
        Map<String, String> textTokens = LetterTemplate.getTextTokenValues(db, formTypeId, formGuid);
        byte[] fileBytes = lt.createLetter(globalFormInfo, textTokens);
        ByteArrayInputStream downloadBais = new ByteArrayInputStream(fileBytes);
        DownloadResponse dr = new DownloadResponse();
        dr.setContents(downloadBais);
        dr.setContentType("application/pdf");
        dr.setFileName(formGuid + ".pdf");
        return dr;
    }
}
