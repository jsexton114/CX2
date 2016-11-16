/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/

package com.civicxpress.cx2.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.wavemaker.runtime.data.model.CustomQuery;
import com.wavemaker.runtime.data.dao.query.WMQueryExecutor;
import com.wavemaker.runtime.data.exception.QueryParameterMismatchException;

@Service
public class Cx2QueryExecutorServiceImpl implements Cx2QueryExecutorService {
	private static final Logger LOGGER = LoggerFactory.getLogger(Cx2QueryExecutorServiceImpl.class);

	@Autowired
	@Qualifier("cx2WMQueryExecutor")
	private WMQueryExecutor queryExecutor;

	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeAdminsMunicipalities(Pageable pageable, java.lang.Integer user)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user", user);
        return queryExecutor.executeNamedQuery("AdminsMunicipalities", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeEmployeesMunicipalities(Pageable pageable, java.lang.Integer user)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user", user);
        return queryExecutor.executeNamedQuery("EmployeesMunicipalities", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeMunicipalityCount(Pageable pageable)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        return queryExecutor.executeNamedQuery("MunicipalityCount", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeResetPasswordForUser( java.lang.String newPassword ,java.lang.Integer userID)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("newPassword", newPassword);
        params.put("userID", userID);
        return queryExecutor.executeNamedQueryForUpdate("resetPasswordForUser", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeResetPasswordWithTokenForUser( java.lang.Integer userid ,java.lang.String token)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userid", userid);
        params.put("token", token);
        return queryExecutor.executeNamedQueryForUpdate("resetPasswordWithTokenForUser", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeStandardUserMunicipalites(Pageable pageable, java.lang.Integer USER)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("USER", USER);
        return queryExecutor.executeNamedQuery("StandardUserMunicipalites", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeSubDivisonCount(Pageable pageable, java.lang.Integer municipalityId)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("municipalityId", municipalityId);
        return queryExecutor.executeNamedQuery("SubDivisonCount", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeUpdateCFInProfile( java.lang.String cf ,java.lang.Integer user)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cf", cf);
        params.put("user", user);
        return queryExecutor.executeNamedQueryForUpdate("UpdateCFInProfile", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeUpdateInfoFromMyProfile( java.lang.String fn ,java.lang.String ln ,java.lang.String em ,java.lang.String ph ,java.lang.String ad1 ,java.lang.String ad2 ,java.lang.String st ,java.lang.String ct ,java.lang.String pc ,java.lang.Integer user)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("fn", fn);
        params.put("ln", ln);
        params.put("em", em);
        params.put("ph", ph);
        params.put("ad1", ad1);
        params.put("ad2", ad2);
        params.put("st", st);
        params.put("ct", ct);
        params.put("pc", pc);
        params.put("user", user);
        return queryExecutor.executeNamedQueryForUpdate("UpdateInfoFromMyProfile", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeUpdatePasswordAndCF( java.lang.String password ,java.lang.String cf ,java.lang.Integer newUser)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("password", password);
        params.put("cf", cf);
        params.put("newUser", newUser);
        return queryExecutor.executeNamedQueryForUpdate("UpdatePasswordAndCF", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeUserCount(Pageable pageable)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        return queryExecutor.executeNamedQuery("UserCount", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeUserSubscriptionsCount(Pageable pageable)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        return queryExecutor.executeNamedQuery("userSubscriptionsCount", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeUserSubscriptionsCountForMunicipality(Pageable pageable, java.lang.Integer municipalityId)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("municipalityId", municipalityId);
        return queryExecutor.executeNamedQuery("userSubscriptionsCountForMunicipality", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeVerifyPasswordResetToken(Pageable pageable, java.lang.String token)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("token", token);
        return queryExecutor.executeNamedQuery("verifyPasswordResetToken", params, pageable);
	}

	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeWMCustomQuerySelect(CustomQuery query, Pageable pageable) {
	    return queryExecutor.executeCustomQuery(query, pageable);
	}

	@Transactional(value = "cx2TransactionManager")
    @Override
    public int executeWMCustomQueryUpdate(CustomQuery query) {
        return queryExecutor.executeCustomQueryForUpdate(query);
    }
}

