/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/

package com.civicxpress.cx2.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.file.model.Downloadable;

import com.civicxpress.cx2.FormMessages;
import com.civicxpress.cx2.FormTypeFields;
import com.civicxpress.cx2.FormsToInspections;
import com.civicxpress.cx2.InspectionDesign;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.UserSubscriptions;
import com.civicxpress.cx2.Users;
import com.civicxpress.cx2.Vendor;
import com.civicxpress.cx2.models.query.*;

public interface Cx2QueryExecutorService {

    Page<GetRecentMessageIdResponse> executeGetRecentMessageId(String form, Timestamp postedAt, Pageable pageable);

    Downloadable exportGetRecentMessageId(ExportType exportType, String form, Timestamp postedAt, Pageable pageable);

    Page<GetInspectionsByOutcomeResponse> executeGetInspectionsByOutcome(String outcome, Integer municipality, Pageable pageable);

    Downloadable exportGetInspectionsByOutcome(ExportType exportType, String outcome, Integer municipality, Pageable pageable);

    Page<FormTypeFields> executeGetCalculatableFormFields(Integer formTypeId, Pageable pageable);

    Downloadable exportGetCalculatableFormFields(ExportType exportType, Integer formTypeId, Pageable pageable);

    Page<AdminsMunicipalitiesResponse> executeAdminsMunicipalities(Integer user, Pageable pageable);

    Downloadable exportAdminsMunicipalities(ExportType exportType, Integer user, Pageable pageable);

    Integer executeInsertIntoCart(InsertIntoCartRequest insertIntoCartRequest);

    Integer executeProjectSoftDelete(ProjectSoftDeleteRequest projectSoftDeleteRequest);

    Integer executeUpdateMultipleFeeComments(UpdateMultipleFeeCommentsRequest updateMultipleFeeCommentsRequest);

    Integer executeUpdatePrimaryVendorInMasterForms(UpdatePrimaryVendorInMasterFormsRequest updatePrimaryVendorInMasterFormsRequest);

    Page<CounNewCasesAndAfterDateResponse> executeCounNewCasesAndAfterDate(Boolean codeEnforcement, Boolean closed, Integer municipalityId, Timestamp dateSubmitted, Pageable pageable);

    Downloadable exportCounNewCasesAndAfterDate(ExportType exportType, Boolean codeEnforcement, Boolean closed, Integer municipalityId, Timestamp dateSubmitted, Pageable pageable);

    Page<StandardUserMunicipalitesResponse> executeStandardUserMunicipalites(Integer user, Pageable pageable);

    Downloadable exportStandardUserMunicipalites(ExportType exportType, Integer user, Pageable pageable);

    Page<GetEmailIdResponse> executeGetEmailId(Integer userId, Pageable pageable);

    Downloadable exportGetEmailId(ExportType exportType, Integer userId, Pageable pageable);

    Page<CounCasesByStatusAndMunicipalityResponse> executeCounCasesByStatusAndMunicipality(Boolean codeEnforcement, Boolean closed, Integer municipalityId, Pageable pageable);

    Downloadable exportCounCasesByStatusAndMunicipality(ExportType exportType, Boolean codeEnforcement, Boolean closed, Integer municipalityId, Pageable pageable);

    Integer executeAddUsersToVendor(AddUsersToVendorRequest addUsersToVendorRequest);

    Integer executeUpdatePasswordAndCF(UpdatePasswordAndCfRequest updatePasswordAndCfRequest);

    Page<CheckingUserWithMunicipalityInRolesResponse> executeCheckingUserWithMunicipalityInRoles(Integer muncipality, Integer user, Pageable pageable);

    Downloadable exportCheckingUserWithMunicipalityInRoles(ExportType exportType, Integer muncipality, Integer user, Pageable pageable);

    Integer executeUpdateCFInProfile(UpdateCfinProfileRequest updateCfinProfileRequest);

    Page<MunicipalitiesGroupsCountsResponse> executeMunicipalitiesGroupsCounts(Integer municipality, Pageable pageable);

    Downloadable exportMunicipalitiesGroupsCounts(ExportType exportType, Integer municipality, Pageable pageable);

    FormMessages executeGetRecentMessageIdForProject(String project, Timestamp postedAt);

    Integer executeInsertCategoryMapping(InsertCategoryMappingRequest insertCategoryMappingRequest);

    Page<ProcessFormsForUserByMunicipalityResponse> executeProcessFormsForUserByMunicipality(Boolean closed, Long municipalityId, Long userId, Pageable pageable);

    Downloadable exportProcessFormsForUserByMunicipality(ExportType exportType, Boolean closed, Long municipalityId, Long userId, Pageable pageable);

    Page<FormsCountForMunicipalitiesResponse> executeFormsCountForMunicipalities(Integer municipalityId, Pageable pageable);

    Downloadable exportFormsCountForMunicipalities(ExportType exportType, Integer municipalityId, Pageable pageable);

    Page<CountOfMunicipalityProjectsResponse> executeCountOfMunicipalityProjects(Integer municipalityId, Boolean active, Pageable pageable);

    Downloadable exportCountOfMunicipalityProjects(ExportType exportType, Integer municipalityId, Boolean active, Pageable pageable);

    Page<VendorCountResponse> executeVendorCount(Pageable pageable);

    Downloadable exportVendorCount(ExportType exportType, Pageable pageable);

    Integer executeUpdateWorkMunicipality(UpdateWorkMunicipalityRequest updateWorkMunicipalityRequest);

    Page<FeeByFeeTypesToDashboardResponse> executeFeeByFeeTypesToDashboard(Integer municpality, Date startDate, Date endDate, Pageable pageable);

    Downloadable exportFeeByFeeTypesToDashboard(ExportType exportType, Integer municpality, Date startDate, Date endDate, Pageable pageable);

    Page<GetFormTypeFieldsByTypeIdResponse> executeGetFormTypeFieldsByTypeId(Long formTypeId, Pageable pageable);

    Downloadable exportGetFormTypeFieldsByTypeId(ExportType exportType, Long formTypeId, Pageable pageable);

    Page<CountOfProjectsForUsersAndSharedWithByMunicipalityResponse> executeCountOfProjectsForUsersAndSharedWithByMunicipality(Integer municipalityId, Boolean active, Integer creatorUser, Integer sharedWithUser, Pageable pageable);

    Downloadable exportCountOfProjectsForUsersAndSharedWithByMunicipality(ExportType exportType, Integer municipalityId, Boolean active, Integer creatorUser, Integer sharedWithUser, Pageable pageable);

    Integer executeUpdateConsiderClosedForInspectionOutcome(UpdateConsiderClosedForInspectionOutcomeRequest updateConsiderClosedForInspectionOutcomeRequest);

    Integer executeUpdateAsCXVendorAdmin(UpdateAsCxvendorAdminRequest updateAsCxvendorAdminRequest);

    Page<FormsByCategoryResponse> executeFormsByCategory(Integer formCategory, Boolean isActive, Pageable pageable);

    Downloadable exportFormsByCategory(ExportType exportType, Integer formCategory, Boolean isActive, Pageable pageable);

    SumOfFeesInUsersCartResponse executeSumOfFeesInUsersCart(Integer user);

    Page<SearchWithFormTitleResponse> executeSearchWithFormTitle(Boolean codeEnforcement, Integer municipalityId, String formTitle, Pageable pageable);

    Downloadable exportSearchWithFormTitle(ExportType exportType, Boolean codeEnforcement, Integer municipalityId, String formTitle, Pageable pageable);

    Page<CountOfCompnayFormsByVendorIdResponse> executeCountOfCompnayFormsByVendorId(Boolean closed, Integer vendorId, Pageable pageable);

    Downloadable exportCountOfCompnayFormsByVendorId(ExportType exportType, Boolean closed, Integer vendorId, Pageable pageable);

    Page<MasterForms> executeOpenedOrClosedFormsForUserOrSharedWith(Long userId, Pageable pageable);

    Downloadable exportOpenedOrClosedFormsForUserOrSharedWith(ExportType exportType, Long userId, Pageable pageable);

    Page<SearchUsersByEmailOrNameResponse> executeSearchUsersByEmailOrName(String searchString, Pageable pageable);

    Downloadable exportSearchUsersByEmailOrName(ExportType exportType, String searchString, Pageable pageable);

    Integer executeUpdateGlobalEmailSig(UpdateGlobalEmailSigRequest updateGlobalEmailSigRequest);

    Integer executeUpdateTrackViolations(UpdateTrackViolationsRequest updateTrackViolationsRequest);

    Integer executeDeleteFromVendorAdmins(Integer user, Integer vendor);

    Integer executeSetPrimaryVendorStatusForFormandVendor(SetPrimaryVendorStatusForFormandVendorRequest setPrimaryVendorStatusForFormandVendorRequest);

    Page<AllFeeformCountResponse> executeAllFeeformCount(Boolean allowPayment, String paidStatus, Integer municipalityId, Integer userId, Pageable pageable);

    Downloadable exportAllFeeformCount(ExportType exportType, Boolean allowPayment, String paidStatus, Integer municipalityId, Integer userId, Pageable pageable);

    Integer executeDeleteCategoryMapping(Integer form);

    Page<GetUserIdFromPasswordResetTokenResponse> executeGetUserIdFromPasswordResetToken(String token, Pageable pageable);

    Downloadable exportGetUserIdFromPasswordResetToken(ExportType exportType, String token, Pageable pageable);

    Page<FormsByVendorsForDashboardResponse> executeFormsByVendorsForDashboard(Integer municipality, Timestamp startDate, Timestamp endDate, Pageable pageable);

    Downloadable exportFormsByVendorsForDashboard(ExportType exportType, Integer municipality, Timestamp startDate, Timestamp endDate, Pageable pageable);

    Page<CountOfProcessFormsByMuncipalityResponse> executeCountOfProcessFormsByMuncipality(Integer municipalityId, Boolean closed, Integer userId, Pageable pageable);

    Downloadable exportCountOfProcessFormsByMuncipality(ExportType exportType, Integer municipalityId, Boolean closed, Integer userId, Pageable pageable);

    Page<CountAssignedInspectionsBetweenDatesResponse> executeCountAssignedInspectionsBetweenDates(Boolean isClosed, Integer municipality, Integer assignedTo, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    Downloadable exportCountAssignedInspectionsBetweenDates(ExportType exportType, Boolean isClosed, Integer municipality, Integer assignedTo, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    Integer executeUpdateVendorsToProject(UpdateVendorsToProjectRequest updateVendorsToProjectRequest);

    Page<CountOfCxprojectsActiveResponse> executeCountOfCXProjectsActive(Pageable pageable);

    Downloadable exportCountOfCXProjectsActive(ExportType exportType, Pageable pageable);

    Page<DistinctInspectionDesignsForFormTypeResponse> executeDistinctInspectionDesignsForFormType(Integer formTypeId, Boolean active, Pageable pageable);

    Downloadable exportDistinctInspectionDesignsForFormType(ExportType exportType, Integer formTypeId, Boolean active, Pageable pageable);

    Page<SearchAllFormsByAddressResponse> executeSearchAllFormsByAddress(Boolean codeEnforcement, Integer gisrecordId, Integer municipalityId, Integer formcategoryId, Integer formtypeId, Boolean closed, Timestamp startd, Timestamp endd, Pageable pageable);

    Downloadable exportSearchAllFormsByAddress(ExportType exportType, Boolean codeEnforcement, Integer gisrecordId, Integer municipalityId, Integer formcategoryId, Integer formtypeId, Boolean closed, Timestamp startd, Timestamp endd, Pageable pageable);

    Page<FormsToInspections> executeInspectionsOfForm(String form, Pageable pageable);

    Downloadable exportInspectionsOfForm(ExportType exportType, String form, Pageable pageable);

    Page<CasesByMunicipalityResponse> executeCasesByMunicipality(Boolean codeEnforcement, Boolean closed, Integer municipalityId, Pageable pageable);

    Downloadable exportCasesByMunicipality(ExportType exportType, Boolean codeEnforcement, Boolean closed, Integer municipalityId, Pageable pageable);

    Integer executeAddingVendorsToForm(AddingVendorsToFormRequest addingVendorsToFormRequest);

    Integer executeUpdateUserBillingInfoPreference(UpdateUserBillingInfoPreferenceRequest updateUserBillingInfoPreferenceRequest);

    Page<GetCartItemIdsResponse> executeGetCartItemIds(Long userId, Pageable pageable);

    Downloadable exportGetCartItemIds(ExportType exportType, Long userId, Pageable pageable);

    Page<PreferenceForUserResponse> executePreferenceForUser(Integer userId, Pageable pageable);

    Downloadable exportPreferenceForUser(ExportType exportType, Integer userId, Pageable pageable);

    Integer executeInsertMasterInspections(InsertMasterInspectionsRequest insertMasterInspectionsRequest);

    Page<UserCountResponse> executeUserCount(Pageable pageable);

    Downloadable exportUserCount(ExportType exportType, Pageable pageable);

    Page<FeesInCartByUserResponse> executeFeesInCartByUser(Integer user, Pageable pageable);

    Downloadable exportFeesInCartByUser(ExportType exportType, Integer user, Pageable pageable);

    Page<NoOfItemsInUserCartResponse> executeNoOfItemsInUserCart(Integer user, Pageable pageable);

    Downloadable exportNoOfItemsInUserCart(ExportType exportType, Integer user, Pageable pageable);

    Integer executeDeleteInspectionDesign(Integer inspectionId);

    Page<CheckIfCompanyUserIsVendorAdminResponse> executeCheckIfCompanyUserIsVendorAdmin(Integer user, Integer vendor, Pageable pageable);

    Downloadable exportCheckIfCompanyUserIsVendorAdmin(ExportType exportType, Integer user, Integer vendor, Pageable pageable);

    Page<AllFeesOfFormsForCreatedByAndSharedWithByMunicipalityResponse> executeAllFeesOfFormsForCreatedByAndSharedWithByMunicipality(Boolean allowPayment, Integer municipalityId, String paidStatus, Integer userId, Pageable pageable);

    Downloadable exportAllFeesOfFormsForCreatedByAndSharedWithByMunicipality(ExportType exportType, Boolean allowPayment, Integer municipalityId, String paidStatus, Integer userId, Pageable pageable);

    Integer executeUpdateProjectDetails(UpdateProjectDetailsRequest updateProjectDetailsRequest);

    Integer executeInsertUserPreference(InsertUserPreferenceRequest insertUserPreferenceRequest);

    Page<CountOfMuncipalityApplicationsByVendorResponse> executeCountOfMuncipalityApplicationsByVendor(Integer vendorId, Pageable pageable);

    Downloadable exportCountOfMuncipalityApplicationsByVendor(ExportType exportType, Integer vendorId, Pageable pageable);

    Page<AllFeeFormCountByMunicipalityResponse> executeAllFeeFormCountByMunicipality(Boolean allowPayment, String paidStatus, Integer municipalityId, Integer userId, Pageable pageable);

    Downloadable exportAllFeeFormCountByMunicipality(ExportType exportType, Boolean allowPayment, String paidStatus, Integer municipalityId, Integer userId, Pageable pageable);

    Page<UserSubscriptionsCountForMunicipalityResponse> executeUserSubscriptionsCountForMunicipality(Integer municipalityId, Pageable pageable);

    Downloadable exportUserSubscriptionsCountForMunicipality(ExportType exportType, Integer municipalityId, Pageable pageable);

    Integer executeUpdateFeesStatus(UpdateFeesStatusRequest updateFeesStatusRequest);

    Integer executeAddUserToMunicipalityGroup(AddUserToMunicipalityGroupRequest addUserToMunicipalityGroupRequest);

    Integer executeAddGIStoProjects(AddGistoProjectsRequest addGistoProjectsRequest);

    Page<CountOfUserFormsResponse> executeCountOfUserForms(Boolean codeEnforcement, Boolean closed, Integer creatorUser, Integer sharedWithUser, Integer municipalityId, Pageable pageable);

    Downloadable exportCountOfUserForms(ExportType exportType, Boolean codeEnforcement, Boolean closed, Integer creatorUser, Integer sharedWithUser, Integer municipalityId, Pageable pageable);

    Page<CountOfFormsForMunicipalityResponse> executeCountOfFormsForMunicipality(Integer municipalityId, Pageable pageable);

    Downloadable exportCountOfFormsForMunicipality(ExportType exportType, Integer municipalityId, Pageable pageable);

    Integer executeDeleteDraft(Long draftId);

    Integer executeUpdateDevFormDetailsForCXAdmin(UpdateDevFormDetailsForCxadminRequest updateDevFormDetailsForCxadminRequest);

    Page<TimeFormEnteredStatusResponse> executeTimeFormEnteredStatus(String formGuid, Pageable pageable);

    Downloadable exportTimeFormEnteredStatus(ExportType exportType, String formGuid, Pageable pageable);

    Integer executeUpdateVendorForMasterForms(UpdateVendorForMasterFormsRequest updateVendorForMasterFormsRequest);

    Page<CountOfCxcodeSetsGlobalResponse> executeCountOfCXCodeSetsGlobal(Pageable pageable);

    Downloadable exportCountOfCXCodeSetsGlobal(ExportType exportType, Pageable pageable);

    Page<EmployeesOrAdminsMunicipalitiesResponse> executeEmployeesOrAdminsMunicipalities(Integer user, String role, Pageable pageable);

    Downloadable exportEmployeesOrAdminsMunicipalities(ExportType exportType, Integer user, String role, Pageable pageable);

    Integer executeUpdateForceInspectionSequenceForForm(UpdateForceInspectionSequenceForFormRequest updateForceInspectionSequenceForFormRequest);

    Page<CountOfVendorsResponse> executeCountOfVendors(Integer vendor, Pageable pageable);

    Downloadable exportCountOfVendors(ExportType exportType, Integer vendor, Pageable pageable);

    Integer executeUpdateMunicipalityInfo(UpdateMunicipalityInfoRequest updateMunicipalityInfoRequest);

    Page<CountOfUserFormsForMunicipalityResponse> executeCountOfUserFormsForMunicipality(Integer municipalityId, Boolean closed, Integer creatorUser, Integer sharedWithUser, Pageable pageable);

    Downloadable exportCountOfUserFormsForMunicipality(ExportType exportType, Integer municipalityId, Boolean closed, Integer creatorUser, Integer sharedWithUser, Pageable pageable);

    Integer executeDeleteToken(String token);

    Page<CountOfAllProjectsForUsersAndSharedWithResponse> executeCountOfAllProjectsForUsersAndSharedWith(Boolean active, Integer municipalityId, Integer creatorUser, Integer sharedWithUser, Pageable pageable);

    Downloadable exportCountOfAllProjectsForUsersAndSharedWith(ExportType exportType, Boolean active, Integer municipalityId, Integer creatorUser, Integer sharedWithUser, Pageable pageable);

    Integer executeInsertTagForMessage(InsertTagForMessageRequest insertTagForMessageRequest);

    Page<ManualFeeTypeCountForMunicipalityResponse> executeManualFeeTypeCountForMunicipality(Integer municipalityId, Pageable pageable);

    Downloadable exportManualFeeTypeCountForMunicipality(ExportType exportType, Integer municipalityId, Pageable pageable);

    Page<AssignedInspectionsBetweenDatesResponse> executeAssignedInspectionsBetweenDates(Boolean isClosed, Integer municipality, Integer assignedTo, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    Downloadable exportAssignedInspectionsBetweenDates(ExportType exportType, Boolean isClosed, Integer municipality, Integer assignedTo, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    Integer executeInsertSubscription(InsertSubscriptionRequest insertSubscriptionRequest);

    Page<GetProcessGroupMemebersByFormGuidResponse> executeGetProcessGroupMemebersByFormGUID(String formGuid, Integer userId, Pageable pageable);

    Downloadable exportGetProcessGroupMemebersByFormGUID(ExportType exportType, String formGuid, Integer userId, Pageable pageable);

    Page<AdminVendorsListResponse> executeAdminVendorsList(Integer user, Pageable pageable);

    Downloadable exportAdminVendorsList(ExportType exportType, Integer user, Pageable pageable);

    Page<GetFormTypesByCategoriesAndMunicipalitiesResponse> executeGetFormTypesByCategoriesAndMunicipalities(Integer formCategory, Integer municipalityId, Boolean codeEnforcement, Integer userId, Pageable pageable);

    Downloadable exportGetFormTypesByCategoriesAndMunicipalities(ExportType exportType, Integer formCategory, Integer municipalityId, Boolean codeEnforcement, Integer userId, Pageable pageable);

    Integer executeDeleteRoleForMuncipality(String role, Integer municipality, Integer user);

    Page<Users> executeGetMunicipalityEmployees(String roleName, Integer municipalityId, String email, Pageable pageable);

    Downloadable exportGetMunicipalityEmployees(ExportType exportType, String roleName, Integer municipalityId, String email, Pageable pageable);

    Integer executeMapAsAdminForVendor(MapAsAdminForVendorRequest mapAsAdminForVendorRequest);

    Page<CountOfInspectionDesignByMunicipalityResponse> executeCountOfInspectionDesignByMunicipality(Integer municipalityId, Pageable pageable);

    Downloadable exportCountOfInspectionDesignByMunicipality(ExportType exportType, Integer municipalityId, Pageable pageable);

    Integer executeInsertFormsIntoProject(InsertFormsIntoProjectRequest insertFormsIntoProjectRequest);

    Integer executeRecordFormHistory(RecordFormHistoryRequest recordFormHistoryRequest);

    Page<GetWriteAccessGroupMembersByFormGuidResponse> executeGetWriteAccessGroupMembersByFormGUID(String formGuid, Pageable pageable);

    Downloadable exportGetWriteAccessGroupMembersByFormGUID(ExportType exportType, String formGuid, Pageable pageable);

    Page<FormsByCategoryForDashBoardResponse> executeFormsByCategoryForDashBoard(Integer municipality, Timestamp startDate, Timestamp endDate, Pageable pageable);

    Downloadable exportFormsByCategoryForDashBoard(ExportType exportType, Integer municipality, Timestamp startDate, Timestamp endDate, Pageable pageable);

    Page<EmployeesMunicipalitiesResponse> executeEmployeesMunicipalities(Integer user, Pageable pageable);

    Downloadable exportEmployeesMunicipalities(ExportType exportType, Integer user, Pageable pageable);

    Integer executeRemoveVendorFromMasterForms(RemoveVendorFromMasterFormsRequest removeVendorFromMasterFormsRequest);

    Integer executeUpdateFormStatusInMasterForms(UpdateFormStatusInMasterFormsRequest updateFormStatusInMasterFormsRequest);

    Page<ProjectFormsResponse> executeProjectForms(String project, Pageable pageable);

    Downloadable exportProjectForms(ExportType exportType, String project, Pageable pageable);

    Page<GetFeeIdsOfUserInCartResponse> executeGetFeeIdsOfUserInCart(Integer user, Pageable pageable);

    Downloadable exportGetFeeIdsOfUserInCart(ExportType exportType, Integer user, Pageable pageable);

    Page<CountOfCasesForMunicipalityResponse> executeCountOfCasesForMunicipality(Integer municipalityId, Pageable pageable);

    Downloadable exportCountOfCasesForMunicipality(ExportType exportType, Integer municipalityId, Pageable pageable);

    Integer executeUpdatePrimaryVendorStatusInVEndor2Forms(UpdatePrimaryVendorStatusInVendor2formsRequest updatePrimaryVendorStatusInVendor2formsRequest);

    Page<MunicipalityProjectsResponse> executeMunicipalityProjects(Boolean active, Integer municipalityId, Pageable pageable);

    Downloadable exportMunicipalityProjects(ExportType exportType, Boolean active, Integer municipalityId, Pageable pageable);

    Page<GetMunicipalityGroupIdIdsResponse> executeGetMunicipalityGroupIdIDs(Integer userId, Pageable pageable);

    Downloadable exportGetMunicipalityGroupIdIDs(ExportType exportType, Integer userId, Pageable pageable);

    Page<UserSubscriptions> executeUserSubscriptionsByMunicipality(Integer municipalityId, Pageable pageable);

    Downloadable exportUserSubscriptionsByMunicipality(ExportType exportType, Integer municipalityId, Pageable pageable);

    Page<FormsForUsersAndSharedResponse> executeFormsForUsersAndShared(Boolean codeEnforcement, Boolean closed, Integer creatorUser, Integer sharedWithUser, Long municipalityId, Pageable pageable);

    Downloadable exportFormsForUsersAndShared(ExportType exportType, Boolean codeEnforcement, Boolean closed, Integer creatorUser, Integer sharedWithUser, Long municipalityId, Pageable pageable);

    Page<GetUserIdResponse> executeGetUserID(String email, Pageable pageable);

    Downloadable exportGetUserID(ExportType exportType, String email, Pageable pageable);

    Page<GetListofUsersResponse> executeGetListofUsers(Integer municipalityId, String email, Pageable pageable);

    Downloadable exportGetListofUsers(ExportType exportType, Integer municipalityId, String email, Pageable pageable);

    Page<CountOfGlobalCodeListResponse> executeCountOfGlobalCodeList(Pageable pageable);

    Downloadable exportCountOfGlobalCodeList(ExportType exportType, Pageable pageable);

    Integer executeInsertNewRole(InsertNewRoleRequest insertNewRoleRequest);

    Integer executeRemoveFeesFromAllUserCarts(List<Integer> feeList);

    Page<BannedDetailsResponse> executeBannedDetails(String emailid, Pageable pageable);

    Downloadable exportBannedDetails(ExportType exportType, String emailid, Pageable pageable);

    Page<GetVendorApprovalListByMunicipalityResponse> executeGetVendorApprovalListByMunicipality(String approvalStatus, Integer municipalityId, Boolean a, Pageable pageable);

    Downloadable exportGetVendorApprovalListByMunicipality(ExportType exportType, String approvalStatus, Integer municipalityId, Boolean a, Pageable pageable);

    Integer executeDeleteMunicipalityGroup(Integer municipalityGroupId, Integer userId);

    Page<VerifyPasswordResetTokenResponse> executeVerifyPasswordResetToken(String token, Pageable pageable);

    Downloadable exportVerifyPasswordResetToken(ExportType exportType, String token, Pageable pageable);

    Page<GetListofGroupNameResponse> executeGetListofGroupName(List<Integer> municipalityGroupId, Integer municipalityId, Pageable pageable);

    Downloadable exportGetListofGroupName(ExportType exportType, List<Integer> municipalityGroupId, Integer municipalityId, Pageable pageable);

    Page<GetProjectGisrecordsResponse> executeGetProjectGisrecords(String relatedProjectGuid, Pageable pageable);

    Downloadable exportGetProjectGisrecords(ExportType exportType, String relatedProjectGuid, Pageable pageable);

    Page<SubDivisonCountResponse> executeSubDivisonCount(Integer municipalityId, Pageable pageable);

    Downloadable exportSubDivisonCount(ExportType exportType, Integer municipalityId, Pageable pageable);

    Integer executeDeleteAllMunicipalityRoles(Integer municipalityId, Integer userId);

    Page<GetRolesForMunicipalityResponse> executeGetRolesForMunicipality(String role, Integer municipality, Pageable pageable);

    Downloadable exportGetRolesForMunicipality(ExportType exportType, String role, Integer municipality, Pageable pageable);

    Integer executeResetPasswordForUser(ResetPasswordForUserRequest resetPasswordForUserRequest);

    Integer executeInsertGroups(InsertGroupsRequest insertGroupsRequest);

    Integer executeUpdateUserPreferences(UpdateUserPreferencesRequest updateUserPreferencesRequest);

    Integer executeAddGIStoForms(AddGistoFormsRequest addGistoFormsRequest);

    Integer executeAddMemeberToProject(AddMemeberToProjectRequest addMemeberToProjectRequest);

    CheckInspectionLimitResponse executeCheckInspectionLimit(Integer inspectionDesignId, LocalDateTime startOfDay, LocalDateTime endOfDay);

    Page<GetOwnersForGisRecordsResponse> executeGetOwnersForGisRecords(List<Integer> gisRecordIds, Pageable pageable);

    Downloadable exportGetOwnersForGisRecords(ExportType exportType, List<Integer> gisRecordIds, Pageable pageable);

    Page<CountOfMunicipalityCodeSetsResponse> executeCountOfMunicipalityCodeSets(Integer municipalityId, Pageable pageable);

    Downloadable exportCountOfMunicipalityCodeSets(ExportType exportType, Integer municipalityId, Pageable pageable);

    Integer executeInsertFormsToInspectionsMapping(InsertFormsToInspectionsMappingRequest insertFormsToInspectionsMappingRequest);

    Page<FetchRolesForUserWithMunicipalityResponse> executeFetchRolesForUserWithMunicipality(Integer user, Integer municipality, Pageable pageable);

    Downloadable exportFetchRolesForUserWithMunicipality(ExportType exportType, Integer user, Integer municipality, Pageable pageable);

    Integer executeUpdateAssessFeeYN(UpdateAssessFeeYnRequest updateAssessFeeYnRequest);

    Page<SearchAllFormsByUserResponse> executeSearchAllFormsByUser(Boolean codeEnforcement, Integer sharedWithUser, Integer municipalityId, Integer formcategoryId, Integer formtypeId, Boolean closed, Timestamp startd, Timestamp endd, Pageable pageable);

    Downloadable exportSearchAllFormsByUser(ExportType exportType, Boolean codeEnforcement, Integer sharedWithUser, Integer municipalityId, Integer formcategoryId, Integer formtypeId, Boolean closed, Timestamp startd, Timestamp endd, Pageable pageable);

    Page<Vendor> executeVendorsByMunicipalityAndStatus(Integer municipalityId, String approvalStatus, Boolean active, String companyName, Pageable pageable);

    Downloadable exportVendorsByMunicipalityAndStatus(ExportType exportType, Integer municipalityId, String approvalStatus, Boolean active, String companyName, Pageable pageable);

    Integer executeSetModifiedDateForProject(SetModifiedDateForProjectRequest setModifiedDateForProjectRequest);

    Page<CompanyFormsByVendorIdResponse> executeCompanyFormsByVendorId(Boolean closed, Integer vendorId, Pageable pageable);

    Downloadable exportCompanyFormsByVendorId(ExportType exportType, Boolean closed, Integer vendorId, Pageable pageable);

    Integer executeUpdateInfoFromMyProfile(UpdateInfoFromMyProfileRequest updateInfoFromMyProfileRequest);

    Integer executeResetPasswordWithTokenForUser(Integer userid, String token);

    Page<VendorsLinkedWithFormResponse> executeVendorsLinkedWithForm(String relatedFormGuid, Pageable pageable);

    Downloadable exportVendorsLinkedWithForm(ExportType exportType, String relatedFormGuid, Pageable pageable);

    Integer executeUpdateRoleForMunicipality(UpdateRoleForMunicipalityRequest updateRoleForMunicipalityRequest);

    Page<GetFormsForMunicpalityResponse> executeGetFormsForMunicpality(Integer municipalityId, Pageable pageable);

    Downloadable exportGetFormsForMunicpality(ExportType exportType, Integer municipalityId, Pageable pageable);

    Integer executeUpdateProcessOwnersForGUID(UpdateProcessOwnersForGuidRequest updateProcessOwnersForGuidRequest);

    Page<MasterForms> executeSearchFormByVendor(Timestamp startd, Timestamp endd, Integer formTypeId, Integer municipalityId, Boolean closed, Integer vendorId, Pageable pageable);

    Downloadable exportSearchFormByVendor(ExportType exportType, Timestamp startd, Timestamp endd, Integer formTypeId, Integer municipalityId, Boolean closed, Integer vendorId, Pageable pageable);

    Page<CountOfMunicipalityCodeListResponse> executeCountOfMunicipalityCodeList(Integer municipalityId, Pageable pageable);

    Downloadable exportCountOfMunicipalityCodeList(ExportType exportType, Integer municipalityId, Pageable pageable);

    Integer executeUpdateProjectDescription(UpdateProjectDescriptionRequest updateProjectDescriptionRequest);

    Page<AllFeesOfFormsForCreatedByAndSharedWithResponse> executeAllFeesOfFormsForCreatedByAndSharedWith(Boolean allowPayment, String paidStatus, Integer userId, Pageable pageable);

    Downloadable exportAllFeesOfFormsForCreatedByAndSharedWith(ExportType exportType, Boolean allowPayment, String paidStatus, Integer userId, Pageable pageable);

    Page<FormsWithCodeEnforcementByMunicipalityResponse> executeFormsWithCodeEnforcementByMunicipality(Boolean codeEnforcement, Boolean closed, Integer municipalityId, Pageable pageable);

    Downloadable exportFormsWithCodeEnforcementByMunicipality(ExportType exportType, Boolean codeEnforcement, Boolean closed, Integer municipalityId, Pageable pageable);

    Page<VendorsCountForMunicipalitiesResponse> executeVendorsCountForMunicipalities(Integer municipalityId, Pageable pageable);

    Downloadable exportVendorsCountForMunicipalities(ExportType exportType, Integer municipalityId, Pageable pageable);

    Page<GetRolesForUserResponse> executeGetRolesForUser(Integer userId, Pageable pageable);

    Downloadable exportGetRolesForUser(ExportType exportType, Integer userId, Pageable pageable);

    Page<CountAssignedInspectionsLessThanDateResponse> executeCountAssignedInspectionsLessThanDate(Boolean isClosed, Integer assignedTo, LocalDateTime requestedFor, Integer municipality, Pageable pageable);

    Downloadable exportCountAssignedInspectionsLessThanDate(ExportType exportType, Boolean isClosed, Integer assignedTo, LocalDateTime requestedFor, Integer municipality, Pageable pageable);

    Integer executeDeleteFromVendorUsers(Integer user, Integer vendor);

    Page<CheckingUserWithInVendorUsersResponse> executeCheckingUserWithInVendorUsers(Integer user, Integer vendor, Pageable pageable);

    Downloadable exportCheckingUserWithInVendorUsers(ExportType exportType, Integer user, Integer vendor, Pageable pageable);

    Integer executeUpdateVendorStatus(UpdateVendorStatusRequest updateVendorStatusRequest);

    Integer executeDeleteProjectForms(Integer projectForm);

    Page<InspectionDesign> executeInspectionDesignForCategoriesMappedToForms(Integer formTypeId, Pageable pageable);

    Downloadable exportInspectionDesignForCategoriesMappedToForms(ExportType exportType, Integer formTypeId, Pageable pageable);

    Page<GetGis2formsByFormResponse> executeGetGis2formsByForm(String relatedFormGuid, Pageable pageable);

    Downloadable exportGetGis2formsByForm(ExportType exportType, String relatedFormGuid, Pageable pageable);

    Page<UnpaidFormFeeCountResponse> executeUnpaidFormFeeCount(Boolean allowPayment, String paidStatus, Integer userId, Pageable pageable);

    Downloadable exportUnpaidFormFeeCount(ExportType exportType, Boolean allowPayment, String paidStatus, Integer userId, Pageable pageable);

    Page<GetInspectionsToBeScheduledByMunicipalityResponse> executeGetInspectionsToBeScheduledByMunicipality(String outcome, Integer municipality, Pageable pageable);

    Downloadable exportGetInspectionsToBeScheduledByMunicipality(ExportType exportType, String outcome, Integer municipality, Pageable pageable);

    Page<FormsTypesForDashboardResponse> executeFormsTypesForDashboard(Integer municipality, Timestamp startDate, Timestamp endDate, Pageable pageable);

    Downloadable exportFormsTypesForDashboard(ExportType exportType, Integer municipality, Timestamp startDate, Timestamp endDate, Pageable pageable);

    Integer executeDeleteExistingSubscriptionsForUser(Integer user);

    Page<MunicipalityCountResponse> executeMunicipalityCount(Pageable pageable);

    Downloadable exportMunicipalityCount(ExportType exportType, Pageable pageable);

    Page<Users> executeGetInspectorsByMunicipality(Integer municipalityId, Pageable pageable);

    Downloadable exportGetInspectorsByMunicipality(ExportType exportType, Integer municipalityId, Pageable pageable);

    Integer executeUpdateMultipleFeeStatus(UpdateMultipleFeeStatusRequest updateMultipleFeeStatusRequest);

    Page<UserSubscriptionsCountResponse> executeUserSubscriptionsCount(Pageable pageable);

    Downloadable exportUserSubscriptionsCount(ExportType exportType, Pageable pageable);

    Integer executeInsertFormMessage(InsertFormMessageRequest insertFormMessageRequest);

    Page<CasesByMunicipalityAndAfterDateResponse> executeCasesByMunicipalityAndAfterDate(Boolean codeEnforcement, Boolean closed, Integer municipalityId, Timestamp dateSubmitted, Pageable pageable);

    Downloadable exportCasesByMunicipalityAndAfterDate(ExportType exportType, Boolean codeEnforcement, Boolean closed, Integer municipalityId, Timestamp dateSubmitted, Pageable pageable);

    Page<SearchAllFormsByVendorResponse> executeSearchAllFormsByVendor(Boolean codeEnforcement, Integer vendorId, Integer municipalityId, Integer formcategoryId, Integer formtypeId, Boolean closed, Timestamp startd, Timestamp endd, Pageable pageable);

    Downloadable exportSearchAllFormsByVendor(ExportType exportType, Boolean codeEnforcement, Integer vendorId, Integer municipalityId, Integer formcategoryId, Integer formtypeId, Boolean closed, Timestamp startd, Timestamp endd, Pageable pageable);

    Page<Projects> executeProjectsForUsersAndSharedWith(Boolean active, Integer creatorUser, Integer sharedWithUser, Long municipalityId, Pageable pageable);

    Downloadable exportProjectsForUsersAndSharedWith(ExportType exportType, Boolean active, Integer creatorUser, Integer sharedWithUser, Long municipalityId, Pageable pageable);

    Integer executeUpdateNewPassword(UpdateNewPasswordRequest updateNewPasswordRequest);

    Page<FormsTaggedWithGisrecordsResponse> executeFormsTaggedWithGISRecords(Boolean codeEnforcement, Integer gisrecordId, Pageable pageable);

    Downloadable exportFormsTaggedWithGISRecords(ExportType exportType, Boolean codeEnforcement, Integer gisrecordId, Pageable pageable);

    Page<CountOfVendorUsersResponse> executeCountOfVendorUsers(Integer vendor, Pageable pageable);

    Downloadable exportCountOfVendorUsers(ExportType exportType, Integer vendor, Pageable pageable);

    Page<GetInspectionDesignByInspectionCategoriesAssignedToFormTypeResponse> executeGetInspectionDesignByInspectionCategoriesAssignedToFormType(Boolean active, Integer formType, Pageable pageable);

    Downloadable exportGetInspectionDesignByInspectionCategoriesAssignedToFormType(ExportType exportType, Boolean active, Integer formType, Pageable pageable);

    Integer executeInsertProjectMessage(InsertProjectMessageRequest insertProjectMessageRequest);

}


