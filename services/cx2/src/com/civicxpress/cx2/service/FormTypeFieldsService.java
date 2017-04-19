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

import com.civicxpress.cx2.FormTypeFields;

/**
 * Service object for domain model class {@link FormTypeFields}.
 */
public interface FormTypeFieldsService {

    /**
     * Creates a new FormTypeFields. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on FormTypeFields if any.
     *
     * @param formTypeFields Details of the FormTypeFields to be created; value cannot be null.
     * @return The newly created FormTypeFields.
     */
	FormTypeFields create(FormTypeFields formTypeFields);


	/**
	 * Returns FormTypeFields by given id if exists.
	 *
	 * @param formtypefieldsId The id of the FormTypeFields to get; value cannot be null.
	 * @return FormTypeFields associated with the given formtypefieldsId.
     * @throws EntityNotFoundException If no FormTypeFields is found.
	 */
	FormTypeFields getById(Integer formtypefieldsId) throws EntityNotFoundException;

    /**
	 * Find and return the FormTypeFields by given id if exists, returns null otherwise.
	 *
	 * @param formtypefieldsId The id of the FormTypeFields to get; value cannot be null.
	 * @return FormTypeFields associated with the given formtypefieldsId.
	 */
	FormTypeFields findById(Integer formtypefieldsId);


	/**
	 * Updates the details of an existing FormTypeFields. It replaces all fields of the existing FormTypeFields with the given formTypeFields.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on FormTypeFields if any.
     *
	 * @param formTypeFields The details of the FormTypeFields to be updated; value cannot be null.
	 * @return The updated FormTypeFields.
	 * @throws EntityNotFoundException if no FormTypeFields is found with given input.
	 */
	FormTypeFields update(FormTypeFields formTypeFields) throws EntityNotFoundException;

    /**
	 * Deletes an existing FormTypeFields with the given id.
	 *
	 * @param formtypefieldsId The id of the FormTypeFields to be deleted; value cannot be null.
	 * @return The deleted FormTypeFields.
	 * @throws EntityNotFoundException if no FormTypeFields found with the given id.
	 */
	FormTypeFields delete(Integer formtypefieldsId) throws EntityNotFoundException;

	/**
	 * Find all FormTypeFields matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching FormTypeFields.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<FormTypeFields> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all FormTypeFields matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching FormTypeFields.
     *
     * @see Pageable
     * @see Page
	 */
    Page<FormTypeFields> findAll(String query, Pageable pageable);

    /**
	 * Exports all FormTypeFields matching the given input query to the given exportType format.
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
	 * Retrieve the count of the FormTypeFields in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the FormTypeFields.
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

