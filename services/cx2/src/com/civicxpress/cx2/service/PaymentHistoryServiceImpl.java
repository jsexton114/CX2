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

import com.civicxpress.cx2.PaymentHistory;
import com.civicxpress.cx2.TransactionToFees;


/**
 * ServiceImpl object for domain model class PaymentHistory.
 *
 * @see PaymentHistory
 */
@Service("cx2.PaymentHistoryService")
public class PaymentHistoryServiceImpl implements PaymentHistoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentHistoryServiceImpl.class);

    @Autowired
	@Qualifier("cx2.TransactionToFeesService")
	private TransactionToFeesService transactionToFeesService;

    @Autowired
    @Qualifier("cx2.PaymentHistoryDao")
    private WMGenericDao<PaymentHistory, Integer> wmGenericDao;

    public void setWMGenericDao(WMGenericDao<PaymentHistory, Integer> wmGenericDao) {
        this.wmGenericDao = wmGenericDao;
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
	public PaymentHistory create(PaymentHistory paymentHistory) {
        LOGGER.debug("Creating a new PaymentHistory with information: {}", paymentHistory);
        PaymentHistory paymentHistoryCreated = this.wmGenericDao.create(paymentHistory);
        if(paymentHistoryCreated.getTransactionToFeeses() != null) {
            for(TransactionToFees transactionToFeese : paymentHistoryCreated.getTransactionToFeeses()) {
                transactionToFeese.setPaymentHistory(paymentHistoryCreated);
                LOGGER.debug("Creating a new child TransactionToFees with information: {}", transactionToFeese);
                transactionToFeesService.create(transactionToFeese);
            }
        }
        return paymentHistoryCreated;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public PaymentHistory getById(Integer paymenthistoryId) throws EntityNotFoundException {
        LOGGER.debug("Finding PaymentHistory by id: {}", paymenthistoryId);
        PaymentHistory paymentHistory = this.wmGenericDao.findById(paymenthistoryId);
        if (paymentHistory == null){
            LOGGER.debug("No PaymentHistory found with id: {}", paymenthistoryId);
            throw new EntityNotFoundException(String.valueOf(paymenthistoryId));
        }
        return paymentHistory;
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public PaymentHistory findById(Integer paymenthistoryId) {
        LOGGER.debug("Finding PaymentHistory by id: {}", paymenthistoryId);
        return this.wmGenericDao.findById(paymenthistoryId);
    }


	@Transactional(rollbackFor = EntityNotFoundException.class, value = "cx2TransactionManager")
	@Override
	public PaymentHistory update(PaymentHistory paymentHistory) throws EntityNotFoundException {
        LOGGER.debug("Updating PaymentHistory with information: {}", paymentHistory);
        this.wmGenericDao.update(paymentHistory);

        Integer paymenthistoryId = paymentHistory.getTransactionId();

        return this.wmGenericDao.findById(paymenthistoryId);
    }

    @Transactional(value = "cx2TransactionManager")
	@Override
	public PaymentHistory delete(Integer paymenthistoryId) throws EntityNotFoundException {
        LOGGER.debug("Deleting PaymentHistory with id: {}", paymenthistoryId);
        PaymentHistory deleted = this.wmGenericDao.findById(paymenthistoryId);
        if (deleted == null) {
            LOGGER.debug("No PaymentHistory found with id: {}", paymenthistoryId);
            throw new EntityNotFoundException(String.valueOf(paymenthistoryId));
        }
        this.wmGenericDao.delete(deleted);
        return deleted;
    }

	@Transactional(readOnly = true, value = "cx2TransactionManager")
	@Override
	public Page<PaymentHistory> findAll(QueryFilter[] queryFilters, Pageable pageable) {
        LOGGER.debug("Finding all PaymentHistories");
        return this.wmGenericDao.search(queryFilters, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Page<PaymentHistory> findAll(String query, Pageable pageable) {
        LOGGER.debug("Finding all PaymentHistories");
        return this.wmGenericDao.searchByQuery(query, pageable);
    }

    @Transactional(readOnly = true, value = "cx2TransactionManager")
    @Override
    public Downloadable export(ExportType exportType, String query, Pageable pageable) {
        LOGGER.debug("exporting data in the service cx2 for table PaymentHistory to {} format", exportType);
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
    public Page<TransactionToFees> findAssociatedTransactionToFeeses(Integer transactionId, Pageable pageable) {
        LOGGER.debug("Fetching all associated transactionToFeeses");

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("paymentHistory.transactionId = '" + transactionId + "'");

        return transactionToFeesService.findAll(queryBuilder.toString(), pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service TransactionToFeesService instance
	 */
	protected void setTransactionToFeesService(TransactionToFeesService service) {
        this.transactionToFeesService = service;
    }

}

