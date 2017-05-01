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

import com.civicxpress.cx2.LetterTemplateToFormStatus;


/**
 * ServiceImpl object for domain model class LetterTemplateToFormStatus.
 *
 * @see LetterTemplateToFormStatus
 */
@Service("cx2.LetterTemplateToFormStatusService")
public class LetterTemplateToFormStatusServiceImpl implements LetterTemplateToFormStatusService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LetterTemplateToFormStatusServiceImpl.class);


    @Autowired
    @Qualifier("cx2.LetterTemplateToFormStatusDao")
    private WMGenericDao<LetterTemplateToFormStatus, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<LetterTemplateToFormStatus, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public LetterTemplateToFormStatus create(LetterTemplateToFormStatus letterTemplateToFormStatus) {
        LOGGER.debug("Creating a new LetterTemplateToFormStatus with information: {}", letterTemplateToFormStatus);
        LetterTemplateToFormStatus letterTemplateToFormStatusCreated = this.wmGenericDao.create(letterTemplateToFormStatus);
        return letterTemplateToFormStatusCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public LetterTemplateToFormStatus getById(Integer lettertemplatetoformstatusId) throws EntityNotFoundException {
        LOGGER.debug("Finding LetterTemplateToFormStatus by id: {}", lettertemplatetoformstatusId);
        LetterTemplateToFormStatus letterTemplateToFormStatus = this.wmGenericDao.findById(lettertemplatetoformstatusId);
        if (letterTemplateToFormStatus == null){
            LOGGER.debug("No LetterTemplateToFormStatus found with id: {}", lettertemplatetoformstatusId);
            throw new EntityNotFoundException(String.valueOf(lettertemplatetoformstatusId));
        }
        return letterTemplateToFormStatus;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public LetterTemplateToFormStatus findById(Integer lettertemplatetoformstatusId) {
        LOGGER.debug("Finding LetterTemplateToFormStatus by id: {}", lettertemplatetoformstatusId);
        return this.wmGenericDao.findById(lettertemplatetoformstatusId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public LetterTemplateToFormStatus update(LetterTemplateToFormStatus letterTemplateToFormStatus) throws EntityNotFoundException {
        LOGGER.debug("Updating LetterTemplateToFormStatus with information: {}", letterTemplateToFormStatus);
        this.wmGenericDao.update(letterTemplateToFormStatus);

        Integer lettertemplatetoformstatusId = letterTemplateToFormStatus.getId();

        return this.wmGenericDao.findById(lettertemplatetoformstatusId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public LetterTemplateToFormStatus delete(Integer lettertemplatetoformstatusId) throws EntityNotFoundException {
        LOGGER.debug("Deleting LetterTemplateToFormStatus with id: {}", lettertemplatetoformstatusId);
        LetterTemplateToFormStatus deleted = this.wmGenericDao.findById(lettertemplatetoformstatusId);
        if (deleted == null) {
            LOGGER.debug("No LetterTemplateToFormStatus found with id: {}", lettertemplatetoformstatusId);
            throw new EntityNotFoundException(String.valueOf(lettertemplatetoformstatusId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<LetterTemplateToFormStatus> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all LetterTemplateToFormStatuses");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<LetterTemplateToFormStatus> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all LetterTemplateToFormStatuses");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table LetterTemplateToFormStatus to {} format", exportType);
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

