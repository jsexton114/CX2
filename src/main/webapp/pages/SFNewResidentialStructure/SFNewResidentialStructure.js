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

    // Updating the status to record
    $scope.Cx2SfnewResidentialStructureDataonSuccess = function(variable, data) {
        $scope.Variables.UserUpdateRecordStatus.setInput({
            'tableName': data.formTypes.tbLocation,
            'formStatusId': $scope.Variables.GetDefaultStatusByFormAndSortOrder.dataSet.data[0].id,
            'recordId': data.id
        });
        $scope.Variables.UserUpdateRecordStatus.update();
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
    }
]);