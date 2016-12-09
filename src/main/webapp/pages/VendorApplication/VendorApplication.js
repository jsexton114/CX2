Application.$controller("VendorApplicationPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        //current date
        $scope.Variables.Today.dataSet.dataValue = Date.parse(new Date().toDateString());
    };


    $scope.VendorDataonSuccess = function(variable, data) {
        $scope.Variables.MapAsAdminForVendor.setInput({
            "UserId": $scope.Variables.CurrentUserDetails.dataSet.id,
            "VendorId": data.id
        });
        $scope.Variables.MapAsAdminForVendor.update();
    };

}]);




Application.$controller("liveformVendorApplicationController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);