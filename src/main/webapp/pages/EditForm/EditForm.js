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
        $scope.Widgets.textDefaultValue.reset();
        $scope.Widgets.longTextDefault.reset();
        $scope.Widgets.dateDefaultValue.reset();
        $scope.Widgets.datetimeDefaultValue.reset();
        $scope.Widgets.numberDefaultValue.reset();
        $scope.Widgets.booleanDefaultValue.reset();
        $scope.Widgets.textareaFormFieldHelpText.reset();
        $scope.Widgets.textFormFieldDisplayOrder.reset();
        $scope.Variables.stvPossibleValues.dataSet = [];
    };


    $scope.liveformUpdateFormTypeBeforeservicecall = function($event, $operation, $data) {
        console.log($data.instructions);
        if ($operation === 'update') {
            if ($scope.Widgets.gisOptionSelect.datavalue === 'Multiple') {
                $data.gisrecord = true;
                $data.multipleGisrecords = true;
            } else if ($scope.Widgets.gisOptionSelect.datavalue === 'Single') {
                $data.gisrecord = true;
                $data.multipleGisrecords = false;
            } else {
                $data.gisrecord = false;
                $data.multipleGisrecords = false;
            }

            if ($scope.Widgets.selectVendorOption.datavalue === 'Multiple') {
                $data.vendorSelection = true;
                $data.multipleVendors = true;
            } else if ($scope.Widgets.selectVendorOption.datavalue === 'Single') {
                $data.vendorSelection = true;
                $data.multipleVendors = false;
            } else {
                $data.vendorSelection = false;
                $data.multipleVendors = false;
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
            if (newVal === 'None') {
                $scope.Widgets.liveformUpdateFormType.formWidgets.gismap.datavalue = false;
                $scope.Widgets.liveformUpdateFormType.formWidgets.requireOwner.datavalue = false;
            }
        };

    }
]);

Application.$controller("gridFormTypeStatusController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("lfFormTypeStatusController", ["$scope",
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

Application.$controller("gridFieldsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dlgFormTypeFieldController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.buttonSaveFormFieldClick = function($event, $isolateScope) {
            var possibleValues = "";
            var defaultValue = "";

            switch ($scope.Widgets.selectFormFieldType._proxyModel.label) {
                case 'Text':
                case 'Header':
                    defaultValue = $scope.Widgets.textDefaultValue.datavalue;
                    break;
                case 'Long Text':
                case 'Instruction Text':
                    defaultValue = $scope.Widgets.longTextDefault.datavalue;
                    break;
                case 'Date':
                    defaultValue = $scope.Widgets.dateDefaultValue.datavalue;
                    break;
                case 'Date+Time':
                    defaultValue = !!$scope.Widgets.datetimeDefaultValue.datavalue ? moment($scope.Widgets.datetimeDefaultValue.datavalue).format("YYYY-MM-DD HH:mm:ss") : '';
                    break;
                case 'Number':
                    defaultValue = $scope.Widgets.numberDefaultValue.datavalue;
                    break;
                case 'Currency':
                    defaultValue = $scope.Widgets.currencyDefaultValue.datavalue;
                    break;
                case 'Boolean':
                    defaultValue = $scope.Widgets.booleanDefaultValue.datavalue;
                    break;
                default:
                    defaultValue = $scope.Widgets.textDefaultValue.datavalue;
                    break;
            }

            $scope.Variables.stvPossibleValues.dataSet.forEach(function(possibleValue, index) {
                if (index > 0) {
                    possibleValues += ',';
                }
                possibleValues += possibleValue.dataValue.replace(/,/g, '&#44;');
            });

            $scope.Variables.svSaveFormField.setInput('possibleValues', possibleValues);
            $scope.Variables.svSaveFormField.setInput('defaultValue', defaultValue);
            $scope.Variables.svSaveFormField.update();
            $scope.Widgets.dlgFormTypeField.close();
        };

    }
]);

Application.$controller("gridPossibleValuesController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridFormtoinspectioncategorymappController", ["$scope",
	function($scope) {
		"use strict";
		$scope.ctrlScope = $scope;
	}
]);

Application.$controller("liveformFormtoinspectioncategoryController", ["$scope",
	function($scope) {
		"use strict";
		$scope.ctrlScope = $scope;
	}
]);

Application.$controller("gridInspectionSequenceController", ["$scope",
	function($scope) {
		"use strict";
		$scope.ctrlScope = $scope;
	}
]);

Application.$controller("liveformInspectionSequenceController", ["$scope",
	function($scope) {
		"use strict";
		$scope.ctrlScope = $scope;
	}
]);