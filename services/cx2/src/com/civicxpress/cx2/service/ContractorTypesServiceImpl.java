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

import com.civicxpress.cx2.ContractorTypes;
import com.civicxpress.cx2.Vendor;


/**
 * ServiceImpl object for domain model class ContractorTypes.
 *
 * @see ContractorTypes
 */
@Service("cx2.ContractorTypesService")
public class ContractorTypesServiceImpl implements ContractorTypesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContractorTypesServiceImpl.class);

    @Autowired
	@Qualifier("cx2.VendorService")
	private VendorService vendorService;

    @Autowired
    @Qualifier("cx2.ContractorTypesDao")
    private WMGenericDao<ContractorTypes, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<ContractorTypes, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public ContractorTypes create(ContractorTypes contractorTypes) {
        LOGGER.debug("Creating a new ContractorTypes with information: {}", contractorTypes);
        ContractorTypes contractorTypesCreated = this.wmGenericDao.create(contractorTypes);
        if(contractorTypesCreated.getVendors() != null) {
            for(Vendor vendor : contractorTypesCreated.getVendors()) {
                vendor.setContractorTypes(contractorTypesCreated);
                LOGGER.debug("Creating a new child Vendor with information: {}", vendor);
                vendorService.create(vendor);
            }
        }
        return contractorTypesCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public ContractorTypes getById(Integer contractortypesId) throws EntityNotFoundException {
        LOGGER.debug("Finding ContractorTypes by id: {}", contractortypesId);
        ContractorTypes contractorTypes = this.wmGenericDao.findById(contractortypesId);
        if (contractorTypes == null){
            LOGGER.debug("No ContractorTypes found with id: {}", contractortypesId);
            throw new EntityNotFoundException(String.valueOf(contractortypesId));
        }
        return contractorTypes;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public ContractorTypes findById(Integer contractortypesId) {
        LOGGER.debug("Finding ContractorTypes by id: {}", contractortypesId);
        return this.wmGenericDao.findById(contractortypesId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public ContractorTypes update(ContractorTypes contractorTypes) throws EntityNotFoundException {
        LOGGER.debug("Updating ContractorTypes with information: {}", contractorTypes);
        this.wmGenericDao.update(contractorTypes);

        Integer contractortypesId = contractorTypes.getId();

        return this.wmGenericDao.findById(contractortypesId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public ContractorTypes delete(Integer contractortypesId) throws EntityNotFoundException {
        LOGGER.debug("Deleting ContractorTypes with id: {}", contractortypesId);
        ContractorTypes deleted = this.wmGenericDao.findById(contractortypesId);
        if (deleted == null) {
            LOGGER.debug("No ContractorTypes found with id: {}", contractortypesId);
            throw new EntityNotFoundException(String.valueOf(contractortypesId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<ContractorTypes> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all ContractorTypes");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<ContractorTypes> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all ContractorTypes");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table ContractorTypes to {} format", exportType);
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
    public Page<Vendor> findAssociatedVendors(Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated vendors");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("contractorTypes.id = '" + id + "'");

        return vendorService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service VendorService instance
	 */
	protected void setVendorService(VendorService service) {
        this.vendorService = service;
    }

}

