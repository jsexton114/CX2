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

import com.civicxpress.cx2.Gisrecords;
import com.civicxpress.cx2.Subdivisions;
import com.civicxpress.cx2.service.SubdivisionsService;


/**
 * Controller object for domain model class Subdivisions.
 * @see Subdivisions
 */
@RestController("cx2.SubdivisionsController")
@Api(value = "SubdivisionsController", description = "Exposes APIs to work with Subdivisions resource.")
@RequestMapping("/cx2/Subdivisions")
public class SubdivisionsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubdivisionsController.class);

    @Autowired
	@Qualifier("cx2.SubdivisionsService")
	private SubdivisionsService subdivisionsService;

	@ApiOperation(value = "Creates a new Subdivisions instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Subdivisions createSubdivisions(@RequestBody Subdivisions subdivisions) {
		LOGGER.debug("Create Subdivisions with information: {}" , subdivisions);

		subdivisions = subdivisionsService.create(subdivisions);
		LOGGER.debug("Created Subdivisions with information: {}" , subdivisions);

	    return subdivisions;
	}


    @ApiOperation(value = "Returns the Subdivisions instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Subdivisions getSubdivisions(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting Subdivisions with id: {}" , id);

        Subdivisions foundSubdivisions = subdivisionsService.getById(id);
        LOGGER.debug("Subdivisions details with id: {}" , foundSubdivisions);

        return foundSubdivisions;
    }

    @ApiOperation(value = "Updates the Subdivisions instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Subdivisions editSubdivisions(@PathVariable("id") Integer id, @RequestBody Subdivisions subdivisions) throws EntityNotFoundException {
        LOGGER.debug("Editing Subdivisions with id: {}" , subdivisions.getId());

        subdivisions.setId(id);
        subdivisions = subdivisionsService.update(subdivisions);
        LOGGER.debug("Subdivisions details with id: {}" , subdivisions);

        return subdivisions;
    }

    @ApiOperation(value = "Deletes the Subdivisions instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteSubdivisions(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Subdivisions with id: {}" , id);

        Subdivisions deletedSubdivisions = subdivisionsService.delete(id);

        return deletedSubdivisions != null;
    }

    /**
     * @deprecated Use {@link #findSubdivisions(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Subdivisions instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Subdivisions> searchSubdivisionsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Subdivisions list");
        return subdivisionsService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Subdivisions instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Subdivisions> findSubdivisions(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Subdivisions list");
        return subdivisionsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Subdivisions instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Subdivisions> filterSubdivisions(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Subdivisions list");
        return subdivisionsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportSubdivisions(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return subdivisionsService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of Subdivisions instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countSubdivisions( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Subdivisions");
		return subdivisionsService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getSubdivisionsAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return subdivisionsService.getAggregatedValues(aggregationInfo, pageable);
    }

    @RequestMapping(value="/{id:.+}/gisrecordses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the gisrecordses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Gisrecords> findAssociatedGisrecordses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated gisrecordses");
        return subdivisionsService.findAssociatedGisrecordses(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service SubdivisionsService instance
	 */
	protected void setSubdivisionsService(SubdivisionsService service) {
		this.subdivisionsService = service;
	}

}

