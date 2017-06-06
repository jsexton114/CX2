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
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.civicxpress.cx2.InspectionHistory;
import com.civicxpress.cx2.InspectionOutcome;
import com.civicxpress.cx2.InspectionOutcomeFee;
import com.civicxpress.cx2.LetterTemplateToInspectionOutcome;
import com.civicxpress.cx2.MasterInspections;


/**
 * ServiceImpl object for domain model class InspectionOutcome.
 *
 * @see InspectionOutcome
 */
@Service("cx2.InspectionOutcomeService")
public class InspectionOutcomeServiceImpl implements InspectionOutcomeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InspectionOutcomeServiceImpl.class);

    @Autowired
	@Qualifier("cx2.InspectionOutcomeFeeService")
	private InspectionOutcomeFeeService inspectionOutcomeFeeService;

    @Autowired
	@Qualifier("cx2.InspectionHistoryService")
	private InspectionHistoryService inspectionHistoryService;

    @Autowired
	@Qualifier("cx2.MasterInspectionsService")
	private MasterInspectionsService masterInspectionsService;

    @Autowired
	@Qualifier("cx2.LetterTemplateToInspectionOutcomeService")
	private LetterTemplateToInspectionOutcomeService letterTemplateToInspectionOutcomeService;

    @Autowired
    @Qualifier("cx2.InspectionOutcomeDao")
    private WMGenericDao<InspectionOutcome, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<InspectionOutcome, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public InspectionOutcome create(InspectionOutcome inspectionOutcome) {
        LOGGER.debug("Creating a new InspectionOutcome with information: {}", inspectionOutcome);
        InspectionOutcome inspectionOutcomeCreated = this.wmGenericDao.create(inspectionOutcome);
        if(inspectionOutcomeCreated.getInspectionHistoriesForNewOutcomeId() != null) {
            for(InspectionHistory inspectionHistoriesForNewOutcomeId : inspectionOutcomeCreated.getInspectionHistoriesForNewOutcomeId()) {
                inspectionHistoriesForNewOutcomeId.setInspectionOutcomeByNewOutcomeId(inspectionOutcomeCreated);
                LOGGER.debug("Creating a new child InspectionHistory with information: {}", inspectionHistoriesForNewOutcomeId);
                inspectionHistoryService.create(inspectionHistoriesForNewOutcomeId);
            }
        }

        if(inspectionOutcomeCreated.getInspectionHistoriesForOldOutcomeId() != null) {
            for(InspectionHistory inspectionHistoriesForOldOutcomeId : inspectionOutcomeCreated.getInspectionHistoriesForOldOutcomeId()) {
                inspectionHistoriesForOldOutcomeId.setInspectionOutcomeByOldOutcomeId(inspectionOutcomeCreated);
                LOGGER.debug("Creating a new child InspectionHistory with information: {}", inspectionHistoriesForOldOutcomeId);
                inspectionHistoryService.create(inspectionHistoriesForOldOutcomeId);
            }
        }

        if(inspectionOutcomeCreated.getInspectionOutcomeFees() != null) {
            for(InspectionOutcomeFee inspectionOutcomeFee : inspectionOutcomeCreated.getInspectionOutcomeFees()) {
                inspectionOutcomeFee.setInspectionOutcome(inspectionOutcomeCreated);
                LOGGER.debug("Creating a new child InspectionOutcomeFee with information: {}", inspectionOutcomeFee);
                inspectionOutcomeFeeService.create(inspectionOutcomeFee);
            }
        }

        if(inspectionOutcomeCreated.getLetterTemplateToInspectionOutcomes() != null) {
            for(LetterTemplateToInspectionOutcome letterTemplateToInspectionOutcome : inspectionOutcomeCreated.getLetterTemplateToInspectionOutcomes()) {
                letterTemplateToInspectionOutcome.setInspectionOutcome(inspectionOutcomeCreated);
                LOGGER.debug("Creating a new child LetterTemplateToInspectionOutcome with information: {}", letterTemplateToInspectionOutcome);
                letterTemplateToInspectionOutcomeService.create(letterTemplateToInspectionOutcome);
            }
        }

        if(inspectionOutcomeCreated.getMasterInspectionses() != null) {
            for(MasterInspections masterInspectionse : inspectionOutcomeCreated.getMasterInspectionses()) {
                masterInspectionse.setInspectionOutcome(inspectionOutcomeCreated);
                LOGGER.debug("Creating a new child MasterInspections with information: {}", masterInspectionse);
                masterInspectionsService.create(masterInspectionse);
            }
        }
        return inspectionOutcomeCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public InspectionOutcome getById(Integer inspectionoutcomeId) throws EntityNotFoundException {
        LOGGER.debug("Finding InspectionOutcome by id: {}", inspectionoutcomeId);
        InspectionOutcome inspectionOutcome = this.wmGenericDao.findById(inspectionoutcomeId);
        if (inspectionOutcome == null){
            LOGGER.debug("No InspectionOutcome found with id: {}", inspectionoutcomeId);
            throw new EntityNotFoundException(String.valueOf(inspectionoutcomeId));
        }
        return inspectionOutcome;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public InspectionOutcome findById(Integer inspectionoutcomeId) {
        LOGGER.debug("Finding InspectionOutcome by id: {}", inspectionoutcomeId);
        return this.wmGenericDao.findById(inspectionoutcomeId);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public InspectionOutcome getByInspectDesignIdAndOutcomeOrder(Integer inspectDesignId, Integer outcomeOrder) {
        Map<String, Object> inspectDesignIdAndOutcomeOrderMap = new HashMap<>();
        inspectDesignIdAndOutcomeOrderMap.put("inspectDesignId", inspectDesignId);
        inspectDesignIdAndOutcomeOrderMap.put("outcomeOrder", outcomeOrder);

        LOGGER.debug("Finding InspectionOutcome by unique keys: {}", inspectDesignIdAndOutcomeOrderMap);
        InspectionOutcome inspectionOutcome = this.wmGenericDao.findByUniqueKey(inspectDesignIdAndOutcomeOrderMap);

        if (inspectionOutcome == null){
            LOGGER.debug("No InspectionOutcome found with given unique key values: {}", inspectDesignIdAndOutcomeOrderMap);
            throw new EntityNotFoundException(String.valueOf(inspectDesignIdAndOutcomeOrderMap));
        }

        return inspectionOutcome;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public InspectionOutcome update(InspectionOutcome inspectionOutcome) throws EntityNotFoundException {
        LOGGER.debug("Updating InspectionOutcome with information: {}", inspectionOutcome);
        this.wmGenericDao.update(inspectionOutcome);

        Integer inspectionoutcomeId = inspectionOutcome.getId();

        return this.wmGenericDao.findById(inspectionoutcomeId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public InspectionOutcome delete(Integer inspectionoutcomeId) throws EntityNotFoundException {
        LOGGER.debug("Deleting InspectionOutcome with id: {}", inspectionoutcomeId);
        InspectionOutcome deleted = this.wmGenericDao.findById(inspectionoutcomeId);
        if (deleted == null) {
            LOGGER.debug("No InspectionOutcome found with id: {}", inspectionoutcomeId);
            throw new EntityNotFoundException(String.valueOf(inspectionoutcomeId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<InspectionOutcome> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all InspectionOutcomes");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<InspectionOutcome> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all InspectionOutcomes");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table InspectionOutcome to {} format", exportType);
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
    public Page<InspectionHistory> findAssociatedInspectionHistoriesForNewOutcomeId(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated inspectionHistoriesForNewOutcomeId");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("inspectionOutcomeByNewOutcomeId.id = '" + id + "'");

        return inspectionHistoryService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<InspectionHistory> findAssociatedInspectionHistoriesForOldOutcomeId(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated inspectionHistoriesForOldOutcomeId");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("inspectionOutcomeByOldOutcomeId.id = '" + id + "'");

        return inspectionHistoryService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<InspectionOutcomeFee> findAssociatedInspectionOutcomeFees(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated inspectionOutcomeFees");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("inspectionOutcome.id = '" + id + "'");

        return inspectionOutcomeFeeService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<LetterTemplateToInspectionOutcome> findAssociatedLetterTemplateToInspectionOutcomes(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated letterTemplateToInspectionOutcomes");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("inspectionOutcome.id = '" + id + "'");

        return letterTemplateToInspectionOutcomeService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<MasterInspections> findAssociatedMasterInspectionses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated masterInspectionses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("inspectionOutcome.id = '" + id + "'");

        return masterInspectionsService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service InspectionOutcomeFeeService instance
	 */
	protected void setInspectionOutcomeFeeService(InspectionOutcomeFeeService service) {
        this.inspectionOutcomeFeeService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service InspectionHistoryService instance
	 */
	protected void setInspectionHistoryService(InspectionHistoryService service) {
        this.inspectionHistoryService = service;
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
	 * @param service LetterTemplateToInspectionOutcomeService instance
	 */
	protected void setLetterTemplateToInspectionOutcomeService(LetterTemplateToInspectionOutcomeService service) {
        this.letterTemplateToInspectionOutcomeService = service;
    }

}

