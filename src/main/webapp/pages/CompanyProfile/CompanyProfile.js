Application.$controller("CompanyProfilePageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        //current date
        $scope.Variables.Today.dataSet.dataValue = Date.parse(new Date().toDateString());
    };





    $scope.CurrentVendorObjonBeforeDatasetReady = function(variable, data) {
        _.forEach(data, function(row) {
            row.coi = row.coi === null ? null : '';

        });
    };


}]);


Application.$controller("liveform1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("grid1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveform2Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);