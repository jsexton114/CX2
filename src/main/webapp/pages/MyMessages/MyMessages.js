Application.$controller("MyMessagesPageController", ["$scope", function($scope) {
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


Application.$controller("gridInboxController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.updaterowAction = function($event, $rowData) {
            $scope.gridData[+$($event.target).closest('tr').attr('data-row-id')].messageRead = true;

            //$rowData.messageRead = true;
            //$scope.Widgets.gridInbox.redraw();
            //$scope.Widgets.gridInbox.refreshData();
        };


        $scope.deleterowAction = function($event, $rowData) {

            switch ($rowData.sourceCategory) {
                case "Form":
                    $scope.Variables.stvSourceGuid.dataSet.dataValue = $rowData.sourceGuid;
                    $scope.Widgets.pagedialogViewForm.open();
                    break;
                case "Inspection":
                    $scope.Variables.stvSourceGuid.dataSet.dataValue = $rowData.sourceGuid;
                    $scope.Widgets.pagedialogViewInspection.open();
                    break;
                case "Project":
                    $scope.Variables.stvSourceGuid.dataSet.dataValue = $rowData.sourceGuid;
                    $scope.Widgets.pagedialogViewProject.open();
                    break;

            }



        };

    }
]);

Application.$controller("pagedialogViewFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("pagedialogViewInspectionController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("pagedialogViewProjectController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridSentController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRowAction = function($event, $rowData) {
            switch ($rowData.sourceCategory) {
                case "Form":
                    $scope.Variables.stvSourceGuid.dataSet.dataValue = $rowData.sourceGuid;
                    $scope.Widgets.pagedialogViewForm.open();
                    break;
                case "Inspection":
                    $scope.Variables.stvSourceGuid.dataSet.dataValue = $rowData.sourceGuid;
                    $scope.Widgets.pagedialogViewInspection.open();
                    break;
                case "Project":
                    $scope.Variables.stvSourceGuid.dataSet.dataValue = $rowData.sourceGuid;
                    $scope.Widgets.pagedialogViewProject.open();
                    break;

            }
        };

    }
]);