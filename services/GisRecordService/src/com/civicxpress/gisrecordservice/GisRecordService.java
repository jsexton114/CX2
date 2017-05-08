/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.gisrecordservice;

import com.civicxpress.dbconnectionservice.DBConnectionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.tekdog.dbutils.*;

//import com.civicxpress.gisrecordservice.model.*;

@ExposeToClient
public class GisRecordService {

    private static final Logger logger = LoggerFactory.getLogger(GisRecordService.class);

    @Autowired
    private SecurityService securityService;

    private void uploadDocuments(Connection cx2Conn, MultipartFile[] files, Long gisRecordId) throws SQLException, IOException {
    	StringBuilder documentAddQuery = new StringBuilder("INSERT INTO Document (GisRecordId, Filename, Mimetype, Contents, CreatedBy) VALUES ");

    	DBQueryParams queryParams = new DBQueryParams();
    	queryParams.addLong("gisRecordId", gisRecordId);
    	queryParams.addLong("createdBy", Long.parseLong(securityService.getUserId()));

        for (int i = 0; i < files.length; i++) {
        	MultipartFile file = files[i];

        	if (i > 0) {
        		documentAddQuery.append(',');
        	}

        	queryParams.addString("doc"+i+"filename", file.getOriginalFilename());
        	queryParams.addString("doc"+i+"mimetype", file.getContentType());
        	queryParams.addBytes("doc"+i+"contents", file.getBytes());

        	documentAddQuery.append("(:gisRecordId, :doc"+i+"filename, :doc"+i+"mimetype, :doc"+i+"contents, :createdBy)");
        }

        if (files.length > 0) {
        	DBUtils.simpleUpdateQuery(cx2Conn, documentAddQuery.toString(), queryParams);
        }
    }

    public void uploadDocuments(MultipartFile[] files, Long gisRecordId) throws SQLException {
        Connection cx2Conn = DBConnectionService.getConnection();

    	cx2Conn.setAutoCommit(false);

    	try {
	        uploadDocuments(cx2Conn, files, gisRecordId);

	        cx2Conn.commit();
        } catch (IOException e) {
        	cx2Conn.rollback();
			e.printStackTrace();
		} finally {
			cx2Conn.close();
		}
    }
}
