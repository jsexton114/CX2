/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.data.model.AggregationInfo;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.civicxpress.cx2.Fees;
import com.civicxpress.cx2.FormMessages;
import com.civicxpress.cx2.MasterCases;
import com.civicxpress.cx2.MasterInspections;
import com.civicxpress.cx2.ProjectForms;
import com.civicxpress.cx2.ProjectGisrecords;
import com.civicxpress.cx2.ProjectSharedWith;
import com.civicxpress.cx2.ProjectTasks;
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.VendorsToProject;
import com.civicxpress.cx2.service.ProjectsService;


/**
 * Controller object for domain model class Projects.
 * @see Projects
 */
@RestController("cx2.ProjectsController")
@Api(value = "ProjectsController", description = "Exposes APIs to work with Projects resource.")
@RequestMapping("/cx2/Projects")
public class ProjectsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectsController.class);

    @Autowired
	@Qualifier("cx2.ProjectsService")
	private ProjectsService projectsService;

	@ApiOperation(value = "Creates a new Projects instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Projects createProjects(@RequestBody Projects projects) {
		LOGGER.debug("Create Projects with information: {}" , projects);

		projects = projectsService.create(projects);
		LOGGER.debug("Created Projects with information: {}" , projects);

	    return projects;
	}


    @ApiOperation(value = "Returns the Projects instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Projects getProjects(@PathVariable("id") String id) throws EntityNotFoundException {
        LOGGER.debug("Getting Projects with id: {}" , id);

        Projects foundProjects = projectsService.getById(id);
        LOGGER.debug("Projects details with id: {}" , foundProjects);

        return foundProjects;
    }

    @ApiOperation(value = "Updates the Projects instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Projects editProjects(@PathVariable("id") String id, @RequestBody Projects projects) throws EntityNotFoundException {
        LOGGER.debug("Editing Projects with id: {}" , projects.getProjectGuid());

        projects.setProjectGuid(id);
        projects = projectsService.update(projects);
        LOGGER.debug("Projects details with id: {}" , projects);

        return projects;
    }

    @ApiOperation(value = "Deletes the Projects instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteProjects(@PathVariable("id") String id) throws EntityNotFoundException {
        LOGGER.debug("Deleting Projects with id: {}" , id);

        Projects deletedProjects = projectsService.delete(id);

        return deletedProjects != null;
    }

    /**
     * @deprecated Use {@link #findProjects(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Projects instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Projects> searchProjectsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Projects list");
        return projectsService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Projects instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Projects> findProjects(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Projects list");
        return projectsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Projects instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Projects> filterProjects(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Projects list");
        return projectsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportProjects(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return projectsService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of Projects instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countProjects( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Projects");
		return projectsService.count(query);
	}

    @ApiOperation(value = "Returns aggregated result with given aggregation info")
	@RequestMapping(value = "/aggregations", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Page<Map<String, Object>> getProjectsAggregatedValues(@RequestBody AggregationInfo aggregationInfo, Pageable pageable) {
        LOGGER.debug("Fetching aggregated results for {}", aggregationInfo);
        return projectsService.getAggregatedValues(aggregationInfo, pageable);
    }

    @RequestMapping(value="/{id:.+}/feeses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the feeses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Fees> findAssociatedFeeses(@PathVariable("id") String id, Pageable pageable) {

        LOGGER.debug("Fetching all associated feeses");
        return projectsService.findAssociatedFeeses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/formMessageses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the formMessageses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormMessages> findAssociatedFormMessageses(@PathVariable("id") String id, Pageable pageable) {

        LOGGER.debug("Fetching all associated formMessageses");
        return projectsService.findAssociatedFormMessageses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/masterInspectionses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the masterInspectionses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MasterInspections> findAssociatedMasterInspectionses(@PathVariable("id") String id, Pageable pageable) {

        LOGGER.debug("Fetching all associated masterInspectionses");
        return projectsService.findAssociatedMasterInspectionses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/masterCaseses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the masterCaseses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<MasterCases> findAssociatedMasterCaseses(@PathVariable("id") String id, Pageable pageable) {

        LOGGER.debug("Fetching all associated masterCaseses");
        return projectsService.findAssociatedMasterCaseses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/projectFormses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the projectFormses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<ProjectForms> findAssociatedProjectFormses(@PathVariable("id") String id, Pageable pageable) {

        LOGGER.debug("Fetching all associated projectFormses");
        return projectsService.findAssociatedProjectFormses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/projectGisrecordses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the projectGisrecordses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<ProjectGisrecords> findAssociatedProjectGisrecordses(@PathVariable("id") String id, Pageable pageable) {

        LOGGER.debug("Fetching all associated projectGisrecordses");
        return projectsService.findAssociatedProjectGisrecordses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/projectSharedWiths", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the projectSharedWiths instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<ProjectSharedWith> findAssociatedProjectSharedWiths(@PathVariable("id") String id, Pageable pageable) {

        LOGGER.debug("Fetching all associated projectSharedWiths");
        return projectsService.findAssociatedProjectSharedWiths(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/projectTaskses", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the projectTaskses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<ProjectTasks> findAssociatedProjectTaskses(@PathVariable("id") String id, Pageable pageable) {

        LOGGER.debug("Fetching all associated projectTaskses");
        return projectsService.findAssociatedProjectTaskses(id, pageable);
    }

    @RequestMapping(value="/{id:.+}/vendorsToProjects", method=RequestMethod.GET)
    @ApiOperation(value = "Gets the vendorsToProjects instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<VendorsToProject> findAssociatedVendorsToProjects(@PathVariable("id") String id, Pageable pageable) {

        LOGGER.debug("Fetching all associated vendorsToProjects");
        return projectsService.findAssociatedVendorsToProjects(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service ProjectsService instance
	 */
	protected void setProjectsService(ProjectsService service) {
		this.projectsService = service;
	}

}

