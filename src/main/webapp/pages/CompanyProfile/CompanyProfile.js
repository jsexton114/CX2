Application.$controller("CompanyProfilePageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        // debugger;
        // if ($scope.pageParams.companyID != null) {
        //     $scope.Variables.CurrentVendorObj.setInput('id', $scope.pageParams.companyID);
        //     $scope.Variables.CurrentVendorObj.update();
        // } else {
        //     $scope.Variables.CurrentVendorObj.setInput('id', $scope.Widgets.selectSelectCompany.datavalue.ID);
        //     $scope.Variables.CurrentVendorObj.update();
        // }
        //current date
        $scope.Variables.Today.dataSet.dataValue = Date.parse(new Date().toDateString());
    };





    $scope.CurrentVendorObjonBeforeDatasetReady = function(variable, data) {
        _.forEach(data, function(row) {
            row.coi = row.coi === null ? null : '';

        });
    };


    $scope.liveform1Beforeservicecall = function($event, $operation, $data) {
        $data.lastUpdated = $scope.Variables.Today.dataSet.dataValue;
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