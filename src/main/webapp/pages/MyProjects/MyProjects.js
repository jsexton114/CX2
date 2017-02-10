Application.$controller("MyProjectsPageController", ["$scope", function($scope) {
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
                'createDate': $scope.today
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