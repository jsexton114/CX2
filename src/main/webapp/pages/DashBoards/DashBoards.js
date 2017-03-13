Application.$controller("DashBoardsPageController", ["$scope", function($scope) {
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


    $scope.selectTermsChange = function($event, $isolateScope, newVal, oldVal) {
        switch (newVal) {

            case "Year":
                $scope.Widgets.datetimeEnd.timestamp = moment().valueOf();
                $scope.Widgets.datetimeStart.timestamp = moment().subtract(1, 'years').valueOf();
                break;
            case "Half":
                $scope.Widgets.datetimeEnd.timestamp = moment().valueOf();
                $scope.Widgets.datetimeStart.timestamp = moment().subtract(6, 'months').valueOf();
                break;
            case "Quarter":
                $scope.Widgets.datetimeEnd.timestamp = moment().valueOf();
                $scope.Widgets.datetimeStart.timestamp = moment().subtract(4, 'months').valueOf();
                break;
            case "Month":
                $scope.Widgets.datetimeEnd.timestamp = moment().valueOf();
                $scope.Widgets.datetimeStart.timestamp = moment().subtract(1, 'months').valueOf();
                break;
            case "Week":
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