Application.$controller("EditGISRecordPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        window.refreshAttachments = function() {
            $scope.Variables.Cx2DocumentData.update();
        };
    };


    $scope.liveformGisrecordsBeforeservicecall = function($event, $operation, $data) {
        delete $data.dateModified;
        delete $data.fullAddress;

        $data.modifiedBy = parseInt(Variables.loggedInUser.dataSet.id);
    };


    $scope.buttonAddMessageClick = function($event, $isolateScope) {
        // Posting Message
        $scope.Variables.PostFormMessage.setInput({
            'municipalityMessage': true,
            'message': $scope.Widgets.textAddMessage.datavalue
        });
        $scope.Variables.PostFormMessage.insertRecord();
    };


    $scope.PostFormMessageonSuccess = function(variable, data) {
        $scope.Widgets.textAddMessage.reset();

        let people = $scope.Variables.PeopleList.dataSet;

        if (people.length === 0) {
            // DO nothing
        } else {
            $scope.messageMailingList = '';
            // Insert people as Tagged People For RecentMessage
            for (var i = 0; i < people.length; i++) {
                $scope.Variables.InsertTaggedPeople.setInput({
                    "taggedPersonId": people[i].id,
                    "formMessageId": data.id
                });
                $scope.Variables.InsertTaggedPeople.insertRecord();
                $scope.messageMailingList = $scope.messageMailingList + people[i].email + ",";
            }

            $scope.messageMailingList = $scope.messageMailingList.substring(0, $scope.messageMailingList.length - 1);

            // Send Mails of Message
            var tempLink = window.location.hostname + "/#/EditGISRecord?GISRecordId=" + $scope.pageParams.GISRecordId;
            $scope.Variables.svSendGISMessagesMail.setInput({
                'gisLink': tempLink,
                'recipient': $scope.messageMailingList,
                'comments': data.message
            });
            $scope.Variables.svSendGISMessagesMail.update();
        }
    };


    $scope.anchorViewTaggedPeopleClick = function($event, $isolateScope, item, currentItemWidgets) {
        $scope.Variables.GetTaggedPeopleListByMessage.setFilter({
            'formMessageId': item.id
        });
        $scope.Variables.GetTaggedPeopleListByMessage.update();
        $scope.Widgets.dialogShowTaggedPeople.open();
    };


    $scope.svSendGISMessagesMailonSuccess = function(variable, data) {
        $scope.Variables.PeopleList.dataSet = [];
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

}]);

Application.$controller("gridDocumentsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.updaterowAction = function($event, $rowData) {
            window.open('resources/leadTools/index.html?docId=' + $rowData.id);
        };
    }
]);

Application.$controller("dialogUploadDocumentController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.docsToUpload = [];

        $scope.dialogUploadDocumentClose = function($event, $isolateScope) {
            $scope.docsToUpload = [];
        };

        $scope.buttonUploadFileClick = function($event, $isolateScope) {
            var filesContents = [];

            $scope.docsToUpload.forEach(function(doc, index) {
                filesContents.push(doc.Contents);
            });
            $scope.Variables.svUploadDocument.setInput('files', filesContents);
            $scope.Variables.svUploadDocument.update();
            $scope.docsToUpload = [];
            $scope.Widgets.dialogUploadDocument.close();
        };
    }
]);

Application.$controller("liveformGisrecordsController", ["$scope", "$rootScope",
    function($scope, $rs) {
        "use strict";

        $scope.disable = ($rs.Variables.loggedInUser.dataSet.roles[0] === "MunicipalityEmployee");
    }
]);


Application.$controller("gridGISContactsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveformGISContactsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridOpenFormsController", ["$scope",
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

Application.$controller("gridCasesController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRowAction = function($event, $rowData) {
            $scope.Variables.stvFormLink.dataSet.dataValue = $rowData.formGuid;
            $scope.Widgets.pagedialogViewForm.open();
        };

    }
]);

Application.$controller("gridFormsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRowAction = function($event, $rowData) {
            $scope.Variables.stvFormLink.dataSet.dataValue = $rowData.formGuid;
            $scope.Widgets.pagedialogViewForm.open();
        };

    }
]);

Application.$controller("gridTransactionsController", ["$scope",
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

Application.$controller("dialogGisMapController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("pagedialogViewFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogShowTaggedPeopleController", ["$scope",
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
            var temp = $scope.Widgets.textSearchInternalPeople.datavalue;

            // Pushing selected users to List(Static Variable)
            if (temp !== "") {
                var data = $scope.Variables.PeopleList.dataSet;
                // checking for any people in PeopleList variable, if not add from search 
                if (data.length === 0) {
                    data.push(temp);
                    // clear search after pushing
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