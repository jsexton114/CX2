Application.$controller("MyCartPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        console.log("document.referrer: " + document.referrer);
        console.log("window.location: " + window.location);
        console.log("document.cookie: " + document.cookie);
    };

    $scope.wizardstep1Load = function($isolateScope, stepIndex) {
        // if the stripeToken is avaiable, then $scope.Widgets.wizardCheckOut.next(); from wizardstep1Load
        var stripeToken = null;
        console.log("wizardstep1Load()");
        stripeToken = getCookieValue("stripeToken");
        console.log("stripeToken from cookie: " + stripeToken);
        if (!stripeToken) {
            stripeToken = getQueryStringByParameter("stripToken");
            console.log("stripeToken from query string: " + stripeToken);
        }
        if (stripeToken) $scope.Widgets.wizardCheckOut.next();
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

        $scope.Variables.svCartItemIds.dataSet.content.forEach(function(cartItem, index) {
            feeIds.push(cartItem.feeId);
        });

        $scope.Variables.svCheckout.setInput("Long", feeIds);
        $scope.Variables.svCheckout.update();
    };


    $scope.svCheckoutonSuccess = function(variable, data) {

        $scope.Variables.svCartItemIds.update();
        $scope.Widgets.wizardCheckOut.show = false
        $scope.Widgets.containerPaymentRecieved.show = true
            // $scope.$parent.Widgets.pagedialog1.close();

    };


    $scope.wizardstep2Load = function($isolateScope, stepIndex) {
        var stripeToken = null;
        if (!isMunicipalityEmployee()) {
            if ($scope.Widgets.radiosetPaymentOptions) {
                $scope.Widgets.radiosetPaymentOptions.selectedvalue = "bind:Variables.stvPaymentOptions.dataSet[2].paymentType";
                $scope.Widgets.radiosetPaymentOptions.show = false;
            }
            if ($scope.Widgets.paymentTypeLabel) $scope.Widgets.paymentTypeLabel.show = false;
            if ($scope.Widgets.compositeComments) $scope.Widgets.compositeComments.show = false;
        }
        stripeToken = getCookieValue("stripeToken");
        if ($scope.Widgets.radiosetPaymentOptions.selectedvalue == "Credit Card") {
            if (stripeToken) {
                if ($scope.Widgets.html1) $scope.Widgets.html1.show = false;
            } else {
                $scope.Widgets.wizardstep2.disabledone = true;
            }
        }
    };

    function getQueryStringByParameter(name) {
        name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
            results = regex.exec(location.search);
        return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
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