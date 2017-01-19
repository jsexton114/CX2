Application.$controller("EditFormPageController", ["$scope", "wmToaster", function($scope, wmToaster) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {

    };




    $scope.liveform2Error = function($event, $operation, $data) {
        wmToaster.show('error', 'ERROR', 'Sort Order cannot be duplicated.', 5000);
    };

}]);


Application.$controller("liveformUpdateFormTypeController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("grid1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveform2Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("grid2Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveform3Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("grid3Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dlgFormTypeFieldController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);