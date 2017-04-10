Application.$controller("EditInspectionPageController", ["$scope", function($scope) {
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


    $scope.CurrentInspectionObjonSuccess = function(variable, data) {
        $scope.Variables.stvCodeSetScope.dataSet[1].dataValue = data[0].municipalities.municipalityName;
    };

}]);






Application.$controller("gridFieldsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridFormTypeStatusController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("lfFormTypeStatusController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridInspectioncategorymappingController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveformInspectioncategorymappinController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridOutcomeController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.assessFeeChange = function(newVal) {

            $scope.Variables.svUpdateAssessFeeYN.setInput({
                'AssessFeeYN': newVal
            });
            $scope.Variables.svUpdateAssessFeeYN.update();

        };
        $scope.considerClosedChange = function(newVal) {

            $scope.Variables.svUpdateConsiderClosedForInspectionOutcome.setInput({
                'ConsiderClosed': newVal
            });
            $scope.Variables.svUpdateConsiderClosedForInspectionOutcome.update();


        };

    }
]);

Application.$controller("liveformOutcomeController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.ConsiderClosedDialogChange = function($event, $isolateScope, newVal, oldVal) {
            if (newVal === false) {
                $scope.Widgets.liveformOutcome.formWidgets.autoReInspection.datavalue = false;
                $scope.Widgets.liveformOutcome.formWidgets.daysToAutoReInspection.datavalue = null;

            }
        };

    }
]);

Application.$controller("dialogOutcomeFeeController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridOutcomeFeeController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveformOutcomeFeeController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveformUpdateInspectiondesignController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.allowCancelChange = function($event, $isolateScope, newVal, oldVal) {
            if (newVal === false) {
                $scope.Widgets.liveformUpdateInspectiondesign.formWidgets.allowSameDayCancel.datavalue = false;

            }
        };

        $scope.scheduleDateOnlyChange = function($event, $isolateScope, newVal, oldVal) {
            if (newVal === true) {
                $scope.Widgets.liveformUpdateInspectiondesign.formWidgets.scheduleDateAndTime.datavalue = false;
                $scope.Widgets.liveformUpdateInspectiondesign.formWidgets.totalInspectionsHourly.datavalue = "";

            }
            if (newVal === false) {
                $scope.Widgets.liveformUpdateInspectiondesign.formWidgets.scheduleDateAndTime.datavalue = true;
            }
        };

        $scope.scheduleDateAndTimeChange = function($event, $isolateScope, newVal, oldVal) {
            if (newVal === true) {
                $scope.Widgets.liveformUpdateInspectiondesign.formWidgets.scheduleDateOnly.datavalue = false;

            }
            if (newVal === false) {
                $scope.Widgets.liveformUpdateInspectiondesign.formWidgets.scheduleDateOnly.datavalue = true;

            }
        };

    }
]);

Application.$controller("gridLettersController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveformLettersController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);





Application.$controller("gridInspectionCodeSetsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveformInspectionCodeSetsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;


        $scope.formCodeSetScopeChange = function($event, $isolateScope, newVal, oldVal) {

            if (newVal == "Global") {
                $scope.Variables.lvListOfCodeSets.setFilter({
                    'globalYn': false
                });
                $scope.Variables.lvListOfCodeSets.update();
            } else {
                $scope.Variables.lvListOfCodeSets.setFilter({
                    'globalYn': false,
                    'municipalityId': $scope.Variables.CurrentInspectionObj.firstRecord.municipalityId
                });
                $scope.Variables.lvListOfCodeSets.update();
            }
        };


        $scope.saveAction = function($event) {
            $scope.Variables.lvListOfCodeSets.setFilter({
                'globalYn': false
            });
            $scope.Variables.lvListOfCodeSets.update();
        };

    }
]);