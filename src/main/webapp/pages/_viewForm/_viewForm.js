Application.$controller("_viewFormPageController", ["$scope", "$timeout", "$filter", function($scope, $timeout, $filter) {
    "use strict";

    var currentBreadCrumb = null;
    var openClosedFormBreadCrumb = {};

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {


        $scope.today = moment().valueOf();
        // var breadCrumbs = $scope.pageParams.breadCrumbs;
        // currentBreadCrumb = breadCrumbs[breadCrumbs.length - 1];
        // currentBreadCrumb.link += $scope.pageParams.FormGUID;
        // openClosedFormBreadCrumb = breadCrumbs[1];
        $scope.expiresOn = null;
        $scope.isExpired = false;

        window.refreshAttachments = function() {
            $scope.Variables.Cx2DocumentData.update();
        };
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
        $scope.currentStatusId = currentStatusId;

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

            // if (currentStatus.considerClosed === true) {
            //     openClosedFormBreadCrumb.label = 'Closed Forms';
            //     openClosedFormBreadCrumb.link = '#/UserClosedForms';
            // } else {
            //     openClosedFormBreadCrumb.label = 'Open Forms';
            //     openClosedFormBreadCrumb.link = '#/UserOpenForms';
            // }
        }
    }




    $scope.SharedWithDataonSuccess = function(variable, data) {
        $scope.sharedWith = data;
    };

    $scope.lvFormTypeonSuccess = function(variable, data) {
        // currentBreadCrumb.label = data[0].formType;
    };

    $scope.RemoveOtherPrimaryVendorsonSuccess = function(variable, data) {
        $scope.Variables.SetPrimaryVendorStatusForFormandVendor.setInput({
            'vendor': $scope.Widgets.searchVendor.datavalue.id
        });

        $scope.Variables.SetPrimaryVendorStatusForFormandVendor.update();
    };

    $scope.gridRemovePrimaryVendorsonSuccess = function(variable, data) {
        $scope.Variables.gridSetPrimaryVendorStatusForFormandVendor.update();
    };

    $scope.buttonAddMessageClick = function($event, $isolateScope) {
        // Posting Message
        $scope.Variables.PostFormMessage.setInput({
            'municipalityMessage': false,
            'message': $scope.Widgets.textAddMessage.datavalue
        });
        $scope.Variables.PostFormMessage.insertRecord();
    };

    $scope.PostFormMessageonSuccess = function(variable, data) {
        $scope.Widgets.textAddMessage.reset();

        if (!!$scope.Widgets.textInternalAddMessage) {
            $scope.Widgets.textInternalAddMessage.reset();
        }

        let people = $scope.Variables.PeopleList.dataSet;

        //Checking if createdBy is tagged with message, if not tagging him
        if ($scope.Variables.lvFormOwner.dataSet.data.length > 0) {
            let owner = $scope.Variables.lvFormOwner.dataSet.data[0];
            let ownerFound = _.find(people, ['id', owner.id]);
            if (ownerFound == undefined) {
                people.push(owner);
            }
        }


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
            var tempLink = window.location.hostname + "/#/Forms?FormGUID=" + $scope.pageParams.FormGUID;
            $scope.Variables.svSendFormMessagesMail.setInput({
                'formLink': tempLink,
                'recipient': $scope.messageMailingList,
                'comments': data.message
            });
            $scope.Variables.svSendFormMessagesMail.update();
        }
    };

    $scope.svSetFormStatusonSuccess = function(variable, data) {
        setFormStatusProgressValue($scope.Widgets.selectStatus._proxyModel.id);
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
        var currentForm = data[0];
        var formType = currentForm.formTypes;
        $scope.expirationType = formType.expirationType;
        $scope.currentStatusId = currentForm.formStatusId;

        if (!!$scope.expirationType) {
            $scope.expirationDays = formType.expirationDays;

            if (formType.expirationType === 'FormStatusId') { // Status
                $scope.expirationStatusId = formType.expirationStatusId; // See FormStatusonSuccess for more details
            } else { // Dates
                $scope.expirationStatusId = null;
                var fieldName = (formType.expirationType.charAt(0).toUpperCase() + formType.expirationType.substring(1));

                $scope.expiresOn = moment(currentForm[fieldName]).startOf('day').add(formType.expirationDays, 'days').valueOf();

                $scope.isExpired = (moment().valueOf() >= $scope.expiresOn);
            }
        } else {
            $scope.expirationDays = null;
            $scope.expirationStatusId = null;
            $scope.expiresOn = null;
            $scope.isExpired = false;
        }

        $scope.Variables.svDateStatusEntered.update();
    };

    $scope.buttonAddInternalMessageClick = function($event, $isolateScope) {
        // Posting Message
        $scope.Variables.PostFormMessage.setInput({
            'municipalityMessage': true,
            'message': $scope.Widgets.textInternalAddMessage.datavalue
        });
        $scope.Variables.PostFormMessage.insertRecord();
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

        $scope.Variables.PeopleList.dataSet = _.clone($scope.Variables.stvProcessOwners.dataSet);
    };

    $scope.tabpaneIntenalMessagesSelect = function($event, $isolateScope) {
        $scope.Variables.PeopleList.dataSet = _.clone($scope.Variables.stvProcessOwners.dataSet);
    };

    $scope.svSendFormMessagesMailonSuccess = function(variable, data) {
        $scope.Variables.PeopleList.dataSet = _.clone($scope.Variables.stvProcessOwners.dataSet);
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

    $scope.svDateStatusEnteredonSuccess = function(variable, data) {
        if (!data.content.length) {
            return;
        }

        var momentStatusEntered = moment(data.content[0].createdTime);

        if (!!$scope.expirationStatusId && $scope.expirationStatusId === $scope.currentStatusId) {
            $scope.expiresOn = momentStatusEntered.startOf('day').add($scope.expirationDays, 'days').valueOf();
            $scope.isExpired = (moment().valueOf() >= $scope.expiresOn);
        } else if (!!$scope.expirationStatusId) {
            $scope.expiresOn = null;
            $scope.isExpired = false;
        }
    };

    $scope.lvHolidaysonSuccess = function(variable, data) {
        $scope.holidayDateList = [];

        data.forEach(function(holidayData, index) {
            $scope.holidayDateList.push(holidayData.date);
        });
    };

    $scope.Gis2formsDataonSuccess = function(variable, data) {
        var isHostile = false;
        if (data.content.length > 0) {
            data.content.some(function(obj, index) {
                if (obj.gisrecords.isHostile) {
                    isHostile = true;
                    return true;
                }
            });
        }

        $scope.Widgets.labelHostile.show = isHostile;
    };

    $scope.buttonUpdateStatusClick = function($event, $isolateScope) {
        $scope.Variables.svSetFormStatus.setInput('formLink', window.location.hostname + "/#/Forms?FormGUID=" + $scope.pageParams.FormGUID);
        $scope.Variables.svSetFormStatus.update();
    };

    $scope.svProcessOwnersOfFormonSuccess = function(variable, data) {
        $scope.Variables.stvProcessOwners.dataSet = _.clone(data.content);
        $scope.Variables.PeopleList.dataSet = _.clone(data.content)
    };


    $scope.svScheduleInspectiononSuccess = function(variable, data) {
        //fetching outcomes of design
        $scope.Variables.svOutcomeByDesign.setInput({
            inspectionDesign: $scope.Variables.stvSelectedInspectionDesign.dataSet.id,
        });
        $scope.Variables.svOutcomeByDesign.update();

    };


    $scope.svOutcomeByDesignonSuccess = function(variable, data) {
        debugger
        if (data.content[0].sendEmail) {

        }
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
            if (newVal === true) {
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
            if ($scope.Widgets.checkboxPrimaryVendor.datavalue === true) {
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

        $scope.customButtonAction = function($event) {
            let feeData = $scope.Widgets.gridFees.gridData;
            //let feeData = $scope.Variables.svfetchUnpaidFeesOfFormsForCreatedByAndSharedWith.dataSet.content;
            let targetList = [];
            _.forEach(feeData, function(obj) {
                if ((!($scope.itemInCart(obj.id))) && (obj.paidStatus == 'Unpaid')) {
                    targetList.push({
                        'feeId': obj.id
                    });
                }
            });

            let itemSet = {
                'content': targetList
            };
            let temp = JSON.stringify(itemSet);
            $scope.Variables.svInsertAllFeeToCart.setInput({
                'feeListString': temp,
                'userId': $scope.Variables.CurrentUserDetails.dataSet.id
            });

            $scope.Variables.svInsertAllFeeToCart.update();
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
            if (temp !== "") {
                var data = $scope.Variables.PeopleList.dataSet;
                // checking for any people in PeopleList variable, if not add from search 
                if (data.length === 0) {
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

        $scope.inspectionObject = null;
        $scope.minDaysForInspection = moment().startOf('day').valueOf();
        $scope.maxDaysForInspection = moment().startOf('day').add(15, 'years').valueOf();

        $scope.cannotAddInspection = function() {
            return $scope.$eval("!inspectionObject || (inspectionObject.scheduleDateAndTime ? !Widgets.datetimeInspectionRequest.datavalue : !Widgets.dateInspectionRequest.datavalue) || (isSameDayRequest() && inspectionObject.sameDayInspectionFee > 0 && !Widgets.checkboxSameDayFeeConfirm.datavalue) || tooManyInspectionsForHour || tooManyInspectionsForDay || ((Variables.CurrentForm.dataSet.data[$i].balanceDue | stringToNumber) > 0 && inspectionObject.requireFeesPaidBeforeScheduling)");
        };

        function getRequestedFor() {
            if (!$scope.inspectionObject) {
                return null;
            }

            return $scope.inspectionObject.scheduleDateAndTime === true ? $scope.Widgets.datetimeInspectionRequest.datavalue : $scope.Widgets.dateInspectionRequest.datavalue;
        }

        $scope.hasOperatingTimeError = function() {
            if (!getRequestedFor()) {
                return false;
            }

            var requestedMoment = moment(getRequestedFor());

            var closeMoment = moment($scope.Variables.lvMunicipality.dataSet.data[0].closeTime, 'HH:mm:ss');
            var openMoment = moment($scope.Variables.lvMunicipality.dataSet.data[0].openTime, 'HH:mm:ss');
            var requestedTimeMoment = moment(requestedMoment.format('HH:mm:ss'));

            return (requestedTimeMoment.isBefore(openMoment) || requestedTimeMoment.isAfter(closeMoment));
        };

        $scope.isSameDayRequest = function() {
            return moment().startOf('day').diff(moment(getRequestedFor()).startOf('day'), 'days') === 0;
        };

        function updateMinMaxDates(inspectionDesignData) {
            $scope.Widgets.dateInspectionRequest.reset();

            if (!inspectionDesignData) {
                return;
            }

            $scope.minDaysForInspection = moment().startOf('day').add(inspectionDesignData.allowSameDayInspections ? 0 : 1, 'days').valueOf();

            if (inspectionDesignData.maxDaysInAdvance > 0) {
                $scope.maxDaysForInspection = moment().startOf('day').add(inspectionDesignData.maxDaysInAdvance, 'days').valueOf();
            } else {
                $scope.maxDaysForInspection = moment().startOf('day').add(15, 'years').valueOf();
            }
        }

        $scope.button2InspectionRequestClick = function($event, $isolateScope) {

            // Scheduling Inspections
            $scope.Variables.svScheduleInspection.setInput({
                inspectionDesignId: $scope.inspectionObject.id,
                requestedFor: getRequestedFor()
            });
            $scope.Variables.svScheduleInspection.update();

        };

        $scope.selectInspectionDesignChange = function($event, $isolateScope, newVal, oldVal) {
            $scope.Variables.stvSelectedInspectionDesign.dataSet = newVal;
            $scope.inspectionObject = newVal;
            updateMinMaxDates(newVal);
        };

        $scope.selectInspectionDesignBySequenceChange = function($event, $isolateScope, newVal, oldVal) {
            $scope.inspectionObject = !!newVal ? newVal.inspectionDesign : null;
            $scope.Variables.stvSelectedInspectionDesign.dataSet = $scope.inspectionObject;
            updateMinMaxDates(newVal);
        };

        function updateDailyInspectionLimit(selectedDate) {
            $scope.Variables.svCountInspectionsDaily.setInput({
                inspectionDesignId: $scope.inspectionObject.id,
                startOfDay: moment(selectedDate).startOf('day').valueOf(),
                endOfDay: moment(selectedDate).endOf('day').valueOf()
            });

            $scope.Variables.svCountInspectionsDaily.update();
        }

        $scope.dateInspectionRequestChange = function($event, $isolateScope, newVal, oldVal) {
            updateDailyInspectionLimit(newVal);
        };

        $scope.$watch(function() {
            return $scope.Variables.svCountInspectionsHourly.dataSet;
        }, function(newValue, oldValue) {
            if (!!newValue.inspectionCount && !!$scope.inspectionObject) {
                $scope.tooManyInspectionsForHour = newValue.inspectionCount >= $scope.inspectionObject.totalInspectionsHourly;
            }
        });

        $scope.$watch(function() {
            return $scope.Variables.svCountInspectionsDaily.dataSet;
        }, function(newValue, oldValue) {
            if (!!newValue.inspectionCount && !!$scope.inspectionObject) {
                $scope.tooManyInspectionsForDay = newValue.inspectionCount >= $scope.inspectionObject.totalInspectionsDaily;
            }
        });

        $scope.datetimeInspectionRequestChange = function($event, $isolateScope, newVal, oldVal) {
            updateDailyInspectionLimit(newVal);

            $scope.Variables.svCountInspectionsHourly.setInput({
                inspectionDesignId: $scope.inspectionObject.id,
                startOfDay: moment(newVal).startOf('hour').valueOf(),
                endOfDay: moment(newVal).endOf('hour').valueOf()
            });

            $scope.Variables.svCountInspectionsHourly.update();
        };
    }
]);

Application.$controller("dialogVendorDetailsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogViewInspectionController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridLettersController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRow1Action = function($event, $rowData) {
            $scope.Variables.svSendLetter.setInput("formLink", window.location.hostname + "/#/Forms?FormGUID=" + $scope.pageParams.FormGUID);
            $scope.Variables.svSendLetter.setInput("letterTemplateId", $rowData.id);
            $scope.Variables.svSendLetter.update();
        };
    }
]);

Application.$controller("dialog1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogOwnerDetailsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);