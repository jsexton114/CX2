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

import com.civicxpress.cx2.FormMessages;
import com.civicxpress.cx2.Gis2forms;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.SharedWith;
import com.civicxpress.cx2.Vendors2form;


/**
 * ServiceImpl object for domain model class MasterForms.
 *
 * @see MasterForms
 */
@Service("cx2.MasterFormsService")
public class MasterFormsServiceImpl implements MasterFormsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MasterFormsServiceImpl.class);

    @Autowired
	@Qualifier("cx2.Gis2formsService")
	private Gis2formsService gis2formsService;

    @Autowired
	@Qualifier("cx2.FormMessagesService")
	private FormMessagesService formMessagesService;

    @Autowired
	@Qualifier("cx2.Vendors2formService")
	private Vendors2formService vendors2formService;

    @Autowired
	@Qualifier("cx2.SharedWithService")
	private SharedWithService sharedWithService;

    @Autowired
    @Qualifier("cx2.MasterFormsDao")
    private WMGenericDao<MasterForms, String> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<MasterForms, String> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public MasterForms create(MasterForms masterForms) {
        LOGGER.debug("Creating a new MasterForms with information: {}", masterForms);
        MasterForms masterFormsCreated = this.wmGenericDao.create(masterForms);
        if(masterFormsCreated.getFormMessageses() != null) {
            for(FormMessages formMessagese : masterFormsCreated.getFormMessageses()) {
                formMessagese.setMasterForms(masterFormsCreated);
                LOGGER.debug("Creating a new child FormMessages with information: {}", formMessagese);
                formMessagesService.create(formMessagese);
            }
        }

        if(masterFormsCreated.getGis2formses() != null) {
            for(Gis2forms gis2formse : masterFormsCreated.getGis2formses()) {
                gis2formse.setMasterForms(masterFormsCreated);
                LOGGER.debug("Creating a new child Gis2forms with information: {}", gis2formse);
                gis2formsService.create(gis2formse);
            }
        }

        if(masterFormsCreated.getSharedWiths() != null) {
            for(SharedWith sharedWith : masterFormsCreated.getSharedWiths()) {
                sharedWith.setMasterForms(masterFormsCreated);
                LOGGER.debug("Creating a new child SharedWith with information: {}", sharedWith);
                sharedWithService.create(sharedWith);
            }
        }

        if(masterFormsCreated.getVendors2forms() != null) {
            for(Vendors2form vendors2form : masterFormsCreated.getVendors2forms()) {
                vendors2form.setMasterForms(masterFormsCreated);
                LOGGER.debug("Creating a new child Vendors2form with information: {}", vendors2form);
                vendors2formService.create(vendors2form);
            }
        }
        return masterFormsCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public MasterForms getById(String masterformsId) throws EntityNotFoundException {
        LOGGER.debug("Finding MasterForms by id: {}", masterformsId);
        MasterForms masterForms = this.wmGenericDao.findById(masterformsId);
        if (masterForms == null){
            LOGGER.debug("No MasterForms found with id: {}", masterformsId);
            throw new EntityNotFoundException(String.valueOf(masterformsId));
        }
        return masterForms;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public MasterForms findById(String masterformsId) {
        LOGGER.debug("Finding MasterForms by id: {}", masterformsId);
        return this.wmGenericDao.findById(masterformsId);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public MasterForms getByFormGuid(String formGuid) {
        Map<String, Object> formGuidMap = new HashMap<>();
        formGuidMap.put("formGuid", formGuid);

        LOGGER.debug("Finding MasterForms by unique keys: {}", formGuidMap);
        MasterForms masterForms = this.wmGenericDao.findByUniqueKey(formGuidMap);

        if (masterForms == null){
            LOGGER.debug("No MasterForms found with given unique key values: {}", formGuidMap);
            throw new EntityNotFoundException(String.valueOf(formGuidMap));
        }

        return masterForms;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public MasterForms update(MasterForms masterForms) throws EntityNotFoundException {
        LOGGER.debug("Updating MasterForms with information: {}", masterForms);
        this.wmGenericDao.update(masterForms);

        String masterformsId = masterForms.getFormGuid();

        return this.wmGenericDao.findById(masterformsId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public MasterForms delete(String masterformsId) throws EntityNotFoundException {
        LOGGER.debug("Deleting MasterForms with id: {}", masterformsId);
        MasterForms deleted = this.wmGenericDao.findById(masterformsId);
        if (deleted == null) {
            LOGGER.debug("No MasterForms found with id: {}", masterformsId);
            throw new EntityNotFoundException(String.valueOf(masterformsId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<MasterForms> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all MasterForms");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<MasterForms> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all MasterForms");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table MasterForms to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormMessages> findAssociatedFormMessageses(String formGuid, Pageable pageable) {
        LOGGER.debug("Fetching all associated formMessageses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("masterForms.formGuid = '" + formGuid + "'");

        return formMessagesService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Gis2forms> findAssociatedGis2formses(String formGuid, Pageable pageable) {
        LOGGER.debug("Fetching all associated gis2formses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("masterForms.formGuid = '" + formGuid + "'");

        return gis2formsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<SharedWith> findAssociatedSharedWiths(String formGuid, Pageable pageable) {
        LOGGER.debug("Fetching all associated sharedWiths");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("masterForms.formGuid = '" + formGuid + "'");

        return sharedWithService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Vendors2form> findAssociatedVendors2forms(String formGuid, Pageable pageable) {
        LOGGER.debug("Fetching all associated vendors2forms");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("masterForms.formGuid = '" + formGuid + "'");

        return vendors2formService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service Gis2formsService instance
	 */
	protected void setGis2formsService(Gis2formsService service) {
        this.gis2formsService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FormMessagesService instance
	 */
	protected void setFormMessagesService(FormMessagesService service) {
        this.formMessagesService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service Vendors2formService instance
	 */
	protected void setVendors2formService(Vendors2formService service) {
        this.vendors2formService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service SharedWithService instance
	 */
	protected void setSharedWithService(SharedWithService service) {
        this.sharedWithService = service;
    }

}

