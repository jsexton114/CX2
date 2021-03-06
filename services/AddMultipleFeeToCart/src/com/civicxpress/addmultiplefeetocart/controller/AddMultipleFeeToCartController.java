/**This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
package com.civicxpress.addmultiplefeetocart.controller;

import com.civicxpress.addmultiplefeetocart.AddMultipleFeeToCart;
import java.lang.String;
import java.lang.Integer;
import java.util.Map;
import com.civicxpress.cx2.MyCart;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;

@RestController
@RequestMapping(value = "/addMultipleFeeToCart")
public class AddMultipleFeeToCartController {

    @Autowired
    private AddMultipleFeeToCart addMultipleFeeToCart;

    @RequestMapping(value = "/feesToCart", method = RequestMethod.POST)
    public void addFeesToCart(@RequestParam(value = "feeListString", required = false) String feeListString, @RequestParam(value = "userId", required = false) Integer userId) {
        addMultipleFeeToCart.addFeesToCart(feeListString, userId);
    }

    @RequestMapping(value = "/table", method = RequestMethod.POST)
    public MyCart createTable(@RequestBody Map cartItems, @RequestParam(value = "userId", required = false) Integer userId) {
        return addMultipleFeeToCart.createTable(cartItems, userId);
    }
}
