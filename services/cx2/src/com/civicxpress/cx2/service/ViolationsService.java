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

import com.civicxpress.cx2.Violations;

/**
 * Service object for domain model class {@link Violations}.
 */
public interface ViolationsService {

    /**
     * Creates a new Violations. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Violations if any.
     *
     * @param violations Details of the Violations to be created; value cannot be null.
     * @return The newly created Violations.
     */
	Violations create(Violations violations);


	/**
	 * Returns Violations by given id if exists.
	 *
	 * @param violationsId The id of the Violations to get; value cannot be null.
	 * @return Violations associated with the given violationsId.
     * @throws EntityNotFoundException If no Violations is found.
	 */
	Violations getById(Integer violationsId) throws EntityNotFoundException;

    /**
	 * Find and return the Violations by given id if exists, returns null otherwise.
	 *
	 * @param violationsId The id of the Violations to get; value cannot be null.
	 * @return Violations associated with the given violationsId.
	 */
	Violations findById(Integer violationsId);


	/**
	 * Updates the details of an existing Violations. It replaces all fields of the existing Violations with the given violations.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on Violations if any.
     *
	 * @param violations The details of the Violations to be updated; value cannot be null.
	 * @return The updated Violations.
	 * @throws EntityNotFoundException if no Violations is found with given input.
	 */
	Violations update(Violations violations) throws EntityNotFoundException;

    /**
	 * Deletes an existing Violations with the given id.
	 *
	 * @param violationsId The id of the Violations to be deleted; value cannot be null.
	 * @return The deleted Violations.
	 * @throws EntityNotFoundException if no Violations found with the given id.
	 */
	Violations delete(Integer violationsId) throws EntityNotFoundException;

	/**
	 * Find all Violations matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Violations.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<Violations> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all Violations matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Violations.
     *
     * @see Pageable
     * @see Page
	 */
    Page<Violations> findAll(String query, Pageable pageable);

    /**
	 * Exports all Violations matching the given input query to the given exportType format.
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
	 * Retrieve the count of the Violations in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the Violations.
	 */
	long count(String query);


}

