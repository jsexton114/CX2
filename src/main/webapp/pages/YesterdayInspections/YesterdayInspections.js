Application.$controller("YesterdayInspectionsPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        $scope.yesterDayStart = moment().startOf('day').subtract(1, 'days').valueOf();
        $scope.toDayStart = moment().startOf('day').valueOf();
        // $scope.toDayEnd = moment().endOf('day').valueOf();
        // $scope.tomorrowEnd = moment().endOf('day').add(1, 'days').valueOf();
    };

}]);