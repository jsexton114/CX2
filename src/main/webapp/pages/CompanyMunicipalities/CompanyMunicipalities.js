Application.$controller("CompanyMunicipalitiesPageController", ["$scope", function($scope) {
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






Application.$controller("grid2Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveform2Controller", ["$scope", "wmToaster",
    function($scope, wmToaster) {
        "use strict";
        //$scope.ctrlScope = $scope;
        $scope.saveAction = function($event) {
            $scope.InsuranceDate = $scope.Widgets.selectSelectCompany.dataset[0].InsuranceExpires;
            $scope.InsuranceDate = moment($scope.InsuranceDate);
            var curDate = moment()
            var differnceDays = $scope.InsuranceDate.diff(curDate, 'days')
            if (differnceDays < 30) {
                wmToaster.show('error', 'ERROR', 'insurance expiring next week', 5000);
                return;
            } else {
                $scope.Widgets.liveform2.save();
            }

        };

    }
]);