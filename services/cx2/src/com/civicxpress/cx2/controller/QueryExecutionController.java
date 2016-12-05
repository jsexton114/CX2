/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Qualifier;
import com.civicxpress.cx2.service.Cx2QueryExecutorService;
import com.wavemaker.runtime.data.model.CustomQuery;
import com.wavemaker.runtime.data.exception.QueryParameterMismatchException;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;

@RestController(value = "Cx2.QueryExecutionController")
@Api(value = "QueryExecutionController", description = "Controller class for query execution")
@RequestMapping("/cx2/queryExecutor")
public class QueryExecutionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryExecutionController.class);

    @Autowired
    private Cx2QueryExecutorService queryService;

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/AdminsMunicipalities", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Object> executeAdminsMunicipalities(@RequestParam(value = "user", required = false) java.lang.Integer user, Pageable pageable) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query AdminsMunicipalities");
        Page<Object> result = queryService.executeAdminsMunicipalities(pageable, user);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/AdminVendorsList", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Object> executeAdminVendorsList(@RequestParam(value = "user", required = false) java.lang.Integer user, Pageable pageable) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query AdminVendorsList");
        Page<Object> result = queryService.executeAdminVendorsList(pageable, user);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/CheckingUserWithMunicipalityInRoles", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Object> executeCheckingUserWithMunicipalityInRoles(@RequestParam(value = "muncipality", required = false) java.lang.Integer muncipality, @RequestParam(value = "user", required = false) java.lang.Integer user, Pageable pageable) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query CheckingUserWithMunicipalityInRoles");
        Page<Object> result = queryService.executeCheckingUserWithMunicipalityInRoles(pageable, muncipality, user);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/CountOfVendors", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Object> executeCountOfVendors(@RequestParam(value = "vendor", required = false) java.lang.Integer vendor, Pageable pageable) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query CountOfVendors");
        Page<Object> result = queryService.executeCountOfVendors(pageable, vendor);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/DeleteCategoryMapping", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public int executeDeleteCategoryMapping(@RequestParam(value = "form", required = false) java.lang.Integer form) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query DeleteCategoryMapping");
        int result = queryService.executeDeleteCategoryMapping(form);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/DeleteExistingSubscriptionsForUser", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public int executeDeleteExistingSubscriptionsForUser(@RequestParam(value = "user", required = false) java.lang.Integer user) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query DeleteExistingSubscriptionsForUser");
        int result = queryService.executeDeleteExistingSubscriptionsForUser(user);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/deleteToken", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public int executeDeleteToken(@RequestParam(value = "token", required = false) java.lang.String token) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query deleteToken");
        int result = queryService.executeDeleteToken(token);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/EmployeesMunicipalities", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Object> executeEmployeesMunicipalities(@RequestParam(value = "user", required = false) java.lang.Integer user, Pageable pageable) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query EmployeesMunicipalities");
        Page<Object> result = queryService.executeEmployeesMunicipalities(pageable, user);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/getEmailId", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Object> executeGetEmailId(@RequestParam(value = "userID", required = false) java.lang.Integer userID, Pageable pageable) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query getEmailId");
        Page<Object> result = queryService.executeGetEmailId(pageable, userID);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/GetFormTypesByCategoriesAndMunicipalities", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Object> executeGetFormTypesByCategoriesAndMunicipalities(@RequestParam(value = "formCategory", required = false) java.lang.Integer formCategory, @RequestParam(value = "municipality", required = false) java.lang.Integer municipality, Pageable pageable) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query GetFormTypesByCategoriesAndMunicipalities");
        Page<Object> result = queryService.executeGetFormTypesByCategoriesAndMunicipalities(pageable, formCategory, municipality);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/GetRolesForMunicipality", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Object> executeGetRolesForMunicipality(@RequestParam(value = "role", required = false) java.lang.String role, @RequestParam(value = "municipality", required = false) java.lang.Integer municipality, Pageable pageable) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query GetRolesForMunicipality");
        Page<Object> result = queryService.executeGetRolesForMunicipality(pageable, role, municipality);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/getUserID", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Object> executeGetUserID(@RequestParam(value = "Email", required = false) java.lang.String Email, Pageable pageable) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query getUserID");
        Page<Object> result = queryService.executeGetUserID(pageable, Email);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/getUserIdFromPasswordResetToken", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Object> executeGetUserIdFromPasswordResetToken(@RequestParam(value = "token", required = false) java.lang.String token, Pageable pageable) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query getUserIdFromPasswordResetToken");
        Page<Object> result = queryService.executeGetUserIdFromPasswordResetToken(pageable, token);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/InsertCategoryMapping", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public int executeInsertCategoryMapping(@RequestParam(value = "FormTypeId", required = false) java.lang.Integer FormTypeId, @RequestParam(value = "FormCategoryId", required = false) java.lang.Integer FormCategoryId) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query InsertCategoryMapping");
        int result = queryService.executeInsertCategoryMapping(FormTypeId, FormCategoryId);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/InsertGroups", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public int executeInsertGroups(@RequestParam(value = "GroupName", required = false) java.lang.String GroupName, @RequestParam(value = "GroupDescription", required = false) java.lang.String GroupDescription, @RequestParam(value = "MunicipalityId", required = false) java.lang.Integer MunicipalityId) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query InsertGroups");
        int result = queryService.executeInsertGroups(GroupName, GroupDescription, MunicipalityId);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/InsertNewRole", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public int executeInsertNewRole(@RequestParam(value = "RoleName", required = false) java.lang.String RoleName, @RequestParam(value = "MunicipalityId", required = false) java.lang.Integer MunicipalityId, @RequestParam(value = "Description", required = false) java.lang.String Description, @RequestParam(value = "UserId", required = false) java.lang.Integer UserId) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query InsertNewRole");
        int result = queryService.executeInsertNewRole(RoleName, MunicipalityId, Description, UserId);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/InsertSubscription", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public int executeInsertSubscription(@RequestParam(value = "UserId", required = false) java.lang.Integer UserId, @RequestParam(value = "MunicipalityId", required = false) java.lang.Integer MunicipalityId, @RequestParam(value = "DateSubscribed", required = false) java.lang.String DateSubscribed) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query InsertSubscription");
        int result = queryService.executeInsertSubscription(UserId, MunicipalityId, DateSubscribed);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/MapAsAdminForVendor", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public int executeMapAsAdminForVendor(@RequestParam(value = "UserId", required = false) java.lang.Integer UserId, @RequestParam(value = "VendorId", required = false) java.lang.Integer VendorId) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query MapAsAdminForVendor");
        int result = queryService.executeMapAsAdminForVendor(UserId, VendorId);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/MunicipalitiesGroupsCounts", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Object> executeMunicipalitiesGroupsCounts(@RequestParam(value = "municipality", required = false) java.lang.Integer municipality, Pageable pageable) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query MunicipalitiesGroupsCounts");
        Page<Object> result = queryService.executeMunicipalitiesGroupsCounts(pageable, municipality);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/MunicipalityCount", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Object> executeMunicipalityCount(Pageable pageable) {
        LOGGER.debug("Executing named query MunicipalityCount");
        Page<Object> result = queryService.executeMunicipalityCount(pageable);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/resetPasswordForUser", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public int executeResetPasswordForUser(@RequestParam(value = "newPassword", required = false) java.lang.String newPassword, @RequestParam(value = "token", required = false) java.lang.String token) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query resetPasswordForUser");
        int result = queryService.executeResetPasswordForUser(newPassword, token);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/resetPasswordWithTokenForUser", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public int executeResetPasswordWithTokenForUser(@RequestParam(value = "userid", required = false) java.lang.Integer userid, @RequestParam(value = "token", required = false) java.lang.String token) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query resetPasswordWithTokenForUser");
        int result = queryService.executeResetPasswordWithTokenForUser(userid, token);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/StandardUserMunicipalites", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Object> executeStandardUserMunicipalites(@RequestParam(value = "USER", required = false) java.lang.Integer USER, Pageable pageable) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query StandardUserMunicipalites");
        Page<Object> result = queryService.executeStandardUserMunicipalites(pageable, USER);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/SubDivisonCount", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Object> executeSubDivisonCount(@RequestParam(value = "municipalityId", required = false) java.lang.Integer municipalityId, Pageable pageable) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query SubDivisonCount");
        Page<Object> result = queryService.executeSubDivisonCount(pageable, municipalityId);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/UpdateAsCXVendorAdmin", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public int executeUpdateAsCXVendorAdmin(@RequestParam(value = "role", required = false) java.lang.String role, @RequestParam(value = "municipality", required = false) java.lang.Integer municipality, @RequestParam(value = "user", required = false) java.lang.Integer user) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query UpdateAsCXVendorAdmin");
        int result = queryService.executeUpdateAsCXVendorAdmin(role, municipality, user);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/UpdateCFInProfile", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public int executeUpdateCFInProfile(@RequestParam(value = "cf", required = false) java.lang.String cf, @RequestParam(value = "user", required = false) java.lang.Integer user) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query UpdateCFInProfile");
        int result = queryService.executeUpdateCFInProfile(cf, user);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/UpdateGlobalEmailSig", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public int executeUpdateGlobalEmailSig(@RequestParam(value = "gs", required = false) java.lang.String gs, @RequestParam(value = "municipality", required = false) java.lang.Integer municipality) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query UpdateGlobalEmailSig");
        int result = queryService.executeUpdateGlobalEmailSig(gs, municipality);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/UpdateInfoFromMyProfile", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public int executeUpdateInfoFromMyProfile(@RequestParam(value = "fn", required = false) java.lang.String fn, @RequestParam(value = "ln", required = false) java.lang.String ln, @RequestParam(value = "em", required = false) java.lang.String em, @RequestParam(value = "ph", required = false) java.lang.String ph, @RequestParam(value = "ad1", required = false) java.lang.String ad1, @RequestParam(value = "ad2", required = false) java.lang.String ad2, @RequestParam(value = "st", required = false) java.lang.Integer st, @RequestParam(value = "ct", required = false) java.lang.String ct, @RequestParam(value = "ctry", required = false) java.lang.String ctry, @RequestParam(value = "pc", required = false) java.lang.String pc, @RequestParam(value = "user", required = false) java.lang.Integer user) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query UpdateInfoFromMyProfile");
        int result = queryService.executeUpdateInfoFromMyProfile(fn, ln, em, ph, ad1, ad2, st, ct, ctry, pc, user);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/UpdateMunicipalityInfo", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public int executeUpdateMunicipalityInfo(@RequestParam(value = "mn", required = false) java.lang.String mn, @RequestParam(value = "em", required = false) java.lang.String em, @RequestParam(value = "ph", required = false) java.lang.String ph, @RequestParam(value = "ad1", required = false) java.lang.String ad1, @RequestParam(value = "ad2", required = false) java.lang.String ad2, @RequestParam(value = "st", required = false) java.lang.Integer st, @RequestParam(value = "ct", required = false) java.lang.String ct, @RequestParam(value = "pc", required = false) java.lang.String pc, @RequestParam(value = "municipality", required = false) java.lang.Integer municipality) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query UpdateMunicipalityInfo");
        int result = queryService.executeUpdateMunicipalityInfo(mn, em, ph, ad1, ad2, st, ct, pc, municipality);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/UpdateNewPassword", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public int executeUpdateNewPassword(@RequestParam(value = "password", required = false) java.lang.String password, @RequestParam(value = "newUser", required = false) java.lang.Integer newUser) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query UpdateNewPassword");
        int result = queryService.executeUpdateNewPassword(password, newUser);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/UpdatePasswordAndCF", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public int executeUpdatePasswordAndCF(@RequestParam(value = "password", required = false) java.lang.String password, @RequestParam(value = "cf", required = false) java.lang.String cf, @RequestParam(value = "newUser", required = false) java.lang.Integer newUser) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query UpdatePasswordAndCF");
        int result = queryService.executeUpdatePasswordAndCF(password, cf, newUser);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/UpdateRoleForMunicipality", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public int executeUpdateRoleForMunicipality(@RequestParam(value = "role", required = false) java.lang.String role, @RequestParam(value = "municipality", required = false) java.lang.Integer municipality, @RequestParam(value = "user", required = false) java.lang.Integer user) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query UpdateRoleForMunicipality");
        int result = queryService.executeUpdateRoleForMunicipality(role, municipality, user);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/UpdateVendorStatus", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public int executeUpdateVendorStatus(@RequestParam(value = "DateApproved", required = false) java.sql.Date DateApproved, @RequestParam(value = "ApprovedBy", required = false) java.lang.String ApprovedBy, @RequestParam(value = "ExpiresDate", required = false) java.sql.Date ExpiresDate, @RequestParam(value = "Active", required = false) java.lang.Boolean Active, @RequestParam(value = "ApprovalStatus", required = false) java.lang.String ApprovalStatus, @RequestParam(value = "Reviewer", required = false) java.lang.String Reviewer, @RequestParam(value = "municipality", required = false) java.lang.Integer municipality, @RequestParam(value = "vendor", required = false) java.lang.Integer vendor) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query UpdateVendorStatus");
        int result = queryService.executeUpdateVendorStatus(DateApproved, ApprovedBy, ExpiresDate, Active, ApprovalStatus, Reviewer, municipality, vendor);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/UpdateWorkMunicipality", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public int executeUpdateWorkMunicipality(@RequestParam(value = "monday", required = false) java.lang.Boolean monday, @RequestParam(value = "tuesday", required = false) java.lang.Boolean tuesday, @RequestParam(value = "wednesday", required = false) java.lang.Boolean wednesday, @RequestParam(value = "thursday", required = false) java.lang.Boolean thursday, @RequestParam(value = "friday", required = false) java.lang.Boolean friday, @RequestParam(value = "saturday", required = false) java.lang.Boolean saturday, @RequestParam(value = "sunday", required = false) java.lang.Boolean sunday, @RequestParam(value = "timezone", required = false) java.lang.String timezone, @RequestParam(value = "openTime", required = false) java.sql.Time openTime, @RequestParam(value = "closeTime", required = false) java.sql.Time closeTime, @RequestParam(value = "municipalityId", required = false) java.lang.Integer municipalityId) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query UpdateWorkMunicipality");
        int result = queryService.executeUpdateWorkMunicipality(monday, tuesday, wednesday, thursday, friday, saturday, sunday, timezone, openTime, closeTime, municipalityId);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/UserCount", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Object> executeUserCount(Pageable pageable) {
        LOGGER.debug("Executing named query UserCount");
        Page<Object> result = queryService.executeUserCount(pageable);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/userSubscriptionsCount", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Object> executeUserSubscriptionsCount(Pageable pageable) {
        LOGGER.debug("Executing named query userSubscriptionsCount");
        Page<Object> result = queryService.executeUserSubscriptionsCount(pageable);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/userSubscriptionsCountForMunicipality", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Object> executeUserSubscriptionsCountForMunicipality(@RequestParam(value = "municipalityId", required = false) java.lang.Integer municipalityId, Pageable pageable) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query userSubscriptionsCountForMunicipality");
        Page<Object> result = queryService.executeUserSubscriptionsCountForMunicipality(pageable, municipalityId);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute queries")
    @RequestMapping(value = "/queries/verifyPasswordResetToken", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Object> executeVerifyPasswordResetToken(@RequestParam(value = "token", required = false) java.lang.String token, Pageable pageable) throws QueryParameterMismatchException {
        LOGGER.debug("Executing named query verifyPasswordResetToken");
        Page<Object> result = queryService.executeVerifyPasswordResetToken(pageable, token);
        LOGGER.debug("got the result of named query {}", result);
        return result;
    }

    @ApiOperation(value = "Process request to execute customer queries")
    @RequestMapping(value = "/queries/wm_custom", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Object> executeWMCustomQuery(@RequestBody CustomQuery query, Pageable pageable) {
        Page<Object> result = queryService.executeWMCustomQuerySelect(query, pageable);
        LOGGER.debug("got the result {}" + result);
        return result;
    }

    @ApiOperation(value = "Process request to execute customer queries")
    @RequestMapping(value = "/queries/wm_custom_update", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public int executeWMCustomQuery(@RequestBody CustomQuery query) {
        int result = queryService.executeWMCustomQueryUpdate(query);
        LOGGER.debug("got the result {}" + result);
        return result;
    }
}
