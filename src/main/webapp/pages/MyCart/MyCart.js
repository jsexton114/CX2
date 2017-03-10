Application.$controller("MyCartPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {

    };

    $scope.svFeesInCartByUseronSuccess = function(variable, data) {

        $scope.totalSumInCart = _.sumBy(data.content, function(obj) {
            return _.toNumber(obj.fees.amount);
        });
        $scope.Widgets.gridFeesList.totalSumInCart = $scope.totalSumInCart;
    };

}]);


Application.$controller("pagedialogNewFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridFeesListController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.totalSumInCart;
        $scope.customRowAction = function($event, $rowData) {
            $scope.Variables.svUpdateFeesStatus.setInput({
                'status': 'Pending'
            });
            $scope.Variables.svUpdateFeesStatus.update();
        };

    }
]);

Application.$controller("gridFeesListController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);