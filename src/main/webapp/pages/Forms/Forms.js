Application.$controller("FormsPageController", ["$scope", "$timeout", function($scope, $timeout) {
    "use strict";

    var currentBreadCrumb = null;

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        $scope.today = moment().valueOf();
        var breadCrumbs = $scope.Variables.BreadCrumb.dataSet;
        currentBreadCrumb = breadCrumbs[breadCrumbs.length - 1];
        currentBreadCrumb.link += $scope.pageParams.FormGUID;
    };
    $scope.sharedWith;
    $scope.allFormStatus;





    $scope.FormStatusonSuccess = function(variable, data) {
        setFormStatusProgressValue();
    };

    $scope.defaultObjectForSelectStatus = {};
    $scope.currentProgress = 0;

    function setFormStatusProgressValue(newStatusId) {
        var statusListData = $scope.Variables.FormStatus.dataSet.data;
        var currentStatusId = newStatusId || $scope.Variables.CurrentForm.dataSet.data[0].formStatusId;

        if (!!newStatusId) {
            $scope.defaultObjectForSelectStatus = statusListData[newStatusId];
        }

        if (!!statusListData && !!currentStatusId) {
            $scope.allFormStatus = statusListData;
            // For showing current Status of form
            var currentStatus = _.findIndex(statusListData, {
                'id': _.parseInt(currentStatusId)
            });
            $scope.defaultObjectForSelectStatus = statusListData[currentStatus];
            $scope.currentProgress = parseInt(!!$scope.defaultObjectForSelectStatus.considerClosed ? 100 : ((currentStatus) / statusListData.length * 100));
            $timeout(function() {
                var a = $('.livelist-status li.app-list-item:nth-child(' + (currentStatus + 1) + ')').addClass('active');
            });
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
        if (!((isCXAdminMunicipalityAdmin == 1) || (found > -1))) {
            $scope.Widgets.panelFormReview.show = false;
        }
    };


    $scope.UpdateFormStatusInMasterFormsonSuccess = function(variable, data) {
        setFormStatusProgressValue($scope.Widgets.selectStatus._proxyModel.id);

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

    };

    $scope.SharedWithDataonSuccess = function(variable, data) {
        $scope.sharedWith = data;
    };

    $scope.lvFormTypeonSuccess = function(variable, data) {
        currentBreadCrumb.label = data[0].formType;
        // For Hiding Locations
        if ((data[0].gisrecord == false) && (data[0].multipleGisrecords == false)) {
            $scope.showMaps = false;
        } else {
            $scope.showMaps = true;
        }

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
            $scope.Widgets.tabpaneLocation.show = false;
        }
    };


    $scope.RecordFormHistoryonSuccess = function(variable, data) {
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