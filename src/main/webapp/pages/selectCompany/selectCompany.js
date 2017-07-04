Application.$controller("selectCompanyPageController", ["$scope", function($scope) {
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

    $scope.getSelectedCompany = function(variable, data) {
        debugger
        variable = variable || $scope.Variables.CompanyListOfEmployee;
        data = data || variable.dataSet;

        return (!!$scope.pageParams.companyId ? _.find(data.content, {
            id: parseInt($scope.pageParams.companyId)
        }) : variable.firstRecord);
    };

}]);