Application.$controller("VendorApplicationPageController", ["$scope", "wmToaster", function($scope, wmToaster) {
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


    $scope.liveformVendorApplicationError = function($event, $operation, $data) {
        wmToaster.show('error', 'ERROR', 'A vendor with this FEIN number has already been entered in CivicXpress. If you have any questions, please email support@tekdoginc.com.', 5000);
    };

}]);




Application.$controller("liveformVendorApplicationController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);