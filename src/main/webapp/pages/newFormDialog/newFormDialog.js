Application.$controller("newFormDialogPageController", ["$scope", "$location", function($scope, $location) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.isEmployee;

    function userFilter() {
        if (!$scope.Widgets.selectMunicipality.datavalue) {
            return;
        }

        //setting inputs for categories
        $scope.Variables.lvFormCategories.setFilter({
            'municipalityId': $scope.Widgets.selectMunicipality.datavalue,
            'municipalityInternalCategory': false,
        });
        $scope.Variables.lvFormCategories.update();

        //setting inputs for forms
        $scope.Variables.svFormTypes.setInput({
            'municipalityId': $scope.Widgets.selectMunicipality.datavalue,
            'formCategory': $scope.Widgets.selectCategory.datavalue,
            'municipalityInternalForm': false
        });
        $scope.Variables.svFormTypes.update();
    }

    function employeeFilter() {
        if (!$scope.Widgets.selectMunicipality.datavalue) {
            return;
        }

        //setting inputs for categories
        $scope.Variables.lvFormCategories.setFilter({
            'municipalityId': $scope.Widgets.selectMunicipality.datavalue,
            'municipalityInternalCategory': undefined
        });
        $scope.Variables.lvFormCategories.update();

        //setting inputs for forms
        $scope.Variables.svFormTypes.setInput({
            'municipalityId': $scope.Widgets.selectMunicipality.datavalue,
            'formCategory': $scope.Widgets.selectCategory.datavalue,
            'municipalityInternalForm': undefined
        });
        $scope.Variables.svFormTypes.update();
    }

    function filterResults() {
        if ($scope.isEmployee) {
            employeeFilter();
        } else {
            userFilter();
        }
    }

    $scope.onPageReady = function() {
        var roles = $scope.Variables.loggedInUser.dataSet.roles;
        $scope.isEmployee = false;
        for (let i = 0; i < roles.length; i++) {
            if ((roles[i] == "MunicipalityEmployee")) {
                $scope.isEmployee = true;
            }
        }
        filterResults();
    };

    $scope.closeDlg = function() {
        $scope.$parent.Widgets[$scope.pageParams.dialogName].close();
    };

    $scope.button1Click = function($event, $isolateScope) {
        $scope.closeDlg();
    };

    $scope.$watch(function() {
        return $scope.pageParams.municipalityId;
    }, function(newValue, oldValue) {
        if (newValue === undefined) {
            $scope.Widgets.selectMunicipality.datavalue = undefined;
        } else {
            $scope.Widgets.selectMunicipality.datavalue = newValue;
        }
    });

    $scope.buttonCreateClick = function($event, $isolateScope) {
        $location.path("/NewForm").search("formTypeId", $scope.Widgets.selectForm.datavalue);
    };

    $scope.selectMunicipalityChange = function($event, $isolateScope, newVal, oldVal) {
        if (newVal == undefined) {
            $scope.Variables.lvFormCategories.dataSet = {};
            $scope.Variables.svFormTypes.dataSet = {};
        } else {
            filterResults();
        }
    };

    $scope.selectCategoryChange = function($event, $isolateScope, newVal, oldVal) {
        filterResults();
    };
}]);