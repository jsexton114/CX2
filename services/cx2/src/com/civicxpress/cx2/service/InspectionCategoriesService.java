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

import com.civicxpress.cx2.FormToInspectionCategoryMapping;
import com.civicxpress.cx2.InspectionCategories;
import com.civicxpress.cx2.InspectionCategoryMapping;
import com.civicxpress.cx2.MasterInspections;

/**
 * Service object for domain model class {@link InspectionCategories}.
 */
public interface InspectionCategoriesService {

    /**
     * Creates a new InspectionCategories. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on InspectionCategories if any.
     *
     * @param inspectionCategories Details of the InspectionCategories to be created; value cannot be null.
     * @return The newly created InspectionCategories.
     */
	InspectionCategories create(InspectionCategories inspectionCategories);


	/**
	 * Returns InspectionCategories by given id if exists.
	 *
	 * @param inspectioncategoriesId The id of the InspectionCategories to get; value cannot be null.
	 * @return InspectionCategories associated with the given inspectioncategoriesId.
     * @throws EntityNotFoundException If no InspectionCategories is found.
	 */
	InspectionCategories getById(Integer inspectioncategoriesId) throws EntityNotFoundException;

    /**
	 * Find and return the InspectionCategories by given id if exists, returns null otherwise.
	 *
	 * @param inspectioncategoriesId The id of the InspectionCategories to get; value cannot be null.
	 * @return InspectionCategories associated with the given inspectioncategoriesId.
	 */
	InspectionCategories findById(Integer inspectioncategoriesId);


	/**
	 * Updates the details of an existing InspectionCategories. It replaces all fields of the existing InspectionCategories with the given inspectionCategories.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on InspectionCategories if any.
     *
	 * @param inspectionCategories The details of the InspectionCategories to be updated; value cannot be null.
	 * @return The updated InspectionCategories.
	 * @throws EntityNotFoundException if no InspectionCategories is found with given input.
	 */
	InspectionCategories update(InspectionCategories inspectionCategories) throws EntityNotFoundException;

    /**
	 * Deletes an existing InspectionCategories with the given id.
	 *
	 * @param inspectioncategoriesId The id of the InspectionCategories to be deleted; value cannot be null.
	 * @return The deleted InspectionCategories.
	 * @throws EntityNotFoundException if no InspectionCategories found with the given id.
	 */
	InspectionCategories delete(Integer inspectioncategoriesId) throws EntityNotFoundException;

	/**
	 * Find all InspectionCategories matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching InspectionCategories.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<InspectionCategories> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all InspectionCategories matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching InspectionCategories.
     *
     * @see Pageable
     * @see Page
	 */
    Page<InspectionCategories> findAll(String query, Pageable pageable);

    /**
	 * Exports all InspectionCategories matching the given input query to the given exportType format.
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
	 * Retrieve the count of the InspectionCategories in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the InspectionCategories.
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
     * Returns the associated formToInspectionCategoryMappings for given InspectionCategories id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated FormToInspectionCategoryMapping instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<FormToInspectionCategoryMapping> findAssociatedFormToInspectionCategoryMappings(Integer id, Pageable pageable);

    /*
     * Returns the associated inspectionCategoryMappings for given InspectionCategories id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated InspectionCategoryMapping instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<InspectionCategoryMapping> findAssociatedInspectionCategoryMappings(Integer id, Pageable pageable);

    /*
     * Returns the associated masterInspectionses for given InspectionCategories id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated MasterInspections instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<MasterInspections> findAssociatedMasterInspectionses(Integer id, Pageable pageable);

}

