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

import com.civicxpress.cx2.VendorAdmins;


/**
 * ServiceImpl object for domain model class VendorAdmins.
 *
 * @see VendorAdmins
 */
@Service("cx2.VendorAdminsService")
public class VendorAdminsServiceImpl implements VendorAdminsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VendorAdminsServiceImpl.class);


    @Autowired
    @Qualifier("cx2.VendorAdminsDao")
    private WMGenericDao<VendorAdmins, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<VendorAdmins, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public VendorAdmins create(VendorAdmins vendorAdmins) {
        LOGGER.debug("Creating a new VendorAdmins with information: {}", vendorAdmins);
        VendorAdmins vendorAdminsCreated = this.wmGenericDao.create(vendorAdmins);
        return vendorAdminsCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public VendorAdmins getById(Integer vendoradminsId) throws EntityNotFoundException {
        LOGGER.debug("Finding VendorAdmins by id: {}", vendoradminsId);
        VendorAdmins vendorAdmins = this.wmGenericDao.findById(vendoradminsId);
        if (vendorAdmins == null){
            LOGGER.debug("No VendorAdmins found with id: {}", vendoradminsId);
            throw new EntityNotFoundException(String.valueOf(vendoradminsId));
        }
        return vendorAdmins;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public VendorAdmins findById(Integer vendoradminsId) {
        LOGGER.debug("Finding VendorAdmins by id: {}", vendoradminsId);
        return this.wmGenericDao.findById(vendoradminsId);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public VendorAdmins getByUserIdAndVendorId(int userId, int vendorId) {
        Map<String, Object> userIdAndVendorIdMap = new HashMap<>();
        userIdAndVendorIdMap.put("userId", userId);
        userIdAndVendorIdMap.put("vendorId", vendorId);

        LOGGER.debug("Finding VendorAdmins by unique keys: {}", userIdAndVendorIdMap);
        VendorAdmins vendorAdmins = this.wmGenericDao.findByUniqueKey(userIdAndVendorIdMap);

        if (vendorAdmins == null){
            LOGGER.debug("No VendorAdmins found with given unique key values: {}", userIdAndVendorIdMap);
            throw new EntityNotFoundException(String.valueOf(userIdAndVendorIdMap));
        }

        return vendorAdmins;
    }

	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public VendorAdmins update(VendorAdmins vendorAdmins) throws EntityNotFoundException {
        LOGGER.debug("Updating VendorAdmins with information: {}", vendorAdmins);
        this.wmGenericDao.update(vendorAdmins);

        Integer vendoradminsId = vendorAdmins.getId();

        return this.wmGenericDao.findById(vendoradminsId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public VendorAdmins delete(Integer vendoradminsId) throws EntityNotFoundException {
        LOGGER.debug("Deleting VendorAdmins with id: {}", vendoradminsId);
        VendorAdmins deleted = this.wmGenericDao.findById(vendoradminsId);
        if (deleted == null) {
            LOGGER.debug("No VendorAdmins found with id: {}", vendoradminsId);
            throw new EntityNotFoundException(String.valueOf(vendoradminsId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<VendorAdmins> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all VendorAdmins");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<VendorAdmins> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all VendorAdmins");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table VendorAdmins to {} format", exportType);
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

