Application.$controller("EditFormPageController", ["$scope", "wmToaster", function($scope, wmToaster) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {

    };

    $scope.liveform2Error = function($event, $operation, $data) {
        wmToaster.show('error', 'ERROR', 'Sort Order cannot be duplicated.', 5000);
    };


    $scope.svSaveFormFieldonSuccess = function(variable, data) {
        $scope.Widgets.textFormFieldLabel.reset();
        $scope.Widgets.selectFormFieldType.reset();
        $scope.Widgets.checkboxFormFieldRequired.reset();
        $scope.Widgets.textFormFieldDefaultValue.reset();
        $scope.Widgets.textareaFormFieldHelpText.reset();
        $scope.Widgets.textFormFieldDisplayOrder.reset();
    };


    $scope.liveformUpdateFormTypeBeforeservicecall = function($event, $operation, $data) {
        if ($operation === 'update') {
            if ($scope.Widgets.gisOptionSelect._proxyModel === 'Multiple') {
                $data.gisrecord = true;
                $data.multipleGisrecords = true;
            } else if ($scope.Widgets.gisOptionSelect._proxyModel === 'Single') {
                $data.gisrecord = true;
                $data.multipleGisrecords = false;
            } else {
                $data.gisrecord = false;
                $data.multipleGisrecords = false;
            }
        }
    };

}]);


Application.$controller("liveformUpdateFormTypeController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.prefixDateChange = function($event, $isolateScope, newVal, oldVal) {
            if (newVal === 'None') {
                $scope.Widgets.liveformUpdateFormType.formWidgets.prefixNumber.datavalue = 'AutoIncrement';
            }
        };

        $scope.generateFormTitlePreview = function() {
            var prefix = $scope.Widgets.liveformUpdateFormType.formWidgets.titlePrefix.datavalue;
            var dateOption = $scope.Widgets.liveformUpdateFormType.formWidgets.prefixDate.datavalue;
            var numberOption = $scope.Widgets.liveformUpdateFormType.formWidgets.prefixNumber.datavalue;
            var prefixDashes = $scope.Widgets.liveformUpdateFormType.formWidgets.prefixDashes.datavalue;
            var numberStart = $scope.Widgets.liveformUpdateFormType.formWidgets.prefixNumberStart.datavalue;

            var formTitlePreview = '' + prefix;

            var today = new Date();

            if (dateOption !== 'None') {
                var year = today.getFullYear();
                var month = today.getMonth() + 1;

                month = (month < 10) ? '0' + month : month;
                if (prefixDashes === true) {
                    formTitlePreview += '-';
                }
                if (dateOption === 'MonthYear') {
                    formTitlePreview += month + '' + year;
                } else {
                    formTitlePreview += year + '' + month;
                }
            }

            if (prefixDashes === true) {
                formTitlePreview += '-';
            }

            formTitlePreview += numberStart;

            return formTitlePreview;
        };


        $scope.gisOptionSelectChange = function($event, $isolateScope, newVal, oldVal) {
            $scope.Widgets.liveformUpdateFormType.formWidgets.gismap.setProperty('disabled', (newVal === 'None'));
        };

    }
]);

Application.$controller("grid1Controller", ["$scope",
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

Application.$controller("grid2Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveform3Controller", ["$scope",
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

Application.$controller("dlgFormTypeFieldController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);