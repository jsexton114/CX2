Application.$controller("FormsPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        $scope.today = Date.parse(new Date().toDateString());
    };

}]);