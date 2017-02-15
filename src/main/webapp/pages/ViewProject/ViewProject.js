Application.$controller("ViewProjectPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    var crum;
    $scope.addMember;
    $scope.onPageReady = function() {
        $scope.today = moment().valueOf();
        //var temp = $scope.Variables.BreadCrumb.dataSet;
        $scope.addMember = false;
    };


    $scope.CurrentProjectonSuccess = function(variable, data) {
        debugger
        if ($scope.Variables.loggedInUser.dataSet.id == data[0].usersByCreatedBy.id) {
            $scope.addMember = true;
        }
        var temp = $scope.Variables.loggedInUser.dataSet.roles;
        //Checking if user is muniadmin or cxadmin
        for (let i = 0; i < temp.length; i++) {
            if ((temp[i] == "MunicipalityAdmin") || (temp[i] == "CXAdmin")) {
                $scope.addMember = true;
            }
        }
    };

}]);

Application.$controller("gridProjectMembersController", ["$scope",
    function($scope) {
        "use strict";
        debugger
        $scope.ctrlScope = $scope;
        $scope.addMember
    }
]);

Application.$controller("dialogAddMemberController", ["$scope",
    function($scope) {
        "use strict";
        debugger
        $scope.ctrlScope = $scope;
        $scope.today = moment().valueOf();
        $scope.addMember
    }
]);

Application.$controller("gridLocationController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogAddGISRecordController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.today = moment().valueOf();

        $scope.buttonAddByAddressClick = function($event, $isolateScope) {
            $scope.Variables.AddGIStoProjects.setInput({
                'GISRecordId': $scope.Widgets.searchAddress.datavalue.id
            });
            $scope.Variables.AddGIStoProjects.update();
        };


        $scope.buttonAddBySubdivisionClick = function($event, $isolateScope) {
            $scope.Variables.AddGIStoProjects.setInput({
                'GISRecordId': $scope.Widgets.searchSubdivision.datavalue.id
            });
            $scope.Variables.AddGIStoProjects.update();
        };


        $scope.buttonAddByParcelClick = function($event, $isolateScope) {
            $scope.Variables.AddGIStoProjects.setInput({
                'GISRecordId': $scope.Widgets.searchParcel.datavalue.id
            });
            $scope.Variables.AddGIStoProjects.update();
        };

    }
]);

Application.$controller("dialogParcelController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridProjectFormsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogAddFormsToProjectController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);