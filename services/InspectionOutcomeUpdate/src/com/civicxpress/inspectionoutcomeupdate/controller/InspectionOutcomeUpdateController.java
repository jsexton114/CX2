/**This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
package com.civicxpress.inspectionoutcomeupdate.controller;

import com.civicxpress.inspectionoutcomeupdate.InspectionOutcomeUpdate;
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
@RequestMapping(value = "/inspectionOutcomeUpdate")
public class InspectionOutcomeUpdateController {

    @Autowired
    private InspectionOutcomeUpdate inspectionOutcomeUpdate;

    @RequestMapping(value = "/sendOutcomeUpdateMail", produces = "application/json", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public String sendOutcomeUpdateMail(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "recipient", required = false) String recipient, @RequestParam(value = "emailSubject", required = false) String emailSubject, @RequestParam(value = "emailBody", required = false) String emailBody, @RequestParam(value = "municipality", required = false) String municipality, @RequestParam(value = "inspectionDesign", required = false) String inspectionDesign, @RequestParam(value = "feesAssessed", required = false) String feesAssessed, @RequestParam(value = "lot", required = false) String lot, @RequestParam(value = "fullAddress", required = false) String fullAddress, @RequestParam(value = "inspectionOutcome", required = false) String inspectionOutcome, @RequestParam(value = "subdivision", required = false) String subdivision, @RequestParam(value = "municipalitySignature", required = false) String municipalitySignature, @RequestParam(value = "inspectionTitle", required = false) String inspectionTitle, @RequestParam(value = "formLink", required = false) String formLink) throws MessagingException {
        return inspectionOutcomeUpdate.sendOutcomeUpdateMail(username, recipient, emailSubject, emailBody, municipality, inspectionDesign, feesAssessed, lot, fullAddress, inspectionOutcome, subdivision, municipalitySignature, inspectionTitle, formLink);
    }
}
