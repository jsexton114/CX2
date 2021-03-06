/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

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

import com.civicxpress.cx2.UserPasswordResetTokens;


/**
 * ServiceImpl object for domain model class UserPasswordResetTokens.
 *
 * @see UserPasswordResetTokens
 */
@Service("cx2.UserPasswordResetTokensService")
public class UserPasswordResetTokensServiceImpl implements UserPasswordResetTokensService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserPasswordResetTokensServiceImpl.class);


    @Autowired
    @Qualifier("cx2.UserPasswordResetTokensDao")
    private WMGenericDao<UserPasswordResetTokens, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<UserPasswordResetTokens, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public UserPasswordResetTokens create(UserPasswordResetTokens userPasswordResetTokens) {
        LOGGER.debug("Creating a new UserPasswordResetTokens with information: {}", userPasswordResetTokens);
        UserPasswordResetTokens userPasswordResetTokensCreated = this.wmGenericDao.create(userPasswordResetTokens);
        return userPasswordResetTokensCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public UserPasswordResetTokens getById(Integer userpasswordresettokensId) throws EntityNotFoundException {
        LOGGER.debug("Finding UserPasswordResetTokens by id: {}", userpasswordresettokensId);
        UserPasswordResetTokens userPasswordResetTokens = this.wmGenericDao.findById(userpasswordresettokensId);
        if (userPasswordResetTokens == null){
            LOGGER.debug("No UserPasswordResetTokens found with id: {}", userpasswordresettokensId);
            throw new EntityNotFoundException(String.valueOf(userpasswordresettokensId));
        }
        return userPasswordResetTokens;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public UserPasswordResetTokens findById(Integer userpasswordresettokensId) {
        LOGGER.debug("Finding UserPasswordResetTokens by id: {}", userpasswordresettokensId);
        return this.wmGenericDao.findById(userpasswordresettokensId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public UserPasswordResetTokens update(UserPasswordResetTokens userPasswordResetTokens) throws EntityNotFoundException {
        LOGGER.debug("Updating UserPasswordResetTokens with information: {}", userPasswordResetTokens);
        this.wmGenericDao.update(userPasswordResetTokens);

        Integer userpasswordresettokensId = userPasswordResetTokens.getId();

        return this.wmGenericDao.findById(userpasswordresettokensId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public UserPasswordResetTokens delete(Integer userpasswordresettokensId) throws EntityNotFoundException {
        LOGGER.debug("Deleting UserPasswordResetTokens with id: {}", userpasswordresettokensId);
        UserPasswordResetTokens deleted = this.wmGenericDao.findById(userpasswordresettokensId);
        if (deleted == null) {
            LOGGER.debug("No UserPasswordResetTokens found with id: {}", userpasswordresettokensId);
            throw new EntityNotFoundException(String.valueOf(userpasswordresettokensId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<UserPasswordResetTokens> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all UserPasswordResetTokens");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<UserPasswordResetTokens> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all UserPasswordResetTokens");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table UserPasswordResetTokens to {} format", exportType);
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

