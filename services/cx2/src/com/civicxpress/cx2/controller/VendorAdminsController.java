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
import com.civicxpress.cx2.VendorAdmins;
import com.civicxpress.cx2.service.VendorAdminsService;

/**
 * Controller object for domain model class VendorAdmins.
 * @see VendorAdmins
 */
@RestController("cx2.VendorAdminsController")
@Api(value = "VendorAdminsController", description = "Exposes APIs to work with VendorAdmins resource.")
@RequestMapping("/cx2/VendorAdmins")
public class VendorAdminsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VendorAdminsController.class);

    @Autowired
    @Qualifier("cx2.VendorAdminsService")
    private VendorAdminsService vendorAdminsService;

    @ApiOperation(value = "Creates a new VendorAdmins instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public VendorAdmins createVendorAdmins(@RequestBody VendorAdmins vendorAdmins) {
        LOGGER.debug("Create VendorAdmins with information: {}", vendorAdmins);
        vendorAdmins = vendorAdminsService.create(vendorAdmins);
        LOGGER.debug("Created VendorAdmins with information: {}", vendorAdmins);
        return vendorAdmins;
    }

    @ApiOperation(value = "Returns the VendorAdmins instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public VendorAdmins getVendorAdmins(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting VendorAdmins with id: {}", id);
        VendorAdmins foundVendorAdmins = vendorAdminsService.getById(id);
        LOGGER.debug("VendorAdmins details with id: {}", foundVendorAdmins);
        return foundVendorAdmins;
    }

    @ApiOperation(value = "Updates the VendorAdmins instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public VendorAdmins editVendorAdmins(@PathVariable("id") Integer id, @RequestBody VendorAdmins vendorAdmins) throws EntityNotFoundException {
        LOGGER.debug("Editing VendorAdmins with id: {}", vendorAdmins.getId());
        vendorAdmins.setId(id);
        vendorAdmins = vendorAdminsService.update(vendorAdmins);
        LOGGER.debug("VendorAdmins details with id: {}", vendorAdmins);
        return vendorAdmins;
    }

    @ApiOperation(value = "Deletes the VendorAdmins instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteVendorAdmins(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting VendorAdmins with id: {}", id);
        VendorAdmins deletedVendorAdmins = vendorAdminsService.delete(id);
        return deletedVendorAdmins != null;
    }

    @RequestMapping(value = "/userId-vendorId", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching VendorAdmins with given unique key values.")
    public VendorAdmins getByUserIdAndVendorId(@RequestParam(name = "userId") Integer userId, @RequestParam(name = "vendorId") Integer vendorId) {
        LOGGER.debug("Getting VendorAdmins with uniques key UserIdAndVendorId");
        return vendorAdminsService.getByUserIdAndVendorId(userId, vendorId);
    }

    /**
     * @deprecated Use {@link #findVendorAdmins(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of VendorAdmins instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<VendorAdmins> searchVendorAdminsByQueryFilters(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering VendorAdmins list");
        return vendorAdminsService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of VendorAdmins instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<VendorAdmins> findVendorAdmins(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering VendorAdmins list");
        return vendorAdminsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportVendorAdmins(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return vendorAdminsService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of VendorAdmins instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countVendorAdmins(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting VendorAdmins");
        return vendorAdminsService.count(query);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service VendorAdminsService instance
	 */
    protected void setVendorAdminsService(VendorAdminsService service) {
        this.vendorAdminsService = service;
    }
}
