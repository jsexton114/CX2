/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.util.Map;

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
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.civicxpress.cx2.Gis2forms;
import com.civicxpress.cx2.service.Gis2formsService;


/**
 * Controller object for domain model class Gis2forms.
 * @see Gis2forms
 */
@RestController("cx2.Gis2formsController")
@Api(value = "Gis2formsController", description = "Exposes APIs to work with Gis2forms resource.")
@RequestMapping("/cx2/Gis2forms")
public class Gis2formsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(Gis2formsController.class);

    @Autowired
	@Qualifier("cx2.Gis2formsService")
	private Gis2formsService gis2formsService;

	@ApiOperation(value = "Creates a new Gis2forms instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Gis2forms createGis2forms(@RequestBody Gis2forms gis2forms) {
		LOGGER.debug("Create Gis2forms with information: {}" , gis2forms);

		gis2forms = gis2formsService.create(gis2forms);
		LOGGER.debug("Created Gis2forms with information: {}" , gis2forms);

	    return gis2forms;
	}


    @ApiOperation(value = "Returns the Gis2forms instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Gis2forms getGis2forms(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting Gis2forms with id: {}" , id);

        Gis2forms foundGis2forms = gis2formsService.getById(id);
        LOGGER.debug("Gis2forms details with id: {}" , foundGis2forms);

        return foundGis2forms;
    }

    @ApiOperation(value = "Updates the Gis2forms instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Gis2forms editGis2forms(@PathVariable("id") Integer id, @RequestBody Gis2forms gis2forms) throws EntityNotFoundException {
        LOGGER.debug("Editing Gis2forms with id: {}" , gis2forms.getId());

        gis2forms.setId(id);
        gis2forms = gis2formsService.update(gis2forms);
        LOGGER.debug("Gis2forms details with id: {}" , gis2forms);

        return gis2forms;
    }

    @ApiOperation(value = "Deletes the Gis2forms instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteGis2forms(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Gis2forms with id: {}" , id);

        Gis2forms deletedGis2forms = gis2formsService.delete(id);

        return deletedGis2forms != null;
    }

    /**
     * @deprecated Use {@link #findGis2forms(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Gis2forms instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Gis2forms> searchGis2formsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Gis2forms list");
        return gis2formsService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Gis2forms instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Gis2forms> findGis2forms(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Gis2forms list");
        return gis2formsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Gis2forms instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Gis2forms> filterGis2forms(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Gis2forms list");
        return gis2formsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportGis2forms(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return gis2formsService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of Gis2forms instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countGis2forms( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Gis2forms");
		return gis2formsService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getGis2formsAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return gis2formsService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service Gis2formsService instance
	 */
	protected void setGis2formsService(Gis2formsService service) {
		this.gis2formsService = service;
	}

}

