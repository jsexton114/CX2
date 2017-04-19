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

import com.civicxpress.cx2.LetterTemplates;


/**
 * ServiceImpl object for domain model class LetterTemplates.
 *
 * @see LetterTemplates
 */
@Service("cx2.LetterTemplatesService")
public class LetterTemplatesServiceImpl implements LetterTemplatesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LetterTemplatesServiceImpl.class);


    @Autowired
    @Qualifier("cx2.LetterTemplatesDao")
    private WMGenericDao<LetterTemplates, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<LetterTemplates, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public LetterTemplates create(LetterTemplates letterTemplates) {
        LOGGER.debug("Creating a new LetterTemplates with information: {}", letterTemplates);
        LetterTemplates letterTemplatesCreated = this.wmGenericDao.create(letterTemplates);
        return letterTemplatesCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public LetterTemplates getById(Integer lettertemplatesId) throws EntityNotFoundException {
        LOGGER.debug("Finding LetterTemplates by id: {}", lettertemplatesId);
        LetterTemplates letterTemplates = this.wmGenericDao.findById(lettertemplatesId);
        if (letterTemplates == null){
            LOGGER.debug("No LetterTemplates found with id: {}", lettertemplatesId);
            throw new EntityNotFoundException(String.valueOf(lettertemplatesId));
        }
        return letterTemplates;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public LetterTemplates findById(Integer lettertemplatesId) {
        LOGGER.debug("Finding LetterTemplates by id: {}", lettertemplatesId);
        return this.wmGenericDao.findById(lettertemplatesId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public LetterTemplates update(LetterTemplates letterTemplates) throws EntityNotFoundException {
        LOGGER.debug("Updating LetterTemplates with information: {}", letterTemplates);
        this.wmGenericDao.update(letterTemplates);

        Integer lettertemplatesId = letterTemplates.getId();

        return this.wmGenericDao.findById(lettertemplatesId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public LetterTemplates delete(Integer lettertemplatesId) throws EntityNotFoundException {
        LOGGER.debug("Deleting LetterTemplates with id: {}", lettertemplatesId);
        LetterTemplates deleted = this.wmGenericDao.findById(lettertemplatesId);
        if (deleted == null) {
            LOGGER.debug("No LetterTemplates found with id: {}", lettertemplatesId);
            throw new EntityNotFoundException(String.valueOf(lettertemplatesId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<LetterTemplates> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all LetterTemplates");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<LetterTemplates> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all LetterTemplates");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table LetterTemplates to {} format", exportType);
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

