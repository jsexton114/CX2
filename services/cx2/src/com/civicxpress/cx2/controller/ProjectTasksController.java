/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cx2.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
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
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.civicxpress.cx2.ProjectTasks;
import com.civicxpress.cx2.service.ProjectTasksService;

/**
 * Controller object for domain model class ProjectTasks.
 * @see ProjectTasks
 */
@RestController("cx2.ProjectTasksController")
@Api(value = "ProjectTasksController", description = "Exposes APIs to work with ProjectTasks resource.")
@RequestMapping("/cx2/ProjectTasks")
public class ProjectTasksController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectTasksController.class);

    @Autowired
    @Qualifier("cx2.ProjectTasksService")
    private ProjectTasksService projectTasksService;

    @ApiOperation(value = "Creates a new ProjectTasks instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public ProjectTasks createProjectTasks(@RequestBody ProjectTasks projectTasks) {
        LOGGER.debug("Create ProjectTasks with information: {}", projectTasks);
        projectTasks = projectTasksService.create(projectTasks);
        LOGGER.debug("Created ProjectTasks with information: {}", projectTasks);
        return projectTasks;
    }

    @ApiOperation(value = "Returns the ProjectTasks instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public ProjectTasks getProjectTasks(@PathVariable("id") String id) throws EntityNotFoundException {
        LOGGER.debug("Getting ProjectTasks with id: {}", id);
        ProjectTasks foundProjectTasks = projectTasksService.getById(id);
        LOGGER.debug("ProjectTasks details with id: {}", foundProjectTasks);
        return foundProjectTasks;
    }

    @ApiOperation(value = "Updates the ProjectTasks instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public ProjectTasks editProjectTasks(@PathVariable("id") String id, @RequestBody ProjectTasks projectTasks) throws EntityNotFoundException {
        LOGGER.debug("Editing ProjectTasks with id: {}", projectTasks.getPmid());
        projectTasks.setPmid(id);
        projectTasks = projectTasksService.update(projectTasks);
        LOGGER.debug("ProjectTasks details with id: {}", projectTasks);
        return projectTasks;
    }

    @ApiOperation(value = "Deletes the ProjectTasks instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteProjectTasks(@PathVariable("id") String id) throws EntityNotFoundException {
        LOGGER.debug("Deleting ProjectTasks with id: {}", id);
        ProjectTasks deletedProjectTasks = projectTasksService.delete(id);
        return deletedProjectTasks != null;
    }

    /**
     * @deprecated Use {@link #findProjectTasks(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of ProjectTasks instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<ProjectTasks> searchProjectTasksByQueryFilters(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering ProjectTasks list");
        return projectTasksService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of ProjectTasks instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<ProjectTasks> findProjectTasks(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering ProjectTasks list");
        return projectTasksService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportProjectTasks(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return projectTasksService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of ProjectTasks instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countProjectTasks(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting ProjectTasks");
        return projectTasksService.count(query);
    }

    @RequestMapping(value = "/{id:.+}/projectTasksesForPredecessor", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the projectTasksesForPredecessor instance associated with the given id.")
    public Page<ProjectTasks> findAssociatedProjectTasksesForPredecessor(@PathVariable("id") String id, Pageable pageable) {
        LOGGER.debug("Fetching all associated projectTasksesForPredecessor");
        return projectTasksService.findAssociatedProjectTasksesForPredecessor(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service ProjectTasksService instance
	 */
    protected void setProjectTasksService(ProjectTasksService service) {
        this.projectTasksService = service;
    }
}
