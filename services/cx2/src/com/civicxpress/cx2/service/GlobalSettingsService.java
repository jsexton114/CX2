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

import com.civicxpress.cx2.GlobalSettings;

/**
 * Service object for domain model class {@link GlobalSettings}.
 */
public interface GlobalSettingsService {

    /**
     * Creates a new GlobalSettings. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on GlobalSettings if any.
     *
     * @param globalSettings Details of the GlobalSettings to be created; value cannot be null.
     * @return The newly created GlobalSettings.
     */
	GlobalSettings create(GlobalSettings globalSettings);


	/**
	 * Returns GlobalSettings by given id if exists.
	 *
	 * @param globalsettingsId The id of the GlobalSettings to get; value cannot be null.
	 * @return GlobalSettings associated with the given globalsettingsId.
     * @throws EntityNotFoundException If no GlobalSettings is found.
	 */
	GlobalSettings getById(Integer globalsettingsId) throws EntityNotFoundException;

    /**
	 * Find and return the GlobalSettings by given id if exists, returns null otherwise.
	 *
	 * @param globalsettingsId The id of the GlobalSettings to get; value cannot be null.
	 * @return GlobalSettings associated with the given globalsettingsId.
	 */
	GlobalSettings findById(Integer globalsettingsId);


	/**
	 * Updates the details of an existing GlobalSettings. It replaces all fields of the existing GlobalSettings with the given globalSettings.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on GlobalSettings if any.
     *
	 * @param globalSettings The details of the GlobalSettings to be updated; value cannot be null.
	 * @return The updated GlobalSettings.
	 * @throws EntityNotFoundException if no GlobalSettings is found with given input.
	 */
	GlobalSettings update(GlobalSettings globalSettings) throws EntityNotFoundException;

    /**
	 * Deletes an existing GlobalSettings with the given id.
	 *
	 * @param globalsettingsId The id of the GlobalSettings to be deleted; value cannot be null.
	 * @return The deleted GlobalSettings.
	 * @throws EntityNotFoundException if no GlobalSettings found with the given id.
	 */
	GlobalSettings delete(Integer globalsettingsId) throws EntityNotFoundException;

	/**
	 * Find all GlobalSettings matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching GlobalSettings.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<GlobalSettings> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all GlobalSettings matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching GlobalSettings.
     *
     * @see Pageable
     * @see Page
	 */
    Page<GlobalSettings> findAll(String query, Pageable pageable);

    /**
	 * Exports all GlobalSettings matching the given input query to the given exportType format.
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
	 * Retrieve the count of the GlobalSettings in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the GlobalSettings.
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

