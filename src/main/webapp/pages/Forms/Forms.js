Application.$controller("FormsPageController", ["$scope", "$timeout", function($scope, $timeout) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        $scope.today = moment().valueOf();

    };

    $scope.allFormStatus;
    $scope.FormStatusonSuccess = function(variable, data) {
        $scope.allFormStatus = data;
        // For showing current Status of form
        var currentStatus = _.findIndex(data, {
            'id': _.parseInt($scope.pageParams.FormStatusId)
        });
        $scope.defaultObjectForSelectStatus = data[currentStatus];
        $timeout(function() {
            var a = $('.livelist-status li.app-list-item:nth-child(' + (currentStatus + 1) + ')').addClass('active');
        });
    };




    $scope.lvFormTypeFieldsonSuccess = function(variable, data) {
        console.log(data);
        console.log(variable);
    };


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
        $scope.Variables.CloseOrOpenFormByGUID.setInput({
            'closed': $scope.Widgets.selectStatus.datavalue.considerClosed,
            'FormGUID': $scope.pageParams.FormGUID
        });;
        $scope.Variables.CloseOrOpenFormByGUID.update();
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