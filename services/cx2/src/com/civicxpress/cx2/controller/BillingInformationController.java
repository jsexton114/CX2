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

import com.civicxpress.cx2.BillingInformation;
import com.civicxpress.cx2.service.BillingInformationService;


/**
 * Controller object for domain model class BillingInformation.
 * @see BillingInformation
 */
@RestController("cx2.BillingInformationController")
@Api(value = "BillingInformationController", description = "Exposes APIs to work with BillingInformation resource.")
@RequestMapping("/cx2/BillingInformation")
public class BillingInformationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BillingInformationController.class);

    @Autowired
	@Qualifier("cx2.BillingInformationService")
	private BillingInformationService billingInformationService;

	@ApiOperation(value = "Creates a new BillingInformation instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public BillingInformation createBillingInformation(@RequestBody BillingInformation billingInformation) {
		LOGGER.debug("Create BillingInformation with information: {}" , billingInformation);

		billingInformation = billingInformationService.create(billingInformation);
		LOGGER.debug("Created BillingInformation with information: {}" , billingInformation);

	    return billingInformation;
	}


    @ApiOperation(value = "Returns the BillingInformation instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public BillingInformation getBillingInformation(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting BillingInformation with id: {}" , id);

        BillingInformation foundBillingInformation = billingInformationService.getById(id);
        LOGGER.debug("BillingInformation details with id: {}" , foundBillingInformation);

        return foundBillingInformation;
    }

    @ApiOperation(value = "Updates the BillingInformation instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public BillingInformation editBillingInformation(@PathVariable("id") Integer id, @RequestBody BillingInformation billingInformation) throws EntityNotFoundException {
        LOGGER.debug("Editing BillingInformation with id: {}" , billingInformation.getId());

        billingInformation.setId(id);
        billingInformation = billingInformationService.update(billingInformation);
        LOGGER.debug("BillingInformation details with id: {}" , billingInformation);

        return billingInformation;
    }

    @ApiOperation(value = "Deletes the BillingInformation instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteBillingInformation(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting BillingInformation with id: {}" , id);

        BillingInformation deletedBillingInformation = billingInformationService.delete(id);

        return deletedBillingInformation != null;
    }

    /**
     * @deprecated Use {@link #findBillingInformations(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of BillingInformation instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<BillingInformation> searchBillingInformationsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering BillingInformations list");
        return billingInformationService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of BillingInformation instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<BillingInformation> findBillingInformations(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering BillingInformations list");
        return billingInformationService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of BillingInformation instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<BillingInformation> filterBillingInformations(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering BillingInformations list");
        return billingInformationService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportBillingInformations(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return billingInformationService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of BillingInformation instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countBillingInformations( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting BillingInformations");
		return billingInformationService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getBillingInformationAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return billingInformationService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service BillingInformationService instance
	 */
	protected void setBillingInformationService(BillingInformationService service) {
		this.billingInformationService = service;
	}

}

