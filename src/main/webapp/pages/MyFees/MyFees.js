Application.$controller("MyFeesPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        $scope.$watch(function() {
            return $scope.Widgets.panelSelectMunicipality.Widgets.selectMunicipality.datavalue;
        }, function(newVal, oldVal) {

            //Checking if no municipality is selected
            if (newVal == undefined) {
                $scope.Variables.svfetchUnpaidFeesOfFormsForCreatedByAndSharedWith.setInput({
                    'MunicipalityId': undefined
                });
                $scope.Variables.svfetchUnpaidFeesOfFormsForCreatedByAndSharedWith.update();
            } else {
                // For selected municipality
                $scope.Variables.svfetchUnpaidFeesOfFormsForCreatedByAndSharedWith.setInput({
                    'MunicipalityId': newVal.ID
                });
                $scope.Variables.svfetchUnpaidFeesOfFormsForCreatedByAndSharedWith.update();

            }
        });

    };




    $scope.lvInsertAllFeesTocartonError = function(variable, data) {
        // Do nothing		
    };


    $scope.lvInsertAllFeesTocartonSuccess = function(variable, data) {

        //Checking if no municipality is selected
        if ($scope.Widgets.panelSelectMunicipality.Widgets.selectMunicipality.datavalue == undefined) {
            $scope.Variables.svfetchUnpaidFeesOfFormsForCreatedByAndSharedWith.update();
        } else {
            // For selected municipality
            $scope.Variables.svAllFeesOfFormsByMunicipality.update();
        }
    };


    $scope.svfetchUnpaidFeesOfFormsForCreatedByAndSharedWithonSuccess = function(variable, data) {
        // let cartItems = $scope.Variables.svCartIds.dataSet.content;
        // _.remove(data.content, function(obj) {
        //     if (_.find(cartItems, {
        //             'feeId': obj.feeId
        //         })) {
        //         return true;
        //     }
        // });
    };



    $scope.svCartIdsonSuccess = function(variable, data) {
        $scope.Variables.stvCartCount.dataSet.dataValue = data.content.length;
    };

}]);



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

Application.$controller("gridUnpaidFeesController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.itemInCart = function(feeId) {
            let cartItems = $scope.Variables.svCartIds.dataSet.content;

            if (!cartItems) {
                return true; // By default, disable add to cart for anything if we don't have the cart data yet.
            }

            return !!_.find(cartItems, {
                feeId: feeId
            });
        };



        $scope.addNewRowAction = function($event) {
            let feeData = $scope.Widgets.gridUnpaidFees.gridData;
            //let feeData = $scope.Variables.svfetchUnpaidFeesOfFormsForCreatedByAndSharedWith.dataSet.content;
            let targetList = []
            _.forEach(feeData, function(obj) {
                targetList.push({
                    'feeId': obj.feeId
                });
            });

            let itemSet = {
                'content': targetList
            };
            let temp = JSON.stringify(itemSet);
            $scope.Variables.svInsertAllFeeToCart.setInput({
                'feeListString': temp,
                'userId': $scope.Variables.CurrentUserDetails.dataSet.id
            });
            $scope.Variables.svInsertAllFeeToCart.update();
        };

        $scope.updaterowAction = function($event, $rowData) {
            $scope.Widgets.pagedialogViewForm.open();
        };

    }
]);