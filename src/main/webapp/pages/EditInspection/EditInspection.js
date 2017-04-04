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