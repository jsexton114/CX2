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
import com.civicxpress.cx2.McnewElectricConnection;
import com.civicxpress.cx2.service.McnewElectricConnectionService;

/**
 * Controller object for domain model class McnewElectricConnection.
 * @see McnewElectricConnection
 */
@RestController("cx2.McnewElectricConnectionController")
@Api(value = "McnewElectricConnectionController", description = "Exposes APIs to work with McnewElectricConnection resource.")
@RequestMapping("/cx2/McnewElectricConnection")
public class McnewElectricConnectionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(McnewElectricConnectionController.class);

    @Autowired
    @Qualifier("cx2.McnewElectricConnectionService")
    private McnewElectricConnectionService mcnewElectricConnectionService;

    @ApiOperation(value = "Creates a new McnewElectricConnection instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public McnewElectricConnection createMcnewElectricConnection(@RequestBody McnewElectricConnection mcnewElectricConnection) {
        LOGGER.debug("Create McnewElectricConnection with information: {}", mcnewElectricConnection);
        mcnewElectricConnection = mcnewElectricConnectionService.create(mcnewElectricConnection);
        LOGGER.debug("Created McnewElectricConnection with information: {}", mcnewElectricConnection);
        return mcnewElectricConnection;
    }

    @ApiOperation(value = "Returns the McnewElectricConnection instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public McnewElectricConnection getMcnewElectricConnection(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting McnewElectricConnection with id: {}", id);
        McnewElectricConnection foundMcnewElectricConnection = mcnewElectricConnectionService.getById(id);
        LOGGER.debug("McnewElectricConnection details with id: {}", foundMcnewElectricConnection);
        return foundMcnewElectricConnection;
    }

    @ApiOperation(value = "Updates the McnewElectricConnection instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public McnewElectricConnection editMcnewElectricConnection(@PathVariable("id") Integer id, @RequestBody McnewElectricConnection mcnewElectricConnection) throws EntityNotFoundException {
        LOGGER.debug("Editing McnewElectricConnection with id: {}", mcnewElectricConnection.getId());
        mcnewElectricConnection.setId(id);
        mcnewElectricConnection = mcnewElectricConnectionService.update(mcnewElectricConnection);
        LOGGER.debug("McnewElectricConnection details with id: {}", mcnewElectricConnection);
        return mcnewElectricConnection;
    }

    @ApiOperation(value = "Deletes the McnewElectricConnection instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteMcnewElectricConnection(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting McnewElectricConnection with id: {}", id);
        McnewElectricConnection deletedMcnewElectricConnection = mcnewElectricConnectionService.delete(id);
        return deletedMcnewElectricConnection != null;
    }

    /**
     * @deprecated Use {@link #findMcnewElectricConnections(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of McnewElectricConnection instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<McnewElectricConnection> findMcnewElectricConnections(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering McnewElectricConnections list");
        return mcnewElectricConnectionService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of McnewElectricConnection instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<McnewElectricConnection> findMcnewElectricConnections(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering McnewElectricConnections list");
        return mcnewElectricConnectionService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportMcnewElectricConnections(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return mcnewElectricConnectionService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of McnewElectricConnection instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countMcnewElectricConnections(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting McnewElectricConnections");
        return mcnewElectricConnectionService.count(query);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service McnewElectricConnectionService instance
	 */
    protected void setMcnewElectricConnectionService(McnewElectricConnectionService service) {
        this.mcnewElectricConnectionService = service;
    }
}
