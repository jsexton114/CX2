Application.$controller("ManageMunicipalityVendorsPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {

        $scope.today = moment().valueOf();
    };



}]);





Application.$controller("gridApprovedVendorsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRowAction = function($event, $rowData) {
            $scope.Variables.VendorProfile.setFilter('id', $rowData.vendorId)
            $scope.Variables.VendorProfile.update();
            $scope.Variables.VendorLicensesData.setFilter('vendorId', $rowData.vendorId)
            $scope.Variables.VendorLicensesData.update();
            $scope.Widgets.viewCompanyDetails.open();

        };

    }
]);



Application.$controller("gridSuspendedController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRowAction = function($event, $rowData) {
            $scope.Variables.VendorProfile.setFilter('id', $rowData.vendorId)
            $scope.Variables.VendorProfile.update();
            $scope.Variables.VendorLicensesData.setFilter('vendorId', $rowData.vendorId)
            $scope.Variables.VendorLicensesData.update();
            $scope.Widgets.viewCompanyDetails.open();



        };

    }
]);



Application.$controller("viewCompanyDetailsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("grid5Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);





Application.$controller("liveform4Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridPendingController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.customRowAction = function($event, $rowData) {
            $scope.Variables.VendorProfile.setFilter('id', $rowData.vendorId)
            $scope.Variables.VendorProfile.update();
            $scope.Variables.VendorLicensesData.setFilter('vendorId', $rowData.vendorId)
            $scope.Variables.VendorLicensesData.update();
            $scope.Widgets.viewCompanyDetails.open();

        };
    }
]);



Application.$controller("dialogPendingApprovalsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogReviewApprovedController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogReviewSuspendedController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);