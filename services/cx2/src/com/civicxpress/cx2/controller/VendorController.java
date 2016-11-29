/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.TypeMismatchException;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.DownloadResponse;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.runtime.util.WMMultipartUtils;
import com.wavemaker.runtime.util.WMRuntimeUtils;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.civicxpress.cx2.Vendor;
import com.civicxpress.cx2.service.VendorService;

/**
 * Controller object for domain model class Vendor.
 * @see Vendor
 */
@RestController("cx2.VendorController")
@Api(value = "VendorController", description = "Exposes APIs to work with Vendor resource.")
@RequestMapping("/cx2/Vendor")
public class VendorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VendorController.class);

    @Autowired
    @Qualifier("cx2.VendorService")
    private VendorService vendorService;

    @ApiOperation(value = "Creates a new Vendor instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Vendor createVendor(@RequestBody Vendor vendor) {
        LOGGER.debug("Create Vendor with information: {}", vendor);
        vendor = vendorService.create(vendor);
        LOGGER.debug("Created Vendor with information: {}", vendor);
        return vendor;
    }

    @ApiOperation(value = "Creates a new Vendor instance.This API should be used when the Vendor instance has fields that requires multipart data.")
    @RequestMapping(method = RequestMethod.POST, consumes = { "multipart/form-data" })
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Vendor createVendor(MultipartHttpServletRequest multipartHttpServletRequest) {
        Vendor vendor = WMMultipartUtils.toObject(multipartHttpServletRequest, Vendor.class, "cx2");
        LOGGER.debug("Creating a new Vendor with information: {}", vendor);
        return vendorService.create(vendor);
    }

    @ApiOperation(value = "Returns the Vendor instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Vendor getVendor(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting Vendor with id: {}", id);
        Vendor foundVendor = vendorService.getById(id);
        LOGGER.debug("Vendor details with id: {}", foundVendor);
        return foundVendor;
    }

    @ApiOperation(value = "Retrieves content for the given BLOB field in Vendor instance")
    @RequestMapping(value = "/{id}/content/{fieldName}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public DownloadResponse getVendorBLOBContent(@PathVariable("id") Integer id, @PathVariable("fieldName") String fieldName, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestParam(value = "download", defaultValue = "false") boolean download) {
        LOGGER.debug("Retrieves content for the given BLOB field {} in Vendor instance", fieldName);
        if (!WMRuntimeUtils.isLob(Vendor.class, fieldName)) {
            throw new TypeMismatchException("Given field " + fieldName + " is not a valid BLOB type");
        }
        Vendor vendor = vendorService.getById(id);
        return WMMultipartUtils.buildDownloadResponseForBlob(vendor, fieldName, httpServletRequest, download);
    }

    @ApiOperation(value = "Updates the Vendor instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Vendor editVendor(@PathVariable("id") Integer id, @RequestBody Vendor vendor) throws EntityNotFoundException {
        LOGGER.debug("Editing Vendor with id: {}", vendor.getId());
        vendor.setId(id);
        vendor = vendorService.update(vendor);
        LOGGER.debug("Vendor details with id: {}", vendor);
        return vendor;
    }

    @ApiOperation(value = "Updates the Vendor instance associated with the given id.This API should be used when Vendor instance fields that require multipart data.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Vendor editVendor(@PathVariable("id") Integer id, MultipartHttpServletRequest multipartHttpServletRequest) throws EntityNotFoundException {
        Vendor newVendor = WMMultipartUtils.toObject(multipartHttpServletRequest, Vendor.class, "cx2");
        newVendor.setId(id);
        Vendor oldVendor = vendorService.getById(id);
        WMMultipartUtils.updateLobsContent(oldVendor, newVendor);
        LOGGER.debug("Updating Vendor with information: {}", newVendor);
        return vendorService.update(newVendor);
    }

    @ApiOperation(value = "Deletes the Vendor instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteVendor(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Vendor with id: {}", id);
        Vendor deletedVendor = vendorService.delete(id);
        return deletedVendor != null;
    }

    /**
     * @deprecated Use {@link #findVendors(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Vendor instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Vendor> findVendors(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Vendors list");
        return vendorService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of Vendor instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Vendor> findVendors(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Vendors list");
        return vendorService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportVendors(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return vendorService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of Vendor instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countVendors(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting Vendors");
        return vendorService.count(query);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service VendorService instance
	 */
    protected void setVendorService(VendorService service) {
        this.vendorService = service;
    }
}
