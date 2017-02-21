Application.$controller("MyProjectsPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        $scope.today = moment().valueOf();
    };

}]);




Application.$controller("gridProjectsListController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.updaterowAction = function($event, $rowData) {
            $scope.Variables.goToPage_ViewProject.setData({
                'ProjectGUID': $rowData.projectGuid
            });
            $scope.Variables.goToPage_ViewProject.navigate();
        };





        $scope.deleterowAction = function($event, $rowData) {
            var allowDelete = false;
            if ($scope.Variables.loggedInUser.dataSet.id == $rowData.usersByCreatedBy.id) {
                allowDelete = true;
            }
            var temp = $scope.Variables.loggedInUser.dataSet.roles;
            //Checking if user is muniadmin or cxadmin
            for (let i = 0; i < temp.length; i++) {
                if ((temp[i] == "MunicipalityAdmin") || (temp[i] == "CXAdmin")) {
                    allowDelete = true;
                }
            }
            if (allowDelete) {
                $scope.Widgets.confirmdialogSoftDeleteProjectCo.open();
            } else {
                $scope.Variables.NoDeleteAccess.notify();
            }
        };

    }
]);

Application.$controller("dialogCreateProjectController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.today = moment().valueOf();

        $scope.buttonCreateProjectClick = function($event, $isolateScope) {
            $scope.Variables.ProjectsData.setInput({
                'modifiedDate': $scope.today,
                'createdDate': $scope.today
            });
            $scope.Variables.ProjectsData.insertRecord();
        };

    }
]);

Application.$controller("confirmdialogSoftDeleteProjectCoController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);