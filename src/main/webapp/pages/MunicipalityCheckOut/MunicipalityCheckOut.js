Application.$controller("MunicipalityCheckOutPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        /*
         * variables can be accessed through '$scope.Variables' property here
         * e.g. to get dataSet in a staticVariable named 'loggedInUser' use following script
         * $scope.Variables.loggedInUser.getData()
         *
         * widgets can be accessed through '$scope.Widgets' property here
         * e.g. to get value of text widget named 'username' use following script
         * '$scope.Widgets.username.datavalue'
         */
    };


    $scope.lvCheckOutonSuccess = function(variable, data) {
        debugger
        var fees = [];

        var temp = $scope.Variables.svCartItemIds.dataSet.content;
        for (let i = 0; i < temp.length; i++) {
            fees.push(temp[i].feeId);
        }
        $scope.Variables.svUpdateMultipleFeeStatus.setInput({
            'feeList': fees
        });
        $scope.Variables.svUpdateMultipleFeeStatus.update();
        $scope.Variables.svRemoveFeesFromAllUserCarts.setInput({
            'feeList': fees
        });
        $scope.Variables.svRemoveFeesFromAllUserCarts.update();
    };

}]);