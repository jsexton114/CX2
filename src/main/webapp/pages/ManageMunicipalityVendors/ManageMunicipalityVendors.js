Application.$controller("ManageMunicipalityVendorsPageController", ["$scope", function($scope) {
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




}]);

Application.$controller("gridPendingController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRowAction = function($event, $rowData) {
            $scope.Variables.VendorProfile.setFilter('id', $rowData.vendorId)
            $scope.Variables.VendorProfile.update();
            $scope.Variables.VendorLicensesData.setFilter('id', $rowData.vendorId)
            $scope.Variables.VendorLicensesData.update();
            $scope.Widgets.viewCompanyDetails.open();



        };




    }
]);

Application.$controller("liveform2Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridApprovedVendorsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRowAction = function($event, $rowData) {
            $scope.Variables.VendorProfile.setFilter('id', $rowData.vendorId)
            $scope.Variables.VendorProfile.update();
            $scope.Variables.VendorLicensesData.setFilter('id', $rowData.vendorId)
            $scope.Variables.VendorLicensesData.update();
            $scope.Widgets.viewCompanyDetails.open();



        };

    }
]);

Application.$controller("liveformApprovedVendorsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridSuspendedController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRowAction = function($event, $rowData) {
            $scope.Variables.VendorProfile.setFilter('id', $rowData.vendorId)
            $scope.Variables.VendorProfile.update();
            $scope.Variables.VendorLicensesData.setFilter('id', $rowData.vendorId)
            $scope.Variables.VendorLicensesData.update();
            $scope.Widgets.viewCompanyDetails.open();



        };

    }
]);

Application.$controller("liveformSuspendedController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
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

Application.$controller("grid4Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("grid6Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);