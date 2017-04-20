/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.letterservice;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.wavemaker.runtime.file.model.DownloadResponse;

import com.civicxpress.LetterTemplate;
import com.civicxpress.SectionalTemplatePdf;
import com.civicxpress.Cx2DataAccess;
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

    public List<String> getAvailableTokens(int formTypeId) {
    	Cx2DataAccess.setSqlUrl(sqlUrl);
        List<String> availableTokens = null;
        availableTokens = LetterTemplate.getAvailableTokens(formTypeId);
        return availableTokens;
    }
   
    public DownloadResponse createLetter(Long formTypeId, String formGuid, String bodyTopRightCustomTextTemplate) {
    	Cx2DataAccess.setSqlUrl(sqlUrl);
        String bodyTopLeftTitleTemplate = "LOT NO.";
        String bodyTopLeftCustomTextTemplate = "12";
        String bodyBottomTitleTemplate = "OPEN BURNING PROHIBITED";
        String bodyBottomCustomTextTemplate = "All construction shall comply with applicable codes and zoning adopted by the City of Fishers. \r\nReinspection fees will be charged for all inspections that have failed or are not ready when requested.\r\n\r\nIssued by Todd Suchy, Building Inspector";
        String bodyFooterCustomText = "THIS PERMIT MUST BE POSTED WHERE IT IS\r\nVISIBLE FROM THE STREET";
        byte[] fileBytes = SectionalTemplatePdf.createLetter(formTypeId,formGuid,
                bodyTopLeftTitleTemplate, bodyTopLeftCustomTextTemplate, bodyTopRightCustomTextTemplate,
                bodyBottomTitleTemplate, bodyBottomCustomTextTemplate, bodyFooterCustomText,
                false);
        
        ByteArrayInputStream downloadBais = new ByteArrayInputStream(fileBytes);
        
        DownloadResponse dr = new DownloadResponse();
        dr.setContents(downloadBais);
        dr.setContentType("application/pdf");
        dr.setFileName("Test-Letter-generation.pdf");
        
        return dr;
    }
}
