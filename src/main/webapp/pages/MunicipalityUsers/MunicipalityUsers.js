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
        wmToaster.show('success', 'SUCCESS', 'The user has has been removed from the group', 5000);
        $scope.Variables.getMunicipalityGroupIdIDs.setInput('userID', $scope.Widgets.gridEmployees.selecteditem.ID ? $scope.Widgets.gridEmployees.selecteditem.ID : $scope.Widgets.gridAdmin.selecteditem.ID);
        $scope.Variables.getMunicipalityGroupIdIDs.update();
    };


    $scope.addMunicipalityGroupMembersonError = function(variable, data) {
        wmToaster.show('error', 'ERROR', 'The user has already been added to that group', 5000);
    };


    $scope.gridEmployeesClick = function($event, $rowData) {
        $scope.Variables.StateInformation.setFilter('id', $scope.Widgets.gridEmployees.selecteditem.StateId);
        $scope.Variables.StateInformation.update();
    };


    $scope.gridAdminClick = function($event, $rowData) {
        $scope.Variables.StateInformation.setFilter('id', $scope.Widgets.gridAdmin.selecteditem.StateId);
        $scope.Variables.StateInformation.update();
    };


    $scope.FetchRolesForUserWithMunicipalityonSuccess = function(variable, data) {
        var isMunicipalityAdmin = _.findIndex(data.content, {
            'RoleName': 'MunicipalityAdmin'
        });
        if (isMunicipalityAdmin > -1) {
            $scope.Widgets.confirmdialogConfrimAdminDelete.open();

        } else {
            $scope.Variables.DeleteEmployee.update();
        }
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
            $scope.Variables.getMunicipalityGroupIdIDs.setInput('userID', $rowData.ID);
            $scope.Variables.getMunicipalityGroupIdIDs.update();
            $scope.Widgets.EmployeeDialog.open();

        };


        $scope.customRowAction = function($event, $rowData) {
            $scope.Variables.FetchRolesForUserWithMunicipality.setInput({
                'user': $rowData.ID
            });
            $scope.Variables.FetchRolesForUserWithMunicipality.update();
        };

    }
]);

Application.$controller("dialogAddEmployeeORAdminController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.buttonRoleClick = function($event, $isolateScope) {
            // Checking if adding emp or admin
            if ($scope.Variables.AdminOrEmp.dataSet.dataValue == "MunicipalityEmployee")
                $scope.Variables.NewRole.update();
            else {
                // Checking if he is already a emp
                if ($scope.Variables.CheckingUserWithMunicipalityInRoles.dataSet.content[0].exist > 0) {
                    //already a emp then add as admin
                    $scope.Variables.NewRole.update();
                } else {
                    // Adding both employee and admin
                    $scope.Variables.NewRole.update();
                    $scope.Variables.NewRole.setInput({
                        'RoleName': 'MunicipalityEmployee'
                    });
                    $scope.Variables.NewRole.update();
                }
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

        $scope.customButtonAction2 = function($event) {
            $scope.Variables.AdminOrEmp.dataSet.dataValue = "MunicipalityAdmin";
        };


        $scope.customRow1Action2 = function($event, $rowData) {
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

Application.$controller("confirmdialogConfrimAdminDeleteController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.confirmdialogConfrimAdminDeleteOk = function($event, $isolateScope) {
            $scope.Variables.DeleteEmployeeWithAdmin.update();
            $scope.Variables.DeleteAdmin.setInput({
                'user': parseInt($scope.Widgets.gridEmployees.selecteditem.ID)
            });
            $scope.Variables.DeleteAdmin.update();
        };

    }
]);