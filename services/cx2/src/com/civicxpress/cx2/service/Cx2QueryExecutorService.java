/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/

package com.civicxpress.cx2.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.model.CustomQuery;
import com.wavemaker.runtime.data.exception.QueryParameterMismatchException;

public interface Cx2QueryExecutorService {
	int executeAddGIStoForms(  java.lang.Integer GISRecordId , java.lang.String RelatedFormGUID , java.lang.Integer AddedBy , java.sql.Timestamp AddedTime) throws QueryParameterMismatchException;
	int executeAddGIStoProjects(  java.lang.Integer GISRecordId , java.lang.String RelatedProjectGUID , java.lang.Integer AddedByUser , java.sql.Timestamp AddedAt) throws QueryParameterMismatchException;
	int executeAddingVendorsToForm(  java.lang.String RelatedFormGUID , java.sql.Timestamp SharedOn , java.lang.Integer VendorId) throws QueryParameterMismatchException;
	int executeAddMemeberToProject(  java.lang.String RelatedProjectGUID , java.sql.Timestamp ProjectSharedOn , java.lang.Integer ProjectSharedWith , java.lang.Integer ProjectSharedBy) throws QueryParameterMismatchException;
	int executeAddUsersToVendor(  java.lang.Integer VendorId , java.lang.Integer UserId , java.sql.Date JoiningDate) throws QueryParameterMismatchException;
    Page<Object> executeAdminsMunicipalities(Pageable pageable, java.lang.Integer user) throws QueryParameterMismatchException;
    Page<Object> executeAdminVendorsList(Pageable pageable, java.lang.Integer user) throws QueryParameterMismatchException;
    Page<Object> executeBannedDetails(Pageable pageable, java.lang.String emailid) throws QueryParameterMismatchException;
    Page<Object> executeCheckingUserWithInVendorUsers(Pageable pageable, java.lang.Integer user, java.lang.Integer vendor) throws QueryParameterMismatchException;
    Page<Object> executeCheckingUserWithMunicipalityInRoles(Pageable pageable, java.lang.Integer muncipality, java.lang.Integer user) throws QueryParameterMismatchException;
    Page<Object> executeCompanyFormsByVendorId(Pageable pageable, java.lang.Boolean closed, java.lang.Integer vendorId) throws QueryParameterMismatchException;
    Page<Object> executeCountOfCompnayFormsByVendorId(Pageable pageable, java.lang.Boolean closed, java.lang.Integer vendorId) throws QueryParameterMismatchException;
    Page<Object> executeCountOfFormsForMunicipality(Pageable pageable, java.lang.Integer MunicipalityId) throws QueryParameterMismatchException;
    Page<Object> executeCountOfMuncipalityApplicationsByVendor(Pageable pageable, java.lang.Integer VendorId) throws QueryParameterMismatchException;
    Page<Object> executeCountOfProcessFormsByMuncipality(Pageable pageable, java.lang.Integer UserId, java.lang.Integer MunicipalityId, java.lang.Boolean closed) throws QueryParameterMismatchException;
    Page<Object> executeCountOfUserForms(Pageable pageable, java.lang.Boolean closed, java.lang.Integer creatorUser, java.lang.Integer sharedWithUser) throws QueryParameterMismatchException;
    Page<Object> executeCountOfUserFormsForMunicipality(Pageable pageable, java.lang.Integer MunicipalityId, java.lang.Boolean closed, java.lang.Integer creatorUser, java.lang.Integer sharedWithUser) throws QueryParameterMismatchException;
    Page<Object> executeCountOfVendors(Pageable pageable, java.lang.Integer vendor) throws QueryParameterMismatchException;
    Page<Object> executeCountOfVendorUsers(Pageable pageable, java.lang.Integer vendor) throws QueryParameterMismatchException;
	int executeDeleteCategoryMapping(  java.lang.Integer form) throws QueryParameterMismatchException;
	int executeDeleteExistingSubscriptionsForUser(  java.lang.Integer user) throws QueryParameterMismatchException;
	int executeDeleteFromVendorAdmins(  java.lang.Integer user , java.lang.Integer vendor) throws QueryParameterMismatchException;
	int executeDeleteFromVendorUsers(  java.lang.Integer user , java.lang.Integer vendor) throws QueryParameterMismatchException;
	int executeDeleteMunicipalityGroup(  java.lang.Integer MunicipalityGroupId , java.lang.Integer UserId) throws QueryParameterMismatchException;
	int executeDeleteRoleForMuncipality(  java.lang.String role , java.lang.Integer municipality , java.lang.Integer user) throws QueryParameterMismatchException;
	int executeDeleteToken(  java.lang.String token) throws QueryParameterMismatchException;
    Page<Object> executeEmployeesMunicipalities(Pageable pageable, java.lang.Integer user) throws QueryParameterMismatchException;
    Page<Object> executeEmployeesOrAdminsMunicipalities(Pageable pageable, java.lang.Integer user, java.lang.String role) throws QueryParameterMismatchException;
    Page<Object> executeFormsByCategory(Pageable pageable, java.lang.Integer formCategory, java.lang.Boolean isActive) throws QueryParameterMismatchException;
    Page<Object> executeFormsCountForMunicipalities(Pageable pageable, java.lang.Integer MunicipalityId) throws QueryParameterMismatchException;
    Page<Object> executeFormsForUsersAndShared(Pageable pageable, java.lang.Boolean closed, java.lang.Integer creatorUser, java.lang.Integer sharedWithUser) throws QueryParameterMismatchException;
    Page<Object> executeFormsTaggedWithGISRecords(Pageable pageable, java.lang.Integer gisrecordId) throws QueryParameterMismatchException;
    Page<Object> executeGetEmailId(Pageable pageable, java.lang.Integer userID) throws QueryParameterMismatchException;
    Page<Object> executeGetFormsForMunicpality(Pageable pageable, java.lang.Integer MunicipalityId) throws QueryParameterMismatchException;
    Page<Object> executeGetFormTypeFieldsByTypeId(Pageable pageable, java.lang.Long formTypeId) throws QueryParameterMismatchException;
    Page<Object> executeGetFormTypesByCategoriesAndMunicipalities(Pageable pageable, java.lang.Integer formCategory, java.lang.Boolean isActive, java.lang.Integer userId) throws QueryParameterMismatchException;
    Page<Object> executeGetListofGroupName(Pageable pageable,java.util.List<java.lang.Integer> MunicipalityGroupID, java.lang.Integer MunicipalityID) throws QueryParameterMismatchException;
    Page<Object> executeGetListofUsers(Pageable pageable, java.lang.Integer municipalityID, java.lang.String Email) throws QueryParameterMismatchException;
    Page<Object> executeGetMunicipalityGroupIdIDs(Pageable pageable, java.lang.Integer userID) throws QueryParameterMismatchException;
    Page<Object> executeGetProcessGroupMemebersByFormGUID(Pageable pageable, java.lang.String FormGUID) throws QueryParameterMismatchException;
    Page<Object> executeGetRecentMessageId(Pageable pageable, java.lang.String form, java.sql.Timestamp PostedAt) throws QueryParameterMismatchException;
    Page<Object> executeGetRolesForMunicipality(Pageable pageable, java.lang.String role, java.lang.Integer municipality) throws QueryParameterMismatchException;
    Page<Object> executeGetRolesForUser(Pageable pageable, java.lang.Integer userId) throws QueryParameterMismatchException;
    Page<Object> executeGetUserID(Pageable pageable, java.lang.String Email) throws QueryParameterMismatchException;
    Page<Object> executeGetUserIdFromPasswordResetToken(Pageable pageable, java.lang.String token) throws QueryParameterMismatchException;
    Page<Object> executeGetWriteAccessGroupMembersByFormGUID(Pageable pageable, java.lang.String FormGUID) throws QueryParameterMismatchException;
	int executeInsertCategoryMapping(  java.lang.Integer FormTypeId , java.lang.Integer FormCategoryId) throws QueryParameterMismatchException;
	int executeInsertFormMessage(  java.lang.Integer UserId , java.lang.String RelatedFormGUID , java.lang.String Message , java.sql.Timestamp PostedAt) throws QueryParameterMismatchException;
	int executeInsertGroups(  java.lang.String GroupName , java.lang.String GroupDescription , java.lang.Integer MunicipalityId) throws QueryParameterMismatchException;
	int executeInsertNewRole(  java.lang.String RoleName , java.lang.Integer MunicipalityId , java.lang.String Description , java.lang.Integer UserId) throws QueryParameterMismatchException;
	int executeInsertSubscription(  java.lang.Integer UserId , java.lang.Integer MunicipalityId , java.lang.String DateSubscribed) throws QueryParameterMismatchException;
	int executeInsertTagForMessage(  java.lang.Integer FormMessageId , java.lang.Integer TaggedPersonId) throws QueryParameterMismatchException;
    Page<Object> executeManualFeeTypeCountForMunicipality(Pageable pageable, java.lang.Integer MunicipalityId) throws QueryParameterMismatchException;
	int executeMapAsAdminForVendor(  java.lang.Integer UserId , java.lang.Integer VendorId) throws QueryParameterMismatchException;
    Page<Object> executeMunicipalitiesGroupsCounts(Pageable pageable, java.lang.Integer municipality) throws QueryParameterMismatchException;
    Page<Object> executeMunicipalityCount(Pageable pageable) throws QueryParameterMismatchException;
    Page<Object> executeProcessFormsForUserByMunicipality(Pageable pageable, java.lang.Integer UserId, java.lang.Integer MunicipalityId, java.lang.Boolean Closed) throws QueryParameterMismatchException;
    Page<Object> executeProjectsForUsersAndSharedWith(Pageable pageable, java.lang.Integer municipalityId, java.lang.Boolean Active, java.lang.Integer creatorUser, java.lang.Integer sharedWithUser) throws QueryParameterMismatchException;
	int executeProjectSoftDelete(  java.lang.Boolean active , java.lang.String ProjectGuid) throws QueryParameterMismatchException;
	int executeRecordFormHistory(  java.lang.String FormGUID , java.lang.Integer FormTypeId , java.lang.Integer NewStatusId , java.lang.Integer OldStatusId , java.lang.String Comments , java.lang.Integer CreatedBy) throws QueryParameterMismatchException;
	int executeRemoveVendorFromMasterForms(  java.lang.String hb) throws QueryParameterMismatchException;
	int executeResetPasswordForUser(  java.lang.String newPassword , java.lang.String token) throws QueryParameterMismatchException;
	int executeResetPasswordWithTokenForUser(  java.lang.Integer userid , java.lang.String token) throws QueryParameterMismatchException;
    Page<Object> executeSearchFormByVendor(Pageable pageable, java.sql.Timestamp startd, java.sql.Timestamp endd, java.lang.Integer FormTypeId, java.lang.Boolean closed, java.lang.Integer vendorId) throws QueryParameterMismatchException;
	int executeSetPrimaryVendorStatusForFormandVendor(  java.lang.Boolean pv , java.lang.String form , java.lang.Integer vendor) throws QueryParameterMismatchException;
    Page<Object> executeStandardUserMunicipalites(Pageable pageable, java.lang.Integer USER) throws QueryParameterMismatchException;
    Page<Object> executeSubDivisonCount(Pageable pageable, java.lang.Integer municipalityId) throws QueryParameterMismatchException;
	int executeUpdateAsCXVendorAdmin(  java.lang.String role , java.lang.Integer municipality , java.lang.Integer user) throws QueryParameterMismatchException;
	int executeUpdateCFInProfile(  java.lang.String cf , java.lang.Integer user) throws QueryParameterMismatchException;
	int executeUpdateDevFormDetailsForCXAdmin(  java.lang.String Report , java.lang.String FormTableName , java.lang.Integer form) throws QueryParameterMismatchException;
	int executeUpdateFormStatusInMasterForms(  java.lang.Integer formStatus , java.lang.Boolean closed , java.lang.String FormGUID) throws QueryParameterMismatchException;
	int executeUpdateGlobalEmailSig(  java.lang.String gs , java.lang.Integer municipality) throws QueryParameterMismatchException;
	int executeUpdateInfoFromMyProfile(  java.lang.String fn , java.lang.String ln , java.lang.String em , java.lang.String ph , java.lang.String ad1 , java.lang.String ad2 , java.lang.Integer st , java.lang.String ct , java.lang.String ctry , java.lang.String pc , java.lang.Integer user) throws QueryParameterMismatchException;
	int executeUpdateMunicipalityInfo(  java.lang.String mn , java.lang.String em , java.lang.String ph , java.lang.String ad1 , java.lang.String ad2 , java.lang.Integer st , java.lang.String ct , java.lang.String pc , java.lang.Integer municipality) throws QueryParameterMismatchException;
	int executeUpdateNewPassword(  java.lang.String password , java.lang.Integer newUser) throws QueryParameterMismatchException;
	int executeUpdatePasswordAndCF(  java.lang.String password , java.lang.String cf , java.lang.Integer newUser) throws QueryParameterMismatchException;
	int executeUpdatePrimaryVendorInMasterForms(  java.lang.Integer VendorId , java.lang.String FormGUID) throws QueryParameterMismatchException;
	int executeUpdatePrimaryVendorStatusInVEndor2Forms(  java.lang.Boolean pv , java.lang.String form) throws QueryParameterMismatchException;
	int executeUpdateProcessOwnersForGUID(  java.lang.Integer AssignedToGroupId , java.lang.String GUID) throws QueryParameterMismatchException;
	int executeUpdateRoleForMunicipality(  java.lang.String role , java.lang.Integer municipality , java.lang.Integer user) throws QueryParameterMismatchException;
	int executeUpdateVendorForMasterForms(  java.lang.Integer CXVendorId , java.lang.String FormGUID) throws QueryParameterMismatchException;
	int executeUpdateVendorStatus(  java.sql.Timestamp DateApproved , java.lang.String ApprovedBy , java.sql.Timestamp ExpiresDate , java.lang.Boolean Active , java.lang.String ApprovalStatus , java.lang.String Reviewer , java.lang.Integer municipality , java.lang.Integer vendor) throws QueryParameterMismatchException;
	int executeUpdateWorkMunicipality(  java.lang.Boolean monday , java.lang.Boolean tuesday , java.lang.Boolean wednesday , java.lang.Boolean thursday , java.lang.Boolean friday , java.lang.Boolean saturday , java.lang.Boolean sunday , java.lang.String timezone , java.sql.Time openTime , java.sql.Time closeTime , java.lang.Integer municipalityId) throws QueryParameterMismatchException;
    Page<Object> executeUserCount(Pageable pageable) throws QueryParameterMismatchException;
    Page<Object> executeUserSubscriptionsCount(Pageable pageable) throws QueryParameterMismatchException;
    Page<Object> executeUserSubscriptionsCountForMunicipality(Pageable pageable, java.lang.Integer municipalityId) throws QueryParameterMismatchException;
    Page<Object> executeVendorCount(Pageable pageable) throws QueryParameterMismatchException;
    Page<Object> executeVendorsCountForMunicipalities(Pageable pageable, java.lang.Integer MunicipalityId) throws QueryParameterMismatchException;
    Page<Object> executeVendorsLinkedWithForm(Pageable pageable, java.lang.String RelatedFormGUID) throws QueryParameterMismatchException;
    Page<Object> executeVerifyPasswordResetToken(Pageable pageable, java.lang.String token) throws QueryParameterMismatchException;

	
	Page<Object> executeWMCustomQuerySelect(CustomQuery query, Pageable pageable) ;

	int executeWMCustomQueryUpdate(CustomQuery query) ;
}

