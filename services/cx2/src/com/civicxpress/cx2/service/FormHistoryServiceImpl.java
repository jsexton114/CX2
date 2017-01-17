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

import com.civicxpress.cx2.FormHistory;


/**
 * ServiceImpl object for domain model class FormHistory.
 *
 * @see FormHistory
 */
@Service("cx2.FormHistoryService")
public class FormHistoryServiceImpl implements FormHistoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormHistoryServiceImpl.class);


    @Autowired
    @Qualifier("cx2.FormHistoryDao")
    private WMGenericDao<FormHistory, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<FormHistory, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public FormHistory create(FormHistory formHistory) {
        LOGGER.debug("Creating a new FormHistory with information: {}", formHistory);
        FormHistory formHistoryCreated = this.wmGenericDao.create(formHistory);
        return formHistoryCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public FormHistory getById(Integer formhistoryId) throws EntityNotFoundException {
        LOGGER.debug("Finding FormHistory by id: {}", formhistoryId);
        FormHistory formHistory = this.wmGenericDao.findById(formhistoryId);
        if (formHistory == null){
            LOGGER.debug("No FormHistory found with id: {}", formhistoryId);
            throw new EntityNotFoundException(String.valueOf(formhistoryId));
        }
        return formHistory;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public FormHistory findById(Integer formhistoryId) {
        LOGGER.debug("Finding FormHistory by id: {}", formhistoryId);
        return this.wmGenericDao.findById(formhistoryId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public FormHistory update(FormHistory formHistory) throws EntityNotFoundException {
        LOGGER.debug("Updating FormHistory with information: {}", formHistory);
        this.wmGenericDao.update(formHistory);

        Integer formhistoryId = formHistory.getId();

        return this.wmGenericDao.findById(formhistoryId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public FormHistory delete(Integer formhistoryId) throws EntityNotFoundException {
        LOGGER.debug("Deleting FormHistory with id: {}", formhistoryId);
        FormHistory deleted = this.wmGenericDao.findById(formhistoryId);
        if (deleted == null) {
            LOGGER.debug("No FormHistory found with id: {}", formhistoryId);
            throw new EntityNotFoundException(String.valueOf(formhistoryId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<FormHistory> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all FormHistories");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormHistory> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all FormHistories");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table FormHistory to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }



}
