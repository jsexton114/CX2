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


    $scope.lvInsertRoleonError = function(variable, data) {

        //Do nothing
    };





    $scope.AllUsersonSuccess = function(variable, data) {
        debugger
        $scope.Variables.filterUsers.dataSet = data;
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

Application.$controller("gridInspectorController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customButtonAction = function($event) {
            $scope.Variables.stvSelectedRole.dataSet.dataValue = "Inspector";
        };


        $scope.customRow1Action = function($event, $rowData) {
            $scope.Variables.getMunicipalityGroupIdIDs.setInput('userID', $rowData.ID);
            $scope.Variables.getMunicipalityGroupIdIDs.update();
            $scope.Widgets.EmployeeDialog.open();

        };


        // $scope.customRowAction = function($event, $rowData) {
        //     $scope.Variables.FetchRolesForUserWithMunicipality.setInput({
        //         'user': $rowData.ID
        //     });
        //     $scope.Variables.FetchRolesForUserWithMunicipality.update();
        // };


        $scope.customRowAction1 = function($event, $rowData) {

            $scope.Variables.stvDeleteRole.dataSet.role = "Inspector";
            $scope.Variables.stvDeleteRole.dataSet.user = $rowData.ID;
            $scope.Variables.stvDeleteRoleText.dataSet.dataValue = "This action will also revoke all higher privileges that have been granted (InspectorManager). Would you like to proceed?";
            $scope.Widgets.dialogDeleteRoleConfrim.open();
        };

    }
]);

Application.$controller("dialogAddRoleController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        function insertAdmin() {
            $scope.Variables.lvInsertRole.setInput({
                'roleName': 'MunicipalityAdmin',
                'municipalityId': parseInt($scope.Widgets.selectMunicipality.datavalue.ID),
                'userId': $scope.Widgets.textSelectUser.datavalue
            });
            $scope.Variables.lvInsertRole.insertRecord();
        }

        function insertCodeManager() {
            $scope.Variables.lvInsertRole.setInput({
                'roleName': 'CodeManager',
                'municipalityId': parseInt($scope.Widgets.selectMunicipality.datavalue.ID),
                'userId': $scope.Widgets.textSelectUser.datavalue
            });
            $scope.Variables.lvInsertRole.insertRecord();
        }

        function insertCodeOfficer() {
            $scope.Variables.lvInsertRole.setInput({
                'roleName': 'CodeOfficer',
                'municipalityId': parseInt($scope.Widgets.selectMunicipality.datavalue.ID),
                'userId': $scope.Widgets.textSelectUser.datavalue
            });
            $scope.Variables.lvInsertRole.insertRecord();
        }

        function insertInspectorManager() {
            $scope.Variables.lvInsertRole.setInput({
                'roleName': 'InspectorManager',
                'municipalityId': parseInt($scope.Widgets.selectMunicipality.datavalue.ID),
                'userId': $scope.Widgets.textSelectUser.datavalue
            });
            $scope.Variables.lvInsertRole.insertRecord();
        }

        function insertInspector() {
            $scope.Variables.lvInsertRole.setInput({
                'roleName': 'Inspector',
                'municipalityId': parseInt($scope.Widgets.selectMunicipality.datavalue.ID),
                'userId': $scope.Widgets.textSelectUser.datavalue
            });
            $scope.Variables.lvInsertRole.insertRecord();
        }

        function insertMunicipalityEmployee() {
            $scope.Variables.lvInsertRole.setInput({
                'roleName': 'MunicipalityEmployee',
                'municipalityId': parseInt($scope.Widgets.selectMunicipality.datavalue.ID),
                'userId': $scope.Widgets.textSelectUser.datavalue
            });
            $scope.Variables.lvInsertRole.insertRecord();
        }


        $scope.buttonRoleClick = function($event, $isolateScope) {
            // lvInsertRole

            switch ($scope.Variables.stvSelectedRole.dataSet.dataValue) {
                case "MunicipalityEmployee":
                    insertMunicipalityEmployee();
                    break;
                case "Inspector":
                    insertMunicipalityEmployee();
                    insertInspector();
                    break;
                case "InspectorManager":
                    insertMunicipalityEmployee();
                    insertInspector();
                    insertInspectorManager();
                    break;
                case "CodeOfficer":
                    insertMunicipalityEmployee();
                    insertCodeOfficer();
                    break;
                case "CodeManager":
                    insertMunicipalityEmployee();
                    insertCodeOfficer();
                    insertCodeManager();
                    break;
                case "MunicipalityAdmin":
                    insertAdmin();
                    insertCodeManager();
                    insertCodeOfficer();
                    insertInspectorManager();
                    insertInspector();
                    insertMunicipalityEmployee();
                    break;

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
            $scope.Variables.stvSelectedRole.dataSet.dataValue = "MunicipalityAdmin";
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

Application.$controller("confirmdialogConfrimRoleDeleteController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.confirmdialogConfrimAdminDeleteOk = function($event, $isolateScope) {
            $scope.Variables.DeleteEmployeeWithAdmin.update();
            $scope.Variables.svDeleteAdminRole.setInput({
                'user': parseInt($scope.Widgets.gridEmployees.selecteditem.ID)
            });
            $scope.Variables.svDeleteAdminRole.update();
        };

    }
]);

Application.$controller("gridEmployeesController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customButtonAction = function($event) {
            $scope.Variables.stvSelectedRole.dataSet.dataValue = "MunicipalityEmployee";
        };


        $scope.customRow1Action = function($event, $rowData) {
            $scope.Variables.getMunicipalityGroupIdIDs.setInput('userID', $rowData.ID);
            $scope.Variables.getMunicipalityGroupIdIDs.update();
            $scope.Widgets.EmployeeDialog.open();

        };


        // $scope.customRowAction = function($event, $rowData) {
        //     $scope.Variables.FetchRolesForUserWithMunicipality.setInput({
        //         'user': $rowData.ID
        //     });
        //     $scope.Variables.FetchRolesForUserWithMunicipality.update();
        // };

        $scope.customRowAction1 = function($event, $rowData) {
            $scope.Variables.stvDeleteRole.dataSet.role = "MunicipalityEmployee";
            $scope.Variables.stvDeleteRole.dataSet.user = $rowData.ID;
            $scope.Variables.stvDeleteRoleText.dataSet.dataValue = "This action will also revoke all higher privileges that have been granted (Municipality Admin, Inspection roles, and Code Enforcement roles). Would you like to proceed?";
            $scope.Widgets.dialogDeleteRoleConfrim.open();
        };

    }
]);

Application.$controller("gridInspectorManagerController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customButtonAction = function($event) {
            $scope.Variables.stvSelectedRole.dataSet.dataValue = "InspectorManager";
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

Application.$controller("gridCodeOfficerController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customButtonAction = function($event) {
            $scope.Variables.stvSelectedRole.dataSet.dataValue = "CodeOfficer";
        };


        $scope.customRow1Action = function($event, $rowData) {
            $scope.Variables.getMunicipalityGroupIdIDs.setInput('userID', $rowData.ID);
            $scope.Variables.getMunicipalityGroupIdIDs.update();
            $scope.Widgets.EmployeeDialog.open();

        };


        $scope.customRowAction1 = function($event, $rowData) {

            $scope.Variables.stvDeleteRole.dataSet.role = "CodeOfficer";
            $scope.Variables.stvDeleteRole.dataSet.user = $rowData.ID;
            $scope.Variables.stvDeleteRoleText.dataSet.dataValue = "This action will also revoke all higher privileges that have been granted (CodeOfficer). Would you like to proceed?";
            $scope.Widgets.dialogDeleteRoleConfrim.open();
        };

    }
]);

Application.$controller("gridCodeManagerController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.customButtonAction = function($event) {
            $scope.Variables.stvSelectedRole.dataSet.dataValue = "CodeManager";
        };


        $scope.customRow1Action = function($event, $rowData) {
            $scope.Variables.getMunicipalityGroupIdIDs.setInput('userID', $rowData.ID);
            $scope.Variables.getMunicipalityGroupIdIDs.update();
            $scope.Widgets.EmployeeDialog.open();

        };


        // $scope.customRowAction = function($event, $rowData) {
        //     $scope.Variables.FetchRolesForUserWithMunicipality.setInput({
        //         'user': $rowData.ID
        //     });
        //     $scope.Variables.FetchRolesForUserWithMunicipality.update();
        // };
    }
]);

Application.$controller("dialogDeleteRoleConfrimController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.selectedDelete;
        $scope.buttonProceedRoleDeleteClick = function($event, $isolateScope) {
            switch ($scope.Variables.stvDeleteRole.dataSet.role) {
                case "MunicipalityEmployee":
                    // Delete All Municipality Roles
                    $scope.Variables.svDeleteAllMunicipalityRoles.setInput({
                        'UserId': $scope.Variables.stvDeleteRole.dataSet.user
                    });
                    $scope.Variables.svDeleteAllMunicipalityRoles.update();
                    break;
                case "Inspector":
                    //Delete Inspector,InspectorManager Roles
                    $scope.Variables.svDeleteRole.setInput({
                        'role': 'Inspector',
                        'user': $scope.Variables.stvDeleteRole.dataSet.user
                    });
                    $scope.Variables.svDeleteRole.update();
                    $scope.Variables.svDeleteRole.setInput({
                        'role': 'InspectorManager',
                        'user': $scope.Variables.stvDeleteRole.dataSet.user
                    });
                    $scope.Variables.svDeleteRole.update();
                    break;

                case "CodeOfficer":
                    //Delete CodeOfficer,InspectorManager Roles
                    $scope.Variables.svDeleteRole.setInput({
                        'role': 'CodeOfficer',
                        'user': $scope.Variables.stvDeleteRole.dataSet.user
                    });
                    $scope.Variables.svDeleteRole.update();
                    $scope.Variables.svDeleteRole.setInput({
                        'role': 'InspectorManager',
                        'user': $scope.Variables.stvDeleteRole.dataSet.user
                    });
                    $scope.Variables.svDeleteRole.update();
                    break;


            }
        };

    }
]);