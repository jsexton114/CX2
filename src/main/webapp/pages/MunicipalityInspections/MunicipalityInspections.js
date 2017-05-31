Application.$controller("MunicipalityInspectionsPageController", ["$scope", "$location", function($scope, $location) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        $scope.yesterDayStart = moment().startOf('day').subtract(1, 'months').valueOf();
        $scope.toDayStart = moment().startOf('day').valueOf();
        $scope.toDayEnd = moment().endOf('day').valueOf();
        $scope.tomorrowEnd = moment().endOf('day').add(1, 'months').valueOf();
    };

    $scope.goToInspectionList = function($event, maxDay, municipalityId) {
        municipalityId = municipalityId || $scope.Widgets.selectMunicipality.datavalue.ID;

        $location.path("/InspectionList").search({
            municipalityId: municipalityId,
            maxDay: maxDay
        });
    };

}]);

Application.$controller("dialogNewCaseController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);