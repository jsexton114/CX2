/**This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
package com.civicxpress.formstatusupdate.controller;

import com.civicxpress.formstatusupdate.FormStatusUpdate;
import java.lang.String;
import javax.mail.MessagingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;

@RestController
@RequestMapping(value = "/formStatusUpdate")
public class FormStatusUpdateController {

    @Autowired
    private FormStatusUpdate formStatusUpdate;

    @RequestMapping(value = "/sendStatusUpdateMail", produces = "application/json", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public String sendStatusUpdateMail(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "recipient", required = false) String recipient, @RequestParam(value = "emailSubject", required = false) String emailSubject, @RequestParam(value = "emailBody", required = false) String emailBody, @RequestParam(value = "municipality", required = false) String municipality, @RequestParam(value = "formType", required = false) String formType, @RequestParam(value = "municipalitySignature", required = false) String municipalitySignature, @RequestParam(value = "formTitle", required = false) String formTitle, @RequestParam(value = "formLink", required = false) String formLink) throws MessagingException {
        return formStatusUpdate.sendStatusUpdateMail(username, recipient, emailSubject, emailBody, municipality, formType, municipalitySignature, formTitle, formLink);
    }
}
