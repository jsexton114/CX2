Application.$controller("_viewInspectionPageController", ["$scope", function($scope) {
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

    $scope.buttonUpdateOutcomeClick = function($event, $isolateScope) {
        $scope.Variables.svSetInspectionOutcome.update();
    };

    $scope.svFieldDataonSuccess = function(variable, data) {};

    $scope.liveformFeesBeforeservicecall = function($event, $operation, $data) {
        $data.inspectionGuid = $scope.Variables.lvInspection.dataSet.data[0].inspectionGuid;
    };

    $scope.liveformFeesSuccess = function($event, $operation, $data) {
        // var comments = null;

        // if ($operation === 'insert') {
        //     comments = ('Added a ' + $filter('toCurrency')($data.amount, '$', 2) + ' ' + $data.feeType + ' fee.');
        // } else if ($operation === 'update') {
        //     comments = ('Comments', ('Updated the ' + $data.feeType + ' fee [' + $filter('toCurrency')($data.amount, '$', 2) + '].'));
        // } else if ($operation === 'delete') {
        //     comments = ('Removed the ' + $data.feeType + ' fee [' + $filter('toCurrency')($data.amount, '$', 2) + '].');
        // }

        // $scope.Variables.svRecordHistory.setInput('Comments', comments);
        // $scope.Variables.svRecordHistory.update();
    };

    $scope.lvDocumentsonSuccess = function(variable, data) {

    };

    $scope.svSetInspectionOutcomeonSuccess = function(variable, data) {
        if ($scope.Widgets.selectOutcome.datavalue.sendEmail) {
            var tempLink = window.location.hostname + "/#/ViewInspection?inspectionGuid=" + $scope.pageParams.inspectionGuid;
            //Sending mail to  CreatedBy
            $scope.Variables.svSendOutcomeUpdate.setInput({
                'formLink': tempLink,
            });
            $scope.Variables.svSendOutcomeUpdate.update();
        }

        $scope.Widgets.textareaNotes.reset();
    };

    $scope.buttonAddMessageClick = function($event, $isolateScope) {
        // Posting Message
        $scope.Variables.PostInspectionMessage.setInput({
            'municipalityMessage': false,
            'message': $scope.Widgets.textAddMessage.datavalue
        });
        $scope.Variables.PostInspectionMessage.insertRecord();
    };

    $scope.PostInspectionMessageonSuccess = function(variable, data) {
        $scope.Widgets.textAddMessage.reset();

        if (!!$scope.Widgets.textInternalAddMessage) {
            $scope.Widgets.textInternalAddMessage.reset();
        }

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
            var tempLink = window.location.hostname + "/#/ViewInspection?inspectionGuid=" + $scope.pageParams.inspectionGuid;
            $scope.Variables.svSendInspectionMessagesMail.setInput({
                'inspectionLink': tempLink,
                'recipient': $scope.messageMailingList,
                'comments': data.message
            });
            $scope.Variables.svSendInspectionMessagesMail.update();
        }
    };

    $scope.buttonAddInternalMessageClick = function($event, $isolateScope) {
        // Posting Message
        $scope.Variables.PostInspectionMessage.setInput({
            'municipalityMessage': true,
            'message': $scope.Widgets.textInternalAddMessage.datavalue
        });
        $scope.Variables.PostInspectionMessage.insertRecord();
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

    $scope.svSendInspectionMessagesMailonSuccess = function(variable, data) {
        $scope.Variables.PeopleList.dataSet = [];
    };

    $scope.textAddMessageKeyup = function($event, $isolateScope) {
        if ($event.which === 13) { // Enter key
            let targetInput = $($event.currentTarget);

            if (targetInput.val() === undefined || !targetInput.val().length) {
                return;
            }

            if (targetInput.attr('name') === 'textInternalAddMessage') {
                $scope.buttonAddInternalMessageClick();
            } else {
                $scope.buttonAddMessageClick();
            }
        }
    };
}]);

var liveformFeesScope = {};

Application.$controller("gridFeesController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.addNewRowAction = function($event) {
            liveformFeesScope.isNewFee = true;
        };

        $scope.updaterowAction = function($event, $rowData) {
            liveformFeesScope.isNewFee = false;
        };

        $scope.itemInCart = function(feeId) {
            let cartItems = $scope.Variables.svCartItemIds.dataSet.content;

            if (!cartItems) {
                return true; // By default, disable add to cart for anything if we don't have the cart data yet.
            }

            return !!_.find(cartItems, {
                feeId: feeId
            });
        };

        $scope.customRowAction = function($event, $rowData) {
            // Add to Cart Functionality
            $scope.Variables.svInsertIntoCart.setInput({
                'FeeId': $rowData.id
            });
            $scope.Variables.svInsertIntoCart.update();
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

Application.$controller("liveformFeesController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        liveformFeesScope = $scope;
    }
]);

Application.$controller("gridLocationController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogParcelController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridDocumentsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.updaterowAction = function($event, $rowData) {
            window.open('resources/leadTools/index.html?docId=' + $rowData.id);
        };
    }
]);

Application.$controller("gridInspectionHistoryController", ["$scope",
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
            if ($scope.Variables.stvTagSelection.dataSet.dataValue == 'employees') {
                temp = $scope.Widgets.textSearchInternalPeople.datavalue;
            } else {
                temp = $scope.Widgets.textSearchPeople.datavalue;
            }
            // Pushing selected users to List(Static Variable)
            if (temp !== "") {
                var data = $scope.Variables.PeopleList.dataSet;
                // checking for any people in PeopleList variable, if not add from search 
                if (!data.length) {
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

Application.$controller("gridViolationsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogViolationController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.docsToUpload = [];

        $scope.dialogUploadDocumentClose = function($event, $isolateScope) {
            $scope.docsToUpload = [];
        };

        $scope.buttonRemovePictureClick = function($event, $isolateScope, item, currentItemWidgets) {
            $scope.Variables.stvInspectionPicturesForUpload.dataSet.splice($scope.Variables.stvInspectionPicturesForUpload.dataSet.indexOf(item), 1);
        };

        $scope.buttonUploadViolationPicturesClick = function($event, $isolateScope) {
            var filesContents = [];

            $scope.docsToUpload.forEach(function(doc, index) {
                let pictureData = {
                    filename: doc.Filename,
                    mimetype: doc.Mimetype,
                    contents: doc.Contents,
                    dataUrl: null
                };

                var reader = new FileReader();

                reader.onload = function(e) {
                    pictureData.dataUrl = e.target.result;
                    $scope.$apply();
                };

                reader.readAsDataURL(doc.Contents);

                $scope.Variables.stvInspectionPicturesForUpload.dataSet.push(pictureData);
            });

            $scope.docsToUpload = [];
            document.getElementById('violationPictureUpload').value = '';
        };

        $scope.dialogViolationClose = function($event, $isolateScope) {
            $scope.Widgets.selectCodeSet.reset();
            $scope.Widgets.textareaViolationNotes.reset();
            $scope.Variables.stvInspectionPicturesForUpload.dataSet = [];
        };

        $scope.buttonSaveViolationClick = function($event, $isolateScope) {
            var pictures = [];

            $scope.Variables.stvInspectionPicturesForUpload.dataSet.forEach(function(pic, index) {
                pictures.push(pic.contents);
            });

            $scope.Variables.svAddViolation.setInput('pictures', pictures);
            $scope.Variables.svAddViolation.update();
            $scope.docsToUpload = [];

            $scope.Widgets.dialogViolation.close();
        };
    }
]);

Application.$controller("dialogViewViolationController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogRemoveViolationController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);