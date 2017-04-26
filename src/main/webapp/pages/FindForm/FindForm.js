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
                $scope.Variables.SearchFormByVendor.setInput({
                    'Closed': undefined
                });
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
                $scope.Variables.svSearchAllFormsByUser.setInput({
                    'Closed': undefined
                });
                $scope.Variables.svSearchAllFormsByUser.update();
                break;
        }
    };


    $scope.buttonSearchByAddressClick = function($event, $isolateScope) {
        debugger

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
                $scope.Variables.svSearchAllFormsByAddress.setInput({
                    'Closed': undefined
                });
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

    function updateCategoriesForUser() {

        $scope.Variables.MunicipalityCategories.setFilter({
            'municipalityId': $scope.Widgets.selectMunicipality.datavalue.ID
        });
        $scope.Variables.MunicipalityCategories.update();
    }

    function updateCategoriesForVENDOR() {
        $scope.Variables.MunicipalityCategories.setFilter({
            'municipalityId': $scope.Widgets.selectMuncipalityForVENDOR.datavalue.ID
        });
        $scope.Variables.MunicipalityCategories.update();
    }

    function updateCategoriesForAddress() {
        $scope.Variables.MunicipalityCategories.setFilter({
            'municipalityId': $scope.Widgets.selectMunicipalityForAddress.datavalue.ID
        });
        $scope.Variables.MunicipalityCategories.update();
    }

    $scope.selectMunicipalityChange = function($event, $isolateScope, newVal, oldVal) {
        updateCategoriesForUser();
    };


    $scope.selectMuncipalityForVENDORChange = function($event, $isolateScope, newVal, oldVal) {
        updateCategoriesForVENDOR();
    };


    $scope.selectMunicipalityForAddressChange = function($event, $isolateScope, newVal, oldVal) {
        updateCategoriesForAddress();
    };


    $scope.tabpaneUSERSelect = function($event, $isolateScope) {
        updateCategoriesForUser();
    };


    $scope.tabpaneVENDORSelect = function($event, $isolateScope) {
        updateCategoriesForVENDOR();
    };


    $scope.tabpaneADDRESSSelect = function($event, $isolateScope) {
        updateCategoriesForAddress();
    };


    $scope.selectCategoriesChange = function($event, $isolateScope, newVal, oldVal) {

        if (newVal === undefined) {
            $scope.Variables.GetFormTypesByCategoriesAndMunicipalities.setInput({
                'formCategory': undefined
            });
            $scope.Variables.GetFormTypesByCategoriesAndMunicipalities.update();
        } else {
            $scope.Variables.GetFormTypesByCategoriesAndMunicipalities.setInput({
                'formCategory': newVal.id
            });
            $scope.Variables.GetFormTypesByCategoriesAndMunicipalities.update();
        }

    };


    $scope.selectAddressCategoryChange = function($event, $isolateScope, newVal, oldVal) {
        if (newVal === undefined) {
            $scope.Variables.GetFormTypesByCategoriesAndMunicipalities.setInput({
                'formCategory': undefined
            });
            $scope.Variables.GetFormTypesByCategoriesAndMunicipalities.update();
        } else {
            $scope.Variables.GetFormTypesByCategoriesAndMunicipalities.setInput({
                'formCategory': newVal.id
            });
            $scope.Variables.GetFormTypesByCategoriesAndMunicipalities.update();
        }
    };


    $scope.selectCategoriesForVendorChange = function($event, $isolateScope, newVal, oldVal) {

        if (newVal === undefined) {
            $scope.Variables.FormTypesByCategoriesForVendor.setInput({
                'formCategory': undefined
            });
            $scope.Variables.FormTypesByCategoriesForVendor.update();
        } else {
            $scope.Variables.FormTypesByCategoriesForVendor.setInput({
                'formCategory': newVal.id
            });
            $scope.Variables.FormTypesByCategoriesForVendor.update();
        }

    };




}]);




Application.$controller("gridAdvancedController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRowAction = function($event, $rowData) {
            // $scope.Variables.goToPage_FormsFormSearch.setData({
            //     'FormGUID': $rowData.formGuid
            // });
            // $scope.Variables.goToPage_FormsFormSearch.navigate();
            // var tempLink = window.location.hostname + "/#/Forms?FormGUID=" + $rowData.formGuid
            // window.open(tempLink);
            $scope.Variables.stvFormLink.dataSet.dataValue = $rowData.formGuid;
            $scope.Widgets.pagedialogViewForm.open();
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
            // $scope.Variables.goToPage_FormsFormSearch.setData({
            //     'FormGUID': $rowData.formGuid
            // });
            // $scope.Variables.goToPage_FormsFormSearch.navigate();
            // var tempLink = window.location.hostname + "/#/Forms?FormGUID=" + $rowData.formGuid
            // window.open(tempLink);
            $scope.Variables.stvFormLink.dataSet.dataValue = $rowData.formGuid;
            $scope.Widgets.pagedialogViewForm.open();
        };

    }
]);

Application.$controller("gridUserFormsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRowAction = function($event, $rowData) {
            // $scope.Variables.goToPage_FormsFormSearch.setData({
            //     'FormGUID': $rowData.formGuid
            // });
            // $scope.Variables.goToPage_FormsFormSearch.navigate();
            // var tempLink = window.location.hostname + "/#/Forms?FormGUID=" + $rowData.formGuid
            // window.open(tempLink);
            $scope.Variables.stvFormLink.dataSet.dataValue = $rowData.formGuid;
            $scope.Widgets.pagedialogViewForm.open();
        };

    }
]);

Application.$controller("gridFormsAddressController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRowAction = function($event, $rowData) {
            $scope.customRowAction = function($event, $rowData) {
                // $scope.Variables.goToPage_FormsFormSearch.setData({
                //     'FormGUID': $rowData.formGuid
                // });
                // $scope.Variables.goToPage_FormsFormSearch.navigate();
                // var tempLink = window.location.hostname + "/#/Forms?FormGUID=" + $rowData.formGuid
                // window.open(tempLink);
                $scope.Variables.stvFormLink.dataSet.dataValue = $rowData.formGuid;
                $scope.Widgets.pagedialogViewForm.open();
            };
        };

    }
]);

Application.$controller("pagedialogViewFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);