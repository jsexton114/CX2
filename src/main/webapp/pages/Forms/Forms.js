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
        $scope.Variables.SendFormMessagesMail.setInput({
            "recipient": $scope.messageMailingList
        });
        $scope.Variables.SendFormMessagesMail.update();
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

    $scope.SendFormMessagesMailonSuccess = function(variable, data) {
        $scope.Variables.PeopleList.dataSet = undefined;
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
        $scope.Variables.svRecordHistory.setInput('Comments', ('Added a ' + $filter('toCurrency')($data.amount, '$', 2) + ' ' + $data.feeType + ' fee.'));
        $scope.Variables.svRecordHistory.update();
    };

    $scope.CurrentFormonSuccess = function(variable, data) {
        if (data[0].formStatuses.allowPayment) {
            $scope.showCannotAddFee = false;
        }
    };


    $scope.svGetFeeIdsOfUserInCartonSuccess = function(variable, data) {
        debugger;
        // let userFees = $scope.Variables.Cx2FeesData.dataSet.data;
        // let cartFees = data.content;
        // let notInCartFees = _(userFees)
        //     .differenceBy(cartFees, 'id')
        //     .map(_.partial(_.pick, _, 'id'))
        //     .value();


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

        $scope.customRowAction = function($event, $rowData) {
            debugger
            let temp = $scope.Variables.loggedInUser.dataSet.roles;
            let allowToCart = false;
            if ($scope.Variables.CurrentForm.dataSet.data[0].formStatuses.allowPayment) {
                allowToCart = true;
            } else {
                $scope.Variables.DontAddToCart.notify();
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

            console.log($scope.docsToUpload);
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
            if ($scope.Widgets.textSearchPeople.datavalue != undefined) {
                var temp = $scope.Widgets.textSearchPeople.datavalue;
                var data = $scope.Variables.PeopleList.dataSet;
                // checking for any people in PeopleList variable, if not add from search 
                if (data.length == 0) {
                    data.push(temp);
                    // clear search after pushing
                    $scope.Widgets.textSearchPeople.datavalue = undefined;
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
                    $scope.Widgets.textSearchPeople.datavalue = undefined;
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