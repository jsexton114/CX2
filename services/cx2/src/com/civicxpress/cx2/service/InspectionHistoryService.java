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

import com.civicxpress.cx2.InspectionHistory;

/**
 * Service object for domain model class {@link InspectionHistory}.
 */
public interface InspectionHistoryService {

    /**
     * Creates a new InspectionHistory. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on InspectionHistory if any.
     *
     * @param inspectionHistory Details of the InspectionHistory to be created; value cannot be null.
     * @return The newly created InspectionHistory.
     */
	InspectionHistory create(InspectionHistory inspectionHistory);


	/**
	 * Returns InspectionHistory by given id if exists.
	 *
	 * @param inspectionhistoryId The id of the InspectionHistory to get; value cannot be null.
	 * @return InspectionHistory associated with the given inspectionhistoryId.
     * @throws EntityNotFoundException If no InspectionHistory is found.
	 */
	InspectionHistory getById(Integer inspectionhistoryId) throws EntityNotFoundException;

    /**
	 * Find and return the InspectionHistory by given id if exists, returns null otherwise.
	 *
	 * @param inspectionhistoryId The id of the InspectionHistory to get; value cannot be null.
	 * @return InspectionHistory associated with the given inspectionhistoryId.
	 */
	InspectionHistory findById(Integer inspectionhistoryId);


	/**
	 * Updates the details of an existing InspectionHistory. It replaces all fields of the existing InspectionHistory with the given inspectionHistory.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on InspectionHistory if any.
     *
	 * @param inspectionHistory The details of the InspectionHistory to be updated; value cannot be null.
	 * @return The updated InspectionHistory.
	 * @throws EntityNotFoundException if no InspectionHistory is found with given input.
	 */
	InspectionHistory update(InspectionHistory inspectionHistory) throws EntityNotFoundException;

    /**
	 * Deletes an existing InspectionHistory with the given id.
	 *
	 * @param inspectionhistoryId The id of the InspectionHistory to be deleted; value cannot be null.
	 * @return The deleted InspectionHistory.
	 * @throws EntityNotFoundException if no InspectionHistory found with the given id.
	 */
	InspectionHistory delete(Integer inspectionhistoryId) throws EntityNotFoundException;

	/**
	 * Find all InspectionHistories matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching InspectionHistories.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<InspectionHistory> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all InspectionHistories matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching InspectionHistories.
     *
     * @see Pageable
     * @see Page
	 */
    Page<InspectionHistory> findAll(String query, Pageable pageable);

    /**
	 * Exports all InspectionHistories matching the given input query to the given exportType format.
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
	 * Retrieve the count of the InspectionHistories in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the InspectionHistory.
	 */
	long count(String query);


}
