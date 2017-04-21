Application.$controller("CompanyProjectsPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {

    };


    $scope.ProjectsDataonSuccess = function(variable, data) {

        $scope.Variables.lvMapProjectAndVendor.setInput({
            'projectGuid': data.projectGuid,
            'vendorId': $scope.Widgets.panelSelectCompany.Widgets.selectCompany.datavalue.ID,
            'primaryVendor': true
        });
        $scope.Variables.lvMapProjectAndVendor.createRecord();
    };

}]);



Application.$controller("gridProjectsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogCreateProjectController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;


    }
]);

Application.$controller("confirmdialogSoftDeleteProjectCoController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);