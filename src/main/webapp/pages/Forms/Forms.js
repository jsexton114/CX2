Application.$controller("FormsPageController", ["$scope", "$timeout", function($scope, $timeout) {
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
    };
    $scope.sharedWith;
    $scope.allFormStatus;

    $scope.FormStatusonSuccess = function(variable, data) {
        setFormStatusProgressValue();
    };

    $scope.canAddVendor = function() {
        return (!$scope.Variables.lvFormType.dataSet.data[0].multipleVendors && $scope.Variables.Cx2Vendors2formData.dataSet.data.length >= 1) ? true : false;
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
            $scope.currentProgress = parseInt(!!currentStatus.considerClosed ? 100 : ((currentStatusIndex) / statusListData.length * 100));
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


    $scope.GetProcessGroupMemebersByFormGUIDonSuccess = function(variable, data) {
        var temp = $scope.Variables.loggedInUser.dataSet.roles;
        var isCXAdminMunicipalityAdmin = 0;
        //Checking if user is muniadmin or cxadmin
        for (let i = 0; i < temp.length; i++) {
            if ((temp[i] == "MunicipalityAdmin") || (temp[i] == "CXAdmin")) {
                isCXAdminMunicipalityAdmin = 1;
            }
        }
        //Checking user is in processowner(assignedto) group of that status, if not return -1
        var found = _.findIndex(data.content, {
            'UserId': _.parseInt($scope.Variables.loggedInUser.dataSet.id)
        });

        // If not muniadmin or cxadmin OR not in group then hide panel
        if (!!$scope.Widgets.panelFormReview && !((isCXAdminMunicipalityAdmin == 1) || (found > -1))) {
            $scope.Widgets.panelFormReview.show = false;
        }
    };


    $scope.SharedWithDataonSuccess = function(variable, data) {
        $scope.sharedWith = data;
    };

    $scope.lvFormTypeonSuccess = function(variable, data) {
        currentBreadCrumb.label = data[0].formType;
    };

    $scope.GetWriteAccessGroupMembersByFormGUIDonSuccess = function(variable, data) {
        var temp = $scope.Variables.loggedInUser.dataSet.roles;
        var isCXAdminMunicipalityAdmin = 0;
        //Checking if user is muniadmin or cxadmin
        for (let i = 0; i < temp.length; i++) {
            if ((temp[i] == "MunicipalityAdmin") || (temp[i] == "CXAdmin")) {
                isCXAdminMunicipalityAdmin = 1;
            }
        }
        //Checking user is in writeAccess group of that status, if not return -1
        var found = _.findIndex(data.content, {
            'UserId': _.parseInt($scope.Variables.loggedInUser.dataSet.id)
        });

        // If not muniadmin or cxadmin OR not in group then hide tab
        if (!((isCXAdminMunicipalityAdmin == 1) || (found > -1))) {
            // $scope.Widgets.tabpaneLocation.show = false;
        }
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
        let people = $scope.Variables.PeopleList.dataSet;
        if (people.length == 0) {
            // DO nothing
        } else {
            console.log(variable.dataBinding.PostedAt)
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
        });
        $scope.Variables.PostFormMessage.update();
    };


    $scope.GetMessageIdForCurrentPostonSuccess = function(variable, data) {
        var people = $scope.Variables.PeopleList.dataSet;
        var m = data.content[0];
        // Insert people as Tagged People For RecentMessage
        for (var i = 0; i < people.length; i++) {
            $scope.Variables.InsertTaggedPeople.setInput({
                "taggedPersonId": people[i].id,
                "users": people[i],
                "formMessages": m,
                "formMessageId": data.content[0].id,
            });
            $scope.Variables.InsertTaggedPeople.insertRecord();
        }

    };

    $scope.svSetFormStatusonSuccess = function(variable, data) {
        setFormStatusProgressValue($scope.Widgets.selectStatus._proxyModel.id);

        //Checking to send mail
        if ($scope.Widgets.selectStatus.datavalue.sendEmail) {
            //Sending mail to  CreatedBy
            $scope.Variables.SendStatusUpdate.setInput({
                'username': $scope.Variables.CurrentForm.dataSet.data[0].users.firstName,
                'recipient': $scope.Variables.CurrentForm.dataSet.data[0].users.email
            });
            $scope.Variables.SendStatusUpdate.update();
            //Sending mail to SharedWith
            var contacts = $scope.sharedWith;
            for (let i = 0; i < contacts.length; i++) {
                $scope.Variables.SendStatusUpdate.setInput({
                    'username': contacts[i].usersBySharedWithUser.firstName,
                    'recipient': contacts[i].usersBySharedWithUser.email
                });
                $scope.Variables.SendStatusUpdate.update();
            }
        }

        $scope.Widgets.textareaNotes.reset();
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

Application.$controller("gridLocationController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveformLocationController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogAddGISRecordController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.buttonAddByAddressClick = function($event, $isolateScope) {
            $scope.Variables.AddGIStoForms.setInput({
                'GISRecordId': $scope.Widgets.searchAddress.datavalue.id
            });
            $scope.Variables.AddGIStoForms.update();
        };


        $scope.buttonAddBySubdivisionClick = function($event, $isolateScope) {
            $scope.Variables.AddGIStoForms.setInput({
                'GISRecordId': $scope.Widgets.searchSubdivision.datavalue.id
            });
            $scope.Variables.AddGIStoForms.update();
        };


        $scope.buttonAddByParcelClick = function($event, $isolateScope) {
            $scope.Variables.AddGIStoForms.setInput({
                'GISRecordId': $scope.Widgets.searchParcel.datavalue.id
            });
            $scope.Variables.AddGIStoForms.update();
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
            $scope.Variables.gridRemovePrimaryVendors.setInput({
                'vendor': $scope.selecteditem.relatedFormGuid
            });
            $scope.Variables.gridRemovePrimaryVendors.update();
            $scope.Variables.UpdatePrimaryVendorInMasterForms.setInput({
                'VendorId': $scope.selecteditem.vendorId
            });
            $scope.Variables.UpdatePrimaryVendorInMasterForms.update();
            $scope.Variables.gridSetPrimaryVendorStatusForFormandVendor.setInput({
                'pv': newVal,
                'vendor': $scope.selecteditem.vendorId
            });


        };

        $scope.deleterowAction = function($event, $rowData) {
            if ($rowData.primaryVendor) {
                $scope.Variables.RemoveVendorFromMasterForms.update();
            }
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

        liveformFeesScope.isEditingFee = false;

        $scope.addNewRowAction = function($event) {
            console.log(liveformFeesScope);
            console.log('test-add');
            liveformFeesScope.isEditingFee = false;
        };

        $scope.updaterowAction = function($event, $rowData) {
            console.log(liveformFeesScope);
            console.log('test-edit');
            liveformFeesScope.isEditingFee = true;
        };

    }
]);

Application.$controller("lfFeesController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridFeesController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
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
    }
]);

Application.$controller("dialogUploadDocumentController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.docToUpload = null;

        $scope.dialogUploadDocumentClose = function($event, $isolateScope) {
            $scope.docToUpload = null;
        };

        $scope.buttonUploadFileClick = function($event, $isolateScope) {
            $scope.Variables.svUploadDocument.setInput('files', [$scope.docToUpload.Contents]);
            $scope.Variables.svUploadDocument.update();
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
            if ($scope.Widgets.textSearchPeople.datavalue != undefined) {
                var temp = $scope.Widgets.textSearchPeople.datavalue;
                var data = $scope.Variables.PeopleList.dataSet;
                // checking for any municipalities in PeopleList variable, if not add from search 
                if (data.length == 0) {
                    data.push(temp);
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
            selectedMunicipalites = $scope.Variables.PeopleList.dataSet;
        };

    }
]);

Application.$controller("dialogShowTaggedPeopleController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);