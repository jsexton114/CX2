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
            var inspectionLink;
            if (window.location.hostname == "www.wavemakeronline.com") {
                inspectionLink = "https://" + window.location.hostname + window.location.pathname + "#/ViewInspection?inspectionGuid=" + aM.inspectionGuid;
            } else {
                inspectionLink = "https://" + window.location.hostname + "#/ViewInspection?inspectionGuid=" + aM.inspectionGuid;
            }
            return '<label>' + 'Location: ' + aM.fullAddress + '</label> </br>' +
                '<label>' + 'Inspection Title: ' + aM.inspectionTitle + '</label></br>' +
                '<label>' + 'Requested By: ' + aM.requestedByFullName + '</label></br>' +
                '<label>' + 'Date Requested: ' + moment(aM.requestedFor).format('MM-DD-YYYY') + '</label></br>' +
                '<label>' + 'Assigned To: ' + aM.assignedToPersonName + '</label></br>' +
                '<a target="_blank" href=' + inspectionLink + '>View Inspection</a>';
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




    $scope.svTeamInspectionListonSuccess = function(variable, data) {
        _.forEach(data.content, function(obj) {
            if (obj.assignedTo == $scope.Variables.loggedInUser.dataSet.id) {
                obj.markerIcon = "http://maps.google.com/mapfiles/ms/micons/red-dot.png";
            } else {
                obj.markerIcon = "http://maps.google.com/mapfiles/ms/micons/blue-dot.png";
            }
        });
        $scope.Variables.svTotalInspections.dataSet = _.clone(data.content);

    };


    $scope.selectInspectionFilterChange = function($event, $isolateScope, newVal, oldVal) {
        $scope.Variables.svTotalInspections.dataSet = _.clone($scope.Variables.svTeamInspectionList.dataSet.content);
        if (newVal == "My") { //
            let others = _.remove($scope.Variables.svTotalInspections.dataSet, function(obj) {
                if (obj.assignedTo != $scope.Variables.loggedInUser.dataSet.id) {
                    return true;
                }
            });
        } else if (newVal == "Team") { //
            let others = _.remove($scope.Variables.svTotalInspections.dataSet, function(obj) {
                if (obj.assignedTo == $scope.Variables.loggedInUser.dataSet.id) {
                    return true;
                }
            });
        } else { //nothing
        }
    };


    $scope.Cx2InspectionDraftDataonError = function(variable, data) {
        //nothing	
    };

}]);

Application.$controller("gridInspectionsController", ["$scope", "$location",
    function($scope, $location) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.viewInspectionAction = function($event, $rowData) {
            $scope.Variables.stvInspectionGUID.dataSet.dataValue = $rowData.inspectionGuid
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

        $scope.viewInspectionAction = function($event, $rowData) {

            $scope.Variables.stvInspectionGUID.dataSet.dataValue = $rowData.inspectionGuid
            $scope.Widgets.pagedialogViewInspection.open();

        };
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