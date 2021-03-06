/**This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
package com.civicxpress.projecttaskassignment.controller;

import com.civicxpress.projecttaskassignment.ProjectTaskAssignment;
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
@RequestMapping(value = "/projectTaskAssignment")
public class ProjectTaskAssignmentController {

    @Autowired
    private ProjectTaskAssignment projectTaskAssignment;

    @RequestMapping(value = "/sendAssignedTaskMail", produces = "application/json", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public String sendAssignedTaskMail(@RequestParam(value = "username", required = false) String username, @RequestParam(value = "recipient", required = false) String recipient, @RequestParam(value = "emailSubject", required = false) String emailSubject, @RequestParam(value = "municipalitySignature", required = false) String municipalitySignature, @RequestParam(value = "projectName", required = false) String projectName, @RequestParam(value = "projectLink", required = false) String projectLink) throws MessagingException {
        return projectTaskAssignment.sendAssignedTaskMail(username, recipient, emailSubject, municipalitySignature, projectName, projectLink);
    }
}
