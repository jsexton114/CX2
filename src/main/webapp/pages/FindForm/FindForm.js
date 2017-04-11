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


    $scope.buttonSearchByAddressClick = function($event, $isolateScope) {

        switch ($scope.Widgets.radiosetStatus.datavalue) {
            case "Closed":
                $scope.Variables.svSearchAllFormsByAddress.setInput({
                    'Closed': true
                });
                break;
            case "Open":
                $scope.Variables.svSearchAllFormsByAddress.setInput({
                    'Closed': false
                });
                break;
            case "Both":
                // Set Nothing
                break;
        }
        switch ($scope.Widgets.radiosetSelectAddressType.datavalue) {
            case "Address":
                $scope.Variables.svSearchAllFormsByAddress.setInput({
                    'GISRecordId': $scope.Widgets.searchAddress.datavalue.id
                });
                $scope.Variables.svSearchAllFormsByAddress.update();
                break;
            case "Subdivision":
                $scope.Variables.svSearchAllFormsByAddress.setInput({
                    'GISRecordId': $scope.Widgets.searchSubdivision.datavalue.id
                });
                $scope.Variables.svSearchAllFormsByAddress.update();
                break;
            case "Parcel":
                $scope.Variables.svSearchAllFormsByAddress.setInput({
                    'GISRecordId': $scope.Widgets.searchParcel.datavalue.id
                });
                $scope.Variables.svSearchAllFormsByAddress.update();
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

Application.$controller("gridFormsAddressController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRowAction = function($event, $rowData) {
            $scope.customRowAction = function($event, $rowData) {
                $scope.Variables.goToPage_FormsFormSearch.setData({
                    'FormGUID': $rowData.formGuid
                });
                $scope.Variables.goToPage_FormsFormSearch.navigate();
            };
        };

    }
]);