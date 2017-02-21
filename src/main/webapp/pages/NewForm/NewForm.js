Application.$controller("NewFormPageController", ["$scope", "$location", "$timeout", function($scope, $location, $timeout) {
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

        $scope.Variables.stvContacts.dataSet = [];
        $scope.Variables.stvDocuments.dataSet = [];
        $scope.Variables.stvGisData.dataSet = [];
        $scope.Variables.stvVendors.dataSet = [];
    };

    $scope.loaded = false;

    var itemsLoaded = 0;
    var totalItemsToLoad = 2;

    var userRoles = null;
    var municipalityId = null;
    var internalForm = null;

    // Variables to control which steps should be available
    $scope.submitOnBehalf = false;
    $scope.gisRecords = false;
    $scope.vendorInfo = false;
    $scope.ownerInfo = false;
    $scope.documents = false;
    $scope.sharing = false;

    $scope.formData = {};
    $scope.locationIds = '';
    $scope.vendorIds = '';
    $scope.sharedUserString = '';

    function iterateLoading() {
        itemsLoaded += 1;

        if (itemsLoaded == totalItemsToLoad) {
            $scope.loaded = true;
        }
    }

    $scope.svGetUserRolesonSuccess = function(variable, data) {
        userRoles = data.content;
        shouldSubmitOnBehalf();
    };

    $scope.getOwnerGisRecordIds = function() {
        var ownerGisRecordIds = [];
        $scope.Variables.stvGisData.dataSet.forEach(function(gisRecord, index) {
            ownerGisRecordIds.push(gisRecord.ID);
        });

        return ownerGisRecordIds.length > 0 ? ownerGisRecordIds : undefined;
    };


    $scope.lvFormTypeonSuccess = function(variable, data) {
        var formType = data[0];
        municipalityId = formType.municipalityId;
        internalForm = formType.municipalityInternalForm;
        $scope.Variables.stvBreadCrumbs.dataSet[1].label += (': ' + data[0].formType);
        $scope.gisRecords = formType.gisrecord;
        $scope.vendorInfo = formType.vendorSelection;
        $scope.ownerInfo = formType.requireOwner && formType.gisrecord;
        $scope.documents = formType.attachments;
        $scope.sharing = formType.sharedWith;

        shouldSubmitOnBehalf();
    };

    function shouldSubmitOnBehalf() {
        if (!userRoles || municipalityId === false || internalForm === null) {
            return;
        }

        if (internalForm === false) {
            userRoles.some(function(role, index) {
                if ((role.RoleName === 'CXAdmin') || ((role.RoleName === 'MunicipalityAdmin' || role.RoleName === 'MunicipalityEmployee') && role.MunicipalityId == municipalityId)) {
                    $scope.submitOnBehalf = true;
                    return true;
                }
            });
        }

        iterateLoading();
    }


    $scope.lvGetFormFieldsonSuccess = function(variable, data) {
        data.forEach(function(formField, index) {
            if (formField.formFieldTypes.label === 'Number') {
                $scope.formData[formField.fieldName] = parseFloat(formField.defaultValue);
            } else if (formField.formFieldTypes.label === 'Boolean') {
                $scope.formData[formField.fieldName] = (formField.defaultValue === 'true');
            } else if (formField.formFieldTypes.label === 'Date+Time') {
                $scope.formData[formField.fieldName] = (!formField.defaultValue ? null : moment(formField.defaultValue, "YYYY-MM-DD HH:mm:ss").toDate().getTime());
            } else if (formField.formFieldTypes.label === 'Date') {
                $scope.formData[formField.fieldName] = (!formField.defaultValue ? null : moment(formField.defaultValue, "YYYY-MM-DD").toDate().getTime());
            } else {
                $scope.formData[formField.fieldName] = formField.defaultValue;
            }
        });

        iterateLoading();
    };

    $scope.searchOnBehalfOfUserChange = function($event, $isolateScope, newVal, oldVal) {};

    function generateIdString(collection) {
        var idString = '';
        for (let i = 0; i < collection.length; i++) {
            if (i > 0) {
                idString += ',';
            }
            idString += collection[i].ID;
        }

        return idString;
    }

    function submitForm(ownerId) {
        if ($scope.ownerInfo === true) {
            if (!!ownerId) {
                $scope.Variables.svSubmitForm.setInput('ownerId', ownerId);
            } else if ($scope.Widgets.checkboxOtherOwner.datavalue || $scope.Widgets.checkboxVendorIsOwner.datavalue) {
                if (!$scope.Widgets.lfOwner.formfields.id.value) {
                    $scope.Widgets.lfOwner.formfields.contactType.value = 'Owner';
                    $scope.Widgets.lfOwner.formfields.active.value = true;
                    $scope.Widgets.lfOwner.save();
                    return; // TBC
                } else {
                    $scope.Variables.svSubmitForm.setInput('ownerId', $scope.Widgets.lfOwner.formfields.id.value);
                }
            } else if ($scope.Widgets.gridOwners.selectedItems.length > 0) {
                $scope.Variables.svSubmitForm.setInput('ownerId', $scope.Widgets.gridOwners.selectedItems[0].id);
            }
        }

        $scope.Variables.svSubmitForm.setInput('locationIds', generateIdString($scope.Variables.stvGisData.dataSet));
        $scope.Variables.svSubmitForm.setInput('vendorIds', generateIdString($scope.Variables.stvVendors.dataSet));
        $scope.Variables.svSubmitForm.setInput('usersWithWhomToShare', generateIdString($scope.Variables.stvContacts.dataSet));
        $scope.Variables.stvVendors.dataSet.some(function(vendorData, index) {
            if (vendorData.Primary) {
                $scope.Variables.svSubmitForm.setInput('primaryVendorId', vendorData.ID);
                return true;
            }
        });
        $scope.Variables.svSubmitForm.update();
    }

    $scope.newFormWizardDone = function($isolateScope, steps) {
        if ($scope.submitOnBehalf && ($scope.Widgets.checkboxNewUser.datavalue === true || (!!$scope.Widgets.searchOnBehalfOfUser._proxyModel && !!$scope.Widgets.searchOnBehalfOfUser._proxyModel.id))) {
            if ($scope.Widgets.checkboxNewUser.datavalue === true) {
                $scope.Widgets.lfSubmitOnBehalf.save();
                return; // submitForm will be run after the live form saves the new user
            } else if (!!$scope.Widgets.searchOnBehalfOfUser._proxyModel && !!$scope.Widgets.searchOnBehalfOfUser._proxyModel.id) {
                $scope.Variables.svSubmitForm.setInput('behalfOfUserId', $scope.Widgets.searchOnBehalfOfUser._proxyModel.id);
            }
        }

        submitForm();
    };


    $scope.svSubmitFormonSuccess = function(variable, data) {
        if ($scope.documents && $scope.Variables.stvDocuments.dataSet.length > 0) {
            $scope.Variables.svUploadDocuments.setInput('formGuid', data);
            var documentFiles = [];

            $scope.Variables.stvDocuments.dataSet.forEach(function(document, index) {
                documentFiles.push(document.Contents);
            });

            $scope.Variables.svUploadDocuments.setInput('files', documentFiles);

            $scope.Variables.svUploadDocuments.update();
        } else {
            $scope.Variables.goToPage_UserOpenForms.navigate();
        }
    };

    $scope.checkboxNewUserChange = function($event, $isolateScope, newVal, oldVal) {
        $scope.Widgets.searchOnBehalfOfUser.reset();
        $scope.Widgets.lfSubmitOnBehalf.clearData();
        $scope.Widgets.lfSubmitOnBehalf.formfields.country.value = 'USA';
    };


    $scope.wizardstep2Skip = function($isolateScope, currentStep, stepIndex) {
        $scope.Widgets.checkboxNewUser.reset();
        $scope.Widgets.searchOnBehalfOfUser.reset();
        $scope.Widgets.lfSubmitOnBehalf.clearData();
    };


    $scope.lfSubmitOnBehalfSuccess = function($event, $operation, $data) {
        $scope.Variables.svSubmitForm.setInput('behalfOfUserId', $data.id);
        submitForm();
    };


    $scope.newFormWizardCancel = function($isolateScope, steps) {
        $location.path("/");
    };

    $scope.checkForExistingOwner = function(vendorData, gisId) {
        var vendorInfo = vendorData || _.find($scope.Variables.stvVendors.dataSet, {
            'Primary': true
        });
        var gisRecordId = gisId || $scope.Widgets.gisRecordSelect.datavalue;
        var ownerExists = false;
        $scope.Widgets.gridOwners.gridData.some(function(gridOwner, index) {
            if (gridOwner.firstName === vendorInfo.Company && gridOwner.email === vendorInfo.EmailAddress && gridOwner.gisrecords.id === gisRecordId) {
                ownerExists = true;
                $scope.Widgets.lfOwner.formfields.id.value = gridOwner.id;
                return true;
            }
        });

        if (!ownerExists) {
            $scope.Widgets.lfOwner.formfields.id.value = null;
        }
    };


    $scope.checkboxVendorIsOwnerChange = function($event, $isolateScope, newVal, oldVal) {
        if (newVal === true) {
            var vendorInfo = _.find($scope.Variables.stvVendors.dataSet, {
                'Primary': true
            });
            $scope.Widgets.lfOwner.formfields.firstName.value = vendorInfo.Company;
            $scope.Widgets.lfOwner.formfields.address1.value = vendorInfo.Address1;
            $scope.Widgets.lfOwner.formfields.address2.value = vendorInfo.Address2;
            $scope.Widgets.lfOwner.formfields.city.value = vendorInfo.City;
            $scope.Widgets.lfOwner.formfields.states.value = {
                id: vendorInfo.StateId,
                stateName: vendorInfo.State
            };
            $scope.Widgets.lfOwner.formfields.postalCode.value = vendorInfo.PostalCode;
            $scope.Widgets.lfOwner.formfields.phone.value = vendorInfo.PhoneNumber;
            $scope.Widgets.lfOwner.formfields.email.value = vendorInfo.EmailAddress;
            $scope.Widgets.lfOwner.formfields.country.value = vendorInfo.Country;

            $scope.checkForExistingOwner(vendorInfo);
        } else {
            $scope.Widgets.lfOwner.clearData();
        }
    };


    $scope.lfOwnerSuccess = function($event, $operation, $data) {
        submitForm($data.id);
    };


    $scope.svUploadDocumentsonSuccess = function(variable, data) {
        $scope.Variables.goToPage_UserOpenForms.navigate();
    };

    function setLFFieldsRO(liveForm, readOnly) {
        liveForm.formFields.forEach(function(field, index) {
            if (field.show === true) {
                field.readonly = readOnly;
                if (field.name !== 'notes' && field.name !== 'address2') {
                    field.required = !readOnly;
                }
            }
        });
    }

    $scope.wizardstep9Load = function($isolateScope, stepIndex) {
        if (!$scope.Widgets.checkboxOtherOwner.datavalue) {
            setLFFieldsRO($scope.Widgets.lfOwner, true);
        }

        if ($scope.Widgets.gridOwners.gridData.length === 1) {
            $timeout(function() {
                $('div[name=gridOwners] div.app-grid-content table td[data-col-id=0]').click();
            }, 1);
        }
    };


    $scope.checkboxOtherOwnerChange = function($event, $isolateScope, newVal, oldVal) {
        if (newVal === true) {
            setLFFieldsRO($scope.Widgets.lfOwner, false);
        } else {
            setLFFieldsRO($scope.Widgets.lfOwner, true);
        }
    };

    $scope.svGetOwnersonSuccess = function(variable, data) {};

}]);


Application.$controller("lfSubmitOnBehalfController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridLocationController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.deleterowAction = function($event, $rowData) {
            var rowIndex = _.findIndex($scope.Variables.stvGisData.dataSet, {
                'ID': $rowData.ID
            });

            $scope.Variables.stvGisData.dataSet.splice(rowIndex, 1);
        };

    }
]);



Application.$controller("gridNewFormDocumentsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogAddGISRecordController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.canAddParcel = function() {
            var activeTabIndex = $scope.Widgets.tabsAddGISRecord.activeTabIndex;
            if (activeTabIndex === 0) {
                return ($scope.Widgets.searchAddress.datavalue === undefined);
            } else if (activeTabIndex === 1) {
                return ($scope.Widgets.searchSubdivision.datavalue === undefined);
            } else {
                return ($scope.Widgets.searchParcel.datavalue === undefined);
            }
        };

        function addGisRecord(gisRecord) {
            if (!gisRecord) {
                return;
            }

            var rowIndex = _.findIndex($scope.Variables.stvGisData.dataSet, {
                'ID': gisRecord.id
            });

            if (rowIndex === -1) {
                $scope.Variables.stvGisData.dataSet.push({
                    "ID": gisRecord.id,
                    "SubdivisionName": gisRecord.subdivisions.subdivision,
                    "Parcel": gisRecord.parcel,
                    "Lot": gisRecord.lot,
                    "Section": gisRecord.section,
                    "StreetNumber": gisRecord.streetNumber,
                    "StreetName": gisRecord.streetName,
                    "City": gisRecord.city,
                    "State": gisRecord.states.stateName,
                    "InspectionZone": gisRecord.inspectionZone,
                    "FullAddress": gisRecord.fullAddress
                });
            }
        }

        $scope.buttonAddLocationClick = function($event, $isolateScope) {
            var activeTabIndex = $scope.Widgets.tabsAddGISRecord.activeTabIndex;
            if (activeTabIndex === 0) {
                addGisRecord($scope.Widgets.searchAddress.datavalue);
            } else if (activeTabIndex === 1) {
                addGisRecord($scope.Widgets.searchSubdivision.datavalue);
            } else {
                addGisRecord($scope.Widgets.searchParcel.datavalue);
            }

            $scope.Widgets.dialogAddGISRecord.close();
        };

    }
]);

Application.$controller("dialogUploadDocumentController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.documentsToUpload = [];

        $scope.dialogUploadDocumentOpened = function($event, $isolateScope) {
            $scope.documentsToUpload = [];
        };


        $scope.buttonAddDocumentClick = function($event, $isolateScope) {
            $scope.Variables.stvDocuments.dataSet = $scope.Variables.stvDocuments.dataSet.concat($scope.documentsToUpload);
            $scope.documentsToUpload = [];
        };

    }
]);

Application.$controller("gridSharingController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.customRowAction = function($event, $rowData) {
            var rowIndex = _.findIndex($scope.Variables.stvContacts.dataSet, {
                'ID': $rowData.ID
            });

            $scope.Variables.stvContacts.dataSet.splice(rowIndex, 1);
        };

    }
]);

Application.$controller("dialogAddContactController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.button7Click = function($event, $isolateScope) {
            var newContact = $scope.Widgets.searchContact._proxyModel;
            var rowIndex = _.findIndex($scope.Variables.stvContacts.dataSet, {
                'ID': newContact.id
            });

            if (rowIndex === -1) {
                $scope.Variables.stvContacts.dataSet.push({
                    ID: newContact.id,
                    FirstName: newContact.firstName,
                    LastName: newContact.lastName,
                    Email: newContact.email
                });
            }
        };

    }
]);

Application.$controller("gridVendorsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.changePrimaryVendor = function($event, $rowData) {
            $event.preventDefault();
            if ($rowData.Primary) {
                return;
            } else {
                $scope.Variables.stvVendors.dataSet.forEach(function(vendorData, index) {
                    vendorData.Primary = (vendorData.ID === $rowData.ID);
                });
            }
        };

        $scope.customRowAction = function($event, $rowData) {
            var rowIndex = _.findIndex($scope.Variables.stvVendors.dataSet, {
                'ID': $rowData.ID
            });

            $scope.Variables.stvVendors.dataSet.splice(rowIndex, 1);

            if ($rowData.Primary && $scope.Variables.stvVendors.dataSet.length > 0) {
                $scope.Variables.stvVendors.dataSet[0].Primary = true;
            }
        };

    }
]);

Application.$controller("dialogAddVendorController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.buttonAddVendorClick = function($event, $isolateScope) {
            var newVendor = $scope.Widgets.searchVendors._proxyModel;
            var rowIndex = _.findIndex($scope.Variables.stvVendors.dataSet, {
                'ID': newVendor.vendor.id
            });

            if (rowIndex === -1) {
                $scope.Variables.stvVendors.dataSet.push({
                    ID: newVendor.vendor.id,
                    Company: newVendor.vendor.companyName,
                    PhoneNumber: newVendor.vendor.companyPhone,
                    EmailAddress: newVendor.vendor.companyEmail,
                    Website: newVendor.vendor.companyWebsite,
                    Address1: newVendor.vendor.address1,
                    Address2: newVendor.vendor.address2,
                    City: newVendor.vendor.city,
                    State: newVendor.vendor.states.stateName,
                    StateId: newVendor.vendor.states.id,
                    PostalCode: newVendor.vendor.postalCode,
                    Country: newVendor.vendor.country,
                    Primary: $scope.Variables.stvVendors.dataSet.length === 0
                });
            }
        };

    }
]);

Application.$controller("lfOwnerController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.gisRecordSelectChange = function($event, $isolateScope, newVal, oldVal) {
            $scope.$parent.checkForExistingOwner(null, parseInt(newVal));
        };

    }
]);

Application.$controller("gridOwnersController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.selectOwner = function($event, $rowData) {
            $event.preventDefault();
        };
    }
]);