Application.$controller("UserOpenFormsPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        $scope.$watch(function() {
            return $scope.Widgets.panelSelectMunicipality.Widgets.selectMunicipality.datavalue;
        }, function(newVal, oldVal) {

            //Checking if no municipality is selected
            if (newVal == undefined) {
                $scope.Variables.FormsForUsersAndShared.setInput({
                    'municipalityId': undefined
                });
                $scope.Variables.FormsForUsersAndShared.update();

            } else {
                // For selected municipality
                $scope.Variables.FormsForUsersAndShared.setInput({
                    'municipalityId': $scope.Widgets.panelSelectMunicipality.Widgets.selectMunicipality.datavalue.ID
                });
                $scope.Variables.FormsForUsersAndShared.update();

            }
        });
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

        $scope.customRowAction = function($event, $rowData) {
            $scope.Widgets.pagedialogViewForm.open();
        };

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

Application.$controller("pagedialog1Controller", ["$scope",
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