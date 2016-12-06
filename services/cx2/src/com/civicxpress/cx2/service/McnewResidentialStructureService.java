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

import com.civicxpress.cx2.McnewResidentialStructure;

/**
 * Service object for domain model class {@link McnewResidentialStructure}.
 */
public interface McnewResidentialStructureService {

    /**
     * Creates a new McnewResidentialStructure. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on McnewResidentialStructure if any.
     *
     * @param mcnewResidentialStructure Details of the McnewResidentialStructure to be created; value cannot be null.
     * @return The newly created McnewResidentialStructure.
     */
	McnewResidentialStructure create(McnewResidentialStructure mcnewResidentialStructure);


	/**
	 * Returns McnewResidentialStructure by given id if exists.
	 *
	 * @param mcnewresidentialstructureId The id of the McnewResidentialStructure to get; value cannot be null.
	 * @return McnewResidentialStructure associated with the given mcnewresidentialstructureId.
     * @throws EntityNotFoundException If no McnewResidentialStructure is found.
	 */
	McnewResidentialStructure getById(Integer mcnewresidentialstructureId) throws EntityNotFoundException;

    /**
	 * Find and return the McnewResidentialStructure by given id if exists, returns null otherwise.
	 *
	 * @param mcnewresidentialstructureId The id of the McnewResidentialStructure to get; value cannot be null.
	 * @return McnewResidentialStructure associated with the given mcnewresidentialstructureId.
	 */
	McnewResidentialStructure findById(Integer mcnewresidentialstructureId);


	/**
	 * Updates the details of an existing McnewResidentialStructure. It replaces all fields of the existing McnewResidentialStructure with the given mcnewResidentialStructure.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on McnewResidentialStructure if any.
     *
	 * @param mcnewResidentialStructure The details of the McnewResidentialStructure to be updated; value cannot be null.
	 * @return The updated McnewResidentialStructure.
	 * @throws EntityNotFoundException if no McnewResidentialStructure is found with given input.
	 */
	McnewResidentialStructure update(McnewResidentialStructure mcnewResidentialStructure) throws EntityNotFoundException;

    /**
	 * Deletes an existing McnewResidentialStructure with the given id.
	 *
	 * @param mcnewresidentialstructureId The id of the McnewResidentialStructure to be deleted; value cannot be null.
	 * @return The deleted McnewResidentialStructure.
	 * @throws EntityNotFoundException if no McnewResidentialStructure found with the given id.
	 */
	McnewResidentialStructure delete(Integer mcnewresidentialstructureId) throws EntityNotFoundException;

	/**
	 * Find all McnewResidentialStructures matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching McnewResidentialStructures.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<McnewResidentialStructure> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all McnewResidentialStructures matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching McnewResidentialStructures.
     *
     * @see Pageable
     * @see Page
	 */
    Page<McnewResidentialStructure> findAll(String query, Pageable pageable);

    /**
	 * Exports all McnewResidentialStructures matching the given input query to the given exportType format.
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
	 * Retrieve the count of the McnewResidentialStructures in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the McnewResidentialStructure.
	 */
	long count(String query);


}

