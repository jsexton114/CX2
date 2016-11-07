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


/**
 * ServiceImpl object for domain model class FormStatuses.
 *
 * @see FormStatuses
 */
@Service("cx2.FormStatusesService")
public class FormStatusesServiceImpl implements FormStatusesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormStatusesServiceImpl.class);


    @Autowired
    @Qualifier("cx2.FormStatusesDao")
    private WMGenericDao<FormStatuses, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<FormStatuses, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public FormStatuses create(FormStatuses formStatuses) {
        LOGGER.debug("Creating a new FormStatuses with information: {}", formStatuses);
        FormStatuses formStatusesCreated = this.wmGenericDao.create(formStatuses);
        return formStatusesCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public FormStatuses getById(Integer formstatusesId) throws EntityNotFoundException {
        LOGGER.debug("Finding FormStatuses by id: {}", formstatusesId);
        FormStatuses formStatuses = this.wmGenericDao.findById(formstatusesId);
        if (formStatuses == null){
            LOGGER.debug("No FormStatuses found with id: {}", formstatusesId);
            throw new EntityNotFoundException(String.valueOf(formstatusesId));
        }
        return formStatuses;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public FormStatuses findById(Integer formstatusesId) {
        LOGGER.debug("Finding FormStatuses by id: {}", formstatusesId);
        return this.wmGenericDao.findById(formstatusesId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public FormStatuses update(FormStatuses formStatuses) throws EntityNotFoundException {
        LOGGER.debug("Updating FormStatuses with information: {}", formStatuses);
        this.wmGenericDao.update(formStatuses);

        Integer formstatusesId = formStatuses.getId();

        return this.wmGenericDao.findById(formstatusesId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public FormStatuses delete(Integer formstatusesId) throws EntityNotFoundException {
        LOGGER.debug("Deleting FormStatuses with id: {}", formstatusesId);
        FormStatuses deleted = this.wmGenericDao.findById(formstatusesId);
        if (deleted == null) {
            LOGGER.debug("No FormStatuses found with id: {}", formstatusesId);
            throw new EntityNotFoundException(String.valueOf(formstatusesId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<FormStatuses> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all FormStatuses");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormStatuses> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all FormStatuses");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table FormStatuses to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }



}

