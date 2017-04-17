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

        var fees = [];

        var temp = $scope.Variables.svCartItemIds.dataSet.content;
        for (let i = 0; i < temp.length; i++) {
            fees.push(temp[i].feeId);
        }

        //Updating the Status of fees to Paid after Payment
        $scope.Variables.svUpdateMultipleFeeStatus.setInput({
            'feeList': fees
        });
        $scope.Variables.svUpdateMultipleFeeStatus.update();
        // Removing the fee from All users Carts
        $scope.Variables.svRemoveFeesFromAllUserCarts.setInput({
            'feeList': fees
        });
        $scope.Variables.svRemoveFeesFromAllUserCarts.update();
        // Mapping paid Fees with Transaction
        let feeData = $scope.Variables.svCartItemIds.dataSet.content;
        let targetList = [];
        _.forEach(feeData, function(obj) {
            targetList.push({
                'feeId': obj.feeId
            });
        });

        let itemSet = {
            'content': targetList
        };
        let mappedList = JSON.stringify(itemSet);

        $scope.Variables.svMapFeeWithTransaction.setInput({
            'feeListString': mappedList,
            'transactionId': data.transactionId
        });
        $scope.Variables.svMapFeeWithTransaction.update();

        $scope.Widgets.alertdialogPaymentSuccess.open();
    };

}]);

Application.$controller("confirmdialogPaymentSuccessController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("alertdialogPaymentSuccessController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);