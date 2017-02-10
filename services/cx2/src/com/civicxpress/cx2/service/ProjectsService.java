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

import com.civicxpress.cx2.ProjectSharedWith;
import com.civicxpress.cx2.Projects;

/**
 * Service object for domain model class {@link Projects}.
 */
public interface ProjectsService {

    /**
     * Creates a new Projects. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Projects if any.
     *
     * @param projects Details of the Projects to be created; value cannot be null.
     * @return The newly created Projects.
     */
	Projects create(Projects projects);


	/**
	 * Returns Projects by given id if exists.
	 *
	 * @param projectsId The id of the Projects to get; value cannot be null.
	 * @return Projects associated with the given projectsId.
     * @throws EntityNotFoundException If no Projects is found.
	 */
	Projects getById(String projectsId) throws EntityNotFoundException;

    /**
	 * Find and return the Projects by given id if exists, returns null otherwise.
	 *
	 * @param projectsId The id of the Projects to get; value cannot be null.
	 * @return Projects associated with the given projectsId.
	 */
	Projects findById(String projectsId);


	/**
	 * Updates the details of an existing Projects. It replaces all fields of the existing Projects with the given projects.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on Projects if any.
     *
	 * @param projects The details of the Projects to be updated; value cannot be null.
	 * @return The updated Projects.
	 * @throws EntityNotFoundException if no Projects is found with given input.
	 */
	Projects update(Projects projects) throws EntityNotFoundException;

    /**
	 * Deletes an existing Projects with the given id.
	 *
	 * @param projectsId The id of the Projects to be deleted; value cannot be null.
	 * @return The deleted Projects.
	 * @throws EntityNotFoundException if no Projects found with the given id.
	 */
	Projects delete(String projectsId) throws EntityNotFoundException;

	/**
	 * Find all Projects matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Projects.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<Projects> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all Projects matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Projects.
     *
     * @see Pageable
     * @see Page
	 */
    Page<Projects> findAll(String query, Pageable pageable);

    /**
	 * Exports all Projects matching the given input query to the given exportType format.
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
	 * Retrieve the count of the Projects in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the Projects.
	 */
	long count(String query);

    /*
     * Returns the associated projectSharedWiths for given Projects id.
     *
     * @param projectGuid value of projectGuid; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated ProjectSharedWith instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<ProjectSharedWith> findAssociatedProjectSharedWiths(String projectGuid, Pageable pageable);

}
