Application.$controller("UserClosedFormsPageController", ["$scope", function($scope) {
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

}]);


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