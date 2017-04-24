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

import com.civicxpress.cx2.FormToInspectionCategoryMapping;
import com.civicxpress.cx2.InspectionCategories;
import com.civicxpress.cx2.InspectionCategoryMapping;
import com.civicxpress.cx2.MasterInspections;
import com.civicxpress.cx2.service.InspectionCategoriesService;


/**
 * Controller object for domain model class InspectionCategories.
 * @see InspectionCategories
 */
@RestController("cx2.InspectionCategoriesController")
@Api(value = "InspectionCategoriesController", description = "Exposes APIs to work with InspectionCategories resource.")
@RequestMapping("/cx2/InspectionCategories")
public class InspectionCategoriesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InspectionCategoriesController.class);

    @Autowired
	@Qualifier("cx2.InspectionCategoriesService")
	private InspectionCategoriesService inspectionCategoriesService;

	@ApiOperation(value = "Creates a new InspectionCategories instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public InspectionCategories createInspectionCategories(@RequestBody InspectionCategories inspectionCategories) {
		LOGGER.debug("Create InspectionCategories with information: {}" , inspectionCategories);

		inspectionCategories = inspectionCategoriesService.create(inspectionCategories);
		LOGGER.debug("Created InspectionCategories with information: {}" , inspectionCategories);

	    return inspectionCategories;
	}


    @ApiOperation(value = "Returns the InspectionCategories instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public InspectionCategories getInspectionCategories(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting InspectionCategories with id: {}" , id);

        InspectionCategories foundInspectionCategories = inspectionCategoriesService.getById(id);
        LOGGER.debug("InspectionCategories details with id: {}" , foundInspectionCategories);

        return foundInspectionCategories;
    }

    @ApiOperation(value = "Updates the InspectionCategories instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public InspectionCategories editInspectionCategories(@PathVariable("id") Integer id, @RequestBody InspectionCategories inspectionCategories) throws EntityNotFoundException {
        LOGGER.debug("Editing InspectionCategories with id: {}" , inspectionCategories.getId());

        inspectionCategories.setId(id);
        inspectionCategories = inspectionCategoriesService.update(inspectionCategories);
        LOGGER.debug("InspectionCategories details with id: {}" , inspectionCategories);

        return inspectionCategories;
    }

    @ApiOperation(value = "Deletes the InspectionCategories instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteInspectionCategories(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting InspectionCategories with id: {}" , id);

        InspectionCategories deletedInspectionCategories = inspectionCategoriesService.delete(id);

        return deletedInspectionCategories != null;
    }

    /**
     * @deprecated Use {@link #findInspectionCategories(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of InspectionCategories instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<InspectionCategories> searchInspectionCategoriesByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering InspectionCategories list");
        return inspectionCategoriesService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of InspectionCategories instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<InspectionCategories> findInspectionCategories(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering InspectionCategories list");
        return inspectionCategoriesService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of InspectionCategories instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<InspectionCategories> filterInspectionCategories(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering InspectionCategories list");
        return inspectionCategoriesService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportInspectionCategories(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return inspectionCategoriesService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of InspectionCategories instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countInspectionCategories( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting InspectionCategories");
		return inspectionCategoriesService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getInspectionCategoriesAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return inspectionCategoriesService.getAggregatedValues(aggregationInfo, pageable);
    }

    @RequestMapping(value="/{id:.+}/formToInspectionCategoryMappings", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the formToInspectionCategoryMappings instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormToInspectionCategoryMapping> findAssociatedFormToInspectionCategoryMappings(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated formToInspectionCategoryMappings");
        return inspectionCategoriesService.findAssociatedFormToInspectionCategoryMappings(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/inspectionCategoryMappings", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the inspectionCategoryMappings instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<InspectionCategoryMapping> findAssociatedInspectionCategoryMappings(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated inspectionCategoryMappings");
        return inspectionCategoriesService.findAssociatedInspectionCategoryMappings(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/masterInspectionses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the masterInspectionses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MasterInspections> findAssociatedMasterInspectionses(@PathVariable("id") Integer id, Pageable pageable) {

        LOGGER.debug("Fetching all associated masterInspectionses");
        return inspectionCategoriesService.findAssociatedMasterInspectionses(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service InspectionCategoriesService instance
	 */
	protected void setInspectionCategoriesService(InspectionCategoriesService service) {
		this.inspectionCategoriesService = service;
	}

}

