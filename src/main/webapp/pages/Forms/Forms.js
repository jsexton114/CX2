Application.$controller("FormsPageController", ["$scope", "$timeout", "$location", "$filter", function($scope, $timeout, $location, $filter) {
    "use strict";

    var currentBreadCrumb = null;
    var openClosedFormBreadCrumb = {};

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        $scope.today = moment().valueOf();
        var breadCrumbs = $scope.Variables.BreadCrumb.dataSet;
        currentBreadCrumb = breadCrumbs[breadCrumbs.length - 1];
        currentBreadCrumb.link += $scope.pageParams.FormGUID;
        openClosedFormBreadCrumb = $scope.Variables.BreadCrumb.dataSet[1];
        $scope.disableMessageBox = true;
        $scope.showCannotAddFee = true;
    };

    $scope.sharedWith;
    $scope.allFormStatus;

    $scope.FormStatusonSuccess = function(variable, data) {
        setFormStatusProgressValue();
    };

    $scope.canAddVendor = function() {
        return ($scope.Variables.lvFormType.dataSet.data[0].multipleVendors || $scope.Variables.Cx2Vendors2formData.dataSet.data.length == 1);
    };

    $scope.currentProgress = 0;

    function setFormStatusProgressValue(newStatusId) {
        var statusListData = $scope.Variables.FormStatus.dataSet.data;
        var currentStatusId = newStatusId || $scope.Variables.CurrentForm.dataSet.data[0].formStatusId;

        if (!!statusListData && !!currentStatusId) {
            $scope.allFormStatus = statusListData;
            // For showing current Status of form
            var currentStatusIndex = _.findIndex(statusListData, {
                'id': _.parseInt(currentStatusId)
            });

            var currentStatus = statusListData[currentStatusIndex];
            $scope.Variables.stvCurrentStatus.dataSet = currentStatus;
            $scope.currentProgress = parseInt(!!currentStatus.considerClosed ? 100 : ((currentStatusIndex + 1) / statusListData.length * 100));
            $timeout(function() {
                var a = $('.livelist-status li.app-list-item:nth-child(' + (currentStatusIndex + 1) + ')').addClass('active');
            });

            if (currentStatus.considerClosed === true) {
                openClosedFormBreadCrumb.label = 'Closed Forms';
                openClosedFormBreadCrumb.link = '#/UserClosedForms';
            } else {
                openClosedFormBreadCrumb.label = 'Open Forms';
                openClosedFormBreadCrumb.link = '#/UserOpenForms';
            }
        }
    }

    $scope.SharedWithDataonSuccess = function(variable, data) {
        $scope.sharedWith = data;
    };

    $scope.lvFormTypeonSuccess = function(variable, data) {
        currentBreadCrumb.label = data[0].formType;
    };

    $scope.RemoveOtherPrimaryVendorsonSuccess = function(variable, data) {
        $scope.Variables.SetPrimaryVendorStatusForFormandVendor.setInput({
            'vendor': $scope.Widgets.searchVendor.datavalue.vendorId
        });

        $scope.Variables.SetPrimaryVendorStatusForFormandVendor.update();
    };

    $scope.gridRemovePrimaryVendorsonSuccess = function(variable, data) {
        $scope.Variables.gridSetPrimaryVendorStatusForFormandVendor.update();
    };

    $scope.PostFormMessageonSuccess = function(variable, data) {
        $scope.Widgets.textAddMessage.datavalue = undefined;
        $scope.Widgets.textInternalAddMessage.datavalue = undefined;
        let people = $scope.Variables.PeopleList.dataSet;
        if (people.length === 0) {
            // DO nothing
        } else {
            $scope.Variables.GetMessageIdForCurrentPost.setInput({
                'PostedAt': variable.dataBinding.PostedAt,
                'form': $scope.pageParams.FormGUID
            });
            $scope.Variables.GetMessageIdForCurrentPost.update();

        }
    };

    $scope.buttonAddMessageClick = function($event, $isolateScope) {
        // Posting Message
        $scope.Variables.PostFormMessage.setInput({
            'PostedAt': moment().valueOf(),
            'MunicipalityMessage': false,
            'Message': $scope.Widgets.textAddMessage.datavalue
        });
        $scope.Variables.PostFormMessage.update();
    };

    $scope.GetMessageIdForCurrentPostonSuccess = function(variable, data) {
        var people = $scope.Variables.PeopleList.dataSet;
        var m = data.content[0];
        $scope.messageMailingList = '';
        // Insert people as Tagged People For RecentMessage
        for (var i = 0; i < people.length; i++) {
            $scope.Variables.InsertTaggedPeople.setInput({
                "taggedPersonId": people[i].id,
                "users": people[i],
                "formMessages": m,
                "formMessageId": data.content[0].id,
            });
            $scope.Variables.InsertTaggedPeople.insertRecord();

            $scope.messageMailingList = $scope.messageMailingList + people[i].email + ",";

        }
        $scope.messageMailingList = $scope.messageMailingList.substring(0, $scope.messageMailingList.length - 1);

        // Send Mails of Message
        var tempLink = window.location.hostname + "/#/Forms?FormGUID=" + $scope.pageParams.FormGUID
        $scope.Variables.svSendFormMessagesMail.setInput({
            'formLink': tempLink,
            'recipient': $scope.messageMailingList,
            'comments': data.content[0].message
        });
        $scope.Variables.svSendFormMessagesMail.update();
    };

    $scope.svSetFormStatusonSuccess = function(variable, data) {
        setFormStatusProgressValue($scope.Widgets.selectStatus._proxyModel.id);

        //Checking to send mail
        if ($scope.Widgets.selectStatus.datavalue.sendEmail) {

            var tempLink = window.location.hostname + "/#/Forms?FormGUID=" + $scope.pageParams.FormGUID
                //Sending mail to  CreatedBy
            $scope.Variables.svSendStatusUpdate.setInput({
                'formLink': tempLink,
                'username': $scope.Variables.CurrentForm.dataSet.data[0].users.firstName,
                'recipient': $scope.Variables.CurrentForm.dataSet.data[0].users.email
            });
            $scope.Variables.svSendStatusUpdate.update();
            //Sending mail to SharedWith

            var contacts = $scope.sharedWith;
            if ((contacts.length) > 0) {
                //Check if shared with users
                $scope.formStatusMailingList = '';
                for (let i = 0; i < contacts.length; i++) {
                    $scope.formStatusMailingList = $scope.formStatusMailingList + contacts[i].usersBySharedWithUser.email + ",";
                }

                // Send Mail to Shared With Users
                $scope.formStatusMailingList = $scope.formStatusMailingList.substring(0, $scope.formStatusMailingList.length - 1);
                $scope.Variables.svSendStatusUpdate.setInput({
                    'formLink': tempLink,
                    'username': 'User',
                    'recipient': $scope.formStatusMailingList
                });
                $scope.Variables.svSendStatusUpdate.update();
            }
        }

        $scope.Widgets.textareaNotes.reset();
    };



    $scope.svUserPermissionsonSuccess = function(variable, data) {
        if (!data.canView) {
            $location.path("/");
            return;
        }
    };

    $scope.liveformFeesBeforeservicecall = function($event, $operation, $data) {
        $data.formGuid = $scope.Variables.CurrentForm.dataSet.data[0].formGuid;
    };

    $scope.liveformFeesSuccess = function($event, $operation, $data) {
        var comments = null;

        if ($operation === 'insert') {
            comments = ('Added a ' + $filter('toCurrency')($data.amount, '$', 2) + ' ' + $data.feeType + ' fee.');
        } else if ($operation === 'update') {
            comments = ('Comments', ('Updated the ' + $data.feeType + ' fee [' + $filter('toCurrency')($data.amount, '$', 2) + '].'));
        } else if ($operation === 'delete') {
            comments = ('Removed the ' + $data.feeType + ' fee [' + $filter('toCurrency')($data.amount, '$', 2) + '].');
        }

        $scope.Variables.svRecordHistory.setInput('Comments', comments);
        $scope.Variables.svRecordHistory.update();
    };

    $scope.CurrentFormonSuccess = function(variable, data) {
        if (data[0].formStatuses.allowPayment) {
            $scope.showCannotAddFee = false;
        }
    };




    $scope.lvMasterInspectionsInsertonSuccess = function(variable, data) {
        $scope.Variables.svInsertFormsToInspectionsMapping.setInput({
            'RelatedInspectionGUID': data.inspectionGuid,
            'RelatedFormGUID': data.formGuid,
            'AddedBy': $scope.Variables.CurrentUserDetails.dataSet.id
        });
        $scope.Variables.svInsertFormsToInspectionsMapping.update();
    };


    $scope.buttonAddInternalMessageClick = function($event, $isolateScope) {
        // Posting Message
        $scope.Variables.PostFormMessage.setInput({
            'PostedAt': moment().valueOf(),
            'MunicipalityMessage': true,
            'Message': $scope.Widgets.textInternalAddMessage.datavalue
        });
        $scope.Variables.PostFormMessage.update();
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

}]);

Application.$controller("gridSharedwithController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveformSharedwithController", ["$scope",
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
                $scope.Variables.AddGIStoForms.setInput({
                    'GISRecordId': gisRecordId
                });

                $scope.Variables.AddGIStoForms.update();

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

Application.$controller("dlgFormSubmittedController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridFormVendorsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.primaryVendorChange = function(newVal) {
            //UpdatePrimaryVendorStatusInVEndor2Forms
            $scope.Variables.gridRemovePrimaryVendors.setInput({
                'vendor': $scope.selecteditem.relatedFormGuid
            });
            $scope.Variables.gridRemovePrimaryVendors.update();
            if (newVal == true) {
                $scope.Variables.UpdatePrimaryVendorInMasterForms.setInput({
                    'VendorId': $scope.selecteditem.vendorId
                });
                $scope.Variables.UpdatePrimaryVendorInMasterForms.update();
            } else {
                $scope.Variables.UpdatePrimaryVendorInMasterForms.setInput({
                    'VendorId': null
                });
                $scope.Variables.UpdatePrimaryVendorInMasterForms.update();
            }

            $scope.Variables.gridSetPrimaryVendorStatusForFormandVendor.setInput({
                'pv': newVal,
                'vendor': $scope.selecteditem.vendorId
            });
        };
    }
]);



Application.$controller("dialogAddVendorController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.buttonTagVendorClick = function($event, $isolateScope) {
            if ($scope.Widgets.checkboxPrimaryVendor.datavalue == true) {
                $scope.Variables.AddingVendorsToForm.update();
                $scope.Variables.UpdateVendorForMasterForms.update();
                $scope.Variables.RemoveOtherPrimaryVendors.update();

            } else {
                $scope.Variables.AddingVendorsToForm.update();
            }
        };
    }
]);

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
            let temp = $scope.Variables.loggedInUser.dataSet.roles;
            let allowToCart = false;
            if ($scope.Variables.CurrentForm.dataSet.data[0].formStatuses.allowPayment) {
                allowToCart = true;
            } else {
                //Checking if user is muniadmin or cxadmin or muniemp
                for (let i = 0; i < temp.length; i++) {
                    if ((temp[i] == "MunicipalityAdmin") || (temp[i] == "CXAdmin") || (temp[i] == "MunicipalityEmployee")) {
                        allowToCart = true;
                    }
                }
            }

            if (allowToCart) {
                // Add to Cart Functionality
                $scope.Variables.svInsertIntoCart.setInput({
                    'FeeId': $rowData.id
                });
                $scope.Variables.svInsertIntoCart.update();
            }
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

Application.$controller("gridLocationController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogDeleteGISRecordConfController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogVendorDeleteConfController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.buttonDeleteVendorOKClick = function($event, $isolateScope) {
            if ($scope.Widgets.gridFormVendors.selecteditem.primaryVendor) {
                $scope.Variables.RemoveVendorFromMasterForms.update();
            }
        };
    }
]);

Application.$controller("gridFormInspectionsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogInspectionRequestController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;



        $scope.button2InspectionRequestClick = function($event, $isolateScope) {
            if ($scope.Variables.CurrentForm.dataSet.data[0].formTypes.forceInspectionSequence) {
                $scope.Variables.lvMasterInspectionsInsert.setInput({
                    'inspectionDesignId': $scope.Widgets.selectInspectionDesignBySequence.datavalue.inspectionDesignId
                });
                $scope.Variables.lvMasterInspectionsInsert.insertRecord();
            } else {
                $scope.Variables.lvMasterInspectionsInsert.setInput({
                    'inspectionDesignId': $scope.Widgets.selectInspectionDesign.datavalue.id
                });
                $scope.Variables.lvMasterInspectionsInsert.insertRecord();

            }
        };

    }
]);