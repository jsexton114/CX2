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

import com.civicxpress.cx2.ProjectForms;

/**
 * Service object for domain model class {@link ProjectForms}.
 */
public interface ProjectFormsService {

    /**
     * Creates a new ProjectForms. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on ProjectForms if any.
     *
     * @param projectForms Details of the ProjectForms to be created; value cannot be null.
     * @return The newly created ProjectForms.
     */
	ProjectForms create(ProjectForms projectForms);


	/**
	 * Returns ProjectForms by given id if exists.
	 *
	 * @param projectformsId The id of the ProjectForms to get; value cannot be null.
	 * @return ProjectForms associated with the given projectformsId.
     * @throws EntityNotFoundException If no ProjectForms is found.
	 */
	ProjectForms getById(Integer projectformsId) throws EntityNotFoundException;

    /**
	 * Find and return the ProjectForms by given id if exists, returns null otherwise.
	 *
	 * @param projectformsId The id of the ProjectForms to get; value cannot be null.
	 * @return ProjectForms associated with the given projectformsId.
	 */
	ProjectForms findById(Integer projectformsId);


	/**
	 * Updates the details of an existing ProjectForms. It replaces all fields of the existing ProjectForms with the given projectForms.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on ProjectForms if any.
     *
	 * @param projectForms The details of the ProjectForms to be updated; value cannot be null.
	 * @return The updated ProjectForms.
	 * @throws EntityNotFoundException if no ProjectForms is found with given input.
	 */
	ProjectForms update(ProjectForms projectForms) throws EntityNotFoundException;

    /**
	 * Deletes an existing ProjectForms with the given id.
	 *
	 * @param projectformsId The id of the ProjectForms to be deleted; value cannot be null.
	 * @return The deleted ProjectForms.
	 * @throws EntityNotFoundException if no ProjectForms found with the given id.
	 */
	ProjectForms delete(Integer projectformsId) throws EntityNotFoundException;

	/**
	 * Find all ProjectForms matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching ProjectForms.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<ProjectForms> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all ProjectForms matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching ProjectForms.
     *
     * @see Pageable
     * @see Page
	 */
    Page<ProjectForms> findAll(String query, Pageable pageable);

    /**
	 * Exports all ProjectForms matching the given input query to the given exportType format.
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
	 * Retrieve the count of the ProjectForms in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the ProjectForms.
	 */
	long count(String query);


}

