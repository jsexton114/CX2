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

import com.civicxpress.cx2.CodesToInspection;

/**
 * Service object for domain model class {@link CodesToInspection}.
 */
public interface CodesToInspectionService {

    /**
     * Creates a new CodesToInspection. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on CodesToInspection if any.
     *
     * @param codesToInspection Details of the CodesToInspection to be created; value cannot be null.
     * @return The newly created CodesToInspection.
     */
	CodesToInspection create(CodesToInspection codesToInspection);


	/**
	 * Returns CodesToInspection by given id if exists.
	 *
	 * @param codestoinspectionId The id of the CodesToInspection to get; value cannot be null.
	 * @return CodesToInspection associated with the given codestoinspectionId.
     * @throws EntityNotFoundException If no CodesToInspection is found.
	 */
	CodesToInspection getById(Integer codestoinspectionId) throws EntityNotFoundException;

    /**
	 * Find and return the CodesToInspection by given id if exists, returns null otherwise.
	 *
	 * @param codestoinspectionId The id of the CodesToInspection to get; value cannot be null.
	 * @return CodesToInspection associated with the given codestoinspectionId.
	 */
	CodesToInspection findById(Integer codestoinspectionId);


	/**
	 * Updates the details of an existing CodesToInspection. It replaces all fields of the existing CodesToInspection with the given codesToInspection.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on CodesToInspection if any.
     *
	 * @param codesToInspection The details of the CodesToInspection to be updated; value cannot be null.
	 * @return The updated CodesToInspection.
	 * @throws EntityNotFoundException if no CodesToInspection is found with given input.
	 */
	CodesToInspection update(CodesToInspection codesToInspection) throws EntityNotFoundException;

    /**
	 * Deletes an existing CodesToInspection with the given id.
	 *
	 * @param codestoinspectionId The id of the CodesToInspection to be deleted; value cannot be null.
	 * @return The deleted CodesToInspection.
	 * @throws EntityNotFoundException if no CodesToInspection found with the given id.
	 */
	CodesToInspection delete(Integer codestoinspectionId) throws EntityNotFoundException;

	/**
	 * Find all CodesToInspections matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching CodesToInspections.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<CodesToInspection> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all CodesToInspections matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching CodesToInspections.
     *
     * @see Pageable
     * @see Page
	 */
    Page<CodesToInspection> findAll(String query, Pageable pageable);

    /**
	 * Exports all CodesToInspections matching the given input query to the given exportType format.
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
	 * Retrieve the count of the CodesToInspections in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the CodesToInspection.
	 */
	long count(String query);


}

