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
import com.civicxpress.cx2.Fees;
import com.civicxpress.cx2.FormHistory;
import com.civicxpress.cx2.FormMessages;
import com.civicxpress.cx2.Gis2forms;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.MunicipalityGroupMembers;
import com.civicxpress.cx2.Roles;
import com.civicxpress.cx2.SharedWith;
import com.civicxpress.cx2.UserPasswordResetTokens;
import com.civicxpress.cx2.UserSubscriptions;
import com.civicxpress.cx2.Users;
import com.civicxpress.cx2.VendorAdmins;
import com.civicxpress.cx2.VendorUsers;
import com.civicxpress.cx2.service.UsersService;

/**
 * Controller object for domain model class Users.
 * @see Users
 */
@RestController("cx2.UsersController")
@Api(value = "UsersController", description = "Exposes APIs to work with Users resource.")
@RequestMapping("/cx2/Users")
public class UsersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    @Qualifier("cx2.UsersService")
    private UsersService usersService;

    @ApiOperation(value = "Creates a new Users instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Users createUsers(@RequestBody Users users) {
        LOGGER.debug("Create Users with information: {}", users);
        users = usersService.create(users);
        LOGGER.debug("Created Users with information: {}", users);
        return users;
    }

    @ApiOperation(value = "Creates a new Users instance.This API should be used when the Users instance has fields that requires multipart data.")
    @RequestMapping(method = RequestMethod.POST, consumes = { "multipart/form-data" })
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Users createUsers(MultipartHttpServletRequest multipartHttpServletRequest) {
        Users users = WMMultipartUtils.toObject(multipartHttpServletRequest, Users.class, "cx2");
        LOGGER.debug("Creating a new Users with information: {}", users);
        return usersService.create(users);
    }

    @ApiOperation(value = "Returns the Users instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Users getUsers(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting Users with id: {}", id);
        Users foundUsers = usersService.getById(id);
        LOGGER.debug("Users details with id: {}", foundUsers);
        return foundUsers;
    }

    @ApiOperation(value = "Retrieves content for the given BLOB field in Users instance")
    @RequestMapping(value = "/{id}/content/{fieldName}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public DownloadResponse getUsersBLOBContent(@PathVariable("id") Integer id, @PathVariable("fieldName") String fieldName, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestParam(value = "download", defaultValue = "false") boolean download) {
        LOGGER.debug("Retrieves content for the given BLOB field {} in Users instance", fieldName);
        if (!WMRuntimeUtils.isLob(Users.class, fieldName)) {
            throw new TypeMismatchException("Given field " + fieldName + " is not a valid BLOB type");
        }
        Users users = usersService.getById(id);
        return WMMultipartUtils.buildDownloadResponseForBlob(users, fieldName, httpServletRequest, download);
    }

    @ApiOperation(value = "Updates the Users instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Users editUsers(@PathVariable("id") Integer id, @RequestBody Users users) throws EntityNotFoundException {
        LOGGER.debug("Editing Users with id: {}", users.getId());
        users.setId(id);
        users = usersService.update(users);
        LOGGER.debug("Users details with id: {}", users);
        return users;
    }

    @ApiOperation(value = "Updates the Users instance associated with the given id.This API should be used when Users instance fields that require multipart data.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Users editUsers(@PathVariable("id") Integer id, MultipartHttpServletRequest multipartHttpServletRequest) throws EntityNotFoundException {
        Users newUsers = WMMultipartUtils.toObject(multipartHttpServletRequest, Users.class, "cx2");
        newUsers.setId(id);
        Users oldUsers = usersService.getById(id);
        WMMultipartUtils.updateLobsContent(oldUsers, newUsers);
        LOGGER.debug("Updating Users with information: {}", newUsers);
        return usersService.update(newUsers);
    }

    @ApiOperation(value = "Deletes the Users instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteUsers(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Users with id: {}", id);
        Users deletedUsers = usersService.delete(id);
        return deletedUsers != null;
    }

    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the matching Users with given unique key values.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Users getByEmail(@PathVariable("email") String email) {
        LOGGER.debug("Getting Users with uniques key Email");
        return usersService.getByEmail(email);
    }

    /**
     * @deprecated Use {@link #findUsers(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Users instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Users> searchUsersByQueryFilters(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Users list");
        return usersService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of Users instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Users> findUsers(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Users list");
        return usersService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportUsers(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return usersService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of Users instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countUsers(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting Users");
        return usersService.count(query);
    }

    @RequestMapping(value = "/{id}/feeses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the feeses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Fees> findAssociatedFeeses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated feeses");
        return usersService.findAssociatedFeeses(id, pageable);
    }

    @RequestMapping(value = "/{id}/formHistories", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the formHistories instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormHistory> findAssociatedFormHistories(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated formHistories");
        return usersService.findAssociatedFormHistories(id, pageable);
    }

    @RequestMapping(value = "/{id:.+}/formMessageses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the formMessageses instance associated with the given id.")
    public Page<FormMessages> findAssociatedFormMessageses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated formMessageses");
        return usersService.findAssociatedFormMessageses(id, pageable);
    }

    @RequestMapping(value = "/{id}/gis2formses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the gis2formses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Gis2forms> findAssociatedGis2formses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated gis2formses");
        return usersService.findAssociatedGis2formses(id, pageable);
    }

    @RequestMapping(value = "/{id}/masterFormses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the masterFormses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MasterForms> findAssociatedMasterFormses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated masterFormses");
        return usersService.findAssociatedMasterFormses(id, pageable);
    }

    @RequestMapping(value = "/{id}/municipalityGroupMemberses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the municipalityGroupMemberses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MunicipalityGroupMembers> findAssociatedMunicipalityGroupMemberses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated municipalityGroupMemberses");
        return usersService.findAssociatedMunicipalityGroupMemberses(id, pageable);
    }

    @RequestMapping(value = "/{id}/roleses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the roleses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Roles> findAssociatedRoleses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated roleses");
        return usersService.findAssociatedRoleses(id, pageable);
    }

    @RequestMapping(value = "/{id}/sharedWithsForCreatedBy", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the sharedWithsForCreatedBy instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<SharedWith> findAssociatedSharedWithsForCreatedBy(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated sharedWithsForCreatedBy");
        return usersService.findAssociatedSharedWithsForCreatedBy(id, pageable);
    }

    @RequestMapping(value = "/{id}/sharedWithsForSharedWithUser", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the sharedWithsForSharedWithUser instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<SharedWith> findAssociatedSharedWithsForSharedWithUser(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated sharedWithsForSharedWithUser");
        return usersService.findAssociatedSharedWithsForSharedWithUser(id, pageable);
    }

    @RequestMapping(value = "/{id}/userPasswordResetTokenses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the userPasswordResetTokenses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<UserPasswordResetTokens> findAssociatedUserPasswordResetTokenses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated userPasswordResetTokenses");
        return usersService.findAssociatedUserPasswordResetTokenses(id, pageable);
    }

    @RequestMapping(value = "/{id}/userSubscriptionses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the userSubscriptionses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<UserSubscriptions> findAssociatedUserSubscriptionses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated userSubscriptionses");
        return usersService.findAssociatedUserSubscriptionses(id, pageable);
    }

    @RequestMapping(value = "/{id}/vendorAdminses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the vendorAdminses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<VendorAdmins> findAssociatedVendorAdminses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated vendorAdminses");
        return usersService.findAssociatedVendorAdminses(id, pageable);
    }

    @RequestMapping(value = "/{id}/vendorUserses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the vendorUserses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<VendorUsers> findAssociatedVendorUserses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated vendorUserses");
        return usersService.findAssociatedVendorUserses(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service UsersService instance
	 */
    protected void setUsersService(UsersService service) {
        this.usersService = service;
    }
}
