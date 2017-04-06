Application.$controller("UserOpenFormsPageController", ["$scope", function($scope) {
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

    $scope.formGridOptions = {
        enableSorting: true,
        paginationPageSizes: [20, 50, 100],
        enablePaginationControls: true,
        columnDefs: [{
            name: 'formTitle',
            displayName: 'Form Title'
        }, {
            name: 'createdBy',
            displayName: 'Created By'
        }, {
            name: 'formDesign',
            displayName: 'Form Design'
        }, {
            name: 'formStatus',
            displayName: 'Form Status'
        }, {
            name: 'address',
            displayName: 'Address'
        }, {
            name: 'balanceDue',
            displayName: 'Balance Due'
        }]
    };

}]);




Application.$controller("dialogCompaniesListController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridCompaniesListController", ["$scope",
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

Application.$controller("dialogNewFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("grid2Controller", ["$scope", "$location",
    function($scope, $location) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.updaterowAction = function($event, $rowData) {
            $location.path("/NewForm").search({
                draftId: $rowData.id
            });
        };

    }
]);