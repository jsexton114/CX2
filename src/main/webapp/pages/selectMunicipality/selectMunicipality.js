Application.$controller("selectMunicipalityPageController", ["$scope", function($scope) {
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

        $scope.muniCount = 0;
    };

    $scope.getSelectedMunicipality = function() {
        var municipalityId = parseInt($scope.pageParams.municipalityId);

        var variable = $scope.pageParams.muniType === 'a' ? $scope.Variables.AdminsMunicipalities : ($scope.pageParams.muniType === 'e' ? $scope.Variables.EmployeesMunicipalities : $scope.Variables.StandardUserMunicipalities);
        var data = variable.dataSet;

        if (!variable.dataSet.content) {
            return null;
        }

        $scope.muniCount = variable.dataSet.content.length;

        return (!!$scope.pageParams.municipalityId ? _.find(data.content, {
            ID: municipalityId
        }) : ($scope.muniCount === 1 || $scope.pageParams.muniType !== 's' ? variable.firstRecord : undefined));
    };

}]);