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
    Page<Object> executeEmployeesMunicipalities(Pageable pageable, java.lang.Integer user) throws QueryParameterMismatchException;
    Page<Object> executeMunicipalityCount(Pageable pageable) throws QueryParameterMismatchException;
	int executeResetPasswordForUser(  java.lang.String newPassword , java.lang.Integer userID) throws QueryParameterMismatchException;
	int executeResetPasswordWithTokenForUser(  java.lang.Integer userid , java.lang.String token) throws QueryParameterMismatchException;
    Page<Object> executeStandardUserMunicipalites(Pageable pageable, java.lang.Integer USER) throws QueryParameterMismatchException;
    Page<Object> executeSubDivisonCount(Pageable pageable, java.lang.Integer municipalityId) throws QueryParameterMismatchException;
	int executeUpdateCFInProfile(  java.lang.String cf , java.lang.Integer user) throws QueryParameterMismatchException;
	int executeUpdateInfoFromMyProfile(  java.lang.String fn , java.lang.String ln , java.lang.String em , java.lang.String ph , java.lang.String ad1 , java.lang.String ad2 , java.lang.String st , java.lang.String ct , java.lang.String pc , java.lang.Integer user) throws QueryParameterMismatchException;
	int executeUpdatePasswordAndCF(  java.lang.String password , java.lang.String cf , java.lang.Integer newUser) throws QueryParameterMismatchException;
    Page<Object> executeUserCount(Pageable pageable) throws QueryParameterMismatchException;
    Page<Object> executeUserSubscriptionsCount(Pageable pageable) throws QueryParameterMismatchException;
    Page<Object> executeUserSubscriptionsCountForMunicipality(Pageable pageable, java.lang.Integer municipalityId) throws QueryParameterMismatchException;
    Page<Object> executeVerifyPasswordResetToken(Pageable pageable, java.lang.String token) throws QueryParameterMismatchException;

	
	Page<Object> executeWMCustomQuerySelect(CustomQuery query, Pageable pageable) ;

	int executeWMCustomQueryUpdate(CustomQuery query) ;
}

