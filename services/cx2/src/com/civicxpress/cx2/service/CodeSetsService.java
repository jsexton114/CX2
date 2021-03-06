/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;

import com.civicxpress.cx2.Code;
import com.civicxpress.cx2.CodeSets;
import com.civicxpress.cx2.CodesToForm;
import com.civicxpress.cx2.CodesToInspection;

/**
 * Service object for domain model class {@link CodeSets}.
 */
public interface CodeSetsService {

    /**
     * Creates a new CodeSets. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on CodeSets if any.
     *
     * @param codeSets Details of the CodeSets to be created; value cannot be null.
     * @return The newly created CodeSets.
     */
	CodeSets create(CodeSets codeSets);


	/**
	 * Returns CodeSets by given id if exists.
	 *
	 * @param codesetsId The id of the CodeSets to get; value cannot be null.
	 * @return CodeSets associated with the given codesetsId.
     * @throws EntityNotFoundException If no CodeSets is found.
	 */
	CodeSets getById(Integer codesetsId) throws EntityNotFoundException;

    /**
	 * Find and return the CodeSets by given id if exists, returns null otherwise.
	 *
	 * @param codesetsId The id of the CodeSets to get; value cannot be null.
	 * @return CodeSets associated with the given codesetsId.
	 */
	CodeSets findById(Integer codesetsId);


	/**
	 * Updates the details of an existing CodeSets. It replaces all fields of the existing CodeSets with the given codeSets.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on CodeSets if any.
     *
	 * @param codeSets The details of the CodeSets to be updated; value cannot be null.
	 * @return The updated CodeSets.
	 * @throws EntityNotFoundException if no CodeSets is found with given input.
	 */
	CodeSets update(CodeSets codeSets) throws EntityNotFoundException;

    /**
	 * Deletes an existing CodeSets with the given id.
	 *
	 * @param codesetsId The id of the CodeSets to be deleted; value cannot be null.
	 * @return The deleted CodeSets.
	 * @throws EntityNotFoundException if no CodeSets found with the given id.
	 */
	CodeSets delete(Integer codesetsId) throws EntityNotFoundException;

	/**
	 * Find all CodeSets matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching CodeSets.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<CodeSets> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all CodeSets matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching CodeSets.
     *
     * @see Pageable
     * @see Page
	 */
    Page<CodeSets> findAll(String query, Pageable pageable);

    /**
	 * Exports all CodeSets matching the given input query to the given exportType format.
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
	 * Retrieve the count of the CodeSets in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the CodeSets.
	 */
	long count(String query);

	/**
	 * Retrieve aggregated values with matching aggregation info.
     *
     * @param aggregationInfo info related to aggregations.
     * @param pageable Details of the pagination information along with the sorting options. If null exports all matching records.
	 * @return Paginated data with included fields.

     * @see AggregationInfo
     * @see Pageable
     * @see Page
	 */
	Page<Map<String, Object>> getAggregatedValues(AggregationInfo aggregationInfo, Pageable pageable);

    /*
     * Returns the associated codes for given CodeSets id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Code instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Code> findAssociatedCodes(Integer id, Pageable pageable);

    /*
     * Returns the associated codesToForms for given CodeSets id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated CodesToForm instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<CodesToForm> findAssociatedCodesToForms(Integer id, Pageable pageable);

    /*
     * Returns the associated codesToInspections for given CodeSets id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated CodesToInspection instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<CodesToInspection> findAssociatedCodesToInspections(Integer id, Pageable pageable);

}

