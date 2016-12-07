Application.$controller("CompanyProfilePageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        //current date
        $scope.Variables.Today.dataSet.dataValue = Date.parse(new Date().toDateString());

    };




}]);


Application.$controller("liveform1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);