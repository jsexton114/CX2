Application.$controller("MunicipalityCodeListPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        if ($scope.pageParams.CodeSetId === undefined) {
            $scope.Variables.goToPage_MunicipalityCodeSets.navigate();
        }
    };

}]);


Application.$controller("gridCodeListController", ["$scope", "$timeout",
    function($scope, $timeout) {
        "use strict";
        $scope.ctrlScope = $scope;
        // This parsing was done to remove '$$hashKey' from object which was broken on WM Release 8.5
        $scope.updaterowAction = function($event, $rowData) {
            $timeout(function() {
                $scope.Widgets.codeSets.datavalue = JSON.parse(angular.toJson($scope.Variables.lvCurrentCodeSetObj.firstRecord));
            })
        };

    }
]);

Application.$controller("liveformCodeListController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);