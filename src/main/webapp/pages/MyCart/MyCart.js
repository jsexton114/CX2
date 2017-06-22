Application.$controller("MyCartPageController", ["$scope", function($scope) {
    "use strict";

    $scope.stripeToken = null;
    var tdUtils = new TekDogUtilities();

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {

    };

    $scope.wizardstep1Load = function($isolateScope, stepIndex) {
        // if the stripeToken is avaiable, then $scope.Widgets.wizardCheckOut.next(); from wizardstep1Load
        $scope.stripeToken = tdUtils.getCookieValue("stripeToken");
        if (!!$scope.stripeToken) {
            console.log("!!$scope.stripeToken");
            $scope.Widgets.wizardCheckOut.next();
            console.log("$scope.Widgets.wizardCheckOut.next();");
            //$scope.Widgets.wizardCheckOut.done();
            $scope.Variables.svCheckout.setInput("paymentNumber", $scope.stripeToken);
            $scope.Variables.svCheckout.setInput("comments", "Stripe payment transaction ID " + $scope.stripeToken);
            console.log("Stripe payment transaction ID " + $scope.stripeToken);
        }
        console.log("$scope.stripeToken: " + $scope.stripeToken);
    };

    $scope.wizardstep1Next = function($isolateScope, currentStep, stepIndex) {
        $scope.Variables.svCartItemIds.update();
        $scope.Variables.svSumOfFeesInUsersCart.update();

        // let temp = $scope.Variables.loggedInUser.dataSet.roles;
        // for (let i = 0; i < temp.length; i++) {
        //     if ((temp[i] == "MunicipalityEmployee")) {
        //         $scope.Variables.goToPage_MunicipalityCheckOut.navigate();
        //     }
        // }
    };

    $scope.wizardstep2Load = function($isolateScope, stepIndex) {
        console.log("wizardstep2Load");
        console.log("$scope.Widgets.radiosetPaymentOptions.datavalue: " + $scope.Widgets.radiosetPaymentOptions.datavalue);
        console.log("!$scope.stripeToken: " + !$scope.stripeToken);
        console.log("$scope.isMunicipalityEmployee(): " + $scope.isMunicipalityEmployee());
        $scope.stripeToken = tdUtils.getCookieValue("stripeToken");
        if (!$scope.stripeToken || ($scope.isMunicipalityEmployee() && !$scope.stripeToken && $scope.Widgets.radiosetPaymentOptions.datavalue == 'Credit Card')) {
            $scope.Widgets.wizardstep2.disabledone = true;
        } else {
            $scope.Widgets.wizardstep2.disabledone = false;
        }
    };

    $scope.wizardstep2Prev = function($isolateScope, currentStep, stepIndex) {
        tdUtils.eraseCookie("stripeToken");
        $scope.stripeToken = null;
    };

    $scope.wizardCheckOutDone = function($isolateScope, steps) {
        var feeIds = [];

        if ($scope.Variables.svCartItemIds) {
            $scope.Variables.svCartItemIds.dataSet.content.forEach(function(cartItem, index) {
                feeIds.push(cartItem.feeId);
            });
        }

        $scope.Variables.svCheckout.setInput("Long", feeIds);
        $scope.Variables.svCheckout.update();
    };

    $scope.svCheckoutonSuccess = function(variable, data) {

        $scope.Variables.svCartItemIds.update();
        if ($scope.Widgets.wizardCheckOut) $scope.Widgets.wizardCheckOut.show = false;
        if ($scope.Widgets.containerPaymentRecieved) $scope.Widgets.containerPaymentRecieved.show = true;
        // $scope.$parent.Widgets.pagedialog1.close();

        tdUtils.eraseCookie("stripeToken");
        $scope.stripeToken = null;

    };

    $scope.svFeesInCartByUseronSuccess = function(variable, data) {
        $scope.$broadcast('feeListUpdate', data.content);

    };

    $scope.svSumOfFeesInUsersCartonSuccess = function(variable, data) {
        // Stripe HTML is written programmatically so WM doesn't remove it
        var html1 = document.getElementById('html1');
        console.log("document.getElementById('html1'): " + document.getElementById('html1'));
        if (html1) {
            console.log("html1.children.length: " + html1.children.length);
        }
        if (html1 && html1.children.length === 0) {
            console.log("$scope.Variables.svSumOfFeesInUsersCart.dataSet.sumOfFeesInCart: " + $scope.Variables.svSumOfFeesInUsersCart.dataSet.sumOfFeesInCart);
            var form = createStripeButtonHtml("pk_test_XPwevpSch24R4UhQqbH3bGPB", $scope.Variables.svSumOfFeesInUsersCart.dataSet.sumOfFeesInCart, "CivicXpress", "Municipality fees");
            html1.appendChild(form);
        }
    };

    function createStripeButtonHtml(dataKeyValue, dataAmountValue, dataNameValue, dataDescriptionValue) {
        // Stripe HTML is written programmatically so WM doesn't remove it
        var form = document.createElement("form");
        var action = document.createAttribute("action");
        var method = document.createAttribute("method");
        var script = document.createElement("script");
        var classAttribute = document.createAttribute("class");
        var src = document.createAttribute("src");
        var dataKey = document.createAttribute("data-key");
        var dataAmount = document.createAttribute("data-amount");
        var dataName = document.createAttribute("data-name");
        var dataDescription = document.createAttribute("data-description");
        var dataLocale = document.createAttribute("data-locale");
        var dataImage = document.createAttribute("data-image");
        var contextPath = window.location.origin + window.location.pathname;
        console.log("contextPath as formed by JavaScript: " + contextPath);
        action.value = contextPath + "/services/payment/callback";
        form.attributes.setNamedItem(action);
        method.value = "POST";
        form.attributes.setNamedItem(method);
        src.value = "https://checkout.stripe.com/checkout.js";
        script.attributes.setNamedItem(src);
        classAttribute.value = "stripe-button";
        script.attributes.setNamedItem(classAttribute);
        dataKey.value = dataKeyValue; //"pk_test_XPwevpSch24R4UhQqbH3bGPB";
        script.attributes.setNamedItem(dataKey);
        dataAmount.value = (dataAmountValue * 100); //"999";
        script.attributes.setNamedItem(dataAmount);
        dataName.value = dataNameValue; //"TekDog Inc.";
        script.attributes.setNamedItem(dataName);
        dataDescription.value = dataDescriptionValue; // "Municipality fees";
        script.attributes.setNamedItem(dataDescription);
        dataLocale.value = "auto";
        script.attributes.setNamedItem(dataLocale);
        dataImage.value = "https://s3.amazonaws.com/stripe-uploads/acct_1ASmehESexevkNnpmerchant-icon-1497016245641-CX200.png";
        script.attributes.setNamedItem(dataImage);
        form.appendChild(script);
        return form;
    }

    $scope.isMunicipalityEmployee = function() {
        var returnIsMunicipalityEmployee = false;
        let temp = $scope.Variables.loggedInUser.dataSet.roles;
        for (let i = 0; i < temp.length; i++) {
            if ((temp[i] == "MunicipalityEmployee")) {
                returnIsMunicipalityEmployee = true;
            }
        }
        return returnIsMunicipalityEmployee;
    };

    function TekDogUtilities() {}

    // cookie methods used from 
    // http://stackoverflow.com/questions/5639346/shortest-function-for-reading-a-cookie-in-javascript
    TekDogUtilities.prototype.createCookie = function(name, value, days) {
        // TODO: how to have a "use once" feature of the cookie?
        var expires;
        if (days) {
            var date = new Date();
            date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
            expires = "; expires=" + date.toGMTString();
        } else expires = "";
        document.cookie = name + "=" + value + expires + "; path=/";
    };

    TekDogUtilities.prototype.eraseCookie = function(name) {
        this.createCookie(name, "", -1);
    };

    TekDogUtilities.prototype.getCookieValue = function(a, b) {
        b = document.cookie.match('(^|;)\\s*' + a + '\\s*=\\s*([^;]+)');
        return b ? b.pop() : '';
    };

    TekDogUtilities.prototype.getQueryStringByParameter = function(name) {
        name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
            results = regex.exec(location.search);
        return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    }

}]);

Application.$controller("gridFeesListController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.feeTotal = 0;

        $scope.$on('feeListUpdate', function(event, fees) {
            var newFeeTotal = 0;

            fees.forEach(function(fee, index) {
                newFeeTotal += fee.amount;
            });
            if (newFeeTotal == 0) {
                $scope.Widgets.wizardstep1.disablenext = true;
            }
            $scope.feeTotal = newFeeTotal;
        });

        $scope.updaterowAction = function($event, $rowData) {
            if ($rowData.itemType == 'form') {
                $scope.Widgets.pagedialogViewForm.open();
            } else {
                $scope.Widgets.pagedialogViewInspection.open();
            }
        };
    }
]);

Application.$controller("iframedialog1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("pagedialogNewFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("pagedialogViewFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("pagedialogViewInspectionController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);