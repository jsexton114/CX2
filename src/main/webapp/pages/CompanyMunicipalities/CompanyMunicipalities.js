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






Application.$controller("grid2_1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveform2_12Controller", ["$scope", "wmToaster",
    function($scope, wmToaster) {
        "use strict";
        //$scope.ctrlScope = $scope;
        $scope.saveAction1 = function($event) {
            $scope.InsuranceDate = $scope.Widgets.selectSelectCompany.datavalue.InsuranceExpires;
            $scope.InsuranceDate = moment($scope.InsuranceDate);
            var curDate = moment()
            var differnceDays = $scope.InsuranceDate.diff(curDate, 'days')
            if (differnceDays <= 0) {
                wmToaster.show('error', 'ERROR', 'You may not add municipalities at this time because your business insurance has expired.  Please provide a new Certificate of Insurance and update your Insurance Expiration Date then reapply to the municipality.', 5000);
                $scope.Variables.goToPage_CompanyProfile.navigate();
                return;
            } else if (differnceDays <= 30) {

                wmToaster.show('error', 'ERROR', 'You may not add municipalities at this time because your business insurance is expiring in the next 30 days.  Please provide a new Certificate of Insurance and update your Insurance Expiration Date then reapply to the municipality.', 5000);
                $scope.Variables.goToPage_CompanyProfile.navigate();
                return;
            } else {
                $scope.Widgets.liveform2.save();
                wmToaster.show('success', 'SUCCESS', 'Your application has been submitted to the municipality. Check back later for updated application status.', 5000);
            }

        };



    }
]);