Application.$controller("DashBoardsPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        //setting default to year
        $scope.Widgets.datetimeEnd.timestamp = moment().valueOf();
        $scope.Widgets.datetimeStart.timestamp = moment.utc(new Date(2016, 6, 1)).valueOf();
    };


    $scope.selectTermsChange = function($event, $isolateScope, newVal, oldVal) {
        switch (newVal) {
            case "All Time":
                $scope.Widgets.datetimeEnd.timestamp = moment().valueOf();
                $scope.Widgets.datetimeStart.timestamp = moment.utc(new Date(2016, 6, 1)).valueOf();
                break;
            case "This Year":
                $scope.Widgets.datetimeEnd.timestamp = moment().valueOf();
                $scope.Widgets.datetimeStart.timestamp = moment().subtract(1, 'years').valueOf();
                break;
            case "This Half":
                $scope.Widgets.datetimeEnd.timestamp = moment().valueOf();
                $scope.Widgets.datetimeStart.timestamp = moment().subtract(6, 'months').valueOf();
                break;
            case "This Quarter":
                $scope.Widgets.datetimeEnd.timestamp = moment().valueOf();
                $scope.Widgets.datetimeStart.timestamp = moment().subtract(4, 'months').valueOf();
                break;
            case "This Month":
                $scope.Widgets.datetimeEnd.timestamp = moment().valueOf();
                $scope.Widgets.datetimeStart.timestamp = moment().subtract(1, 'months').valueOf();
                break;
            case "This Week":
                $scope.Widgets.datetimeEnd.timestamp = moment().valueOf();
                $scope.Widgets.datetimeStart.timestamp = moment().subtract(1, 'weeks').valueOf();
                break;
            case "Today":
                $scope.Widgets.datetimeEnd.timestamp = moment().valueOf();
                $scope.Widgets.datetimeStart.timestamp = moment().startOf('day').valueOf();
                break;

        }
    };

}]);