Application.$controller("FormsPageController", ["$scope", "$timeout", function($scope, $timeout) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        $scope.today = moment().valueOf();

    };


    $scope.FormStatusonSuccess = function(variable, data) {
        // For showing current Status of form
        var currentStatus = _.findIndex(data, {
            'id': _.parseInt($scope.pageParams.FormStatusId)
        });
        $scope.defaultObjectForSelectStatus = data[currentStatus];
        $timeout(function() {
            var a = $('.livelist-status li.app-list-item:nth-child(' + (currentStatus + 1) + ')').addClass('active');
        });
    };

}]);



Application.$controller("gridSharedwithController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveformSharedwithController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("grid3Controller", ["$scope",
	function($scope) {
		"use strict";
		$scope.ctrlScope = $scope;
	}
]);