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

import com.civicxpress.cx2.Fees;
import com.civicxpress.cx2.FormMessages;
import com.civicxpress.cx2.FormsToInspections;
import com.civicxpress.cx2.InspectionHistory;
import com.civicxpress.cx2.MasterCases;
import com.civicxpress.cx2.MasterInspections;
import com.civicxpress.cx2.Violations;


/**
 * ServiceImpl object for domain model class MasterInspections.
 *
 * @see MasterInspections
 */
@Service("cx2.MasterInspectionsService")
public class MasterInspectionsServiceImpl implements MasterInspectionsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MasterInspectionsServiceImpl.class);

    @Autowired
	@Qualifier("cx2.FormsToInspectionsService")
	private FormsToInspectionsService formsToInspectionsService;

    @Autowired
	@Qualifier("cx2.FeesService")
	private FeesService feesService;

    @Autowired
	@Qualifier("cx2.FormMessagesService")
	private FormMessagesService formMessagesService;

    @Autowired
	@Qualifier("cx2.InspectionHistoryService")
	private InspectionHistoryService inspectionHistoryService;

    @Autowired
	@Qualifier("cx2.ViolationsService")
	private ViolationsService violationsService;

    @Autowired
	@Qualifier("cx2.MasterCasesService")
	private MasterCasesService masterCasesService;

    @Autowired
    @Qualifier("cx2.MasterInspectionsDao")
    private WMGenericDao<MasterInspections, String> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<MasterInspections, String> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public MasterInspections create(MasterInspections masterInspections) {
        LOGGER.debug("Creating a new MasterInspections with information: {}", masterInspections);
        MasterInspections masterInspectionsCreated = this.wmGenericDao.create(masterInspections);
        if(masterInspectionsCreated.getFeeses() != null) {
            for(Fees feese : masterInspectionsCreated.getFeeses()) {
                feese.setMasterInspections(masterInspectionsCreated);
                LOGGER.debug("Creating a new child Fees with information: {}", feese);
                feesService.create(feese);
            }
        }

        if(masterInspectionsCreated.getFormMessageses() != null) {
            for(FormMessages formMessagese : masterInspectionsCreated.getFormMessageses()) {
                formMessagese.setMasterInspections(masterInspectionsCreated);
                LOGGER.debug("Creating a new child FormMessages with information: {}", formMessagese);
                formMessagesService.create(formMessagese);
            }
        }

        if(masterInspectionsCreated.getFormsToInspectionses() != null) {
            for(FormsToInspections formsToInspectionse : masterInspectionsCreated.getFormsToInspectionses()) {
                formsToInspectionse.setMasterInspections(masterInspectionsCreated);
                LOGGER.debug("Creating a new child FormsToInspections with information: {}", formsToInspectionse);
                formsToInspectionsService.create(formsToInspectionse);
            }
        }

        if(masterInspectionsCreated.getInspectionHistories() != null) {
            for(InspectionHistory inspectionHistorie : masterInspectionsCreated.getInspectionHistories()) {
                inspectionHistorie.setMasterInspections(masterInspectionsCreated);
                LOGGER.debug("Creating a new child InspectionHistory with information: {}", inspectionHistorie);
                inspectionHistoryService.create(inspectionHistorie);
            }
        }

        if(masterInspectionsCreated.getMasterCaseses() != null) {
            for(MasterCases masterCasese : masterInspectionsCreated.getMasterCaseses()) {
                masterCasese.setMasterInspections(masterInspectionsCreated);
                LOGGER.debug("Creating a new child MasterCases with information: {}", masterCasese);
                masterCasesService.create(masterCasese);
            }
        }

        if(masterInspectionsCreated.getViolationses() != null) {
            for(Violations violationse : masterInspectionsCreated.getViolationses()) {
                violationse.setMasterInspections(masterInspectionsCreated);
                LOGGER.debug("Creating a new child Violations with information: {}", violationse);
                violationsService.create(violationse);
            }
        }
        return masterInspectionsCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public MasterInspections getById(String masterinspectionsId) throws EntityNotFoundException {
        LOGGER.debug("Finding MasterInspections by id: {}", masterinspectionsId);
        MasterInspections masterInspections = this.wmGenericDao.findById(masterinspectionsId);
        if (masterInspections == null){
            LOGGER.debug("No MasterInspections found with id: {}", masterinspectionsId);
            throw new EntityNotFoundException(String.valueOf(masterinspectionsId));
        }
        return masterInspections;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public MasterInspections findById(String masterinspectionsId) {
        LOGGER.debug("Finding MasterInspections by id: {}", masterinspectionsId);
        return this.wmGenericDao.findById(masterinspectionsId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public MasterInspections update(MasterInspections masterInspections) throws EntityNotFoundException {
        LOGGER.debug("Updating MasterInspections with information: {}", masterInspections);
        this.wmGenericDao.update(masterInspections);

        String masterinspectionsId = masterInspections.getInspectionGuid();

        return this.wmGenericDao.findById(masterinspectionsId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public MasterInspections delete(String masterinspectionsId) throws EntityNotFoundException {
        LOGGER.debug("Deleting MasterInspections with id: {}", masterinspectionsId);
        MasterInspections deleted = this.wmGenericDao.findById(masterinspectionsId);
        if (deleted == null) {
            LOGGER.debug("No MasterInspections found with id: {}", masterinspectionsId);
            throw new EntityNotFoundException(String.valueOf(masterinspectionsId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<MasterInspections> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all MasterInspections");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<MasterInspections> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all MasterInspections");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table MasterInspections to {} format", exportType);
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
    public Page<Fees> findAssociatedFeeses(String inspectionGuid, Pageable pageable) {
        LOGGER.debug("Fetching all associated feeses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("masterInspections.inspectionGuid = '" + inspectionGuid + "'");

        return feesService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormMessages> findAssociatedFormMessageses(String inspectionGuid, Pageable pageable) {
        LOGGER.debug("Fetching all associated formMessageses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("masterInspections.inspectionGuid = '" + inspectionGuid + "'");

        return formMessagesService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormsToInspections> findAssociatedFormsToInspectionses(String inspectionGuid, Pageable pageable) {
        LOGGER.debug("Fetching all associated formsToInspectionses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("masterInspections.inspectionGuid = '" + inspectionGuid + "'");

        return formsToInspectionsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<InspectionHistory> findAssociatedInspectionHistories(String inspectionGuid, Pageable pageable) {
        LOGGER.debug("Fetching all associated inspectionHistories");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("masterInspections.inspectionGuid = '" + inspectionGuid + "'");

        return inspectionHistoryService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<MasterCases> findAssociatedMasterCaseses(String inspectionGuid, Pageable pageable) {
        LOGGER.debug("Fetching all associated masterCaseses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("masterInspections.inspectionGuid = '" + inspectionGuid + "'");

        return masterCasesService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<Violations> findAssociatedViolationses(String inspectionGuid, Pageable pageable) {
        LOGGER.debug("Fetching all associated violationses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("masterInspections.inspectionGuid = '" + inspectionGuid + "'");

        return violationsService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FormsToInspectionsService instance
	 */
	protected void setFormsToInspectionsService(FormsToInspectionsService service) {
        this.formsToInspectionsService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FeesService instance
	 */
	protected void setFeesService(FeesService service) {
        this.feesService = service;
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
	 * @param service InspectionHistoryService instance
	 */
	protected void setInspectionHistoryService(InspectionHistoryService service) {
        this.inspectionHistoryService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service ViolationsService instance
	 */
	protected void setViolationsService(ViolationsService service) {
        this.violationsService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service MasterCasesService instance
	 */
	protected void setMasterCasesService(MasterCasesService service) {
        this.masterCasesService = service;
    }

}

