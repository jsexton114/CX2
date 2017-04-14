Application.$controller("ScheduleInspectionsPageController", ["$scope", function($scope) {
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

    $scope.inspectorAssignmentQueue = [];

    $scope.buttonAssignInspectorClick = function($event, $isolateScope) {
        $scope.Widgets.gridUnassignedInspections.selecteditem.forEach(function(inspection, index) {
            $scope.inspectorAssignmentQueue.push({
                inspectorId: $scope.Widgets.gridInspectors.selecteditem.id,
                inspectionGuid: inspection.inspectionGuid,
                dateAssigned: inspection.requestedFor
            });
        });

        $scope.Widgets.gridUnassignedInspections.selecteditem = [];

        updateAssignments();
    };


    $scope.buttonReassignClick = function($event, $isolateScope) {
        $scope.Widgets.gridAssignedInspections.selecteditem.forEach(function(inspection, index) {
            $scope.inspectorAssignmentQueue.push({
                inspectorId: $scope.Widgets.gridInspectorsA.selecteditem.id,
                inspectionGuid: inspection.inspectionGuid,
                dateAssigned: inspection.requestedFor
            });
        });

        $scope.Widgets.gridAssignedInspections.selecteditem = [];

        updateAssignments();
    };

    function updateAssignments() {
        if ($scope.inspectorAssignmentQueue.length === 0) {
            return;
        }

        var assignment = $scope.inspectorAssignmentQueue.pop();

        $scope.Variables.svAssignInspector.setInput(assignment);
        $scope.Variables.svAssignInspector.update();
    }


    $scope.svAssignInspectoronSuccess = function(variable, data) {
        if (!$scope.inspectorAssignmentQueue.length) {
            $scope.Variables.svGetAssignedInspections.update();
            $scope.Variables.svGetInspectionsByOutcome.update();
        } else {
            updateAssignments();
        }
    };

}]);

Application.$controller("gridUnassignedInspectionsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridInspectorsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridAssignedInspectionsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridInspectorsAController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);