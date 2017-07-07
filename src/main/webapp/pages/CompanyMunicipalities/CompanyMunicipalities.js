Application.$controller("CompanyMunicipalitiesPageController", ["$scope", "$location", function($scope, $location) {
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

        //$scope.Variables.BreadCrum.dataSet[0].link += ('?companyId=' + $scope.pageParams.companyId);
    };

    $scope.breadcrumb1Beforenavigate = function($isolateScope, $item) {
        $location.path($item.link);
        return false;
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
            var isMunicipalityExist = _.find($scope.Variables.svMunicipalityIdsOfVendor.dataSet.content, function(obj) {
                return obj.municipalityIds == $scope.formFields[0].value.id;
            });
            if (isMunicipalityExist === undefined) {
                $scope.InsuranceDate = $scope.Variables.CurrentVendorObj.dataSet.data[0].insuranceExpires;
                $scope.InsuranceDate = moment($scope.InsuranceDate);
                var curDate = moment();
                var differnceDays = $scope.InsuranceDate.diff(curDate, 'days');
                if (differnceDays <= 0) {
                    wmToaster.show('error', 'ERROR', 'You may not add municipalities at this time because your business insurance has expired. Please update your Insurance Expiration Date before reapplying to the municipality.', 10000);
                    $scope.Variables.goToPage_CompanyProfile.navigate();
                    return;
                } else if (differnceDays <= 30) {
                    wmToaster.show('error', 'ERROR', 'You may not add municipalities at this time because your business insurance is expiring in the next 30 days. Please update your Insurance Expiration Date before reapplying to the municipality.', 10000);
                    $scope.Variables.goToPage_CompanyProfile.navigate();
                    return;
                } else if ($scope.formFields[0].value.separateContractorApplicationRequired) {
                    $scope.Variables.goToPage_NewForm.setData({
                        'formTypeId': $scope.formFields[0].value.contractorApplicationFormId,
                        'companyId': $scope.Variables.CurrentVendorObj.dataSet.data[0].id
                    });
                    $scope.Variables.goToPage_NewForm.navigate();

                } else {
                    $scope.Widgets.liveform2_12.save();
                }
            } else {
                wmToaster.show('error', 'ERROR', 'You have already applied to that municipality.', 5000);
            }

        };
    }
]);