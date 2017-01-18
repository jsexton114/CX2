/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/

package com.civicxpress.cx2.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.wavemaker.runtime.data.model.CustomQuery;
import com.wavemaker.runtime.data.dao.query.WMQueryExecutor;
import com.wavemaker.runtime.data.exception.QueryParameterMismatchException;

@Service
public class Cx2QueryExecutorServiceImpl implements Cx2QueryExecutorService {
	private static final Logger LOGGER = LoggerFactory.getLogger(Cx2QueryExecutorServiceImpl.class);

	@Autowired
	@Qualifier("cx2WMQueryExecutor")
	private WMQueryExecutor queryExecutor;

	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeAddUsersToVendor( java.lang.Integer VendorId ,java.lang.Integer UserId ,java.sql.Date JoiningDate)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("VendorId", VendorId);
        params.put("UserId", UserId);
        params.put("JoiningDate", JoiningDate);
        return queryExecutor.executeNamedQueryForUpdate("AddUsersToVendor", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeAdminsMunicipalities(Pageable pageable, java.lang.Integer user)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user", user);
        return queryExecutor.executeNamedQuery("AdminsMunicipalities", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeAdminVendorsList(Pageable pageable, java.lang.Integer user)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user", user);
        return queryExecutor.executeNamedQuery("AdminVendorsList", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeBannedDetails(Pageable pageable, java.lang.String emailid)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("emailid", emailid);
        return queryExecutor.executeNamedQuery("bannedDetails", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeCheckingUserWithInVendorUsers(Pageable pageable, java.lang.Integer user, java.lang.Integer vendor)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user", user);
        params.put("vendor", vendor);
        return queryExecutor.executeNamedQuery("CheckingUserWithInVendorUsers", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeCheckingUserWithMunicipalityInRoles(Pageable pageable, java.lang.Integer muncipality, java.lang.Integer user)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("muncipality", muncipality);
        params.put("user", user);
        return queryExecutor.executeNamedQuery("CheckingUserWithMunicipalityInRoles", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeCountOfFormsForMunicipality(Pageable pageable, java.lang.Integer MunicipalityId)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("MunicipalityId", MunicipalityId);
        return queryExecutor.executeNamedQuery("CountOfFormsForMunicipality", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeCountOfUserForms(Pageable pageable, java.lang.Boolean closed, java.lang.Integer user)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("closed", closed);
        params.put("user", user);
        return queryExecutor.executeNamedQuery("CountOfUserForms", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeCountOfUserFormsForMunicipality(Pageable pageable, java.lang.Integer user, java.lang.Boolean closed, java.lang.Integer municipality)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user", user);
        params.put("closed", closed);
        params.put("municipality", municipality);
        return queryExecutor.executeNamedQuery("CountOfUserFormsForMunicipality", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeCountOfVendors(Pageable pageable, java.lang.Integer vendor)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("vendor", vendor);
        return queryExecutor.executeNamedQuery("CountOfVendors", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeCountOfVendorUsers(Pageable pageable, java.lang.Integer vendor)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("vendor", vendor);
        return queryExecutor.executeNamedQuery("CountOfVendorUsers", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeDeleteCategoryMapping( java.lang.Integer form)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("form", form);
        return queryExecutor.executeNamedQueryForUpdate("DeleteCategoryMapping", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeDeleteExistingSubscriptionsForUser( java.lang.Integer user)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user", user);
        return queryExecutor.executeNamedQueryForUpdate("DeleteExistingSubscriptionsForUser", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeDeleteFromVendorAdmins( java.lang.Integer user ,java.lang.Integer vendor)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user", user);
        params.put("vendor", vendor);
        return queryExecutor.executeNamedQueryForUpdate("DeleteFromVendorAdmins", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeDeleteFromVendorUsers( java.lang.Integer user ,java.lang.Integer vendor)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user", user);
        params.put("vendor", vendor);
        return queryExecutor.executeNamedQueryForUpdate("DeleteFromVendorUsers", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeDeleteMunicipalityGroup( java.lang.Integer MunicipalityGroupId ,java.lang.Integer UserId)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("MunicipalityGroupId", MunicipalityGroupId);
        params.put("UserId", UserId);
        return queryExecutor.executeNamedQueryForUpdate("deleteMunicipalityGroup", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeDeleteRoleForMuncipality( java.lang.String role ,java.lang.Integer municipality ,java.lang.Integer user)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("role", role);
        params.put("municipality", municipality);
        params.put("user", user);
        return queryExecutor.executeNamedQueryForUpdate("DeleteRoleForMuncipality", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeDeleteToken( java.lang.String token)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("token", token);
        return queryExecutor.executeNamedQueryForUpdate("deleteToken", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeEmployeesMunicipalities(Pageable pageable, java.lang.Integer user)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user", user);
        return queryExecutor.executeNamedQuery("EmployeesMunicipalities", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeEmployeesOrAdminsMunicipalities(Pageable pageable, java.lang.Integer user, java.lang.String role)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user", user);
        params.put("role", role);
        return queryExecutor.executeNamedQuery("EmployeesOrAdminsMunicipalities", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeGetEmailId(Pageable pageable, java.lang.Integer userID)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userID", userID);
        return queryExecutor.executeNamedQuery("getEmailId", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeGetFormsForMunicpality(Pageable pageable, java.lang.Integer MunicipalityId)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("MunicipalityId", MunicipalityId);
        return queryExecutor.executeNamedQuery("GetFormsForMunicpality", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeGetFormTypesByCategoriesAndMunicipalities(Pageable pageable, java.lang.Integer formCategory, java.lang.Boolean MunicipalityInternalForm)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("formCategory", formCategory);
        params.put("MunicipalityInternalForm", MunicipalityInternalForm);
        return queryExecutor.executeNamedQuery("GetFormTypesByCategoriesAndMunicipalities", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeGetListofGroupName(Pageable pageable, java.util.List<java.lang.Integer> MunicipalityGroupID, java.lang.Integer MunicipalityID)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("MunicipalityGroupID", MunicipalityGroupID);
        params.put("MunicipalityID", MunicipalityID);
        return queryExecutor.executeNamedQuery("getListofGroupName", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeGetListofUsers(Pageable pageable, java.lang.Integer municipalityID, java.lang.String Email)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("municipalityID", municipalityID);
        params.put("Email", Email);
        return queryExecutor.executeNamedQuery("getListofUsers", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeGetMunicipalityGroupIdIDs(Pageable pageable, java.lang.Integer userID)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userID", userID);
        return queryExecutor.executeNamedQuery("getMunicipalityGroupIdIDs", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeGetRolesForMunicipality(Pageable pageable, java.lang.String role, java.lang.Integer municipality)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("role", role);
        params.put("municipality", municipality);
        return queryExecutor.executeNamedQuery("GetRolesForMunicipality", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeGetUserID(Pageable pageable, java.lang.String Email)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("Email", Email);
        return queryExecutor.executeNamedQuery("getUserID", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeGetUserIdFromPasswordResetToken(Pageable pageable, java.lang.String token)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("token", token);
        return queryExecutor.executeNamedQuery("getUserIdFromPasswordResetToken", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeInsertCategoryMapping( java.lang.Integer FormTypeId ,java.lang.Integer FormCategoryId)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("FormTypeId", FormTypeId);
        params.put("FormCategoryId", FormCategoryId);
        return queryExecutor.executeNamedQueryForUpdate("InsertCategoryMapping", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeInsertGroups( java.lang.String GroupName ,java.lang.String GroupDescription ,java.lang.Integer MunicipalityId)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("GroupName", GroupName);
        params.put("GroupDescription", GroupDescription);
        params.put("MunicipalityId", MunicipalityId);
        return queryExecutor.executeNamedQueryForUpdate("InsertGroups", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeInsertNewRole( java.lang.String RoleName ,java.lang.Integer MunicipalityId ,java.lang.String Description ,java.lang.Integer UserId)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("RoleName", RoleName);
        params.put("MunicipalityId", MunicipalityId);
        params.put("Description", Description);
        params.put("UserId", UserId);
        return queryExecutor.executeNamedQueryForUpdate("InsertNewRole", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeInsertSubscription( java.lang.Integer UserId ,java.lang.Integer MunicipalityId ,java.lang.String DateSubscribed)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("UserId", UserId);
        params.put("MunicipalityId", MunicipalityId);
        params.put("DateSubscribed", DateSubscribed);
        return queryExecutor.executeNamedQueryForUpdate("InsertSubscription", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeManualFeeTypeCountForMunicipality(Pageable pageable, java.lang.Integer MunicipalityId)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("MunicipalityId", MunicipalityId);
        return queryExecutor.executeNamedQuery("ManualFeeTypeCountForMunicipality", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeMapAsAdminForVendor( java.lang.Integer UserId ,java.lang.Integer VendorId)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("UserId", UserId);
        params.put("VendorId", VendorId);
        return queryExecutor.executeNamedQueryForUpdate("MapAsAdminForVendor", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeMunicipalitiesGroupsCounts(Pageable pageable, java.lang.Integer municipality)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("municipality", municipality);
        return queryExecutor.executeNamedQuery("MunicipalitiesGroupsCounts", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeMunicipalityCount(Pageable pageable)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        return queryExecutor.executeNamedQuery("MunicipalityCount", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeRecordFormHistory( java.lang.String FormGUID ,java.lang.Integer FormTypeId ,java.lang.Integer NewStatusId ,java.lang.Integer OldStatusId ,java.lang.String Comments ,java.lang.Integer CreatedBy ,java.sql.Timestamp CreatedTime)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("FormGUID", FormGUID);
        params.put("FormTypeId", FormTypeId);
        params.put("NewStatusId", NewStatusId);
        params.put("OldStatusId", OldStatusId);
        params.put("Comments", Comments);
        params.put("CreatedBy", CreatedBy);
        params.put("CreatedTime", CreatedTime);
        return queryExecutor.executeNamedQueryForUpdate("RecordFormHistory", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeResetPasswordForUser( java.lang.String newPassword ,java.lang.String token)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("newPassword", newPassword);
        params.put("token", token);
        return queryExecutor.executeNamedQueryForUpdate("resetPasswordForUser", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeResetPasswordWithTokenForUser( java.lang.Integer userid ,java.lang.String token)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userid", userid);
        params.put("token", token);
        return queryExecutor.executeNamedQueryForUpdate("resetPasswordWithTokenForUser", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeStandardUserMunicipalites(Pageable pageable, java.lang.Integer USER)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("USER", USER);
        return queryExecutor.executeNamedQuery("StandardUserMunicipalites", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeSubDivisonCount(Pageable pageable, java.lang.Integer municipalityId)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("municipalityId", municipalityId);
        return queryExecutor.executeNamedQuery("SubDivisonCount", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeUpdateAsCXVendorAdmin( java.lang.String role ,java.lang.Integer municipality ,java.lang.Integer user)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("role", role);
        params.put("municipality", municipality);
        params.put("user", user);
        return queryExecutor.executeNamedQueryForUpdate("UpdateAsCXVendorAdmin", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeUpdateCFInProfile( java.lang.String cf ,java.lang.Integer user)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cf", cf);
        params.put("user", user);
        return queryExecutor.executeNamedQueryForUpdate("UpdateCFInProfile", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeUpdateDevFormDetailsForCXAdmin( java.lang.String TbLocation ,java.lang.String PageName ,java.lang.String Report ,java.lang.String FormTableName ,java.lang.Integer form)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("TbLocation", TbLocation);
        params.put("PageName", PageName);
        params.put("Report", Report);
        params.put("FormTableName", FormTableName);
        params.put("form", form);
        return queryExecutor.executeNamedQueryForUpdate("UpdateDevFormDetailsForCXAdmin", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeUpdateFormStatusInMasterForms( java.lang.Integer formStatus ,java.lang.String FormGUID)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("formStatus", formStatus);
        params.put("FormGUID", FormGUID);
        return queryExecutor.executeNamedQueryForUpdate("UpdateFormStatusInMasterForms", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeUpdateGlobalEmailSig( java.lang.String gs ,java.lang.Integer municipality)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("gs", gs);
        params.put("municipality", municipality);
        return queryExecutor.executeNamedQueryForUpdate("UpdateGlobalEmailSig", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeUpdateInfoFromMyProfile( java.lang.String fn ,java.lang.String ln ,java.lang.String em ,java.lang.String ph ,java.lang.String ad1 ,java.lang.String ad2 ,java.lang.Integer st ,java.lang.String ct ,java.lang.String ctry ,java.lang.String pc ,java.lang.Integer user)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("fn", fn);
        params.put("ln", ln);
        params.put("em", em);
        params.put("ph", ph);
        params.put("ad1", ad1);
        params.put("ad2", ad2);
        params.put("st", st);
        params.put("ct", ct);
        params.put("ctry", ctry);
        params.put("pc", pc);
        params.put("user", user);
        return queryExecutor.executeNamedQueryForUpdate("UpdateInfoFromMyProfile", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeUpdateMunicipalityInfo( java.lang.String mn ,java.lang.String em ,java.lang.String ph ,java.lang.String ad1 ,java.lang.String ad2 ,java.lang.Integer st ,java.lang.String ct ,java.lang.String pc ,java.lang.Integer municipality)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("mn", mn);
        params.put("em", em);
        params.put("ph", ph);
        params.put("ad1", ad1);
        params.put("ad2", ad2);
        params.put("st", st);
        params.put("ct", ct);
        params.put("pc", pc);
        params.put("municipality", municipality);
        return queryExecutor.executeNamedQueryForUpdate("UpdateMunicipalityInfo", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeUpdateNewPassword( java.lang.String password ,java.lang.Integer newUser)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("password", password);
        params.put("newUser", newUser);
        return queryExecutor.executeNamedQueryForUpdate("UpdateNewPassword", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeUpdatePasswordAndCF( java.lang.String password ,java.lang.String cf ,java.lang.Integer newUser)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("password", password);
        params.put("cf", cf);
        params.put("newUser", newUser);
        return queryExecutor.executeNamedQueryForUpdate("UpdatePasswordAndCF", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeUpdateRoleForMunicipality( java.lang.String role ,java.lang.Integer municipality ,java.lang.Integer user)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("role", role);
        params.put("municipality", municipality);
        params.put("user", user);
        return queryExecutor.executeNamedQueryForUpdate("UpdateRoleForMunicipality", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeUpdateVendorStatus( java.sql.Date DateApproved ,java.lang.String ApprovedBy ,java.sql.Date ExpiresDate ,java.lang.Boolean Active ,java.lang.String ApprovalStatus ,java.lang.String Reviewer ,java.lang.Integer municipality ,java.lang.Integer vendor)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("DateApproved", DateApproved);
        params.put("ApprovedBy", ApprovedBy);
        params.put("ExpiresDate", ExpiresDate);
        params.put("Active", Active);
        params.put("ApprovalStatus", ApprovalStatus);
        params.put("Reviewer", Reviewer);
        params.put("municipality", municipality);
        params.put("vendor", vendor);
        return queryExecutor.executeNamedQueryForUpdate("UpdateVendorStatus", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeUpdateWorkMunicipality( java.lang.Boolean monday ,java.lang.Boolean tuesday ,java.lang.Boolean wednesday ,java.lang.Boolean thursday ,java.lang.Boolean friday ,java.lang.Boolean saturday ,java.lang.Boolean sunday ,java.lang.String timezone ,java.sql.Time openTime ,java.sql.Time closeTime ,java.lang.Integer municipalityId)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("monday", monday);
        params.put("tuesday", tuesday);
        params.put("wednesday", wednesday);
        params.put("thursday", thursday);
        params.put("friday", friday);
        params.put("saturday", saturday);
        params.put("sunday", sunday);
        params.put("timezone", timezone);
        params.put("openTime", openTime);
        params.put("closeTime", closeTime);
        params.put("municipalityId", municipalityId);
        return queryExecutor.executeNamedQueryForUpdate("UpdateWorkMunicipality", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeUserCount(Pageable pageable)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        return queryExecutor.executeNamedQuery("UserCount", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeUserSubscriptionsCount(Pageable pageable)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        return queryExecutor.executeNamedQuery("userSubscriptionsCount", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeUserSubscriptionsCountForMunicipality(Pageable pageable, java.lang.Integer municipalityId)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("municipalityId", municipalityId);
        return queryExecutor.executeNamedQuery("userSubscriptionsCountForMunicipality", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeVendorCount(Pageable pageable)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        return queryExecutor.executeNamedQuery("VendorCount", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeVendorsCountForMunicipalities(Pageable pageable, java.lang.Integer MunicipalityId)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("MunicipalityId", MunicipalityId);
        return queryExecutor.executeNamedQuery("VendorsCountForMunicipalities", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeVerifyPasswordResetToken(Pageable pageable, java.lang.String token)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("token", token);
        return queryExecutor.executeNamedQuery("verifyPasswordResetToken", params, pageable);
	}

	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeWMCustomQuerySelect(CustomQuery query, Pageable pageable) {
	    return queryExecutor.executeCustomQuery(query, pageable);
	}

	@Transactional(value = "cx2TransactionManager")
    @Override
    public int executeWMCustomQueryUpdate(CustomQuery query) {
        return queryExecutor.executeCustomQueryForUpdate(query);
    }
}

