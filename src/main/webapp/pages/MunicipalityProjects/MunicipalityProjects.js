Application.$controller("MunicipalityProjectsPageController", ["$scope", function($scope) {
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
            // var encodedDescription = btoa(unescape(encodeURIComponent($scope.Widgets.richtexteditorProjectDescription.datavalue)));
            $scope.Variables.ProjectsData.setInput({
                'modifiedDate': $scope.today,
                //'projectDescription': encodedDescription,
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