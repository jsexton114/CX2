Application.$controller("EmployeeLocationsPageController", ["$scope", function($scope) {
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


    $scope.googlemaps1Load = function($isolateScope) {

        $isolateScope.setInfoWindow = function() {

            var aM = $isolateScope.activeMarker;
            var img = "services/cx2/Users/" + aM.userId + "/content/photo"
            return '<img srcset=' + img + ' height="42" width="42" align="middle">' + '</img></br>' +
                '<label>' + 'Employee- ' + aM.users.fullName + '</label></br>' +
                '<label>' + 'Last Contact Time -' + aM.devicelastContactTime + '</label></br>'

        };
    };

}]);