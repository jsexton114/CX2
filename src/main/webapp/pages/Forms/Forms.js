Application.$controller("FormsPageController", ["$scope", "$timeout", function($scope, $timeout) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        $scope.today = moment().valueOf();

    };
    $scope.sharedWith
    $scope.allFormStatus;
    $scope.FormStatusonSuccess = function(variable, data) {
        setFormStatusListValue(data, null);
    };

    var statusListData = null;
    var currentStatusId = null;

    function setFormStatusListValue(listData, statusId) {
        if (!!listData) {
            statusListData = listData;
        }

        if (!!statusId) {
            currentStatusId = statusId;
        }

        if (!!statusListData && !!currentStatusId) {
            $scope.allFormStatus = statusListData;
            // For showing current Status of form
            var currentStatus = _.findIndex(statusListData, {
                'id': _.parseInt(currentStatusId)
            });
            $scope.defaultObjectForSelectStatus = statusListData[currentStatus];
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

        // Setting  Closed or Open according to Status
        $scope.Variables.CloseOrOpenFormByGUID.setInput({
            'closed': $scope.Widgets.selectStatus.datavalue.considerClosed,
            'FormGUID': $scope.pageParams.FormGUID
        });
        $scope.Variables.CloseOrOpenFormByGUID.update();

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


    $scope.CurrentFormonSuccess = function(variable, data) {
        setFormStatusListValue(null, data[0].formStatusId);
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