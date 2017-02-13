Application.$controller("ViewProjectPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    var crum;
    $scope.onPageReady = function() {
        $scope.today = moment().valueOf();
        //var temp = $scope.Variables.BreadCrumb.dataSet;
    };

}]);

Application.$controller("gridProjectMembersController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogAddMemberController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.today = moment().valueOf();
    }
]);