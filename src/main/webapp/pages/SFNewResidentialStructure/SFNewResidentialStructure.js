Application.$controller("SFNewResidentialStructurePageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        var charSet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
        var randomString = '';
        for (var i = 0; i < 32; i++) {
            var randomPoz = Math.floor(Math.random() * charSet.length);
            randomString += charSet.substring(randomPoz, randomPoz + 1);
        }
        $scope.setGUID = randomString;
    };


    $scope.Cx2SfnewResidentialStructureDataonSuccess = function(variable, data) {
        debugger
        // Updating the status to record
        $scope.Variables.UserUpdateRecordStatus.setInput({
            'tableName': data.formTypes.tbLocation,
            'formStatusId': $scope.Variables.GetDefaultStatusByFormAndSortOrder.dataSet.data[0].id,
            'recordId': data.id
        });
        $scope.Variables.UserUpdateRecordStatus.update();

        // Recording Form Entry In Master Forms
        $scope.Variables.MasterFormInsertion.setInput({
            'formGuid': data.guid,
            'formStatusId': $scope.Variables.GetDefaultStatusByFormAndSortOrder.dataSet.data[0].id
        });
        $scope.Variables.MasterFormInsertion.insertRecord();
    };



    $scope.GetDefaultStatusByFormAndSortOrderonSuccess = function(variable, data) {
        $scope.Widgets.liveformSFNewResidential.save();
    };

}]);


Application.$controller("liveformController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveformSFNewResidentialController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.saveAction = function($event) {
            // Setting sort order to be in "APPLICATION" status
            $scope.Variables.GetDefaultStatusByFormAndSortOrder.setFilter({
                'formTypeId': $scope.pageParams.FormId,
                'sortOrder': '2'
            });
            $scope.Variables.GetDefaultStatusByFormAndSortOrder.update();
        };

        $scope.cancelAction = function($event) {
            // Setting sort order to be in "DRAFT" status
            $scope.Variables.GetDefaultStatusByFormAndSortOrder.setFilter({
                'formTypeId': $scope.pageParams.FormId,
                'sortOrder': '1'
            });
            $scope.Variables.GetDefaultStatusByFormAndSortOrder.update();
        };

    }
]);