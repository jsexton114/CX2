/**This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
package com.civicxpress.pushnotification.controller;

import com.civicxpress.pushnotification.PushNotification;
import java.lang.String;
import java.lang.Integer;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;

@RestController
@RequestMapping(value = "/pushNotification")
public class PushNotificationController {

    @Autowired
    private PushNotification pushNotification;

    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public void notify(@RequestBody String[] message, @RequestParam(value = "deviceToken", required = false) String deviceToken, @RequestParam(value = "deviceOS", required = false) String deviceOS) {
        pushNotification.notify(message, deviceToken, deviceOS);
    }

    @RequestMapping(value = "/registerDevice", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public void registerDevice(@RequestParam(value = "deviceId", required = false) String deviceId, @RequestParam(value = "deviceOs", required = false) String deviceOs, @RequestParam(value = "userId", required = false) Integer userId, @RequestParam(value = "deviceUUID", required = false) String deviceUUID, @RequestParam(value = "deviceModel", required = false) String deviceModel) {
        pushNotification.registerDevice(deviceId, deviceOs, userId, deviceUUID, deviceModel);
    }

    @RequestMapping(value = "/unregisterDevice", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public void unregisterDevice(@RequestParam(value = "deviceId", required = false) String deviceId, @RequestParam(value = "userId", required = false) Integer userId, @RequestParam(value = "deviceOs", required = false) String deviceOs) {
        pushNotification.unregisterDevice(deviceId, userId, deviceOs);
    }
}