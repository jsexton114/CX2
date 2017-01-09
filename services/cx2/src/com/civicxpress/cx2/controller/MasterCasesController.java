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

import com.civicxpress.cx2.MasterCases;
import com.civicxpress.cx2.service.MasterCasesService;


/**
 * Controller object for domain model class MasterCases.
 * @see MasterCases
 */
@RestController("cx2.MasterCasesController")
@Api(value = "MasterCasesController", description = "Exposes APIs to work with MasterCases resource.")
@RequestMapping("/cx2/MasterCases")
public class MasterCasesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MasterCasesController.class);

    @Autowired
	@Qualifier("cx2.MasterCasesService")
	private MasterCasesService masterCasesService;

	@ApiOperation(value = "Creates a new MasterCases instance.")
	@RequestMapping(method = RequestMethod.POST)
        @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public MasterCases createMasterCases(@RequestBody MasterCases masterCases) {
		LOGGER.debug("Create MasterCases with information: {}" , masterCases);

		masterCases = masterCasesService.create(masterCases);
		LOGGER.debug("Created MasterCases with information: {}" , masterCases);

	    return masterCases;
	}


    @ApiOperation(value = "Returns the MasterCases instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public MasterCases getMasterCases(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting MasterCases with id: {}" , id);

        MasterCases foundMasterCases = masterCasesService.getById(id);
        LOGGER.debug("MasterCases details with id: {}" , foundMasterCases);

        return foundMasterCases;
    }

    @ApiOperation(value = "Updates the MasterCases instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public MasterCases editMasterCases(@PathVariable("id") Integer id, @RequestBody MasterCases masterCases) throws EntityNotFoundException {
        LOGGER.debug("Editing MasterCases with id: {}" , masterCases.getId());

        masterCases.setId(id);
        masterCases = masterCasesService.update(masterCases);
        LOGGER.debug("MasterCases details with id: {}" , masterCases);

        return masterCases;
    }

    @ApiOperation(value = "Deletes the MasterCases instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteMasterCases(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting MasterCases with id: {}" , id);

        MasterCases deletedMasterCases = masterCasesService.delete(id);

        return deletedMasterCases != null;
    }

    /**
     * @deprecated Use {@link #findMasterCases(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of MasterCases instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MasterCases> searchMasterCasesByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering MasterCases list");
        return masterCasesService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of MasterCases instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MasterCases> findMasterCases(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering MasterCases list");
        return masterCasesService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportMasterCases(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return masterCasesService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of MasterCases instances.")
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	@WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countMasterCases( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting MasterCases");
		return masterCasesService.count(query);
	}


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service MasterCasesService instance
	 */
	protected void setMasterCasesService(MasterCasesService service) {
		this.masterCasesService = service;
	}

}

