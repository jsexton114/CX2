Application.$controller("CompanyUsersPageController", ["$scope", "$filter", "$location", function($scope, $filter, $location) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        // $scope.Variables.BreadCrum.dataSet[0].link += ('?companyId=' + $scope.pageParams.companyId);
    };


    $scope.CheckIfCompanyUserIsVendorAdminonSuccess = function(variable, data) {

        if (data.content[0].count == 1) {
            $scope.Widgets.confirmdialogDeleteAdminConfrim.open();
        } else {
            $scope.Variables.DeleteFromVendorUsers.update();
        }
    };

    $scope.getJoinDate = function() {
        return $filter('toDate')(new Date().getTime(), 'MM-dd-yyyy');
    };




    $scope.lvGiveCompanyEmployeeRoleonError = function(variable, data) {
        //Nothing		
    };


    $scope.breadcrumb1Beforenavigate = function($isolateScope, $item) {
        $location.path($item.link);
        return false;
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

        $scope.customRow1Action = function($event, $rowData) {
            $scope.Variables.CheckIfCompanyUserIsVendorAdmin.setInput({
                'user': $rowData.userId
            });
            $scope.Variables.CheckIfCompanyUserIsVendorAdmin.update();
        };

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
                $scope.Variables.lvGiveCompanyEmployeeRole.setInput({
                    'userId': $scope.Widgets.searchForAdmin.datavalue.id
                });
                $scope.Variables.lvGiveCompanyEmployeeRole.insertRecord();


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

Application.$controller("confirmdialogDeleteAdminConfrimController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.confirmdialogDeleteAdminConfrimOk = function($event, $isolateScope) {
            $scope.Variables.DeleteFromVendorAdmins.setInput({
                'user': $scope.Widgets.gridVendorUsers.selecteditem.userId
            });
            $scope.Variables.DeleteFromVendorAdmins.update();
            $scope.Variables.DeleteUserWithAdmin.setInput({
                'user': $scope.Widgets.gridVendorUsers.selecteditem.userId
            });
            $scope.Variables.DeleteUserWithAdmin.update();
        };

    }
]);