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
import com.civicxpress.cx2.CaseTypes;
import com.civicxpress.cx2.MasterCases;

/**
 * Service object for domain model class {@link CaseTypes}.
 */
public interface CaseTypesService {

    /**
     * Creates a new CaseTypes. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on CaseTypes if any.
     *
     * @param caseTypes Details of the CaseTypes to be created; value cannot be null.
     * @return The newly created CaseTypes.
     */
	CaseTypes create(CaseTypes caseTypes);


	/**
	 * Returns CaseTypes by given id if exists.
	 *
	 * @param casetypesId The id of the CaseTypes to get; value cannot be null.
	 * @return CaseTypes associated with the given casetypesId.
     * @throws EntityNotFoundException If no CaseTypes is found.
	 */
	CaseTypes getById(Integer casetypesId) throws EntityNotFoundException;

    /**
	 * Find and return the CaseTypes by given id if exists, returns null otherwise.
	 *
	 * @param casetypesId The id of the CaseTypes to get; value cannot be null.
	 * @return CaseTypes associated with the given casetypesId.
	 */
	CaseTypes findById(Integer casetypesId);


	/**
	 * Updates the details of an existing CaseTypes. It replaces all fields of the existing CaseTypes with the given caseTypes.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on CaseTypes if any.
     *
	 * @param caseTypes The details of the CaseTypes to be updated; value cannot be null.
	 * @return The updated CaseTypes.
	 * @throws EntityNotFoundException if no CaseTypes is found with given input.
	 */
	CaseTypes update(CaseTypes caseTypes) throws EntityNotFoundException;

    /**
	 * Deletes an existing CaseTypes with the given id.
	 *
	 * @param casetypesId The id of the CaseTypes to be deleted; value cannot be null.
	 * @return The deleted CaseTypes.
	 * @throws EntityNotFoundException if no CaseTypes found with the given id.
	 */
	CaseTypes delete(Integer casetypesId) throws EntityNotFoundException;

	/**
	 * Find all CaseTypes matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching CaseTypes.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<CaseTypes> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all CaseTypes matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching CaseTypes.
     *
     * @see Pageable
     * @see Page
	 */
    Page<CaseTypes> findAll(String query, Pageable pageable);

    /**
	 * Exports all CaseTypes matching the given input query to the given exportType format.
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
	 * Retrieve the count of the CaseTypes in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the CaseTypes.
	 */
	long count(String query);

    /*
     * Returns the associated caseStatuseses for given CaseTypes id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated CaseStatuses instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<CaseStatuses> findAssociatedCaseStatuseses(Integer id, Pageable pageable);

    /*
     * Returns the associated masterCaseses for given CaseTypes id.
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
