/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;

import com.civicxpress.cx2.CaseStatuses;
import com.civicxpress.cx2.MasterCases;

/**
 * Service object for domain model class {@link CaseStatuses}.
 */
public interface CaseStatusesService {

    /**
     * Creates a new CaseStatuses. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on CaseStatuses if any.
     *
     * @param caseStatuses Details of the CaseStatuses to be created; value cannot be null.
     * @return The newly created CaseStatuses.
     */
	CaseStatuses create(CaseStatuses caseStatuses);


	/**
	 * Returns CaseStatuses by given id if exists.
	 *
	 * @param casestatusesId The id of the CaseStatuses to get; value cannot be null.
	 * @return CaseStatuses associated with the given casestatusesId.
     * @throws EntityNotFoundException If no CaseStatuses is found.
	 */
	CaseStatuses getById(Integer casestatusesId) throws EntityNotFoundException;

    /**
	 * Find and return the CaseStatuses by given id if exists, returns null otherwise.
	 *
	 * @param casestatusesId The id of the CaseStatuses to get; value cannot be null.
	 * @return CaseStatuses associated with the given casestatusesId.
	 */
	CaseStatuses findById(Integer casestatusesId);

    /**
	 * Find and return the CaseStatuses for given caseTypeId  andsortNumber  if exists.
	 *
	 * @param caseTypeId value of caseTypeId; value cannot be null.
	 * @param sortNumber value of sortNumber; value cannot be null.
	 * @return CaseStatuses associated with the given inputs.
     * @throws EntityNotFoundException if no matching CaseStatuses found.
	 */
    CaseStatuses getByCaseTypeIdAndSortNumber(Integer caseTypeId, Integer sortNumber)throws EntityNotFoundException;

	/**
	 * Updates the details of an existing CaseStatuses. It replaces all fields of the existing CaseStatuses with the given caseStatuses.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on CaseStatuses if any.
     *
	 * @param caseStatuses The details of the CaseStatuses to be updated; value cannot be null.
	 * @return The updated CaseStatuses.
	 * @throws EntityNotFoundException if no CaseStatuses is found with given input.
	 */
	CaseStatuses update(CaseStatuses caseStatuses) throws EntityNotFoundException;

    /**
	 * Deletes an existing CaseStatuses with the given id.
	 *
	 * @param casestatusesId The id of the CaseStatuses to be deleted; value cannot be null.
	 * @return The deleted CaseStatuses.
	 * @throws EntityNotFoundException if no CaseStatuses found with the given id.
	 */
	CaseStatuses delete(Integer casestatusesId) throws EntityNotFoundException;

	/**
	 * Find all CaseStatuses matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching CaseStatuses.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<CaseStatuses> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all CaseStatuses matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching CaseStatuses.
     *
     * @see Pageable
     * @see Page
	 */
    Page<CaseStatuses> findAll(String query, Pageable pageable);

    /**
	 * Exports all CaseStatuses matching the given input query to the given exportType format.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param exportType The format in which to export the data; value cannot be null.
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
     * @return The Downloadable file in given export type.
     *
     * @see Pageable
     * @see ExportType
     * @see Downloadable
	 */
    Downloadable export(ExportType exportType, String query, Pageable pageable);

	/**
	 * Retrieve the count of the CaseStatuses in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the CaseStatuses.
	 */
	long count(String query);

    /*
     * Returns the associated masterCaseses for given CaseStatuses id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated MasterCases instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<MasterCases> findAssociatedMasterCaseses(Integer id, Pageable pageable);

}

