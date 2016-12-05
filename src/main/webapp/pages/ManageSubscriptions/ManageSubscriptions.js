Application.$controller("ManageSubscriptionsPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        $scope.Variables.BreadCrum.dataSet = [{
            "label": "Home",
            "icon": "glyphicon glyphicon-home",
            "link": "#/CXAdmin"
        }, {
            "label": "ManageSubscriptions",
            "icon": "wi wi-ellipsis-v",
            "link": "#/ManageSubscriptions"
        }]
    };

}]);






Application.$controller("grid2Controller", ["$scope",
	function($scope) {
		"use strict";
		$scope.ctrlScope = $scope;
	}
]);

Application.$controller("liveform2Controller", ["$scope",
	function($scope) {
		"use strict";
		$scope.ctrlScope = $scope;
	}
]);