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

import com.civicxpress.cx2.FormCategories;
import com.civicxpress.cx2.FormCategoryMapping;

/**
 * Service object for domain model class {@link FormCategories}.
 */
public interface FormCategoriesService {

    /**
     * Creates a new FormCategories. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on FormCategories if any.
     *
     * @param formCategories Details of the FormCategories to be created; value cannot be null.
     * @return The newly created FormCategories.
     */
	FormCategories create(FormCategories formCategories);


	/**
	 * Returns FormCategories by given id if exists.
	 *
	 * @param formcategoriesId The id of the FormCategories to get; value cannot be null.
	 * @return FormCategories associated with the given formcategoriesId.
     * @throws EntityNotFoundException If no FormCategories is found.
	 */
	FormCategories getById(Integer formcategoriesId) throws EntityNotFoundException;

    /**
	 * Find and return the FormCategories by given id if exists, returns null otherwise.
	 *
	 * @param formcategoriesId The id of the FormCategories to get; value cannot be null.
	 * @return FormCategories associated with the given formcategoriesId.
	 */
	FormCategories findById(Integer formcategoriesId);


	/**
	 * Updates the details of an existing FormCategories. It replaces all fields of the existing FormCategories with the given formCategories.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on FormCategories if any.
     *
	 * @param formCategories The details of the FormCategories to be updated; value cannot be null.
	 * @return The updated FormCategories.
	 * @throws EntityNotFoundException if no FormCategories is found with given input.
	 */
	FormCategories update(FormCategories formCategories) throws EntityNotFoundException;

    /**
	 * Deletes an existing FormCategories with the given id.
	 *
	 * @param formcategoriesId The id of the FormCategories to be deleted; value cannot be null.
	 * @return The deleted FormCategories.
	 * @throws EntityNotFoundException if no FormCategories found with the given id.
	 */
	FormCategories delete(Integer formcategoriesId) throws EntityNotFoundException;

	/**
	 * Find all FormCategories matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching FormCategories.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<FormCategories> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all FormCategories matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching FormCategories.
     *
     * @see Pageable
     * @see Page
	 */
    Page<FormCategories> findAll(String query, Pageable pageable);

    /**
	 * Exports all FormCategories matching the given input query to the given exportType format.
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
	 * Retrieve the count of the FormCategories in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the FormCategories.
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
     * Returns the associated formCategoryMappings for given FormCategories id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated FormCategoryMapping instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<FormCategoryMapping> findAssociatedFormCategoryMappings(Integer id, Pageable pageable);

}

