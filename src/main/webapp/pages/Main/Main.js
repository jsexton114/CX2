Application.$controller("MainPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        //current date
        //$scope.Variables.Today.dataSet.dataValue = Date.parse(new Date().toDateString());

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





    $scope.selectStandardUserMunicipalityChange = function($event, $isolateScope, newVal, oldVal) {
        //Checking if no municipality is selected 
        if (newVal == undefined) {
            $scope.Variables.CountOfClosedFormsForUser.update();
            $scope.Variables.CountOfOpenFormsForUser.update();
        } else {
            // For selected municipality
            $scope.Variables.CountOfClosedFormsForMunicipality.update();
            $scope.Variables.CountOfOpenFormsForMunicipality.update();
        }
    };


    $scope.CountOfClosedFormsForUseronSuccess = function(variable, data) {
        $scope.closedCount = data.content[0].count;
    };


    $scope.CountOfOpenFormsForUseronSuccess = function(variable, data) {
        $scope.openCount = data.content[0].count;
        $scope.Variables.UserOpenFormsCount.dataSet.dataValue = data.content[0].count;
    };


    $scope.CountOfClosedFormsForMunicipalityonSuccess = function(variable, data) {
        $scope.closedCount = data.content[0].count;
    };


    $scope.CountOfOpenFormsForMunicipalityonSuccess = function(variable, data) {
        $scope.openCount = data.content[0].count;
    };


    $scope.svCreateFormonSuccess = function(variable, data) {
        var navToForm = $scope.Variables.navGoToForm;
        navToForm.dataSet.FormGUID = data;
        navToForm.navigate();
    };

}]);

Application.$controller("dialogNewFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

    }
]);

Application.$controller("pagedialogNewFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);