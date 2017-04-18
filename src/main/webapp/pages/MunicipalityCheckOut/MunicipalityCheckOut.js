Application.$controller("MunicipalityCheckOutPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {

    };


    $scope.lvCheckOutonSuccess = function(variable, data) {
        debugger
        var fees = [];

        var temp = $scope.Variables.svCartItemIds.dataSet.content;
        for (let i = 0; i < temp.length; i++) {
            fees.push(temp[i].feeId);
        }

        //Updating the Status of fees to Paid after Payment
        debugger;
        $scope.Variables.svUpdateMultipleFeeStatus.setInput({
            'feeList': fees
        });
        $scope.Variables.svUpdateMultipleFeeStatus.update();
        //Updating the Comments of fees from transaction comments
        $scope.Variables.svUpdateMultipleFeeComments.setInput({
            'feeList': _.join(fees, ',')
        });
        $scope.Variables.svUpdateMultipleFeeComments.update();
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


    $scope.svUpdateMultipleFeeCommentsonError = function(variable, data) {
        debugger
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