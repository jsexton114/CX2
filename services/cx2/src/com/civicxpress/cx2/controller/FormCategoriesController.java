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
import com.civicxpress.cx2.FormCategories;
import com.civicxpress.cx2.FormTypes;
import com.civicxpress.cx2.service.FormCategoriesService;

/**
 * Controller object for domain model class FormCategories.
 * @see FormCategories
 */
@RestController("cx2.FormCategoriesController")
@Api(value = "FormCategoriesController", description = "Exposes APIs to work with FormCategories resource.")
@RequestMapping("/cx2/FormCategories")
public class FormCategoriesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormCategoriesController.class);

    @Autowired
    @Qualifier("cx2.FormCategoriesService")
    private FormCategoriesService formCategoriesService;

    @ApiOperation(value = "Creates a new FormCategories instance.")
    @RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public FormCategories createFormCategories(@RequestBody FormCategories formCategories) {
        LOGGER.debug("Create FormCategories with information: {}", formCategories);
        formCategories = formCategoriesService.create(formCategories);
        LOGGER.debug("Created FormCategories with information: {}", formCategories);
        return formCategories;
    }

    @ApiOperation(value = "Returns the FormCategories instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public FormCategories getFormCategories(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Getting FormCategories with id: {}", id);
        FormCategories foundFormCategories = formCategoriesService.getById(id);
        LOGGER.debug("FormCategories details with id: {}", foundFormCategories);
        return foundFormCategories;
    }

    @ApiOperation(value = "Updates the FormCategories instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public FormCategories editFormCategories(@PathVariable("id") Integer id, @RequestBody FormCategories formCategories) throws EntityNotFoundException {
        LOGGER.debug("Editing FormCategories with id: {}", formCategories.getId());
        formCategories.setId(id);
        formCategories = formCategoriesService.update(formCategories);
        LOGGER.debug("FormCategories details with id: {}", formCategories);
        return formCategories;
    }

    @ApiOperation(value = "Deletes the FormCategories instance associated with the given id.")
    @RequestMapping(value = "/{id:.+}", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteFormCategories(@PathVariable("id") Integer id) throws EntityNotFoundException {
        LOGGER.debug("Deleting FormCategories with id: {}", id);
        FormCategories deletedFormCategories = formCategoriesService.delete(id);
        return deletedFormCategories != null;
    }

    /**
     * @deprecated Use {@link #findFormCategories(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of FormCategories instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormCategories> findFormCategories(Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering FormCategories list");
        return formCategoriesService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the list of FormCategories instances matching the search criteria.")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormCategories> findFormCategories(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering FormCategories list");
        return formCategoriesService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data.")
    @RequestMapping(value = "/export/{exportType}", method = RequestMethod.GET, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportFormCategories(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        return formCategoriesService.export(exportType, query, pageable);
    }

    @ApiOperation(value = "Returns the total count of FormCategories instances.")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Long countFormCategories(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
        LOGGER.debug("counting FormCategories");
        return formCategoriesService.count(query);
    }

    @RequestMapping(value = "/{id}/formTypeses", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the formTypeses instance associated with the given id.")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<FormTypes> findAssociatedFormTypeses(@PathVariable("id") Integer id, Pageable pageable) {
        LOGGER.debug("Fetching all associated formTypeses");
        return formCategoriesService.findAssociatedFormTypeses(id, pageable);
    }

    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service FormCategoriesService instance
	 */
    protected void setFormCategoriesService(FormCategoriesService service) {
        this.formCategoriesService = service;
    }
}
