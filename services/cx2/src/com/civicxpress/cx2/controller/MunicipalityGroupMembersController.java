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

import com.civicxpress.cx2.MunicipalityGroupMembers;
import com.civicxpress.cx2.service.MunicipalityGroupMembersService;


/**
 * Controller object for domain model class MunicipalityGroupMembers.
 * @see MunicipalityGroupMembers
 */
@RestController("cx2.MunicipalityGroupMembersController")
@Api(value = "MunicipalityGroupMembersController", description = "Exposes APIs to work with MunicipalityGroupMembers resource.")
@RequestMapping("/cx2/MunicipalityGroupMembers")
public class MunicipalityGroupMembersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MunicipalityGroupMembersController.class);

    @Autowired
	@Qualifier("cx2.MunicipalityGroupMembersService")
	private MunicipalityGroupMembersService municipalityGroupMembersService;

	@ApiOperation(value = "Creates a new MunicipalityGroupMembers instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public MunicipalityGroupMembers createMunicipalityGroupMembers(@RequestBody MunicipalityGroupMembers municipalityGroupMembers) {
		LOGGER.debug("Create MunicipalityGroupMembers with information: {}" , municipalityGroupMembers);

		municipalityGroupMembers = municipalityGroupMembersService.create(municipalityGroupMembers);
		LOGGER.debug("Created MunicipalityGroupMembers with information: {}" , municipalityGroupMembers);

	    return municipalityGroupMembers;
	}


    @ApiOperation(value = "Returns the MunicipalityGroupMembers instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public MunicipalityGroupMembers getMunicipalityGroupMembers(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting MunicipalityGroupMembers with id: {}" , id);

        MunicipalityGroupMembers foundMunicipalityGroupMembers = municipalityGroupMembersService.getById(id);
        LOGGER.debug("MunicipalityGroupMembers details with id: {}" , foundMunicipalityGroupMembers);

        return foundMunicipalityGroupMembers;
    }

    @ApiOperation(value = "Updates the MunicipalityGroupMembers instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public MunicipalityGroupMembers editMunicipalityGroupMembers(@PathVariable("id") Integer id, @RequestBody MunicipalityGroupMembers municipalityGroupMembers) throws EntityNotFoundException {
        LOGGER.debug("Editing MunicipalityGroupMembers with id: {}" , municipalityGroupMembers.getId());

        municipalityGroupMembers.setId(id);
        municipalityGroupMembers = municipalityGroupMembersService.update(municipalityGroupMembers);
        LOGGER.debug("MunicipalityGroupMembers details with id: {}" , municipalityGroupMembers);

        return municipalityGroupMembers;
    }

    @ApiOperation(value = "Deletes the MunicipalityGroupMembers instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteMunicipalityGroupMembers(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting MunicipalityGroupMembers with id: {}" , id);

        MunicipalityGroupMembers deletedMunicipalityGroupMembers = municipalityGroupMembersService.delete(id);

        return deletedMunicipalityGroupMembers != null;
    }

    @RequestMapping(value = "/municipalityGroupId-userId", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching MunicipalityGroupMembers with given unique key values.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public MunicipalityGroupMembers getByMunicipalityGroupIdAndUserId(@RequestParam("municipalityGroupId") Integer municipalityGroupId, @RequestParam("userId") Integer userId) {
        LOGGER.debug("Getting MunicipalityGroupMembers with uniques key MunicipalityGroupIdAndUserId");
        return municipalityGroupMembersService.getByMunicipalityGroupIdAndUserId(municipalityGroupId, userId);
    }

    /**
     * @deprecated Use {@link #findMunicipalityGroupMembers(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of MunicipalityGroupMembers instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MunicipalityGroupMembers> searchMunicipalityGroupMembersByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering MunicipalityGroupMembers list");
        return municipalityGroupMembersService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of MunicipalityGroupMembers instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MunicipalityGroupMembers> findMunicipalityGroupMembers(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering MunicipalityGroupMembers list");
        return municipalityGroupMembersService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of MunicipalityGroupMembers instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MunicipalityGroupMembers> filterMunicipalityGroupMembers(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering MunicipalityGroupMembers list");
        return municipalityGroupMembersService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportMunicipalityGroupMembers(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return municipalityGroupMembersService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of MunicipalityGroupMembers instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countMunicipalityGroupMembers( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting MunicipalityGroupMembers");
		return municipalityGroupMembersService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getMunicipalityGroupMembersAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return municipalityGroupMembersService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service MunicipalityGroupMembersService instance
	 */
	protected void setMunicipalityGroupMembersService(MunicipalityGroupMembersService service) {
		this.municipalityGroupMembersService = service;
	}

}

