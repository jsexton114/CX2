/**This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
package com.civicxpress.userservice.controller;

import com.civicxpress.userservice.UserService;
import org.springframework.web.multipart.MultipartFile;
import java.lang.Long;
import java.lang.Exception;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/saveAdditionalImage", method = RequestMethod.POST, consumes = "multipart/form-data")
    public void saveAdditionalImage(@RequestPart(value = "photo") MultipartFile[] photo, @RequestParam(value = "municipalityId", required = false) Long municipalityId, @RequestParam(value = "userId", required = false) Long userId) throws Exception {
        userService.saveAdditionalImage(photo, municipalityId, userId);
    }

    @RequestMapping(value = "/saveMunicipalityLogo", method = RequestMethod.POST, consumes = "multipart/form-data")
    public void saveMunicipalityLogo(@RequestPart(value = "photo") MultipartFile[] photo, @RequestParam(value = "municipalityId", required = false) Long municipalityId) throws Exception {
        userService.saveMunicipalityLogo(photo, municipalityId);
    }

    @RequestMapping(value = "/saveUserPhoto", method = RequestMethod.POST, consumes = "multipart/form-data")
    public void saveUserPhoto(@RequestPart(value = "photo") MultipartFile[] photo, @RequestParam(value = "userId", required = false) Long userId) throws Exception {
        userService.saveUserPhoto(photo, userId);
    }
}
