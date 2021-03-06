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
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.Vendor;
import com.civicxpress.cx2.VendorAdmins;
import com.civicxpress.cx2.VendorApprovals;
import com.civicxpress.cx2.VendorLicenses;
import com.civicxpress.cx2.VendorUsers;
import com.civicxpress.cx2.Vendors2form;
import com.civicxpress.cx2.VendorsToProject;

/**
 * Service object for domain model class {@link Vendor}.
 */
public interface VendorService {

    /**
     * Creates a new Vendor. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Vendor if any.
     *
     * @param vendor Details of the Vendor to be created; value cannot be null.
     * @return The newly created Vendor.
     */
	Vendor create(Vendor vendor);


	/**
	 * Returns Vendor by given id if exists.
	 *
	 * @param vendorId The id of the Vendor to get; value cannot be null.
	 * @return Vendor associated with the given vendorId.
     * @throws EntityNotFoundException If no Vendor is found.
	 */
	Vendor getById(Integer vendorId) throws EntityNotFoundException;

    /**
	 * Find and return the Vendor by given id if exists, returns null otherwise.
	 *
	 * @param vendorId The id of the Vendor to get; value cannot be null.
	 * @return Vendor associated with the given vendorId.
	 */
	Vendor findById(Integer vendorId);

    /**
	 * Find and return the Vendor for given companyEmail  if exists.
	 *
	 * @param companyEmail value of companyEmail; value cannot be null.
	 * @return Vendor associated with the given inputs.
     * @throws EntityNotFoundException if no matching Vendor found.
	 */
    Vendor getByCompanyEmail(String companyEmail)throws EntityNotFoundException;

	/**
	 * Updates the details of an existing Vendor. It replaces all fields of the existing Vendor with the given vendor.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on Vendor if any.
     *
	 * @param vendor The details of the Vendor to be updated; value cannot be null.
	 * @return The updated Vendor.
	 * @throws EntityNotFoundException if no Vendor is found with given input.
	 */
	Vendor update(Vendor vendor) throws EntityNotFoundException;

    /**
	 * Deletes an existing Vendor with the given id.
	 *
	 * @param vendorId The id of the Vendor to be deleted; value cannot be null.
	 * @return The deleted Vendor.
	 * @throws EntityNotFoundException if no Vendor found with the given id.
	 */
	Vendor delete(Integer vendorId) throws EntityNotFoundException;

	/**
	 * Find all Vendors matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Vendors.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<Vendor> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all Vendors matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Vendors.
     *
     * @see Pageable
     * @see Page
	 */
    Page<Vendor> findAll(String query, Pageable pageable);

    /**
	 * Exports all Vendors matching the given input query to the given exportType format.
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
	 * Retrieve the count of the Vendors in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the Vendor.
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
     * Returns the associated feeses for given Vendor id.
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
     * Returns the associated masterFormses for given Vendor id.
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
     * Returns the associated projectses for given Vendor id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Projects instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Projects> findAssociatedProjectses(Integer id, Pageable pageable);

    /*
     * Returns the associated vendorApprovalses for given Vendor id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated VendorApprovals instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<VendorApprovals> findAssociatedVendorApprovalses(Integer id, Pageable pageable);

    /*
     * Returns the associated vendorLicenseses for given Vendor id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated VendorLicenses instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<VendorLicenses> findAssociatedVendorLicenseses(Integer id, Pageable pageable);

    /*
     * Returns the associated vendorAdminses for given Vendor id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated VendorAdmins instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<VendorAdmins> findAssociatedVendorAdminses(Integer id, Pageable pageable);

    /*
     * Returns the associated vendors2forms for given Vendor id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Vendors2form instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Vendors2form> findAssociatedVendors2forms(Integer id, Pageable pageable);

    /*
     * Returns the associated vendorsToProjects for given Vendor id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated VendorsToProject instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<VendorsToProject> findAssociatedVendorsToProjects(Integer id, Pageable pageable);

    /*
     * Returns the associated vendorUserses for given Vendor id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated VendorUsers instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<VendorUsers> findAssociatedVendorUserses(Integer id, Pageable pageable);

}

