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

import com.civicxpress.cx2.Fees;
import com.civicxpress.cx2.MyCart;

/**
 * Service object for domain model class {@link Fees}.
 */
public interface FeesService {

    /**
     * Creates a new Fees. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Fees if any.
     *
     * @param fees Details of the Fees to be created; value cannot be null.
     * @return The newly created Fees.
     */
	Fees create(Fees fees);


	/**
	 * Returns Fees by given id if exists.
	 *
	 * @param feesId The id of the Fees to get; value cannot be null.
	 * @return Fees associated with the given feesId.
     * @throws EntityNotFoundException If no Fees is found.
	 */
	Fees getById(Integer feesId) throws EntityNotFoundException;

    /**
	 * Find and return the Fees by given id if exists, returns null otherwise.
	 *
	 * @param feesId The id of the Fees to get; value cannot be null.
	 * @return Fees associated with the given feesId.
	 */
	Fees findById(Integer feesId);


	/**
	 * Updates the details of an existing Fees. It replaces all fields of the existing Fees with the given fees.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on Fees if any.
     *
	 * @param fees The details of the Fees to be updated; value cannot be null.
	 * @return The updated Fees.
	 * @throws EntityNotFoundException if no Fees is found with given input.
	 */
	Fees update(Fees fees) throws EntityNotFoundException;

    /**
	 * Deletes an existing Fees with the given id.
	 *
	 * @param feesId The id of the Fees to be deleted; value cannot be null.
	 * @return The deleted Fees.
	 * @throws EntityNotFoundException if no Fees found with the given id.
	 */
	Fees delete(Integer feesId) throws EntityNotFoundException;

	/**
	 * Find all Fees matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Fees.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<Fees> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all Fees matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Fees.
     *
     * @see Pageable
     * @see Page
	 */
    Page<Fees> findAll(String query, Pageable pageable);

    /**
	 * Exports all Fees matching the given input query to the given exportType format.
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
	 * Retrieve the count of the Fees in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the Fees.
	 */
	long count(String query);

    /*
     * Returns the associated myCarts for given Fees id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated MyCart instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<MyCart> findAssociatedMyCarts(Integer id, Pageable pageable);

}

