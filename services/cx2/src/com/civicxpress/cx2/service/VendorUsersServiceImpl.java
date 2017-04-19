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

import com.civicxpress.cx2.VendorUsers;


/**
 * ServiceImpl object for domain model class VendorUsers.
 *
 * @see VendorUsers
 */
@Service("cx2.VendorUsersService")
public class VendorUsersServiceImpl implements VendorUsersService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VendorUsersServiceImpl.class);


    @Autowired
    @Qualifier("cx2.VendorUsersDao")
    private WMGenericDao<VendorUsers, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<VendorUsers, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public VendorUsers create(VendorUsers vendorUsers) {
        LOGGER.debug("Creating a new VendorUsers with information: {}", vendorUsers);
        VendorUsers vendorUsersCreated = this.wmGenericDao.create(vendorUsers);
        return vendorUsersCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public VendorUsers getById(Integer vendorusersId) throws EntityNotFoundException {
        LOGGER.debug("Finding VendorUsers by id: {}", vendorusersId);
        VendorUsers vendorUsers = this.wmGenericDao.findById(vendorusersId);
        if (vendorUsers == null){
            LOGGER.debug("No VendorUsers found with id: {}", vendorusersId);
            throw new EntityNotFoundException(String.valueOf(vendorusersId));
        }
        return vendorUsers;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public VendorUsers findById(Integer vendorusersId) {
        LOGGER.debug("Finding VendorUsers by id: {}", vendorusersId);
        return this.wmGenericDao.findById(vendorusersId);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public VendorUsers getByVendorIdAndUserId(Integer vendorId, Integer userId) {
        Map<String, Object> vendorIdAndUserIdMap = new HashMap<>();
        vendorIdAndUserIdMap.put("vendorId", vendorId);
        vendorIdAndUserIdMap.put("userId", userId);

        LOGGER.debug("Finding VendorUsers by unique keys: {}", vendorIdAndUserIdMap);
        VendorUsers vendorUsers = this.wmGenericDao.findByUniqueKey(vendorIdAndUserIdMap);

        if (vendorUsers == null){
            LOGGER.debug("No VendorUsers found with given unique key values: {}", vendorIdAndUserIdMap);
            throw new EntityNotFoundException(String.valueOf(vendorIdAndUserIdMap));
        }

        return vendorUsers;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public VendorUsers update(VendorUsers vendorUsers) throws EntityNotFoundException {
        LOGGER.debug("Updating VendorUsers with information: {}", vendorUsers);
        this.wmGenericDao.update(vendorUsers);

        Integer vendorusersId = vendorUsers.getId();

        return this.wmGenericDao.findById(vendorusersId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public VendorUsers delete(Integer vendorusersId) throws EntityNotFoundException {
        LOGGER.debug("Deleting VendorUsers with id: {}", vendorusersId);
        VendorUsers deleted = this.wmGenericDao.findById(vendorusersId);
        if (deleted == null) {
            LOGGER.debug("No VendorUsers found with id: {}", vendorusersId);
            throw new EntityNotFoundException(String.valueOf(vendorusersId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<VendorUsers> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all VendorUsers");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<VendorUsers> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all VendorUsers");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table VendorUsers to {} format", exportType);
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

