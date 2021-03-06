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

import com.civicxpress.cx2.FormTypeFields;
import com.civicxpress.cx2.service.FormTypeFieldsService;


/**
 * Controller object for domain model class FormTypeFields.
 * @see FormTypeFields
 */
@RestController("cx2.FormTypeFieldsController")
@Api(value = "FormTypeFieldsController", description = "Exposes APIs to work with FormTypeFields resource.")
@RequestMapping("/cx2/FormTypeFields")
public class FormTypeFieldsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormTypeFieldsController.class);

    @Autowired
	@Qualifier("cx2.FormTypeFieldsService")
	private FormTypeFieldsService formTypeFieldsService;

	@ApiOperation(value = "Creates a new FormTypeFields instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public FormTypeFields createFormTypeFields(@RequestBody FormTypeFields formTypeFields) {
		LOGGER.debug("Create FormTypeFields with information: {}" , formTypeFields);

		formTypeFields = formTypeFieldsService.create(formTypeFields);
		LOGGER.debug("Created FormTypeFields with information: {}" , formTypeFields);

	    return formTypeFields;
	}


    @ApiOperation(value = "Returns the FormTypeFields instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public FormTypeFields getFormTypeFields(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting FormTypeFields with id: {}" , id);

        FormTypeFields foundFormTypeFields = formTypeFieldsService.getById(id);
        LOGGER.debug("FormTypeFields details with id: {}" , foundFormTypeFields);

        return foundFormTypeFields;
    }

    @ApiOperation(value = "Updates the FormTypeFields instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public FormTypeFields editFormTypeFields(@PathVariable("id") Integer id, @RequestBody FormTypeFields formTypeFields) throws EntityNotFoundException {
        LOGGER.debug("Editing FormTypeFields with id: {}" , formTypeFields.getId());

        formTypeFields.setId(id);
        formTypeFields = formTypeFieldsService.update(formTypeFields);
        LOGGER.debug("FormTypeFields details with id: {}" , formTypeFields);

        return formTypeFields;
    }

    @ApiOperation(value = "Deletes the FormTypeFields instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteFormTypeFields(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting FormTypeFields with id: {}" , id);

        FormTypeFields deletedFormTypeFields = formTypeFieldsService.delete(id);

        return deletedFormTypeFields != null;
    }

    /**
     * @deprecated Use {@link #findFormTypeFields(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of FormTypeFields instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormTypeFields> searchFormTypeFieldsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering FormTypeFields list");
        return formTypeFieldsService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of FormTypeFields instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormTypeFields> findFormTypeFields(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering FormTypeFields list");
        return formTypeFieldsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of FormTypeFields instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormTypeFields> filterFormTypeFields(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering FormTypeFields list");
        return formTypeFieldsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportFormTypeFields(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return formTypeFieldsService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of FormTypeFields instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countFormTypeFields( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting FormTypeFields");
		return formTypeFieldsService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getFormTypeFieldsAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return formTypeFieldsService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FormTypeFieldsService instance
	 */
	protected void setFormTypeFieldsService(FormTypeFieldsService service) {
		this.formTypeFieldsService = service;
	}

}

