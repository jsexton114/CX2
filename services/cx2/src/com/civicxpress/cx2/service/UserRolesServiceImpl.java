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

import com.civicxpress.cx2.UserRoles;


/**
 * ServiceImpl object for domain model class UserRoles.
 *
 * @see UserRoles
 */
@Service("cx2.UserRolesService")
public class UserRolesServiceImpl implements UserRolesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRolesServiceImpl.class);


    @Autowired
    @Qualifier("cx2.UserRolesDao")
    private WMGenericDao<UserRoles, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<UserRoles, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public UserRoles create(UserRoles userRoles) {
        LOGGER.debug("Creating a new UserRoles with information: {}", userRoles);
        UserRoles userRolesCreated = this.wmGenericDao.create(userRoles);
        return userRolesCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public UserRoles getById(Integer userrolesId) throws EntityNotFoundException {
        LOGGER.debug("Finding UserRoles by id: {}", userrolesId);
        UserRoles userRoles = this.wmGenericDao.findById(userrolesId);
        if (userRoles == null){
            LOGGER.debug("No UserRoles found with id: {}", userrolesId);
            throw new EntityNotFoundException(String.valueOf(userrolesId));
        }
        return userRoles;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public UserRoles findById(Integer userrolesId) {
        LOGGER.debug("Finding UserRoles by id: {}", userrolesId);
        return this.wmGenericDao.findById(userrolesId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public UserRoles update(UserRoles userRoles) throws EntityNotFoundException {
        LOGGER.debug("Updating UserRoles with information: {}", userRoles);
        this.wmGenericDao.update(userRoles);

        Integer userrolesId = userRoles.getId();

        return this.wmGenericDao.findById(userrolesId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public UserRoles delete(Integer userrolesId) throws EntityNotFoundException {
        LOGGER.debug("Deleting UserRoles with id: {}", userrolesId);
        UserRoles deleted = this.wmGenericDao.findById(userrolesId);
        if (deleted == null) {
            LOGGER.debug("No UserRoles found with id: {}", userrolesId);
            throw new EntityNotFoundException(String.valueOf(userrolesId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<UserRoles> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all UserRoles");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<UserRoles> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all UserRoles");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table UserRoles to {} format", exportType);
        return this.wmGenericDao.export(exportType, query, pageable);
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public long count(String query) {
        return this.wmGenericDao.count(query);
    }



}

