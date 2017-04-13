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

import com.civicxpress.cx2.CodesToForm;
import com.civicxpress.cx2.FormCategoryMapping;
import com.civicxpress.cx2.FormDraft;
import com.civicxpress.cx2.FormHistory;
import com.civicxpress.cx2.FormStatuses;
import com.civicxpress.cx2.FormToInspectionCategoryMapping;
import com.civicxpress.cx2.FormTypeFields;
import com.civicxpress.cx2.FormTypes;
import com.civicxpress.cx2.InspectionSequence;
import com.civicxpress.cx2.LetterTemplates;
import com.civicxpress.cx2.MasterForms;


/**
 * ServiceImpl object for domain model class FormTypes.
 *
 * @see FormTypes
 */
@Service("cx2.FormTypesService")
public class FormTypesServiceImpl implements FormTypesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormTypesServiceImpl.class);

    @Autowired
	@Qualifier("cx2.LetterTemplatesService")
	private LetterTemplatesService letterTemplatesService;

    @Autowired
	@Qualifier("cx2.FormStatusesService")
	private FormStatusesService formStatusesService;

    @Autowired
	@Qualifier("cx2.InspectionSequenceService")
	private InspectionSequenceService inspectionSequenceService;

    @Autowired
	@Qualifier("cx2.FormTypeFieldsService")
	private FormTypeFieldsService formTypeFieldsService;

    @Autowired
	@Qualifier("cx2.FormHistoryService")
	private FormHistoryService formHistoryService;

    @Autowired
	@Qualifier("cx2.MasterFormsService")
	private MasterFormsService masterFormsService;

    @Autowired
	@Qualifier("cx2.CodesToFormService")
	private CodesToFormService codesToFormService;

    @Autowired
	@Qualifier("cx2.FormDraftService")
	private FormDraftService formDraftService;

    @Autowired
	@Qualifier("cx2.FormToInspectionCategoryMappingService")
	private FormToInspectionCategoryMappingService formToInspectionCategoryMappingService;

    @Autowired
	@Qualifier("cx2.FormCategoryMappingService")
	private FormCategoryMappingService formCategoryMappingService;

    @Autowired
    @Qualifier("cx2.FormTypesDao")
    private WMGenericDao<FormTypes, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<FormTypes, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public FormTypes create(FormTypes formTypes) {
        LOGGER.debug("Creating a new FormTypes with information: {}", formTypes);
        FormTypes formTypesCreated = this.wmGenericDao.create(formTypes);
        if(formTypesCreated.getCodesToForms() != null) {
            for(CodesToForm codesToForm : formTypesCreated.getCodesToForms()) {
                codesToForm.setFormTypes(formTypesCreated);
                LOGGER.debug("Creating a new child CodesToForm with information: {}", codesToForm);
                codesToFormService.create(codesToForm);
            }
        }

        if(formTypesCreated.getFormCategoryMappings() != null) {
            for(FormCategoryMapping formCategoryMapping : formTypesCreated.getFormCategoryMappings()) {
                formCategoryMapping.setFormTypes(formTypesCreated);
                LOGGER.debug("Creating a new child FormCategoryMapping with information: {}", formCategoryMapping);
                formCategoryMappingService.create(formCategoryMapping);
            }
        }

        if(formTypesCreated.getFormHistories() != null) {
            for(FormHistory formHistorie : formTypesCreated.getFormHistories()) {
                formHistorie.setFormTypes(formTypesCreated);
                LOGGER.debug("Creating a new child FormHistory with information: {}", formHistorie);
                formHistoryService.create(formHistorie);
            }
        }

        if(formTypesCreated.getFormDrafts() != null) {
            for(FormDraft formDraft : formTypesCreated.getFormDrafts()) {
                formDraft.setFormTypes(formTypesCreated);
                LOGGER.debug("Creating a new child FormDraft with information: {}", formDraft);
                formDraftService.create(formDraft);
            }
        }

        if(formTypesCreated.getFormStatuseses() != null) {
            for(FormStatuses formStatusese : formTypesCreated.getFormStatuseses()) {
                formStatusese.setFormTypes(formTypesCreated);
                LOGGER.debug("Creating a new child FormStatuses with information: {}", formStatusese);
                formStatusesService.create(formStatusese);
            }
        }

        if(formTypesCreated.getFormToInspectionCategoryMappings() != null) {
            for(FormToInspectionCategoryMapping formToInspectionCategoryMapping : formTypesCreated.getFormToInspectionCategoryMappings()) {
                formToInspectionCategoryMapping.setFormTypes(formTypesCreated);
                LOGGER.debug("Creating a new child FormToInspectionCategoryMapping with information: {}", formToInspectionCategoryMapping);
                formToInspectionCategoryMappingService.create(formToInspectionCategoryMapping);
            }
        }

        if(formTypesCreated.getFormTypeFieldses() != null) {
            for(FormTypeFields formTypeFieldse : formTypesCreated.getFormTypeFieldses()) {
                formTypeFieldse.setFormTypes(formTypesCreated);
                LOGGER.debug("Creating a new child FormTypeFields with information: {}", formTypeFieldse);
                formTypeFieldsService.create(formTypeFieldse);
            }
        }

        if(formTypesCreated.getLetterTemplateses() != null) {
            for(LetterTemplates letterTemplatese : formTypesCreated.getLetterTemplateses()) {
                letterTemplatese.setFormTypes(formTypesCreated);
                LOGGER.debug("Creating a new child LetterTemplates with information: {}", letterTemplatese);
                letterTemplatesService.create(letterTemplatese);
            }
        }

        if(formTypesCreated.getInspectionSequences() != null) {
            for(InspectionSequence inspectionSequence : formTypesCreated.getInspectionSequences()) {
                inspectionSequence.setFormTypes(formTypesCreated);
                LOGGER.debug("Creating a new child InspectionSequence with information: {}", inspectionSequence);
                inspectionSequenceService.create(inspectionSequence);
            }
        }

        if(formTypesCreated.getMasterFormses() != null) {
            for(MasterForms masterFormse : formTypesCreated.getMasterFormses()) {
                masterFormse.setFormTypes(formTypesCreated);
                LOGGER.debug("Creating a new child MasterForms with information: {}", masterFormse);
                masterFormsService.create(masterFormse);
            }
        }
        return formTypesCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public FormTypes getById(Integer formtypesId) throws EntityNotFoundException {
        LOGGER.debug("Finding FormTypes by id: {}", formtypesId);
        FormTypes formTypes = this.wmGenericDao.findById(formtypesId);
        if (formTypes == null){
            LOGGER.debug("No FormTypes found with id: {}", formtypesId);
            throw new EntityNotFoundException(String.valueOf(formtypesId));
        }
        return formTypes;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public FormTypes findById(Integer formtypesId) {
        LOGGER.debug("Finding FormTypes by id: {}", formtypesId);
        return this.wmGenericDao.findById(formtypesId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public FormTypes update(FormTypes formTypes) throws EntityNotFoundException {
        LOGGER.debug("Updating FormTypes with information: {}", formTypes);
        this.wmGenericDao.update(formTypes);

        Integer formtypesId = formTypes.getId();

        return this.wmGenericDao.findById(formtypesId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public FormTypes delete(Integer formtypesId) throws EntityNotFoundException {
        LOGGER.debug("Deleting FormTypes with id: {}", formtypesId);
        FormTypes deleted = this.wmGenericDao.findById(formtypesId);
        if (deleted == null) {
            LOGGER.debug("No FormTypes found with id: {}", formtypesId);
            throw new EntityNotFoundException(String.valueOf(formtypesId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<FormTypes> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all FormTypes");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormTypes> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all FormTypes");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table FormTypes to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<CodesToForm> findAssociatedCodesToForms(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated codesToForms");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("formTypes.id = '" + id + "'");

        return codesToFormService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormCategoryMapping> findAssociatedFormCategoryMappings(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated formCategoryMappings");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("formTypes.id = '" + id + "'");

        return formCategoryMappingService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormHistory> findAssociatedFormHistories(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated formHistories");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("formTypes.id = '" + id + "'");

        return formHistoryService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormDraft> findAssociatedFormDrafts(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated formDrafts");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("formTypes.id = '" + id + "'");

        return formDraftService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormStatuses> findAssociatedFormStatuseses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated formStatuseses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("formTypes.id = '" + id + "'");

        return formStatusesService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormToInspectionCategoryMapping> findAssociatedFormToInspectionCategoryMappings(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated formToInspectionCategoryMappings");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("formTypes.id = '" + id + "'");

        return formToInspectionCategoryMappingService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<FormTypeFields> findAssociatedFormTypeFieldses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated formTypeFieldses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("formTypes.id = '" + id + "'");

        return formTypeFieldsService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<LetterTemplates> findAssociatedLetterTemplateses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated letterTemplateses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("formTypes.id = '" + id + "'");

        return letterTemplatesService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<InspectionSequence> findAssociatedInspectionSequences(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated inspectionSequences");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("formTypes.id = '" + id + "'");

        return inspectionSequenceService.findAll(queryBuilder.toString(), pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<MasterForms> findAssociatedMasterFormses(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated masterFormses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("formTypes.id = '" + id + "'");

        return masterFormsService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service LetterTemplatesService instance
	 */
	protected void setLetterTemplatesService(LetterTemplatesService service) {
        this.letterTemplatesService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FormStatusesService instance
	 */
	protected void setFormStatusesService(FormStatusesService service) {
        this.formStatusesService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service InspectionSequenceService instance
	 */
	protected void setInspectionSequenceService(InspectionSequenceService service) {
        this.inspectionSequenceService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FormTypeFieldsService instance
	 */
	protected void setFormTypeFieldsService(FormTypeFieldsService service) {
        this.formTypeFieldsService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FormHistoryService instance
	 */
	protected void setFormHistoryService(FormHistoryService service) {
        this.formHistoryService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service MasterFormsService instance
	 */
	protected void setMasterFormsService(MasterFormsService service) {
        this.masterFormsService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service CodesToFormService instance
	 */
	protected void setCodesToFormService(CodesToFormService service) {
        this.codesToFormService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FormDraftService instance
	 */
	protected void setFormDraftService(FormDraftService service) {
        this.formDraftService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FormToInspectionCategoryMappingService instance
	 */
	protected void setFormToInspectionCategoryMappingService(FormToInspectionCategoryMappingService service) {
        this.formToInspectionCategoryMappingService = service;
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FormCategoryMappingService instance
	 */
	protected void setFormCategoryMappingService(FormCategoryMappingService service) {
        this.formCategoryMappingService = service;
    }

}

