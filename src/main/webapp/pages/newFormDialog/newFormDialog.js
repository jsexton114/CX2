Application.$controller("newFormDialogPageController", ["$scope", "$location", function($scope, $location) {
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

    $scope.closeDlg = function() {
        $scope.$parent.Widgets[$scope.pageParams.dialogName].close();
    };

    $scope.button1Click = function($event, $isolateScope) {
        $scope.closeDlg();
    };

    $scope.$watch(function() {
        return $scope.pageParams.municipalityId;
    }, function(newValue, oldValue) {
        if (newValue === undefined) {
            $scope.Widgets.selectMunicipality.datavalue = undefined;
        } else {
            $scope.Widgets.selectMunicipality.datavalue = newValue;
        }
    });

    $scope.buttonCreateClick = function($event, $isolateScope) {
        $location.path("/NewForm").search("formTypeId", $scope.Widgets.selectForm.datavalue);
    };

}]);