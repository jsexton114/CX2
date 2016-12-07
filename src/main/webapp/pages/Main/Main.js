Application.$controller("MainPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        //current date
        $scope.Variables.Today.dataSet.dataValue = Date.parse(new Date().toDateString());

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