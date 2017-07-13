Application.$controller("EditFormPageController", ["$scope", "wmToaster", function($scope, wmToaster) {
    "use strict";

    $scope.tokensByTwo = [];

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {

    };

    $scope.liveform2Error = function($event, $operation, $data) {
        wmToaster.show('error', 'ERROR', 'Sort Order cannot be duplicated.', 5000);
    };

    $scope.liveformUpdateFormTypeBeforeservicecall = function($event, $operation, $data) {
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

    $scope.svGetAvailableTokensonSuccess = function(variable, data) {
        // $scope.tokensByTwo = [];

        // data.forEach(function(token, index) {
        //     if (index % 2 === 0) {
        //         $scope.tokensByTwo.push([]);
        //     }

        //     $scope.tokensByTwo[$scope.tokensByTwo.length - 1].push(token);
        // });
    };

    $scope.svGetLetterTemplateonSuccess = function(variable, data) {

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
                $scope.Widgets.liveformUpdateFormType.formWidgets.allowOwnerToCompleteWork.datavalue = false;
            }
        };

        $scope.collectFeesChange = function($event, $isolateScope, newVal, oldVal) {
            if (newVal === false) {
                $scope.Widgets.liveformUpdateFormType.formWidgets.automaticFees.datavalue = false;

            }
        };

        $scope.expirationTypeChange = function($event, $isolateScope, newVal, oldVal) {
            if (!newVal) {
                $scope.Widgets.liveformUpdateFormType.formWidgets.expirationDays.datavalue = undefined;
                $scope.Widgets.liveformUpdateFormType.formWidgets.expirationStatusId.datavalue = undefined;
            } else {
                $scope.Widgets.liveformUpdateFormType.formWidgets.expirationDays.datavalue = 365;
            }
        };

        $scope.requireOwnerChange = function($event, $isolateScope, newVal, oldVal) {

            $scope.Widgets.liveformUpdateFormType.formWidgets.allowOwnerToCompleteWork.datavalue = false
        };


        $scope.selectVendorOptionChange = function($event, $isolateScope, newVal, oldVal) {
            if (newVal === 'None') {
                $scope.Widgets.liveformUpdateFormType.formWidgets.allowOwnerToCompleteWork.datavalue = false;
                $scope.Widgets.liveformUpdateFormType.formWidgets.allowContractorTbd.datavalue = false;
            }
        };

    }
]);

Application.$controller("gridFormTypeStatusController", ["$scope", "$timeout",
    function($scope, $timeout) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.updaterowAction = function($event, $rowData) {
            $timeout(function() {
                $scope.Widgets.municipalityGroupsByProcessOwners.datavalue = JSON.parse(angular.toJson($scope.Widgets.gridFormTypeStatus.selecteditem.municipalityGroupsByProcessOwners));
                $scope.Widgets.municipalityGroupsByWriteAccess.datavalue = JSON.parse(angular.toJson($scope.Widgets.gridFormTypeStatus.selecteditem.municipalityGroupsByWriteAccess));
            });
        };
    }
]);

Application.$controller("lfFormTypeStatusController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.allowAuthorEditsChange = function($event, $isolateScope, newVal, oldVal) {
            if (newVal === false) {
                $scope.Widgets.lfFormTypeStatus.formWidgets.allowSharedWithEdits.datavalue = false;
            }
        };

        $scope.considerClosedChange = function($event, $isolateScope, newVal, oldVal) {
            if (newVal === true) {
                $scope.Widgets.lfFormTypeStatus.formWidgets.allowAuthorEdits.datavalue = false;
                $scope.Widgets.lfFormTypeStatus.formWidgets.allowSharedWithEdits.datavalue = false;
                $scope.Widgets.lfFormTypeStatus.formWidgets.recalculateAutoFees.datavalue = false;
                $scope.Widgets.lfFormTypeStatus.formWidgets.allowPayment.datavalue = false;
                $scope.Widgets.lfFormTypeStatus.formWidgets.advanceOnZero.datavalue = false;
                $scope.Widgets.lfFormTypeStatus.formWidgets.allowInspections.datavalue = false;
            }
        };
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

Application.$controller("dialogShowMunicipalityGroupsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridMunicipalityGroupsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveformMunicipalityGroupsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogManageSelectedGroupController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridGroupMembersController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogAddUserToGroupController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveformLettersController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridCodeSetsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveformCodeSetsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogFormStatusLettersController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.liveformStatusLettersBeforeservicecall = function($event, $operation, $data) {
            $data.formStatusId = $scope.Widgets.gridFormTypeStatus.selectedItems[0].id;
        };
    }
]);

Application.$controller("dialogAddStatusLetterController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridStatusLettersController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveformStatusLettersController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogCopyStatusesController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);