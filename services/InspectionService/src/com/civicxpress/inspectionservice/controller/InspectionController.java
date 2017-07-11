/**This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
package com.civicxpress.inspectionservice.controller;

import com.civicxpress.inspectionservice.InspectionService;
import java.lang.String;
import java.lang.Long;
import org.springframework.web.multipart.MultipartFile;
import java.sql.SQLException;
import java.io.IOException;
import java.util.Date;
import javax.mail.MessagingException;
import java.lang.Object;
import java.util.Map;
import java.lang.Integer;
import java.lang.Boolean;
import java.util.HashMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;

@RestController
@RequestMapping(value = "/inspection")
public class InspectionController {

    @Autowired
    private InspectionService inspectionService;

    @RequestMapping(value = "/violation", method = RequestMethod.POST, consumes = "multipart/form-data")
    public void addViolation(@RequestParam(value = "inspectionGuid", required = false) String inspectionGuid, @RequestParam(value = "codeId", required = false) Long codeId, @RequestParam(value = "notes", required = false) String notes, @RequestPart(value = "pictures") MultipartFile[] pictures) throws SQLException, IOException {
        inspectionService.addViolation(inspectionGuid, codeId, notes, pictures);
    }

    @RequestMapping(value = "/assignInspector", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public void assignInspector(@RequestParam(value = "inspectorId", required = false) Long inspectorId, @RequestParam(value = "inspectionGuid", required = false) String inspectionGuid, @RequestParam(value = "dateAssigned", required = false) Date dateAssigned, @RequestParam(value = "assignedByManager", required = false) String assignedByManager, @RequestParam(value = "inspectionLink", required = false) String inspectionLink) throws SQLException, MessagingException {
        inspectionService.assignInspector(inspectorId, inspectionGuid, dateAssigned, assignedByManager, inspectionLink);
    }

    @RequestMapping(value = "/dynamicFielddata", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public Map<String, Object> getDynamicFieldData(@RequestParam(value = "inspectionGuid", required = false) String inspectionGuid) throws SQLException {
        return inspectionService.getDynamicFieldData(inspectionGuid);
    }

    @RequestMapping(value = "/saveDynamicField", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public void saveDynamicField(@RequestParam(value = "inspectionDesignId", required = false) Long inspectionDesignId, @RequestParam(value = "label", required = false) String label, @RequestParam(value = "fieldTypeId", required = false) Long fieldTypeId, @RequestParam(value = "displayOrder", required = false) Integer displayOrder, @RequestParam(value = "required", required = false) Boolean required, @RequestParam(value = "defaultValue", required = false) String defaultValue, @RequestParam(value = "helpText", required = false) String helpText, @RequestParam(value = "possibleValues", required = false) String possibleValues) throws SQLException {
        inspectionService.saveDynamicField(inspectionDesignId, label, fieldTypeId, displayOrder, required, defaultValue, helpText, possibleValues);
    }

    @RequestMapping(value = "/saveDynamicFieldData", method = RequestMethod.POST)
    public void saveDynamicFieldData(@RequestParam(value = "inspectionGuid", required = false) String inspectionGuid, @RequestBody HashMap<String, Object> fieldData) throws SQLException {
        inspectionService.saveDynamicFieldData(inspectionGuid, fieldData);
    }

    @RequestMapping(value = "/saveInspectionDesign", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public Long saveInspectionDesign(@RequestParam(value = "municipalityId", required = false) Long municipalityId, @RequestParam(value = "inspectionName", required = false) String inspectionName, @RequestParam(value = "description", required = false) String description) throws SQLException {
        return inspectionService.saveInspectionDesign(municipalityId, inspectionName, description);
    }

    @RequestMapping(value = "/scheduleInspection", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public void scheduleInspection(@RequestParam(value = "formGuid", required = false) String formGuid, @RequestParam(value = "inspectionDesignId", required = false) Long inspectionDesignId, @RequestParam(value = "requestedFor", required = false) Date requestedFor, @RequestParam(value = "targetLink", required = false) String targetLink) throws SQLException, MessagingException {
        inspectionService.scheduleInspection(formGuid, inspectionDesignId, requestedFor, targetLink);
    }

    @RequestMapping(value = "/sendLetter", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public void sendLetter(@RequestParam(value = "inspectionGuid", required = false) String inspectionGuid, @RequestParam(value = "letterTemplateId", required = false) Integer letterTemplateId, @RequestParam(value = "formLink", required = false) String formLink) throws SQLException, MessagingException {
        inspectionService.sendLetter(inspectionGuid, letterTemplateId, formLink);
    }

    @RequestMapping(value = "/inspectionOutcome", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public void setInspectionOutcome(@RequestParam(value = "inspectionGuid", required = false) String inspectionGuid, @RequestParam(value = "inspectionOutcomeId", required = false) Long inspectionOutcomeId, @RequestParam(value = "comments", required = false) String comments, @RequestParam(value = "formLink", required = false) String formLink) throws SQLException, MessagingException {
        inspectionService.setInspectionOutcome(inspectionGuid, inspectionOutcomeId, comments, formLink);
    }

    @RequestMapping(value = "/uploadDocuments", method = RequestMethod.POST, consumes = "multipart/form-data")
    public void uploadDocuments(@RequestPart(value = "files") MultipartFile[] files, @RequestParam(value = "inspectionGuid", required = false) String inspectionGuid) throws SQLException, IOException {
        inspectionService.uploadDocuments(files, inspectionGuid);
    }
}
