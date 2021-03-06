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

import com.civicxpress.cx2.Gis2forms;

/**
 * Service object for domain model class {@link Gis2forms}.
 */
public interface Gis2formsService {

    /**
     * Creates a new Gis2forms. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Gis2forms if any.
     *
     * @param gis2forms Details of the Gis2forms to be created; value cannot be null.
     * @return The newly created Gis2forms.
     */
	Gis2forms create(Gis2forms gis2forms);


	/**
	 * Returns Gis2forms by given id if exists.
	 *
	 * @param gis2formsId The id of the Gis2forms to get; value cannot be null.
	 * @return Gis2forms associated with the given gis2formsId.
     * @throws EntityNotFoundException If no Gis2forms is found.
	 */
	Gis2forms getById(Integer gis2formsId) throws EntityNotFoundException;

    /**
	 * Find and return the Gis2forms by given id if exists, returns null otherwise.
	 *
	 * @param gis2formsId The id of the Gis2forms to get; value cannot be null.
	 * @return Gis2forms associated with the given gis2formsId.
	 */
	Gis2forms findById(Integer gis2formsId);


	/**
	 * Updates the details of an existing Gis2forms. It replaces all fields of the existing Gis2forms with the given gis2forms.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on Gis2forms if any.
     *
	 * @param gis2forms The details of the Gis2forms to be updated; value cannot be null.
	 * @return The updated Gis2forms.
	 * @throws EntityNotFoundException if no Gis2forms is found with given input.
	 */
	Gis2forms update(Gis2forms gis2forms) throws EntityNotFoundException;

    /**
	 * Deletes an existing Gis2forms with the given id.
	 *
	 * @param gis2formsId The id of the Gis2forms to be deleted; value cannot be null.
	 * @return The deleted Gis2forms.
	 * @throws EntityNotFoundException if no Gis2forms found with the given id.
	 */
	Gis2forms delete(Integer gis2formsId) throws EntityNotFoundException;

	/**
	 * Find all Gis2forms matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Gis2forms.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<Gis2forms> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all Gis2forms matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Gis2forms.
     *
     * @see Pageable
     * @see Page
	 */
    Page<Gis2forms> findAll(String query, Pageable pageable);

    /**
	 * Exports all Gis2forms matching the given input query to the given exportType format.
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
	 * Retrieve the count of the Gis2forms in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the Gis2forms.
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

