Application.$controller("MyCartPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {

    };

    // $scope.svFeesInCartByUseronSuccess = function(variable, data) {
    //     $scope.Widgets.gridFeesList.totalSumInCart = _.sumBy(data.content, function(obj) {
    //         return _.toNumber(obj.fees.amount);
    //     });
    // };
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

        $scope.customButtonAction = function($event) {
            debugger
            let temp = $scope.Variables.loggedInUser.dataSet.roles;
            for (let i = 0; i < temp.length; i++) {
                if ((temp[i] == "MunicipalityEmployee")) {
                    $scope.Variables.goToPage_MunicipalityCheckOut.navigate();
                }
            }
        };

    }
]);