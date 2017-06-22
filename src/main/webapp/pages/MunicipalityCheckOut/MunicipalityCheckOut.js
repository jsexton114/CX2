// NOTE: MunicipalityCheckOut page is obsolete and replaced by MyCart
Application.$controller("MunicipalityCheckOutPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {

    };

    $scope.buttonSubmitClick = function($event, $isolateScope) {
        var feeIds = [];

        $scope.Variables.svCartItemIds.dataSet.content.forEach(function(cartItem, index) {
            feeIds.push(cartItem.feeId);
        });

        $scope.Variables.svCheckout.setInput("Long", feeIds);
        $scope.Variables.svCheckout.update();
    };

    $scope.svCheckoutonSuccess = function(variable, data) {
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