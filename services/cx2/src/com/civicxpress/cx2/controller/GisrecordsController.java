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
import com.civicxpress.cx2.Fees;
import com.civicxpress.cx2.Giscontacts;
import com.civicxpress.cx2.Gisrecords;
import com.civicxpress.cx2.service.GisrecordsService;

/**
 * Controller object for domain model class Gisrecords.
 * @see Gisrecords
 */
@RestController("cx2.GisrecordsController")
@Api(value = "GisrecordsController", description = "Exposes APIs to work with Gisrecords resource.")
@RequestMapping("/cx2/Gisrecords")
public class GisrecordsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GisrecordsController.class);

    @Autowired
    @Qualifier("cx2.GisrecordsService")
    private GisrecordsService gisrecordsService;

    @ApiOperation(value = "Creates a new Gisrecords instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Gisrecords createGisrecords(@RequestBody Gisrecords gisrecords) {
        LOGGER.debug("Create Gisrecords with information: {}", gisrecords);
        gisrecords = gisrecordsService.create(gisrecords);
        LOGGER.debug("Created Gisrecords with information: {}", gisrecords);
        return gisrecords;
    }

    @ApiOperation(value = "Returns the Gisrecords instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Gisrecords getGisrecords(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting Gisrecords with id: {}", id);
        Gisrecords foundGisrecords = gisrecordsService.getById(id);
        LOGGER.debug("Gisrecords details with id: {}", foundGisrecords);
        return foundGisrecords;
    }

    @ApiOperation(value = "Updates the Gisrecords instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Gisrecords editGisrecords(@PathVariable("id") Integer id, @RequestBody Gisrecords gisrecords) throws EntityNotFoundException {
        LOGGER.debug("Editing Gisrecords with id: {}", gisrecords.getId());
        gisrecords.setId(id);
        gisrecords = gisrecordsService.update(gisrecords);
        LOGGER.debug("Gisrecords details with id: {}", gisrecords);
        return gisrecords;
    }

    @ApiOperation(value = "Deletes the Gisrecords instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteGisrecords(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Gisrecords with id: {}", id);
        Gisrecords deletedGisrecords = gisrecordsService.delete(id);
        return deletedGisrecords != null;
    }

    /**
     * @deprecated Use {@link #findGisrecords(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Gisrecords instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Gisrecords> searchGisrecordsByQueryFilters(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Gisrecords list");
        return gisrecordsService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of Gisrecords instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Gisrecords> findGisrecords(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Gisrecords list");
        return gisrecordsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportGisrecords(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return gisrecordsService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of Gisrecords instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countGisrecords(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting Gisrecords");
        return gisrecordsService.count(query);
    }

    @RequestMapping(value = "/{id}/feeses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the feeses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Fees> findAssociatedFeeses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated feeses");
        return gisrecordsService.findAssociatedFeeses(id, pageable);
    }

    @RequestMapping(value = "/{id}/giscontactses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the giscontactses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Giscontacts> findAssociatedGiscontactses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated giscontactses");
        return gisrecordsService.findAssociatedGiscontactses(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service GisrecordsService instance
	 */
    protected void setGisrecordsService(GisrecordsService service) {
        this.gisrecordsService = service;
    }
}
