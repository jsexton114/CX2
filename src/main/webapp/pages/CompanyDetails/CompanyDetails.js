Application.$controller("CompanyDetailsPageController", ["$scope", function($scope) {
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

    $scope.CurrentVendorObjonBeforeDatasetReady = function(variable, data) {
        _.forEach(data, function(row) {
            row.coi = row.coi === null ? null : '';

        });
    };

    $scope.liveform1Beforeservicecall = function($event, $operation, $data) {
        $data.lastUpdated = new Date().getTime();
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
        $scope.today = moment().valueOf();
        //$scope.minExpiration;

        $scope.issuedDateChange = function($event, $isolateScope, newVal, oldVal) {
            // $scope.Variables.minExpiration.dataSet.dataValue;
            // var selectedDate = moment(newVal);
            // var currentDate = moment();
            // if (selectedDate > currentDate) {
            //     $scope.Variables.minExpiration.dataSet.dataValue = selectedDate;

            // } else if (selectedDate < currentDate) {
            //     $scope.Variables.minExpiration.dataSet.dataValue = currentDate;

            // } else {
            //     $scope.Variables.minExpiration.dataSet.dataValue = currentDate;

            // }
        };

    }
]);