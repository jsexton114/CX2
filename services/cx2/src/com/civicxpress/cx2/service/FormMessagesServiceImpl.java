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

import com.civicxpress.cx2.FormMessageTagging;
import com.civicxpress.cx2.FormMessages;


/**
 * ServiceImpl object for domain model class FormMessages.
 *
 * @see FormMessages
 */
@Service("cx2.FormMessagesService")
public class FormMessagesServiceImpl implements FormMessagesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormMessagesServiceImpl.class);

    @Autowired
	@Qualifier("cx2.FormMessageTaggingService")
	private FormMessageTaggingService formMessageTaggingService;

    @Autowired
    @Qualifier("cx2.FormMessagesDao")
    private WMGenericDao<FormMessages, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<FormMessages, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public FormMessages create(FormMessages formMessages) {
        LOGGER.debug("Creating a new FormMessages with information: {}", formMessages);
        FormMessages formMessagesCreated = this.wmGenericDao.create(formMessages);
        if(formMessagesCreated.getFormMessageTaggings() != null) {
            for(FormMessageTagging formMessageTagging : formMessagesCreated.getFormMessageTaggings()) {
                formMessageTagging.setFormMessages(formMessagesCreated);
                LOGGER.debug("Creating a new child FormMessageTagging with information: {}", formMessageTagging);
                formMessageTaggingService.create(formMessageTagging);
            }
        }
        return formMessagesCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public FormMessages getById(Integer formmessagesId) throws EntityNotFoundException {
        LOGGER.debug("Finding FormMessages by id: {}", formmessagesId);
        FormMessages formMessages = this.wmGenericDao.findById(formmessagesId);
        if (formMessages == null){
            LOGGER.debug("No FormMessages found with id: {}", formmessagesId);
            throw new EntityNotFoundException(String.valueOf(formmessagesId));
        }
        return formMessages;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public FormMessages findById(Integer formmessagesId) {
        LOGGER.debug("Finding FormMessages by id: {}", formmessagesId);
        return this.wmGenericDao.findById(formmessagesId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public FormMessages update(FormMessages formMessages) throws EntityNotFoundException {
        LOGGER.debug("Updating FormMessages with information: {}", formMessages);
        this.wmGenericDao.update(formMessages);

        Integer formmessagesId = formMessages.getId();

        return this.wmGenericDao.findById(formmessagesId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public FormMessages delete(Integer formmessagesId) throws EntityNotFoundException {
        LOGGER.debug("Deleting FormMessages with id: {}", formmessagesId);
        FormMessages deleted = this.wmGenericDao.findById(formmessagesId);
        if (deleted == null) {
            LOGGER.debug("No FormMessages found with id: {}", formmessagesId);
            throw new EntityNotFoundException(String.valueOf(formmessagesId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<FormMessages> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all FormMessages");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormMessages> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all FormMessages");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table FormMessages to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormMessageTagging> findAssociatedFormMessageTaggings(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated formMessageTaggings");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("formMessages.id = '" + id + "'");

        return formMessageTaggingService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FormMessageTaggingService instance
	 */
	protected void setFormMessageTaggingService(FormMessageTaggingService service) {
        this.formMessageTaggingService = service;
    }

}
