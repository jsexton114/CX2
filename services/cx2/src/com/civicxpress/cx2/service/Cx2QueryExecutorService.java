/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/

package com.civicxpress.cx2.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.model.CustomQuery;
import com.wavemaker.runtime.data.exception.QueryParameterMismatchException;

public interface Cx2QueryExecutorService {
    Page<Object> executeAdminsMunicipalities(Pageable pageable, java.lang.Integer user) throws QueryParameterMismatchException;
    Page<Object> executeMunicipalityCount(Pageable pageable) throws QueryParameterMismatchException;
    Page<Object> executeSubDivisonCount(Pageable pageable, java.lang.Integer municipalityId) throws QueryParameterMismatchException;
	int executeUpdatePasswordAndCF(  java.lang.String password , java.lang.String cf , java.lang.Integer newUser) throws QueryParameterMismatchException;
    Page<Object> executeUserCount(Pageable pageable) throws QueryParameterMismatchException;
    Page<Object> executeUserSubscriptionsCount(Pageable pageable) throws QueryParameterMismatchException;

	
	Page<Object> executeWMCustomQuerySelect(CustomQuery query, Pageable pageable) ;

	int executeWMCustomQueryUpdate(CustomQuery query) ;
}

