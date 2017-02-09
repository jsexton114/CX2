Application.$controller("FindFormPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        $scope.today = moment().valueOf();
    };

}]);


Application.$controller("gridFliteredFormsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRowAction = function($event, $rowData) {
            $scope.Variables.goToPage_FormsFormSearch.setData({
                'FormGUID': $rowData.formGuid
            });
            $scope.Variables.goToPage_FormsFormSearch.navigate();
        };

    }
]);

Application.$controller("gridAdvancedController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRowAction = function($event, $rowData) {
            $scope.Variables.goToPage_FormsFormSearch.setData({
                'FormGUID': $rowData.formGuid
            });
            $scope.Variables.goToPage_FormsFormSearch.navigate();
        };

    }
]);

Application.$controller("gridVENDORController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRowAction = function($event, $rowData) {
            $scope.Variables.goToPage_FormsFormSearch.setData({
                'FormGUID': $rowData.formGuid
            });
            $scope.Variables.goToPage_FormsFormSearch.navigate();
        };

    }
]);

Application.$controller("gridAddressController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);