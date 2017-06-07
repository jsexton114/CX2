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
            $scope.Variables.stvSelectedVendor.dataSet.dataValue = parseInt($rowData.vendorId);
            $scope.Widgets.viewCompanyDetails.open();

        };

    }
]);



Application.$controller("gridSuspendedController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRowAction = function($event, $rowData) {
            $scope.Variables.stvSelectedVendor.dataSet.dataValue = parseInt($rowData.vendorId);
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



Application.$controller("gridPendingController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.customRowAction = function($event, $rowData) {
            $scope.Variables.stvSelectedVendor.dataSet.dataValue = parseInt($rowData.vendorId);
            $scope.Widgets.viewCompanyDetails.open();

        };
    }
]);



Application.$controller("dialogPendingApprovalsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.today = moment().valueOf();
    }
]);

Application.$controller("dialogReviewApprovedController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.today = moment().valueOf();
    }
]);

Application.$controller("dialogReviewSuspendedController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.today = moment().valueOf();
    }
]);