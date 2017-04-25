Application.$controller("_viewProjectPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    var crum;
    $scope.onPageReady = function() {
        $scope.today = moment().valueOf();
        //var temp = $scope.Variables.BreadCrumb.dataSet;
        $scope.Widgets.gridProjectMembers.addMember = false;
    };


    $scope.CurrentProjectonSuccess = function(variable, data) {

        if ($scope.Variables.loggedInUser.dataSet.id == data[0].usersByCreatedBy.id) {
            $scope.Widgets.gridProjectMembers.addMember = true;
        }
        var temp = $scope.Variables.loggedInUser.dataSet.roles;
        //Checking if user is muniadmin or cxadmin
        for (let i = 0; i < temp.length; i++) {
            if ((temp[i] == "MunicipalityAdmin") || (temp[i] == "CXAdmin")) {
                $scope.Widgets.gridProjectMembers.addMember = true;
            }
        }
    };


    $scope.UpdateProjectDescriptiononSuccess = function(variable, data) {
        $scope.today = moment().valueOf();
        $scope.Variables.SetModifiedDateForProject.setInput({
            'DateModified': $scope.today
        });
        $scope.Variables.SetModifiedDateForProject.update();
    };


    $scope.UpdateProjectDetailsonSuccess = function(variable, data) {
        $scope.today = moment().valueOf();
        $scope.Variables.SetModifiedDateForProject.setInput({
            'DateModified': $scope.today
        });
        $scope.Variables.SetModifiedDateForProject.update();
    };


    $scope.buttonAddMessageClick = function($event, $isolateScope) {
        // Posting Message
        $scope.Variables.PostProjectMessage.setInput({
            'municipalityMessage': false,
            'message': $scope.Widgets.textAddMessage.datavalue
        });
        $scope.Variables.PostProjectMessage.insertRecord();
    };

    $scope.PostProjectMessageonSuccess = function(variable, data) {
        $scope.Widgets.textAddMessage.datavalue = undefined;
        $scope.Widgets.textInternalAddMessage.datavalue = undefined;
        let people = $scope.Variables.PeopleList.dataSet;
        if (people.length === 0) {
            // DO nothing
        } else {
            $scope.messageMailingList = '';
            // Insert people as Tagged People For RecentMessage
            for (var i = 0; i < people.length; i++) {
                $scope.Variables.InsertTaggedPeople.setInput({
                    "taggedPersonId": people[i].id,
                    "formMessageId": data.id,
                });
                $scope.Variables.InsertTaggedPeople.insertRecord();

                $scope.messageMailingList = $scope.messageMailingList + people[i].email + ",";

            }
            $scope.messageMailingList = $scope.messageMailingList.substring(0, $scope.messageMailingList.length - 1);

            // Send Mails of Message
            var tempLink = window.location.hostname + "/#/ViewProject?ProjectGUID=" + $scope.pageParams.ProjectGUID
            $scope.Variables.svSendProjectMessagesMail.setInput({
                'projectLink': tempLink,
                'recipient': $scope.messageMailingList,
                'comments': data.message
            });
            $scope.Variables.svSendProjectMessagesMail.update();

        }
    };


    $scope.buttonUpdateDetailsClick = function($event, $isolateScope) {
        if ($scope.Widgets.datetimeEstimatedStartDate.timestamp == '') {
            $scope.Variables.UpdateProjectDetails.setInput({
                'EstStartDate': 0
            });
        }
        if ($scope.Widgets.datetimeEstimatedCompletionDat.timestamp == '') {
            $scope.Variables.UpdateProjectDetails.setInput({
                'EstEndDate': 0
            });
        }
        $scope.Variables.UpdateProjectDetails.update();
    };


    $scope.InsertTaskonSuccess = function(variable, data) {
        if ($scope.Widgets.selectProjectAssignedTo.datavalue != undefined) {
            var user = $scope.Widgets.selectProjectAssignedTo.datavalue.firstName + ' ' + $scope.Widgets.selectProjectAssignedTo.datavalue.lastName;
            var tempLink = window.location.hostname + "/#/ViewProject?ProjectGUID=" + $scope.pageParams.FormGUID
            $scope.Variables.svAssignedTaskMail.setInput({
                'projectLink': tempLink,
                'username': user,
                'recipient': $scope.Widgets.selectProjectAssignedTo.datavalue.email
            });
            $scope.Variables.svAssignedTaskMail.update();
        }
    };


    $scope.UpdateTaskonSuccess = function(variable, data) {
        if ($scope.Widgets.selectUpdateProjectAssignedTo.datavalue != undefined) {
            if ($scope.Widgets.gridTasks.selecteditem.usersByAssignedTo.id != $scope.Widgets.selectUpdateProjectAssignedTo.datavalue.id) {
                var user = $scope.Widgets.selectUpdateProjectAssignedTo.datavalue.firstName + ' ' + $scope.Widgets.selectUpdateProjectAssignedTo.datavalue.lastName;
                var tempLink = window.location.hostname + "/#/ViewProject?ProjectGUID=" + $scope.pageParams.FormGUID
                $scope.Variables.svAssignedTaskMail.setInput({
                    'projectLink': tempLink,
                    'username': user,
                    'recipient': $scope.Widgets.selectUpdateProjectAssignedTo.datavalue.email
                });
                $scope.Variables.svAssignedTaskMail.update();

            }
        }
    };

    $scope.buttonAddInternalMessageClick = function($event, $isolateScope) {
        // Posting Message
        $scope.Variables.PostProjectMessage.setInput({
            'municipalityMessage': true,
            'message': $scope.Widgets.textInternalAddMessage.datavalue
        });
        $scope.Variables.PostProjectMessage.insertRecord();
    };


    $scope.anchorViewTaggedPeopleClick = function($event, $isolateScope, item, currentItemWidgets) {
        $scope.Variables.GetTaggedPeopleListByMessage.setFilter({
            'formMessageId': item.id
        });
        $scope.Variables.GetTaggedPeopleListByMessage.update();

    };


    $scope.GetTaggedPeopleListByMessageonSuccess = function(variable, data) {
        $scope.Widgets.dialogShowTaggedPeople.open();
    };


    $scope.anchorViewInternalTaggedPeopleClick = function($event, $isolateScope, item, currentItemWidgets) {
        $scope.Variables.GetTaggedPeopleListByMessage.setFilter({
            'formMessageId': item.id
        });
        $scope.Variables.GetTaggedPeopleListByMessage.update();
    };

    $scope.buttonTagPeopleClick = function($event, $isolateScope) {
        $scope.Variables.stvTagSelection.dataSet.dataValue = 'users';
        $scope.Widgets.dialogTagPeople.open();
    };


    $scope.buttonInternalTagPeopleClick = function($event, $isolateScope) {
        $scope.Variables.stvTagSelection.dataSet.dataValue = 'employees';
        $scope.Widgets.dialogTagPeople.open();
    };


    $scope.tabpaneMessagesSelect = function($event, $isolateScope) {
        $scope.Variables.PeopleList.dataSet = [];
    };

    $scope.tabpaneIntenalMessagesSelect = function($event, $isolateScope) {
        $scope.Variables.PeopleList.dataSet = [];
    };


    $scope.svSendFormMessagesMailonSuccess = function(variable, data) {
        $scope.Variables.PeopleList.dataSet = [];
    };



    $scope.svSendProjectMessagesMailonSuccess = function(variable, data) {
        $scope.Variables.PeopleList.dataSet = [];
    };


    $scope.lvVendorsToProjectsonSuccess = function(variable, data) {

        if (data.length > 0) {
            $scope.Variables.stvDefaultPrimaryVendor.dataSet.dataValue = false
        } else {
            $scope.Variables.stvDefaultPrimaryVendor.dataSet.dataValue = true
        }
    };

}]);


Application.$controller("dialogAddMemberController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.today = moment().valueOf();
    }
]);

Application.$controller("dialogAddGISRecordController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.cantAddLocation = function() {
            var activeTabIndex = $scope.Widgets.tabsAddGISRecord.activeTabIndex;
            if (activeTabIndex === 0) {
                return (!$scope.Widgets.searchAddress.datavalue.id);
            } else if (activeTabIndex === 1) {
                return (!$scope.Widgets.searchSubdivision.datavalue.id);
            } else {
                return (!$scope.Widgets.searchParcel.datavalue.id);
            }
        };

        $scope.buttonAddLocationClick = function($event, $isolateScope) {
            var activeTabIndex = $scope.Widgets.tabsAddGISRecord.activeTabIndex;
            var gisRecordId = null;
            if (activeTabIndex === 0) {
                gisRecordId = $scope.Widgets.searchAddress.datavalue.id;
            } else if (activeTabIndex === 1) {
                gisRecordId = $scope.Widgets.searchSubdivision.datavalue.id;
            } else {
                gisRecordId = $scope.Widgets.searchParcel.datavalue.id;
            }

            if (gisRecordId !== null && gisRecordId !== undefined) {
                $scope.Variables.AddGIStoProjects.setInput({
                    'GISRecordId': gisRecordId
                });

                $scope.Variables.AddGIStoProjects.update();

                $scope.Widgets.dialogAddGISRecord.close();
            }
        };

    }
]);

Application.$controller("dialogParcelController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);



Application.$controller("dialogAddFormsToProjectController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridLocationController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridTasksController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogAddtaskController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.buttonTaskCreateClick = function($event, $isolateScope) {
            $scope.Variables.InsertTask.setInput({
                'assignedTo': $scope.Widgets.selectProjectAssignedTo.datavalue.id
            });
            $scope.Variables.InsertTask.insertRecord();

        };

    }
]);

Application.$controller("dialogUpdateTaskController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.buttonTaskUpdateClick = function($event, $isolateScope) {

            $scope.Variables.UpdateTask.setInput({
                'assignedTo': $scope.Widgets.selectUpdateProjectAssignedTo.datavalue.id
            });
            $scope.Variables.UpdateTask.updateRecord();

        };

    }
]);


Application.$controller("dialogDeleteGISRecordConfController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogEditProjectDescriptionController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.buttonUpdateProjectDescriptionClick = function($event, $isolateScope) {
            $scope.Variables.UpdateProjectDescription.update();
        };

    }
]);

Application.$controller("dialogTagPeopleController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        var selectedPeople = [];
        $scope.ButtonTagPeopleClick = function($event, $isolateScope) {
            var temp;
            if ($scope.Variables.stvTagSelection.dataSet.dataValue == 'employees') {
                temp = $scope.Widgets.textSearchInternalPeople.datavalue;
            } else {
                temp = $scope.Widgets.textSearchPeople.datavalue;
            }
            // Pushing selected users to List(Static Variable)
            if (temp != "") {
                var data = $scope.Variables.PeopleList.dataSet;
                // checking for any people in PeopleList variable, if not add from search 
                if (data.length == 0) {
                    data.push(temp);
                    // clear search after pushing
                    $scope.Widgets.textSearchPeople.datavalue = "";
                    $scope.Widgets.textSearchInternalPeople.datavalue = "";
                } else {
                    // checking if adding value already exist in PeopleList variable 
                    var exist = 0;
                    for (let i = 0; i < data.length; i++) {
                        if (temp.id == data[i].id)
                            exist = 1;
                    }
                    // If already added then notify user else push to PeopleList variable
                    if (exist == 1)
                        $scope.Variables.PersonAlreadyTagged.notify();
                    else
                        data.push(temp);
                    // clear search after pushing
                    $scope.Widgets.textSearchPeople.datavalue = "";
                    $scope.Widgets.textSearchInternalPeople.datavalue = "";
                }
                // Setting for adding to Tagging
                selectedPeople = $scope.Variables.PeopleList.dataSet;
            } else {
                $scope.Variables.NoPersonAdded.notify();
            }
        };


        $scope.buttonRemoveClick = function($event, $isolateScope, item, currentItemWidgets) {

            // Removing the deleted People
            _.remove($scope.Variables.PeopleList.dataSet, {
                id: item.id
            });
            // Setting for adding to subscriptions
            selectedPeople = $scope.Variables.PeopleList.dataSet;
        };
    }
]);

Application.$controller("dialogShowTaggedPeopleController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridProjectMembersController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridProjectFormsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridProjectVendorsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.primaryVendorChange = function(newVal) {
            $scope.Variables.svUpdateVendorsToProject.setInput({
                'vendor': $scope.selecteditem.vendorId,
                'PrimaryVendor': newVal
            });
            $scope.Variables.svUpdateVendorsToProject.update();
        }
    }
]);

Application.$controller("dialogAddVendorController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;


    }
]);

Application.$controller("confirmdialogDeleteVendorConfrimController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogVendorDetailsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);