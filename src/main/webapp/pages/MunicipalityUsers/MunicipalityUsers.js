Application.$controller("MunicipalityUsersPageController", ["$scope", "wmToaster", function($scope, wmToaster) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        /*
         * variables can be accessed through '$scope.Variables' property here
         * e.g. to get dataSet in a staticVariable named 'loggedInUser' use following script
         * $scope.Variables.loggedInUser.getData()
         *
         * widgets can be accessed through '$scope.Widgets' property here
         * e.g. to get value of text widget named 'username' use following script
         * '$scope.Widgets.username.datavalue'
         */
    };



    $scope.MunicipalityGroupMembersDataonError = function(variable, data) {
        wmToaster.show('error', 'ERROR', 'The user you selected has already been added to this group.', 5000);
    };


    $scope.getMunicipalityGroupIdIDsonSuccess = function(variable, data) {
        var items = [];
        if (data.totalElements === 0) {
            $scope.Variables.getListofGroupName.setInput('MunicipalityGroupID', 1);
            $scope.Variables.getListofGroupName.setInput('MunicipalityID', $scope.Widgets.selectMunicipality.datavalue.ID);
            $scope.Variables.getListofGroupName.update({}, function(data) {

            });
            return;
        } else {
            for (var i = 0; i <= ((data.totalElements) - 1); i++) {
                if (data.content[i].MunicipalityGroupId === null) {
                    continue;
                }
                items.push(data.content[i].MunicipalityGroupId)

            }

            $scope.Variables.getListofGroupName.setInput('MunicipalityGroupID', items);
            $scope.Variables.getListofGroupName.setInput('MunicipalityID', $scope.Widgets.selectMunicipality.datavalue.ID);
            $scope.Variables.getListofGroupName.update({}, function(data) {

            });
        }
    };


    $scope.deleteMunicipalityGrouponSuccess = function(variable, data) {
        wmToaster.show('success', 'SUCCESS', 'The user has has been removed from the Group', 5000);
        $scope.Variables.getMunicipalityGroupIdIDs.setInput('userID', $scope.Widgets.gridEmployees.selecteditem.ID ? $scope.Widgets.gridEmployees.selecteditem.ID : $scope.Widgets.gridAdmin.selecteditem.ID);
        $scope.Variables.getMunicipalityGroupIdIDs.update();
    };


    $scope.addMunicipalityGroupMembersonError = function(variable, data) {
        wmToaster.show('error', 'ERROR', 'The user has already been added to the selected Group', 5000);
    };

}]);



Application.$controller("gridUsersubscriptionsListController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialog1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);




Application.$controller("grid4Controller", ["$scope",
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

Application.$controller("gridEmployeesController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customButtonAction = function($event) {
            $scope.Variables.AdminOrEmp.dataSet.dataValue = "MunicipalityEmployee";
        };


        $scope.customRow1Action = function($event, $rowData) {
            debugger
            $scope.Variables.StateInformation.setFilter('id', $scope.Widgets.gridEmployees.selecteditem.StateId);
            $scope.Variables.StateInformation.update({}, function(data) {
                debugger;
            });
            $scope.Variables.getMunicipalityGroupIdIDs.setInput('userID', $rowData.ID);
            $scope.Variables.getMunicipalityGroupIdIDs.update();
            $scope.Widgets.EmployeeDialog.open();

        };

    }
]);

Application.$controller("dialogAddEmployeeORAdminController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.buttonRoleClick = function($event, $isolateScope) {
            if ($scope.Variables.CheckingUserWithMunicipalityInRoles.dataSet.content[0].exist > 0) {
                $scope.Variables.UpdateEmployeeORAdminRoleForMunicipality.update();
            } else {
                $scope.Variables.NewRole.update();
            }
        };

    }
]);

Application.$controller("MunicipalitygroupsgridController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveform1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("ManageUsersController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("GroupMembersDataController", ["$scope", "wmToaster",
    function($scope, wmToaster) {
        "use strict";
        $scope.ctrlScope = $scope;


        $scope.liveform2Error = function($event, $operation, $data) {
            wmToaster.show('error', 'ERROR', 'The user you selected has already been added to this group.', 5000);

        };

    }
]);

Application.$controller("gridAdminController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        debugger

        $scope.customButtonAction2 = function($event) {
            debugger
            $scope.Variables.AdminOrEmp.dataSet.dataValue = "MunicipalityAdmin";
        };


        $scope.customRow1Action2 = function($event, $rowData) {
            debugger;
            $scope.Widgets.gridAdmin.selecteditem.StateId
            $scope.Variables.StateInformation.setFilter('id', $scope.Widgets.gridAdmin.selecteditem.StateId);
            $scope.Variables.StateInformation.update();
            $scope.Variables.getMunicipalityGroupIdIDs.setInput('userID', $rowData.ID);
            $scope.Variables.getMunicipalityGroupIdIDs.update();
            $scope.Widgets.Admindialog.open();
        };

    }
]);

Application.$controller("dialog5Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("AddGroupstoMembersController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;


    }
]);

Application.$controller("EmployeeDialogController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("grid6Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRowAction = function($event, $rowData) {
            $scope.Variables.deleteMunicipalityGroup.setInput('MunicipalityGroupId', $rowData.ID);
            $scope.Variables.deleteMunicipalityGroup.setInput('UserId', $scope.Widgets.gridEmployees.selecteditem.ID);
            $scope.Variables.deleteMunicipalityGroup.update();

        };

    }
]);

Application.$controller("AdmindialogController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("grid7Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRowAction = function($event, $rowData) {
            $scope.Variables.deleteMunicipalityGroup.setInput('MunicipalityGroupId', $rowData.ID);
            $scope.Variables.deleteMunicipalityGroup.setInput('UserId', $scope.Widgets.gridAdmin.selecteditem.ID);
            $scope.Variables.deleteMunicipalityGroup.update();
        };

    }
]);