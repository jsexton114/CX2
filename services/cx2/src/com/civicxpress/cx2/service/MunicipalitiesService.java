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

import com.civicxpress.cx2.CaseTypes;
import com.civicxpress.cx2.CodeSets;
import com.civicxpress.cx2.Document;
import com.civicxpress.cx2.Fees;
import com.civicxpress.cx2.FormCategories;
import com.civicxpress.cx2.FormTypes;
import com.civicxpress.cx2.Gisrecords;
import com.civicxpress.cx2.Holidays;
import com.civicxpress.cx2.InspectionCategories;
import com.civicxpress.cx2.InspectionDesign;
import com.civicxpress.cx2.ManualFeeTypes;
import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Municipalities;
import com.civicxpress.cx2.MunicipalityGroups;
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.Roles;
import com.civicxpress.cx2.Subdivisions;
import com.civicxpress.cx2.UserSubscriptions;
import com.civicxpress.cx2.VendorApprovals;

/**
 * Service object for domain model class {@link Municipalities}.
 */
public interface MunicipalitiesService {

    /**
     * Creates a new Municipalities. It does cascade insert for all the children in a single transaction.
     *
     * This method overrides the input field values using Server side or database managed properties defined on Municipalities if any.
     *
     * @param municipalities Details of the Municipalities to be created; value cannot be null.
     * @return The newly created Municipalities.
     */
	Municipalities create(Municipalities municipalities);


	/**
	 * Returns Municipalities by given id if exists.
	 *
	 * @param municipalitiesId The id of the Municipalities to get; value cannot be null.
	 * @return Municipalities associated with the given municipalitiesId.
     * @throws EntityNotFoundException If no Municipalities is found.
	 */
	Municipalities getById(Integer municipalitiesId) throws EntityNotFoundException;

    /**
	 * Find and return the Municipalities by given id if exists, returns null otherwise.
	 *
	 * @param municipalitiesId The id of the Municipalities to get; value cannot be null.
	 * @return Municipalities associated with the given municipalitiesId.
	 */
	Municipalities findById(Integer municipalitiesId);


	/**
	 * Updates the details of an existing Municipalities. It replaces all fields of the existing Municipalities with the given municipalities.
	 *
     * This method overrides the input field values using Server side or database managed properties defined on Municipalities if any.
     *
	 * @param municipalities The details of the Municipalities to be updated; value cannot be null.
	 * @return The updated Municipalities.
	 * @throws EntityNotFoundException if no Municipalities is found with given input.
	 */
	Municipalities update(Municipalities municipalities) throws EntityNotFoundException;

    /**
	 * Deletes an existing Municipalities with the given id.
	 *
	 * @param municipalitiesId The id of the Municipalities to be deleted; value cannot be null.
	 * @return The deleted Municipalities.
	 * @throws EntityNotFoundException if no Municipalities found with the given id.
	 */
	Municipalities delete(Integer municipalitiesId) throws EntityNotFoundException;

	/**
	 * Find all Municipalities matching the given QueryFilter(s).
     * All the QueryFilter(s) are ANDed to filter the results.
     * This method returns Paginated results.
	 *
     * @deprecated Use {@link #findAll(String, Pageable)} instead.
	 *
     * @param queryFilters Array of queryFilters to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Municipalities.
     *
     * @see QueryFilter
     * @see Pageable
     * @see Page
	 */
    @Deprecated
	Page<Municipalities> findAll(QueryFilter[] queryFilters, Pageable pageable);

    /**
	 * Find all Municipalities matching the given input query. This method returns Paginated results.
     * Note: Go through the documentation for <u>query</u> syntax.
	 *
     * @param query The query to filter the results; No filters applied if the input is null/empty.
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of matching Municipalities.
     *
     * @see Pageable
     * @see Page
	 */
    Page<Municipalities> findAll(String query, Pageable pageable);

    /**
	 * Exports all Municipalities matching the given input query to the given exportType format.
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
	 * Retrieve the count of the Municipalities in the repository with matching query.
     * Note: Go through the documentation for <u>query</u> syntax.
     *
     * @param query query to filter results. No filters applied if the input is null/empty.
	 * @return The count of the Municipalities.
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
     * Returns the associated codeSetses for given Municipalities id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated CodeSets instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<CodeSets> findAssociatedCodeSetses(Integer id, Pageable pageable);

    /*
     * Returns the associated caseTypeses for given Municipalities id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated CaseTypes instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<CaseTypes> findAssociatedCaseTypeses(Integer id, Pageable pageable);

    /*
     * Returns the associated documents for given Municipalities id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Document instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Document> findAssociatedDocuments(Integer id, Pageable pageable);

    /*
     * Returns the associated feeses for given Municipalities id.
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
     * Returns the associated formCategorieses for given Municipalities id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated FormCategories instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<FormCategories> findAssociatedFormCategorieses(Integer id, Pageable pageable);

    /*
     * Returns the associated formTypeses for given Municipalities id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated FormTypes instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<FormTypes> findAssociatedFormTypeses(Integer id, Pageable pageable);

    /*
     * Returns the associated gisrecordses for given Municipalities id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Gisrecords instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Gisrecords> findAssociatedGisrecordses(Integer id, Pageable pageable);

    /*
     * Returns the associated inspectionDesigns for given Municipalities id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated InspectionDesign instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<InspectionDesign> findAssociatedInspectionDesigns(Integer id, Pageable pageable);

    /*
     * Returns the associated holidayses for given Municipalities id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Holidays instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Holidays> findAssociatedHolidayses(Integer id, Pageable pageable);

    /*
     * Returns the associated inspectionCategorieses for given Municipalities id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated InspectionCategories instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<InspectionCategories> findAssociatedInspectionCategorieses(Integer id, Pageable pageable);

    /*
     * Returns the associated masterFormses for given Municipalities id.
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
     * Returns the associated manualFeeTypeses for given Municipalities id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated ManualFeeTypes instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<ManualFeeTypes> findAssociatedManualFeeTypeses(Integer id, Pageable pageable);

    /*
     * Returns the associated municipalityGroupses for given Municipalities id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated MunicipalityGroups instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<MunicipalityGroups> findAssociatedMunicipalityGroupses(Integer id, Pageable pageable);

    /*
     * Returns the associated projectses for given Municipalities id.
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
     * Returns the associated roleses for given Municipalities id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Roles instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Roles> findAssociatedRoleses(Integer id, Pageable pageable);

    /*
     * Returns the associated subdivisionses for given Municipalities id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated Subdivisions instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<Subdivisions> findAssociatedSubdivisionses(Integer id, Pageable pageable);

    /*
     * Returns the associated userSubscriptionses for given Municipalities id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated UserSubscriptions instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<UserSubscriptions> findAssociatedUserSubscriptionses(Integer id, Pageable pageable);

    /*
     * Returns the associated vendorApprovalses for given Municipalities id.
     *
     * @param id value of id; value cannot be null
     * @param pageable Details of the pagination information along with the sorting options. If null returns all matching records.
     * @return Paginated list of associated VendorApprovals instances.
     *
     * @see Pageable
     * @see Page
     */
    Page<VendorApprovals> findAssociatedVendorApprovalses(Integer id, Pageable pageable);

}

