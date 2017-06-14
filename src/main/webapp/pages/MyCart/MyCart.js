Application.$controller("MyCartPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        //console.log("document.referrer: " + document.referrer);
        //console.log("window.location: " + window.location);
        //console.log("document.cookie: " + document.cookie);
    };

    $scope.wizardstep1Load = function($isolateScope, stepIndex) {
        // if the stripeToken is avaiable, then $scope.Widgets.wizardCheckOut.next(); from wizardstep1Load
        var stripeToken = null;
        //console.log("wizardstep1Load()");
        stripeToken = getCookieValue("stripeToken");
        //console.log("stripeToken from cookie: " + stripeToken);
        if (!stripeToken) {
            stripeToken = getQueryStringByParameter("stripeToken");
            //console.log("stripeToken from query string: " + stripeToken);
        }
        if (stripeToken) {
            $scope.Widgets.wizardCheckOut.next();
            $scope.Widgets.wizardCheckOut.done();
        }
    };

    $scope.svFeesInCartByUseronSuccess = function(variable, data) {
        $scope.$broadcast('feeListUpdate', data.content);

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
        if ($scope.Widgets.wizardCheckOut) $scope.Widgets.wizardCheckOut.show = false
        if ($scope.Widgets.containerPaymentRecieved) $scope.Widgets.containerPaymentRecieved.show = true
            // $scope.$parent.Widgets.pagedialog1.close();

    };


    $scope.wizardstep2Load = function($isolateScope, stepIndex) {
        var stripeToken = null;
        //console.log("!isMunicipalityEmployee(): " + !isMunicipalityEmployee());
        if (!isMunicipalityEmployee()) {
            // not a municipality employee, so hide options other than credit card
            if ($scope.Widgets.radiosetPaymentOptions) {
                $scope.Widgets.radiosetPaymentOptions.selectedvalue = "bind:Variables.stvPaymentOptions.dataSet[2].paymentType";
                $scope.Widgets.radiosetPaymentOptions.show = false;
                // when using credit card, look for the stripe token
                stripeToken = getCookieValue("stripeToken");
                if (stripeToken) {
                    // hide the stripe button becuase we already have the token
                    console.log("stripeToken: " + stripeToken);
                    if ($scope.Widgets.html1) $scope.Widgets.html1.show = false;
                    console.log("$scope.Widgets.wizardstep2.disabledone: " + $scope.Widgets.wizardstep2.disabledone);
                    $scope.Widgets.wizardstep2.disabledone = false;
                } else {
                    // Stripe HTML is written programmatically so WM doesn't remove it
                    var html1 = document.getElementById('html1');
                    console.log("$scope.Variables.svSumOfFeesInUsersCart.dataSet.sumOfFeesInCart: " + $scope.Variables.svSumOfFeesInUsersCart.dataSet.sumOfFeesInCart);
                    var form = createStripeButtonHtml("pk_test_XPwevpSch24R4UhQqbH3bGPB", $scope.Variables.svSumOfFeesInUsersCart.dataSet.sumOfFeesInCart, "TekDog Inc.", "Municipality fees");
                    html1.appendChild(form);
                    // disable done in the wizard so the user has to click the stripe button to enter credit card information
                    $scope.Widgets.wizardstep2.disabledone = true;
                }
            }
            if ($scope.Widgets.paymentTypeLabel) $scope.Widgets.paymentTypeLabel.show = false;
            if ($scope.Widgets.compositeComments) {
                $scope.Widgets.compositeComments.show = false;
                $scope.Widgets.compositeComments.required = false;
            }
        }
        //console.log("$scope.Widgets.radiosetPaymentOptions.datavalue: " + $scope.Widgets.radiosetPaymentOptions.datavalue);
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
        action.value = "https://www.wavemakeronline.com/run-700gxljbr3/CivicXpress/services/payment/chargeCreditCard";
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

    function getQueryStringByParameter(name) {
        name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
            results = regex.exec(location.search);
        return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    }

    function getCookieValue(a) {
        var b = document.cookie.match('(^|;)\\s*' + a + '\\s*=\\s*([^;]+)');
        return b ? b.pop() : '';
    }

    function isMunicipalityEmployee() {
        var returnIsMunicipalityEmployee = false;
        let temp = $scope.Variables.loggedInUser.dataSet.roles;
        for (let i = 0; i < temp.length; i++) {
            if ((temp[i] == "MunicipalityEmployee")) {
                returnIsMunicipalityEmployee = true;
            }
        }
        return returnIsMunicipalityEmployee;
    }

}]);

Application.$controller("pagedialogNewFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);



Application.$controller("iframedialog1Controller", ["$scope",
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