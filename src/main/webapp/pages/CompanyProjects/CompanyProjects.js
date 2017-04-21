Application.$controller("CompanyProjectsPageController", ["$scope", function($scope) {
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


    $scope.ProjectsDataonSuccess = function(variable, data) {
        debugger
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