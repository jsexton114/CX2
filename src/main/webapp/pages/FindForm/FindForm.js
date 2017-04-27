Application.$controller("FindFormPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        $scope.startDate = moment.utc(new Date(2016, 6, 1)).valueOf();
        $scope.toDayEnd = moment().endOf('day').valueOf();
    };

    function isEmployee() {
        let temp = $scope.Variables.loggedInUser.dataSet.roles;
        let isEmployee = false
        for (let i = 0; i < temp.length; i++) {
            if ((temp[i] == "CXAdmin") || (temp[i] == "MunicipalityEmployee")) {
                isEmployee = true;
            }
        }
        return isEmployee;
    }

    $scope.buttonByVendorClick = function($event, $isolateScope) {

        switch ($scope.Widgets.radiosetFromVendor.datavalue) {
            case "Closed":
                $scope.Variables.SearchFormByVendor.setInput({
                    'Closed': true
                });
                break;
            case "Open":
                $scope.Variables.SearchFormByVendor.setInput({
                    'Closed': false
                });
                break;
            case "Both":
                $scope.Variables.SearchFormByVendor.setInput({
                    'Closed': undefined
                });
                break;
        }

        if (!isEmployee()) {
            $scope.Variables.SearchFormByVendor.setFilter({
                'municipalityInternalForm': false,
                'publicRead': true
            });
        }

        $scope.Variables.SearchFormByVendor.update();
    };


    $scope.buttonSearchClick = function($event, $isolateScope) {

        switch ($scope.Widgets.radiosetStatus.datavalue) {
            case "Closed":
                $scope.Variables.svSearchAllFormsByUser.setInput({
                    'Closed': true
                });
                break;
            case "Open":
                $scope.Variables.svSearchAllFormsByUser.setInput({
                    'Closed': false
                });
                break;
            case "Both":
                $scope.Variables.svSearchAllFormsByUser.setInput({
                    'Closed': undefined
                });
                break;
        }

        if (!isEmployee()) {
            $scope.Variables.svSearchAllFormsByUser.setFilter({
                'municipalityInternalForm': false,
                'publicRead': true
            });
        }

        $scope.Variables.svSearchAllFormsByUser.update();
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
                break;
            case "Subdivision":
                $scope.Variables.svSearchAllFormsByAddress.setInput({
                    'GISRecordId': $scope.Widgets.searchSubdivision.datavalue.id
                });
                break;
            case "Parcel":
                $scope.Variables.svSearchAllFormsByAddress.setInput({
                    'GISRecordId': $scope.Widgets.searchParcel.datavalue.id
                });
                break;
        }

        if (!isEmployee()) {
            $scope.Variables.svSearchAllFormsByAddress.setFilter({
                'municipalityInternalForm': false,
                'publicRead': true
            });
        }

        $scope.Variables.svSearchAllFormsByAddress.update();
    };

    function updateCategoriesForUser() {

        $scope.Variables.MunicipalityCategories.setFilter({
            'municipalityId': $scope.Widgets.selectMunicipality.datavalue.ID
        });
        if (!isEmployee()) {
            $scope.Variables.MunicipalityCategories.setFilter({
                'municipalityInternalCategory': false
            });
        }
        $scope.Variables.MunicipalityCategories.update();
    }

    function updateCategoriesForVENDOR() {
        $scope.Variables.MunicipalityCategories.setFilter({
            'municipalityId': $scope.Widgets.selectMuncipalityForVENDOR.datavalue.ID
        });
        if (!isEmployee()) {
            $scope.Variables.MunicipalityCategories.setFilter({
                'municipalityInternalCategory': false
            });
        }
        $scope.Variables.MunicipalityCategories.update();
    }

    function updateCategoriesForAddress() {
        $scope.Variables.MunicipalityCategories.setFilter({
            'municipalityId': $scope.Widgets.selectMunicipalityForAddress.datavalue.ID
        });
        if (!isEmployee()) {
            $scope.Variables.MunicipalityCategories.setFilter({
                'municipalityInternalCategory': false
            });
        }
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
            if (!isEmployee()) {
                $scope.Variables.GetFormTypesByCategoriesAndMunicipalities.setFilter({
                    'municipalityInternalForm': false
                });
            }
            $scope.Variables.GetFormTypesByCategoriesAndMunicipalities.update();
        } else {
            $scope.Variables.GetFormTypesByCategoriesAndMunicipalities.setInput({
                'formCategory': newVal.id
            });
            if (!isEmployee()) {
                $scope.Variables.GetFormTypesByCategoriesAndMunicipalities.setFilter({
                    'municipalityInternalForm': false
                });
            }
            $scope.Variables.GetFormTypesByCategoriesAndMunicipalities.update();
        }

    };


    $scope.selectAddressCategoryChange = function($event, $isolateScope, newVal, oldVal) {
        if (newVal === undefined) {
            $scope.Variables.GetFormTypesByCategoriesAndMunicipalities.setInput({
                'formCategory': undefined
            });
            if (!isEmployee()) {
                $scope.Variables.GetFormTypesByCategoriesAndMunicipalities.setFilter({
                    'municipalityInternalForm': false
                });
            }
            $scope.Variables.GetFormTypesByCategoriesAndMunicipalities.update();
        } else {
            $scope.Variables.GetFormTypesByCategoriesAndMunicipalities.setInput({
                'formCategory': newVal.id
            });
            if (!isEmployee()) {
                $scope.Variables.GetFormTypesByCategoriesAndMunicipalities.setFilter({
                    'municipalityInternalForm': false
                });
            }
            $scope.Variables.GetFormTypesByCategoriesAndMunicipalities.update();
        }
    };


    $scope.selectCategoriesForVendorChange = function($event, $isolateScope, newVal, oldVal) {

        if (newVal === undefined) {
            $scope.Variables.FormTypesByCategoriesForVendor.setInput({
                'formCategory': undefined
            });
            if (!isEmployee()) {
                $scope.Variables.FormTypesByCategoriesForVendor.setFilter({
                    'municipalityInternalForm': false
                });
            }
            $scope.Variables.FormTypesByCategoriesForVendor.update();
        } else {
            $scope.Variables.FormTypesByCategoriesForVendor.setInput({
                'formCategory': newVal.id
            });
            if (!isEmployee()) {
                $scope.Variables.FormTypesByCategoriesForVendor.setFilter({
                    'municipalityInternalForm': false
                });
            }
            $scope.Variables.FormTypesByCategoriesForVendor.update();
        }

    };

    $scope.buttonSearchAdvancedClick = function($event, $isolateScope) {
        if (!isEmployee()) {
            $scope.Variables.svSearchAllFormsByFormTitle.setFilter({
                'municipalityInternalForm': false,
                'publicRead': true
            });
        }
        $scope.Variables.svSearchAllFormsByFormTitle.update();
    };

}]);




Application.$controller("gridAdvancedController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRowAction = function($event, $rowData) {

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

            $scope.Variables.stvFormLink.dataSet.dataValue = $rowData.formGuid;
            $scope.Widgets.pagedialogViewForm.open();
        };

    }
]);

Application.$controller("pagedialogViewFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);