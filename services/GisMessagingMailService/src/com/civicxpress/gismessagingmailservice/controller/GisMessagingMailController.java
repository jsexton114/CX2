/**This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
package com.civicxpress.gismessagingmailservice.controller;

import com.civicxpress.gismessagingmailservice.GisMessagingMailService;
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
@RequestMapping(value = "/gisMessagingMail")
public class GisMessagingMailController {

    @Autowired
    private GisMessagingMailService gisMessagingMailService;

    @RequestMapping(value = "/sendMessagingMail", produces = "application/json", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public String sendMessagingMail(@RequestParam(value = "sender", required = false) String sender, @RequestParam(value = "comments", required = false) String comments, @RequestParam(value = "username", required = false) String username, @RequestParam(value = "recipient", required = false) String recipient, @RequestParam(value = "municipality", required = false) String municipality, @RequestParam(value = "subdivision", required = false) String subdivision, @RequestParam(value = "municipalitySignature", required = false) String municipalitySignature, @RequestParam(value = "address", required = false) String address, @RequestParam(value = "gisLink", required = false) String gisLink) throws MessagingException {
        return gisMessagingMailService.sendMessagingMail(sender, comments, username, recipient, municipality, subdivision, municipalitySignature, address, gisLink);
    }
}
