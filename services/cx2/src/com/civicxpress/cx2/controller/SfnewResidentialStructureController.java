/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.civicxpress.cx2.FormStatuses;
import com.civicxpress.cx2.SfnewResidentialStructure;
import com.civicxpress.cx2.service.SfnewResidentialStructureService;

/**
 * Controller object for domain model class SfnewResidentialStructure.
 * @see SfnewResidentialStructure
 */
@RestController("cx2.SfnewResidentialStructureController")
@Api(value = "SfnewResidentialStructureController", description = "Exposes APIs to work with SfnewResidentialStructure resource.")
@RequestMapping("/cx2/SfnewResidentialStructure")
public class SfnewResidentialStructureController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SfnewResidentialStructureController.class);

    @Autowired
    @Qualifier("cx2.SfnewResidentialStructureService")
    private SfnewResidentialStructureService sfnewResidentialStructureService;

    @ApiOperation(value = "Creates a new SfnewResidentialStructure instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public SfnewResidentialStructure createSfnewResidentialStructure(@RequestBody SfnewResidentialStructure sfnewResidentialStructure) {
        LOGGER.debug("Create SfnewResidentialStructure with information: {}", sfnewResidentialStructure);
        sfnewResidentialStructure = sfnewResidentialStructureService.create(sfnewResidentialStructure);
        LOGGER.debug("Created SfnewResidentialStructure with information: {}", sfnewResidentialStructure);
        return sfnewResidentialStructure;
    }

    @ApiOperation(value = "Returns the SfnewResidentialStructure instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public SfnewResidentialStructure getSfnewResidentialStructure(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting SfnewResidentialStructure with id: {}", id);
        SfnewResidentialStructure foundSfnewResidentialStructure = sfnewResidentialStructureService.getById(id);
        LOGGER.debug("SfnewResidentialStructure details with id: {}", foundSfnewResidentialStructure);
        return foundSfnewResidentialStructure;
    }

    @ApiOperation(value = "Updates the SfnewResidentialStructure instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public SfnewResidentialStructure editSfnewResidentialStructure(@PathVariable("id") Integer id, @RequestBody SfnewResidentialStructure sfnewResidentialStructure) throws EntityNotFoundException {
        LOGGER.debug("Editing SfnewResidentialStructure with id: {}", sfnewResidentialStructure.getId());
        sfnewResidentialStructure.setId(id);
        sfnewResidentialStructure = sfnewResidentialStructureService.update(sfnewResidentialStructure);
        LOGGER.debug("SfnewResidentialStructure details with id: {}", sfnewResidentialStructure);
        return sfnewResidentialStructure;
    }

    @ApiOperation(value = "Deletes the SfnewResidentialStructure instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteSfnewResidentialStructure(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting SfnewResidentialStructure with id: {}", id);
        SfnewResidentialStructure deletedSfnewResidentialStructure = sfnewResidentialStructureService.delete(id);
        return deletedSfnewResidentialStructure != null;
    }

    /**
     * @deprecated Use {@link #findSfnewResidentialStructures(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of SfnewResidentialStructure instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<SfnewResidentialStructure> findSfnewResidentialStructures(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering SfnewResidentialStructures list");
        return sfnewResidentialStructureService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of SfnewResidentialStructure instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<SfnewResidentialStructure> findSfnewResidentialStructures(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering SfnewResidentialStructures list");
        return sfnewResidentialStructureService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportSfnewResidentialStructures(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return sfnewResidentialStructureService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of SfnewResidentialStructure instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countSfnewResidentialStructures(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting SfnewResidentialStructures");
        return sfnewResidentialStructureService.count(query);
    }

    @RequestMapping(value = "/{id}/formStatuseses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the formStatuseses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormStatuses> findAssociatedFormStatuseses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated formStatuseses");
        return sfnewResidentialStructureService.findAssociatedFormStatuseses(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service SfnewResidentialStructureService instance
	 */
    protected void setSfnewResidentialStructureService(SfnewResidentialStructureService service) {
        this.sfnewResidentialStructureService = service;
    }
}
