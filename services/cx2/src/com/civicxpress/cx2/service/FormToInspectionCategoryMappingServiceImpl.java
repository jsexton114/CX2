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

import com.civicxpress.cx2.FormToInspectionCategoryMapping;


/**
 * ServiceImpl object for domain model class FormToInspectionCategoryMapping.
 *
 * @see FormToInspectionCategoryMapping
 */
@Service("cx2.FormToInspectionCategoryMappingService")
public class FormToInspectionCategoryMappingServiceImpl implements FormToInspectionCategoryMappingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormToInspectionCategoryMappingServiceImpl.class);


    @Autowired
    @Qualifier("cx2.FormToInspectionCategoryMappingDao")
    private WMGenericDao<FormToInspectionCategoryMapping, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<FormToInspectionCategoryMapping, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public FormToInspectionCategoryMapping create(FormToInspectionCategoryMapping formToInspectionCategoryMapping) {
        LOGGER.debug("Creating a new FormToInspectionCategoryMapping with information: {}", formToInspectionCategoryMapping);
        FormToInspectionCategoryMapping formToInspectionCategoryMappingCreated = this.wmGenericDao.create(formToInspectionCategoryMapping);
        return formToInspectionCategoryMappingCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public FormToInspectionCategoryMapping getById(Integer formtoinspectioncategorymappingId) throws EntityNotFoundException {
        LOGGER.debug("Finding FormToInspectionCategoryMapping by id: {}", formtoinspectioncategorymappingId);
        FormToInspectionCategoryMapping formToInspectionCategoryMapping = this.wmGenericDao.findById(formtoinspectioncategorymappingId);
        if (formToInspectionCategoryMapping == null){
            LOGGER.debug("No FormToInspectionCategoryMapping found with id: {}", formtoinspectioncategorymappingId);
            throw new EntityNotFoundException(String.valueOf(formtoinspectioncategorymappingId));
        }
        return formToInspectionCategoryMapping;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public FormToInspectionCategoryMapping findById(Integer formtoinspectioncategorymappingId) {
        LOGGER.debug("Finding FormToInspectionCategoryMapping by id: {}", formtoinspectioncategorymappingId);
        return this.wmGenericDao.findById(formtoinspectioncategorymappingId);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public FormToInspectionCategoryMapping getByFormTypeIdAndInspectionCategoryId(Integer formTypeId, Integer inspectionCategoryId) {
        Map<String, Object> formTypeIdAndInspectionCategoryIdMap = new HashMap<>();
        formTypeIdAndInspectionCategoryIdMap.put("formTypeId", formTypeId);
        formTypeIdAndInspectionCategoryIdMap.put("inspectionCategoryId", inspectionCategoryId);

        LOGGER.debug("Finding FormToInspectionCategoryMapping by unique keys: {}", formTypeIdAndInspectionCategoryIdMap);
        FormToInspectionCategoryMapping formToInspectionCategoryMapping = this.wmGenericDao.findByUniqueKey(formTypeIdAndInspectionCategoryIdMap);

        if (formToInspectionCategoryMapping == null){
            LOGGER.debug("No FormToInspectionCategoryMapping found with given unique key values: {}", formTypeIdAndInspectionCategoryIdMap);
            throw new EntityNotFoundException(String.valueOf(formTypeIdAndInspectionCategoryIdMap));
        }

        return formToInspectionCategoryMapping;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public FormToInspectionCategoryMapping update(FormToInspectionCategoryMapping formToInspectionCategoryMapping) throws EntityNotFoundException {
        LOGGER.debug("Updating FormToInspectionCategoryMapping with information: {}", formToInspectionCategoryMapping);
        this.wmGenericDao.update(formToInspectionCategoryMapping);

        Integer formtoinspectioncategorymappingId = formToInspectionCategoryMapping.getId();

        return this.wmGenericDao.findById(formtoinspectioncategorymappingId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public FormToInspectionCategoryMapping delete(Integer formtoinspectioncategorymappingId) throws EntityNotFoundException {
        LOGGER.debug("Deleting FormToInspectionCategoryMapping with id: {}", formtoinspectioncategorymappingId);
        FormToInspectionCategoryMapping deleted = this.wmGenericDao.findById(formtoinspectioncategorymappingId);
        if (deleted == null) {
            LOGGER.debug("No FormToInspectionCategoryMapping found with id: {}", formtoinspectioncategorymappingId);
            throw new EntityNotFoundException(String.valueOf(formtoinspectioncategorymappingId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<FormToInspectionCategoryMapping> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all FormToInspectionCategoryMappings");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormToInspectionCategoryMapping> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all FormToInspectionCategoryMappings");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table FormToInspectionCategoryMapping to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }



}

