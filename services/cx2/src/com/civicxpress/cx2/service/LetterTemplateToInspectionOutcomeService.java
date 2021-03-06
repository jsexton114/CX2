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

import com.civicxpress.cx2.LetterTemplateToInspectionOutcome;

/**
 * Service object for domain model class {@link LetterTemplateToInspectionOutcome}.
 */
public interface LetterTemplateToInspectionOutcomeService {

    /**
     * Creates a new LetterTemplateToInspectionOutcome. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on LetterTemplateToInspectionOutcome if any.
     *
     * @param letterTemplateToInspectionOutcome Details of the LetterTemplateToInspectionOutcome to be created; value cannot be null.
     * @return The newly created LetterTemplateToInspectionOutcome.
     */
	LetterTemplateToInspectionOutcome create(LetterTemplateToInspectionOutcome letterTemplateToInspectionOutcome);


	/**
	 * Returns LetterTemplateToInspectionOutcome by given id if exists.
	 *
	 * @param lettertemplatetoinspectionoutcomeId The id of the LetterTemplateToInspectionOutcome to get; value cannot be null.
	 * @return LetterTemplateToInspectionOutcome associated with the given lettertemplatetoinspectionoutcomeId.
     * @throws EntityNotFoundException If no LetterTemplateToInspectionOutcome is found.
	 */
	LetterTemplateToInspectionOutcome getById(Integer lettertemplatetoinspectionoutcomeId) throws EntityNotFoundException;

    /**
	 * Find and return the LetterTemplateToInspectionOutcome by given id if exists, returns null otherwise.
	 *
	 * @param lettertemplatetoinspectionoutcomeId The id of the LetterTemplateToInspectionOutcome to get; value cannot be null.
	 * @return LetterTemplateToInspectionOutcome associated with the given lettertemplatetoinspectionoutcomeId.
	 */
	LetterTemplateToInspectionOutcome findById(Integer lettertemplatetoinspectionoutcomeId);


	/**
	 * Updates the details of an existing LetterTemplateToInspectionOutcome. It replaces all fields of the existing LetterTemplateToInspectionOutcome with the given letterTemplateToInspectionOutcome.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on LetterTemplateToInspectionOutcome if any.
     *
	 * @param letterTemplateToInspectionOutcome The details of the LetterTemplateToInspectionOutcome to be updated; value cannot be null.
	 * @return The updated LetterTemplateToInspectionOutcome.
	 * @throws EntityNotFoundException if no LetterTemplateToInspectionOutcome is found with given input.
	 */
	LetterTemplateToInspectionOutcome update(LetterTemplateToInspectionOutcome letterTemplateToInspectionOutcome) throws EntityNotFoundException;

    /**
	 * Deletes an existing LetterTemplateToInspectionOutcome with the given id.
	 *
	 * @param lettertemplatetoinspectionoutcomeId The id of the LetterTemplateToInspectionOutcome to be deleted; value cannot be null.
	 * @return The deleted LetterTemplateToInspectionOutcome.
	 * @throws EntityNotFoundException if no LetterTemplateToInspectionOutcome found with the given id.
	 */
	LetterTemplateToInspectionOutcome delete(Integer lettertemplatetoinspectionoutcomeId) throws EntityNotFoundException;

	/**
	 * Find all LetterTemplateToInspectionOutcomes matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching LetterTemplateToInspectionOutcomes.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<LetterTemplateToInspectionOutcome> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all LetterTemplateToInspectionOutcomes matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching LetterTemplateToInspectionOutcomes.
     *
     * @see Pageable
     * @see Page
	 */
    Page<LetterTemplateToInspectionOutcome> findAll(String query, Pageable pageable);

    /**
	 * Exports all LetterTemplateToInspectionOutcomes matching the given input query to the given exportType format.
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
	 * Retrieve the count of the LetterTemplateToInspectionOutcomes in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the LetterTemplateToInspectionOutcome.
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


}

