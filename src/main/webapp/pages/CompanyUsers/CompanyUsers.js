Application.$controller("CompanyUsersPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        //current date
        $scope.toDay = Date.parse(new Date().toDateString());
    };

}]);



Application.$controller("dialogAddUserController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogUserProfileController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridVendorUsersController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridAdminsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogAddAdminController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.buttonSaveAdminClick = function($event, $isolateScope) {
            if ($scope.Variables.CheckingUserWithInVendorUsers.dataSet.content[0].exist > 0) {
                //already a user of company then add as admin
                $scope.Variables.MapAsAdminForVendor.update();
            } else {
                // Adding as both user and admin
                $scope.Variables.MapAsAdminForVendor.update();
                $scope.Variables.AddUsersToCompany.setInput({
                    'UserId': $scope.Widgets.searchForAdmin.datavalue.id
                });
                $scope.Variables.AddUsersToCompany.update();


            }
        };

    }
]);

Application.$controller("dialogAdminProfileController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);