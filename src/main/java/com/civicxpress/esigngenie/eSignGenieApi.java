package com.civicxpress.esigngenie;

import com.google.gson.Gson;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jason on 3/14/2017.
 */
public class eSignGenieApi {

    public static String createFolder(byte[] pdfBytes,
                                      String folderName, String clientId, String clientSecret,
                                      String firstNameOfRecipientParty, String lastNameOfRecipientParty, String emailIdOfRecipientParty)
            throws MalformedURLException, IOException {
        LinkedHashMap<String, String> nvc = new LinkedHashMap<String, String>();
        Gson gson = new Gson();
        String accessTokenString = null;
        AccessTokenObject accessTokenObject = null;
        String webAddress = "https://www.esigngenie.com/esign/api/folders/createfolder";
        String response = null;

        try {
            accessTokenString = generateAccessToken(clientId, clientSecret);
        } catch (MalformedURLException ex) {
            System.out.println("The eSign Genie generate access token URL is malformed.");
        } catch (IOException ex) {
            System.out.println("There was an IO error trying to generate the eSign Genie access token.");
        }

        String json = constructSigningRequestJson(folderName, firstNameOfRecipientParty, lastNameOfRecipientParty, emailIdOfRecipientParty);
        System.out.println("Signing request is " + json);
        accessTokenObject = gson.fromJson(accessTokenString, AccessTokenObject.class);
        System.out.println("Access token is " + accessTokenObject.access_token);

        nvc.put("data", json);
        response = httpUploadFile(webAddress, pdfBytes, folderName, "file", "application/pdf", nvc, accessTokenObject.access_token);
        return response;
    }

    private static String generateAccessToken(String clientId, String clientSecret) throws MalformedURLException, IOException {
        URL url = new URL("https://www.esigngenie.com/esign/api/oauth2/access_token");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        String returnValue = null;
        con.setDoOutput(true);
        con.setUseCaches(false);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        String urlParameters = "grant_type=client_credentials&client_id=" + clientId + "&client_secret=" + clientSecret + "&scope=read-write";

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);

        if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : " + con.getResponseCode());
        }
        BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
            returnValue = output;
        }

        con.disconnect();  // ? I left this out yesterday?
        return (returnValue);
    }

    private static String constructSigningRequestJson(String folderName,
                                                      String firstNameOfRecipientParty, String lastNameOfRecipientParty,
                                                      String emailIdOfRecipientParty) {
        String returnValue = "{\"folderName\":\"" + folderName
                + "\", \"processTextTags\":true, \"signInSequence\":false, \"sendNow\":false, \"createEmbeddedSendingSession\":false, \"fixRecipientParties\":true, \"fixDocuments\":true, \"sendSuccessUrl\":null, \"sendErrorUrl\":null, \"createEmbeddedSigningSession\":true, \"createEmbeddedSigningSessionForAllParties\":true, \"signSuccessUrl\":null, \"signDeclineUrl\":null, \"signLaterUrl\":null, \"signErrorUrl\":null, \"themeColor\":\"white\", \"parties\":[ { \"firstName\":\"" + firstNameOfRecipientParty + "\", \"lastName\":\"" + lastNameOfRecipientParty + "\", \"emailId\":\"" + emailIdOfRecipientParty + "\", \"permission\":\"FILL_FIELDS_AND_SIGN\", \"sequence\":1 } ] }";
        return returnValue;
    }

    // "Upload files with HTTPWebrequest (multipart/form-data)", Cristian Romanescu, 6/8/2010, retrieved 3/4/2017
    // http://stackoverflow.com/questions/566462/upload-files-with-httpwebrequest-multipart-form-data
    private static String httpUploadFile(String url, byte[] fileBytes, String filename, String paramName, String contentType,
                                         LinkedHashMap nvc, String accessTokenString)
            throws MalformedURLException, IOException {

        List<String> response = null;
        String boundary = "---------------------------" + Long.toHexString(System.currentTimeMillis());
        byte[] boundaryBytes = ("\r\n--" + boundary + "\r\n").getBytes(StandardCharsets.UTF_8);

        URL urlObject = new URL(url);
        HttpURLConnection wr = (HttpURLConnection) urlObject.openConnection();
        wr.setDoOutput(true);
        wr.setRequestProperty("Content-Type", "multipart/form-data; boundary=\"" + boundary + "\"");
        wr.setRequestProperty("Authorization", "Bearer " + accessTokenString);

        OutputStream rs = wr.getOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(rs, "UTF-8"), true);
        String formDataTemplate = "Content-Disposition: form-data; name=\"%s\"\r\n\r\n%s";

        rs.write(boundaryBytes, 0, boundaryBytes.length);

        for (Object key : nvc.keySet()) {
            System.out.println(key + ":\t" + nvc.get(key));
            String formItem = String.format(formDataTemplate, key, nvc.get(key));
            byte[] formItemBytes = formItem.getBytes(StandardCharsets.UTF_8);
            rs.write(formItemBytes, 0, formItemBytes.length);
        }
        rs.write(boundaryBytes, 0, boundaryBytes.length);

        String headerTemplate = "Content-Disposition: form-data; name=\"%s\"; filename=\"%s\"\r\nContent-Type: %s\r\n\r\n";
        String header = String.format(headerTemplate, paramName, filename, contentType);
        byte[] headerBytes = header.getBytes(StandardCharsets.UTF_8);
        rs.write(headerBytes, 0, headerBytes.length);

        String s = new String(fileBytes, 0, fileBytes.length, StandardCharsets.UTF_8);
        rs.write(s.getBytes());
        rs.flush();

        byte[] trailer = ("\r\n--" + boundary + "--\r\n").getBytes(StandardCharsets.UTF_8);
        rs.write(trailer, 0, trailer.length);

        response = new ArrayList<String>();
        if (wr.getResponseCode() == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(wr.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                response.add(line);
            }

            reader.close();
            wr.disconnect();
        } else {
            throw new IOException("Server returned non-OK status: " + wr.getResponseCode());
        }

        System.out.println("Finally Reply from server " + wr.getResponseCode());

        writer.close();
        wr.disconnect();

        return response.toString();
    }

    public final class AccessTokenObject {
        public final String access_token;
        public final String token_type;
        public final long expires_in;

        public AccessTokenObject(String access_token, String token_type, long expires_in) {
            this.access_token = access_token;
            this.token_type = token_type;
            this.expires_in = expires_in;
        }
    }

}
