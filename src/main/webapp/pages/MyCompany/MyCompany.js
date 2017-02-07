Application.$controller("MyCompanyPageController", ["$scope", function($scope) {
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


    $scope.CountOfVendorsonSuccess = function(variable, data) {
        $scope.Variables.MyCompaniesCount.dataSet.dataValue = data.content[0].CountOfVendors;
    };


    $scope.CountOfCompnayFormsByVendorIdonSuccess = function(variable, data) {
        $scope.Variables.CompanyOpenFormsCount.dataSet.dataValue = data.content[0].count;
    };

}]);