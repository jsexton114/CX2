/**This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
package com.civicxpress.formservice.controller;

import com.civicxpress.formservice.FormService;
import java.lang.Long;
import java.sql.SQLException;
import com.wavemaker.runtime.file.model.DownloadResponse;
import java.lang.Integer;
import java.lang.String;
import java.lang.Exception;
import org.apache.http.HttpEntity;
import java.io.IOException;
import java.lang.Object;
import java.util.Map;
import com.civicxpress.formservice.FormService.UserPermissionsPojo;
import java.util.HashMap;
import java.lang.Boolean;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;

@RestController
@RequestMapping(value = "/form")
public class FormController {

    @Autowired
    private FormService formService;

    @RequestMapping(value = "/downloadDocument", produces = "application/octet-stream", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public DownloadResponse downloadDocument(@RequestParam(value = "documentId", required = false) Long documentId) throws SQLException {
        return formService.downloadDocument(documentId);
    }

    @RequestMapping(value = "/document", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public HttpEntity editDocument(@RequestParam(value = "documentId", required = false) Long documentId, @RequestParam(value = "resolution", required = false) Integer resolution, @RequestParam(value = "options", required = false) String options) throws Exception {
        return formService.editDocument(documentId, resolution, options);
    }

    @RequestMapping(value = "/documentSignatureLink", produces = "application/json", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public String getDocumentSignatureLink(@RequestParam(value = "formGuid", required = false) String formGuid) throws IOException, SQLException {
        return formService.getDocumentSignatureLink(formGuid);
    }

    @RequestMapping(value = "/formData", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public Map<String, Object> getFormData(@RequestParam(value = "formGuid", required = false) String formGuid) throws SQLException {
        return formService.getFormData(formGuid);
    }

    @RequestMapping(value = "/userPermissions", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public UserPermissionsPojo getUserPermissions(@RequestParam(value = "formGuid", required = false) String formGuid) throws SQLException {
        return formService.getUserPermissions(formGuid);
    }

    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public void main(@RequestBody String[] args) {
        formService.main(args);
    }

    @RequestMapping(value = "/saveFormData", method = RequestMethod.POST)
    public void saveFormData(@RequestParam(value = "formTypeId", required = false) Long formTypeId, @RequestParam(value = "formGuid", required = false) String formGuid, @RequestBody HashMap<String, Object> fieldData) throws SQLException {
        formService.saveFormData(formTypeId, formGuid, fieldData);
    }

    @RequestMapping(value = "/saveFormType", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public Long saveFormType(@RequestParam(value = "municipalityId", required = false) Long municipalityId, @RequestParam(value = "formType", required = false) String formType) throws SQLException {
        return formService.saveFormType(municipalityId, formType);
    }

    @RequestMapping(value = "/saveFormTypeField", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public void saveFormTypeField(@RequestParam(value = "formTypeId", required = false) Long formTypeId, @RequestParam(value = "label", required = false) String label, @RequestParam(value = "fieldTypeId", required = false) Long fieldTypeId, @RequestParam(value = "displayOrder", required = false) Integer displayOrder, @RequestParam(value = "required", required = false) Boolean required, @RequestParam(value = "defaultValue", required = false) String defaultValue, @RequestParam(value = "helpText", required = false) String helpText, @RequestParam(value = "possibleValues", required = false) String possibleValues) throws SQLException {
        formService.saveFormTypeField(formTypeId, label, fieldTypeId, displayOrder, required, defaultValue, helpText, possibleValues);
    }

    @RequestMapping(value = "/formStatus", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public void setFormStatus(@RequestParam(value = "formGuid", required = false) String formGuid, @RequestParam(value = "formStatusId", required = false) Long formStatusId, @RequestParam(value = "comments", required = false) String comments) throws SQLException {
        formService.setFormStatus(formGuid, formStatusId, comments);
    }

    @RequestMapping(value = "/submitForm", produces = "application/json", method = RequestMethod.POST, consumes = "multipart/form-data")
    public String submitForm(@RequestParam(value = "formTypeId", required = false) Long formTypeId, @RequestParam(value = "behalfOfUserId", required = false) Long behalfOfUserId, @RequestParam(value = "ownerId", required = false) Long ownerId, @RequestParam(value = "locationIds", required = false) String locationIds, @RequestParam(value = "vendorIds", required = false) String vendorIds, @RequestParam(value = "primaryVendorId", required = false) Long primaryVendorId, @RequestParam(value = "usersWithWhomToShare", required = false) String usersWithWhomToShare, @RequestParam(value = "fieldDataJsonString", required = false) String fieldDataJsonString, @RequestPart(value = "attachments") MultipartFile[] attachments) throws Exception {
        return formService.submitForm(formTypeId, behalfOfUserId, ownerId, locationIds, vendorIds, primaryVendorId, usersWithWhomToShare, fieldDataJsonString, attachments);
    }

    @RequestMapping(value = "/formTypefield", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public void updateFormTypeField(@RequestParam(value = "formTypeFieldId", required = false) Long formTypeFieldId, @RequestParam(value = "label", required = false) String label, @RequestParam(value = "displayOrder", required = false) Integer displayOrder, @RequestParam(value = "required", required = false) Boolean required, @RequestParam(value = "defaultValue", required = false) String defaultValue, @RequestParam(value = "helpText", required = false) String helpText, @RequestParam(value = "possibleValues", required = false) String possibleValues) throws SQLException {
        formService.updateFormTypeField(formTypeFieldId, label, displayOrder, required, defaultValue, helpText, possibleValues);
    }

    @RequestMapping(value = "/uploadDocuments", method = RequestMethod.POST, consumes = "multipart/form-data")
    public void uploadDocuments(@RequestPart(value = "files") MultipartFile[] files, @RequestParam(value = "formGuid", required = false) String formGuid) throws SQLException {
        formService.uploadDocuments(files, formGuid);
    }
}
