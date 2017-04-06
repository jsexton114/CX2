Application.$controller("NewCasesPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        $scope.weekDayStart = moment().startOf('day').subtract(7, 'days').valueOf();
    };

}]);


Application.$controller("gridNewCasesController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);