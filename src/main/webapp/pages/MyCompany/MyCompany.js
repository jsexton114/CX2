Application.$controller("MyCompanyPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {

    };


    $scope.CountOfVendorsonSuccess = function(variable, data) {
        $scope.Variables.MyCompaniesCount.dataSet.dataValue = data.content[0].CountOfVendors;
    };


    $scope.CountOfCompnayFormsByVendorIdonSuccess = function(variable, data) {
        $scope.Variables.CompanyOpenFormsCount.dataSet.dataValue = data.content[0].count;
    };

}]);