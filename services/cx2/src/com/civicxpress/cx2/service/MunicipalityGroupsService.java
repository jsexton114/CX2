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

import com.civicxpress.cx2.FormStatuses;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.MunicipalityGroupMembers;
import com.civicxpress.cx2.MunicipalityGroups;

/**
 * Service object for domain model class {@link MunicipalityGroups}.
 */
public interface MunicipalityGroupsService {

    /**
     * Creates a new MunicipalityGroups. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on MunicipalityGroups if any.
     *
     * @param municipalityGroups Details of the MunicipalityGroups to be created; value cannot be null.
     * @return The newly created MunicipalityGroups.
     */
	MunicipalityGroups create(MunicipalityGroups municipalityGroups);


	/**
	 * Returns MunicipalityGroups by given id if exists.
	 *
	 * @param municipalitygroupsId The id of the MunicipalityGroups to get; value cannot be null.
	 * @return MunicipalityGroups associated with the given municipalitygroupsId.
     * @throws EntityNotFoundException If no MunicipalityGroups is found.
	 */
	MunicipalityGroups getById(Integer municipalitygroupsId) throws EntityNotFoundException;

    /**
	 * Find and return the MunicipalityGroups by given id if exists, returns null otherwise.
	 *
	 * @param municipalitygroupsId The id of the MunicipalityGroups to get; value cannot be null.
	 * @return MunicipalityGroups associated with the given municipalitygroupsId.
	 */
	MunicipalityGroups findById(Integer municipalitygroupsId);


	/**
	 * Updates the details of an existing MunicipalityGroups. It replaces all fields of the existing MunicipalityGroups with the given municipalityGroups.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on MunicipalityGroups if any.
     *
	 * @param municipalityGroups The details of the MunicipalityGroups to be updated; value cannot be null.
	 * @return The updated MunicipalityGroups.
	 * @throws EntityNotFoundException if no MunicipalityGroups is found with given input.
	 */
	MunicipalityGroups update(MunicipalityGroups municipalityGroups) throws EntityNotFoundException;

    /**
	 * Deletes an existing MunicipalityGroups with the given id.
	 *
	 * @param municipalitygroupsId The id of the MunicipalityGroups to be deleted; value cannot be null.
	 * @return The deleted MunicipalityGroups.
	 * @throws EntityNotFoundException if no MunicipalityGroups found with the given id.
	 */
	MunicipalityGroups delete(Integer municipalitygroupsId) throws EntityNotFoundException;

	/**
	 * Find all MunicipalityGroups matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching MunicipalityGroups.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<MunicipalityGroups> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all MunicipalityGroups matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching MunicipalityGroups.
     *
     * @see Pageable
     * @see Page
	 */
    Page<MunicipalityGroups> findAll(String query, Pageable pageable);

    /**
	 * Exports all MunicipalityGroups matching the given input query to the given exportType format.
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
	 * Retrieve the count of the MunicipalityGroups in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the MunicipalityGroups.
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
     * Returns the associated formStatusesesForWriteAccess for given MunicipalityGroups id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated FormStatuses instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<FormStatuses> findAssociatedFormStatusesesForWriteAccess(Integer id, Pageable pageable);

    /*
     * Returns the associated formStatusesesForProcessOwners for given MunicipalityGroups id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated FormStatuses instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<FormStatuses> findAssociatedFormStatusesesForProcessOwners(Integer id, Pageable pageable);

    /*
     * Returns the associated masterFormses for given MunicipalityGroups id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated MasterForms instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<MasterForms> findAssociatedMasterFormses(Integer id, Pageable pageable);

    /*
     * Returns the associated municipalityGroupMemberses for given MunicipalityGroups id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated MunicipalityGroupMembers instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<MunicipalityGroupMembers> findAssociatedMunicipalityGroupMemberses(Integer id, Pageable pageable);

}

