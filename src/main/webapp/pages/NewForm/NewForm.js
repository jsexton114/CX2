Application.$controller("NewFormPageController", ["$scope", "$location", "$timeout", "$http", "$compile", function($scope, $location, $timeout, $http, $compile) {
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

        $scope.draftId = !!$location.search().draftId ? parseInt($location.search().draftId) : undefined;
        if (!!$scope.draftId) {
            // Check lvDraftonSuccess for what happens from here on out
        } else {
            $scope.formTypeId = parseInt($location.search().formTypeId);
        }

        $(".app-wizard-actions-right").prepend($compile('<button class="btn btn-primary" style="margin-right: 24px" type="button" margin="unset 0.5em" name="btnSaveAsDraft" ng-click="saveAsDraft($event, $scope)"><i class="wi wi-save"></i> Save as Draft</button>')($scope));
    };

    $scope.loaded = false;
    $scope.draftData = null;

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
    $scope.signature = false;

    $scope.formData = {};
    $scope.locationIds = '';
    $scope.vendorIds = '';
    $scope.sharedUserString = '';

    function iterateLoading() {
        itemsLoaded += 1;

        if (itemsLoaded == totalItemsToLoad) {
            $scope.loaded = true;

            $timeout(function() {
                if ($scope.draftData !== null) { // Load draft data
                    // Submit on Behalf
                    if ($scope.submitOnBehalf === true) {
                        angular.forEach($scope.draftData.onBehalfOf, function(value, key) {
                            if (!$scope.Widgets.lfSubmitOnBehalf.formfields[key]) {
                                return; // continue; this is not a part of the form, but rather a value for the 'New User' checkbox or other metadata
                            }

                            $scope.Widgets.lfSubmitOnBehalf.formfields[key].value = value;
                        });

                        $scope.Widgets.checkboxNewUser.datavalue = !!$scope.draftData.onBehalfOf.newUser;

                        if (!$scope.draftData.onBehalfOf.newUser && !!$scope.draftData.onBehalfOf.id) {
                            $scope.draftData.onBehalfOf.fullName = ($scope.draftData.onBehalfOf.firstName + " " + $scope.draftData.onBehalfOf.lastName);
                            $scope.Widgets.searchOnBehalfOfUser.datavalue = $scope.draftData.onBehalfOf;
                        }
                    }

                    // Form data
                    angular.forEach($scope.draftData.formFields, function(value, key) {
                        $scope.formData[key] = value;
                    });

                    // Location(s)
                    $scope.Variables.stvGisData.dataSet = angular.copy($scope.draftData.locations);

                    // Contractor(s)
                    $scope.Variables.stvVendors.dataSet = angular.copy($scope.draftData.vendors);

                    // Owner
                    if ($scope.ownerInfo === true) {
                        var newOwner = $scope.draftData.owner.ownerType === 'new';
                        var ownerIsContractor = $scope.draftData.owner.ownerType === 'contractor';

                        if (newOwner || ownerIsContractor) {
                            $scope.Widgets.checkboxOtherOwner.datavalue = newOwner;
                            $scope.Widgets.checkboxVendorIsOwner.datavalue = ownerIsContractor;

                            angular.forEach($scope.draftData.owner, function(value, key) {
                                if (!$scope.Widgets.lfOwner.formfields[key]) {
                                    return; // continue; this is not a part of the form, but rather a value for checkboxes or other meta data
                                }

                                $scope.Widgets.lfOwner.formfields[key].value = value;
                            });
                        } else if ($scope.draftData.owner.ownerType === 'selected') {
                            // Select owner in the table - see gridOwnersDatarender for continuation
                        }
                    }

                    // Attachments
                    if ($scope.documents === true) {
                        $scope.draftData.attachments.forEach(function(attachment, index) {
                            $scope.Variables.stvDocuments.dataSet.push({
                                Filename: attachment.Filename,
                                Mimetype: attachment.Mimetype,
                                Contents: base64ToFile(attachment.Contents, attachment.Filename, attachment.Mimetype)
                            });
                        });
                    }
                }
            });
        }
    }

    function base64ToFile(base64String, filename, mimetype) {
        var byteString = atob(base64String);

        var arrayBuffer = new ArrayBuffer(byteString.length);
        var uint8Array = new Uint8Array(arrayBuffer);

        for (var i = 0; i < byteString.length; i++) {
            uint8Array[i] = byteString.charCodeAt(i);
        }

        var blob = new Blob([arrayBuffer]);

        return new File([blob], filename, {
            type: mimetype
        });
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
        $scope.signature = formType.requireSignature;

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
                    $scope.Widgets.lfOwner.formfields.gisrecords.value = {
                        id: $scope.Widgets.gisRecordSelect.datavalue
                    };
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

        // Form data
        $scope.Variables.svSubmitForm.setInput('fieldDataJsonString', JSON.stringify($scope.formData));

        // Documents
        if ($scope.documents && $scope.Variables.stvDocuments.dataSet.length > 0) {
            var documentFiles = [];

            $scope.Variables.stvDocuments.dataSet.forEach(function(document, index) {
                documentFiles.push(document.Contents);
            });

            $scope.Variables.svSubmitForm.setInput('attachments', documentFiles);
        } else {
            $scope.Variables.svSubmitForm.setInput('attachments', []);
        }

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
        $scope.Variables.goToPage_UserOpenForms.navigate();
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
            }
        });
    }

    $scope.wizardstep9Load = function($isolateScope, stepIndex) {
        if (!$scope.Widgets.checkboxOtherOwner.datavalue) {
            let defaultOtherOwner = $scope.$eval("vendorInfo === false && !Variables.svGetOwners.dataSet.content.length");
            $scope.Widgets.checkboxOtherOwner.datavalue = defaultOtherOwner;

            if (!defaultOtherOwner) {
                setLFFieldsRO($scope.Widgets.lfOwner, true);
            }
        }

        if ($scope.Widgets.gridOwners.gridData.length === 1) {
            $timeout(function() {
                $('div[name=gridOwners] div.app-grid-content table td[data-col-id=0]').click();
            }, 1);
        }
    };

    $scope.checkboxOtherOwnerChange = function($event, $isolateScope, newVal, oldVal) {
        setLFFieldsRO($scope.Widgets.lfOwner, !newVal);
    };

    $scope.svGetOwnersonSuccess = function(variable, data) {};

    $scope.buttonCreateSigningDocumentClick = function($event, $isolateScope) {

        // TODO: get values from WM
        $scope.Variables.svGetSignLink.setInput('formGuid', "983DD3B3-C40C-E711-80C9-0CC47A46DD63");
        $scope.Variables.svGetSignLink.setInput('formTitle', "form title test");
        $scope.Variables.svGetSignLink.setInput('creatorFullName', "creatorFullName test");
        console.log('JSON.stringify($scope.formData): ' + JSON.stringify($scope.formData));
        $scope.Variables.svGetSignLink.setInput('fieldDataJsonString', JSON.stringify($scope.formData));
        $scope.Variables.svGetSignLink.setInput('municipalityLogo', null);
        // TODO: the following strings should come from the municipalities eSign Genie settiings
        $scope.Variables.svGetSignLink.setInput('clientId', "a4bb2dd0071640b6936f5cf80cf533b4");
        $scope.Variables.svGetSignLink.setInput('clientSecret', "268ebb57a93e4ef197235c68111ed5a6");
        $scope.Variables.svGetSignLink.setInput('firstNameOfRecipientParty', "Jason");
        $scope.Variables.svGetSignLink.setInput('lastNameOfRecipientParty', "Sexton");
        $scope.Variables.svGetSignLink.setInput('emailIdOfRecipientParty', "jason_sexton@hotmail.com");



        $scope.Variables.svGetSignLink.update();

    };

    $scope.svGetSignLinkonSuccess = function(variable, data) {
        // TODO: get folderAccessUrl from folder response json
        $scope.Variables.stvEsignUrl.dataSet.dataValue = data;
        $scope.Widgets.dialogSignForm.open();
    };

    function getDraftModel() {
        var draftModel = {
            onBehalfOf: {},
            formFields: {},
            locations: [],
            vendors: [],
            owner: {},
            attachments: []
        };

        // Submit on behalf
        $scope.Widgets.lfSubmitOnBehalf.formFields.forEach(function(field, index) {
            if (field.name === 'All fields') {
                return;
            }

            draftModel.onBehalfOf[field.key] = field.value;
        });

        draftModel.onBehalfOf.newUser = $scope.Widgets.checkboxNewUser.datavalue;

        // Form Fields
        draftModel.formFields = angular.copy($scope.formData);

        // Owner
        if ($scope.ownerInfo === true) {
            var ownerType = '';

            if (!!$scope.Widgets.checkboxOtherOwner.datavalue) {
                ownerType = 'new';
            } else if (!!$scope.Widgets.checkboxVendorIsOwner.datavalue) {
                ownerType = 'contractor';
            } else {
                ownerType = 'selected';
            }

            if (ownerType !== 'selected') {
                $scope.Widgets.lfOwner.formFields.forEach(function(field, index) {
                    if (field.name === 'All fields') {
                        return;
                    }

                    draftModel.owner[field.key] = field.value;
                });
            } else if ($scope.Widgets.gridOwners.selectedItems.length > 0) {
                draftModel.owner.ownerId = $scope.Widgets.gridOwners.selectedItems[0].id;
            } else {
                draftModel.owner.ownerType = 'none';
            }

            draftModel.owner.ownerType = ownerType;
        } else {
            draftModel.owner.ownerType = 'none';
        }

        // Locations
        draftModel.locations = angular.copy($scope.Variables.stvGisData.dataSet);

        // Vendors
        draftModel.vendors = angular.copy($scope.Variables.stvVendors.dataSet);

        return draftModel;
    }

    function doDraftSave(draftModel) {
        $scope.Variables.svSaveDraft.setInput("String", JSON.stringify(draftModel));
        $scope.Variables.svSaveDraft.update();
    }

    $scope.saveAsDraft = function($event, $isolateScope) {
        var draftModel = getDraftModel();

        // Attachments - Doing this within here because it involves multiple asynchronous calls to FileReader.readAsDataURL
        if ($scope.documents === true && $scope.Variables.stvDocuments.dataSet.length > 0) {
            var attachmentsLoaded = 0;
            $scope.Variables.stvDocuments.dataSet.forEach(function(document, index) {
                var fr = new FileReader();
                fr.addEventListener("load", function() {
                    draftModel.attachments.push({
                        Contents: fr.result.split(',')[1],
                        Filename: document.Filename,
                        Mimetype: document.Mimetype
                    });

                    attachmentsLoaded++;

                    if (attachmentsLoaded === $scope.Variables.stvDocuments.dataSet.length) {
                        doDraftSave(draftModel);
                    }
                }, false);

                fr.readAsDataURL(document.Contents);
            });
        } else {
            doDraftSave(draftModel);
        }
    };

    $scope.svSaveDraftonSuccess = function(variable, data) {
        $scope.draftId = data;
    };

    $scope.lvDraftonSuccess = function(variable, data) {
        if (!data.length) { // Draft didn't exist for that ID, so we'll try to load the form if a formTypeId was provided
            // TODO: Add draft not found notification
            if (!!$location.search().formTypeId) {
                $scope.formTypeId = parseInt($location.search().formTypeId);
            }

            return;
        }

        var draftData = data[0];

        $scope.draftData = JSON.parse(draftData.formData);
        $scope.formTypeId = draftData.formTypeId;
    };


    $scope.gridOwnersDatarender = function($isolateScope, $data) {
        if (!!$scope.draftData.owner.ownerId) {
            var selectedOwner = _.find($data, {
                'id': $scope.draftData.owner.ownerId
            });

            $timeout(function() {
                if (!!selectedOwner) {
                    $isolateScope.selectItem(selectedOwner);
                }
            });
        }
    };

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
                return (!$scope.Widgets.searchAddress.datavalue.id);
            } else if (activeTabIndex === 1) {
                return (!$scope.Widgets.searchSubdivision.datavalue.id);
            } else {
                return (!$scope.Widgets.searchParcel.datavalue.id);
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

        $scope.changePrimaryVendor = function($event, newPrimaryVendorData) {
            if (!!$event) $event.preventDefault();

            $scope.Variables.stvVendors.dataSet.forEach(function(vendorData, index) {
                vendorData.Primary = (vendorData.ID === newPrimaryVendorData.ID);
            });
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
            var newVendor = $scope.Widgets.searchVendors.datavalue;
            var rowIndex = _.findIndex($scope.Variables.stvVendors.dataSet, {
                'ID': newVendor.id
            });

            if (rowIndex === -1) {
                $scope.Variables.stvVendors.dataSet.push({
                    ID: newVendor.id,
                    Company: newVendor.companyName,
                    PhoneNumber: newVendor.companyPhone,
                    EmailAddress: newVendor.companyEmail,
                    Website: newVendor.companyWebsite,
                    Address1: newVendor.address1,
                    Address2: newVendor.address2,
                    City: newVendor.city,
                    State: newVendor.states.stateName,
                    StateId: newVendor.states.id,
                    PostalCode: newVendor.postalCode,
                    Country: newVendor.country,
                    Primary: ($scope.Variables.stvVendors.dataSet.length === 0 ? true : $scope.Widgets.checkboxVendorIsPrimary.datavalue)
                });
            }

            if ($scope.Variables.stvVendors.dataSet.length > 1 && $scope.Widgets.checkboxVendorIsPrimary.datavalue) {
                $scope.Widgets.gridVendors.changePrimaryVendor(null, {
                    ID: newVendor.id
                });
            }

            $scope.Widgets.checkboxVendorIsPrimary.reset();
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

Application.$controller("eSignIframeDialogController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogSignFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.dialogSignFormClose = function($event, $isolateScope) {
            //$scope.Variables.goToPage_UserOpenForms.navigate();
            // TODO: get document status from eSign Genie, if not signed then page is not valid
        };

        $scope.dialogSignFormOpened = function($event, $isolateScope) {

        };

    }
]);