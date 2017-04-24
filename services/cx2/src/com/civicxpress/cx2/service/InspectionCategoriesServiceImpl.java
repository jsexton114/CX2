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

import com.civicxpress.cx2.FormToInspectionCategoryMapping;
import com.civicxpress.cx2.InspectionCategories;
import com.civicxpress.cx2.InspectionCategoryMapping;
import com.civicxpress.cx2.MasterInspections;


/**
 * ServiceImpl object for domain model class InspectionCategories.
 *
 * @see InspectionCategories
 */
@Service("cx2.InspectionCategoriesService")
public class InspectionCategoriesServiceImpl implements InspectionCategoriesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InspectionCategoriesServiceImpl.class);

    @Autowired
	@Qualifier("cx2.InspectionCategoryMappingService")
	private InspectionCategoryMappingService inspectionCategoryMappingService;

    @Autowired
	@Qualifier("cx2.MasterInspectionsService")
	private MasterInspectionsService masterInspectionsService;

    @Autowired
	@Qualifier("cx2.FormToInspectionCategoryMappingService")
	private FormToInspectionCategoryMappingService formToInspectionCategoryMappingService;

    @Autowired
    @Qualifier("cx2.InspectionCategoriesDao")
    private WMGenericDao<InspectionCategories, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<InspectionCategories, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public InspectionCategories create(InspectionCategories inspectionCategories) {
        LOGGER.debug("Creating a new InspectionCategories with information: {}", inspectionCategories);
        InspectionCategories inspectionCategoriesCreated = this.wmGenericDao.create(inspectionCategories);
        if(inspectionCategoriesCreated.getFormToInspectionCategoryMappings() != null) {
            for(FormToInspectionCategoryMapping formToInspectionCategoryMapping : inspectionCategoriesCreated.getFormToInspectionCategoryMappings()) {
                formToInspectionCategoryMapping.setInspectionCategories(inspectionCategoriesCreated);
                LOGGER.debug("Creating a new child FormToInspectionCategoryMapping with information: {}", formToInspectionCategoryMapping);
                formToInspectionCategoryMappingService.create(formToInspectionCategoryMapping);
            }
        }

        if(inspectionCategoriesCreated.getInspectionCategoryMappings() != null) {
            for(InspectionCategoryMapping inspectionCategoryMapping : inspectionCategoriesCreated.getInspectionCategoryMappings()) {
                inspectionCategoryMapping.setInspectionCategories(inspectionCategoriesCreated);
                LOGGER.debug("Creating a new child InspectionCategoryMapping with information: {}", inspectionCategoryMapping);
                inspectionCategoryMappingService.create(inspectionCategoryMapping);
            }
        }

        if(inspectionCategoriesCreated.getMasterInspectionses() != null) {
            for(MasterInspections masterInspectionse : inspectionCategoriesCreated.getMasterInspectionses()) {
                masterInspectionse.setInspectionCategories(inspectionCategoriesCreated);
                LOGGER.debug("Creating a new child MasterInspections with information: {}", masterInspectionse);
                masterInspectionsService.create(masterInspectionse);
            }
        }
        return inspectionCategoriesCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public InspectionCategories getById(Integer inspectioncategoriesId) throws EntityNotFoundException {
        LOGGER.debug("Finding InspectionCategories by id: {}", inspectioncategoriesId);
        InspectionCategories inspectionCategories = this.wmGenericDao.findById(inspectioncategoriesId);
        if (inspectionCategories == null){
            LOGGER.debug("No InspectionCategories found with id: {}", inspectioncategoriesId);
            throw new EntityNotFoundException(String.valueOf(inspectioncategoriesId));
        }
        return inspectionCategories;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public InspectionCategories findById(Integer inspectioncategoriesId) {
        LOGGER.debug("Finding InspectionCategories by id: {}", inspectioncategoriesId);
        return this.wmGenericDao.findById(inspectioncategoriesId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public InspectionCategories update(InspectionCategories inspectionCategories) throws EntityNotFoundException {
        LOGGER.debug("Updating InspectionCategories with information: {}", inspectionCategories);
        this.wmGenericDao.update(inspectionCategories);

        Integer inspectioncategoriesId = inspectionCategories.getId();

        return this.wmGenericDao.findById(inspectioncategoriesId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public InspectionCategories delete(Integer inspectioncategoriesId) throws EntityNotFoundException {
        LOGGER.debug("Deleting InspectionCategories with id: {}", inspectioncategoriesId);
        InspectionCategories deleted = this.wmGenericDao.findById(inspectioncategoriesId);
        if (deleted == null) {
            LOGGER.debug("No InspectionCategories found with id: {}", inspectioncategoriesId);
            throw new EntityNotFoundException(String.valueOf(inspectioncategoriesId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<InspectionCategories> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all InspectionCategories");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<InspectionCategories> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all InspectionCategories");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table InspectionCategories to {} format", exportType);
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

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormToInspectionCategoryMapping> findAssociatedFormToInspectionCategoryMappings(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated formToInspectionCategoryMappings");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("inspectionCategories.id = '" + id + "'");

        return formToInspectionCategoryMappingService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<InspectionCategoryMapping> findAssociatedInspectionCategoryMappings(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated inspectionCategoryMappings");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("inspectionCategories.id = '" + id + "'");

        return inspectionCategoryMappingService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<MasterInspections> findAssociatedMasterInspectionses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated masterInspectionses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("inspectionCategories.id = '" + id + "'");

        return masterInspectionsService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service InspectionCategoryMappingService instance
	 */
	protected void setInspectionCategoryMappingService(InspectionCategoryMappingService service) {
        this.inspectionCategoryMappingService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service MasterInspectionsService instance
	 */
	protected void setMasterInspectionsService(MasterInspectionsService service) {
        this.masterInspectionsService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FormToInspectionCategoryMappingService instance
	 */
	protected void setFormToInspectionCategoryMappingService(FormToInspectionCategoryMappingService service) {
        this.formToInspectionCategoryMappingService = service;
    }

}

