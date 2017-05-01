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

import com.civicxpress.cx2.LetterTemplateToFormStatus;
import com.civicxpress.cx2.service.LetterTemplateToFormStatusService;


/**
 * Controller object for domain model class LetterTemplateToFormStatus.
 * @see LetterTemplateToFormStatus
 */
@RestController("cx2.LetterTemplateToFormStatusController")
@Api(value = "LetterTemplateToFormStatusController", description = "Exposes APIs to work with LetterTemplateToFormStatus resource.")
@RequestMapping("/cx2/LetterTemplateToFormStatus")
public class LetterTemplateToFormStatusController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LetterTemplateToFormStatusController.class);

    @Autowired
	@Qualifier("cx2.LetterTemplateToFormStatusService")
	private LetterTemplateToFormStatusService letterTemplateToFormStatusService;

	@ApiOperation(value = "Creates a new LetterTemplateToFormStatus instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public LetterTemplateToFormStatus createLetterTemplateToFormStatus(@RequestBody LetterTemplateToFormStatus letterTemplateToFormStatus) {
		LOGGER.debug("Create LetterTemplateToFormStatus with information: {}" , letterTemplateToFormStatus);

		letterTemplateToFormStatus = letterTemplateToFormStatusService.create(letterTemplateToFormStatus);
		LOGGER.debug("Created LetterTemplateToFormStatus with information: {}" , letterTemplateToFormStatus);

	    return letterTemplateToFormStatus;
	}


    @ApiOperation(value = "Returns the LetterTemplateToFormStatus instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public LetterTemplateToFormStatus getLetterTemplateToFormStatus(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting LetterTemplateToFormStatus with id: {}" , id);

        LetterTemplateToFormStatus foundLetterTemplateToFormStatus = letterTemplateToFormStatusService.getById(id);
        LOGGER.debug("LetterTemplateToFormStatus details with id: {}" , foundLetterTemplateToFormStatus);

        return foundLetterTemplateToFormStatus;
    }

    @ApiOperation(value = "Updates the LetterTemplateToFormStatus instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public LetterTemplateToFormStatus editLetterTemplateToFormStatus(@PathVariable("id") Integer id, @RequestBody LetterTemplateToFormStatus letterTemplateToFormStatus) throws EntityNotFoundException {
        LOGGER.debug("Editing LetterTemplateToFormStatus with id: {}" , letterTemplateToFormStatus.getId());

        letterTemplateToFormStatus.setId(id);
        letterTemplateToFormStatus = letterTemplateToFormStatusService.update(letterTemplateToFormStatus);
        LOGGER.debug("LetterTemplateToFormStatus details with id: {}" , letterTemplateToFormStatus);

        return letterTemplateToFormStatus;
    }

    @ApiOperation(value = "Deletes the LetterTemplateToFormStatus instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteLetterTemplateToFormStatus(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting LetterTemplateToFormStatus with id: {}" , id);

        LetterTemplateToFormStatus deletedLetterTemplateToFormStatus = letterTemplateToFormStatusService.delete(id);

        return deletedLetterTemplateToFormStatus != null;
    }

    /**
     * @deprecated Use {@link #findLetterTemplateToFormStatuses(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of LetterTemplateToFormStatus instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<LetterTemplateToFormStatus> searchLetterTemplateToFormStatusesByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering LetterTemplateToFormStatuses list");
        return letterTemplateToFormStatusService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of LetterTemplateToFormStatus instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<LetterTemplateToFormStatus> findLetterTemplateToFormStatuses(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering LetterTemplateToFormStatuses list");
        return letterTemplateToFormStatusService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of LetterTemplateToFormStatus instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<LetterTemplateToFormStatus> filterLetterTemplateToFormStatuses(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering LetterTemplateToFormStatuses list");
        return letterTemplateToFormStatusService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportLetterTemplateToFormStatuses(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return letterTemplateToFormStatusService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of LetterTemplateToFormStatus instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countLetterTemplateToFormStatuses( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting LetterTemplateToFormStatuses");
		return letterTemplateToFormStatusService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getLetterTemplateToFormStatusAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return letterTemplateToFormStatusService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service LetterTemplateToFormStatusService instance
	 */
	protected void setLetterTemplateToFormStatusService(LetterTemplateToFormStatusService service) {
		this.letterTemplateToFormStatusService = service;
	}

}

