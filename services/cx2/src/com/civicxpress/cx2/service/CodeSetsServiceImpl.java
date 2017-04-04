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

import com.civicxpress.cx2.CodeList;
import com.civicxpress.cx2.CodeSets;


/**
 * ServiceImpl object for domain model class CodeSets.
 *
 * @see CodeSets
 */
@Service("cx2.CodeSetsService")
public class CodeSetsServiceImpl implements CodeSetsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeSetsServiceImpl.class);

    @Autowired
	@Qualifier("cx2.CodeListService")
	private CodeListService codeListService;

    @Autowired
    @Qualifier("cx2.CodeSetsDao")
    private WMGenericDao<CodeSets, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<CodeSets, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public CodeSets create(CodeSets codeSets) {
        LOGGER.debug("Creating a new CodeSets with information: {}", codeSets);
        CodeSets codeSetsCreated = this.wmGenericDao.create(codeSets);
        if(codeSetsCreated.getCodeLists() != null) {
            for(CodeList codeList : codeSetsCreated.getCodeLists()) {
                codeList.setCodeSets(codeSetsCreated);
                LOGGER.debug("Creating a new child CodeList with information: {}", codeList);
                codeListService.create(codeList);
            }
        }
        return codeSetsCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public CodeSets getById(Integer codesetsId) throws EntityNotFoundException {
        LOGGER.debug("Finding CodeSets by id: {}", codesetsId);
        CodeSets codeSets = this.wmGenericDao.findById(codesetsId);
        if (codeSets == null){
            LOGGER.debug("No CodeSets found with id: {}", codesetsId);
            throw new EntityNotFoundException(String.valueOf(codesetsId));
        }
        return codeSets;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public CodeSets findById(Integer codesetsId) {
        LOGGER.debug("Finding CodeSets by id: {}", codesetsId);
        return this.wmGenericDao.findById(codesetsId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public CodeSets update(CodeSets codeSets) throws EntityNotFoundException {
        LOGGER.debug("Updating CodeSets with information: {}", codeSets);
        this.wmGenericDao.update(codeSets);

        Integer codesetsId = codeSets.getCodeSetId();

        return this.wmGenericDao.findById(codesetsId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public CodeSets delete(Integer codesetsId) throws EntityNotFoundException {
        LOGGER.debug("Deleting CodeSets with id: {}", codesetsId);
        CodeSets deleted = this.wmGenericDao.findById(codesetsId);
        if (deleted == null) {
            LOGGER.debug("No CodeSets found with id: {}", codesetsId);
            throw new EntityNotFoundException(String.valueOf(codesetsId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<CodeSets> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all CodeSets");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<CodeSets> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all CodeSets");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table CodeSets to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<CodeList> findAssociatedCodeLists(Integer codeSetId, Pageable pageable) {
        LOGGER.debug("Fetching all associated codeLists");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("codeSets.codeSetId = '" + codeSetId + "'");

        return codeListService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service CodeListService instance
	 */
	protected void setCodeListService(CodeListService service) {
        this.codeListService = service;
    }

}

