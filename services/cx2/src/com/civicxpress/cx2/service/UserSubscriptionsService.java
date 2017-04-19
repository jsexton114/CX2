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

import com.civicxpress.cx2.UserSubscriptions;

/**
 * Service object for domain model class {@link UserSubscriptions}.
 */
public interface UserSubscriptionsService {

    /**
     * Creates a new UserSubscriptions. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on UserSubscriptions if any.
     *
     * @param userSubscriptions Details of the UserSubscriptions to be created; value cannot be null.
     * @return The newly created UserSubscriptions.
     */
	UserSubscriptions create(UserSubscriptions userSubscriptions);


	/**
	 * Returns UserSubscriptions by given id if exists.
	 *
	 * @param usersubscriptionsId The id of the UserSubscriptions to get; value cannot be null.
	 * @return UserSubscriptions associated with the given usersubscriptionsId.
     * @throws EntityNotFoundException If no UserSubscriptions is found.
	 */
	UserSubscriptions getById(Integer usersubscriptionsId) throws EntityNotFoundException;

    /**
	 * Find and return the UserSubscriptions by given id if exists, returns null otherwise.
	 *
	 * @param usersubscriptionsId The id of the UserSubscriptions to get; value cannot be null.
	 * @return UserSubscriptions associated with the given usersubscriptionsId.
	 */
	UserSubscriptions findById(Integer usersubscriptionsId);

    /**
	 * Find and return the UserSubscriptions for given municipalityId  anduserId  if exists.
	 *
	 * @param municipalityId value of municipalityId; value cannot be null.
	 * @param userId value of userId; value cannot be null.
	 * @return UserSubscriptions associated with the given inputs.
     * @throws EntityNotFoundException if no matching UserSubscriptions found.
	 */
    UserSubscriptions getByMunicipalityIdAndUserId(Integer municipalityId, Integer userId)throws EntityNotFoundException;

	/**
	 * Updates the details of an existing UserSubscriptions. It replaces all fields of the existing UserSubscriptions with the given userSubscriptions.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on UserSubscriptions if any.
     *
	 * @param userSubscriptions The details of the UserSubscriptions to be updated; value cannot be null.
	 * @return The updated UserSubscriptions.
	 * @throws EntityNotFoundException if no UserSubscriptions is found with given input.
	 */
	UserSubscriptions update(UserSubscriptions userSubscriptions) throws EntityNotFoundException;

    /**
	 * Deletes an existing UserSubscriptions with the given id.
	 *
	 * @param usersubscriptionsId The id of the UserSubscriptions to be deleted; value cannot be null.
	 * @return The deleted UserSubscriptions.
	 * @throws EntityNotFoundException if no UserSubscriptions found with the given id.
	 */
	UserSubscriptions delete(Integer usersubscriptionsId) throws EntityNotFoundException;

	/**
	 * Find all UserSubscriptions matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching UserSubscriptions.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<UserSubscriptions> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all UserSubscriptions matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching UserSubscriptions.
     *
     * @see Pageable
     * @see Page
	 */
    Page<UserSubscriptions> findAll(String query, Pageable pageable);

    /**
	 * Exports all UserSubscriptions matching the given input query to the given exportType format.
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
	 * Retrieve the count of the UserSubscriptions in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the UserSubscriptions.
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

