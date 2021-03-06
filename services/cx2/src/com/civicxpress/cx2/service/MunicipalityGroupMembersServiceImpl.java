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
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.civicxpress.cx2.MunicipalityGroupMembers;


/**
 * ServiceImpl object for domain model class MunicipalityGroupMembers.
 *
 * @see MunicipalityGroupMembers
 */
@Service("cx2.MunicipalityGroupMembersService")
public class MunicipalityGroupMembersServiceImpl implements MunicipalityGroupMembersService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MunicipalityGroupMembersServiceImpl.class);


    @Autowired
    @Qualifier("cx2.MunicipalityGroupMembersDao")
    private WMGenericDao<MunicipalityGroupMembers, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<MunicipalityGroupMembers, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public MunicipalityGroupMembers create(MunicipalityGroupMembers municipalityGroupMembers) {
        LOGGER.debug("Creating a new MunicipalityGroupMembers with information: {}", municipalityGroupMembers);
        MunicipalityGroupMembers municipalityGroupMembersCreated = this.wmGenericDao.create(municipalityGroupMembers);
        return municipalityGroupMembersCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public MunicipalityGroupMembers getById(Integer municipalitygroupmembersId) throws EntityNotFoundException {
        LOGGER.debug("Finding MunicipalityGroupMembers by id: {}", municipalitygroupmembersId);
        MunicipalityGroupMembers municipalityGroupMembers = this.wmGenericDao.findById(municipalitygroupmembersId);
        if (municipalityGroupMembers == null){
            LOGGER.debug("No MunicipalityGroupMembers found with id: {}", municipalitygroupmembersId);
            throw new EntityNotFoundException(String.valueOf(municipalitygroupmembersId));
        }
        return municipalityGroupMembers;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public MunicipalityGroupMembers findById(Integer municipalitygroupmembersId) {
        LOGGER.debug("Finding MunicipalityGroupMembers by id: {}", municipalitygroupmembersId);
        return this.wmGenericDao.findById(municipalitygroupmembersId);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public MunicipalityGroupMembers getByMunicipalityGroupIdAndUserId(Integer municipalityGroupId, Integer userId) {
        Map<String, Object> municipalityGroupIdAndUserIdMap = new HashMap<>();
        municipalityGroupIdAndUserIdMap.put("municipalityGroupId", municipalityGroupId);
        municipalityGroupIdAndUserIdMap.put("userId", userId);

        LOGGER.debug("Finding MunicipalityGroupMembers by unique keys: {}", municipalityGroupIdAndUserIdMap);
        MunicipalityGroupMembers municipalityGroupMembers = this.wmGenericDao.findByUniqueKey(municipalityGroupIdAndUserIdMap);

        if (municipalityGroupMembers == null){
            LOGGER.debug("No MunicipalityGroupMembers found with given unique key values: {}", municipalityGroupIdAndUserIdMap);
            throw new EntityNotFoundException(String.valueOf(municipalityGroupIdAndUserIdMap));
        }

        return municipalityGroupMembers;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public MunicipalityGroupMembers update(MunicipalityGroupMembers municipalityGroupMembers) throws EntityNotFoundException {
        LOGGER.debug("Updating MunicipalityGroupMembers with information: {}", municipalityGroupMembers);
        this.wmGenericDao.update(municipalityGroupMembers);

        Integer municipalitygroupmembersId = municipalityGroupMembers.getId();

        return this.wmGenericDao.findById(municipalitygroupmembersId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public MunicipalityGroupMembers delete(Integer municipalitygroupmembersId) throws EntityNotFoundException {
        LOGGER.debug("Deleting MunicipalityGroupMembers with id: {}", municipalitygroupmembersId);
        MunicipalityGroupMembers deleted = this.wmGenericDao.findById(municipalitygroupmembersId);
        if (deleted == null) {
            LOGGER.debug("No MunicipalityGroupMembers found with id: {}", municipalitygroupmembersId);
            throw new EntityNotFoundException(String.valueOf(municipalitygroupmembersId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<MunicipalityGroupMembers> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all MunicipalityGroupMembers");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<MunicipalityGroupMembers> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all MunicipalityGroupMembers");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table MunicipalityGroupMembers to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
    public Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable) {
        return this.wmGenericDao.getAggregatedValues(aggregationInfo, pageable);
    }



}

