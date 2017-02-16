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