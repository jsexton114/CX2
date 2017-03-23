Application.$controller("MyFeesPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        $scope.$watch(function() {
            return $scope.Widgets.panelSelectMunicipality.Widgets.selectMunicipality.datavalue;
        }, function(newVal, oldVal) {
            //Checking if no municipality is selected
            if (newVal == undefined) {
                $scope.Variables.svAllFeesOfForms.update();
            } else {
                // For selected municipality
                $scope.Variables.svAllFeesOfFormsByMunicipality.update();

            }
        });
    };


    $scope.svAllFeesOfFormsByMunicipalityonSuccess = function(variable, data) {
        debugger
        $scope.Widgets.gridUnpaidFees.dataset = data.content;
    };


    $scope.svAllFeesOfFormsonSuccess = function(variable, data) {
        debugger
        $scope.Widgets.gridUnpaidFees.dataset = data.content;
    };


    $scope.lvInsertAllFeesTocartonError = function(variable, data) {
        // Do nothing		
    };


    $scope.lvInsertAllFeesTocartonSuccess = function(variable, data) {

        //Checking if no municipality is selected
        if ($scope.Widgets.panelSelectMunicipality.Widgets.selectMunicipality.datavalue == undefined) {
            $scope.Variables.svAllFeesOfForms.update();
        } else {
            // For selected municipality
            $scope.Variables.svAllFeesOfFormsByMunicipality.update();
        }
    };

}]);

Application.$controller("gridUnpaidFeesController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.itemInCart = function(feeId) {
            let cartItems = $scope.Variables.svCartItemIds.dataSet.content;

            if (!cartItems) {
                return true; // By default, disable add to cart for anything if we don't have the cart data yet.
            }

            return !!_.find(cartItems, {
                feeId: feeId
            });
        };


        $scope.addNewRowAction = function($event) {
            let feeData = $scope.Widgets.gridUnpaidFees.dataset;
            _.forEach(feeData, function(obj) {

                $scope.Variables.lvInsertAllFeesTocart.setInput({
                    'feeId': obj.feeId,
                    'userId': $scope.Variables.CurrentUserDetails.dataSet.id
                });
                $scope.Variables.lvInsertAllFeesTocart.insertRecord();
                console.log(obj.feeId);
            });
        };

    }
]);