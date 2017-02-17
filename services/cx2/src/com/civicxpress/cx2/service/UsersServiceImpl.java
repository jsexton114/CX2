/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wavemaker.runtime.data.dao.WMGenericDao;
import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;

import com.civicxpress.cx2.Fees;
import com.civicxpress.cx2.FormHistory;
import com.civicxpress.cx2.FormMessageTagging;
import com.civicxpress.cx2.FormMessages;
import com.civicxpress.cx2.Gis2forms;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.MunicipalityGroupMembers;
import com.civicxpress.cx2.ProjectForms;
import com.civicxpress.cx2.ProjectGisrecords;
import com.civicxpress.cx2.ProjectSharedWith;
import com.civicxpress.cx2.ProjectTasks;
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.Roles;
import com.civicxpress.cx2.SharedWith;
import com.civicxpress.cx2.UserPasswordResetTokens;
import com.civicxpress.cx2.UserSubscriptions;
import com.civicxpress.cx2.UserViewPreferences;
import com.civicxpress.cx2.Users;
import com.civicxpress.cx2.VendorAdmins;
import com.civicxpress.cx2.VendorUsers;


/**
 * ServiceImpl object for domain model class Users.
 *
 * @see Users
 */
@Service("cx2.UsersService")
public class UsersServiceImpl implements UsersService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Autowired
	@Qualifier("cx2.MunicipalityGroupMembersService")
	private MunicipalityGroupMembersService municipalityGroupMembersService;

    @Autowired
	@Qualifier("cx2.Gis2formsService")
	private Gis2formsService gis2formsService;

    @Autowired
	@Qualifier("cx2.FormMessagesService")
	private FormMessagesService formMessagesService;

    @Autowired
	@Qualifier("cx2.FormMessageTaggingService")
	private FormMessageTaggingService formMessageTaggingService;

    @Autowired
	@Qualifier("cx2.ProjectGisrecordsService")
	private ProjectGisrecordsService projectGisrecordsService;

    @Autowired
	@Qualifier("cx2.SharedWithService")
	private SharedWithService sharedWithService;

    @Autowired
	@Qualifier("cx2.ProjectTasksService")
	private ProjectTasksService projectTasksService;

    @Autowired
	@Qualifier("cx2.ProjectFormsService")
	private ProjectFormsService projectFormsService;

    @Autowired
	@Qualifier("cx2.ProjectsService")
	private ProjectsService projectsService;

    @Autowired
	@Qualifier("cx2.FormHistoryService")
	private FormHistoryService formHistoryService;

    @Autowired
	@Qualifier("cx2.FeesService")
	private FeesService feesService;

    @Autowired
	@Qualifier("cx2.MasterFormsService")
	private MasterFormsService masterFormsService;

    @Autowired
	@Qualifier("cx2.UserPasswordResetTokensService")
	private UserPasswordResetTokensService userPasswordResetTokensService;

    @Autowired
	@Qualifier("cx2.VendorUsersService")
	private VendorUsersService vendorUsersService;

    @Autowired
	@Qualifier("cx2.ProjectSharedWithService")
	private ProjectSharedWithService projectSharedWithService;

    @Autowired
	@Qualifier("cx2.UserViewPreferencesService")
	private UserViewPreferencesService userViewPreferencesService;

    @Autowired
	@Qualifier("cx2.RolesService")
	private RolesService rolesService;

    @Autowired
	@Qualifier("cx2.VendorAdminsService")
	private VendorAdminsService vendorAdminsService;

    @Autowired
	@Qualifier("cx2.UserSubscriptionsService")
	private UserSubscriptionsService userSubscriptionsService;

    @Autowired
    @Qualifier("cx2.UsersDao")
    private WMGenericDao<Users, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<Users, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public Users create(Users users) {
        LOGGER.debug("Creating a new Users with information: {}", users);
        Users usersCreated = this.wmGenericDao.create(users);
        if(usersCreated.getFeeses() != null) {
            for(Fees feese : usersCreated.getFeeses()) {
                feese.setUsers(usersCreated);
                LOGGER.debug("Creating a new child Fees with information: {}", feese);
                feesService.create(feese);
            }
        }

        if(usersCreated.getFormHistories() != null) {
            for(FormHistory formHistorie : usersCreated.getFormHistories()) {
                formHistorie.setUsers(usersCreated);
                LOGGER.debug("Creating a new child FormHistory with information: {}", formHistorie);
                formHistoryService.create(formHistorie);
            }
        }

        if(usersCreated.getFormMessageses() != null) {
            for(FormMessages formMessagese : usersCreated.getFormMessageses()) {
                formMessagese.setUsers(usersCreated);
                LOGGER.debug("Creating a new child FormMessages with information: {}", formMessagese);
                formMessagesService.create(formMessagese);
            }
        }

        if(usersCreated.getFormMessageTaggings() != null) {
            for(FormMessageTagging formMessageTagging : usersCreated.getFormMessageTaggings()) {
                formMessageTagging.setUsers(usersCreated);
                LOGGER.debug("Creating a new child FormMessageTagging with information: {}", formMessageTagging);
                formMessageTaggingService.create(formMessageTagging);
            }
        }

        if(usersCreated.getGis2formses() != null) {
            for(Gis2forms gis2formse : usersCreated.getGis2formses()) {
                gis2formse.setUsers(usersCreated);
                LOGGER.debug("Creating a new child Gis2forms with information: {}", gis2formse);
                gis2formsService.create(gis2formse);
            }
        }

        if(usersCreated.getMasterFormses() != null) {
            for(MasterForms masterFormse : usersCreated.getMasterFormses()) {
                masterFormse.setUsers(usersCreated);
                LOGGER.debug("Creating a new child MasterForms with information: {}", masterFormse);
                masterFormsService.create(masterFormse);
            }
        }

        if(usersCreated.getProjectFormses() != null) {
            for(ProjectForms projectFormse : usersCreated.getProjectFormses()) {
                projectFormse.setUsers(usersCreated);
                LOGGER.debug("Creating a new child ProjectForms with information: {}", projectFormse);
                projectFormsService.create(projectFormse);
            }
        }

        if(usersCreated.getProjectGisrecordses() != null) {
            for(ProjectGisrecords projectGisrecordse : usersCreated.getProjectGisrecordses()) {
                projectGisrecordse.setUsers(usersCreated);
                LOGGER.debug("Creating a new child ProjectGisrecords with information: {}", projectGisrecordse);
                projectGisrecordsService.create(projectGisrecordse);
            }
        }

        if(usersCreated.getProjectsesForCreatedBy() != null) {
            for(Projects projectsesForCreatedBy : usersCreated.getProjectsesForCreatedBy()) {
                projectsesForCreatedBy.setUsersByCreatedBy(usersCreated);
                LOGGER.debug("Creating a new child Projects with information: {}", projectsesForCreatedBy);
                projectsService.create(projectsesForCreatedBy);
            }
        }

        if(usersCreated.getProjectsesForModifiedBy() != null) {
            for(Projects projectsesForModifiedBy : usersCreated.getProjectsesForModifiedBy()) {
                projectsesForModifiedBy.setUsersByModifiedBy(usersCreated);
                LOGGER.debug("Creating a new child Projects with information: {}", projectsesForModifiedBy);
                projectsService.create(projectsesForModifiedBy);
            }
        }

        if(usersCreated.getProjectTaskses() != null) {
            for(ProjectTasks projectTaskse : usersCreated.getProjectTaskses()) {
                projectTaskse.setUsers(usersCreated);
                LOGGER.debug("Creating a new child ProjectTasks with information: {}", projectTaskse);
                projectTasksService.create(projectTaskse);
            }
        }

        if(usersCreated.getProjectSharedWithsForProjectSharedBy() != null) {
            for(ProjectSharedWith projectSharedWithsForProjectSharedBy : usersCreated.getProjectSharedWithsForProjectSharedBy()) {
                projectSharedWithsForProjectSharedBy.setUsersByProjectSharedBy(usersCreated);
                LOGGER.debug("Creating a new child ProjectSharedWith with information: {}", projectSharedWithsForProjectSharedBy);
                projectSharedWithService.create(projectSharedWithsForProjectSharedBy);
            }
        }

        if(usersCreated.getProjectSharedWithsForProjectSharedWithUser() != null) {
            for(ProjectSharedWith projectSharedWithsForProjectSharedWithUser : usersCreated.getProjectSharedWithsForProjectSharedWithUser()) {
                projectSharedWithsForProjectSharedWithUser.setUsersByProjectSharedWithUser(usersCreated);
                LOGGER.debug("Creating a new child ProjectSharedWith with information: {}", projectSharedWithsForProjectSharedWithUser);
                projectSharedWithService.create(projectSharedWithsForProjectSharedWithUser);
            }
        }

        if(usersCreated.getRoleses() != null) {
            for(Roles rolese : usersCreated.getRoleses()) {
                rolese.setUsers(usersCreated);
                LOGGER.debug("Creating a new child Roles with information: {}", rolese);
                rolesService.create(rolese);
            }
        }

        if(usersCreated.getSharedWithsForCreatedBy() != null) {
            for(SharedWith sharedWithsForCreatedBy : usersCreated.getSharedWithsForCreatedBy()) {
                sharedWithsForCreatedBy.setUsersByCreatedBy(usersCreated);
                LOGGER.debug("Creating a new child SharedWith with information: {}", sharedWithsForCreatedBy);
                sharedWithService.create(sharedWithsForCreatedBy);
            }
        }

        if(usersCreated.getSharedWithsForSharedWithUser() != null) {
            for(SharedWith sharedWithsForSharedWithUser : usersCreated.getSharedWithsForSharedWithUser()) {
                sharedWithsForSharedWithUser.setUsersBySharedWithUser(usersCreated);
                LOGGER.debug("Creating a new child SharedWith with information: {}", sharedWithsForSharedWithUser);
                sharedWithService.create(sharedWithsForSharedWithUser);
            }
        }

        if(usersCreated.getMunicipalityGroupMemberses() != null) {
            for(MunicipalityGroupMembers municipalityGroupMemberse : usersCreated.getMunicipalityGroupMemberses()) {
                municipalityGroupMemberse.setUsers(usersCreated);
                LOGGER.debug("Creating a new child MunicipalityGroupMembers with information: {}", municipalityGroupMemberse);
                municipalityGroupMembersService.create(municipalityGroupMemberse);
            }
        }

        if(usersCreated.getUserPasswordResetTokenses() != null) {
            for(UserPasswordResetTokens userPasswordResetTokense : usersCreated.getUserPasswordResetTokenses()) {
                userPasswordResetTokense.setUsers(usersCreated);
                LOGGER.debug("Creating a new child UserPasswordResetTokens with information: {}", userPasswordResetTokense);
                userPasswordResetTokensService.create(userPasswordResetTokense);
            }
        }

        if(usersCreated.getUserSubscriptionses() != null) {
            for(UserSubscriptions userSubscriptionse : usersCreated.getUserSubscriptionses()) {
                userSubscriptionse.setUsers(usersCreated);
                LOGGER.debug("Creating a new child UserSubscriptions with information: {}", userSubscriptionse);
                userSubscriptionsService.create(userSubscriptionse);
            }
        }

        if(usersCreated.getUserViewPreferenceses() != null) {
            for(UserViewPreferences userViewPreferencese : usersCreated.getUserViewPreferenceses()) {
                userViewPreferencese.setUsers(usersCreated);
                LOGGER.debug("Creating a new child UserViewPreferences with information: {}", userViewPreferencese);
                userViewPreferencesService.create(userViewPreferencese);
            }
        }

        if(usersCreated.getVendorAdminses() != null) {
            for(VendorAdmins vendorAdminse : usersCreated.getVendorAdminses()) {
                vendorAdminse.setUsers(usersCreated);
                LOGGER.debug("Creating a new child VendorAdmins with information: {}", vendorAdminse);
                vendorAdminsService.create(vendorAdminse);
            }
        }

        if(usersCreated.getVendorUserses() != null) {
            for(VendorUsers vendorUserse : usersCreated.getVendorUserses()) {
                vendorUserse.setUsers(usersCreated);
                LOGGER.debug("Creating a new child VendorUsers with information: {}", vendorUserse);
                vendorUsersService.create(vendorUserse);
            }
        }
        return usersCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Users getById(Integer usersId) throws EntityNotFoundException {
        LOGGER.debug("Finding Users by id: {}", usersId);
        Users users = this.wmGenericDao.findById(usersId);
        if (users == null){
            LOGGER.debug("No Users found with id: {}", usersId);
            throw new EntityNotFoundException(String.valueOf(usersId));
        }
        return users;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Users findById(Integer usersId) {
        LOGGER.debug("Finding Users by id: {}", usersId);
        return this.wmGenericDao.findById(usersId);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Users getByEmail(String email) {
        Map<String, Object> emailMap = new HashMap<>();
        emailMap.put("email", email);

        LOGGER.debug("Finding Users by unique keys: {}", emailMap);
        Users users = this.wmGenericDao.findByUniqueKey(emailMap);

        if (users == null){
            LOGGER.debug("No Users found with given unique key values: {}", emailMap);
            throw new EntityNotFoundException(String.valueOf(emailMap));
        }

        return users;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public Users update(Users users) throws EntityNotFoundException {
        LOGGER.debug("Updating Users with information: {}", users);
        this.wmGenericDao.update(users);

        Integer usersId = users.getId();

        return this.wmGenericDao.findById(usersId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public Users delete(Integer usersId) throws EntityNotFoundException {
        LOGGER.debug("Deleting Users with id: {}", usersId);
        Users deleted = this.wmGenericDao.findById(usersId);
        if (deleted == null) {
            LOGGER.debug("No Users found with id: {}", usersId);
            throw new EntityNotFoundException(String.valueOf(usersId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<Users> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all Users");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Users> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all Users");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table Users to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Fees> findAssociatedFeeses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated feeses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return feesService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormHistory> findAssociatedFormHistories(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated formHistories");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return formHistoryService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormMessages> findAssociatedFormMessageses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated formMessageses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return formMessagesService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormMessageTagging> findAssociatedFormMessageTaggings(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated formMessageTaggings");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return formMessageTaggingService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Gis2forms> findAssociatedGis2formses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated gis2formses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return gis2formsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<MasterForms> findAssociatedMasterFormses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated masterFormses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return masterFormsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<ProjectForms> findAssociatedProjectFormses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated projectFormses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return projectFormsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<ProjectGisrecords> findAssociatedProjectGisrecordses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated projectGisrecordses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return projectGisrecordsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Projects> findAssociatedProjectsesForCreatedBy(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated projectsesForCreatedBy");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("usersByCreatedBy.id = '" + id + "'");

        return projectsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Projects> findAssociatedProjectsesForModifiedBy(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated projectsesForModifiedBy");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("usersByModifiedBy.id = '" + id + "'");

        return projectsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<ProjectTasks> findAssociatedProjectTaskses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated projectTaskses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return projectTasksService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<ProjectSharedWith> findAssociatedProjectSharedWithsForProjectSharedBy(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated projectSharedWithsForProjectSharedBy");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("usersByProjectSharedBy.id = '" + id + "'");

        return projectSharedWithService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<ProjectSharedWith> findAssociatedProjectSharedWithsForProjectSharedWithUser(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated projectSharedWithsForProjectSharedWithUser");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("usersByProjectSharedWithUser.id = '" + id + "'");

        return projectSharedWithService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Roles> findAssociatedRoleses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated roleses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return rolesService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<SharedWith> findAssociatedSharedWithsForCreatedBy(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated sharedWithsForCreatedBy");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("usersByCreatedBy.id = '" + id + "'");

        return sharedWithService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<SharedWith> findAssociatedSharedWithsForSharedWithUser(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated sharedWithsForSharedWithUser");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("usersBySharedWithUser.id = '" + id + "'");

        return sharedWithService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<MunicipalityGroupMembers> findAssociatedMunicipalityGroupMemberses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated municipalityGroupMemberses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return municipalityGroupMembersService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<UserPasswordResetTokens> findAssociatedUserPasswordResetTokenses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated userPasswordResetTokenses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return userPasswordResetTokensService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<UserSubscriptions> findAssociatedUserSubscriptionses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated userSubscriptionses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return userSubscriptionsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<UserViewPreferences> findAssociatedUserViewPreferenceses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated userViewPreferenceses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return userViewPreferencesService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<VendorAdmins> findAssociatedVendorAdminses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated vendorAdminses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return vendorAdminsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<VendorUsers> findAssociatedVendorUserses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated vendorUserses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("users.id = '" + id + "'");

        return vendorUsersService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service MunicipalityGroupMembersService instance
	 */
	protected void setMunicipalityGroupMembersService(MunicipalityGroupMembersService service) {
        this.municipalityGroupMembersService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service Gis2formsService instance
	 */
	protected void setGis2formsService(Gis2formsService service) {
        this.gis2formsService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FormMessagesService instance
	 */
	protected void setFormMessagesService(FormMessagesService service) {
        this.formMessagesService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FormMessageTaggingService instance
	 */
	protected void setFormMessageTaggingService(FormMessageTaggingService service) {
        this.formMessageTaggingService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service ProjectGisrecordsService instance
	 */
	protected void setProjectGisrecordsService(ProjectGisrecordsService service) {
        this.projectGisrecordsService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service SharedWithService instance
	 */
	protected void setSharedWithService(SharedWithService service) {
        this.sharedWithService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service ProjectTasksService instance
	 */
	protected void setProjectTasksService(ProjectTasksService service) {
        this.projectTasksService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service ProjectFormsService instance
	 */
	protected void setProjectFormsService(ProjectFormsService service) {
        this.projectFormsService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service ProjectsService instance
	 */
	protected void setProjectsService(ProjectsService service) {
        this.projectsService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FormHistoryService instance
	 */
	protected void setFormHistoryService(FormHistoryService service) {
        this.formHistoryService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FeesService instance
	 */
	protected void setFeesService(FeesService service) {
        this.feesService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service MasterFormsService instance
	 */
	protected void setMasterFormsService(MasterFormsService service) {
        this.masterFormsService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service UserPasswordResetTokensService instance
	 */
	protected void setUserPasswordResetTokensService(UserPasswordResetTokensService service) {
        this.userPasswordResetTokensService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service VendorUsersService instance
	 */
	protected void setVendorUsersService(VendorUsersService service) {
        this.vendorUsersService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service ProjectSharedWithService instance
	 */
	protected void setProjectSharedWithService(ProjectSharedWithService service) {
        this.projectSharedWithService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service UserViewPreferencesService instance
	 */
	protected void setUserViewPreferencesService(UserViewPreferencesService service) {
        this.userViewPreferencesService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service RolesService instance
	 */
	protected void setRolesService(RolesService service) {
        this.rolesService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service VendorAdminsService instance
	 */
	protected void setVendorAdminsService(VendorAdminsService service) {
        this.vendorAdminsService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service UserSubscriptionsService instance
	 */
	protected void setUserSubscriptionsService(UserSubscriptionsService service) {
        this.userSubscriptionsService = service;
    }

}

