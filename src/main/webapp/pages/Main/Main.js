Application.$controller("MainPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {

        function calculateCount(municipality) {

            $scope.Variables.CountOfClosedFormsForUser.setInput({
                'municipalityId': municipality
            });
            $scope.Variables.CountOfClosedFormsForUser.update();
            $scope.Variables.CountOfOpenFormsForUser.setInput({
                'municipalityId': municipality
            });

            $scope.Variables.CountOfOpenFormsForUser.update();
            $scope.Variables.CountOfAllProjectsForUsersAndSharedWith.setInput({
                'municipalityId': municipality
            });
            $scope.Variables.CountOfAllProjectsForUsersAndSharedWith.update();
            $scope.Variables.svFeeCountForUser.setInput({
                'municipalityId': municipality
            });
            $scope.Variables.svFeeCountForUser.update();
        }

        $scope.$watch(function() {
            return $scope.Widgets.panelSelectMunicipality.Widgets.selectMunicipality.datavalue;
        }, function(newVal, oldVal) {

            //Checking if no municipality is selected
            if (newVal == undefined) {
                calculateCount(undefined);
            } else {
                // For selected municipality
                calculateCount($scope.Widgets.panelSelectMunicipality.Widgets.selectMunicipality.datavalue.ID);
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




    $scope.serviceVariable1onSuccess = function(variable, data) {
        window.open(data);
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