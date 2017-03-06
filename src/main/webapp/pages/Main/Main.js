Application.$controller("MainPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        //current date
        //$scope.Variables.Today.dataSet.dataValue = Date.parse(new Date().toDateString());

        $scope.$watch(function() {
            return $scope.Widgets.panelSelectMunicipality.Widgets.selectMunicipality.datavalue;
        }, function(newVal, oldVal) {
            //Checking if no municipality is selected
            if (newVal == undefined) {
                $scope.Variables.CountOfClosedFormsForUser.update();
                $scope.Variables.CountOfOpenFormsForUser.update();
                $scope.Variables.CountOfAllProjectsForUsersAndSharedWith.update();

            } else {
                // For selected municipality
                $scope.Variables.CountOfClosedFormsForMunicipality.update();
                $scope.Variables.CountOfOpenFormsForMunicipality.update();
                $scope.Variables.CountOfProjectsForUsersAndSharedWithByMunicipality.update();
            }
        });
    };

    $scope.GetAllUsersForAdminonSuccess = function(variable, data) {
        // For Admin Behalf of User
        data[0].id
        $scope.Variables.CurrentUserDetails.setInput({
            "id": data[0].id,
        });
        $scope.Variables.CurrentUserDetails.update();
    };

    $scope.CountOfClosedFormsForUseronSuccess = function(variable, data) {
        $scope.closedCount = data.content[0].count;
    };

    $scope.CountOfOpenFormsForUseronSuccess = function(variable, data) {
        $scope.openCount = data.content[0].count;
        // To display on LeftNav
        $scope.Variables.UserOpenFormsCount.dataSet.dataValue = data.content[0].count;
    };

    $scope.CountOfClosedFormsForMunicipalityonSuccess = function(variable, data) {
        $scope.closedCount = data.content[0].count;
    };

    $scope.CountOfOpenFormsForMunicipalityonSuccess = function(variable, data) {
        $scope.openCount = data.content[0].count;
    };

    $scope.CountOfAllProjectsForUsersAndSharedWithonSuccess = function(variable, data) {
        $scope.projectsCount = data.content[0].count;
    };

    $scope.CountOfProjectsForUsersAndSharedWithByMunicipalityonSuccess = function(variable, data) {
        $scope.projectsCount = data.content[0].count;
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

Application.$controller("pdlgNewFormController", ["$scope",
	function($scope) {
		"use strict";
		$scope.ctrlScope = $scope;
	}
]);