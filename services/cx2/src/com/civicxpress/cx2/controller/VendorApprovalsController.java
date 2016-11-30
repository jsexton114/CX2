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
import com.civicxpress.cx2.VendorApprovals;
import com.civicxpress.cx2.service.VendorApprovalsService;

/**
 * Controller object for domain model class VendorApprovals.
 * @see VendorApprovals
 */
@RestController("cx2.VendorApprovalsController")
@Api(value = "VendorApprovalsController", description = "Exposes APIs to work with VendorApprovals resource.")
@RequestMapping("/cx2/VendorApprovals")
public class VendorApprovalsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VendorApprovalsController.class);

    @Autowired
    @Qualifier("cx2.VendorApprovalsService")
    private VendorApprovalsService vendorApprovalsService;

    @ApiOperation(value = "Creates a new VendorApprovals instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public VendorApprovals createVendorApprovals(@RequestBody VendorApprovals vendorApprovals) {
        LOGGER.debug("Create VendorApprovals with information: {}", vendorApprovals);
        vendorApprovals = vendorApprovalsService.create(vendorApprovals);
        LOGGER.debug("Created VendorApprovals with information: {}", vendorApprovals);
        return vendorApprovals;
    }

    @ApiOperation(value = "Returns the VendorApprovals instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public VendorApprovals getVendorApprovals(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting VendorApprovals with id: {}", id);
        VendorApprovals foundVendorApprovals = vendorApprovalsService.getById(id);
        LOGGER.debug("VendorApprovals details with id: {}", foundVendorApprovals);
        return foundVendorApprovals;
    }

    @ApiOperation(value = "Updates the VendorApprovals instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public VendorApprovals editVendorApprovals(@PathVariable("id") Integer id, @RequestBody VendorApprovals vendorApprovals) throws EntityNotFoundException {
        LOGGER.debug("Editing VendorApprovals with id: {}", vendorApprovals.getId());
        vendorApprovals.setId(id);
        vendorApprovals = vendorApprovalsService.update(vendorApprovals);
        LOGGER.debug("VendorApprovals details with id: {}", vendorApprovals);
        return vendorApprovals;
    }

    @ApiOperation(value = "Deletes the VendorApprovals instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteVendorApprovals(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting VendorApprovals with id: {}", id);
        VendorApprovals deletedVendorApprovals = vendorApprovalsService.delete(id);
        return deletedVendorApprovals != null;
    }

    /**
     * @deprecated Use {@link #findVendorApprovals(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of VendorApprovals instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<VendorApprovals> findVendorApprovals(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering VendorApprovals list");
        return vendorApprovalsService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of VendorApprovals instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<VendorApprovals> findVendorApprovals(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering VendorApprovals list");
        return vendorApprovalsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportVendorApprovals(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return vendorApprovalsService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of VendorApprovals instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countVendorApprovals(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting VendorApprovals");
        return vendorApprovalsService.count(query);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service VendorApprovalsService instance
	 */
    protected void setVendorApprovalsService(VendorApprovalsService service) {
        this.vendorApprovalsService = service;
    }
}
