/**This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
package com.civicxpress.formservice.controller;

import com.civicxpress.formservice.FormService;
import java.lang.Long;
import java.sql.SQLException;
import java.lang.String;
import java.lang.Object;
import java.util.Map;
import java.util.HashMap;
import java.lang.Integer;
import java.lang.Boolean;
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

    @RequestMapping(value = "/form", produces = "application/json", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public String createForm(@RequestParam(value = "municipalityId", required = false) Long municipalityId, @RequestParam(value = "formTypeId", required = false) Long formTypeId) throws SQLException {
        return formService.createForm(municipalityId, formTypeId);
    }

    @RequestMapping(value = "/formData", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public Map<String, Object> getFormData(@RequestParam(value = "formTypeId", required = false) Long formTypeId, @RequestParam(value = "formGuid", required = false) String formGuid) throws SQLException {
        return formService.getFormData(formTypeId, formGuid);
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

    @RequestMapping(value = "/submitForm", produces = "application/json", method = RequestMethod.POST)
    public String submitForm(@RequestParam(value = "formGuid", required = false) String formGuid, @RequestBody HashMap<String, Object> fieldData) throws SQLException {
        return formService.submitForm(formGuid, fieldData);
    }
}
