/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.documentservice;

import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.google.common.io.BaseEncoding;
import com.google.gson.Gson;
import com.tekdog.dbutils.DBQueryParams;
import com.tekdog.dbutils.DBRow;
import com.tekdog.dbutils.DBUtils;
import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.file.model.DownloadResponse;
import com.wavemaker.runtime.service.annotations.ExposeToClient;
import com.wavemaker.runtime.service.annotations.HideFromClient;

//import com.civicxpress.documentservice.model.*;

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
public class DocumentService {

    private static final Logger logger = LoggerFactory.getLogger(DocumentService.class);
    
    private static final String docServiceBaseUrl = "http://192.168.2.233:8080/documents-service/";

    @Autowired
    private SecurityService securityService;
    
    @Value("${cx2.url}")
    private String sqlUrl;
    
    @Value("${cx2.username}")
    private String defaultSqlUser;
    
    @Value("${cx2.schemaName}")
    private String defaultSqlDatabase;
    
    @Value("${cx2.password}")
    private String defaultSqlPassword = "F!yingFishCove1957";

    public class Response {

	   public Response() {
	      super();
	   };

	   // Any arbitrary data the user may wish to pass along through the LEADTOOLS Documents library to the service.
	   public Object userData;
	}
    
    public class BeginUploadResponse extends Response {

	   public BeginUploadResponse() {
	      super();
	   }

	   // The URI to which the document should be uploaded to the cache.
	   public URI uploadUri;
	}
    
    public class Request {
	   // Any arbitrary data the user may wish to pass along through the LEADTOOLS Documents library to the service.
	   public Object userData;
	}
    
    private DBRow getDocument(Connection cx2Conn, Long documentId) throws SQLException {
    	DBQueryParams queryParams = new DBQueryParams();
    	queryParams.addLong("documentId", documentId);
    	
    	DBRow documentData = DBUtils.selectQuery(cx2Conn, "SELECT * FROM Document WHERE ID=:documentId", queryParams).get(0);
    	
    	return documentData;
    }
    
    public DownloadResponse editDocument(Long documentId) throws Exception {
    	DownloadResponse dr = new DownloadResponse();
    	
    	return dr;
    }
    
    private HttpResponse httpPost(String controller, String method, HashMap<String, Object> payload) throws Exception {
    	Gson gson = new Gson();
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	HttpPost postObj = new HttpPost(docServiceBaseUrl.concat(controller).concat("/").concat(method));
    	StringEntity params = new StringEntity(gson.toJson(payload));
    	
    	postObj.setEntity(params);
    	postObj.setHeader("Content-type", "application/json");
    	
    	return httpClient.execute(postObj);
    }
    
    public class PingResponse extends Response {

    	   public PingResponse() {
    	      super();
    	   }

    	   public String message;
    	   public Date time;
    	   public boolean isLicenseChecked;
    	   public boolean isLicenseExpired;
    	   public String kernelType;
    	   public boolean isCacheAccessible;
    	}
    
    @RequestMapping(value = "/ping", method = RequestMethod.POST)
    public PingResponse ping() {
    	PingResponse pingResponse = new PingResponse();
    	pingResponse.message = "I'm gay";
    	pingResponse.time = new Date();
    	pingResponse.isLicenseChecked = true;
    	pingResponse.isLicenseExpired = true;
    	pingResponse.kernelType = "gay";
    	pingResponse.isCacheAccessible = true;
    	
    	return pingResponse;
    }

    @RequestMapping(value = "/beginUpload", method = RequestMethod.POST)
    public String beginUpload() throws Exception {
    	Connection cx2Conn = DBUtils.getConnection(sqlUrl, defaultSqlUser, defaultSqlPassword);
    	
    	HashMap<String, Object> payload = new HashMap<String, Object>();
    	
    	HttpResponse rawResponse = httpPost("Factory", "BeginUpload", payload);
    	
    	logger.error(rawResponse.toString());
    	
    	BeginUploadResponse response = new BeginUploadResponse();
    	
    	return rawResponse.toString();
     }

     // Uploads a chunk of data to the specified URL in the cache.
     public HttpResponse uploadDocument(URI uri, String data) throws Exception {
//        ServiceHelper.initService(uriInfo, servletContext, true);
//        ServiceHelper.checkNullRequest(request);
//
//        byte[] byteArray = null;
//        if (request.data != null)
//           byteArray = BaseEncoding.base64().decode(request.data);
//
//        DocumentFactory.uploadDocument(request.uri, byteArray, 0, byteArray != null ? byteArray.length : 0);

//    	CloseableHttpClient httpClient = HttpClients.createDefault();
//     	HttpPost sendDocument = new HttpPost("...");
//     	MultipartEntityBuilder request = MultipartEntityBuilder.create();
//     	request.addBinaryBody("uploadFile", documentData.getBytes("Contents"), ContentType.APPLICATION_OCTET_STREAM, documentData.getString("Filename"));
//     	request.addTextBody("resolution", resolution.toString());
//     	request.addTextBody("options", options);
//     	
//     	HttpEntity multipart = request.build();
//     	sendDocument.setEntity(multipart);
//     	CloseableHttpResponse docserviceresponse = httpClient.execute(sendDocument);
//     	HttpEntity response = docserviceresponse.getEntity();
     	
        Response response = new Response();
        HashMap<String, Object> payload = new HashMap<String, Object>();
        payload.put("data", data);
        payload.put("uri", uri.toString());
        // response.userData = request.userData;
        return httpPost("Factory", "UploadDocument", payload);
     }
}
