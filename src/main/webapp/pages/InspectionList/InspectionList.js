Application.$controller("InspectionListPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {};

    $scope.getDateRanges = function() {
        return {
            previous: {
                start: moment().endOf('day').subtract(1, 'months').valueOf(),
                end: moment().endOf('day').subtract(1, 'days').valueOf()
            },
            today: {
                start: moment().startOf('day').valueOf(),
                end: moment().endOf('day').valueOf()
            },
            upcoming: {
                start: moment().endOf('day').valueOf(),
                end: moment().startOf('day').add(1, 'months').valueOf()
            }
        };
    };

    $scope.dateRanges = $scope.getDateRanges();

    $scope.googlemaps1Load = function($isolateScope) {
        $isolateScope.setInfoWindow = function() {
            var aM = $isolateScope.activeMarker;
            return '<label>' + 'Inspection- ' + aM.inspectionTitle + '</label></br>' +
                '<label>' + 'By -' + aM.requestedByFullName + '</label></br>' +
                '<label>' + 'On- ' + aM.requestedFor + '</label></br>' +
                '<label>' + 'At- ' + aM.fullAddress + '</label> </br>' +
                '<label>' + 'Assigned To- ' + aM.assignedToPersonName + '</label>'


        };
    };

    $scope.buttonRefreshInspectionListClick = function($event, $isolateScope) {
        $scope.dateRanges = $scope.getDateRanges();

        if ($scope.Widgets.tabs1.activeTabIndex === 1) {
            $scope.Variables.svTeamInspectionList.update();
        } else {
            $scope.Variables.svInspectionList.update();
        }
    };



}]);

Application.$controller("gridInspectionsController", ["$scope", "$location",
    function($scope, $location) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.viewInspectionAction = function($event, $rowData) {
            $scope.Widgets.pagedialogViewInspection.open();

        };
    }
]);

Application.$controller("pagedialog1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("pagedialogViewInspectionController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridTeamInspectionsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridDraftController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveformDraftController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);