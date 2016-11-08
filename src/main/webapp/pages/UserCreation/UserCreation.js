Application.$controller("UserCreationPageController", ["$scope", function($scope) {
    "use strict";
    $scope.newUser;
    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        $scope.toDay = Date.parse(new Date().toDateString());

    };


    var selectedMunicipalites = [];
    $scope.checkboxsetMunicipalitesChange = function($event, $isolateScope, newVal, oldVal) {
        selectedMunicipalites = newVal;
    };

    $scope.CreateUseronSuccess = function(variable, data) {
        $scope.newUser = data;
        $scope.newUserId = data.id;

    };


    $scope.wizard1Done = function($isolateScope, steps) {
        for (var i = 0; i < selectedMunicipalites.length; i++) {
            $scope.Variables.RegisterSubscriptions.setInput({
                "dateSubscribed": $scope.toDay,
                "users": $scope.newUser,
                "userId": $scope.newUser.id,
                "municipalities": selectedMunicipalites[i],
                "municipalityId": selectedMunicipalites[i].id
            });
            $scope.Variables.RegisterSubscriptions.insertRecord();
        }
        $scope.Variables.NewUserToLogin.navigate();

    };

}]);

Application.$controller("liveform2Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);