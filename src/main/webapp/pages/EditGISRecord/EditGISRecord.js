Application.$controller("EditGISRecordPageController", ["$scope", function($scope) {
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


    $scope.liveformGisrecordsBeforeservicecall = function($event, $operation, $data) {
        delete $data.dateModified;
        delete $data.fullAddress;

        $data.modifiedBy = parseInt(Variables.loggedInUser.dataSet.id);
    };

}]);


Application.$controller("liveformGisrecordsController", ["$scope", "$rootScope",
    function($scope, $rs) {
        "use strict";

        $scope.disable = ($rs.Variables.loggedInUser.dataSet.roles[0] === "MunicipalityEmployee");
    }
]);


Application.$controller("gridGISContactsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveformGISContactsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridOpenFormsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("grid3Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridCasesController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRowAction = function($event, $rowData) {
            $scope.Variables.stvFormLink.dataSet.dataValue = $rowData.formGuid;
            $scope.Widgets.pagedialogViewForm.open();
        };

    }
]);

Application.$controller("gridFormsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRowAction = function($event, $rowData) {
            $scope.Variables.stvFormLink.dataSet.dataValue = $rowData.formGuid;
            $scope.Widgets.pagedialogViewForm.open();
        };

    }
]);

Application.$controller("gridTransactionsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveform3Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogGisMapController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("pagedialogViewFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);