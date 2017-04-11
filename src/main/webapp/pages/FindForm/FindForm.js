Application.$controller("FindFormPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        $scope.startDate = moment.utc(new Date(2016, 6, 1)).valueOf();
        $scope.toDayEnd = moment().endOf('day').valueOf();
    };


    $scope.buttonByVendorClick = function($event, $isolateScope) {

        switch ($scope.Widgets.radiosetFromVendor.datavalue) {
            case "Closed":
                $scope.Variables.SearchFormByVendor.setInput({
                    'Closed': true
                });
                $scope.Variables.SearchFormByVendor.update();
                break;
            case "Open":
                $scope.Variables.SearchFormByVendor.setInput({
                    'Closed': false
                });
                $scope.Variables.SearchFormByVendor.update();
                break;
            case "Both":
                $scope.Variables.SearchFormByVendor.update();
                break;
        }
    };


    $scope.buttonSearchClick = function($event, $isolateScope) {
        debugger
        switch ($scope.Widgets.radiosetStatus.datavalue) {
            case "Closed":
                $scope.Variables.svSearchAllFormsByUser.setInput({
                    'Closed': true
                });
                $scope.Variables.svSearchAllFormsByUser.update();
                break;
            case "Open":
                $scope.Variables.svSearchAllFormsByUser.setInput({
                    'Closed': false
                });
                $scope.Variables.svSearchAllFormsByUser.update();
                break;
            case "Both":
                $scope.Variables.svSearchAllFormsByUser.update();
                break;
        }
    };

}]);




Application.$controller("gridAdvancedController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRowAction = function($event, $rowData) {
            $scope.Variables.goToPage_FormsFormSearch.setData({
                'FormGUID': $rowData.formGuid
            });
            $scope.Variables.goToPage_FormsFormSearch.navigate();
        };

    }
]);



Application.$controller("gridAddressController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("livefilter1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridVENDORController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRowAction = function($event, $rowData) {
            $scope.Variables.goToPage_FormsFormSearch.setData({
                'FormGUID': $rowData.formGuid
            });
            $scope.Variables.goToPage_FormsFormSearch.navigate();
        };

    }
]);

Application.$controller("gridUserFormsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRowAction = function($event, $rowData) {
            $scope.Variables.goToPage_FormsFormSearch.setData({
                'FormGUID': $rowData.formGuid
            });
            $scope.Variables.goToPage_FormsFormSearch.navigate();
        };

    }
]);