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

import com.civicxpress.cx2.InspectionCategoryMapping;

/**
 * Service object for domain model class {@link InspectionCategoryMapping}.
 */
public interface InspectionCategoryMappingService {

    /**
     * Creates a new InspectionCategoryMapping. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on InspectionCategoryMapping if any.
     *
     * @param inspectionCategoryMapping Details of the InspectionCategoryMapping to be created; value cannot be null.
     * @return The newly created InspectionCategoryMapping.
     */
	InspectionCategoryMapping create(InspectionCategoryMapping inspectionCategoryMapping);


	/**
	 * Returns InspectionCategoryMapping by given id if exists.
	 *
	 * @param inspectioncategorymappingId The id of the InspectionCategoryMapping to get; value cannot be null.
	 * @return InspectionCategoryMapping associated with the given inspectioncategorymappingId.
     * @throws EntityNotFoundException If no InspectionCategoryMapping is found.
	 */
	InspectionCategoryMapping getById(Integer inspectioncategorymappingId) throws EntityNotFoundException;

    /**
	 * Find and return the InspectionCategoryMapping by given id if exists, returns null otherwise.
	 *
	 * @param inspectioncategorymappingId The id of the InspectionCategoryMapping to get; value cannot be null.
	 * @return InspectionCategoryMapping associated with the given inspectioncategorymappingId.
	 */
	InspectionCategoryMapping findById(Integer inspectioncategorymappingId);

    /**
	 * Find and return the InspectionCategoryMapping for given inspectionCategoryId  andinspectionDesignId  if exists.
	 *
	 * @param inspectionCategoryId value of inspectionCategoryId; value cannot be null.
	 * @param inspectionDesignId value of inspectionDesignId; value cannot be null.
	 * @return InspectionCategoryMapping associated with the given inputs.
     * @throws EntityNotFoundException if no matching InspectionCategoryMapping found.
	 */
    InspectionCategoryMapping getByInspectionCategoryIdAndInspectionDesignId(Integer inspectionCategoryId, Integer inspectionDesignId)throws EntityNotFoundException;

	/**
	 * Updates the details of an existing InspectionCategoryMapping. It replaces all fields of the existing InspectionCategoryMapping with the given inspectionCategoryMapping.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on InspectionCategoryMapping if any.
     *
	 * @param inspectionCategoryMapping The details of the InspectionCategoryMapping to be updated; value cannot be null.
	 * @return The updated InspectionCategoryMapping.
	 * @throws EntityNotFoundException if no InspectionCategoryMapping is found with given input.
	 */
	InspectionCategoryMapping update(InspectionCategoryMapping inspectionCategoryMapping) throws EntityNotFoundException;

    /**
	 * Deletes an existing InspectionCategoryMapping with the given id.
	 *
	 * @param inspectioncategorymappingId The id of the InspectionCategoryMapping to be deleted; value cannot be null.
	 * @return The deleted InspectionCategoryMapping.
	 * @throws EntityNotFoundException if no InspectionCategoryMapping found with the given id.
	 */
	InspectionCategoryMapping delete(Integer inspectioncategorymappingId) throws EntityNotFoundException;

	/**
	 * Find all InspectionCategoryMappings matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching InspectionCategoryMappings.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<InspectionCategoryMapping> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all InspectionCategoryMappings matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching InspectionCategoryMappings.
     *
     * @see Pageable
     * @see Page
	 */
    Page<InspectionCategoryMapping> findAll(String query, Pageable pageable);

    /**
	 * Exports all InspectionCategoryMappings matching the given input query to the given exportType format.
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
	 * Retrieve the count of the InspectionCategoryMappings in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the InspectionCategoryMapping.
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

