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

import com.civicxpress.cx2.VendorUsers;
import com.civicxpress.cx2.service.VendorUsersService;


/**
 * Controller object for domain model class VendorUsers.
 * @see VendorUsers
 */
@RestController("cx2.VendorUsersController")
@Api(value = "VendorUsersController", description = "Exposes APIs to work with VendorUsers resource.")
@RequestMapping("/cx2/VendorUsers")
public class VendorUsersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VendorUsersController.class);

    @Autowired
	@Qualifier("cx2.VendorUsersService")
	private VendorUsersService vendorUsersService;

	@ApiOperation(value = "Creates a new VendorUsers instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public VendorUsers createVendorUsers(@RequestBody VendorUsers vendorUsers) {
		LOGGER.debug("Create VendorUsers with information: {}" , vendorUsers);

		vendorUsers = vendorUsersService.create(vendorUsers);
		LOGGER.debug("Created VendorUsers with information: {}" , vendorUsers);

	    return vendorUsers;
	}


    @ApiOperation(value = "Returns the VendorUsers instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public VendorUsers getVendorUsers(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting VendorUsers with id: {}" , id);

        VendorUsers foundVendorUsers = vendorUsersService.getById(id);
        LOGGER.debug("VendorUsers details with id: {}" , foundVendorUsers);

        return foundVendorUsers;
    }

    @ApiOperation(value = "Updates the VendorUsers instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public VendorUsers editVendorUsers(@PathVariable("id") Integer id, @RequestBody VendorUsers vendorUsers) throws EntityNotFoundException {
        LOGGER.debug("Editing VendorUsers with id: {}" , vendorUsers.getId());

        vendorUsers.setId(id);
        vendorUsers = vendorUsersService.update(vendorUsers);
        LOGGER.debug("VendorUsers details with id: {}" , vendorUsers);

        return vendorUsers;
    }

    @ApiOperation(value = "Deletes the VendorUsers instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteVendorUsers(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting VendorUsers with id: {}" , id);

        VendorUsers deletedVendorUsers = vendorUsersService.delete(id);

        return deletedVendorUsers != null;
    }

    @RequestMapping(value = "/vendorId-userId", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching VendorUsers with given unique key values.")
    public VendorUsers getByVendorIdAndUserId(@RequestParam("vendorId") Integer vendorId, @RequestParam("userId") Integer userId) {
        LOGGER.debug("Getting VendorUsers with uniques key VendorIdAndUserId");
        return vendorUsersService.getByVendorIdAndUserId(vendorId, userId);
    }

    /**
     * @deprecated Use {@link #findVendorUsers(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of VendorUsers instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<VendorUsers> searchVendorUsersByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering VendorUsers list");
        return vendorUsersService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of VendorUsers instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<VendorUsers> findVendorUsers(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering VendorUsers list");
        return vendorUsersService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of VendorUsers instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<VendorUsers> filterVendorUsers(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering VendorUsers list");
        return vendorUsersService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportVendorUsers(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return vendorUsersService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of VendorUsers instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countVendorUsers( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting VendorUsers");
		return vendorUsersService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getVendorUsersAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return vendorUsersService.getAggregatedValues(aggregationInfo, pageable);
    }


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service VendorUsersService instance
	 */
	protected void setVendorUsersService(VendorUsersService service) {
		this.vendorUsersService = service;
	}

}

