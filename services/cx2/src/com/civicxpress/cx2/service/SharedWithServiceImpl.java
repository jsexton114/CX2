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

import com.civicxpress.cx2.SharedWith;


/**
 * ServiceImpl object for domain model class SharedWith.
 *
 * @see SharedWith
 */
@Service("cx2.SharedWithService")
public class SharedWithServiceImpl implements SharedWithService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SharedWithServiceImpl.class);


    @Autowired
    @Qualifier("cx2.SharedWithDao")
    private WMGenericDao<SharedWith, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<SharedWith, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public SharedWith create(SharedWith sharedWith) {
        LOGGER.debug("Creating a new SharedWith with information: {}", sharedWith);
        SharedWith sharedWithCreated = this.wmGenericDao.create(sharedWith);
        return sharedWithCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public SharedWith getById(Integer sharedwithId) throws EntityNotFoundException {
        LOGGER.debug("Finding SharedWith by id: {}", sharedwithId);
        SharedWith sharedWith = this.wmGenericDao.findById(sharedwithId);
        if (sharedWith == null){
            LOGGER.debug("No SharedWith found with id: {}", sharedwithId);
            throw new EntityNotFoundException(String.valueOf(sharedwithId));
        }
        return sharedWith;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public SharedWith findById(Integer sharedwithId) {
        LOGGER.debug("Finding SharedWith by id: {}", sharedwithId);
        return this.wmGenericDao.findById(sharedwithId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public SharedWith update(SharedWith sharedWith) throws EntityNotFoundException {
        LOGGER.debug("Updating SharedWith with information: {}", sharedWith);
        this.wmGenericDao.update(sharedWith);

        Integer sharedwithId = sharedWith.getId();

        return this.wmGenericDao.findById(sharedwithId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public SharedWith delete(Integer sharedwithId) throws EntityNotFoundException {
        LOGGER.debug("Deleting SharedWith with id: {}", sharedwithId);
        SharedWith deleted = this.wmGenericDao.findById(sharedwithId);
        if (deleted == null) {
            LOGGER.debug("No SharedWith found with id: {}", sharedwithId);
            throw new EntityNotFoundException(String.valueOf(sharedwithId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<SharedWith> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all SharedWiths");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<SharedWith> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all SharedWiths");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table SharedWith to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }



}

