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

import com.civicxpress.cx2.Vendors2form;
import com.civicxpress.cx2.service.Vendors2formService;


/**
 * Controller object for domain model class Vendors2form.
 * @see Vendors2form
 */
@RestController("cx2.Vendors2formController")
@Api(value = "Vendors2formController", description = "Exposes APIs to work with Vendors2form resource.")
@RequestMapping("/cx2/Vendors2form")
public class Vendors2formController {

    private static final Logger LOGGER = LoggerFactory.getLogger(Vendors2formController.class);

    @Autowired
	@Qualifier("cx2.Vendors2formService")
	private Vendors2formService vendors2formService;

	@ApiOperation(value = "Creates a new Vendors2form instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Vendors2form createVendors2form(@RequestBody Vendors2form vendors2form) {
		LOGGER.debug("Create Vendors2form with information: {}" , vendors2form);

		vendors2form = vendors2formService.create(vendors2form);
		LOGGER.debug("Created Vendors2form with information: {}" , vendors2form);

	    return vendors2form;
	}


    @ApiOperation(value = "Returns the Vendors2form instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Vendors2form getVendors2form(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting Vendors2form with id: {}" , id);

        Vendors2form foundVendors2form = vendors2formService.getById(id);
        LOGGER.debug("Vendors2form details with id: {}" , foundVendors2form);

        return foundVendors2form;
    }

    @ApiOperation(value = "Updates the Vendors2form instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Vendors2form editVendors2form(@PathVariable("id") Integer id, @RequestBody Vendors2form vendors2form) throws EntityNotFoundException {
        LOGGER.debug("Editing Vendors2form with id: {}" , vendors2form.getId());

        vendors2form.setId(id);
        vendors2form = vendors2formService.update(vendors2form);
        LOGGER.debug("Vendors2form details with id: {}" , vendors2form);

        return vendors2form;
    }

    @ApiOperation(value = "Deletes the Vendors2form instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteVendors2form(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Vendors2form with id: {}" , id);

        Vendors2form deletedVendors2form = vendors2formService.delete(id);

        return deletedVendors2form != null;
    }

    /**
     * @deprecated Use {@link #findVendors2forms(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Vendors2form instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Vendors2form> searchVendors2formsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Vendors2forms list");
        return vendors2formService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Vendors2form instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Vendors2form> findVendors2forms(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Vendors2forms list");
        return vendors2formService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Vendors2form instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Vendors2form> filterVendors2forms(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Vendors2forms list");
        return vendors2formService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportVendors2forms(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return vendors2formService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of Vendors2form instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countVendors2forms( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Vendors2forms");
		return vendors2formService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getVendors2formAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return vendors2formService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service Vendors2formService instance
	 */
	protected void setVendors2formService(Vendors2formService service) {
		this.vendors2formService = service;
	}

}

