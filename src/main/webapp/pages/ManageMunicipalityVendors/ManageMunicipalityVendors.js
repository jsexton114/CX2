Application.$controller("ManageMunicipalityVendorsPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {

        $scope.today = moment().valueOf();
    };

}]);





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

Application.$controller("gridPendingController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.deleterowAction = function($event, $rowData) {
            $scope.Variables.stvDeletingApplicationId.dataSet.dataValue = $rowData.id;
            $scope.Widgets.confirmdialogDeleteApplication.open();
        };



        $scope.customRowAction = function($event, $rowData) {

            $scope.Variables.stvSelectedVendor.dataSet.dataValue = $rowData.vendorId;
            $scope.Widgets.viewCompanyDetails.open();
        };

    }
]);

Application.$controller("gridApprovedVendorsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.deleterowAction = function($event, $rowData) {
            $scope.Variables.stvDeletingApplicationId.dataSet.dataValue = $rowData.id;
            $scope.Widgets.confirmdialogDeleteApplication.open();
        };

        $scope.customRowAction = function($event, $rowData) {
            $scope.Variables.stvSelectedVendor.dataSet.dataValue = $rowData.vendorId;
            $scope.Widgets.viewCompanyDetails.open();
        };
    }
]);

Application.$controller("gridSuspendedController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.deleterowAction = function($event, $rowData) {
            $scope.Variables.stvDeletingApplicationId.dataSet.dataValue = $rowData.id;
            $scope.Widgets.confirmdialogDeleteApplication.open();
        };

        $scope.customRowAction = function($event, $rowData) {
            $scope.Variables.stvSelectedVendor.dataSet.dataValue = $rowData.vendorId;
            $scope.Widgets.viewCompanyDetails.open();
        };
    }
]);

Application.$controller("confirmdialogDeleteApplicationController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);