Application.$controller("MyMessagesPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {

    };


    $scope.PostFormMessageonSuccess = function(variable, data) {
        $scope.Widgets.textAddMessage.reset();
        let people = $scope.Variables.PeopleList.dataSet;
        if (people.length === 0) {
            // DO nothing
            $scope.Widgets.dialogReply.close();
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
            switch ($scope.Widgets.gridInbox.selecteditem.sourceCategory) {
                case "Form":

                    var tempLink = window.location.hostname + "/#/Forms?FormGUID=" + $scope.Widgets.gridInbox.selecteditem.sourceGuid;
                    $scope.Variables.svSendFormMessagesMail.setInput({
                        'formLink': tempLink,
                        'recipient': $scope.messageMailingList,
                        'comments': data.message
                    });
                    $scope.Variables.svSendFormMessagesMail.update();
                    break;
                case "Inspection":

                    var tempLink = window.location.hostname + "/#/ViewInspection?inspectionGuid=" + $scope.Widgets.gridInbox.selecteditem.sourceGuid;
                    $scope.Variables.svSendInspectionMessagesMail.setInput({
                        'inspectionLink': tempLink,
                        'recipient': $scope.messageMailingList,
                        'comments': data.message
                    });
                    $scope.Variables.svSendInspectionMessagesMail.update();
                    break;
                case "Project":

                    var tempLink = window.location.hostname + "/#/ViewProject?ProjectGUID=" + $scope.Widgets.gridInbox.selecteditem.sourceGuid
                    $scope.Variables.svSendProjectMessagesMail.setInput({
                        'projectLink': tempLink,
                        'recipient': $scope.messageMailingList,
                        'comments': data.message
                    });
                    $scope.Variables.svSendProjectMessagesMail.update();
                    break;
                case "GISRecord":
                    var tempLink = window.location.hostname + "/#/EditGISRecord?GISRecordId=" + $scope.Widgets.gridInbox.selecteditem.sourceGuid
                    $scope.Variables.svSendGISMessagesMail.setInput({
                        'gisLink': tempLink,
                        'recipient': $scope.messageMailingList,
                        'comments': data.message
                    });
                    $scope.Variables.svSendGISMessagesMail.update();
                    break

            }

            $scope.Widgets.dialogReply.close();


        }

    };


    $scope.svSendFormMessagesMailonSuccess = function(variable, data) {
        $scope.Variables.PeopleList.dataSet = [];
        $scope.Widgets.dialogReply.close();
    };


    $scope.svSendInspectionMessagesMailonSuccess = function(variable, data) {
        $scope.Variables.PeopleList.dataSet = [];
        $scope.Widgets.dialogReply.close();
    };


    $scope.svSendProjectMessagesMailonSuccess = function(variable, data) {
        $scope.Variables.PeopleList.dataSet = [];
        $scope.Widgets.dialogReply.close();
    };



    $scope.svDefaultTaggedUseronSuccess = function(variable, data) {

        $scope.Variables.PeopleList.dataSet.push(data.content[0])
    };




    $scope.selectStatusChange = function($event, $isolateScope, newVal, oldVal) {

        if (newVal == "All") {
            $scope.Variables.svGetUserMessages.setInput({
                'messageStatus': undefined
            });
            $scope.Variables.svGetUserMessages.update();
        } else {
            $scope.Variables.svGetUserMessages.setInput({
                'messageStatus': newVal
            });
            $scope.Variables.svGetUserMessages.update();
        }
    };

}]);




Application.$controller("pagedialogViewFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("pagedialogViewInspectionController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("pagedialogViewProjectController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridSentController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRowAction = function($event, $rowData) {

            switch ($rowData.sourceCategory) {
                case "Form":
                    $scope.Variables.stvSourceGuid.dataSet.dataValue = $rowData.sourceGuid;
                    $scope.Widgets.pagedialogViewForm.open();
                    break;
                case "Inspection":
                    $scope.Variables.stvSourceGuid.dataSet.dataValue = $rowData.sourceGuid;
                    $scope.Widgets.pagedialogViewInspection.open();
                    break;
                case "Project":
                    $scope.Variables.stvSourceGuid.dataSet.dataValue = $rowData.sourceGuid;
                    $scope.Widgets.pagedialogViewProject.open();
                    break;
                case "GISRecord":
                    $scope.Variables.ngViewEditGIS.setData({
                        'GISRecordId': $rowData.sourceGuid
                    });
                    $scope.Variables.ngViewEditGIS.navigate();
                    break;

            }
        };

    }
]);

Application.$controller("dialogReplyController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.buttonAddMessageClick = function($event, $isolateScope) {
            switch ($scope.Variables.stvSelectedInboxMessage.dataSet.dataValue.sourceCategory) {
                case "Form":
                    // Posting Message
                    $scope.Variables.PostFormMessage.setInput({
                        'municipalityMessage': $scope.Widgets.checkboxInternal.datavalue,
                        'message': $scope.Widgets.textAddMessage.datavalue,
                        'relatedFormGuid': $scope.Variables.stvSelectedInboxMessage.dataSet.dataValue.sourceGuid,
                        'relatedInspectionGuid': undefined,
                        'relatedProjectGuid': undefined,
                        'gisrecordId': undefined
                    });
                    $scope.Variables.PostFormMessage.insertRecord();
                    break;
                case "Inspection":
                    // Posting Message
                    $scope.Variables.PostFormMessage.setInput({
                        'municipalityMessage': $scope.Widgets.checkboxInternal.datavalue,
                        'message': $scope.Widgets.textAddMessage.datavalue,
                        'relatedFormGuid': undefined,
                        'relatedInspectionGuid': $scope.Variables.stvSelectedInboxMessage.dataSet.dataValue.sourceGuid,
                        'relatedProjectGuid': undefined,
                        'gisrecordId': undefined
                    });
                    $scope.Variables.PostFormMessage.insertRecord();
                    break;
                case "Project":
                    // Posting Message
                    $scope.Variables.PostFormMessage.setInput({
                        'municipalityMessage': $scope.Widgets.checkboxInternal.datavalue,
                        'message': $scope.Widgets.textAddMessage.datavalue,
                        'relatedFormGuid': undefined,
                        'relatedInspectionGuid': undefined,
                        'relatedProjectGuid': $scope.Variables.stvSelectedInboxMessage.dataSet.dataValue.sourceGuid,
                        'gisrecordId': undefined
                    });
                    $scope.Variables.PostFormMessage.insertRecord();
                    break;
                case "GISRecord":
                    // Posting Message
                    $scope.Variables.PostFormMessage.setInput({
                        'municipalityMessage': $scope.Widgets.checkboxInternal.datavalue,
                        'message': $scope.Widgets.textAddMessage.datavalue,
                        'relatedFormGuid': undefined,
                        'relatedInspectionGuid': undefined,
                        'relatedProjectGuid': undefined,
                        'gisrecordId': $scope.Variables.stvSelectedInboxMessage.dataSet.dataValue.sourceGuid
                    });
                    $scope.Variables.PostFormMessage.insertRecord();
                    break;

            }


        };


        $scope.textAddMessageKeyup = function($event, $isolateScope) {
            if ($event.which === 13) { // Enter key
                let targetInput = $($event.currentTarget);

                if (targetInput.val() === undefined || !targetInput.val().length) {
                    return;
                }

                $scope.buttonAddMessageClick();
            }
        };

    }
]);

Application.$controller("gridCurrentMessageController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogTagPeopleController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        var selectedPeople = [];
        $scope.ButtonTagPeopleClick = function($event, $isolateScope) {
            var temp;
            if ($scope.Variables.stvTagSelection.dataSet.dataValue == true) {
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

Application.$controller("gridInboxController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.updaterowAction = function($event, $rowData) {
            let messagesList = $scope.Variables.svGetUserMessages.dataSet.content
            _.forEach($scope.Variables.svGetUserMessages.dataSet.content, function(obj) {
                if ($rowData.id == obj.id) {
                    obj.messageRead = true;
                }
            });
            //$rowData.messageRead = true;
        };


        $scope.deleterowAction = function($event, $rowData) {

            switch ($rowData.sourceCategory) {
                case "Form":
                    $scope.Variables.stvSourceGuid.dataSet.dataValue = $rowData.sourceGuid;
                    $scope.Widgets.pagedialogViewForm.open();
                    break;
                case "Inspection":
                    $scope.Variables.stvSourceGuid.dataSet.dataValue = $rowData.sourceGuid;
                    $scope.Widgets.pagedialogViewInspection.open();
                    break;
                case "Project":
                    $scope.Variables.stvSourceGuid.dataSet.dataValue = $rowData.sourceGuid;
                    $scope.Widgets.pagedialogViewProject.open();
                    break;
                case "GISRecord":
                    $scope.Variables.ngViewEditGIS.setData({
                        'GISRecordId': $rowData.sourceGuid
                    });
                    $scope.Variables.ngViewEditGIS.navigate();
                    break;


            }

        };


        $scope.customRowAction = function($event, $rowData) {
            let messagesList = $scope.Variables.svGetUserMessages.dataSet.content
            _.forEach($scope.Variables.svGetUserMessages.dataSet.content, function(obj) {
                if ($rowData.id == obj.id) {
                    obj.messageRead = true;
                }
            });
        };

    }
]);