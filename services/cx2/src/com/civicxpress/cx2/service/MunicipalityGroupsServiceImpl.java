/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


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

import com.civicxpress.cx2.FormStatuses;
import com.civicxpress.cx2.MunicipalityGroupMembers;
import com.civicxpress.cx2.MunicipalityGroups;


/**
 * ServiceImpl object for domain model class MunicipalityGroups.
 *
 * @see MunicipalityGroups
 */
@Service("cx2.MunicipalityGroupsService")
public class MunicipalityGroupsServiceImpl implements MunicipalityGroupsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MunicipalityGroupsServiceImpl.class);

    @Autowired
	@Qualifier("cx2.MunicipalityGroupMembersService")
	private MunicipalityGroupMembersService municipalityGroupMembersService;

    @Autowired
	@Qualifier("cx2.FormStatusesService")
	private FormStatusesService formStatusesService;

    @Autowired
    @Qualifier("cx2.MunicipalityGroupsDao")
    private WMGenericDao<MunicipalityGroups, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<MunicipalityGroups, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public MunicipalityGroups create(MunicipalityGroups municipalityGroups) {
        LOGGER.debug("Creating a new MunicipalityGroups with information: {}", municipalityGroups);
        MunicipalityGroups municipalityGroupsCreated = this.wmGenericDao.create(municipalityGroups);
        if(municipalityGroupsCreated.getMunicipalityGroupMemberses() != null) {
            for(MunicipalityGroupMembers municipalityGroupMemberse : municipalityGroupsCreated.getMunicipalityGroupMemberses()) {
                municipalityGroupMemberse.setMunicipalityGroups(municipalityGroupsCreated);
                LOGGER.debug("Creating a new child MunicipalityGroupMembers with information: {}", municipalityGroupMemberse);
                municipalityGroupMembersService.create(municipalityGroupMemberse);
            }
        }

        if(municipalityGroupsCreated.getFormStatusesesForReadAccess() != null) {
            for(FormStatuses formStatusesesForReadAcces : municipalityGroupsCreated.getFormStatusesesForReadAccess()) {
                formStatusesesForReadAcces.setMunicipalityGroupsByReadAccess(municipalityGroupsCreated);
                LOGGER.debug("Creating a new child FormStatuses with information: {}", formStatusesesForReadAcces);
                formStatusesService.create(formStatusesesForReadAcces);
            }
        }

        if(municipalityGroupsCreated.getFormStatusesesForProcessOwners() != null) {
            for(FormStatuses formStatusesesForProcessOwner : municipalityGroupsCreated.getFormStatusesesForProcessOwners()) {
                formStatusesesForProcessOwner.setMunicipalityGroupsByProcessOwners(municipalityGroupsCreated);
                LOGGER.debug("Creating a new child FormStatuses with information: {}", formStatusesesForProcessOwner);
                formStatusesService.create(formStatusesesForProcessOwner);
            }
        }
        return municipalityGroupsCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public MunicipalityGroups getById(Integer municipalitygroupsId) throws EntityNotFoundException {
        LOGGER.debug("Finding MunicipalityGroups by id: {}", municipalitygroupsId);
        MunicipalityGroups municipalityGroups = this.wmGenericDao.findById(municipalitygroupsId);
        if (municipalityGroups == null){
            LOGGER.debug("No MunicipalityGroups found with id: {}", municipalitygroupsId);
            throw new EntityNotFoundException(String.valueOf(municipalitygroupsId));
        }
        return municipalityGroups;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public MunicipalityGroups findById(Integer municipalitygroupsId) {
        LOGGER.debug("Finding MunicipalityGroups by id: {}", municipalitygroupsId);
        return this.wmGenericDao.findById(municipalitygroupsId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public MunicipalityGroups update(MunicipalityGroups municipalityGroups) throws EntityNotFoundException {
        LOGGER.debug("Updating MunicipalityGroups with information: {}", municipalityGroups);
        this.wmGenericDao.update(municipalityGroups);

        Integer municipalitygroupsId = municipalityGroups.getId();

        return this.wmGenericDao.findById(municipalitygroupsId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public MunicipalityGroups delete(Integer municipalitygroupsId) throws EntityNotFoundException {
        LOGGER.debug("Deleting MunicipalityGroups with id: {}", municipalitygroupsId);
        MunicipalityGroups deleted = this.wmGenericDao.findById(municipalitygroupsId);
        if (deleted == null) {
            LOGGER.debug("No MunicipalityGroups found with id: {}", municipalitygroupsId);
            throw new EntityNotFoundException(String.valueOf(municipalitygroupsId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<MunicipalityGroups> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all MunicipalityGroups");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<MunicipalityGroups> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all MunicipalityGroups");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table MunicipalityGroups to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<MunicipalityGroupMembers> findAssociatedMunicipalityGroupMemberses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated municipalityGroupMemberses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("municipalityGroups.id = '" + id + "'");

        return municipalityGroupMembersService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormStatuses> findAssociatedFormStatusesesForReadAccess(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated formStatusesesForReadAccess");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("municipalityGroupsByReadAccess.id = '" + id + "'");

        return formStatusesService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormStatuses> findAssociatedFormStatusesesForProcessOwners(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated formStatusesesForProcessOwners");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("municipalityGroupsByProcessOwners.id = '" + id + "'");

        return formStatusesService.findAll(queryBuilder.toString(), pageable);
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
	 * @param service FormStatusesService instance
	 */
	protected void setFormStatusesService(FormStatusesService service) {
        this.formStatusesService = service;
    }

}

