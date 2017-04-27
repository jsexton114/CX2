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

import com.civicxpress.cx2.Fees;
import com.civicxpress.cx2.Gis2forms;
import com.civicxpress.cx2.GisTransaction;
import com.civicxpress.cx2.Giscontacts;
import com.civicxpress.cx2.Gisrecords;
import com.civicxpress.cx2.MasterInspections;
import com.civicxpress.cx2.ProjectGisrecords;

/**
 * Service object for domain model class {@link Gisrecords}.
 */
public interface GisrecordsService {

    /**
     * Creates a new Gisrecords. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Gisrecords if any.
     *
     * @param gisrecords Details of the Gisrecords to be created; value cannot be null.
     * @return The newly created Gisrecords.
     */
	Gisrecords create(Gisrecords gisrecords);


	/**
	 * Returns Gisrecords by given id if exists.
	 *
	 * @param gisrecordsId The id of the Gisrecords to get; value cannot be null.
	 * @return Gisrecords associated with the given gisrecordsId.
     * @throws EntityNotFoundException If no Gisrecords is found.
	 */
	Gisrecords getById(Integer gisrecordsId) throws EntityNotFoundException;

    /**
	 * Find and return the Gisrecords by given id if exists, returns null otherwise.
	 *
	 * @param gisrecordsId The id of the Gisrecords to get; value cannot be null.
	 * @return Gisrecords associated with the given gisrecordsId.
	 */
	Gisrecords findById(Integer gisrecordsId);


	/**
	 * Updates the details of an existing Gisrecords. It replaces all fields of the existing Gisrecords with the given gisrecords.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on Gisrecords if any.
     *
	 * @param gisrecords The details of the Gisrecords to be updated; value cannot be null.
	 * @return The updated Gisrecords.
	 * @throws EntityNotFoundException if no Gisrecords is found with given input.
	 */
	Gisrecords update(Gisrecords gisrecords) throws EntityNotFoundException;

    /**
	 * Deletes an existing Gisrecords with the given id.
	 *
	 * @param gisrecordsId The id of the Gisrecords to be deleted; value cannot be null.
	 * @return The deleted Gisrecords.
	 * @throws EntityNotFoundException if no Gisrecords found with the given id.
	 */
	Gisrecords delete(Integer gisrecordsId) throws EntityNotFoundException;

	/**
	 * Find all Gisrecords matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Gisrecords.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<Gisrecords> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all Gisrecords matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Gisrecords.
     *
     * @see Pageable
     * @see Page
	 */
    Page<Gisrecords> findAll(String query, Pageable pageable);

    /**
	 * Exports all Gisrecords matching the given input query to the given exportType format.
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
	 * Retrieve the count of the Gisrecords in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the Gisrecords.
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
     * Returns the associated feeses for given Gisrecords id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Fees instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Fees> findAssociatedFeeses(Integer id, Pageable pageable);

    /*
     * Returns the associated gis2formses for given Gisrecords id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Gis2forms instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Gis2forms> findAssociatedGis2formses(Integer id, Pageable pageable);

    /*
     * Returns the associated giscontactses for given Gisrecords id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Giscontacts instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Giscontacts> findAssociatedGiscontactses(Integer id, Pageable pageable);

    /*
     * Returns the associated gisTransactions for given Gisrecords id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated GisTransaction instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<GisTransaction> findAssociatedGisTransactions(Integer id, Pageable pageable);

    /*
     * Returns the associated masterInspectionses for given Gisrecords id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated MasterInspections instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<MasterInspections> findAssociatedMasterInspectionses(Integer id, Pageable pageable);

    /*
     * Returns the associated projectGisrecordses for given Gisrecords id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated ProjectGisrecords instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<ProjectGisrecords> findAssociatedProjectGisrecordses(Integer id, Pageable pageable);

}

