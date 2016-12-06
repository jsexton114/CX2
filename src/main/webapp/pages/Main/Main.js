Application.$controller("MainPageController", ["$scope", function($scope) {
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


    $scope.StandardUserMunicipalitesonSuccess = function(variable, data) {
        // For count badge in left nav
        $scope.Variables.NoOfMunicipalitiesForUser.dataSet.dataValue = data.totalElements
    };




    $scope.GetAllUsersForAdminonSuccess = function(variable, data) {
        // For Admin Behalf of User
        data[0].id
        $scope.Variables.CurrentUserDetails.setInput({
            "id": data[0].id,
        });
        $scope.Variables.CurrentUserDetails.update();
    };


}]);

Application.$controller("dialog1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        // For navigation to selected form page
        $scope.buttonCreateClick = function($event, $isolateScope) {
            var pageName = $scope.Widgets.selectForm.datavalue.PageName;
            $scope.Variables[pageName].navigate();
        };

    }
]);