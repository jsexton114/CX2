Application.$controller("TomorrowInspectionsPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        $scope.toDay = moment().startOf('day').valueOf();
        $scope.tomorrow = moment().endOf('day').add(1, 'days').valueOf();
    };

}]);

Application.$controller("gridTomorrowInspectionsController", ["$scope",
	function($scope) {
		"use strict";
		$scope.ctrlScope = $scope;
	}
]);