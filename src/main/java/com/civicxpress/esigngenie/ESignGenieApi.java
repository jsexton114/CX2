package com.civicxpress.esigngenie;

import com.google.gson.Gson;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.civicxpress.pdfutilities.PdfUtilities;  // used in WM

/**
 * Created by jason on 3/14/2017.
 */
public class ESignGenieApi {

    public static String createAndSignDocument(String title, Map<String, Object> formData, String clientId, String clientSecret,
                                                String firstNameOfRecipientParty, String lastNameOfRecipientParty, String emailIdOfRecipientParty)
            throws IOException {
        String uploadResponse = null;
        String folderAccessUrl = null;
        String filePath = null;
        filePath = PdfUtilities.createDynamicFormPdf(title, formData, true);
        System.out.println("filePath: " + filePath);
        uploadResponse = ESignGenieApi.createFolder(filePath, title + ".pdf",
                title, clientId, clientSecret, firstNameOfRecipientParty, lastNameOfRecipientParty, emailIdOfRecipientParty);
        System.out.println(uploadResponse);
        folderAccessUrl = getFolderAccessUrl(uploadResponse);
        System.out.println("folderAccessURL: " + folderAccessUrl);

        return folderAccessUrl;
    }


    public static String createFolder(String filePath, String filename,
                                      String folderName, String clientId, String clientSecret,
                                      String firstNameOfRecipientParty, String lastNameOfRecipientParty, String emailIdOfRecipientParty)
                throws IOException {
        final String WEBADDRESS = "https://www.esigngenie.com/esign/api/folders/createfolder";
        LinkedHashMap<String, String> nvc = new LinkedHashMap<String, String>();
        Gson gson = new Gson();
        String accessTokenString = null;
        AccessTokenObject accessTokenObject;
        String response;
        String json;

        try {
            accessTokenString = generateAccessToken(clientId, clientSecret);
        } catch (MalformedURLException ex) {
            System.out.println("The eSign Genie generate access token URL is malformed.");
        } catch (IOException ex) {
            System.out.println("There was an IO error trying to generate the eSign Genie access token.");
        }

        json = constructSigningRequestJson(folderName, firstNameOfRecipientParty, lastNameOfRecipientParty, emailIdOfRecipientParty);
        System.out.println("Signing request is " + json);
        accessTokenObject = gson.fromJson(accessTokenString, AccessTokenObject.class);
        System.out.println("Access token is " + accessTokenObject.access_token);
        nvc.put("data", json);
        response = httpUploadFile(WEBADDRESS, filePath, filename, "file", "application/pdf", nvc, accessTokenObject.access_token);

        return response;
    }

    private static String generateAccessToken(String clientId, String clientSecret) throws MalformedURLException, IOException {
        URL url = new URL("https://www.esigngenie.com/esign/api/oauth2/access_token");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        String returnValue = null;
        String urlParameters = "grant_type=client_credentials&client_id=" + clientId + "&client_secret=" + clientSecret + "&scope=read-write";
        DataOutputStream wr;
        BufferedReader br;
        String output = null;

        con.setDoOutput(true);
        con.setUseCaches(false);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);

        if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : " + con.getResponseCode());
        }

        br = new BufferedReader(new InputStreamReader((con.getInputStream())));
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
            returnValue = output;
        }

        con.disconnect();
        return (returnValue);
    }

    private static String constructSigningRequestJson(String folderName,
                                                      String firstNameOfRecipientParty, String lastNameOfRecipientParty,
                                                      String emailIdOfRecipientParty) {
        String returnValue;
        returnValue = "{\"folderName\":\"" + folderName
                + "\", \"processTextTags\":true, \"signInSequence\":false, \"sendNow\":false, \"createEmbeddedSendingSession\":false, \"fixRecipientParties\":true, \"fixDocuments\":true, \"sendSuccessUrl\":null, \"sendErrorUrl\":null, \"createEmbeddedSigningSession\":true, \"createEmbeddedSigningSessionForAllParties\":true, \"signSuccessUrl\":null, \"signDeclineUrl\":null, \"signLaterUrl\":null, \"signErrorUrl\":null, \"themeColor\":\"white\", \"parties\":[ { \"firstName\":\"" + firstNameOfRecipientParty + "\", \"lastName\":\"" + lastNameOfRecipientParty + "\", \"emailId\":\"" + emailIdOfRecipientParty + "\", \"permission\":\"FILL_FIELDS_AND_SIGN\", \"sequence\":1 } ] }";
        return returnValue;
    }

    // "Upload files with HTTPWebrequest (multipart/form-data)", Cristian Romanescu, 6/8/2010, retrieved 3/4/2017
    // http://stackoverflow.com/questions/566462/upload-files-with-httpwebrequest-multipart-form-data
    private static String httpUploadFile(String url, String filePath, String filename, String paramName, String contentType,
                                         LinkedHashMap nvc, String accessTokenString)
                throws IOException {
        List<String> response;
        String boundary = "---------------------------" + Long.toHexString(System.currentTimeMillis());
        byte[] boundaryBytes = ("\r\n--" + boundary + "\r\n").getBytes(StandardCharsets.UTF_8);
        URL urlObject = new URL(url);
        HttpURLConnection wr = (HttpURLConnection) urlObject.openConnection();
        OutputStream rs;
        PrintWriter writer;
        String formDataTemplate;
        String headerTemplate;
        String header;
        byte[] headerBytes;
        File binaryFile;
        byte[] trailer;

        System.out.println(String.format("Uploading %s to %s", filePath, url));
        wr.setDoOutput(true);
        wr.setRequestProperty("Content-Type", "multipart/form-data; boundary=\"" + boundary + "\"");
        wr.setRequestProperty("Authorization", "Bearer " + accessTokenString);
        rs = wr.getOutputStream();
        writer = new PrintWriter(new OutputStreamWriter(rs, "UTF-8"), true);
        formDataTemplate = "Content-Disposition: form-data; name=\"%s\"\r\n\r\n%s";
        rs.write(boundaryBytes, 0, boundaryBytes.length);

        for (Object key : nvc.keySet()) {
            System.out.println(key + ":\t" + nvc.get(key));
            String formItem = String.format(formDataTemplate, key, nvc.get(key));
            byte[] formItemBytes = formItem.getBytes(StandardCharsets.UTF_8);
            rs.write(formItemBytes, 0, formItemBytes.length);
        }

        rs.write(boundaryBytes, 0, boundaryBytes.length);
        headerTemplate = "Content-Disposition: form-data; name=\"%s\"; filename=\"%s\"\r\nContent-Type: %s\r\n\r\n";
        header = String.format(headerTemplate, paramName, filename, contentType);
        headerBytes = header.getBytes(StandardCharsets.UTF_8);
        rs.write(headerBytes, 0, headerBytes.length);
        binaryFile = new File(filePath);
        Files.copy(binaryFile.toPath(), rs);
        rs.flush();
        trailer = ("\r\n--" + boundary + "--\r\n").getBytes(StandardCharsets.UTF_8);
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

    private static String getFolderAccessUrl(String uploadResponse) {
        String folderAccessUrl = null;
        Pattern p = Pattern.compile("\"folderAccessURL\":\"(.*?)\"");
        Matcher m = p.matcher(uploadResponse);
        boolean isFound = m.find();
        folderAccessUrl = m.group(1);
        return folderAccessUrl;
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
