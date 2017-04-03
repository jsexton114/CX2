Application.$controller("YesterdayInspectionsPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        $scope.start = moment().startOf('day').subtract(1, 'days').valueOf();
        $scope.end = moment().endOf('day').valueOf();
    };

}]);