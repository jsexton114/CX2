Application.$controller("leftnavPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        let temp = $scope.Variables.loggedInUser.dataSet.roles;
        $scope.showDashBoard = true;
        //Checking if user is muniadmin or cxadmin or muniemp
        for (let i = 0; i < temp.length; i++) {
            if ((temp[i] == "MunicipalityEmployee") || (temp[i] == "Inspector") || (temp[i] == "MunicipalityAdmin") || (temp[i] == "InspectorManager") || (temp[i] == "CodeManager") || (temp[i] == "CodeOfficer")) {
                $scope.showDashBoard = false;
            }
        }
    };


    $scope.supportLinkClick = function($event, $isolateScope) {
        $scope.$parent.Widgets.pagedialogNewForm.open();
    };

}]);